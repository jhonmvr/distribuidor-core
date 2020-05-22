package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RIEGO database table.
 * 
 */
@Entity
//@NamedQuery(name="Riego.findAll", query="SELECT r FROM Riego r")
public class Riego implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="RIEGO_RIEGOID_GENERATOR", sequenceName="SEQ_RIEGO")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RIEGO_RIEGOID_GENERATOR")
	private Long riegoid;

	private String riegocod;

	@Temporal(TemporalType.DATE)
	private Date riegodeltim;

	private String riegodelusr;

	private String riegodes;

	@Temporal(TemporalType.DATE)
	private Date riegoinstim;

	private String riegoinsusr;

	private String riegosts;

	@Temporal(TemporalType.DATE)
	private Date riegoupdtim;

	private String riegoupdusr;

	//bi-directional many-to-one association to TbPaCaracteristicaCultivo
	@OneToMany(mappedBy="riegoBean")
	private List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos;

	public Riego() {
	}

	public Long getRiegoid() {
		return this.riegoid;
	}

	public void setRiegoid(Long riegoid) {
		this.riegoid = riegoid;
	}

	public String getRiegocod() {
		return this.riegocod;
	}

	public void setRiegocod(String riegocod) {
		this.riegocod = riegocod;
	}

	public Date getRiegodeltim() {
		return this.riegodeltim;
	}

	public void setRiegodeltim(Date riegodeltim) {
		this.riegodeltim = riegodeltim;
	}

	public String getRiegodelusr() {
		return this.riegodelusr;
	}

	public void setRiegodelusr(String riegodelusr) {
		this.riegodelusr = riegodelusr;
	}

	public String getRiegodes() {
		return this.riegodes;
	}

	public void setRiegodes(String riegodes) {
		this.riegodes = riegodes;
	}

	public Date getRiegoinstim() {
		return this.riegoinstim;
	}

	public void setRiegoinstim(Date riegoinstim) {
		this.riegoinstim = riegoinstim;
	}

	public String getRiegoinsusr() {
		return this.riegoinsusr;
	}

	public void setRiegoinsusr(String riegoinsusr) {
		this.riegoinsusr = riegoinsusr;
	}

	public String getRiegosts() {
		return this.riegosts;
	}

	public void setRiegosts(String riegosts) {
		this.riegosts = riegosts;
	}

	public Date getRiegoupdtim() {
		return this.riegoupdtim;
	}

	public void setRiegoupdtim(Date riegoupdtim) {
		this.riegoupdtim = riegoupdtim;
	}

	public String getRiegoupdusr() {
		return this.riegoupdusr;
	}

	public void setRiegoupdusr(String riegoupdusr) {
		this.riegoupdusr = riegoupdusr;
	}

	public List<TbPaCaracteristicaCultivo> getTbPaCaracteristicaCultivos() {
		return this.tbPaCaracteristicaCultivos;
	}

	public void setTbPaCaracteristicaCultivos(List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos) {
		this.tbPaCaracteristicaCultivos = tbPaCaracteristicaCultivos;
	}

	public TbPaCaracteristicaCultivo addTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().add(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setRiegoBean(this);

		return tbPaCaracteristicaCultivo;
	}

	public TbPaCaracteristicaCultivo removeTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().remove(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setRiegoBean(null);

		return tbPaCaracteristicaCultivo;
	}

}