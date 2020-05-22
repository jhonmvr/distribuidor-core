package com.relative.midas.wrapper;

import java.io.Serializable;

public class DetalleIngresoEgresoWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	private String proceso;
	private String tipoMovimiento;
	private String descripcion;
	private String numeroOperacion;
	private String tasacion;
	private String gastosAdministrativos;
	private String custodia;
	private String transporte;
	private String valorContrato;
	private String subtotal; 
	private String iva;
	private String total;
	private String revaloracion;
	private String fechaCreacion;
	
	
	
	/*
	public DetalleIngresoEgresoWrapper(String proceso, String tipoMovimiento, String descripcion,
			String numeroOperacion, String gastosAdministrativos, String custodia, String transporte, String subtotal,
			String iva, String total) {
		super();
		this.proceso = proceso;
		this.tipoMovimiento = tipoMovimiento;
		this.descripcion = descripcion;
		this.numeroOperacion = numeroOperacion;
		this.gastosAdministrativos = gastosAdministrativos;
		this.custodia = custodia;
		this.transporte = transporte;
		this.subtotal = subtotal;
		this.iva = iva;
		this.total = total;
		
		
	}
	
	*/
	public DetalleIngresoEgresoWrapper() {
		
	}
	
	
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getGastosAdministrativos() {
		return gastosAdministrativos;
	}
	public void setGastosAdministrativos(String gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}
	public String getCustodia() {
		return custodia;
	}
	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	
	
	public String getTasacion() {
		return tasacion;
	}


	public void setTasacion(String tasacion) {
		this.tasacion = tasacion;
	}


	public String getRevaloracion() {
		return revaloracion;
	}


	public void setRevaloracion(String revaloracion) {
		this.revaloracion = revaloracion;
	}
	
	


	public String getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}


	public String getValorContrato() {
		return valorContrato;
	}


	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}
	
	
	
	

}
