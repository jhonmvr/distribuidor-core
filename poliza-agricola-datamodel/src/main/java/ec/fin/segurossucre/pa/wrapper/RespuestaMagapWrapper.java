package ec.com.def.pa.wrapper;

import java.io.Serializable;

public class RespuestaMagapWrapper implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6755012013260790412L;
	
	private String numeroOperacionAseguradora;
	private String fechaRecepcionAseguradora;
	private String causaAseguradora;
	private String observacionesAseguradora;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String error;
	
	public String getNumeroOperacionAseguradora() {
		return numeroOperacionAseguradora;
	}
	public void setNumeroOperacionAseguradora(String numeroOperacionAseguradora) {
		this.numeroOperacionAseguradora = numeroOperacionAseguradora;
	}
	public String getFechaRecepcionAseguradora() {
		return fechaRecepcionAseguradora;
	}
	public void setFechaRecepcionAseguradora(String fechaRecepcionAseguradora) {
		this.fechaRecepcionAseguradora = fechaRecepcionAseguradora;
	}
	public String getCausaAseguradora() {
		return causaAseguradora;
	}
	public void setCausaAseguradora(String causaAseguradora) {
		this.causaAseguradora = causaAseguradora;
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
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
