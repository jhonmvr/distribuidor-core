package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.Date;

public class ActaDevolucionCompraPlazoHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreApoderado;
	private String identificacionApoderado;
	private String nombreCliente;
	private String identificacionCliente;
	private String fechaCreacion;
	private String contrato;
	private String anticipo;
	private String servicioAdministrativo;
	private String custodiaAlmacenamiento;
	private String total;
	private String fechaCancelacion;
	private String nombreAgente;
	private String identificacionAgente;
	private String Total;
	
	public ActaDevolucionCompraPlazoHabilitanteWrapper(String nombreApoderado, String identificacionApoderado, String nombreCliente, String identificacionCliente,
			String fechaCreacion, String contrato, String anticipo, String servicioAdministrativo, String custodiaAlmacenamiento, String total,
			Date fechaVencimiento, String nombreAgente, String identificacionAgente, String Total) {
		this.nombreApoderado = nombreApoderado;
		this.identificacionApoderado = identificacionApoderado;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.fechaCreacion = fechaCreacion;
		this.contrato = contrato;
		this.anticipo = anticipo;
		this.servicioAdministrativo = servicioAdministrativo;
		this.custodiaAlmacenamiento = custodiaAlmacenamiento;
		this.total = total;
		
		this.nombreAgente = nombreAgente;
		this.identificacionAgente = identificacionAgente;
	
		this.total = total;
	}

	public ActaDevolucionCompraPlazoHabilitanteWrapper() {
		// TODO Auto-generated constructor stub
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(String anticipo) {
		this.anticipo = anticipo;
	}

	

	public String getServicioAdministrativo() {
		return servicioAdministrativo;
	}

	public void setServicioAdministrativo(String servicioAdministrativo) {
		this.servicioAdministrativo = servicioAdministrativo;
	}

	public String getCustodiaAlmacenamiento() {
		return custodiaAlmacenamiento;
	}

	public void setCustodiaAlmacenamiento(String custodiaAlmacenamiento) {
		this.custodiaAlmacenamiento = custodiaAlmacenamiento;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(String fechaVencimiento) {
		this.fechaCancelacion = fechaVencimiento;
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

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

