package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Fornecedor_Cliente;
import Model.Fornecedor_Cliente;
import Model.Fornecedor_Cliente;
import Util.HibernateUtil;

public class Fornecedor_ClienteDAO extends GenericoDAO<Fornecedor_Cliente>{

	public Fornecedor_Cliente DeleteFornecedor_Cliente(int id) {
		Transaction trans = null;
		Fornecedor_Cliente d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Fornecedor_Cliente.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Fornecedor_Cliente GetFornecedor_ClienteById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Fornecedor_Cliente d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Fornecedor_Cliente.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
