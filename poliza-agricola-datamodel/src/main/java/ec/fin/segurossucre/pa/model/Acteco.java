package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ACTECO database table.
 * 
 */
@Entity
//@NamedQuery(name="Acteco.findAll", query="SELECT a FROM Acteco a")
public class Acteco implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="ACTECO_AECCOD_GENERATOR", sequenceName="SEQ_ACTECO")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACTECO_AECCOD_GENERATOR")
	private String aeccod;

	private String aecdes;

	//bi-directional many-to-one association to TbSaAsegurado
	@OneToMany(mappedBy="acteco")
	private List<TbSaAsegurado> tbSaAsegurados;

	public Acteco() {
	}

	public String getAeccod() {
		return this.aeccod;
	}

	public void setAeccod(String aeccod) {
		this.aeccod = aeccod;
	}

	public String getAecdes() {
		return this.aecdes;
	}

	public void setAecdes(String aecdes) {
		this.aecdes = aecdes;
	}

	public List<TbSaAsegurado> getTbSaAsegurados() {
		return this.tbSaAsegurados;
	}

	public void setTbSaAsegurados(List<TbSaAsegurado> tbSaAsegurados) {
		this.tbSaAsegurados = tbSaAsegurados;
	}

	public TbSaAsegurado addTbSaAsegurado(TbSaAsegurado tbSaAsegurado) {
		getTbSaAsegurados().add(tbSaAsegurado);
		tbSaAsegurado.setActeco(this);

		return tbSaAsegurado;
	}

	public TbSaAsegurado removeTbSaAsegurado(TbSaAsegurado tbSaAsegurado) {
		getTbSaAsegurados().remove(tbSaAsegurado);
		tbSaAsegurado.setActeco(null);

		return tbSaAsegurado;
	}

}