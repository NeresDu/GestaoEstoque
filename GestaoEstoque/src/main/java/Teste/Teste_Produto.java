package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.GenericoDAO;
import DAO.PerecivelDAO;
import DAO.ProdutoDAO;
import DAO.RiscoDAO;
import Model.Perecivel;
import Model.Produto;
import Model.Risco;

public class Teste_Produto {

	public static void main(String[] args) {
		
		 Produto p = new Produto(); 
		 p.setNome("teste"); 
		 
		
		ProdutoDAO pDAO = new ProdutoDAO();
		
		//pDAO.Delete(Produto.class, 1234);
		
		pDAO.Save(p);
		
		RiscoDAO RDAO = new RiscoDAO();
		Risco PR = new Risco();
		
		PR.setNome("CIANETO");
		PR.setOrientacao_Seguranca("BLA BLA BLA");
		
		RDAO.Save(PR);
		
		
		PerecivelDAO PPDAO = new  PerecivelDAO();
		Perecivel PP = new Perecivel();
		PP.setNome("COXINHA");
		Calendar C = Calendar.getInstance();
		PP.setData_Validade(C.getTime());
		PPDAO.Save(PP);
		
		
		//pDAO.Update(p);
		
		
		//GetById
		//p = new Produto();
		//p = pDAO.GetById(Produto.class, 11);
		//System.out.println("Id: "+p.getId()+"Nome: "+p.getNome()+"Categoria: "+p.getCategoria());
		//List<Produto> produtos = new ArrayList<Produto>();
		//produtos = pDAO.GetAll(Produto.class);
		
		/*
		 * //for (Produto pro : produtos) {
		 * System.out.println("Id: "+pro.getId()+"Nome: "+pro.getNome()+"Categoria: "
		 * +pro.getCategoria()); }
		 */
		
		System.out.println("END");

		
	}

}
