package com.relative.midas.wrapper;

import java.io.Serializable;

public class LoteWrapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String kilataje;
	private String ley;
	private String costoGramo;
	private String precioVenta;
	private String totalGramos;
	private String valorAPagar;
	
	
	
	public LoteWrapper() {
		
	}



	public String getKilataje() {
		return kilataje;
	}



	public void setKilataje(String kilataje) {
		this.kilataje = kilataje;
	}



	public String getLey() {
		return ley;
	}



	public void setLey(String ley) {
		this.ley = ley;
	}



	public String getCostoGramo() {
		return costoGramo;
	}



	public void setCostoGramo(String costoGramo) {
		this.costoGramo = costoGramo;
	}



	public String getPrecioVenta() {
		return precioVenta;
	}



	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}



	public String getTotalGramos() {
		return totalGramos;
	}



	public void setTotalGramos(String totalGramos) {
		this.totalGramos = totalGramos;
	}



	public String getValorAPagar() {
		return valorAPagar;
	}



	public void setValorAPagar(String valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	
	
	

}
