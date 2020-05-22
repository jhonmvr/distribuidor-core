package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiMovimientoCaja;

public class ContratoAccionWrapper implements Serializable{
	private List<TbMiContratoCalculo> detalleCalculos;
	private List<TbMiContratoCalculo> detalleCalculosCancelacion;
	private List<TbMiMovimientoCaja> detalleIngresos;
	private BigDecimal valor;
	private BigDecimal abono;
	public List<TbMiContratoCalculo> getDetalleCalculos() {
		return detalleCalculos;
	}
	public void setDetalleCalculos(List<TbMiContratoCalculo> detalleCalculos) {
		this.detalleCalculos = detalleCalculos;
	}
	public List<TbMiMovimientoCaja> getDetalleIngresos() {
		return detalleIngresos;
	}
	public void setDetalleIngresos(List<TbMiMovimientoCaja> detalleIngresos) {
		this.detalleIngresos = detalleIngresos;
	}
	public BigDecimal getAbono() {
		return abono;
	}
	public void setAbono(BigDecimal abono) {
		this.abono = abono;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public List<TbMiContratoCalculo> getDetalleCalculosCancelacion() {
		return detalleCalculosCancelacion;
	}
	public void setDetalleCalculosCancelacion(List<TbMiContratoCalculo> detalleCalculosCancelacion) {
		this.detalleCalculosCancelacion = detalleCalculosCancelacion;
	}
	
	
	
}
