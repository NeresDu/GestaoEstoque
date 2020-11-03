package DAO;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;
import Util.HibernateUtil;

public class Produto_DepositoDAO {

	public void ProcessarEntrada(Produto prod, Deposito dep, int quant) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			Produto_Deposito d = null;
			List<Produto_Deposito> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
			Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
			CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();
			Boolean estoqueAtualizado = false;
			for (Produto_Deposito pd : lista) {
				if(pd.getDeposito().getIdInt()==dep.getIdInt()) {
					if((!(pd.getEstoque_Maximo() < VerificaEstoque(prod, dep)+quant))) {
						pd.setEstoque(pd.getEstoque()+quant);
						Session.saveOrUpdate(pd);
						trans.commit();
						estoqueAtualizado = true;
						break;
					}
					else {
						throw new IllegalArgumentException("Erro: Adicionar mais que o estoque maximo permitido para esse produto nesse deposito");
					}
				}
			}
			Session.clear();
			if(!estoqueAtualizado) {
				Produto_Deposito produtodeposito = new Produto_Deposito();
				produtodeposito.setDeposito(dep);
				produtodeposito.setProduto(prod);
				produtodeposito.setEstoque(quant);

				Session.save(produtodeposito);

				trans.commit();

			}


		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public int VerificaEstoque(Produto prod, Deposito dep) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			Produto_Deposito d = null;
			List<Produto_Deposito> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
			Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
			CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);
			TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();

			for (Produto_Deposito pd : lista) {
				if(pd.getDeposito().getIdInt()==dep.getIdInt() && pd.getProduto().getIdInt()==prod.getIdInt()) {
					return pd.getEstoque();

				}
			}
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
		}
		return 0;


	}
	public void Atualiza_Minimo_Maximo(Produto_Deposito PD) {
		Produto_Deposito pde = new Produto_Deposito();


		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			//pde = Session.get(Produto_Deposito.class, idProd_Dep);

			//			pde.setCodigo(idProd_Dep);
			//			pde.setEstoque_Maximo(estoqMin);
			//			pde.setEstoque_Maximo(estoqMax);
			pde = PD;
			Session.update(pde);
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

	}
	public void ProcessarSaida(Produto prod, Deposito dep, int quant) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			Produto_Deposito d = null;
			List<Produto_Deposito> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
			Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
			CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();
			Boolean estoqueAtualizado = false;
			
			for (Produto_Deposito pd : lista) {
				if(pd.getDeposito().getIdInt()==dep.getIdInt()) {
					System.out.println("produto deposito encontrado ID:"+pd.getIdInt());
					if((pd.getEstoque_Minimo() < VerificaEstoque(prod, dep)-quant)) {
						pd.setEstoque(pd.getEstoque()-quant);
						Session.saveOrUpdate(pd);
						trans.commit();
						estoqueAtualizado = true;
						break;
					}
					else {
						System.out.println("Voce tentou remover mais que o estoque minimo permitido para esse produto nesse deposito");
					}
				}
			}
			Session.clear();
			if(estoqueAtualizado==false) {
				throw new IllegalArgumentException("Erro: Produto n�o consta no deposito");

			}


		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public Produto_Deposito BuscaProduto_Deposito(Produto Produto, Deposito Deposito) {
		Transaction trans = null;

		try (Session Session = HibernateUtil.getSessionFactory().openSession()){

			trans = Session.beginTransaction();
			Produto_Deposito d = null;
			List<Produto_Deposito> lista = null;
			CriteriaBuilder builder = Session.getCriteriaBuilder();
			CriteriaQuery<Produto_Deposito> criteryQuery = builder.createQuery(Produto_Deposito.class);
			Root<Produto_Deposito> rootEntry = criteryQuery.from(Produto_Deposito.class);
			CriteriaQuery<Produto_Deposito> all = criteryQuery.select(rootEntry);

			TypedQuery<Produto_Deposito> allQuery = Session.createQuery(all);
			lista = allQuery.getResultList();

			for (Produto_Deposito pd : lista) {
				if(pd.getDeposito().getIdInt()==Deposito.getIdInt()) {
					return pd;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return null;

	}
	public Produto_Deposito GetProduto_DepositoById(int Codigo) {
		//Iniciar nova Transação banco
		Transaction trans = null;

		Produto_Deposito d = null;

		//CONTROLE DE SESSÃO COM UMA SESSÃO QUE VIRÁ DO SERVIDOR
		try (Session Session = HibernateUtil.getSessionFactory().openSession()){
			trans = Session.beginTransaction();

			d = Session.get(Produto_Deposito.class, Codigo);


			trans.commit();

		} catch (Exception e) {
			trans.rollback();
		}
		return d;
	}





}
