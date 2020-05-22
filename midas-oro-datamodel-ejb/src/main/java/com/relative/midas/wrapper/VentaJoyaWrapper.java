package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiMovimientoCaja;

public class VentaJoyaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1630126555275794866L;

	private TbMiJoya joya;
	private List<TbMiMovimientoCaja> ingresos;
	private TbMiCliente comprador;
	public TbMiJoya getJoya() {
		return joya;
	}
	public void setJoya(TbMiJoya joya) {
		this.joya = joya;
	}
	public List<TbMiMovimientoCaja> getIngresos() {
		return ingresos;
	}
	public void setIngresos(List<TbMiMovimientoCaja> ingresos) {
		this.ingresos = ingresos;
	}
	public TbMiCliente getComprador() {
		return comprador;
	}
	public void setComprador(TbMiCliente comprador) {
		this.comprador = comprador;
	}
	
}
