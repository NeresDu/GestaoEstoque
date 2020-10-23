package Teste;

import DAO.Fornecedor_ClienteDAO;
import Model.Fornecedor_Cliente;

public class Teste_Fornecedor_Cliente {

	public static void main(String[] args) {
		Fornecedor_Cliente FC = new Fornecedor_Cliente();
		//FC.setCnpj("123456789");
		//FC.setNome("empresa na moral");
		//FC.setTipo_Cadastro("Fornecedor");
		
		Fornecedor_ClienteDAO FC_DAO = new Fornecedor_ClienteDAO();
		//FC_DAO.Save(FC);
		
		FC = FC_DAO.GetById(Fornecedor_Cliente.class, 123);
		FC = new Fornecedor_Cliente();
		
		FC_DAO.Delete(Fornecedor_Cliente.class, 123);
		FC = FC_DAO.GetById(Fornecedor_Cliente.class, 123);
		System.out.println("END");
		
		
	}

}
