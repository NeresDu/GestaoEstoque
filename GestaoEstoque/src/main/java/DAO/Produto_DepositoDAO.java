package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;
import Util.HibernateUtil;

public class Produto_DepositoDAO {

	
	  public void Save(Produto p, Deposito d) { Transaction
	  trans = null;
	  
	  Produto_Deposito pdep = null; try (Session Session =
	  HibernateUtil.getSessionFactory().openSession()){
	  
	  trans = Session.beginTransaction();
	  //Produto_Deposito prod_dep = null;
	  //String Pcodp = p.getIdInt();
	  //if() {
	//	  prod_dep = Session.get(p, );
		  
	 // }
	  
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
	 

}
