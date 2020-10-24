package Teste;

import DAO.DepositoDAO;
import DAO.ProdutoDAO;
import DAO.Produto_DepositoDAO;
import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;

public class Teste_Produto_Deposito {

	public static void main(String[] args) {
		DepositoDAO DDAO = new DepositoDAO();		
		ProdutoDAO PDAO= new ProdutoDAO();
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		Produto_Deposito PD = new Produto_Deposito();

		PD = new Produto_Deposito();
		PD.setDeposito(DDAO.GetById(Deposito.class, 3));
		PD.setProduto(PDAO.GetById(Produto.class, 5));
		
		//processa entrada de mercadorias
		PDDAO.ProcessarEntrada(PD.getProduto(),	PD.getDeposito(), 25);
		PD = PDDAO.BuscaProduto_Deposito(PDAO.GetById(Produto.class, 3), DDAO.GetById(Deposito.class, 3) );
		PD.setEstoque_Maximo(360);
		PD.setEstoque_Minimo(2);
		PD.setCusto(5.35);
		PDDAO.Atualiza_Minimo_Maximo(PD);

		
		//processa saída de mercadorias
		PDDAO.ProcessarSaida(PDAO.GetById(Produto.class, 4), DDAO.GetById(Deposito.class, 3), 2);
		
		
		System.out.println("END");
	}

}
