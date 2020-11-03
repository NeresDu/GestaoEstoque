package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.Produto_MovimentacaoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Produto_Movimentacao;

@RestController
public class ControllerProdutoMovimentacao {
	Produto_MovimentacaoDAO pmovDAO = new Produto_MovimentacaoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarProduto_Movimentacao")
	public Produto_Movimentacao salvaProduto_Movimentacao(@RequestBody String Produto_Movimentacao) {
		Gson gson = new Gson();
		Produto_Movimentacao d = null;
		d = gson.fromJson(Produto_Movimentacao, Produto_Movimentacao.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteProduto_Movimentacao")
	public Produto_Movimentacao updateProduto_Movimentacao(@RequestBody String Produto_Movimentacao) {
		Gson gson = new Gson();
		Produto_Movimentacao d = null;
		d = gson.fromJson(Produto_Movimentacao, Produto_Movimentacao.class);
		pmovDAO.DeleteProduto_Movimentacao(d.getCodigo());
		return d;
		
	}
	@RequestMapping("/getProduto_Movimentacao")
	public Produto_Movimentacao getProduto_Movimentacao(@RequestBody String Produto_Movimentacao) {
		Gson gson = new Gson();
		Produto_Movimentacao d = null;
		d = gson.fromJson(Produto_Movimentacao, Produto_Movimentacao.class);
		return pmovDAO.GetProduto_MovimentacaoById(d.getCodigo());
	
		
	}
	@RequestMapping("/getallProduto_Movimentacaos")
	public List<EntidadeBase> getAllProduto_Movimentacao() {
		Gson gson = new Gson();
		List<EntidadeBase> Produto_Movimentacoes = new ArrayList<EntidadeBase>();
		Produto_Movimentacoes = gDAO.GetAll(Produto_Movimentacao.class);
		return Produto_Movimentacoes;
	
		
	}

}
