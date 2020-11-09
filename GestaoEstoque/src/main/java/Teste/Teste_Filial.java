package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.DepositoDAO;
import DAO.FilialDAO;
import DAO.FuncionarioDAO;
import Model.Deposito;
import Model.Filial;
import Model.Funcionario;

public class Teste_Filial {

	public static void main(String[] args) {
		Filial Filial = new Filial();
		FilialDAO FilialDao = new FilialDAO();
		
		FuncionarioDAO funcionario = new FuncionarioDAO();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario.GetById(Funcionario.class, 1));
		
		Filial.setFuncionarios(funcionarios);
		
		DepositoDAO deposito = new DepositoDAO();
		List<Deposito> depositos = new ArrayList<Deposito>();
		deposito.GetById(Deposito.class, 1);
		
		Filial.setDepositos(depositos);
		
		FilialDao.Save(Filial);
		
		//Filial = FilialDao.GetById(Filial.class, 1);
		
		//FilialDao.Delete(Filial.class, 1);
		
		List<Filial> Filiais = new ArrayList<Filial>();
		Filiais = FilialDao.GetAll(Filial.class);
		
		System.out.println("END");

	}

}
