package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.DepositoDAO;
import DAO.Nota_FiscalDAO;
import Model.Nota_Fiscal;
import Model.Produto;
import Model.Produto_Nota_Fiscal;
import Model.Deposito;
import Model.Funcionario;

public class Teste_Nota_Fiscal {

	public static void main(String[] args) {
		Funcionario F = new Funcionario();
		F.setCargo("COMPRADOR");
		
		
		//construção da nota fiscal
		Nota_Fiscal NF = new Nota_Fiscal();
		Calendar c = Calendar.getInstance();
		NF.setData(c.getTime());
		NF.setUtilizacao("COMPRA");
		
		DepositoDAO DDAO = new DepositoDAO(); 
		
		//produtos da nota fiscal
		List<Produto_Nota_Fiscal> produtos = new ArrayList<Produto_Nota_Fiscal>();
		
		Produto_Nota_Fiscal PNF = new Produto_Nota_Fiscal();
		Produto comum = new Produto();
		comum.setCodigo(2);
		PNF.setProduto(comum);
		PNF.setDeposito(DDAO.GetById(Deposito.class, 5));
		PNF.setCusto(30.02);
		PNF.setNota_Fiscal(5);
		PNF.setQuantidade(97);
		produtos.add(PNF);
		
		comum = new Produto();
		comum.setCodigo(3);
		PNF = new Produto_Nota_Fiscal();
		PNF.setProduto(comum);
		PNF.setDeposito(DDAO.GetById(Deposito.class, 555));
		PNF.setCusto(22.02);
		PNF.setNota_Fiscal(5);
		PNF.setQuantidade(22);
		produtos.add(PNF);
		

		Nota_FiscalDAO NF_DAO = new Nota_FiscalDAO();
		
		NF_DAO.Registrar(NF, produtos, F);
	
		

		
		System.out.println("END");

	}

}
