package com.relative.midas.wrapper;


import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiJoyaSim;

public class AprobacionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1440112020147781455L;
	
	
	private TbMiCliente cliente;
	private List<TbMiJoyaSim> joyas;
	private List<TbMiContratoCalculo> calculos;
	private TbMiAprobarContrato aprobacion;
	
	
	public TbMiCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbMiCliente cliente) {
		this.cliente = cliente;
	}
	public List<TbMiJoyaSim> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbMiJoyaSim> joyas) {
		this.joyas = joyas;
	}
	public List<TbMiContratoCalculo> getCalculos() {
		return calculos;
	}
	public void setCalculos(List<TbMiContratoCalculo> calculos) {
		this.calculos = calculos;
	}
	public TbMiAprobarContrato getAprobacion() {
		return aprobacion;
	}
	public void setAprobacion(TbMiAprobarContrato aprobacion) {
		this.aprobacion = aprobacion;
	}


}
