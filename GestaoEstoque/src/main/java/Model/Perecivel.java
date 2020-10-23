package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "P")
public class Perecivel extends Produto {
	@Column(name = "Data_Validade")
	private Date Data_Validade;
	
	public Perecivel() {
		
	}
	
	public Perecivel(int codigo, String nome, String categoria, Date data_Validade) {
		super(codigo, nome);
		Data_Validade = data_Validade;
	}

	public Date getData_Validade() {
		return Data_Validade;
	}

	public void setData_Validade(Date data_Validade) {
		Data_Validade = data_Validade;
	}
	
	
}
