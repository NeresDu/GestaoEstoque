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
		public void EntradaEstoque(Produto prod, Deposito dep, int quant) {
			Transaction trans = null;

			try (Session Session = HibernateUtil.getSessionFactory().openSession()){

				trans = Session.beginTransaction();
				Produto_Deposito d = null;
				/*
				 * System.out.println(proddep.getIdInt()); d =
				 * Session.get(Produto_Deposito.class, proddep.getIdInt());
				 * //System.out.println(d.getDeposito().getIdInt()+"   "+proddep.getDeposito().
				 * getIdInt()); trans.commit();
				 */
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
						System.out.println("ja existe "+pd.getIdInt());
						pd.setEstoque(pd.getEstoque()+quant);
						Session.saveOrUpdate(pd);
						trans.commit();
						estoqueAtualizado = true;
						break;
					}
				}
				if(!estoqueAtualizado) {
					Produto_Deposito produtodeposito = new Produto_Deposito();
					produtodeposito.setDeposito(dep);
					produtodeposito.setProduto(prod);
					produtodeposito.setEstoque(quant);
					produtodeposito.setCodigo(13);
					 
					 Session.save(produtodeposito);
						
						trans.commit();
					 
					
				}


			} catch (Exception e) {
				e.printStackTrace();
				trans.rollback();
			}
		}
		/*
		 * public int VerificaEstoque(Produto prod) {
		 * 
		 * }
		 */
		
	 

}
