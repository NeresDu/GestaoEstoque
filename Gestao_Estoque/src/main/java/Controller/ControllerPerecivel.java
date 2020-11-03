package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.PerecivelDAO;
import DAO.ProdutoDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Perecivel;

@RestController
public class ControllerPerecivel {
	PerecivelDAO pereDAO = new PerecivelDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarPerecivel")
	public Perecivel salvaPerecivel(@RequestBody String Perecivel) {
		Gson gson = new Gson();
		Perecivel d = null;
		d = gson.fromJson(Perecivel, Perecivel.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deletePerecivel")
	public Perecivel updatePerecivel(@RequestBody String Perecivel) {
		Gson gson = new Gson();
		Perecivel d = null;
		d = gson.fromJson(Perecivel, Perecivel.class);
		pereDAO.DeletePerecivel(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getPerecivel")
	public EntidadeBase getPerecivel(@RequestBody String Perecivel) {
		Gson gson = new Gson();
		Perecivel d = null;
		d = gson.fromJson(Perecivel, Perecivel.class);
		return pereDAO.GetPerecivelById(d.getIdInt());
	
		
	}
	@RequestMapping("/getallPereciveis")
	public List<EntidadeBase> getAllPerecivel() {
		Gson gson = new Gson();
		List<EntidadeBase> Pereciveis = new ArrayList<EntidadeBase>();
		Pereciveis = gDAO.GetAll(Perecivel.class);
		return Pereciveis;
	
		
	}

}
