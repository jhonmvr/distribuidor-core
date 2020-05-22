package com.relative.midas.wrapper;

import java.io.Serializable;

public class DetalleCajaHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String denominacion;
	private String unidades;
	private String valor;

	public DetalleCajaHabilitanteWrapper() {
		
	}
	
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
