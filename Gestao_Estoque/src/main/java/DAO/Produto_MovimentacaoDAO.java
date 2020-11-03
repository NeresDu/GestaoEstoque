package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Funcionario;
import Model.Produto_Movimentacao;
import Model.Produto_Movimentacao;
import Util.HibernateUtil;

public class Produto_MovimentacaoDAO extends GenericoDAO<Produto_Movimentacao>{
	public Produto_Movimentacao DeleteProduto_Movimentacao(int id) {
		Transaction trans = null;
		Produto_Movimentacao d = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Produto_Movimentacao.class, id);
			Session.remove(d);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}
	public Produto_Movimentacao GetProduto_MovimentacaoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Produto_Movimentacao d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Produto_Movimentacao.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}

}
