package Teste;

import java.util.Calendar;

import DAO.Entrada_SaidaDAO;
import Model.Entrada_Saida;

public class Teste_Entrada_Saida {

	public static void main(String[] args) {
		Entrada_Saida ES = new Entrada_Saida();
		Calendar c = Calendar.getInstance();
		ES.setCodigo(3);
		ES.setData(c.getTime());
		ES.setTipo_Movimentacao("ENTRADA");

		Entrada_SaidaDAO ES_DAO = new Entrada_SaidaDAO();
		ES_DAO.Save(ES);
	
		

		//ES = ES_DAO.GetById(Entrada_Saida.class, 123);
		//ES = new Entrada_Saida();

		//ES_DAO.Delete(Entrada_Saida.class, 123);
		//ES = ES_DAO.GetById(Entrada_Saida.class, 123);
		System.out.println("END");

	}

}
