package com.relative.midas.wrapper;

import java.io.Serializable;

public class ContratoCompraPlazoHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombreApoderado;
	private String nombreCliente;
	private String identificacionCliente;
	private String precioCompra;
	private String telefono;
	private String email;
	private String nombreAgente;
	private String identificacionAgente;
	private String numeroContrato;
	private String fechaEmision;
	private String valorenLetras;
	
	
	

	
	
	public ContratoCompraPlazoHabilitanteWrapper() {
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

	public String getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
		
	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
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
