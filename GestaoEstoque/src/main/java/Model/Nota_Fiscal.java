package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
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
	@OneToOne(fetch = FetchType.LAZY)
	private Fornecedor_Cliente Fornecedor_Cliente;
	//venda, compra, devolução
	@Column(name = "Utilizacao")
	private String Utilizacao;
	@Column(name = "Data")
	private Calendar Data;
	@Id
	@Column (name = "Numeracao")
	private String Numeracao;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Produto_Nota_Fiscal> Produtos;
	
	public Nota_Fiscal() {
		
	}

	public Nota_Fiscal(int codigo, Model.Fornecedor_Cliente fornecedor, String utilizacao) {
		super();
		Utilizacao = utilizacao;
	}
	
	public List<Produto_Nota_Fiscal> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto_Nota_Fiscal> produtos) {
		Produtos = produtos;
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

	public Calendar getData() {
		return Data;
	}

	public void setData(Calendar c) {
		Data = c;
	}

	public String getUtilizacao() {
		return Utilizacao;
	}

	public void setUtilizacao(String utilizacao) {
		Utilizacao = utilizacao;
	}

	
	
	
}
