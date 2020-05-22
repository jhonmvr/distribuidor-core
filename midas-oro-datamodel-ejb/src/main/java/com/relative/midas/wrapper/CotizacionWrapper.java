package com.relative.midas.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.midas.model.TbMiCotizacion;
import com.relative.midas.model.TbMiJoyaSim;

public class CotizacionWrapper  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361921646476027352L;
	private TbMiCotizacion cotizacion;
	private List<TbMiJoyaSim> listaJoyas;
	public TbMiCotizacion getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(TbMiCotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	public List<TbMiJoyaSim> getListaJoyas() {
		return listaJoyas;
	}
	public void setListaJoyas(List<TbMiJoyaSim> listaJoyas) {
		this.listaJoyas = listaJoyas;
	}

}
