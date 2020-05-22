package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PAIS database table.
 * 
 */
@Entity
//@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="PAIS_PAICOD_GENERATOR", sequenceName="SEQ_PAIS")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAIS_PAICOD_GENERATOR")
	private String paicod;

	private String painom;

	public Pais() {
	}

	public String getPaicod() {
		return this.paicod;
	}

	public void setPaicod(String paicod) {
		this.paicod = paicod;
	}

	public String getPainom() {
		return this.painom;
	}

	public void setPainom(String painom) {
		this.painom = painom;
	}

}