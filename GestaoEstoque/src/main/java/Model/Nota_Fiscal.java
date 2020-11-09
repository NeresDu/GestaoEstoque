package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import DAO.EntidadeBase;

@Entity
@Table(name = "Notas_Fiscais")
public class Nota_Fiscal implements Serializable{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo")
	private int Codigo;
	@OneToOne(fetch = FetchType.LAZY)
	private Fornecedor_Cliente Fornecedor_Cliente;
	//venda, compra, devolução
	@Column(name = "Utilizacao")
	private String Utilizacao;
	@Column(name = "Data")
	private Date Data;
	@Id
	@Column (name = "Numeracao")
	private String Numeracao;
	
	public Nota_Fiscal() {
		
	}

	public Nota_Fiscal(int codigo, Model.Fornecedor_Cliente fornecedor, String utilizacao) {
		super();
		Codigo = codigo;
		Utilizacao = utilizacao;
	}
	
	
	
	public Fornecedor_Cliente getFornecedor_Cliente() {
		return Fornecedor_Cliente;
	}

	public void setFornecedor_Cliente(Fornecedor_Cliente fornecedor_Cliente) {
		Fornecedor_Cliente = fornecedor_Cliente;
	}

	public String getNumeracao() {
		return Numeracao;
	}

	public void setNumeracao(String numeracao) {
		Numeracao = numeracao;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date date) {
		Data = date;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getUtilizacao() {
		return Utilizacao;
	}

	public void setUtilizacao(String utilizacao) {
		Utilizacao = utilizacao;
	}

	
	
	
}
