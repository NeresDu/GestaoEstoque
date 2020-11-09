package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.FuncionarioDAO;
import Model.Funcionario;

public class Teste_Funcionario {

	public static void main(String[] args) {
		Funcionario Funcionario = new Funcionario();
		FuncionarioDAO FuncionarioDao = new FuncionarioDAO();
		
		Funcionario.setNome("Geremias Silveira");
		Funcionario.setCargo("COMPRADOR");
		Funcionario.setCpf("07207435913");
		
		FuncionarioDao.Save(Funcionario);
		
		//Funcionario = FuncionarioDao.GetById(Funcionario.class, 1);
		
		//FuncionarioDao.Delete(Funcionario.class, 1);
		
		List<Funcionario> Funcionarios = new ArrayList<Funcionario>();
		Funcionarios = FuncionarioDao.GetAll(Funcionario.class);
		
		System.out.println("END");

	}

}
