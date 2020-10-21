package Model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Risco extends Produto{
	@Column(name = "Grau_Risco")
	private String Grau_Risco;
	@Column(name = "Orientacao_Seguranca")
	private String Orientacao_Seguranca;
	
	public Risco() {
		
	}
	
	
	public Risco(int codigo, String nome, String categoria, String grau_Risco, String orientacao_Seguranca) {
		super(codigo, nome, categoria);
		Grau_Risco = grau_Risco;
		Orientacao_Seguranca = orientacao_Seguranca;
	}

	public String getGrau_Risco() {
		return Grau_Risco;
	}

	public void setGrau_Risco(String grau_Risco) {
		Grau_Risco = grau_Risco;
	}

	public String getOrientacao_Seguranca() {
		return Orientacao_Seguranca;
	}

	public void setOrientacao_Seguranca(String orientacao_Seguranca) {
		Orientacao_Seguranca = orientacao_Seguranca;
	}
	
	
	
}
