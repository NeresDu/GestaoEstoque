package Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import DAO.EntidadeBase;

@Entity
@Table(name = "Produtos_Nota_Fiscal")
public class Produto_Nota_Fiscal implements Serializable {
	//deposito no qual ocorrerá a movimentação
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo")
	private int codigo;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Deposito Deposito;
	@Column(name = "Custo")
	private double Custo;
	@Column(name = "Quantidade")
	private int Quantidade;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Nota_Fiscal Nota_Fiscal;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Produto Produto;
	
	public Produto_Nota_Fiscal() {
		
	}
	

	public Produto_Nota_Fiscal(Model.Deposito deposito, double custo, Produto Produto, int quantidade,
			Nota_Fiscal Nota_Fiscal) {
		super();
		Deposito = deposito;
		Custo = custo;
		Produto = Produto;
		Quantidade = quantidade;
		Nota_Fiscal = Nota_Fiscal;
	}

	

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Deposito getDeposito() {
		return Deposito;
	}

	public void setDeposito(Deposito deposito) {
		Deposito = deposito;
	}
	
	public double getCusto() {
		return Custo;
	}

	public void setCusto(double custo) {
		Custo = custo;
	}


	public Model.Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto Produto) {
		this.Produto = Produto;
	}


	public int getQuantidade() {
		return Quantidade;
	}


	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}


	public Nota_Fiscal getNota_Fiscal() {
		return Nota_Fiscal;
	}
	
	public void setNota_Fiscal(Nota_Fiscal nota_Fiscal) {
		this.Nota_Fiscal = nota_Fiscal;
	}





	


	
	
}
