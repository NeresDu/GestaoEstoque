package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.RiscoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Risco;

@RestController
public class ControllerRisco {
	RiscoDAO depDAO = new RiscoDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarRisco")
	public Risco salvaRisco(@RequestBody String Risco) {
		Gson gson = new Gson();
		Risco d = null;
		d = gson.fromJson(Risco, Risco.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteRisco")
	public Risco updateRisco(@RequestBody String Risco) {
		Gson gson = new Gson();
		Risco d = null;
		d = gson.fromJson(Risco, Risco.class);
		depDAO.DeleteRisco(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getRisco")
	public EntidadeBase getRisco(@RequestBody String Risco) {
		Gson gson = new Gson();
		Risco d = null;
		d = gson.fromJson(Risco, Risco.class);
		return depDAO.GetRiscoById(d.getIdInt());
	
		
	}
	@RequestMapping("/getallRiscos")
	public List<EntidadeBase> getAllRisco() {
		Gson gson = new Gson();
		List<EntidadeBase> Riscos = new ArrayList<EntidadeBase>();
		Riscos = gDAO.GetAll(Risco.class);
		return Riscos;
	
		
	}

}
