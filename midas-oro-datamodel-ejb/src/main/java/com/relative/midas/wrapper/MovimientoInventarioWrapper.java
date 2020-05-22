package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiJoya;

public class MovimientoInventarioWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TbMiJoya joya;
	private List<TrazabilidadWrapper> movimientosInventario;
	
	public TbMiJoya getJoya() {
		return joya;
	}
	public void setJoya(TbMiJoya joya) {
		this.joya = joya;
	}
	public List<TrazabilidadWrapper> getMovimientosInventario() {
		return movimientosInventario;
	}
	public void setMovimientosInventario(List<TrazabilidadWrapper> movimientosInventario) {
		this.movimientosInventario = movimientosInventario;
	}
	
	
	
	
}
