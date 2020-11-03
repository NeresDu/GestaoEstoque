package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Funcionario;
import Util.HibernateUtil;

public class FuncionarioDAO extends GenericoDAO<Funcionario>{
	public Funcionario DeleteFuncionario(int id) {
		Transaction trans = null;
		Funcionario d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Funcionario.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Funcionario GetFuncionarioById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Funcionario d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Funcionario.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	
}
