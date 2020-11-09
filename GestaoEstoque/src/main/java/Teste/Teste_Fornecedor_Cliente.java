package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.Fornecedor_ClienteDAO;
import Model.Fornecedor_Cliente;

public class Teste_Fornecedor_Cliente {

	public static void main(String[] args) {
		Fornecedor_Cliente Fornecedor_Cliente = new Fornecedor_Cliente();
		Fornecedor_ClienteDAO Fornecedor_ClienteDao = new Fornecedor_ClienteDAO();
		
		//salvando cliente
		Fornecedor_Cliente.setNome("Geremias Silveira");
		Fornecedor_Cliente.setCpf("07207435913");
		Fornecedor_Cliente.setTipo_Cadastro("Cliente");
		
		Fornecedor_ClienteDao.Save(Fornecedor_Cliente);
		
		
		//salvando fornecedor
		Fornecedor_Cliente = new Fornecedor_Cliente();
		Fornecedor_Cliente.setNome("Comidas e lanches");
		Fornecedor_Cliente.setCnpj("07207435913");
		Fornecedor_Cliente.setTipo_Cadastro("Fornecedor");
		
		Fornecedor_ClienteDao.Save(Fornecedor_Cliente);
		
		//Fornecedor_Cliente = Fornecedor_ClienteDao.GetById(Fornecedor_Cliente.class, 1);
		
		//Fornecedor_ClienteDao.Delete(Fornecedor_Cliente.class, 1);
		
		List<Fornecedor_Cliente> Fornecedor_Clientes = new ArrayList<Fornecedor_Cliente>();
		Fornecedor_Clientes = Fornecedor_ClienteDao.GetAll(Fornecedor_Cliente.class);
		
		System.out.println("END");

	}

}
