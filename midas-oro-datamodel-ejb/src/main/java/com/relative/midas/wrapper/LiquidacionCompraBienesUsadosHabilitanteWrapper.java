package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

public class LiquidacionCompraBienesUsadosHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fechaCreacion;
	private String nombreAgente;
	private String identificacionAgente;
	private String direccion;
	private String telefono;
	private String nombreCliente;
	private String identificacionCliente;
	private String total;
	private String valorenLetras;
	private List<JoyaReporteWrapper> listaJoya;
	
	/*public LiquidacionCompraBienesUsadosHabilitanteWrapper(String fechaCreacion, String nombreAgente,
			String identificacionAgente, String direccion, String telefono, String nombreCliente,
			String identificacionCliente, String total JoyaReporteWrapper listaJoya) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.nombreAgente = nombreAgente;
		this.identificacionAgente = identificacionAgente;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.total = total;
		//this.listaJoya = listaJoya;
	}*/

	
	public LiquidacionCompraBienesUsadosHabilitanteWrapper() {
		
	}

	

	public String getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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
	
	
public String getValorenLetras() {
		return valorenLetras;
	}



	public void setValorenLetras(String valorenLetras) {
		this.valorenLetras = valorenLetras;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<JoyaReporteWrapper> getListaJoya() {
		
		
		
		return listaJoya;
	}



	public void setListaJoya(List<JoyaReporteWrapper> listaJoya) {
		this.listaJoya = listaJoya;
	}
	

}
