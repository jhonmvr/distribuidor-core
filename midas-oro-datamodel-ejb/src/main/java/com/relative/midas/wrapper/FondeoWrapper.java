package com.relative.midas.wrapper;

import java.io.Serializable;

import com.relative.midas.model.TbMiMovimientoCaja;

public class FondeoWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2030402576282980621L;
	private TbMiMovimientoCaja ingreso;
	private TbMiMovimientoCaja egreso;
	private String usuario;
	
	public FondeoWrapper() {
		
	}
	public TbMiMovimientoCaja getIngreso() {
		return ingreso;
	}
	public void setIngreso(TbMiMovimientoCaja ingreso) {
		this.ingreso = ingreso;
	}
	public TbMiMovimientoCaja getEgreso() {
		return egreso;
	}
	public void setEgreso(TbMiMovimientoCaja egreso) {
		this.egreso = egreso;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
