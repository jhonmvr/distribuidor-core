package com.relative.midas.wrapper;

import java.io.Serializable;

public class AdendaContratoCompraVentaPlazoHabilitanteWrapper implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombreApoderado;
	private String identificacionApoderado;
	private String nombreCliente;
	private String identificacionCliente;
	private String nombreAgente;
	private String plazoContrato;
	private String renovacion;
	private String nuevaFechaVencimiento;
	private String custodiaAlmacenamiento;
	private String servicioAdministrativo;
	private String fechaCreacion;
	private String valorRenovacion;
	private String total; 
	private String numeroContrato;
	
	public AdendaContratoCompraVentaPlazoHabilitanteWrapper(String nombreApoderado, String identificacionApoderado, String nombreCliente,
			String identificacionCliente, String nombreAgente, String plazoContrato, String renovacion, String fechaCreacion,
			String nuevaFechaVencimiento, String valorRenovacion, String custodiaAlmacenamiento,
			String servicioAdministrativo, String total) {
		this.nombreApoderado = nombreApoderado;
		this.identificacionApoderado = identificacionApoderado;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.nombreAgente = nombreAgente;
		this.plazoContrato = plazoContrato;
		this.renovacion = renovacion;
		this.fechaCreacion = fechaCreacion;
		this.nuevaFechaVencimiento = nuevaFechaVencimiento;
		this.valorRenovacion = valorRenovacion;
		this.custodiaAlmacenamiento = custodiaAlmacenamiento;
		this.servicioAdministrativo = servicioAdministrativo;
		this.total = total;
	}

	public AdendaContratoCompraVentaPlazoHabilitanteWrapper() {
		// TODO Auto-generated constructor stub
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreApoderado() {
		return nombreApoderado;
	}

	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}

	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}

	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
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

	public String getNombreAgente() {
		return nombreAgente;
	}

	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

	public String getPlazoContrato() {
		return plazoContrato;
	}

	public void setPlazoContrato(String plazoContrato) {
		this.plazoContrato = plazoContrato;
	}

	public String getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	public String getCustodiaAlmacenamiento() {
		return custodiaAlmacenamiento;
	}

	public void setCustodiaAlmacenamiento(String custodiaAlmacenamiento) {
		this.custodiaAlmacenamiento = custodiaAlmacenamiento;
	}

	public String getServicioAdministrativo() {
		return servicioAdministrativo;
	}

	public void setServicioAdministrativo(String servicioAdministrativo) {
		this.servicioAdministrativo = servicioAdministrativo;
	}

	public String getNuevaFechaVencimiento() {
		return nuevaFechaVencimiento;
	}

	public void setNuevaFechaVencimiento(String nuevaFechaVencimiento) {
		this.nuevaFechaVencimiento = nuevaFechaVencimiento;
	}

	public String getValorRenovacion() {
		return valorRenovacion;
	}

	public void setValorRenovacion(String valorRenovacion) {
		this.valorRenovacion = valorRenovacion;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
