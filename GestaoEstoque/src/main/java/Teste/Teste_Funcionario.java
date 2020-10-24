package Teste;

import DAO.FuncionarioDAO;
import Model.Funcionario;

public class Teste_Funcionario {
	public static void main(String[] args) {
		Funcionario F = new Funcionario();
		F.setCargo("COMPRADOR");
		F.setCpf("0643598181");
		F.setNome("JOAO CARLOS MOREIRA");

		FuncionarioDAO F_DAO = new FuncionarioDAO();
		F_DAO.Save(F);
		
		
		F = new Funcionario();
		F.setCodigo(123);
		F.setCargo("COMPRADOR");
		F.setCpf("321654321");
		F.setNome("LUANA ALBUQUER");
		F_DAO.Save(F);
		
//
//		F = F_DAO.GetById(Funcionario.class, 123);
//		F = new Funcionario();

		//F_DAO.Delete(Funcionario.class, 123);
		//F = F_DAO.GetById(Funcionario.class, 123);
		System.out.println("END");

	}
}
