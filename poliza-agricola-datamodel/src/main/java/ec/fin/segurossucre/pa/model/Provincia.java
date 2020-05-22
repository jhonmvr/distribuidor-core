package ec.com.def.pa.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROVINCIA database table.
 * 
 */
@Entity
//@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private String provinciaid;

	private String provinciaestado;

	private String provinciainfx;

	private String provincianom;

	//bi-directional many-to-one association to Canton
	@OneToMany(mappedBy="provincia")
	private List<Canton> cantons;

	public Provincia() {
	}

	public String getProvinciaid() {
		return this.provinciaid;
	}

	public void setProvinciaid(String provinciaid) {
		this.provinciaid = provinciaid;
	}

	public String getProvinciaestado() {
		return this.provinciaestado;
	}

	public void setProvinciaestado(String provinciaestado) {
		this.provinciaestado = provinciaestado;
	}

	public String getProvinciainfx() {
		return this.provinciainfx;
	}

	public void setProvinciainfx(String provinciainfx) {
		this.provinciainfx = provinciainfx;
	}

	public String getProvincianom() {
		return this.provincianom;
	}

	public void setProvincianom(String provincianom) {
		this.provincianom = provincianom;
	}

	public List<Canton> getCantons() {
		return this.cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}

	public Canton addCanton(Canton canton) {
		getCantons().add(canton);
		canton.setProvincia(this);

		return canton;
	}

	public Canton removeCanton(Canton canton) {
		getCantons().remove(canton);
		canton.setProvincia(null);

		return canton;
	}

}