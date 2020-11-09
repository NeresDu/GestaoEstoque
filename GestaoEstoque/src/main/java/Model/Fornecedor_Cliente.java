package Model;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "Fornecedores_Clientes")
public class Fornecedor_Cliente implements EntidadeBase{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo")
	private int Codigo;
	@Column(name = "Cnpj")
	private String Cnpj;
	@Column(name = "Cpf")
	private String Cpf;
	@Column(name = "Nome")
	private String Nome;
	@Column(name = "Tipo_Cadastro")
	private String Tipo_Cadastro;
	

	public Fornecedor_Cliente() {
		
	}	

	public Fornecedor_Cliente(int codigo, String cnpj, String cpf, String nome, String tipo_Cadastro) {
		super();
		Codigo = codigo;
		Cnpj = cnpj;
		Cpf = cpf;
		Nome = nome;
		Tipo_Cadastro = tipo_Cadastro;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTipo_Cadastro() {
		return Tipo_Cadastro;
	}

	public void setTipo_Cadastro(String tipo_Cadastro) {
		Tipo_Cadastro = tipo_Cadastro;
	}

	@Override
	public Serializable getId() {
		return Codigo;
	}
	
	
}
