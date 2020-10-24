package DAO;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;
import Util.HibernateUtil;

public class Produto_DepositoDAO {

	
	  public void Coxinha(Produto p, Deposito d) { Transaction
	  trans = null;
	  
	  Produto_Deposito pdep = null; try (Session Session =
	  HibernateUtil.getSessionFactory().openSession()){
	  
	  trans = Session.beginTransaction();
	  Produto_Deposito prod_dep = null;
		/*
		 * if(true) { prod_dep = Session.
		 * 
		 * }
		 */
	  
	  // verificar se o produto e deposito ja existem para entao conseguir vincular
	  //esse produto a um deposito com seu estoque, //parar atualizar iremos utilizar
	 // o metodo update //if(pdep = Session.fin(p, p.getId()))
	  
	  //Session.save(t);
	  
	  trans.commit();
	  
	  } catch (Exception e) { 
		  e.printStackTrace(); 
		  trans.rollback(); 
		  } 
	  }
		public void ProcessarEntrada(Produto prod, Deposito dep, int quant) {
			Transaction trans = null;

			try (Session Session = HibernateUtil.getSessionFactory().openSession()){

				trans = Session.beginTransaction();
				Produto_Deposito d = null;
				List<Produto_Deposito> lista = null;
				CriteriaBuilder builder = Session.getCriteriaBuilder();
				CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
				Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
				CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

				TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
				lista = allQuery.getResultList();
				Boolean estoqueAtualizado = false;
				for (Produto_Deposito pd : lista) {
					if(pd.getDeposito().getIdInt()==dep.getIdInt()) {
						System.out.println("produto deposito encontrado ID:"+pd.getIdInt());
						if((!(pd.getEstoque_Maximo() < VerificaEstoque(prod, dep)+quant))) {
							pd.setEstoque(pd.getEstoque()+quant);
							Session.saveOrUpdate(pd);
							trans.commit();
							estoqueAtualizado = true;
							break;
						}
						else {
							System.out.println("Voce tentou adicionar mais que o estoque maximo permitido para esse produto nesse deposito");
						}
					}
				}
				Session.clear();
				if(!estoqueAtualizado) {
					Produto_Deposito produtodeposito = new Produto_Deposito();
					produtodeposito.setDeposito(dep);
					produtodeposito.setProduto(prod);
					produtodeposito.setEstoque(quant);
					produtodeposito.setCodigo(20);
					 
					 Session.save(produtodeposito);
						
						trans.commit();
					
				}


			} catch (Exception e) {
				e.printStackTrace();
				trans.rollback();
			}
		}
		
		  public int VerificaEstoque(Produto prod, Deposito dep) {
				Transaction trans = null;

				try (Session Session = HibernateUtil.getSessionFactory().openSession()){

					trans = Session.beginTransaction();
					Produto_Deposito d = null;
					List<Produto_Deposito> lista = null;
					CriteriaBuilder builder = Session.getCriteriaBuilder();
					CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
					Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
					CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);
					TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
					lista = allQuery.getResultList();
					
					for (Produto_Deposito pd : lista) {
						if(pd.getDeposito().getIdInt()==dep.getIdInt() && pd.getProduto().getIdInt()==prod.getIdInt()) {
							System.out.println("produto encontrado estoque atual de "+pd.getEstoque()+" Estoque maximo: "+pd.getEstoque_Maximo()
							+" Estoque maximo: "+pd.getEstoque_Minimo());
							pd.setEstoque(pd.getEstoque());
							return pd.getEstoque();
						
						}
					}
					trans.commit();

				} catch (Exception e) {
					e.printStackTrace();
					
					trans.rollback();
				}
				return 0;
				
		  
		  }
		  public void setProduto_Deposito(int idProd_Dep ,double custo, int estoqMin, int estoqMax) {
			  Produto_Deposito pde =null;
			  
			  
			  Transaction trans = null;

				try (Session Session = HibernateUtil.getSessionFactory().openSession()){

					trans = Session.beginTransaction();
					 pde = Session.get(Produto_Deposito.class, idProd_Dep);
					 
					  pde.setCusto(custo);
					  pde.setEstoque_Maximo(estoqMin);
					  pde.setEstoque_Maximo(estoqMax);
					Session.update(pde);
					

					trans.commit();

				} catch (Exception e) {
					e.printStackTrace();
					trans.rollback();
				}
			  
		  }
			public void ProcessarSaida(Produto prod, Deposito dep, int quant) {
				Transaction trans = null;

				try (Session Session = HibernateUtil.getSessionFactory().openSession()){

					trans = Session.beginTransaction();
					Produto_Deposito d = null;
					List<Produto_Deposito> lista = null;
					CriteriaBuilder builder = Session.getCriteriaBuilder();
					CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
					Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
					CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

					TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
					lista = allQuery.getResultList();
					Boolean estoqueAtualizado = false;
					for (Produto_Deposito pd : lista) {
						if(pd.getDeposito().getIdInt()==dep.getIdInt()) {
							System.out.println("produto deposito encontrado ID:"+pd.getIdInt());
							if((pd.getEstoque_Minimo() < VerificaEstoque(prod, dep)-quant)) {
								pd.setEstoque(pd.getEstoque()-quant);
								Session.saveOrUpdate(pd);
								trans.commit();
								estoqueAtualizado = true;
								break;
							}
							else {
								System.out.println("Voce tentou remover mais que o estoque minimo permitido para esse produto nesse deposito");
							}
						}
					}
					Session.clear();
					if(estoqueAtualizado==false) {
						Produto_Deposito produtodeposito = new Produto_Deposito();
						produtodeposito.setDeposito(dep);
						produtodeposito.setProduto(prod);
						produtodeposito.setEstoque(quant);
						produtodeposito.setCodigo(20);
						 
						 Session.save(produtodeposito);
							
							trans.commit();
						
					}


				} catch (Exception e) {
					e.printStackTrace();
					trans.rollback();
				}
			}
		  
		 
		
	 

}
