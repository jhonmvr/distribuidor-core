package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.util.MidasOroUtil;

public class JoyaReporteWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroFunda;
	private String tipoOro;
	private String cantidad;
	private String descripcion;
	private String pesoNeto;
	private String pesoBruto;
	private String valorCompra;
	private String totalValorCompra;
	private String totalPesoNeto;
	private String totalPesoBruto;
	
	
	public JoyaReporteWrapper() {
		
	}


	public JoyaReporteWrapper(String numeroFunda, String tipoOro, String cantidad, String descripcion, String pesoNeto,
			String pesoBruto, String valorCompra) {
		super();
		this.numeroFunda = numeroFunda;
		this.tipoOro = tipoOro;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.pesoNeto = pesoNeto;
		this.pesoBruto = pesoBruto;
		this.valorCompra = valorCompra;
	}


	public String getNumeroFunda() {
		return numeroFunda;
	}


	public void setNumeroFunda(String numeroFunda) {
		this.numeroFunda = numeroFunda;
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


	public String getTotalValorCompra() {
		return totalValorCompra;
	}


	public void setTotalValorCompra(String totalValorCompra) {
		this.totalValorCompra = totalValorCompra;
	}


	public String getTotalPesoNeto() {
		return totalPesoNeto;
	}


	public void setTotalPesoNeto(String totalPesoNeto) {
		this.totalPesoNeto = totalPesoNeto;
	}


	public String getTotalPesoBruto() {
		return totalPesoBruto;
	}


	public void setTotalPesoBruto(String totalPesoBruto) {
		this.totalPesoBruto = totalPesoBruto;
	}
	
	
	
	
}
