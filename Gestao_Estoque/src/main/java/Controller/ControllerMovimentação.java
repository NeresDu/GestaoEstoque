package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.MovimentacaoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Movimentacao;

@RestController
public class ControllerMovimentação {
	MovimentacaoDAO movDAO = new MovimentacaoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	
	
	@RequestMapping("/salvarMovimentacao")
	public Movimentacao salvaMovimentacao(@RequestBody String Movimentacao) {
		Gson gson = new Gson();
		Movimentacao d = null;
		d = gson.fromJson(Movimentacao, Movimentacao.class);
		//movDAO.Registrar(d);
		return d;
		
	}
	@RequestMapping("/deleteMovimentacao")
	public Movimentacao updateMovimentacao(@RequestBody String Movimentacao) {
		Gson gson = new Gson();
		Movimentacao d = null;
		d = gson.fromJson(Movimentacao, Movimentacao.class);
		//movDAO.DeleteMovimentacao(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getMovimentacao")
	public EntidadeBase getMovimentacao(@RequestBody String Movimentacao) {
		Gson gson = new Gson();
		Movimentacao d = null;
		d = gson.fromJson(Movimentacao, Movimentacao.class);
		return movDAO.GetMovimentacaoById(d.getCodigo());
	
		
	}
	@RequestMapping("/getallMovimentacoes")
	public List<EntidadeBase> getAllMovimentacao() {
		Gson gson = new Gson();
		List<EntidadeBase> Movimentacoes = new ArrayList<EntidadeBase>();
		Movimentacoes = gDAO.GetAll(Movimentacao.class);
		return Movimentacoes;
	
		
	}

}
