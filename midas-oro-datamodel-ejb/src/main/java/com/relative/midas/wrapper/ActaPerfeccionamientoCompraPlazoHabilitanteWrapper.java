package com.relative.midas.wrapper;

import java.io.Serializable;

public class ActaPerfeccionamientoCompraPlazoHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreApoderado;
	private String nombreCliente;
	private String fechaCreacionContrato;
	private String fechaCreacion;
	private String identificacionApoderado;
	private String numeroContrato;
	
	public ActaPerfeccionamientoCompraPlazoHabilitanteWrapper(String nombreApoderado, String nombreCliente, String fechaCreacionContrato,
			String fechaCreacion, String identificacionApoderado) {
		this.nombreApoderado = nombreApoderado;
		this.nombreCliente = nombreCliente;
		this.fechaCreacionContrato = fechaCreacionContrato;
		this.fechaCreacion = fechaCreacion;
		this.identificacionApoderado = identificacionApoderado;		
	}

	public ActaPerfeccionamientoCompraPlazoHabilitanteWrapper() {
		// TODO Auto-generated constructor stub
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

	public String getFechaCreacionContrato() {
		return fechaCreacionContrato;
	}

	public void setFechaCreacionContrato(String fechaCreacionContrato) {
		this.fechaCreacionContrato = fechaCreacionContrato;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}

	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	
	
}
