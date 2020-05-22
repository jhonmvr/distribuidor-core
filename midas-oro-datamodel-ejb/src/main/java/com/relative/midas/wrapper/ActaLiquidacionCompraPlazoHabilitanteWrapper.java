package com.relative.midas.wrapper;

import java.io.Serializable;

public class ActaLiquidacionCompraPlazoHabilitanteWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreApoderado;
	private String nombreCliente;
	private String identificacionCliente;
	private String numeroFunda;
	private String tipoOro;
	private String cantidad;
	private String descripcion;
	private String pesoNeto;
	private String pesoBruto;
	private String valorCompra;
	private String fechaCreacion;
	private String tasacion;
	private String servicioAdministrativo;
	private String custodia;
	private String total;
	private String nombreAgente;
	private String identificacionAgente;
	private String porcentajeTasacion;
	private String fechaPlazo;
	private String porcentajeTasacionB;
	private String servicioAdministrativoB;
	private String custodiaB;
	private String totalB;
	private String totalC;
	private String valorenLetras;
	
	
	public ActaLiquidacionCompraPlazoHabilitanteWrapper(String nombreApoderado, String nombreCliente, String identificacionCliente, String numeroFunda,
			String tipoOro, String cantidad, String descripcion, String pesoNeto, String pesoBruto, String valorCompra, String fechaCreacion, String tasacion,
			String servicioAdministrativo, String custodia, String total, String nombreAgente, String identificacionAgente, String porcentajeTasacion
			,String fechaPlazo, String custodiaB, String servicioAdministrativoB, String porcentajeTasacionB, String totalB, String totalC){
		
		this.nombreApoderado = nombreApoderado;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.numeroFunda = numeroFunda;
		this.tipoOro = tipoOro;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.pesoNeto = pesoNeto;
		this.pesoBruto = pesoBruto;
		this.valorCompra = valorCompra;
		this.fechaCreacion = fechaCreacion;
		this.fechaPlazo = fechaPlazo;
		this.tasacion = tasacion;
		this.porcentajeTasacion= porcentajeTasacion; 
		this.servicioAdministrativo = servicioAdministrativo;
		this.custodia = custodia;
		this.total = total;
		this.nombreAgente = nombreAgente;
		this.identificacionAgente = identificacionAgente;
		this.totalB = totalB;
		
		
	}

	public ActaLiquidacionCompraPlazoHabilitanteWrapper() {
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

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getNumeroFunda() {
		return numeroFunda;
	}

	public void setNumeroFunda(String numeroFunda) {
		this.numeroFunda = numeroFunda;
	}

	public String getTotalC() {
		return totalC;
	}

	public void setTotalC(String totalC) {
		this.totalC = totalC;
	}

	public String getTipoOro() {
		return tipoOro;
	}

	public void setTipoOro(String tipoOro) {
		this.tipoOro = tipoOro;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(String pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public String getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTotalB() {
		return totalB;
	}

	public void setTotalB(String totalB) {
		this.totalB = totalB;
	}

	public String getPorcentajeTasacionB() {
		return porcentajeTasacionB;
	}

	public void setPorcentajeTasacionB(String porcentajeTasacionB) {
		this.porcentajeTasacionB = porcentajeTasacionB;
	}

	public String getServicioAdministrativoB() {
		return servicioAdministrativoB;
	}

	public void setServicioAdministrativoB(String servicioAdministrativoB) {
		this.servicioAdministrativoB = servicioAdministrativoB;
	}

	public String getCustodiaB() {
		return custodiaB;
	}

	public void setCustodiaB(String custodiaB) {
		this.custodiaB = custodiaB;
	}

	public String getTasacion() {
		return tasacion;
	}

	public void setTasacion(String tasacion) {
		this.tasacion = tasacion;
	}

	public String getServicioAdministrativo() {
		return servicioAdministrativo;
	}

	public void setServicioAdministrativo(String servicioAdministrativo) {
		this.servicioAdministrativo = servicioAdministrativo;
	}

	public String getCustodia() {
		return custodia;
	}

	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}

	public String getTotal() {
		return total;
	}
	
	

	public String getValorenLetras() {
		return valorenLetras;
	}

	public void setValorenLetras(String valorenLetras) {
		this.valorenLetras = valorenLetras;
	}

	public String getPorcentajeTasacion() {
		return porcentajeTasacion;
	}

	public void setPorcentajeTasacion(String porcentajeTasacion) {
		this.porcentajeTasacion = porcentajeTasacion;
	}

	public String getFechaPlazo() {
		return fechaPlazo;
	}

	public void setFechaPlazo(String fechaPlazo) {
		this.fechaPlazo = fechaPlazo;
	}

	public void setTotal(String total) {
		this.total = total;
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
