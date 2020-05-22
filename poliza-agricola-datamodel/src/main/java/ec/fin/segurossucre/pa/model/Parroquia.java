package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PARROQUIA database table.
 * 
 */
@Entity
//@NamedQuery(name="Parroquia.findAll", query="SELECT p FROM Parroquia p")
public class Parroquia implements Serializable {
	private static final Long serialVersionUID = 1L;

	@EmbeddedId
	private ParroquiaPK id;

	private String parroquiaestado;

	private String parroquiainfx;

	private String parroquianom;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CANTONID", referencedColumnName="CANTONID"),
		@JoinColumn(name="PROVINCIAID", referencedColumnName="PROVINCIAID")
		})
	private Canton canton;


	public Parroquia() {
	}

	public ParroquiaPK getId() {
		return this.id;
	}

	public void setId(ParroquiaPK id) {
		this.id = id;
	}

	public String getParroquiaestado() {
		return this.parroquiaestado;
	}

	public void setParroquiaestado(String parroquiaestado) {
		this.parroquiaestado = parroquiaestado;
	}

	public String getParroquiainfx() {
		return this.parroquiainfx;
	}

	public void setParroquiainfx(String parroquiainfx) {
		this.parroquiainfx = parroquiainfx;
	}

	public String getParroquianom() {
		return this.parroquianom;
	}

	public void setParroquianom(String parroquianom) {
		this.parroquianom = parroquianom;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}



}