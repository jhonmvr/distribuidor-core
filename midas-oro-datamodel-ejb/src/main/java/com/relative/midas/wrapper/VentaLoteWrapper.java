package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.model.TbMiVentaLote;

public class VentaLoteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4118927048294347352L;
	
	private Long idAgencia;
	private TbMiVentaLote ventaLote;
	private List<TbMiLote> lotes;
	private String reUser;
	private List<TbMiMovimientoCaja> movimientos;
	private TbMiCliente cliente;
	
	
	public VentaLoteWrapper() {
		
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	public TbMiVentaLote getVentaLote() {
		return ventaLote;
	}
	public void setVentaLote(TbMiVentaLote ventaLote) {
		this.ventaLote = ventaLote;
	}
	public List<TbMiLote> getLotes() {
		return lotes;
	}
	public void setLotes(List<TbMiLote> lotes) {
		this.lotes = lotes;
	}
	public String getReUser() {
		return reUser;
	}
	public void setReUser(String reUser) {
		this.reUser = reUser;
	}
	public List<TbMiMovimientoCaja> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<TbMiMovimientoCaja> movimientos) {
		this.movimientos = movimientos;
	}
	public TbMiCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbMiCliente cliente) {
		this.cliente = cliente;
	}
	
	
}
