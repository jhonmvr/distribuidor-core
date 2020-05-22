package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import com.relative.midas.enums.ProcesoEnum;

public class MovimientosDetalleCierreCajaWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String concepto;
	private BigDecimal valorIngreso;
	private BigDecimal valorEgreso;
	
	
	

	public MovimientosDetalleCierreCajaWrapper(ProcesoEnum concepto, BigDecimal valorIngreso, BigDecimal valorEgreso) {
		
		this.concepto = concepto.toString();
		this.valorIngreso = valorIngreso;
		this.valorEgreso = valorEgreso;
	}




	public MovimientosDetalleCierreCajaWrapper() {
		
	}




	public String getConcepto() {
		return concepto;
	}




	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}




	public BigDecimal getValorIngreso() {
		return valorIngreso;
	}




	public void setValorIngreso(BigDecimal valorIngreso) {
		this.valorIngreso = valorIngreso;
	}




	public BigDecimal getValorEgreso() {
		return valorEgreso;
	}




	public void setValorEgreso(BigDecimal valorEgreso) {
		this.valorEgreso = valorEgreso;
	}
	
	

	
	
	
	

}
