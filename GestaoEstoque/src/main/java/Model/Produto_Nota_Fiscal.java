package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos_Nota_Fiscal")
public class Produto_Nota_Fiscal {
	//deposito no qual ocorrerá a movimentação
	@OneToOne(fetch = FetchType.LAZY)
	private Deposito Deposito;
	@Column(name = "Custo")
	private double Custo;
	@OneToOne(fetch = FetchType.LAZY)
	private Produto Produto;
	@Column(name = "Quantidade")
	private int Quantidade;
	
	public Produto_Nota_Fiscal() {
		
	}

	public Produto_Nota_Fiscal(Model.Deposito deposito, double custo, Model.Produto produto, int quantidade) {
		super();
		Deposito = deposito;
		Custo = custo;
		Produto = produto;
		Quantidade = quantidade;
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

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	
	
	
}
