package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.relative.midas.enums.ProcesoEnum;

public class RetazarContratos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idContrato;
	private String codigoContrato;
	private ProcesoEnum proceso;
	private Date fechaActualizacion;
	private String primerNombre;
	private String codigoFunda;
	private String descripcion;
	private String tipoCompra;
	private Long idFunda;
	
	public RetazarContratos(Long idContrato, String codigo, ProcesoEnum proceso, Date fechaActualizacion,
			String primerNombre, String codigoFunda, String descripcion, String tipoCompra,Long idFunda) {
		super();
		this.idContrato = idContrato;
		this.codigoContrato = codigo;
		this.proceso = proceso;
		this.fechaActualizacion = fechaActualizacion;
		this.primerNombre = primerNombre;
		this.codigoFunda = codigoFunda;
		this.descripcion = descripcion;
		this.tipoCompra = tipoCompra;
		this.setIdFunda(idFunda);
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

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getNombre() {
		return primerNombre;
	}

	public void setNombre(String nombre) {
		this.primerNombre = nombre;
	}

	public String getCodigoFunda() {
		return codigoFunda;
	}

	public void setCodigoFunda(String codigoFunda) {
		this.codigoFunda = codigoFunda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public Long getIdFunda() {
		return idFunda;
	}

	public void setIdFunda(Long idFunda) {
		this.idFunda = idFunda;
	}
	
}
