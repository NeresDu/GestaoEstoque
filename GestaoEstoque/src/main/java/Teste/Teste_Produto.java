package Teste;

import java.util.ArrayList;
import java.util.List;

import DAO.GenericoDAO;
import DAO.ProdutoDAO;
import Model.Produto;

public class Teste_Produto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Produto p = new Produto();
		p.setNome("teste");
		p.setCodigo(11);
		p.setCategoria("teste o teste");
		
		ProdutoDAO pDAO = new ProdutoDAO();
		
		//pDAO.Delete(Produto.class, 1234);
		
		//pDAO.Save(p);
		
		//pDAO.Update(p);
		
		//GetById
		//p = new Produto();
		//p = pDAO.GetById(Produto.class, 11);
		//System.out.println("Id: "+p.getId()+"Nome: "+p.getNome()+"Categoria: "+p.getCategoria());
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = pDAO.GetAll(Produto.class);
		
		for (Produto pro : produtos) {
			System.out.println("Id: "+pro.getId()+"Nome: "+pro.getNome()+"Categoria: "+pro.getCategoria());
		}
		

		
	}

}
