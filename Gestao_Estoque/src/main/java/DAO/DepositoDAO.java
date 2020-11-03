package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;
import Model.Deposito;


public class DepositoDAO extends GenericoDAO<Deposito>{
	
	public Deposito DeleteDeposito(int id) {
		Transaction trans = null;
		Deposito d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Deposito.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Deposito GetDepositoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Deposito d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Deposito.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
