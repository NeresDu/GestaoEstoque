package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Funcionarios")
public class Funcionario {
		
	@Id
	@Column(name = "Cpf")
	private String Cpf;
	
	//comprador
	@Column(name = "Cargo")
	private String Cargo;
	@Column(name = "Nome")
	private String Nome;

	public Funcionario() {
		
	}
	
	public Funcionario(String cargo, String cpf, String nome) {
		super();
		Cargo = cargo;
		Cpf = cpf;
		Nome = nome;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
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
	
	
}
