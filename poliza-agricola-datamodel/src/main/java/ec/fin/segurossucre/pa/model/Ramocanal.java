package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RAMOCANAL database table.
 * 
 */
@Entity
//@NamedQuery(name="Ramocanal.findAll", query="SELECT r FROM Ramocanal r")
public class Ramocanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RamocanalPK id;

	private BigDecimal canalcerrado;

	private String canalcerradoobservacion;

	private BigDecimal canalcodcompleto;

	private BigDecimal canalcotizacion;

	private BigDecimal canalfactura;

	@Temporal(TemporalType.DATE)
	private Date canalfchdesde;

	private BigDecimal canalnmaxdias;

	private String canalnombre;

	private String canalrevisionpago;

	private String canaltieneendosob;

	//bi-directional many-to-one association to TbPaSolicitudPoliza
	@OneToMany(mappedBy="ramocanal")
	private List<TbPaSolicitudPoliza> tbPaSolicitudPolizas;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	@JoinColumn(name="RAMOID")
	private Ramo ramo;

	public Ramocanal() {
	}

	public RamocanalPK getId() {
		return this.id;
	}

	public void setId(RamocanalPK id) {
		this.id = id;
	}

	public BigDecimal getCanalcerrado() {
		return this.canalcerrado;
	}

	public void setCanalcerrado(BigDecimal canalcerrado) {
		this.canalcerrado = canalcerrado;
	}

	public String getCanalcerradoobservacion() {
		return this.canalcerradoobservacion;
	}

	public void setCanalcerradoobservacion(String canalcerradoobservacion) {
		this.canalcerradoobservacion = canalcerradoobservacion;
	}

	public BigDecimal getCanalcodcompleto() {
		return this.canalcodcompleto;
	}

	public void setCanalcodcompleto(BigDecimal canalcodcompleto) {
		this.canalcodcompleto = canalcodcompleto;
	}

	public BigDecimal getCanalcotizacion() {
		return this.canalcotizacion;
	}

	public void setCanalcotizacion(BigDecimal canalcotizacion) {
		this.canalcotizacion = canalcotizacion;
	}

	public BigDecimal getCanalfactura() {
		return this.canalfactura;
	}

	public void setCanalfactura(BigDecimal canalfactura) {
		this.canalfactura = canalfactura;
	}

	public Date getCanalfchdesde() {
		return this.canalfchdesde;
	}

	public void setCanalfchdesde(Date canalfchdesde) {
		this.canalfchdesde = canalfchdesde;
	}

	public BigDecimal getCanalnmaxdias() {
		return this.canalnmaxdias;
	}

	public void setCanalnmaxdias(BigDecimal canalnmaxdias) {
		this.canalnmaxdias = canalnmaxdias;
	}

	public String getCanalnombre() {
		return this.canalnombre;
	}

	public void setCanalnombre(String canalnombre) {
		this.canalnombre = canalnombre;
	}

	public String getCanalrevisionpago() {
		return this.canalrevisionpago;
	}

	public void setCanalrevisionpago(String canalrevisionpago) {
		this.canalrevisionpago = canalrevisionpago;
	}

	public String getCanaltieneendosob() {
		return this.canaltieneendosob;
	}

	public void setCanaltieneendosob(String canaltieneendosob) {
		this.canaltieneendosob = canaltieneendosob;
	}

	public List<TbPaSolicitudPoliza> getTbPaSolicitudPolizas() {
		return this.tbPaSolicitudPolizas;
	}

	public void setTbPaSolicitudPolizas(List<TbPaSolicitudPoliza> tbPaSolicitudPolizas) {
		this.tbPaSolicitudPolizas = tbPaSolicitudPolizas;
	}

	public TbPaSolicitudPoliza addTbPaSolicitudPoliza(TbPaSolicitudPoliza tbPaSolicitudPoliza) {
		getTbPaSolicitudPolizas().add(tbPaSolicitudPoliza);
		tbPaSolicitudPoliza.setRamocanal(this);

		return tbPaSolicitudPoliza;
	}

	public TbPaSolicitudPoliza removeTbPaSolicitudPoliza(TbPaSolicitudPoliza tbPaSolicitudPoliza) {
		getTbPaSolicitudPolizas().remove(tbPaSolicitudPoliza);
		tbPaSolicitudPoliza.setRamocanal(null);

		return tbPaSolicitudPoliza;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}