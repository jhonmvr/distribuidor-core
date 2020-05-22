package ec.com.def.pa.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class InformeInspeccionSolicitudWrapper implements Serializable {
	
	protected String codigoSiniestro;
	protected String codigoAvisoCosecha;
	protected String codigoProveedor;
	protected String codigoInspector;
	protected String cedulaAgricultor;
	protected String nombreAgricultor;
	protected String celularAgricultor;
	protected String nombreCanal;
	protected String numeroTramite;
	protected String nombreCultivo;
	protected String nombreProvincia;
	protected String nombreCanton;
	protected String nombreParroquia;
	protected String nombreRecinto;
	protected String referencia;
	protected BigDecimal coordenadaX;
	protected BigDecimal coordenadaY;
	protected BigDecimal hectareasAseguradas;
	protected BigDecimal montoAsegurado;
	protected String fechaRealSiembra;
	protected String fechaAviso;
	protected String fechaAvisoCosecha;
	protected String fechaSiniestro;
	protected String nombreCausa;
	protected String fechaEnvioInspeccion;
	protected String numeroPoliza;
	protected String numeroCertificado;
	protected String fechaInicioVigencia;
	protected String fechaFinVigencia;
	protected String numeroReclamo;
	protected String observaciones;
	
	public InformeInspeccionSolicitudWrapper() {}
	
	
	public InformeInspeccionSolicitudWrapper(String codigoSiniestro, String codigoProveedor, String codigoInspector,
			String cedulaAgricultor, String nombreAgricultor, String celularAgricultor, String nombreCanal,
			String numeroTramite, String nombreCultivo, String nombreProvincia, String nombreCanton,
			String nombreParroquia, String nombreRecinto, String referencia, BigDecimal coordenadaX,
			BigDecimal coordenadaY, BigDecimal hectareasAseguradas, BigDecimal montoAsegurado, String fechaRealSiembra,
			String fechaAviso, String fechaSiniestro, String nombreCausa, String fechaEnvioInspeccion,
			String numeroPoliza, String numeroCertificado, String fechaInicioVigencia, String fechaFinVigencia,
			String numeroReclamo, String observaciones, String fechaAvisoCosecha, String codigoAvisoCosecha) {
		super();
		this.codigoSiniestro = codigoSiniestro;
		this.codigoProveedor = codigoProveedor;
		this.codigoInspector = codigoInspector;
		this.cedulaAgricultor = cedulaAgricultor;
		this.nombreAgricultor = nombreAgricultor;
		this.celularAgricultor = celularAgricultor;
		this.nombreCanal = nombreCanal;
		this.numeroTramite = numeroTramite;
		this.nombreCultivo = nombreCultivo;
		this.nombreProvincia = nombreProvincia;
		this.nombreCanton = nombreCanton;
		this.nombreParroquia = nombreParroquia;
		this.nombreRecinto = nombreRecinto;
		this.referencia = referencia;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.hectareasAseguradas = hectareasAseguradas;
		this.montoAsegurado = montoAsegurado;
		this.fechaRealSiembra = fechaRealSiembra;
		this.fechaAviso = fechaAviso;
		this.fechaSiniestro = fechaSiniestro;
		this.nombreCausa = nombreCausa;
		this.fechaEnvioInspeccion = fechaEnvioInspeccion;
		this.numeroPoliza = numeroPoliza;
		this.numeroCertificado = numeroCertificado;
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
		this.numeroReclamo = numeroReclamo;
		this.observaciones = observaciones;
		this.fechaAvisoCosecha=fechaAvisoCosecha;
		this.codigoAvisoCosecha=codigoAvisoCosecha;
	}
	public String getCodigoSiniestro() {
		return codigoSiniestro;
	}
	public void setCodigoSiniestro(String codigoSiniestro) {
		this.codigoSiniestro = codigoSiniestro;
	}
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	public String getCodigoInspector() {
		return codigoInspector;
	}
	public void setCodigoInspector(String codigoInspector) {
		this.codigoInspector = codigoInspector;
	}
	public String getCedulaAgricultor() {
		return cedulaAgricultor;
	}
	public void setCedulaAgricultor(String cedulaAgricultor) {
		this.cedulaAgricultor = cedulaAgricultor;
	}
	public String getNombreAgricultor() {
		return nombreAgricultor;
	}
	public void setNombreAgricultor(String nombreAgricultor) {
		this.nombreAgricultor = nombreAgricultor;
	}
	public String getCelularAgricultor() {
		return celularAgricultor;
	}
	public void setCelularAgricultor(String celularAgricultor) {
		this.celularAgricultor = celularAgricultor;
	}
	public String getNombreCanal() {
		return nombreCanal;
	}
	public void setNombreCanal(String nombreCanal) {
		this.nombreCanal = nombreCanal;
	}
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public String getNombreCanton() {
		return nombreCanton;
	}
	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}
	public String getNombreParroquia() {
		return nombreParroquia;
	}
	public void setNombreParroquia(String nombreParroquia) {
		this.nombreParroquia = nombreParroquia;
	}
	public String getNombreRecinto() {
		return nombreRecinto;
	}
	public void setNombreRecinto(String nombreRecinto) {
		this.nombreRecinto = nombreRecinto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public BigDecimal getHectareasAseguradas() {
		return hectareasAseguradas;
	}
	public void setHectareasAseguradas(BigDecimal hectareasAseguradas) {
		this.hectareasAseguradas = hectareasAseguradas;
	}
	public BigDecimal getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(BigDecimal montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public String getFechaRealSiembra() {
		return fechaRealSiembra;
	}
	public void setFechaRealSiembra(String fechaRealSiembra) {
		this.fechaRealSiembra = fechaRealSiembra;
	}
	public String getFechaAviso() {
		return fechaAviso;
	}
	public void setFechaAviso(String fechaAviso) {
		this.fechaAviso = fechaAviso;
	}
	public String getFechaSiniestro() {
		return fechaSiniestro;
	}
	public void setFechaSiniestro(String fechaSiniestro) {
		this.fechaSiniestro = fechaSiniestro;
	}
	public String getNombreCausa() {
		return nombreCausa;
	}
	public void setNombreCausa(String nombreCausa) {
		this.nombreCausa = nombreCausa;
	}
	public String getFechaEnvioInspeccion() {
		return fechaEnvioInspeccion;
	}
	public void setFechaEnvioInspeccion(String fechaEnvioInspeccion) {
		this.fechaEnvioInspeccion = fechaEnvioInspeccion;
	}
	public String getNumeroPoliza() {
		return numeroPoliza;
	}
	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	public String getNumeroCertificado() {
		return numeroCertificado;
	}
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}
	public String getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public void setFechaInicioVigencia(String fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public String getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public void setFechaFinVigencia(String fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	public String getFechaAvisoCosecha() {
		return fechaAvisoCosecha;
	}
	public void setFechaAvisoCosecha(String fechaAvisoCosecha) {
		this.fechaAvisoCosecha = fechaAvisoCosecha;
	}


	public String getCodigoAvisoCosecha() {
		return codigoAvisoCosecha;
	}


	public void setCodigoAvisoCosecha(String codigoAvisoCosecha) {
		this.codigoAvisoCosecha = codigoAvisoCosecha;
	}


	public static class Builder{
		private String codigoSiniestro;
		public Builder codigoSiniestro(final String codigoSiniestro) {
			this.codigoSiniestro=codigoSiniestro;
			return this;
		}
		
		private String codigoProveedor;
		public Builder codigoProveedor(final String codigoProveedor) {
			this.codigoProveedor=codigoProveedor;
			return this;
		}
		
		private String codigoInspector;
		public Builder codigoInspector(final String codigoInspector) {
			this.codigoInspector=codigoInspector;
			return this;
		}
		
		private String cedulaAgricultor;
		public Builder cedulaAgricultor(final String cedulaAgricultor) {
			this.cedulaAgricultor=cedulaAgricultor;
			return this;
		}
		
		private String nombreAgricultor;
		public Builder nombreAgricultor(final String nombreAgricultor) {
			this.nombreAgricultor=nombreAgricultor;
			return this;
		}
		
		private String celularAgricultor;
		public Builder celularAgricultor(final String celularAgricultor) {
			this.celularAgricultor=celularAgricultor;
			return this;
		}
		
		private String nombreCanal;
		public Builder nombreCanal(final String nombreCanal) {
			this.nombreCanal=nombreCanal;
			return this;
		}
		
		private String numeroTramite;
		public Builder numeroTramite(final String numeroTramite) {
			this.numeroTramite=numeroTramite;
			return this;
		}
		
		private String nombreCultivo;
		public Builder nombreCultivo(final String nombreCultivo) {
			this.nombreCultivo=nombreCultivo;
			return this;
		}
		
		private String nombreProvincia;
		public Builder nombreProvincia(final String nombreProvincia) {
			this.nombreProvincia=nombreProvincia;
			return this;
		}
		
		private String nombreCanton;
		public Builder nombreCanton(final String nombreCanton) {
			this.nombreCanton=nombreCanton;
			return this;
		}
		
		private String nombreParroquia;
		public Builder nombreParroquia(final String nombreParroquia) {
			this.nombreParroquia=nombreParroquia;
			return this;
		}
		
		private String nombreRecinto;
		public Builder nombreRecinto(final String nombreRecinto) {
			this.nombreRecinto=nombreRecinto;
			return this;
		}
		
		private String referencia;
		public Builder referencia(final String referencia) {
			this.referencia=referencia;
			return this;
		}
		
		private BigDecimal coordenadaX;
		public Builder coordenadaX(final BigDecimal coordenadaX) {
			this.coordenadaX=coordenadaX;
			return this;
		}
		
		private BigDecimal coordenadaY;
		public Builder coordenadaY(final BigDecimal coordenadaY) {
			this.coordenadaY=coordenadaY;
			return this;
		}
		
		private BigDecimal hectareasAseguradas;
		public Builder hectareasAseguradas(final BigDecimal hectareasAseguradas) {
			this.hectareasAseguradas=hectareasAseguradas;
			return this;
		}
		
		private BigDecimal montoAsegurado;
		public Builder montoAsegurado(final BigDecimal montoAsegurado) {
			this.montoAsegurado=montoAsegurado;
			return this;
		}
		
		private String fechaRealSiembra;
		public Builder fechaRealSiembra(final String fechaRealSiembra) {
			this.fechaRealSiembra=fechaRealSiembra;
			return this;
		}
		
		private String fechaAviso;
		public Builder fechaAviso(final String fechaAviso) {
			this.fechaAviso=fechaAviso;
			return this;
		}
		
		private String fechaAvisoCosecha;
		public Builder fechaAvisoCosecha(final String fechaAvisoCosecha) {
			this.fechaAvisoCosecha=fechaAvisoCosecha;
			return this;
		}
		
		private String fechaSiniestro;
		public Builder fechaSiniestro(final String fechaSiniestro) {
			this.fechaSiniestro=fechaSiniestro;
			return this;
		}
		
		private String nombreCausa;
		public Builder nombreCausa(final String nombreCausa) {
			this.nombreCausa=nombreCausa;
			return this;
		}
		
		private String fechaEnvioInspeccion;
		public Builder fechaEnvioInspeccion(final String fechaEnvioInspeccion) {
			this.fechaEnvioInspeccion=fechaEnvioInspeccion;
			return this;
		}
		
		private String numeroPoliza;
		public Builder numeroPoliza(final String numeroPoliza) {
			this.numeroPoliza=numeroPoliza;
			return this;
		}
		
		private String numeroCertificado;
		public Builder numeroCertificado(final String numeroCertificado) {
			this.numeroCertificado=numeroCertificado;
			return this;
		}
		
		private String fechaInicioVigencia;
		public Builder fechaInicioVigencia(final String fechaInicioVigencia) {
			this.fechaInicioVigencia=fechaInicioVigencia;
			return this;
		}
		
		private String fechaFinVigencia;
		public Builder fechaFinVigencia(final String fechaFinVigencia) {
			this.fechaFinVigencia=fechaFinVigencia;
			return this;
		}
		
		private String numeroReclamo;
		public Builder numeroReclamo(final String numeroReclamo) {
			this.numeroReclamo=numeroReclamo;
			return this;
		}
		
		private String observaciones;
		public Builder observaciones(final String observaciones) {
			this.observaciones=observaciones;
			return this;
		}
		
		private String codigoAvisoCosecha;
		public Builder codigoAvisoCosecha(final String codigoAvisoCosecha) {
			this.codigoAvisoCosecha=codigoAvisoCosecha;
			return this;
		}
		
		public InformeInspeccionSolicitudWrapper build() {
			return new InformeInspeccionSolicitudWrapper(codigoSiniestro, codigoProveedor, codigoInspector, 
					cedulaAgricultor, nombreAgricultor, celularAgricultor, nombreCanal, numeroTramite, nombreCultivo,
					nombreProvincia, nombreCanton, nombreParroquia, nombreRecinto, referencia, coordenadaX, 
					coordenadaY, hectareasAseguradas, montoAsegurado, fechaRealSiembra, fechaAviso, fechaSiniestro, 
					nombreCausa, fechaEnvioInspeccion, numeroPoliza, numeroCertificado, fechaInicioVigencia, 
					fechaFinVigencia, numeroReclamo, observaciones,fechaAvisoCosecha,codigoAvisoCosecha);
			
		}
		
	}
	
	
}
