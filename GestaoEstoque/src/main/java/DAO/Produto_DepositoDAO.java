package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;
import Util.HibernateUtil;

public class Produto_DepositoDAO extends GenericoDAO<Produto_Deposito>{
	
	public void Save(Produto p, int idProd, Deposito d, int idDep) {
		Transaction trans = null;
		
		Produto_Deposito pdep = null;
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			
			// verificar se o produto e deposito ja existem para entao conseguir vincular esse produto a um deposito com seu estoque,
			//parar atualizar iremos utilizar o metodo update
			//if(pdep = Session.fin(p, p.getId()))
			
			//Session.save(t);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

}
