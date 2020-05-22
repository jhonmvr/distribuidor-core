package ec.com.def.pa.wrapper;

import java.io.Serializable;
import java.util.Date;

public class RespuestaInspeccionWrapper implements Serializable{
	
	private String codigoSiniestro;
	private String idInspeccion;
	private Date fechaSincronizacion;
	private String mensaje;
	private String codigoRespuesta;
	
	public RespuestaInspeccionWrapper() {}
	
	public RespuestaInspeccionWrapper(String codigoSiniestro, String idInspeccion, Date fechaSincronizacion,
			String mensaje, String codigoRespuesta) {
		super();
		this.codigoSiniestro = codigoSiniestro;
		this.idInspeccion = idInspeccion;
		this.fechaSincronizacion = fechaSincronizacion;
		this.mensaje = mensaje;
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getCodigoSiniestro() {
		return codigoSiniestro;
	}
	public void setCodigoSiniestro(String codigoSiniestro) {
		this.codigoSiniestro = codigoSiniestro;
	}
	public String getIdInspeccion() {
		return idInspeccion;
	}
	public void setIdInspeccion(String idInspeccion) {
		this.idInspeccion = idInspeccion;
	}
	public Date getFechaSincronizacion() {
		return fechaSincronizacion;
	}
	public void setFechaSincronizacion(Date fechaSincronizacion) {
		this.fechaSincronizacion = fechaSincronizacion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}


}
