package com.relative.midas.wrapper;

import java.io.Serializable;

public class CierreCajaHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String agencia;
	private String fechaActual;
	private String fechaUltimoCuadre;
	private String saldoApertura;
	private String nombreAgente;
	private String diferencia;
	private String saldoActual;
	private String horaActual;
	private String justificacion;
	
	
	public String getAgencia() {
		return agencia;
	}
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getFechaUltimoCuadre() {
		return fechaUltimoCuadre;
	}

	public void setFechaUltimoCuadre(String fechaUltimoCuadre) {
		this.fechaUltimoCuadre = fechaUltimoCuadre;
	}

	public String getSaldoApertura() {
		return saldoApertura;
	}

	public void setSaldoApertura(String saldoApertura) {
		this.saldoApertura = saldoApertura;
	}

	public String getNombreAgente() {
		return nombreAgente;
	}

	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}


	public CierreCajaHabilitanteWrapper() {
		
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(String saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getHoraActual() {
		return horaActual;
	}

	public void setHoraActual(String horaActual) {
		this.horaActual = horaActual;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
	
	
}
