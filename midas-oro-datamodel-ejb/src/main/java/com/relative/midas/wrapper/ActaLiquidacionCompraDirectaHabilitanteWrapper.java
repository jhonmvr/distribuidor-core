package com.relative.midas.wrapper;

import java.io.Serializable;

public class ActaLiquidacionCompraDirectaHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombreApoderado;
	private String nombreCliente;
	private String identificacionCliente;
	private String fechaLiquidacion;
	private String valorContrato;
	private String fechaCreacion;
	private String nombreAgente;
	private String identificacionAgente;
	private String valorenLetras; 


	
	

	public ActaLiquidacionCompraDirectaHabilitanteWrapper() {
	
	}



	public String getNombreApoderado() {
		return nombreApoderado;
	}



	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}



	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}



	public String getIdentificacionCliente() {
		return identificacionCliente;
	}



	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}



	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}



	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}



	public String getValorContrato() {
		return valorContrato;
	}



	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}



	public String getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	

	
	

	public String getValorenLetras() {
		return valorenLetras;
	}



	public void setValorenLetras(String valorenLetras) {
		this.valorenLetras = valorenLetras;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
