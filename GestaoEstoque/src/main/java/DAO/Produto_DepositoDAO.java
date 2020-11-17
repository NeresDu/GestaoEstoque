package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;
import Util.HibernateUtil;

public class Produto_DepositoDAO {

	
	 public void Processar_Entrada_Estoque(Produto_Deposito PD) {
		Produto_Deposito pd = null;
		Transaction trans = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();	
			pd = this.Buscar_Produto_Deposito(PD);
			if(pd !=null) {
				if((!(pd.getEstoque_Maximo() < Verificar_Estoque(PD) +PD.getQtd()))) {
					pd.setEstoque(pd.getEstoque()+PD.getQtd());
					Session.update(pd);
					trans.commit();
				}
				else {
					throw new IllegalArgumentException("Erro: Adicionar mais que o estoque maximo permitido para esse produto nesse deposito");
				}
			}else {
				Session.clear();
				Session.save(PD);
				trans.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}
	

	public int Verificar_Estoque(Produto_Deposito PD) {
		Transaction trans = null;
		Produto_Deposito pd = null;
		
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			pd = this.Buscar_Produto_Deposito(PD);
			if(pd !=null) {
				return pd.getEstoque();
			}else {
				throw new IllegalArgumentException("Erro: Produto não encontrado no depósito");
			}

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return 0;
	}
	
	public void Atualiza_Minimo_Maximo(Produto_Deposito PD) {
		Produto_Deposito pd = null;
		Transaction trans = null;
		
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			pd = this.Buscar_Produto_Deposito(PD);
			
			if(pd != null) {
				PD.setCodigo(pd.getIdInt());
				trans = Session.beginTransaction();
				Session.clear();
				Session.update(PD);
				trans.commit();

			}else {
				throw new IllegalArgumentException("Erro: Não há do produto nesse depósito");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

	}
	
	//para a saída de produtos deve ser fornecido a quantia
	public void Processar_Saida_Estoque(Produto_Deposito PD) {
		Transaction trans = null;
		Produto_Deposito pd = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();	
			pd = this.Buscar_Produto_Deposito(PD);
			if(pd !=null) {
				if((pd.getEstoque_Minimo() < Verificar_Estoque(PD)-PD.getQtd())) {
					pd.setEstoque(pd.getEstoque()-PD.getQtd());
					Session.saveOrUpdate(pd);
					trans.commit();
				}else {
					throw new IllegalArgumentException("Erro: remover mais que o estoque minimo permitido para esse produto nesse deposito");
				}	
			}else {
				throw new IllegalArgumentException("Erro: Produto não consta no deposito");
			}

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}
	
	
	public Produto_Deposito Buscar_Produto_Deposito(Produto_Deposito PD) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			/*
			Produto_Deposito PD_aux = new Produto_Deposito();
			
			Session.beginTransaction();
			//criação da query
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteria = builder.createQuery(Produto_Deposito.class);
		    Root<Produto_Deposito> root = criteria.from(Produto_Deposito.class);
		    
		    ParameterExpression<Integer> Parameter = builder.parameter(Integer.class);
		    
		    criteria.select(root).where(
		    		builder.equal(root.get("Produto_Codigo"), Parameter),
		    		builder.equal(root.get("Deposito_Codigo"), Parameter)
		    		);
			
			//execução da query			
			TypedQuery<Produto_Deposito> Query = Session.createQuery(criteria);
			Query.setParameter("Produto_Codigo", PD.getProduto().getId());
			Query.setParameter("Deposito_Codigo", PD.getDeposito().getId());
			PD_aux = Query.getSingleResult();
			
			System.out.println("teste");
			
			*/
			Session.beginTransaction();
			Produto_Deposito d = null;
			List<Produto_Deposito> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
			Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
			CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();

			for (Produto_Deposito pd : lista) {
				if(pd.getDeposito().getIdInt()==PD.getDeposito().getIdInt()) {
					return pd;
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return null;

	}
	
	
	
	//retornar todos os produtos e em quais depositos estão
	//retornar todos os produtos de um deposito





}
