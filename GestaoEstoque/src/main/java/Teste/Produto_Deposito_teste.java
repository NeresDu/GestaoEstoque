package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.DepositoDAO;
import DAO.ProdutoDAO;
import DAO.Produto_DepositoDAO;
import Model.Deposito;
import Model.Produto;
import Model.Produto_Deposito;

public class Produto_Deposito_teste {

	public static void main(String[] args) {
		/* */
		//teste salvar produto
		Produto_Deposito PD = new Produto_Deposito();
		
		ProdutoDAO PDAO = new ProdutoDAO();
		
		PD.setProduto(PDAO.GetById(Produto.class, 1));
		DepositoDAO DDAO = new DepositoDAO();
		PD.setDeposito(DDAO.GetById(Deposito.class, 1));
		PD.setQtd(20);
		PD.setEstoque_Maximo(10000);
		PD.setEstoque_Minimo(2);
		PD.setCusto(20.15);
		
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		PDDAO.Processar_Entrada_Estoque(PD);
		
		
		/* 
		//PROCESSAR SAÍDA DE ESTOQUE
		Produto_Deposito PD = new Produto_Deposito();
		
		ProdutoDAO PDAO = new ProdutoDAO();
		
		PD.setProduto(PDAO.GetById(Produto.class, 1));
		DepositoDAO DDAO = new DepositoDAO();
		PD.setDeposito(DDAO.GetById(Deposito.class, 1));
		PD.setQtd(3);
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		PDDAO.Processar_Saida_Estoque(PD);
		*/
		
		/* 
		//Verifica estoque
		Produto_Deposito PD = new Produto_Deposito();
		
		ProdutoDAO PDAO = new ProdutoDAO();
		
		PD.setProduto(PDAO.GetById(Produto.class, 1));
		DepositoDAO DDAO = new DepositoDAO();
		PD.setDeposito(DDAO.GetById(Deposito.class, 1));
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		
		System.out.println(PDDAO.Verificar_Estoque(PD));
		*/
		
		
		/* 
		//FUNCIONANDO
		//teste busca produto Deposito
		Produto_Deposito PD = new Produto_Deposito();
		
		ProdutoDAO PDAO = new ProdutoDAO();
		
		PD.setProduto(PDAO.GetById(Produto.class, 1));
		DepositoDAO DDAO = new DepositoDAO();
		PD.setDeposito(DDAO.GetById(Deposito.class, 1));
		
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		PD = PDDAO.Buscar_Produto_Deposito(PD);
		*/
		
		
		
		/*  
		//FUNCIONANDO
		//TESTE ATUALIZA MÍNIMO E MÁXIMO
		Produto_Deposito PD = new Produto_Deposito();
		
		ProdutoDAO PDAO = new ProdutoDAO();
		
		PD.setProduto(PDAO.GetById(Produto.class, 1));
		DepositoDAO DDAO = new DepositoDAO();
		PD.setDeposito(DDAO.GetById(Deposito.class, 1));
		PD.setEstoque_Maximo(10000);
		PD.setEstoque_Minimo(2);
		PD.setCusto(20.15);
		
		Produto_DepositoDAO PDDAO = new Produto_DepositoDAO();
		PDDAO.Atualiza_Minimo_Maximo(PD);
		*/
		
		
		System.out.println("end");
	}

}
