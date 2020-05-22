package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TIPOSEMILLA database table.
 * 
 */
@Entity
@NamedQuery(name="Tiposemilla.findAll", query="SELECT t FROM Tiposemilla t")
public class Tiposemilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOSEMILLA_TIPOSEMILLAID_GENERATOR", sequenceName="SEQ_TIPOSEMILLA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOSEMILLA_TIPOSEMILLAID_GENERATOR")
	private long tiposemillaid;

	private String tiposemillacod;

	@Temporal(TemporalType.DATE)
	private Date tiposemilladeltim;

	private String tiposemilladelusr;

	private String tiposemillades;

	@Temporal(TemporalType.DATE)
	private Date tiposemillainstim;

	private String tiposemillainsusr;

	private String tiposemillasts;

	@Temporal(TemporalType.DATE)
	private Date tiposemillaupdtim;

	private String tiposemillaupdusr;

	//bi-directional many-to-one association to TbPaCaracteristicaCultivo
	@OneToMany(mappedBy="tiposemilla")
	private List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos;

	public Tiposemilla() {
	}

	public long getTiposemillaid() {
		return this.tiposemillaid;
	}

	public void setTiposemillaid(long tiposemillaid) {
		this.tiposemillaid = tiposemillaid;
	}

	public String getTiposemillacod() {
		return this.tiposemillacod;
	}

	public void setTiposemillacod(String tiposemillacod) {
		this.tiposemillacod = tiposemillacod;
	}

	public Date getTiposemilladeltim() {
		return this.tiposemilladeltim;
	}

	public void setTiposemilladeltim(Date tiposemilladeltim) {
		this.tiposemilladeltim = tiposemilladeltim;
	}

	public String getTiposemilladelusr() {
		return this.tiposemilladelusr;
	}

	public void setTiposemilladelusr(String tiposemilladelusr) {
		this.tiposemilladelusr = tiposemilladelusr;
	}

	public String getTiposemillades() {
		return this.tiposemillades;
	}

	public void setTiposemillades(String tiposemillades) {
		this.tiposemillades = tiposemillades;
	}

	public Date getTiposemillainstim() {
		return this.tiposemillainstim;
	}

	public void setTiposemillainstim(Date tiposemillainstim) {
		this.tiposemillainstim = tiposemillainstim;
	}

	public String getTiposemillainsusr() {
		return this.tiposemillainsusr;
	}

	public void setTiposemillainsusr(String tiposemillainsusr) {
		this.tiposemillainsusr = tiposemillainsusr;
	}

	public String getTiposemillasts() {
		return this.tiposemillasts;
	}

	public void setTiposemillasts(String tiposemillasts) {
		this.tiposemillasts = tiposemillasts;
	}

	public Date getTiposemillaupdtim() {
		return this.tiposemillaupdtim;
	}

	public void setTiposemillaupdtim(Date tiposemillaupdtim) {
		this.tiposemillaupdtim = tiposemillaupdtim;
	}

	public String getTiposemillaupdusr() {
		return this.tiposemillaupdusr;
	}

	public void setTiposemillaupdusr(String tiposemillaupdusr) {
		this.tiposemillaupdusr = tiposemillaupdusr;
	}

	public List<TbPaCaracteristicaCultivo> getTbPaCaracteristicaCultivos() {
		return this.tbPaCaracteristicaCultivos;
	}

	public void setTbPaCaracteristicaCultivos(List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos) {
		this.tbPaCaracteristicaCultivos = tbPaCaracteristicaCultivos;
	}

	public TbPaCaracteristicaCultivo addTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().add(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setTiposemilla(this);

		return tbPaCaracteristicaCultivo;
	}

	public TbPaCaracteristicaCultivo removeTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().remove(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setTiposemilla(null);

		return tbPaCaracteristicaCultivo;
	}

}