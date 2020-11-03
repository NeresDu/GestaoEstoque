package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Deposito;
import Model.Funcionario;
import Model.Movimentacao;
import Model.Movimentacao;
import Model.Movimentacao;
import Model.Produto_Movimentacao;
import Model.Produto_Movimentacao;
import Util.HibernateUtil;


public class MovimentacaoDAO extends GenericoDAO<Movimentacao>{
	
	public void Registrar(Movimentacao M, List<Produto_Movimentacao> Produtos) {
		Transaction trans = null;

		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		
		if(M.getTipo_Movimentacao().equalsIgnoreCase("VENDA") || M.getTipo_Movimentacao().equalsIgnoreCase("DEVOLUCAO")) {
			for (Produto_Movimentacao PM : Produtos) {
				if(PDDAO.VerificaEstoque(PM.getProduto(), PM.getDeposito()) < PM.getQuantidade() ) {
					throw new IllegalArgumentException("Erro: Quantia de produto invalido");
				}
			}
			
			try (Session Session = HibernateUtil.getSessionFactory().openSession()){
				trans = Session.beginTransaction();
				Session.save(M);
				trans.commit();
				
				
				for (Produto_Movimentacao PM : Produtos) {
					Session.clear();
					trans = Session.beginTransaction();
					Session.save(PM);
					trans.commit();
					
					PDDAO = new Produto_DepositoDAO();
					PDDAO.ProcessarSaida(PM.getProduto(), PM.getDeposito(), PM.getQuantidade());
					
				}

			} catch (Exception e) { e.printStackTrace(); trans.rollback(); }

		}else {
			try (Session Session = HibernateUtil.getSessionFactory().openSession()){
				trans = Session.beginTransaction();
				Session.save(M);
				trans.commit();


				for (Produto_Movimentacao PM : Produtos) {
					Session.clear();
					trans = Session.beginTransaction();
					Session.save(PM);
					trans.commit();

					PDDAO = new Produto_DepositoDAO();
					PDDAO.ProcessarEntrada(PM.getProduto(), PM.getDeposito(), PM.getQuantidade());

				}

			} catch (Exception e) { e.printStackTrace(); trans.rollback(); }


		}
	}
	
	public Movimentacao Consulta_Movimentacao(String Numeracao_Movimentacao) {
		Transaction trans = null;

		Movimentacao M = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			M = Session.get(Movimentacao.class, Numeracao_Movimentacao);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return M;
	}
	public List<Produto_Movimentacao> Consulta_Produtos(String Movimentacao_Numeracao){
		List<Produto_Movimentacao> PM = new ArrayList<Produto_Movimentacao>();
		
		Transaction trans = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();
			List<Produto_Movimentacao> Lista = new ArrayList<Produto_Movimentacao>();

			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Movimentacao> criteryQuery = builder.createQuery(Produto_Movimentacao.class);
			Root<Produto_Movimentacao> rootEntry = criteryQuery.from(Produto_Movimentacao.class);
			CriteriaQuery<Produto_Movimentacao> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Movimentacao> allQuery = Session.createQuery(all);
			Lista = allQuery.getResultList();

			for (Produto_Movimentacao produto_Movimentacao: Lista) {
				if(produto_Movimentacao.getMovimentacao().getNumeracao().equalsIgnoreCase(Movimentacao_Numeracao)) {
					PM.add(produto_Movimentacao);
				}
			}

		} catch (Exception e) {
			trans.rollback();
		}
		return PM;	
		
	}
	public Movimentacao GetMovimentacaoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Movimentacao d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Movimentacao.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
