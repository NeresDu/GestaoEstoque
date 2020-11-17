package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Fornecedor_Cliente;
import Model.Funcionario;
import Model.Nota_Fiscal;
import Model.Produto_Deposito;
import Model.Produto_Nota_Fiscal;
import Util.HibernateUtil;

public class Nota_FiscalDAO {

	public void Registrar_NF(Nota_Fiscal NF) {
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		
		if(NF.getUtilizacao().equalsIgnoreCase("VENDA") || NF.getUtilizacao().equalsIgnoreCase("DEVOLUCAO")) {
			//verifica os estoques dos produtos
						
			if(this.Valida_Produtos_Nota(NF.getProdutos(), NF.getUtilizacao())) {
				Transaction trans = null;
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					//salva produtos		
					Produto_Deposito PD = new Produto_Deposito();
					for (Produto_Nota_Fiscal PNF : NF.getProdutos()) {
						
						PD= new Produto_Deposito();
						PD.setProduto(PNF.getProduto());
						PD.setDeposito(PNF.getDeposito());
						PD.setQtd(PNF.getQuantidade());
						
						PDDAO.Processar_Saida_Estoque(PD);
						Session.clear();
						trans = Session.beginTransaction();	
						Session.saveOrUpdate(PNF);
						trans.commit();
					}
					//salva nf
					Session.clear();
					trans = Session.beginTransaction();	
					Session.saveOrUpdate(NF);
					trans.commit();
					
				} catch (Exception e) {
					e.printStackTrace();
					trans.rollback();
				}
				
			}else {
				//throw de nao foi possível salvar nota (erro estoque produtos)
			}
		}else if(NF.getUtilizacao().equalsIgnoreCase("COMPRA")) {
			if(this.Valida_Produtos_Nota(NF.getProdutos(), NF.getUtilizacao())) {
				Transaction trans = null;
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					//salva produtos		
					Produto_Deposito PD = new Produto_Deposito();
					for (Produto_Nota_Fiscal PNF : NF.getProdutos()) {
						
						PD= new Produto_Deposito();
						PD.setProduto(PNF.getProduto());
						PD.setDeposito(PNF.getDeposito());
						PD.setQtd(PNF.getQuantidade());
												
						PDDAO.Processar_Entrada_Estoque(PD);
						Session.clear();
						trans = Session.beginTransaction();	
						Session.saveOrUpdate(PNF);
						trans.commit();
					}
					//salva nf
					Session.clear();
					trans = Session.beginTransaction();	
					Session.saveOrUpdate(NF);
					trans.commit();
					
				} catch (Exception e) {
					e.printStackTrace();
					trans.rollback();
				}
				
			}else {
				//throw de nao foi possível salvar nota (erro estoque produtos)
			}
			
		}else {
			throw new IllegalArgumentException("Erro: Utilização da nota fiscal inválida");
		}
	}
	
	private boolean Valida_Produtos_Nota(List<Produto_Nota_Fiscal> Produtos, String Utilizacao) {
		boolean valida_NF = true;
		Produto_Deposito pd = new Produto_Deposito();
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		if(Utilizacao.equalsIgnoreCase("Venda") || Utilizacao.equalsIgnoreCase("Devolucao")) {
			for (Produto_Nota_Fiscal PNF : Produtos) {
				pd = new Produto_Deposito();
				pd.setDeposito(PNF.getDeposito());
				pd.setProduto(PNF.getProduto());
				pd.setQtd(PNF.getQuantidade());
				
				pd = PDDAO.Buscar_Produto_Deposito(pd);
				
				//verifica se ao debitar se o estoque nao fica negativo
				if(pd.getIdInt() >= 1) {
					if(!(pd.getEstoque_Minimo() < PDDAO.Verificar_Estoque(pd)-pd.getQtd())) {
						valida_NF = false;
						throw new IllegalArgumentException("Erro: remover mais que o estoque minimo permitido para esse produto nesse deposito");
					}
				}else {
					valida_NF = false;
					throw new IllegalArgumentException("Erro: Não há estoque do produto");
				}
			}
		}else if(Utilizacao.equalsIgnoreCase("Compra")) {
			for (Produto_Nota_Fiscal PNF : Produtos) {
				pd = new Produto_Deposito();
				pd.setDeposito(PNF.getDeposito());
				pd.setProduto(PNF.getProduto());
				pd.setQtd(PNF.getQuantidade());
				
				pd = PDDAO.Buscar_Produto_Deposito(pd);
				
				//verifica se ao debitar se o estoque nao fica negativo
				if(pd.getIdInt() >= 1) {
					if((pd.getEstoque_Maximo() < PDDAO.Verificar_Estoque(pd)+pd.getQtd())) {
						valida_NF = false;
						throw new IllegalArgumentException("Erro: remover mais que o estoque minimo permitido para esse produto nesse deposito");
					}
				}else {
					valida_NF = false;
					throw new IllegalArgumentException("Erro: Não há estoque do produto");
				}
			}
		}
		return valida_NF;
	}
	
	
	
	public Nota_Fiscal Consulta_Nota_Fiscal(String Numeracao_Nota_Fiscal) {
		Transaction trans = null;

		Nota_Fiscal NF = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			NF = Session.get(Nota_Fiscal.class, Numeracao_Nota_Fiscal);
			trans.commit();
			
			if(NF != null) {
				Fornecedor_ClienteDAO FCDAO = new Fornecedor_ClienteDAO();
				//FCDAO.GetById(Fornecedor_Cliente.class, NF.getFornecedor_Cliente().getId());
						
				NF.setProdutos(this.Consulta_Produtos(NF.getNumeracao()));
								
			}else {
				throw new IllegalArgumentException("Erro: Nota fiscal não encontrada");
			}
			

		} catch (Exception e) {
			trans.rollback();
		}
		return NF;
	}
	
	private List<Produto_Nota_Fiscal> Consulta_Produtos(String Nota_Fiscal_Numeracao){
		List<Produto_Nota_Fiscal> PNF = new ArrayList<Produto_Nota_Fiscal>();
				
		Transaction trans = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			Session.beginTransaction();
			List<Produto_Nota_Fiscal> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Nota_Fiscal> criteryQuery = builder.createQuery(Produto_Nota_Fiscal.class);
			Root<Produto_Nota_Fiscal> rootEntry = criteryQuery.from(Produto_Nota_Fiscal.class);
			CriteriaQuery<Produto_Nota_Fiscal> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Nota_Fiscal> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();
			
			DepositoDAO DDAO = new DepositoDAO();
			ProdutoDAO PDAO = new ProdutoDAO();
			
			for (Produto_Nota_Fiscal Produto_NF : lista) {
				if(Produto_NF.getNumeracao_NF().equalsIgnoreCase(Nota_Fiscal_Numeracao)) {
					PNF.add(Produto_NF);
								
				}
			}

		} catch (Exception e) {
			trans.rollback();
		}
		return PNF;	
		
	}
	
	
}
