package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.DepositoDAO;
import DAO.Fornecedor_ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.Nota_FiscalDAO;
import DAO.ProdutoDAO;
import Model.Deposito;
import Model.Fornecedor_Cliente;
import Model.Funcionario;
import Model.Nota_Fiscal;
import Model.Produto;
import Model.Produto_Nota_Fiscal;

public class Nota_Fiscal_Teste {

	public static void main(String[] args) {
		
		Nota_Fiscal NF = new Nota_Fiscal();
		
		//produtos da nf		
		List<Produto_Nota_Fiscal> produtos = new ArrayList<Produto_Nota_Fiscal>();
		Produto_Nota_Fiscal PNF = new Produto_Nota_Fiscal();
		ProdutoDAO PDAO = new ProdutoDAO();
		DepositoDAO DDAO = new DepositoDAO();
		PNF.setCusto(3.5);
		PNF.setProduto(PDAO.GetById(Produto.class, 1));
		PNF.setDeposito(DDAO.GetById(Deposito.class, 1));
		PNF.setNumeracao_NF("272835965654964651");
		PNF.setQuantidade(5);
		produtos.add(PNF);
		NF.setProdutos(produtos);
		
		NF.setNumeracao("272835965654964651");
		
		Fornecedor_ClienteDAO FCDAO = new Fornecedor_ClienteDAO();
		NF.setFornecedor_Cliente(FCDAO.GetById(Fornecedor_Cliente.class, 1));
		NF.setUtilizacao("COMPRA");
		
		Nota_FiscalDAO NFDAO = new Nota_FiscalDAO();
		
		NFDAO.Registrar_NF(NF);
		
		
		
		
		/*
		//CONSULTAR NF COM SEUS PRODUTOS
		Nota_FiscalDAO NFDAO = new Nota_FiscalDAO();
		Nota_Fiscal NF = new Nota_Fiscal();
		
		NF = NFDAO.Consulta_Nota_Fiscal("272835965654964651");
		
		*/
		
		System.out.println("end");
		
	}

}
