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
import Model.Movimentacao;
import Model.Nota_Fiscal;
import Model.Produto_Deposito;
import Model.Movimentacao;
import Model.Movimentacao;
import Model.Produto_Movimentacao;
import Model.Produto_Nota_Fiscal;
import Model.Produto_Movimentacao;
import Util.HibernateUtil;


public class MovimentacaoDAO {
	
	public void Registrar_MOV(Movimentacao MOV) {
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		
		if(MOV.getTipo_Movimentacao().equalsIgnoreCase("VENDA") || MOV.getTipo_Movimentacao().equalsIgnoreCase("DEVOLUCAO")) {
			//verifica os estoques dos produtos
						
			if(this.Valida_Produtos(MOV.getProdutos(), MOV.getTipo_Movimentacao())) {
				Transaction trans = null;
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					//salva produtos		
					Produto_Deposito PD = new Produto_Deposito();
					for (Produto_Movimentacao PMOV : MOV.getProdutos()) {
						
						PD= new Produto_Deposito();
						PD.setProduto(PMOV.getProduto());
						PD.setDeposito(PMOV.getDeposito());
						PD.setQtd(PMOV.getQuantidade());
						
						PDDAO.Processar_Saida_Estoque(PD);
						Session.clear();
						trans = Session.beginTransaction();	
						Session.saveOrUpdate(PMOV);
						trans.commit();
					}
					//salva MOV
					Session.clear();
					trans = Session.beginTransaction();	
					Session.saveOrUpdate(MOV);
					trans.commit();
					
				} catch (Exception e) {
					e.printStackTrace();
					trans.rollback();
				}
				
			}else {
				//throw de nao foi possível salvar nota (erro estoque produtos)
			}
		}else if(MOV.getTipo_Movimentacao().equalsIgnoreCase("COMPRA")) {
			if(this.Valida_Produtos(MOV.getProdutos(), MOV.getTipo_Movimentacao())) {
				Transaction trans = null;
				try (Session Session = HibernateUtil.getSessionFactory().openSession()){
					//salva produtos		
					Produto_Deposito PD = new Produto_Deposito();
					for (Produto_Movimentacao PMOV : MOV.getProdutos()) {
						
						PD= new Produto_Deposito();
						PD.setProduto(PMOV.getProduto());
						PD.setDeposito(PMOV.getDeposito());
						PD.setQtd(PMOV.getQuantidade());
												
						PDDAO.Processar_Entrada_Estoque(PD);
						Session.clear();
						trans = Session.beginTransaction();	
						Session.saveOrUpdate(PMOV);
						trans.commit();
					}
					//salva MOV
					Session.clear();
					trans = Session.beginTransaction();	
					Session.saveOrUpdate(MOV);
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
	
	
	
	private boolean Valida_Produtos(List<Produto_Movimentacao> Produtos, String Tipo_Movimentacao) {
		boolean valida_NF = true;
		Produto_Deposito pd = new Produto_Deposito();
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		if(Tipo_Movimentacao.equalsIgnoreCase("Venda") || Tipo_Movimentacao.equalsIgnoreCase("Devolucao")) {
			for (Produto_Movimentacao PMOV : Produtos) {
				pd = new Produto_Deposito();
				pd.setDeposito(PMOV.getDeposito());
				pd.setProduto(PMOV.getProduto());
				pd.setQtd(PMOV.getQuantidade());
				
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
		}else if(Tipo_Movimentacao.equalsIgnoreCase("Compra")) {
			for (Produto_Movimentacao PMOV : Produtos) {
				pd = new Produto_Deposito();
				pd.setDeposito(PMOV.getDeposito());
				pd.setProduto(PMOV.getProduto());
				pd.setQtd(PMOV.getQuantidade());
				
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
	
	
	public Movimentacao Consulta_Movimentacao(String Numeracao_Movimentacao) {
		Transaction trans = null;

		Movimentacao MOV = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			MOV = Session.get(Movimentacao.class, Numeracao_Movimentacao);
			trans.commit();
			
			if(MOV != null) {
				MOV.setProdutos(this.Consulta_Produtos(MOV.getNumeracao()));
								
			}else {
				throw new IllegalArgumentException("Erro: Movimentação não encontrada");
			}
			

		} catch (Exception e) {
			trans.rollback();
		}
		
		return MOV;
	}
		
	
	
	
	
	private List<Produto_Movimentacao> Consulta_Produtos(String Tipo_Movimentacao){
		List<Produto_Movimentacao> PNF = new ArrayList<Produto_Movimentacao>();
				
		Transaction trans = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			Session.beginTransaction();
			List<Produto_Movimentacao> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Movimentacao> criteryQuery = builder.createQuery(Produto_Movimentacao.class);
			Root<Produto_Movimentacao> rootEntry = criteryQuery.from(Produto_Movimentacao.class);
			CriteriaQuery<Produto_Movimentacao> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Movimentacao> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();
			
			DepositoDAO DDAO = new DepositoDAO();
			ProdutoDAO PDAO = new ProdutoDAO();
			
			for (Produto_Movimentacao Produto_NF : lista) {
				if(Produto_NF.getNumeracao_Movimentacao().equalsIgnoreCase(Tipo_Movimentacao)) {
					PNF.add(Produto_NF);
								
				}
			}

		} catch (Exception e) {
			trans.rollback();
		}
		return PNF;	
		
	}
	
	

}
