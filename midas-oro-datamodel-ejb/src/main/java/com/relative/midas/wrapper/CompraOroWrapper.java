package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiMovimientoCaja;

public class CompraOroWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<TbMiJoya> tbMiJoyas;
	List<TbMiContratoCalculo> tbMiContratoCalculos;
	TbMiContrato tbMiContrato;
	List<TbMiMovimientoCaja> tbMiMovimientoCaja;
	BigDecimal tasacion;
	TbMiAprobarContrato aprobarContrato;
	
	public List<TbMiJoya> getTbMiJoyas() {
		return tbMiJoyas;
	}
	public void setTbMiJoyas(List<TbMiJoya> tbMiJoyas) {
		this.tbMiJoyas = tbMiJoyas;
	}
	public TbMiContrato getTbMiContrato() {
		return tbMiContrato;
	}
	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}
	public List<TbMiContratoCalculo> getTbMiContratoCalculos() {
		return tbMiContratoCalculos;
	}
	public void setTbMiContratoCalculos(List<TbMiContratoCalculo> tbMiContratoCalculos) {
		this.tbMiContratoCalculos = tbMiContratoCalculos;
	}
	public List<TbMiMovimientoCaja> getTbMiMovimientoCaja() {
		return tbMiMovimientoCaja;
	}
	public void setTbMiMovimientoCaja(List<TbMiMovimientoCaja> tbMiMovimientoCaja) {
		this.tbMiMovimientoCaja = tbMiMovimientoCaja;
	}
	public BigDecimal getTasacion() {
		return tasacion;
	}
	public void setTasacion(BigDecimal tasacion) {
		this.tasacion = tasacion;
	}
	public TbMiAprobarContrato getAprobarContrato() {
		return aprobarContrato;
	}
	public void setAprobarContrato(TbMiAprobarContrato aprobarContrato) {
		this.aprobarContrato = aprobarContrato;
	}
	
	

}
