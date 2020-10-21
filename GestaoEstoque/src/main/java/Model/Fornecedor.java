package Model;

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

@Entity
@Table(name = "Fornecedores")
public class Fornecedor {
	@Id
	@Column(name = "Cnpj")
	private String Cnpj;
	@Column(name = "Nome")
	private String Nome;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Nota_Fiscal> Notas_Fiscais;

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
	
	public List<Nota_Fiscal> getNotas_Fiscais() {
		return this.Notas_Fiscais;
	}

	public void setNotas_Fiscais(Nota_Fiscal Nota_Fiscal) {
		if(Nota_Fiscal.getUtilizacao().equalsIgnoreCase("Compra")) {
			this.Notas_Fiscais.add(Nota_Fiscal);
		}
	}
	
	
}
