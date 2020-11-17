package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.DepositoDAO;
import DAO.Fornecedor_ClienteDAO;
import DAO.MovimentacaoDAO;
import DAO.ProdutoDAO;
import Model.Deposito;
import Model.Fornecedor_Cliente;
import Model.Movimentacao;
import Model.Produto;
import Model.Produto_Movimentacao;

public class Movimentacao_teste {

	public static void main(String[] args) {
		//VENDA E DEVOLUÇÃO
		Movimentacao MOV = new Movimentacao();

		//produtos da MOV		
		List<Produto_Movimentacao> produtos = new ArrayList<Produto_Movimentacao>();
		Produto_Movimentacao PMOV = new Produto_Movimentacao();
		ProdutoDAO PDAO = new ProdutoDAO();
		DepositoDAO DDAO = new DepositoDAO();
		PMOV.setCusto(3.5);
		PMOV.setProduto(PDAO.GetById(Produto.class, 1));
		PMOV.setDeposito(DDAO.GetById(Deposito.class, 1));
		PMOV.setNumeracao_Movimentacao("165165156165165");
		PMOV.setQuantidade(5);
		produtos.add(PMOV);
		MOV.setProdutos(produtos);

		MOV.setNumeracao("165165156165165");

		MOV.setTipo_Movimentacao("COMPRA");

		MovimentacaoDAO MOVDAO = new MovimentacaoDAO();

		MOVDAO.Registrar_MOV(MOV);


		/*
		MovimentacaoDAO NFDAO = new MovimentacaoDAO();
		Movimentacao MOV = new Movimentacao();

		MOV = NFDAO.Consulta_Movimentacao("165165156165165");
		 */

		System.out.println("end");

	}

}
