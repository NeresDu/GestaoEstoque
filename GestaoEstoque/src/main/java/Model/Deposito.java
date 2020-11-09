package Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "Depositos")
public class Deposito implements EntidadeBase{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo")
	private int Codigo;
	
	@Column(name = "Nome")
	private String Nome;
	
	@Column(name = "Categoria_Estoque")
	private String Categoria_Estoque;
	
	
	public Deposito() {
		
	}

	public Deposito(String nome, String categoria_Estoque, int codigo, List<Produto_Deposito> produtos) {
		super();
		Categoria_Estoque = categoria_Estoque;
		Codigo = codigo;
		Nome = nome;
	}

	public String getCategoria_Estoque() {
		return Categoria_Estoque;
	}

	public void setCategoria_Estoque(String categoria_Estoque) {
		Categoria_Estoque = categoria_Estoque;
	}
	@Override
	public Serializable getId() {
		return Codigo;
	}
	public int getIdInt() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	
	

}
