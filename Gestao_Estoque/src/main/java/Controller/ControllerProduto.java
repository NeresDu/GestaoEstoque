package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.ProdutoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Produto;

@RestController
public class ControllerProduto {
	ProdutoDAO prodDAO = new ProdutoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarProduto")
	public Produto salvaProduto(@RequestBody String Produto) {
		Gson gson = new Gson();
		Produto d = null;
		d = gson.fromJson(Produto, Produto.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteProduto")
	public Produto updateProduto(@RequestBody String Produto) {
		Gson gson = new Gson();
		Produto d = null;
		d = gson.fromJson(Produto, Produto.class);
		prodDAO.DeleteProduto(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getProduto")
	public EntidadeBase getProduto(@RequestBody String Produto) {
		Gson gson = new Gson();
		Produto d = null;
		d = gson.fromJson(Produto, Produto.class);
		return prodDAO.GetProdutoById(d.getIdInt());
	
		
	}
	@RequestMapping("/getallProdutos")
	public List<EntidadeBase> getAllProduto() {
		Gson gson = new Gson();
		List<EntidadeBase> Produtos = new ArrayList<EntidadeBase>();
		Produtos = gDAO.GetAll(Produto.class);
		return Produtos;
	
		
	}

}
