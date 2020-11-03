package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Risco;
import Model.Risco;
import Util.HibernateUtil;

public class RiscoDAO extends GenericoDAO<Risco> {
	public Risco DeleteRisco(int id) {
		Transaction trans = null;
		Risco d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Risco.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Risco GetRiscoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Risco d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Risco.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
