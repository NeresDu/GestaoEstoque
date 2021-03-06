package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Funcionario;
import Model.Nota_Fiscal;
import Model.Produto_Deposito;
import Model.Produto_Nota_Fiscal;
import Util.HibernateUtil;

public class Nota_FiscalDAO {

	public void Registrar(Nota_Fiscal NF, List<Produto_Nota_Fiscal> Produtos, Funcionario Funcionario) {
		Transaction trans = null;

		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		
		if(NF.getUtilizacao().equalsIgnoreCase("VENDA") || NF.getUtilizacao().equalsIgnoreCase("DEVOLUCAO")) {
			for (Produto_Nota_Fiscal PNF : Produtos) {
				if(PDDAO.VerificaEstoque(PNF.getProduto(), PNF.getDeposito()) < PNF.getQuantidade() ) {
					throw new IllegalArgumentException("Erro: Quantia de produto invalido");
				}
			}
			
			try (Session Session = HibernateUtil.getSessionFactory().openSession()){
				trans = Session.beginTransaction();
				Session.save(NF);
				trans.commit();
				
				
				for (Produto_Nota_Fiscal PNF : Produtos) {
					Session.clear();
					trans = Session.beginTransaction();
					Session.save(PNF);
					trans.commit();
					
					PDDAO = new Produto_DepositoDAO();
					PDDAO.ProcessarSaida(PNF.getProduto(), PNF.getDeposito(), PNF.getQuantidade());
					
				}

			} catch (Exception e) { e.printStackTrace(); trans.rollback(); }

		}else {
			if(Funcionario.getCargo().equalsIgnoreCase("COMPRADOR")) {
				
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					trans = Session.beginTransaction();
					Session.save(NF);
					trans.commit();
					
					
					for (Produto_Nota_Fiscal PNF : Produtos) {
						Session.clear();
						trans = Session.beginTransaction();
						Session.save(PNF);
						trans.commit();
						
						PDDAO = new Produto_DepositoDAO();
						PDDAO.ProcessarEntrada(PNF.getProduto(), PNF.getDeposito(), PNF.getQuantidade());
						
					}

				} catch (Exception e) { e.printStackTrace(); trans.rollback(); }
				
			}else {
				throw new IllegalArgumentException("Erro: Funcionario n�o altorizado");
			}
		}
	}
	
	
	public Nota_Fiscal Consulta_Nota_Fiscal(String Numeracao_Nota_Fiscal) {
		Transaction trans = null;

		Nota_Fiscal NF = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			NF = Session.get(Nota_Fiscal.class, Numeracao_Nota_Fiscal);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return NF;
	}
	
	public List<Produto_Nota_Fiscal> Consulta_Produtos(String Nota_Fiscal_Numeracao){
		List<Produto_Nota_Fiscal> PNF = new ArrayList<Produto_Nota_Fiscal>();
		
		Transaction trans = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();
			List<Produto_Nota_Fiscal> Lista = new ArrayList<Produto_Nota_Fiscal>();

			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Nota_Fiscal> criteryQuery = builder.createQuery(Produto_Nota_Fiscal.class);
			Root<Produto_Nota_Fiscal> rootEntry = criteryQuery.from(Produto_Nota_Fiscal.class);
			CriteriaQuery<Produto_Nota_Fiscal> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Nota_Fiscal> allQuery = Session.createQuery(all);
			Lista = allQuery.getResultList();

			for (Produto_Nota_Fiscal produto_Nota_Fiscal : Lista) {
				if(produto_Nota_Fiscal.getNota_Fiscal().getNumeracao().equalsIgnoreCase(Nota_Fiscal_Numeracao)) {
					PNF.add(produto_Nota_Fiscal);
				}
			}

		} catch (Exception e) {
			trans.rollback();
		}
		return PNF;	
		
	}
}
