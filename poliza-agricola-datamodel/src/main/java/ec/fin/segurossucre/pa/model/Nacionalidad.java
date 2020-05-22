package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nacionalidad database table.
 * 
 */
@Entity
//@NamedQuery(name="Nacionalidad.findAll", query="SELECT p FROM Nacionalidad p")
public class Nacionalidad implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="Nacionalidad_nacionalidadid_GENERATOR", sequenceName="SEQ_Nacionalidad")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Nacionalidad_nacionalidadid_GENERATOR")
	private String nacionalidadid;

	private String nacionalidaddes;

	public Nacionalidad() {
	}

	public String getnacionalidadid() {
		return this.nacionalidadid;
	}

	public void setnacionalidadid(String nacionalidadid) {
		this.nacionalidadid = nacionalidadid;
	}

	public String getNacionalidaddes() {
		return nacionalidaddes;
	}

	public void setNacionalidaddes(String nacionalidaddes) {
		this.nacionalidaddes = nacionalidaddes;
	}
	
}