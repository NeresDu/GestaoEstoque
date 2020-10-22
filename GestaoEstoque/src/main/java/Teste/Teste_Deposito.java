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
		GenericoDAO g = new GenericoDAO();
		
		
		Deposito dep = new Deposito();	//criando deposito
		dep.setCategoria_Estoque("Categoria teste");
		dep.setNome("Deposito de frutas");
		dep.setCodigo(321);
		g.Save(dep);
		
		Produto p1 = new Produto();	//criando produto que vai no produto_deposito
		p1.setNome("prod 1");
		p1.setCodigo(5555);
		p1.setCategoria("produto 1");
		
		g.Save(p1);
		/*
		 * Produto p2 = new Produto(); p2.setNome("prod 2"); p2.setCodigo(2222);
		 * p2.setCategoria("produto 2");
		 */
		List<Produto_Deposito> produtos_depositos = new ArrayList<Produto_Deposito>();
		Produto_Deposito pdep = new Produto_Deposito();	// criando produto_dep, vincular o produto a 1 deposito
		pdep.setProduto(p1);
		pdep.setCusto(123);
		pdep.setEstoque(5);
		pdep.setEstoque_Maximo(5);
		pdep.setEstoque_Minimo(1);
		pdep.setDeposito(dep);

		
		 g.Save(pdep);
		

		
		
	}

}
