package Teste;

import java.util.Calendar;

import DAO.MovimentacaoDAO;
import Model.Movimentacao;

public class Teste_Entrada_Saida {

	public static void main(String[] args) {
		Movimentacao ES = new Movimentacao();
		Calendar c = Calendar.getInstance();
		ES.setCodigo(3);
		ES.setData(c.getTime());
		ES.setTipo_Movimentacao("ENTRADA");

		MovimentacaoDAO ES_DAO = new MovimentacaoDAO();
		ES_DAO.Save(ES);
	
		

		//ES = ES_DAO.GetById(Entrada_Saida.class, 123);
		//ES = new Entrada_Saida();

		//ES_DAO.Delete(Entrada_Saida.class, 123);
		//ES = ES_DAO.GetById(Entrada_Saida.class, 123);
		System.out.println("END");

	}

}
