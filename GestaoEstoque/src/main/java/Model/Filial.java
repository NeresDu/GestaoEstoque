package Model;

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

@Entity
@Table(name = "Filiais")
public class Filial {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Codigo")
	private int Codigo;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deposito> Depositos;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Funcionario> Funcionarios;

	public Filial() {
		
	}
	
	
	public Filial(int codigo, List<Deposito> depositos, List<Funcionario> funcionarios) {
		super();
		Codigo = codigo;
		Depositos = depositos;
		Funcionarios = funcionarios;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public List<Deposito> getDepositos() {
		return Depositos;
	}

	public void setDepositos(List<Deposito> depositos) {
		Depositos = depositos;
	}

	public List<Funcionario> getFuncionarios() {
		return Funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		Funcionarios = funcionarios;
	}
	
	
}
