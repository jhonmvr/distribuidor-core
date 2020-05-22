package ec.com.def.pa.wrapper;

import java.util.Date;

public class RespuestaMagapAvisoCosechaWrapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1768384903672252054L;
	private String numeroOperacionAseguradora;
	private String numeroAvisoCosecha;
	private Date fechaRecepcionAseguradora;
	private String observacionesAseguradora;
	private String codigoRespuesta;
	public String getNumeroOperacionAseguradora() {
		return numeroOperacionAseguradora;
	}
	public void setNumeroOperacionAseguradora(String numeroOperacionAseguradora) {
		this.numeroOperacionAseguradora = numeroOperacionAseguradora;
	}
	public String getNumeroAvisoCosecha() {
		return numeroAvisoCosecha;
	}
	public void setNumeroAvisoCosecha(String numeroAvisoCosecha) {
		this.numeroAvisoCosecha = numeroAvisoCosecha;
	}
	public Date getFechaRecepcionAseguradora() {
		return fechaRecepcionAseguradora;
	}
	public void setFechaRecepcionAseguradora(Date fechaRecepcionAseguradora) {
		this.fechaRecepcionAseguradora = fechaRecepcionAseguradora;
	}
	public String getObservacionesAseguradora() {
		return observacionesAseguradora;
	}
	public void setObservacionesAseguradora(String observacionesAseguradora) {
		this.observacionesAseguradora = observacionesAseguradora;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}


}
