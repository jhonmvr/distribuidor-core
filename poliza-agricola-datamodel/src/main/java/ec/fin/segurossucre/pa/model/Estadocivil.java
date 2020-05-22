package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ESTADOCIVIL database table.
 * 
 */
@Entity
//@NamedQuery(name="Estadocivil.findAll", query="SELECT e FROM Estadocivil e")
public class Estadocivil implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="ESTADOCIVIL_ESTADOCIVILID_GENERATOR", sequenceName="SEQ_ESTADOCIVIL")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADOCIVIL_ESTADOCIVILID_GENERATOR")
	private Long estadocivilid;

	private String estadocivilcod;

	@Temporal(TemporalType.DATE)
	private Date estadocivildeltim;

	private String estadocivildelusr;

	private String estadocivildes;

	@Temporal(TemporalType.DATE)
	private Date estadocivilinstim;

	private String estadocivilinsusr;

	private String estadocivilsts;

	@Temporal(TemporalType.DATE)
	private Date estadocivilupdtim;

	private String estadocivilupdusr;

	//bi-directional many-to-one association to TbSaAsegurado
	@OneToMany(mappedBy="estadocivil")
	private List<TbSaAsegurado> tbSaAsegurados;

	public Estadocivil() {
	}

	public Long getEstadocivilid() {
		return this.estadocivilid;
	}

	public void setEstadocivilid(Long estadocivilid) {
		this.estadocivilid = estadocivilid;
	}

	public String getEstadocivilcod() {
		return this.estadocivilcod;
	}

	public void setEstadocivilcod(String estadocivilcod) {
		this.estadocivilcod = estadocivilcod;
	}

	public Date getEstadocivildeltim() {
		return this.estadocivildeltim;
	}

	public void setEstadocivildeltim(Date estadocivildeltim) {
		this.estadocivildeltim = estadocivildeltim;
	}

	public String getEstadocivildelusr() {
		return this.estadocivildelusr;
	}

	public void setEstadocivildelusr(String estadocivildelusr) {
		this.estadocivildelusr = estadocivildelusr;
	}

	public String getEstadocivildes() {
		return this.estadocivildes;
	}

	public void setEstadocivildes(String estadocivildes) {
		this.estadocivildes = estadocivildes;
	}

	public Date getEstadocivilinstim() {
		return this.estadocivilinstim;
	}

	public void setEstadocivilinstim(Date estadocivilinstim) {
		this.estadocivilinstim = estadocivilinstim;
	}

	public String getEstadocivilinsusr() {
		return this.estadocivilinsusr;
	}

	public void setEstadocivilinsusr(String estadocivilinsusr) {
		this.estadocivilinsusr = estadocivilinsusr;
	}

	public String getEstadocivilsts() {
		return this.estadocivilsts;
	}

	public void setEstadocivilsts(String estadocivilsts) {
		this.estadocivilsts = estadocivilsts;
	}

	public Date getEstadocivilupdtim() {
		return this.estadocivilupdtim;
	}

	public void setEstadocivilupdtim(Date estadocivilupdtim) {
		this.estadocivilupdtim = estadocivilupdtim;
	}

	public String getEstadocivilupdusr() {
		return this.estadocivilupdusr;
	}

	public void setEstadocivilupdusr(String estadocivilupdusr) {
		this.estadocivilupdusr = estadocivilupdusr;
	}

	public List<TbSaAsegurado> getTbSaAsegurados() {
		return this.tbSaAsegurados;
	}

	public void setTbSaAsegurados(List<TbSaAsegurado> tbSaAsegurados) {
		this.tbSaAsegurados = tbSaAsegurados;
	}

	public TbSaAsegurado addTbSaAsegurado(TbSaAsegurado tbSaAsegurado) {
		getTbSaAsegurados().add(tbSaAsegurado);
		tbSaAsegurado.setEstadocivil(this);

		return tbSaAsegurado;
	}

	public TbSaAsegurado removeTbSaAsegurado(TbSaAsegurado tbSaAsegurado) {
		getTbSaAsegurados().remove(tbSaAsegurado);
		tbSaAsegurado.setEstadocivil(null);

		return tbSaAsegurado;
	}

}