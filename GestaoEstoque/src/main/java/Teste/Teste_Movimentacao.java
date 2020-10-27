package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.DepositoDAO;
import DAO.Fornecedor_ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.MovimentacaoDAO;
import DAO.ProdutoDAO;
import DAO.Produto_DepositoDAO;
import Model.Deposito;
import Model.Fornecedor_Cliente;
import Model.Movimentacao;
import Model.Produto;
import Model.Produto_Deposito;
import Model.Produto_Movimentacao;

public class Teste_Movimentacao {
	public static void main(String[] args) {		
		//construção da Movimentação
		Movimentacao M = new Movimentacao();
		Calendar c = Calendar.getInstance();
		M.setCodigo(16);
		M.setData(c.getTime());
		//M.setTipo_Movimentacao("COMPRA");
		M.setNumeracao("326624");		
		
		//contrução dos produtos
		ProdutoDAO PDAO = new ProdutoDAO();
		List<Produto_Movimentacao> produtos = new ArrayList<Produto_Movimentacao>();
		DepositoDAO DDAO = new DepositoDAO(); 
		Produto_Movimentacao PM = new Produto_Movimentacao();
		PM.setProduto(PDAO.GetById(Produto.class, 2));
		PM.setDeposito(DDAO.GetById(Deposito.class, 32112333));
		PM.setCusto(30.02);
		PM.setMovimentacao(M);
		PM.setQuantidade(5);
		produtos.add(PM);
		
		
		//salvar e consultar nota fiscal
		MovimentacaoDAO M_DAO = new MovimentacaoDAO();
		
		M.setTipo_Movimentacao("COMPRA");
		M_DAO.Registrar(M, produtos);
		
		

		
		
		
		
		/*
		M = new Movimentacao();
		produtos = new ArrayList<Produto_Movimentacao>();
		M = M_DAO.Consulta_Movimentacao("987654");
		produtos = M_DAO.Consulta_Produtos("987654");
		*/
		
		
		System.out.println("END");
		
		
	}

}
