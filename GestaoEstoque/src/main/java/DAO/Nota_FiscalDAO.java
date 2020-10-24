package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Funcionario;
import Model.Nota_Fiscal;
import Model.Produto_Nota_Fiscal;
import Util.HibernateUtil;

public class Nota_FiscalDAO {

	public void Registrar(Nota_Fiscal NF, List<Produto_Nota_Fiscal> Produtos, Funcionario Funcionario) {
		Transaction trans = null;

		Produto_DepositoDAO PDAO = new Produto_DepositoDAO();
		
		if(NF.getUtilizacao().equalsIgnoreCase("VENDA") || NF.getUtilizacao().equalsIgnoreCase("DEVOLUCAO")) {
			for (Produto_Nota_Fiscal PNF : Produtos) {
				if(PDAO.VerificaEstoque(PNF.getProduto(), PNF.getDeposito()) < PNF.getQuantidade() ) {
					throw new IllegalArgumentException("Erro: Quantia de produto invalido");
				}
			}
			
			try (Session Session = HibernateUtil.getSessionFactory().openSession()){
				Session.clear();

				System.out.println("entrou no subtrair estoque");
				//Session.save(t);

				trans.commit();

			} catch (Exception e) { e.printStackTrace(); trans.rollback(); }

		}else {
			if(Funcionario.getCargo().equalsIgnoreCase("COMPRADOR")) {
				
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					trans = Session.beginTransaction();
					Session.save(NF);
					trans.commit();
					
					Session.clear();
					
					for (Produto_Nota_Fiscal PNF : Produtos) {
						Session.save(PNF);
						trans.commit();
						
						PDAO.ProcessarEntrada(PNF.getProduto(), PNF.getDeposito(), PNF.getQuantidade());
						
						System.out.println("NF cadastrada");
						
					}
					
					

				} catch (Exception e) { e.printStackTrace(); trans.rollback(); }
				
			}else {
				throw new IllegalArgumentException("Erro: Funcionario não altorizado");
			}
			

		}
		

	}
}
