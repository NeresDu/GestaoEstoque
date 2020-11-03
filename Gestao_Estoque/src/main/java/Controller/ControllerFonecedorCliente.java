package Controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import DAO.Fornecedor_ClienteDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Fornecedor_Cliente;
import Util.HibernateUtil;

public class ControllerFonecedorCliente {
	Fornecedor_ClienteDAO forcliDAO = new Fornecedor_ClienteDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarFornecedor_Cliente")
	public Fornecedor_Cliente salvaFornecedor_Cliente(@RequestBody String Fornecedor_Cliente) {
		Gson gson = new Gson();
		Fornecedor_Cliente d = null;
		d = gson.fromJson(Fornecedor_Cliente, Fornecedor_Cliente.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteFornecedor_Cliente")
	public Fornecedor_Cliente updateFornecedor_Cliente(@RequestBody String Fornecedor_Cliente) {
		Gson gson = new Gson();
		Fornecedor_Cliente d = null;
		d = gson.fromJson(Fornecedor_Cliente, Fornecedor_Cliente.class);
		forcliDAO.DeleteFornecedor_Cliente(d.getCodigo());
		return d;
		
	}
	@RequestMapping("/getFornecedor_Cliente")
	public EntidadeBase getFornecedor_Cliente(@RequestBody String Fornecedor_Cliente) {
		Gson gson = new Gson();
		Fornecedor_Cliente d = null;
		d = gson.fromJson(Fornecedor_Cliente, Fornecedor_Cliente.class);
		return forcliDAO.GetFornecedor_ClienteById(d.getCodigo());
	
		
	}
	@RequestMapping("/getallFornecedor_Clientes")
	public List<EntidadeBase> getAllFornecedor_Cliente() {
		Gson gson = new Gson();
		List<EntidadeBase> Fornecedor_Clientes = new ArrayList<EntidadeBase>();
		Fornecedor_Clientes = gDAO.GetAll(Fornecedor_Cliente.class);
		return Fornecedor_Clientes;
	
		
	}
}