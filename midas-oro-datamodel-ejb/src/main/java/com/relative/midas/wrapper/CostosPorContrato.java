package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CostosPorContrato implements Serializable{
	/**
	 * Simulacion de costos por contrato
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal montoContrato;
	private int porcentajeCustodia;
	private BigDecimal custodiaCancelar;
	private BigDecimal custodiaRenovar;
	private BigDecimal transporteCancelar;
	private BigDecimal revaloracionRevovar;
	private BigDecimal gastosAdministrativosCancelar;
	private BigDecimal gastosAdministrativosRenovar;
	private BigDecimal costoCancelacion;
	private BigDecimal costoRenovacion;
	private BigDecimal totalCancelar;
	private BigDecimal abonoCancelar;
	private BigDecimal abonoRenovar;
	private BigDecimal valorCancelar;
	private BigDecimal valorRenovar;
	
	public BigDecimal getMontoContrato() {
		return montoContrato;
	}
	public void setMontoContrato(BigDecimal montoContrato) {
		this.montoContrato = montoContrato;
	}
	public int getPorcentajeCustodia() {
		return porcentajeCustodia;
	}
	public void setPorcentajeCustodia(int porcentajeCustodia) {
		this.porcentajeCustodia = porcentajeCustodia;
	}
	public BigDecimal getCustodiaCancelar() {
		return custodiaCancelar;
	}
	public void setCustodiaCancelar(BigDecimal custodiaCancelar) {
		this.custodiaCancelar = custodiaCancelar;
	}
	public BigDecimal getCustodiaRenovar() {
		return custodiaRenovar;
	}
	public void setCustodiaRenovar(BigDecimal custodiaRenovar) {
		this.custodiaRenovar = custodiaRenovar;
	}
	public BigDecimal getTransporteCancelar() {
		return transporteCancelar;
	}
	public void setTransporteCancelar(BigDecimal transporteCancelar) {
		this.transporteCancelar = transporteCancelar;
	}
	public BigDecimal getRevaloracionRevovar() {
		return revaloracionRevovar;
	}
	public void setRevaloracionRevovar(BigDecimal revaloracionRevovar) {
		this.revaloracionRevovar = revaloracionRevovar;
	}
	public BigDecimal getGastosAdministrativosCancelar() {
		return gastosAdministrativosCancelar;
	}
	public void setGastosAdministrativosCancelar(BigDecimal gastosAdministrativosCancelar) {
		this.gastosAdministrativosCancelar = gastosAdministrativosCancelar;
	}
	public BigDecimal getGastosAdministrativosRenovar() {
		return gastosAdministrativosRenovar;
	}
	public void setGastosAdministrativosRenovar(BigDecimal gastosAdministrativosRenovar) {
		this.gastosAdministrativosRenovar = gastosAdministrativosRenovar;
	}
	public BigDecimal getCostoCancelacion() {
		return costoCancelacion;
	}
	public void setCostoCancelacion(BigDecimal costoCancelacion) {
		this.costoCancelacion = costoCancelacion;
	}
	public BigDecimal getCostoRenovacion() {
		return costoRenovacion;
	}
	public void setCostoRenovacion(BigDecimal costoRenovacion) {
		this.costoRenovacion = costoRenovacion;
	}
	public BigDecimal getTotalCancelar() {
		return totalCancelar;
	}
	public void setTotalCancelar(BigDecimal totalCancelar) {
		this.totalCancelar = totalCancelar;
	}
	public BigDecimal getAbonoCancelar() {
		return abonoCancelar;
	}
	public void setAbonoCancelar(BigDecimal abonoCancelar) {
		this.abonoCancelar = abonoCancelar;
	}
	public BigDecimal getAbonoRenovar() {
		return abonoRenovar;
	}
	public void setAbonoRenovar(BigDecimal abonoRenovar) {
		this.abonoRenovar = abonoRenovar;
	}
	public BigDecimal getValorCancelar() {
		return valorCancelar;
	}
	public void setValorCancelar(BigDecimal valorCancelar) {
		this.valorCancelar = valorCancelar;
	}
	public BigDecimal getValorRenovar() {
		return valorRenovar;
	}
	public void setValorRenovar(BigDecimal valorRenovar) {
		this.valorRenovar = valorRenovar;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
