package com.relative.midas.wrapper;

import java.io.Serializable;


public class ContratoCompraDirectaHabilitantewrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombre;
	private String nacionalidad;
	private String valorTasacion;
	private String valorContrato;
	private String telefonoCelular;
	private String email;
	private String fechaCreacion;
	private String identificacion;
	private String nombreAgente;
	private String identificacionAgente;
	private String nombreAporderado;
	private String nacionalidadApoderado;
	private String identificacionApoderado;
	private String numeroContrato;
	private String valorenLetras;


	public ContratoCompraDirectaHabilitantewrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public ContratoCompraDirectaHabilitantewrapper(String nombre, String nacionalidad, String valorTasacion, String valorContrato, String telefonoCelular,
			String email, String fechaCreacion, String identificacion, String nombreAgente, String identificacionAgente, String nombreApoderado, String nacionalidadApoderado, String identificacionApoderado ) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.valorTasacion = valorTasacion;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
		this.fechaCreacion = fechaCreacion;
		this.identificacion = identificacion;
		this.nombreAgente = nombreAgente;
		this.identificacionAgente = identificacionAgente;
		this.nombreAporderado = nombreApoderado;
		this.nacionalidadApoderado = nacionalidadApoderado;
		this.identificacionApoderado = identificacionApoderado;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getValorTasacion() {
		return valorTasacion;
	}

	public void setValorTasacion(String valorTasacion) {
		this.valorTasacion = valorTasacion;
	}

	public String getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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

	public String getNombreAporderado() {
		return nombreAporderado;
	}

	public void setNombreAporderado(String nombreAporderado) {
		this.nombreAporderado = nombreAporderado;
	}

	public String getNacionalidadApoderado() {
		return nacionalidadApoderado;
	}

	public void setNacionalidadApoderado(String nacionalidadApoderado) {
		this.nacionalidadApoderado = nacionalidadApoderado;
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
