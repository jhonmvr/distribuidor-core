package com.relative.midas.wrapper;

import java.io.Serializable;
import java.sql.Date;

import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.ProcesoEnum;

public class TrazabilidadWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idHistoricoJoya;
	private String codigoJoya;
	private String nombreLote;
	private ProcesoEnum proceso;
	private String ubicacion;
	private String codigoFunda;
	private EstadoJoyaEnum estadoJoya;
	private String responsable;
	private Date fechaActualizacion;
	private String codigoVentaLote;
	
	/*
	 * public TrazabilidadWrapper(Long idHistoricoJoya, String codigoJoya, String
	 * nombreLote, ProcesoEnum proceso, String ubicacion, String codigoFunda,
	 * EstadoJoyaEnum estadoJoya, String responsable, java.sql.Date
	 * fechaActualizacion, String codigoVentaLote) {
	 * 
	 * }
	 */
	
	public TrazabilidadWrapper(Long idHistoricoJoya, String codigoJoya, String nombreLote, ProcesoEnum proceso,
			String ubicacion, String codigoFunda, EstadoJoyaEnum estadoJoya, String responsable,
			Date fechaActualizacion, String codigoVentaLote) {
		this.idHistoricoJoya = idHistoricoJoya;
		this.codigoJoya = codigoJoya;
		this.nombreLote = nombreLote;
		this.proceso = proceso;
		this.ubicacion = ubicacion;
		this.codigoFunda = codigoFunda;
		this.estadoJoya = estadoJoya;
		this.responsable = responsable;
		this.fechaActualizacion = fechaActualizacion;
		this.codigoVentaLote = codigoVentaLote;
	}
	public String getCodigoJoya() {
		return codigoJoya;
	}
	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}
	public String getNombreLote() {
		return nombreLote;
	}
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getCodigoFunda() {
		return codigoFunda;
	}
	public void setCodigoFunda(String codigoFunda) {
		this.codigoFunda = codigoFunda;
	}
	public EstadoJoyaEnum getEstadoJoya() {
		return estadoJoya;
	}
	public void setEstadoJoya(EstadoJoyaEnum estadoJoya) {
		this.estadoJoya = estadoJoya;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getCodigoVentaLote() {
		return codigoVentaLote;
	}
	public void setCodigoVentaLote(String codigoVentaLote) {
		this.codigoVentaLote = codigoVentaLote;
	}
	public Long getIdHistoricoJoya() {
		return idHistoricoJoya;
	}
	public void setIdHistoricoJoya(Long idHistoricoJoya) {
		this.idHistoricoJoya = idHistoricoJoya;
	}
	
}
