package DAO;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Produto;
import Util.HibernateUtil;

public class ProdutoDAO {
	public void SaveProduto(Produto Produto) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();

			Session.save(Produto);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
	}


	
	public void UpdateProduto(Produto Produto) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();

			Session.saveOrUpdate(Produto);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
	}

	public Produto GetProdutoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Produto Produto = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			Produto = Session.get(Produto.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return Produto;
	}

	public List<Produto> GetAllProduto() {
		Transaction trans = null;

		List<Produto> Produto = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();


			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto> criteryQuery = builder.createQuery(Produto.class);
			Root<Produto> rootEntry = criteryQuery.from(Produto.class);
			CriteriaQuery<Produto> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto> allQuery = Session.createQuery(all);
			Produto = allQuery.getResultList();

		} catch (Exception e) {
			trans.rollback();
		}
		return Produto;
	}

	public void DeleteProduto(int Codigo) {
		Transaction trans = null;
		Produto Produto = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			Produto = Session.get(Produto.class, Codigo);
			Session.remove(Produto);

			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
	}
}
