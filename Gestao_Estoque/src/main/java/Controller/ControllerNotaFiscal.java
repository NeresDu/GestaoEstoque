package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import DAO.Nota_FiscalDAO;
import DAO.EntidadeBase;
import DAO.GenericoDAO;
import Model.Nota_Fiscal;

public class ControllerNotaFiscal {
	Nota_FiscalDAO depDAO = new Nota_FiscalDAO();
	GenericoDAO gDAO = new GenericoDAO();
	

	@RequestMapping("/salvarNota_Fiscal")
	public Nota_Fiscal salvaNota_Fiscal(@RequestBody String Nota_Fiscal) {
		Gson gson = new Gson();
		Nota_Fiscal d = null;
		d = gson.fromJson(Nota_Fiscal, Nota_Fiscal.class);
		//gDAO.Save(d);
		return d;
		
	}
	@RequestMapping("/deleteNota_Fiscal")
	public Nota_Fiscal updateNota_Fiscal(@RequestBody String Nota_Fiscal) {
		Gson gson = new Gson();
		Nota_Fiscal d = null;
		d = gson.fromJson(Nota_Fiscal, Nota_Fiscal.class);
		//depDAO.DeleteNota_Fiscal(d.getIdInt());
		return d;
		
	}
	@RequestMapping("/getNota_Fiscal")
	public Nota_Fiscal getNota_Fiscal(@RequestBody String Nota_Fiscal) {
		Gson gson = new Gson();
		Nota_Fiscal d = null;
		d = gson.fromJson(Nota_Fiscal, Nota_Fiscal.class);
		return depDAO.Consulta_Nota_Fiscal(d.getNumeracao());
	
		
	}
	@RequestMapping("/getallNota_Fiscais")
	public List<EntidadeBase> getAllNota_Fiscal() {
		Gson gson = new Gson();
		List<EntidadeBase> Nota_Fiscais = new ArrayList<EntidadeBase>();
		Nota_Fiscais = gDAO.GetAll(Nota_Fiscal.class);
		return Nota_Fiscais;
	
		
	}

}
