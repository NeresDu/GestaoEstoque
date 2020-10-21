package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Fornecedores")
public class Fornecedor {
	@Id
	@Column(name = "Cnpj")
	private String Cnpj;
	@Column(name = "Nome")
	private String Nome;

	public Fornecedor(String cnpj, String nome) {
		super();
		Cnpj = cnpj;
		Nome = nome;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	
}
