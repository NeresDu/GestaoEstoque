package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.Nota_FiscalDAO;
import Model.Nota_Fiscal;
import Model.Funcionario;

public class Teste_Nota_Fiscal {

	public static void main(String[] args) {
		Nota_Fiscal NF = new Nota_Fiscal();
		Calendar c = Calendar.getInstance();
		NF.setData(c.getTime());
		NF.setUtilizacao("COMPRA");

		Nota_FiscalDAO NF_DAO = new Nota_FiscalDAO();
		NF_DAO.Save(NF);
	
		

		//NF = NF_DAO.GetById(Nota_Fiscal.class, 123);
		//NF = new Nota_Fiscal();

		//NF_DAO.Delete(Nota_Fiscal.class, 123);
		//NF = NF_DAO.GetById(Nota_Fiscal.class, 123);
		System.out.println("END");

	}

}
