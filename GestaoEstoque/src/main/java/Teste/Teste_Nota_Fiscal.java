package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.DepositoDAO;
import DAO.Fornecedor_ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.Nota_FiscalDAO;
import DAO.ProdutoDAO;
import Model.Nota_Fiscal;
import Model.Produto;
import Model.Produto_Nota_Fiscal;
import Model.Deposito;
import Model.Fornecedor_Cliente;
import Model.Funcionario;

public class Teste_Nota_Fiscal {

	public static void main(String[] args) {
		FuncionarioDAO FDAO = new FuncionarioDAO();		
		Fornecedor_ClienteDAO FCDAO = new Fornecedor_ClienteDAO();
		
		
		//construção da nota fiscal
		Nota_Fiscal NF = new Nota_Fiscal();
		Calendar c = Calendar.getInstance();
		NF.setCodigo(505);
		NF.setData(c.getTime());
		NF.setUtilizacao("COMPRA");
		NF.setFornecedor_Cliente(FCDAO.GetById(Fornecedor_Cliente.class, 7));
		NF.setNumeracao("3265454");
		
		//contrução dos produtos da nota fiscal
		ProdutoDAO PDAO = new ProdutoDAO();
		List<Produto_Nota_Fiscal> produtos = new ArrayList<Produto_Nota_Fiscal>();
		DepositoDAO DDAO = new DepositoDAO(); 
		Produto_Nota_Fiscal PNF = new Produto_Nota_Fiscal();
		PNF.setProduto(PDAO.GetById(Produto.class, 2));
		PNF.setDeposito(DDAO.GetById(Deposito.class, 32112333));
		PNF.setCusto(30.02);
		PNF.setNota_Fiscal(NF);
		PNF.setQuantidade(97);
		produtos.add(PNF);
		
		
		//salvar e consultar nota fiscal
		Nota_FiscalDAO NF_DAO = new Nota_FiscalDAO();
		
		//NF_DAO.Registrar(NF, produtos, FDAO.GetById(Funcionario.class, 0));
	
		NF = new Nota_Fiscal();
		produtos = new ArrayList<Produto_Nota_Fiscal>();
		NF = NF_DAO.Consulta_Nota_Fiscal("987654");
		produtos = NF_DAO.Consulta_Produtos("987654");
		
		
		System.out.println("END");

	}

}
