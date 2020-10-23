package Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import DAO.EntidadeBase;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Categoria", length = 1, discriminatorType = DiscriminatorType.STRING)
//Comum
@DiscriminatorValue(value = "C")
@Table(name = "Produtos")
public class Produto implements EntidadeBase{
	@Id
	@Column(name = "Codigo")
	private int Codigo;
	@Column(name = "Nome")
	private String Nome;
	
	
	public Produto() {
		
	}

	public Produto(int codigo, String nome) {
		super();
		Codigo = codigo;
		Nome = nome;
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
