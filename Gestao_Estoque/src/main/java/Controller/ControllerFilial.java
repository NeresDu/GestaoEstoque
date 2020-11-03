package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import DAO.FilialDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Filial;

@RestController
public class ControllerFilial {
	FilialDAO filiDAO = new FilialDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarFilial")
	public Filial salvaFilial(@RequestBody String Filial) {
		Gson gson = new Gson();
		Filial d = null;
		d = gson.fromJson(Filial, Filial.class);
		gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteFilial")
	public Filial updateFilial(@RequestBody String Filial) {
		Gson gson = new Gson();
		Filial d = null;
		d = gson.fromJson(Filial, Filial.class);
		filiDAO.DeleteFilial(d.getCodigo());
		return d;
		
	}
	@RequestMapping("/getFilial")
	public EntidadeBase getFilial(@RequestBody String Filial) {
		Gson gson = new Gson();
		Filial d = null;
		d = gson.fromJson(Filial, Filial.class);
		return filiDAO.GetFilialById( d.getCodigo());
	
		
	}
	@RequestMapping("/getallFiliais")
	public List<EntidadeBase> getAllFilial() {
		Gson gson = new Gson();
		List<EntidadeBase> Filiais = new ArrayList<EntidadeBase>();
		Filiais = gDAO.GetAll(Filial.class);
		return Filiais;
	
		
	}

}
