package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.DepositoDAO;
import Model.Deposito;

public class Teste_Deposito {

	public static void main(String[] args) {
		Deposito Deposito = new Deposito();
		DepositoDAO DepositoDao = new DepositoDAO();
		
		Deposito.setNome("Matriz São Paulo");
		Deposito.setCategoria_Estoque("Comum");	
		
		DepositoDao.Save(Deposito);
		
		//Deposito = DepositoDao.GetById(Deposito.class, 1);
		
		//DepositoDao.Delete(Deposito.class, 1);
		
		List<Deposito> Depositos = new ArrayList<Deposito>();
		Depositos = DepositoDao.GetAll(Deposito.class);
		
		System.out.println("END");

	}

}
