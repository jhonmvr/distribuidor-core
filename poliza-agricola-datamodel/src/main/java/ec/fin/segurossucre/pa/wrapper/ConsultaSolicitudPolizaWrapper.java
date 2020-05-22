package ec.fin.segurossucre.pa.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ec.fin.segurossucre.pa.enums.EstadoSiniestroAgricolaEnum;



public class ConsultaSolicitudPolizaWrapper implements Serializable {
	private static final long serialVersionUID = -2603633238095180178L;
	private Long id;
	private String  codigo ;
	private String numeroTramite;
	private String  cedulaSolictante ;
	private String  nombreSolicitante;
	private String  apellidoSolicitante;
	private Date   fechaCreacion;
	private String  canal;
	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;

	private BigDecimal  costoHectarea;
	private BigDecimal  montoAsegurado;
	private String  observacion ;
	private BigDecimal  primaTotal;
	private String estadoValidado;

	
	public Long getId() {
		return id;
	}
	public ConsultaSolicitudPolizaWrapper(Long id, String codigo, String numeroTramite, String cedulaSolictante,
			String nombreSolicitante,String apellidoSolicitante, Date fechaCreacion, String canal, EstadoSiniestroAgricolaEnum estado,
			BigDecimal costoHectarea, BigDecimal montoAsegurado,String observacion ,BigDecimal  primaTotal) {
		

		super();
		this.id = id;
		this.codigo = codigo;
		this.numeroTramite = numeroTramite;
		this.cedulaSolictante = cedulaSolictante;
		this.nombreSolicitante = nombreSolicitante;
		this.apellidoSolicitante= apellidoSolicitante;
		this.fechaCreacion = fechaCreacion;
		this.canal = canal;
		this.estado = estado;
		this.costoHectarea = costoHectarea;
		this.montoAsegurado = montoAsegurado;
		this.observacion =observacion;
		this.primaTotal = primaTotal;
	}
	
	
	
	public ConsultaSolicitudPolizaWrapper() {
		super();
	}
	
	
	
	public String getEstadoValidado() {
		return estadoValidado;
	}
	public void setEstadoValidado(String estadoValidado) {
		this.estadoValidado = estadoValidado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public BigDecimal getPrimaTotal() {
		return primaTotal;
	}
	public void setPrimaTotal(BigDecimal primaTotal) {
		this.primaTotal = primaTotal;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public String getCedulaSolictante() {
		return cedulaSolictante;
	}
	public void setCedulaSolictante(String cedulaSolictante) {
		this.cedulaSolictante = cedulaSolictante;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getApellidoSolicitante() {
		return apellidoSolicitante;
	}
	public void setApellidoSolicitante(String apellidoSolicitante) {
		this.apellidoSolicitante = apellidoSolicitante;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public EstadoSiniestroAgricolaEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}
	public BigDecimal getCostoHectarea() {
		return costoHectarea;
	}
	public void setCostoHectarea(BigDecimal costoHectarea) {
		this.costoHectarea = costoHectarea;
	}
	public BigDecimal getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(BigDecimal montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	
	
}
