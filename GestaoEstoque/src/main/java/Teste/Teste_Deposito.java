package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.DepositoDAO;
import DAO.GenericoDAO;
import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;

public class Teste_Deposito {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deposito dep = new Deposito();
		Produto p1 = new Produto();
		DepositoDAO dDAO = new DepositoDAO();
		GenericoDAO g = new GenericoDAO();
		Produto_Deposito pdep = new Produto_Deposito();
		List<Produto_Deposito> produtos = new ArrayList<Produto_Deposito>();
		p1.setNome("prod 1");
		p1.setCodigo(22222);
		p1.setCategoria("produto 1");
		
		g.Save(p1);
		/*
		 * Produto p2 = new Produto(); p2.setNome("prod 2"); p2.setCodigo(2222);
		 * p2.setCategoria("produto 2");
		 */
		
		
		pdep.setProduto(p1);
		pdep.setCusto(123);
		pdep.setEstoque(5);
		pdep.setEstoque_Maximo(5);
		pdep.setEstoque_Minimo(1);
		
		//salvar somente depois de o produto e o deposito existirem
		g.Save(pdep);
		/*
		 * pdep.setDeposito(dep);
		 * 
		 * produtos.add(pdep); dep.setProdutos(produtos); dep.setCodigo(123);
		 * dep.setCategoria_Estoque("Risco");
		 */
		
		//g.Save(dep);
		//dDAO.Save(dep);
		
		//pdep.setProduto(p2);
		//dep.setProdutos(pdep);
		

		
		
	}

}
