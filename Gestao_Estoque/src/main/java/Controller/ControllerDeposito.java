package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.DepositoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Deposito;

@RestController
public class ControllerDeposito {
	DepositoDAO depDAO = new DepositoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarDeposito")
	public Deposito salvaDeposito(@RequestBody String Deposito) {
		Gson gson = new Gson();
		Deposito d = null;
		d = gson.fromJson(Deposito, Deposito.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deletedeposito")
	public Deposito updateDeposito(@RequestBody String Deposito) {
		Gson gson = new Gson();
		Deposito d = null;
		d = gson.fromJson(Deposito, Deposito.class);
		depDAO.DeleteDeposito(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getdeposito")
	public EntidadeBase getDeposito(@RequestBody String Deposito) {
		Gson gson = new Gson();
		Deposito d = null;
		d = gson.fromJson(Deposito, Deposito.class);
		return depDAO.GetDepositoById(d.getIdInt());
	
		
	}
	@RequestMapping("/getalldepositos")
	public List<EntidadeBase> getAllDeposito() {
		Gson gson = new Gson();
		List<EntidadeBase> Depositos = new ArrayList<EntidadeBase>();
		Depositos = gDAO.GetAll(Deposito.class);
		return Depositos;
	
		
	}
}
