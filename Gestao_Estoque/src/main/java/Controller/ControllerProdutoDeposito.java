package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.Produto_DepositoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Produto_Deposito;

@RestController
public class ControllerProdutoDeposito {
	Produto_DepositoDAO pdepDao = new Produto_DepositoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarProduto_Deposito")
	public Produto_Deposito salvaProduto_Deposito(@RequestBody String Produto_Deposito) {
		Gson gson = new Gson();
		Produto_Deposito d = null;
		d = gson.fromJson(Produto_Deposito, Produto_Deposito.class);
		//gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteProduto_Deposito")
	public Produto_Deposito updateProduto_Deposito(@RequestBody String Produto_Deposito) {
		Gson gson = new Gson();
		Produto_Deposito d = null;
		d = gson.fromJson(Produto_Deposito, Produto_Deposito.class);
		//pdepDao.DeleteProduto_Deposito(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getProduto_Deposito")
	public EntidadeBase getProduto_Deposito(@RequestBody String Produto_Deposito) {
		Gson gson = new Gson();
		Produto_Deposito d = null;
		d = gson.fromJson(Produto_Deposito, Produto_Deposito.class);
		return pdepDao.GetProduto_DepositoById(d.getIdInt());
	
		
	}
	@RequestMapping("/getallProduto_Depositos")
	public List<EntidadeBase> getAllProduto_Deposito() {
		Gson gson = new Gson();
		List<EntidadeBase> Produto_Depositos = new ArrayList<EntidadeBase>();
		Produto_Depositos = gDAO.GetAll(Produto_Deposito.class);
		return Produto_Depositos;
	
		
	}

}
