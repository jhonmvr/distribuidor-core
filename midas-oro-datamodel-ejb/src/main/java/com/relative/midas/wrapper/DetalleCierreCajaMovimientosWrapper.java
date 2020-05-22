package com.relative.midas.wrapper;

import java.io.Serializable;

public class DetalleCierreCajaMovimientosWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String valorFondeos;
	private String valorCancelaciones;
	private String valorRenovaciones;
	private String valorVenta;
	private String valorSeparacionVenta;
	private String totalIngresos;
	private String transferenciasCaja;
	private String valorComprasDirectas;
	private String valorComprasPlazo;
	private String totalEgresos;
	private String saldoCierreCaja;
	private String saldoAperturaCaja;
	
	

	public DetalleCierreCajaMovimientosWrapper() {
		
	}
	
	
	public String getValorFondeos() {
		return valorFondeos;
	}

	public void setValorFondeos(String valorFondeos) {
		this.valorFondeos = valorFondeos;
	}

	public String getValorCancelaciones() {
		return valorCancelaciones;
	}

	public void setValorCancelaciones(String valorCancelaciones) {
		this.valorCancelaciones = valorCancelaciones;
	}

	public String getValorRenovaciones() {
		return valorRenovaciones;
	}

	public void setValorRenovaciones(String valorRenovaciones) {
		this.valorRenovaciones = valorRenovaciones;
	}

	public String getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(String valorVenta) {
		this.valorVenta = valorVenta;
	}

	public String getValorSeparacionVenta() {
		return valorSeparacionVenta;
	}

	public void setValorSeparacionVenta(String valorSeparacionVenta) {
		this.valorSeparacionVenta = valorSeparacionVenta;
	}

	public String getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(String totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public String getTransferenciasCaja() {
		return transferenciasCaja;
	}

	public void setTransferenciasCaja(String transferenciasCaja) {
		this.transferenciasCaja = transferenciasCaja;
	}

	public String getValorComprasDirectas() {
		return valorComprasDirectas;
	}

	public void setValorComprasDirectas(String valorComprasDirectas) {
		this.valorComprasDirectas = valorComprasDirectas;
	}

	public String getValorComprasPlazo() {
		return valorComprasPlazo;
	}

	public void setValorComprasPlazo(String valorComprasPlazo) {
		this.valorComprasPlazo = valorComprasPlazo;
	}

	public String getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(String totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public String getSaldoCierreCaja() {
		return saldoCierreCaja;
	}

	public void setSaldoCierreCaja(String saldoCierreCaja) {
		this.saldoCierreCaja = saldoCierreCaja;
	}

	public String getSaldoAperturaCaja() {
		return saldoAperturaCaja;
	}

	public void setSaldoAperturaCaja(String saldoAperturaCaja) {
		this.saldoAperturaCaja = saldoAperturaCaja;
	}

	
	
	
	
	
	

}
