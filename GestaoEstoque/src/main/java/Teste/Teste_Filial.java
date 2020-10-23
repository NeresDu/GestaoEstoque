package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.FilialDAO;
import Model.Filial;
import Model.Funcionario;

public class Teste_Filial {

	public static void main(String[] args) {
		Filial F = new Filial();
		Funcionario Funcionario = new Funcionario();
		Funcionario.setCodigo(123);
		List<Funcionario> fs = new ArrayList<Funcionario>();
		fs.add(Funcionario);
		F.setFuncionarios(fs);

		FilialDAO F_DAO = new FilialDAO();
		F_DAO.Save(F);
	
		

		//F = F_DAO.GetById(Filial.class, 123);
		//F = new Filial();

		//F_DAO.Delete(Filial.class, 123);
		//F = F_DAO.GetById(Filial.class, 123);
		System.out.println("END");


	}

}
