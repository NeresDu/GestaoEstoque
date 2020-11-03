package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


import DAO.EntidadeBase;
import DAO.GenericoDAO;
import DAO.Produto_Nota_FiscalDAO;
import Model.Produto_Nota_Fiscal;

@RestController
public class ControllerProdutoNotaFiscal {
	Produto_Nota_FiscalDAO pnfDAO = new Produto_Nota_FiscalDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarProduto_Nota_Fiscal")
	public Produto_Nota_Fiscal salvaProduto_Nota_Fiscal(@RequestBody String Produto_Nota_Fiscal) {
		Gson gson = new Gson();
		Produto_Nota_Fiscal d = null;
		d = gson.fromJson(Produto_Nota_Fiscal, Produto_Nota_Fiscal.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteProduto_Nota_Fiscal")
	public Produto_Nota_Fiscal updateProduto_Nota_Fiscal(@RequestBody String Produto_Nota_Fiscal) {
		Gson gson = new Gson();
		Produto_Nota_Fiscal d = null;
		d = gson.fromJson(Produto_Nota_Fiscal, Produto_Nota_Fiscal.class);
		pnfDAO.DeleteProduto_Nota_Fiscal(d.getCodigo());
		return d;
		
	}
	@RequestMapping("/getProduto_Nota_Fiscal")
	public Produto_Nota_Fiscal getProduto_Nota_Fiscal(@RequestBody String Produto_Nota_Fiscal) {
		Gson gson = new Gson();
		Produto_Nota_Fiscal d = null;
		d = gson.fromJson(Produto_Nota_Fiscal, Produto_Nota_Fiscal.class);
		return pnfDAO.GetProduto_Nota_FiscalById(d.getCodigo());
	
		
	}
	@RequestMapping("/getallProduto_Nota_Fiscais")
	public List<EntidadeBase> getAllProduto_Nota_Fiscal() {
		Gson gson = new Gson();
		List<EntidadeBase> Produto_Nota_Fiscais = new ArrayList<EntidadeBase>();
		Produto_Nota_Fiscais = gDAO.GetAll(Produto_Nota_Fiscal.class);
		return Produto_Nota_Fiscais;
	
		
	}

}
