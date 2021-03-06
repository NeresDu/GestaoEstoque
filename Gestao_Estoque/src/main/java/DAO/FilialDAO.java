package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Filial;
import Model.Filial;
import Util.HibernateUtil;

public class FilialDAO extends GenericoDAO<Filial>{
	public Filial DeleteFilial(int id) {
		Transaction trans = null;
		Filial d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Filial.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Filial GetFilialById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Filial d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Filial.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
