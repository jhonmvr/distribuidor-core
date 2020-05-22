package ec.fin.segurossucre.pa.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ConsultaGeneralWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2603633238095180178L;

	private BigDecimal id;
	private String idSiniestro;
	private String nroTramite;
	private String numeroReclamo;
	private Timestamp fechaSiniestro;
	private Timestamp fechaRecepcion;
	private Timestamp fechaIngreso;
	private String nombreAsegurado;
	private String provincia;
	private String causaSiniestro;
	private String cultivo;
	private String canal;
	private String estado;
	private String tipoPerdida;
	private String mensaje;
	private String observacion;
	private BigDecimal valorIndemnizacion;
	private Timestamp fechaFinInspeccion;
	private String estadoInspeccion;
	private String fechaActualizacion;
	private String numeroReserva;
	private Timestamp fechaDesde;
	private Timestamp fechaHasta;
	private String identificacion;
	private String codigoAvisoCosecha;

	private BigDecimal xx;

	/*
	 * String, String, Timestamp, Timestamp String, String, String, String, String,
	 * String, String, BigDecimal, Timestamp, Timestamp, String
	 */

	public ConsultaGeneralWrapper(BigDecimal id, String idSiniestro, String nroTramite, Timestamp fechaSiniestro,
			Timestamp fechaRecepcion, Timestamp fechaIngreso, String nombreAsegurado, String provincia,
			String causaSiniestro, String cultivo, String canal, String estado, String tipoPerdida, String mensaje,
			String observacion, BigDecimal valorIndemnizacion, Timestamp fechaFinInspeccion, String estadoInspeccion,
			String fechaActualizacion, String numeroReserva, String identificacion, String codigoAvisoCosecha) {

		this.idSiniestro = idSiniestro;
		this.nroTramite = nroTramite;
		this.fechaSiniestro = fechaSiniestro;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaIngreso = fechaIngreso;
		this.nombreAsegurado = nombreAsegurado;
		this.provincia = provincia;
		this.causaSiniestro = causaSiniestro;
		this.cultivo = cultivo;
		this.canal = canal;
		this.estado = estado;
		this.tipoPerdida = tipoPerdida;
		this.mensaje = mensaje;
		this.observacion = observacion;
		this.valorIndemnizacion = valorIndemnizacion;
		this.fechaFinInspeccion = fechaFinInspeccion;
		this.estadoInspeccion = estadoInspeccion;
		this.fechaActualizacion = fechaActualizacion;
		this.numeroReserva = numeroReserva;
		this.identificacion = identificacion;
		this.codigoAvisoCosecha = codigoAvisoCosecha!=null?codigoAvisoCosecha.trim():"";
		this.id = id;
	}

	public ConsultaGeneralWrapper(BigDecimal id, String idSiniestro, String nroTramite, Timestamp fechaSiniestro,
			Timestamp fechaRecepcion, Timestamp fechaIngreso, String nombreAsegurado, String provincia,
			String causaSiniestro, String cultivo, String canal, String estado, String tipoPerdida, String mensaje,
			String observacion, BigDecimal valorIndemnizacion, Timestamp fechaFinInspeccion, String estadoInspeccion,
			String fechaActualizacion, String numeroReserva, String identificacion, String codigoAvisoCosecha,
			BigDecimal xx) {
		this.idSiniestro = idSiniestro;
		this.nroTramite = nroTramite;
		this.fechaSiniestro = fechaSiniestro;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaIngreso = fechaIngreso;
		this.nombreAsegurado = nombreAsegurado;
		this.provincia = provincia;
		this.causaSiniestro = causaSiniestro;
		this.cultivo = cultivo;
		this.canal = canal;
		this.estado = estado;
		this.tipoPerdida = tipoPerdida;
		this.mensaje = mensaje;
		this.observacion = observacion;
		this.valorIndemnizacion = valorIndemnizacion;
		this.fechaFinInspeccion = fechaFinInspeccion;
		this.estadoInspeccion = estadoInspeccion;
		this.fechaActualizacion = fechaActualizacion;
		this.numeroReserva = numeroReserva;
		this.identificacion = identificacion;
		this.id = id;
		this.codigoAvisoCosecha = codigoAvisoCosecha!=null?codigoAvisoCosecha.trim():"";
		this.xx = xx;
	}

	

	public String getIdSiniestro() {
		return idSiniestro;
	}

	public void setIdSiniestro(String idSiniestro) {
		this.idSiniestro = idSiniestro;
	}

	public String getNroTramite() {
		return nroTramite;
	}

	public void setNroTramite(String nroTramite) {
		this.nroTramite = nroTramite;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaSiniestro() {
		return fechaSiniestro;
	}

	public void setFechaSiniestro(Timestamp fechaSiniestro) {
		this.fechaSiniestro = fechaSiniestro;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCausaSiniestro() {
		return causaSiniestro;
	}

	public void setCausaSiniestro(String causaSiniestro) {
		this.causaSiniestro = causaSiniestro;
	}

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public Timestamp getFechaFinInspeccion() {
		return fechaFinInspeccion;
	}

	public void setFechaFinInspeccion(Timestamp fechaFinInspeccion) {
		this.fechaFinInspeccion = fechaFinInspeccion;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTipoPerdida(String tipoPerdida) {
		this.tipoPerdida = tipoPerdida;
	}

	public String getTipoPerdida() {
		return tipoPerdida;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getValorIndemnizacion() {
		return valorIndemnizacion;
	}

	public void setValorIndemnizacion(BigDecimal valorIndemnizacion) {
		this.valorIndemnizacion = valorIndemnizacion;
	}

	public Timestamp getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Timestamp fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Timestamp getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Timestamp fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEstadoInspeccion() {
		return estadoInspeccion;
	}

	public void setEstadoInspeccion(String estadoInspeccion) {
		this.estadoInspeccion = estadoInspeccion;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getXx() {
		return xx;
	}

	public void setXx(BigDecimal xx) {
		this.xx = xx;
	}

	public String getNumeroReclamo() {
		return numeroReclamo;
	}

	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}

	public String getCodigoAvisoCosecha() {
		return codigoAvisoCosecha;
	}

	public void setCodigoAvisoCosecha(String codigoAvisoCosecha) {
		this.codigoAvisoCosecha = codigoAvisoCosecha;
	}
	
	

}
