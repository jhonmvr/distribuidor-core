package com.relative.midas.wrapper;

import java.io.Serializable;

public class ComprobanteEgresoHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	private String fechaEmision;
	private String identificacionCliente;
	private String nombreCliente;
	private String valorRecibido;
	private String nombreAgente;
	private String identificacionAgente;
	private String total;
	private String codigoComprobante;
	
	
	public ComprobanteEgresoHabilitanteWrapper() {
		
	}
	
	
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getValorRecibido() {
		return valorRecibido;
	}
	public void setValorRecibido(String valorRecibido) {
		this.valorRecibido = valorRecibido;
	}
	public String getNombreAgente() {
		return nombreAgente;
	}
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}



	public String getCodigoComprobante() {
		return codigoComprobante;
	}



	public void setCodigoComprobante(String codigoComprobante) {
		this.codigoComprobante = codigoComprobante;
	}



	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
	}



	public String getIdentificacionAgente() {
		return identificacionAgente;
	}



	public void setIdentificacionAgente(String identificacionAgente) {
		this.identificacionAgente = identificacionAgente;
	}
	
	

}
