package Teste;

import DAO.DepositoDAO;
import DAO.ProdutoDAO;
import DAO.Produto_DepositoDAO;
import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;

public class Teste_Produto_Deposito {

	public static void main(String[] args) {
		//criar deposito
		Deposito d = new Deposito();
		d.setCategoria_Estoque("RISCO");
		d.setNome("Visconde");
		d.setCodigo(3);
//		DepositoDAO DDAO = new DepositoDAO();
//		DDAO.Save(d);
		
		
		ProdutoDAO PDAO= new ProdutoDAO();
		
		
		// teste produto_estoque
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		Produto_Deposito PD = new Produto_Deposito();
		PD.setCodigo(1);
		PD.setCusto(27.30);
		PD.setDeposito(d);
		PD.setEstoque(50);
		PD.setEstoque_Maximo(900);
		PD.setEstoque_Minimo(9);
		Produto P = new Produto();
		P.setCodigo(3);
		PD.setProduto(P);
		
		PDDAO.Atualiza_Minimo_Maximo(PD);
		
//		PDDAO.ProcessarEntrada(PDAO.GetById(Produto.class, 1),
//				DDAO.GetById(Deposito.class, 0), 
//				25);
		
		
//		System.out.println(PDDAO.VerificaEstoque(PDAO.GetById(Produto.class, 1), DDAO.GetById(Deposito.class, 0)));
//		
		
		System.out.println("END");
	}

}
