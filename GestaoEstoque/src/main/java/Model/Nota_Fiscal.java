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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notas_Fiscais")
public class Nota_Fiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Codigo")
	private int Codigo;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Produto_Nota_Fiscal> Produtos;
	
	//venda, compra, devolução
	@Column(name = "Utilizacao")
	private String Utilizacao;
	
	public Nota_Fiscal() {
		
	}

	public Nota_Fiscal(int codigo, Model.Fornecedor fornecedor, List<Produto_Nota_Fiscal> produtos, String utilizacao) {
		super();
		Codigo = codigo;
		Produtos = produtos;
		Utilizacao = utilizacao;
	}
	
	
	
	

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public List<Produto_Nota_Fiscal> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto_Nota_Fiscal> produtos) {
		Produtos = produtos;
	}

	public String getUtilizacao() {
		return Utilizacao;
	}

	public void setUtilizacao(String utilizacao) {
		Utilizacao = utilizacao;
	}
	
	
}
