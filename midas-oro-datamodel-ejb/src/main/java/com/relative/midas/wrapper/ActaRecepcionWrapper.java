package com.relative.midas.wrapper;

import java.io.Serializable;

public class ActaRecepcionWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	
	private String fechaEmision;
	private String identificacionCliente;
	private String nombreCliente;
	private String nombreAgente;
	private String identificacionAgente;
	private String total;
	private String totalEnLetras;
	private String fechaVenta;
	private String precioOnzaTroy;
	private String precioGramo;
	private String descuento;
	private String codigoVentaLote;
	
	
	
	
	
	
	public ActaRecepcionWrapper(
			) {}
	
	
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
	
	public String getNombreAgente() {
		return nombreAgente;
	}
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

	public String getIdentificacionAgente() {
		return identificacionAgente;
	}

	public void setIdentificacionAgente(String identificacionAgente) {
		this.identificacionAgente = identificacionAgente;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public String getFechaVenta() {
		return fechaVenta;
	}


	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	public String getPrecioOnzaTroy() {
		return precioOnzaTroy;
	}


	public void setPrecioOnzaTroy(String precioOnzaTroy) {
		this.precioOnzaTroy = precioOnzaTroy;
	}


	public String getPrecioGramo() {
		return precioGramo;
	}


	public void setPrecioGramo(String precioGramo) {
		this.precioGramo = precioGramo;
	}


	public String getDescuento() {
		return descuento;
	}


	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}


	public String getCodigoVentaLote() {
		return codigoVentaLote;
	}


	public void setCodigoVentaLote(String codigoVentaLote) {
		this.codigoVentaLote = codigoVentaLote;
	}


	public String getTotalEnLetras() {
		return totalEnLetras;
	}


	public void setTotalEnLetras(String totalEnLetras) {
		this.totalEnLetras = totalEnLetras;
	}

	
	
	
}
