package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Perecivel;
import Model.Perecivel;
import Util.HibernateUtil;

public class PerecivelDAO extends GenericoDAO<Perecivel>{
	public Perecivel DeletePerecivel(int id) {
		Transaction trans = null;
		Perecivel d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Perecivel.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Perecivel GetPerecivelById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Perecivel d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Perecivel.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
