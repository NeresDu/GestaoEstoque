package Teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.PerecivelDAO;
import DAO.ProdutoDAO;
import DAO.RiscoDAO;
import Model.Perecivel;
import Model.Produto;
import Model.Risco;

public class Teste_Produtos {

	public static void main(String[] args) {
		// CRUD PRODUTO
		
		Produto comum = new Produto();
		ProdutoDAO comumDao = new ProdutoDAO();
		
		comum.setNome("caneta VERMELHA");
		
		comumDao.Save(comum);
		
		//comum = comumDao.GetById(Produto.class, 1);
		
		//comumDao.Delete(Produto.class, 1);
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = comumDao.GetAll(Produto.class);
		
		
		
		//CRUD PRODUTO RISCO
		
		Risco risco = new Risco();
		RiscoDAO RiscoDao = new RiscoDAO();
		
		risco.setNome("H2SO4");
		risco.setGrau_Risco("ácido");
		risco.setOrientacao_Seguranca("não comer");
		
		RiscoDao.Save(risco);
		
		//risco = RiscoDao.GetById(Risco.class, 4);
		
		//RiscoDao.Delete(Risco.class, 4);
		
		List<Risco> produtos_r = new ArrayList<Risco>();
		produtos_r = RiscoDao.GetAll(Risco.class);
		
		
		//CRUD PRODUTO PERECÍVEL
		Perecivel Perecivel = new Perecivel();
		PerecivelDAO PerecivelDao = new PerecivelDAO();
		
		Perecivel.setNome("mamão papaya");
		Calendar c = Calendar.getInstance();
		c.set(2020, 11, 25);
		Perecivel.setData_Validade(c.getTime());
		
		PerecivelDao.Save(Perecivel);
		
		//Perecivel = PerecivelDao.GetById(Perecivel.class, 7);
		
		//PerecivelDao.Delete(Perecivel.class, 7);
		
		List<Perecivel> produtos_p = new ArrayList<Perecivel>();
		produtos_p = PerecivelDao.GetAll(Perecivel.class);
		
		System.out.println("END");
	}

}
