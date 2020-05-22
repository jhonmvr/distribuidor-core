package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.midas.enums.EstadoMidasEnum;

public class HabilitanteWrapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombreArchivo;
	private EstadoMidasEnum estado;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Long idTipoDocumento;
	private String descripcionTipoCodumento;
	private Long idContrato;
	private String codigoContrato;
	private Long idJoya;
	private String codigoJoya;
	private Long idAbono;
	private Long idClienteAbono;
	private String identificacionCliente;
	private Long idCorteCaja;
	
	public HabilitanteWrapper(Long id, String nombreArchivo, EstadoMidasEnum estado, Date fechaCreacion,
			Date fechaActualizacion, Long idTipoDocumento, String descripcionTipoCodumento, Long idContrato,
			String codigoContrato, Long idJoya, String codigoJoya, Long idAbono, Long idClienteAbono,
			String identificacionCliente, Long idCorteCaja) {
		super();
		this.id = id;
		this.nombreArchivo = nombreArchivo;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.idTipoDocumento = idTipoDocumento;
		this.descripcionTipoCodumento = descripcionTipoCodumento;
		this.idContrato = idContrato;
		this.codigoContrato = codigoContrato;
		this.idJoya = idJoya;
		this.codigoJoya = codigoJoya;
		this.idAbono = idAbono;
		this.idClienteAbono = idClienteAbono;
		this.idCorteCaja = idCorteCaja;
		this.identificacionCliente = identificacionCliente;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public EstadoMidasEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDescripcionTipoCodumento() {
		return descripcionTipoCodumento;
	}
	public void setDescripcionTipoCodumento(String descripcionTipoCodumento) {
		this.descripcionTipoCodumento = descripcionTipoCodumento;
	}
	public Long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public Long getIdJoya() {
		return idJoya;
	}
	public void setIdJoya(Long idJoya) {
		this.idJoya = idJoya;
	}
	public String getCodigoJoya() {
		return codigoJoya;
	}
	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}
	public Long getIdAbono() {
		return idAbono;
	}
	public void setIdAbono(Long idAbono) {
		this.idAbono = idAbono;
	}
	public Long getIdCliente() {
		return idClienteAbono;
	}
	public void setIdCliente(Long idCliente) {
		this.idClienteAbono = idCliente;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public Long getIdCorteCaja() {
		return idCorteCaja;
	}

	public void setIdCorteCaja(Long idCorteCaja) {
		this.idCorteCaja = idCorteCaja;
	}
	
	
}