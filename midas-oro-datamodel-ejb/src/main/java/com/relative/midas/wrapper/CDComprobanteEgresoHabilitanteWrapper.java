package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiJoya;

public class CDComprobanteEgresoHabilitanteWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<TbMiJoya> tbMiJoyas;
	List<TbMiCliente> tbMiCliente;
	List<TbMiContratoCalculo> tbMiContratoCalculos;
	TbMiContrato TbMiContrato;
	public List<TbMiJoya> getTbMiJoyas() {
		return tbMiJoyas;
	}
	public void setTbMiJoyas(List<TbMiJoya> tbMiJoyas) {
		this.tbMiJoyas = tbMiJoyas;
	}
	public List<TbMiCliente> getTbMiCliente() {
		return tbMiCliente;
	}
	public void setTbMiCliente(List<TbMiCliente> tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}
	public List<TbMiContratoCalculo> getTbMiContratoCalculos() {
		return tbMiContratoCalculos;
	}
	public void setTbMiContratoCalculos(List<TbMiContratoCalculo> tbMiContratoCalculos) {
		this.tbMiContratoCalculos = tbMiContratoCalculos;
	}
	public TbMiContrato getTbMiContrato() {
		return TbMiContrato;
	}
	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		TbMiContrato = tbMiContrato;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
