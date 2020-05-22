package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RAMO database table.
 * 
 */
@Entity
//@NamedQuery(name="Ramo.findAll", query="SELECT r FROM Ramo r")
public class Ramo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="RAMO_RAMOID_GENERATOR", sequenceName="SEQ_RAMO")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RAMO_RAMOID_GENERATOR")
	private String ramoid;

	private BigDecimal ramodividendo;

	private String ramonombre;

	//bi-directional many-to-one association to Ramocanal
	@OneToMany(mappedBy="ramo")
	private List<Ramocanal> ramocanals;

	//bi-directional many-to-one association to Ramoplan
	@OneToMany(mappedBy="ramo")
	private List<Ramoplan> ramoplans;

	public Ramo() {
	}

	public String getRamoid() {
		return this.ramoid;
	}

	public void setRamoid(String ramoid) {
		this.ramoid = ramoid;
	}

	public BigDecimal getRamodividendo() {
		return this.ramodividendo;
	}

	public void setRamodividendo(BigDecimal ramodividendo) {
		this.ramodividendo = ramodividendo;
	}

	public String getRamonombre() {
		return this.ramonombre;
	}

	public void setRamonombre(String ramonombre) {
		this.ramonombre = ramonombre;
	}

	public List<Ramocanal> getRamocanals() {
		return this.ramocanals;
	}

	public void setRamocanals(List<Ramocanal> ramocanals) {
		this.ramocanals = ramocanals;
	}

	public Ramocanal addRamocanal(Ramocanal ramocanal) {
		getRamocanals().add(ramocanal);
		ramocanal.setRamo(this);

		return ramocanal;
	}

	public Ramocanal removeRamocanal(Ramocanal ramocanal) {
		getRamocanals().remove(ramocanal);
		ramocanal.setRamo(null);

		return ramocanal;
	}

	public List<Ramoplan> getRamoplans() {
		return this.ramoplans;
	}

	public void setRamoplans(List<Ramoplan> ramoplans) {
		this.ramoplans = ramoplans;
	}

	public Ramoplan addRamoplan(Ramoplan ramoplan) {
		getRamoplans().add(ramoplan);
		ramoplan.setRamo(this);

		return ramoplan;
	}

	public Ramoplan removeRamoplan(Ramoplan ramoplan) {
		getRamoplans().remove(ramoplan);
		ramoplan.setRamo(null);

		return ramoplan;
	}

}