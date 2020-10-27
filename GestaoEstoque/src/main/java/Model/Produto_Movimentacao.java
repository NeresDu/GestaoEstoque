package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produto_Movimentacao")
public class Produto_Movimentacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Codigo")
	private int codigo;
	@OneToOne(fetch = FetchType.LAZY)
	private Movimentacao Movimentacao;
	@OneToOne(fetch = FetchType.LAZY)
	private Produto Produto;
	@Column(name = "Custo")
	private double Custo;
	@Column(name = "Quantidade")
	private int Quantidade;
	@OneToOne(fetch = FetchType.LAZY)
	private Deposito Deposito;
	
	public Produto_Movimentacao() {
		
	}
	
	public Produto_Movimentacao(Model.Movimentacao Movimentacao, Model.Produto produto, double custo, int quantidade,
			Model.Deposito deposito) {
		super();
		Movimentacao = Movimentacao;
		Produto = produto;
		Custo = custo;
		Quantidade = quantidade;
		Deposito = deposito;
	}
	
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Movimentacao getMovimentacao() {
		return Movimentacao;
	}

	public void setMovimentacao(Movimentacao Movimentacao) {
		Movimentacao = Movimentacao;
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
