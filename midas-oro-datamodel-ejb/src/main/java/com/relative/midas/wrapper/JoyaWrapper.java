package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.util.MidasOroUtil;

public class JoyaWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoJoya;
	private String fechaDesde;
	private String fechaHasta;
	private List<String> estadosJoyaStr;
	private List<EstadoJoyaEnum> estadosJoya;
	public String getCodigoJoya() {
		return codigoJoya;
	}
	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}
	
	
	
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public List<String> getEstadosJoyaStr() {
		return estadosJoyaStr;
	}
	public void setEstadosJoyaStr(List<String> estadosJoyaStr) {
		this.estadosJoyaStr = estadosJoyaStr;
	}
	public List<EstadoJoyaEnum> getEstadosJoya() {
		if( this.estadosJoyaStr != null && !this.estadosJoyaStr.isEmpty() ) {
			this.estadosJoya= new ArrayList<>();
			estadosJoyaStr.forEach(e->{
				this.estadosJoya.add( MidasOroUtil.getEnumFromString(  EstadoJoyaEnum.class, e ) );
			});
		}
		return estadosJoya;
	}
	public void setEstadosJoya(List<EstadoJoyaEnum> estadosJoya) {
		this.estadosJoya = estadosJoya;
	}
	
	
	
}
