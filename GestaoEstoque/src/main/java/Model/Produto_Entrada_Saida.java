package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produto_Entrada_Saida")
public class Produto_Entrada_Saida {
	@Id
	@OneToOne(fetch = FetchType.LAZY)
	private Entrada_Saida Entrada_Saida;
	@Id
	@OneToOne(fetch = FetchType.LAZY)
	private Produto Produto;
	@Column(name = "Custo")
	private double Custo;
	@Column(name = "Quantidade")
	private int Quantidade;
	@OneToOne(fetch = FetchType.LAZY)
	private Deposito Deposito;
	
	public Produto_Entrada_Saida() {
		
	}
	
	public Produto_Entrada_Saida(Model.Entrada_Saida entrada_Saida, Model.Produto produto, double custo, int quantidade,
			Model.Deposito deposito) {
		super();
		Entrada_Saida = entrada_Saida;
		Produto = produto;
		Custo = custo;
		Quantidade = quantidade;
		Deposito = deposito;
	}

	public Entrada_Saida getEntrada_Saida() {
		return Entrada_Saida;
	}

	public void setEntrada_Saida(Entrada_Saida entrada_Saida) {
		Entrada_Saida = entrada_Saida;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public double getCusto() {
		return Custo;
	}

	public void setCusto(double custo) {
		Custo = custo;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public Deposito getDeposito() {
		return Deposito;
	}

	public void setDeposito(Deposito deposito) {
		Deposito = deposito;
	}
	
	
	
	
	
	
}
