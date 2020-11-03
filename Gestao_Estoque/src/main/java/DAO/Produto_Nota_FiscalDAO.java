package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Produto_Nota_Fiscal;
import Model.Movimentacao;
import Model.Produto_Nota_Fiscal;
import Util.HibernateUtil;

public class Produto_Nota_FiscalDAO extends GenericoDAO<Produto_Nota_Fiscal>{

	public Produto_Nota_Fiscal DeleteProduto_Nota_Fiscal(int id) {
		Transaction trans = null;
		Produto_Nota_Fiscal d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Produto_Nota_Fiscal.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Produto_Nota_Fiscal GetProduto_Nota_FiscalById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Produto_Nota_Fiscal d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Produto_Nota_Fiscal.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
}
