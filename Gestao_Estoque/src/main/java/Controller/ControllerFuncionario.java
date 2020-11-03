package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.FuncionarioDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Funcionario;

@RestController
public class ControllerFuncionario {
	FuncionarioDAO funcDAO = new FuncionarioDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarFuncionario")
	public Funcionario salvaFuncionario(@RequestBody String Funcionario) {
		Gson gson = new Gson();
		Funcionario d = null;
		d = gson.fromJson(Funcionario, Funcionario.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteFuncionario")
	public Funcionario updateFuncionario(@RequestBody String Funcionario) {
		Gson gson = new Gson();
		Funcionario d = null;
		d = gson.fromJson(Funcionario, Funcionario.class);
		funcDAO.DeleteFuncionario(d.getCodigo());
		return d;
		
	}
	@RequestMapping("/getFuncionario")
	public EntidadeBase getFuncionario(@RequestBody String Funcionario) {
		Gson gson = new Gson();
		Funcionario d = null;
		d = gson.fromJson(Funcionario, Funcionario.class);
		return funcDAO.GetFuncionarioById(d.getCodigo());
	
		
	}
	@RequestMapping("/getallFuncionarios")
	public List<EntidadeBase> getAllFuncionario() {
		Gson gson = new Gson();
		List<EntidadeBase> Funcionarios = new ArrayList<EntidadeBase>();
		Funcionarios = gDAO.GetAll(Funcionario.class);
		return Funcionarios;
	
		
	}

}
