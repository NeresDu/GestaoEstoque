package Teste;

import DAO.DepositoDAO;
import DAO.ProdutoDAO;
import DAO.Produto_DepositoDAO;
import Model.Deposito;
import Model.Produto;

public class Teste_Produto_Deposito {

	public static void main(String[] args) {
		
		Deposito d = new Deposito();
		d.setCategoria_Estoque("RISCO");
		d.setNome("Visconde");
		d.setCodigo(556);
		DepositoDAO DDAO = new DepositoDAO();
		DDAO.Save(d);
		
		
		ProdutoDAO PDAO= new ProdutoDAO();
		
		
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		PDDAO.Atualiza_Minimo_Maximo(20, 10, 1000000);
		
		PDDAO.ProcessarEntrada(PDAO.GetById(Produto.class, 1),
				DDAO.GetById(Deposito.class, 0), 
				25);
		
		
		System.out.println(PDDAO.VerificaEstoque(PDAO.GetById(Produto.class, 1), DDAO.GetById(Deposito.class, 0)));
		
		
		System.out.println("END");
	}

}
