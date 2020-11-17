package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DAO.EntidadeBase;

@Entity
@Table(name = "Movimentacao")
public class Movimentacao implements Serializable {
	@Column(name = "Data")
	private Date Data;
	@Column(name = "Tipo_Movimentacao")
	private String Tipo_Movimentacao;
	@Id
	@Column (name = "Numeracao")
	private String Numeracao;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Produto_Movimentacao> Produtos;
	
	public Movimentacao() {
	}
	
	public Movimentacao(int codigo, Date data,String tipo_Movimentacao) {
		super();
		Data = data;
		Tipo_Movimentacao = tipo_Movimentacao;
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

	public void setData(Date data) {
		Data = data;
	}

	public String getTipo_Movimentacao() {
		return Tipo_Movimentacao;
	}

	public void setTipo_Movimentacao(String tipo_Movimentacao) {
		Tipo_Movimentacao = tipo_Movimentacao;
	}

	public List<Produto_Movimentacao> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto_Movimentacao> produtos) {
		Produtos = produtos;
	}

	
	
}
