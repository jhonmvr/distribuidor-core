package ec.fin.segurossucre.pa.model;

import java.io.Serializable;


import javax.persistence.*;

import ec.fin.segurossucre.pa.enums.EstadoSiniestroAgricolaEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_PA_SOLICITUD_POLIZA database table.
 * 
 */
@Entity
@Table(name="TB_PA_SOLICITUD_POLIZA")
//@NamedQuery(name="TbPaSolicitudPoliza.findAll", query="SELECT t FROM TbPaSolicitudPoliza t")
public class TbPaSolicitudPoliza implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PA_SOLICITUD_POLIZA_ID_GENERATOR", sequenceName="SEQ_SOLICITUD_POLIZA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_SOLICITUD_POLIZA_ID_GENERATOR")
	private Long id;

	private String autorizacion;

	private String codigo;

	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;
	
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="NUMERO_TRAMITE")
	private String numeroTramite;

	@Column(name="TIENE_ENDOSO")
	private String tieneEndoso;

	@Lob
	@Column(name="UBICACION_PREDIO")
	private byte[] ubicacionPredio;
	
	@Column(name="PRIMA_TOTAL")
	private BigDecimal primaTotal;

	private String observacion;

	@Column(name="VALOR_ENDOSO")
	private BigDecimal valorEndoso;

	//bi-directional many-to-one association to Ramocanal
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CANAL", referencedColumnName="CANALID"),
		@JoinColumn(name="ID_RAMO", referencedColumnName="RAMOID")
		})
	private Ramocanal ramocanal;

	//bi-directional many-to-one association to TbPaCaracteristicaCultivo
	@ManyToOne
	@JoinColumn(name="ID_CULTIVO")
	private TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo;

	//bi-directional many-to-one association to TbPaPredio
	@ManyToOne
	@JoinColumn(name="ID_PREDIO")
	private TbPaPredio tbPaPredio;

	//bi-directional many-to-one association to TbSaAsegurado
	@ManyToOne
	@JoinColumn(name="ID_SOLICITANTE")
	private TbSaAsegurado tbSaAsegurado;
	
	@Column(name="NOM_EJECUTIVO")
	private String nombreEjecutivo;
	
	@Column(name="CONT_CANAL")
	private String contactoEjecutivo;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;
	
	
	public BigDecimal getPrimaTotal() {
		return primaTotal;
	}

	public void setPrimaTotal(BigDecimal primaTotal) {
		this.primaTotal = primaTotal;
	}
	

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNombreEjecutivo() {
		return nombreEjecutivo;
	}

	public void setNombreEjecutivo(String nombreEjecutivo) {
		this.nombreEjecutivo = nombreEjecutivo;
	}

	public String getContactoEjecutivo() {
		return contactoEjecutivo;
	}

	public void setContactoEjecutivo(String contactoEjecutivo) {
		this.contactoEjecutivo = contactoEjecutivo;
	}

	public TbPaSolicitudPoliza() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EstadoSiniestroAgricolaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNumeroTramite() {
		return this.numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	public String getTieneEndoso() {
		return this.tieneEndoso;
	}

	public void setTieneEndoso(String tieneEndoso) {
		this.tieneEndoso = tieneEndoso;
	}

	public byte[] getUbicacionPredio() {
		return this.ubicacionPredio;
	}

	public void setUbicacionPredio(byte[] ubicacionPredio) {
		this.ubicacionPredio = ubicacionPredio;
	}

	public BigDecimal getValorEndoso() {
		return this.valorEndoso;
	}

	public void setValorEndoso(BigDecimal valorEndoso) {
		this.valorEndoso = valorEndoso;
	}

	public Ramocanal getRamocanal() {
		return this.ramocanal;
	}

	public void setRamocanal(Ramocanal ramocanal) {
		this.ramocanal = ramocanal;
	}

	public TbPaCaracteristicaCultivo getTbPaCaracteristicaCultivo() {
		return this.tbPaCaracteristicaCultivo;
	}

	public void setTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		this.tbPaCaracteristicaCultivo = tbPaCaracteristicaCultivo;
	}

	public TbPaPredio getTbPaPredio() {
		return this.tbPaPredio;
	}

	public void setTbPaPredio(TbPaPredio tbPaPredio) {
		this.tbPaPredio = tbPaPredio;
	}

	public TbSaAsegurado getTbSaAsegurado() {
		return this.tbSaAsegurado;
	}

	public void setTbSaAsegurado(TbSaAsegurado tbSaAsegurado) {
		this.tbSaAsegurado = tbSaAsegurado;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

}