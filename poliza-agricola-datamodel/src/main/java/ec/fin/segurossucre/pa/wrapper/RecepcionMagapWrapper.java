package ec.fin.segurossucre.pa.wrapper;

import java.io.Serializable;

public class RecepcionMagapWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6011897588176054752L;
	private String numeroSiniestroMagap;
	private String numeroTramiteSubsidio;
	private String personaReporta;
	private String nombreReceptorSiniestro;
	private String cedulaAgricultor;
	private String celularAgricultor;
	private String telefonoFijoAgricultor;
	private String emailAgricultor;
	private String provinciaCodigo;
	private String cantonCodigo;
	private String parroquiaCodigo;
	private String recinto;
	private String referenciaUbicacionSntro;
	private Long hasSiniestradas;
	private String cultivoCodigo;
	private String fechaSiembra;
	private String fechaAvisoSiniestro;
	private String fechaOcurrencia;
	private String fechaIngresoSistema;
	private String causaSiniestroCodigo;
	private String observacionAvisoSntro;
	private String archivoAvisoSntro;
	private String archivoAvisoCosecha;
	
	public RecepcionMagapWrapper() {}
	
	
	
	
	public RecepcionMagapWrapper(String numeroSiniestroMagap, String numeroTramiteSubsidio, String personaReporta,
			String nombreReceptorSiniestro, String cedulaAgricultor, String celularAgricultor,
			String telefonoFijoAgricultor, String emailAgricultor, String provinciaCodigo, String cantonCodigo,
			String parroquiaCodigo, String recinto, String referenciaUbicacionSntro, Long hasSiniestradas,
			String cultivoCodigo, String fechaSiembra, String fechaAvisoSiniestro, String fechaOcurrencia,
			String fechaIngresoSistema, String causaSiniestroCodigo, String observacionAvisoSntro,
			String archivoAvisoSntro, String archivoAvisoCosecha) {
		super();
		this.numeroSiniestroMagap = numeroSiniestroMagap;
		this.numeroTramiteSubsidio = numeroTramiteSubsidio;
		this.personaReporta = personaReporta;
		this.nombreReceptorSiniestro = nombreReceptorSiniestro;
		this.cedulaAgricultor = cedulaAgricultor;
		this.celularAgricultor = celularAgricultor;
		this.telefonoFijoAgricultor = telefonoFijoAgricultor;
		this.emailAgricultor = emailAgricultor;
		this.provinciaCodigo = provinciaCodigo;
		this.cantonCodigo = cantonCodigo;
		this.parroquiaCodigo = parroquiaCodigo;
		this.recinto = recinto;
		this.referenciaUbicacionSntro = referenciaUbicacionSntro;
		this.hasSiniestradas = hasSiniestradas;
		this.cultivoCodigo = cultivoCodigo;
		this.fechaSiembra = fechaSiembra;
		this.fechaAvisoSiniestro = fechaAvisoSiniestro;
		this.fechaOcurrencia = fechaOcurrencia;
		this.fechaIngresoSistema = fechaIngresoSistema;
		this.causaSiniestroCodigo = causaSiniestroCodigo;
		this.observacionAvisoSntro = observacionAvisoSntro;
		this.archivoAvisoSntro = archivoAvisoSntro;
		this.archivoAvisoCosecha = archivoAvisoCosecha;
	}
	public String getNumeroSiniestroMagap() {
		return numeroSiniestroMagap;
	}
	public void setNumeroSiniestroMagap(String numeroSiniestroMagap) {
		this.numeroSiniestroMagap = numeroSiniestroMagap;
	}
	public String getNumeroTramiteSubsidio() {
		return numeroTramiteSubsidio;
	}
	public void setNumeroTramiteSubsidio(String numeroTramiteSubsidio) {
		this.numeroTramiteSubsidio = numeroTramiteSubsidio;
	}
	public String getPersonaReporta() {
		return personaReporta;
	}
	public void setPersonaReporta(String personaReporta) {
		this.personaReporta = personaReporta;
	}
	public String getNombreReceptorSiniestro() {
		return nombreReceptorSiniestro;
	}
	public void setNombreReceptorSiniestro(String nombreReceptorSiniestro) {
		this.nombreReceptorSiniestro = nombreReceptorSiniestro;
	}
	public String getCedulaAgricultor() {
		return cedulaAgricultor;
	}
	public void setCedulaAgricultor(String cedulaAgricultor) {
		this.cedulaAgricultor = cedulaAgricultor;
	}
	public String getCelularAgricultor() {
		return celularAgricultor;
	}
	public void setCelularAgricultor(String celularAgricultor) {
		this.celularAgricultor = celularAgricultor;
	}
	public String getTelefonoFijoAgricultor() {
		return telefonoFijoAgricultor;
	}
	public void setTelefonoFijoAgricultor(String telefonoFijoAgricultor) {
		this.telefonoFijoAgricultor = telefonoFijoAgricultor;
	}
	public String getEmailAgricultor() {
		return emailAgricultor;
	}
	public void setEmailAgricultor(String emailAgricultor) {
		this.emailAgricultor = emailAgricultor;
	}
	public String getProvinciaCodigo() {
		return provinciaCodigo;
	}
	public void setProvinciaCodigo(String provinciaCodigo) {
		this.provinciaCodigo = provinciaCodigo;
	}
	public String getCantonCodigo() {
		return cantonCodigo;
	}
	public void setCantonCodigo(String cantonCodigo) {
		this.cantonCodigo = cantonCodigo;
	}
	public String getParroquiaCodigo() {
		return parroquiaCodigo;
	}
	public void setParroquiaCodigo(String parroquiaCodigo) {
		this.parroquiaCodigo = parroquiaCodigo;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getReferenciaUbicacionSntro() {
		return referenciaUbicacionSntro;
	}
	public void setReferenciaUbicacionSntro(String referenciaUbicacionSntro) {
		this.referenciaUbicacionSntro = referenciaUbicacionSntro;
	}
	public Long getHasSiniestradas() {
		return hasSiniestradas;
	}
	public void setHasSiniestradas(Long hasSiniestradas) {
		this.hasSiniestradas = hasSiniestradas;
	}
	public String getCultivoCodigo() {
		return cultivoCodigo;
	}
	public void setCultivoCodigo(String cultivoCodigo) {
		this.cultivoCodigo = cultivoCodigo;
	}
	public String getFechaSiembra() {
		return fechaSiembra;
	}
	public void setFechaSiembra(String fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}
	public String getFechaAvisoSiniestro() {
		return fechaAvisoSiniestro;
	}
	public void setFechaAvisoSiniestro(String fechaAvisoSiniestro) {
		this.fechaAvisoSiniestro = fechaAvisoSiniestro;
	}
	public String getFechaOcurrencia() {
		return fechaOcurrencia;
	}
	public void setFechaOcurrencia(String fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}
	public String getFechaIngresoSistema() {
		return fechaIngresoSistema;
	}
	public void setFechaIngresoSistema(String fechaIngresoSistema) {
		this.fechaIngresoSistema = fechaIngresoSistema;
	}
	public String getCausaSiniestroCodigo() {
		return causaSiniestroCodigo;
	}
	public void setCausaSiniestroCodigo(String causaSiniestroCodigo) {
		this.causaSiniestroCodigo = causaSiniestroCodigo;
	}
	public String getObservacionAvisoSntro() {
		return observacionAvisoSntro;
	}
	public void setObservacionAvisoSntro(String observacionAvisoSntro) {
		this.observacionAvisoSntro = observacionAvisoSntro;
	}
	public String getArchivoAvisoSntro() {
		return archivoAvisoSntro;
	}
	public void setArchivoAvisoSntro(String archivoAvisoSntro) {
		this.archivoAvisoSntro = archivoAvisoSntro;
	}
	public String getArchivoAvisoCosecha() {
		return archivoAvisoCosecha;
	}
	public void setArchivoAvisoCosecha(String archivoAvisoCosecha) {
		this.archivoAvisoCosecha = archivoAvisoCosecha;
	}
	
	public static class Builder {
		private String numeroSiniestroMagap;
		private String numeroTramiteSubsidio;
		private String personaReporta;
		private String nombreReceptorSiniestro;
		private String cedulaAgricultor;
		private String celularAgricultor;
		private String telefonoFijoAgricultor;
		private String emailAgricultor;
		private String provinciaCodigo;
		private String cantonCodigo;
		private String parroquiaCodigo;
		private String recinto;
		private String referenciaUbicacionSntro;
		private Long hasSiniestradas;
		private String cultivoCodigo;
		private String fechaSiembra;
		private String fechaAvisoSiniestro;
		private String fechaOcurrencia;
		private String fechaIngresoSistema;
		private String causaSiniestroCodigo;
		private String observacionAvisoSntro;
		private String archivoAvisoSntro;
		private String archivoAvisoCosecha;
		
		public Builder numeroSiniestroMagap( final String numeroSiniestroMagap ) {
			this.numeroSiniestroMagap=numeroSiniestroMagap;
			return this;
		}
		
		public Builder numeroTramiteSubsidio( final String numeroTramiteSubsidio ) {
			this.numeroTramiteSubsidio=numeroTramiteSubsidio;
			return this;
		}
		public Builder personaReporta( final String personaReporta ) {
			this.personaReporta=personaReporta;
			return this;
		}
		public Builder nombreReceptorSiniestro( final String nombreReceptorSiniestro ) {
			this.nombreReceptorSiniestro=nombreReceptorSiniestro;
			return this;
		}
		public Builder cedulaAgricultor( final String cedulaAgricultor ) {
			this.cedulaAgricultor=cedulaAgricultor;
			return this;
		}
		public Builder celularAgricultor( final String celularAgricultor ) {
			this.celularAgricultor=celularAgricultor;
			return this;
		}
		public Builder telefonoFijoAgricultor( final String telefonoFijoAgricultor ) {
			this.telefonoFijoAgricultor=telefonoFijoAgricultor;
			return this;
		}
		public Builder emailAgricultor( final String emailAgricultor ) {
			this.emailAgricultor=emailAgricultor;
			return this;
		}
		public Builder provinciaCodigo( final String provinciaCodigo ) {
			this.provinciaCodigo=provinciaCodigo;
			return this;
		}
		public Builder cantonCodigo( final String cantonCodigo ) {
			this.cantonCodigo=cantonCodigo;
			return this;
		}
		public Builder parroquiaCodigo( final String parroquiaCodigo ) {
			this.parroquiaCodigo=parroquiaCodigo;
			return this;
		}
		public Builder recinto( final String recinto ) {
			this.recinto=recinto;
			return this;
		}
		public Builder referenciaUbicacionSntro( final String referenciaUbicacionSntro ) {
			this.referenciaUbicacionSntro=referenciaUbicacionSntro;
			return this;
		}
		public Builder hasSiniestradas( final Long hasSiniestradas ) {
			this.hasSiniestradas=hasSiniestradas;
			return this;
		}
		public Builder cultivoCodigo( final String cultivoCodigo ) {
			this.cultivoCodigo=cultivoCodigo;
			return this;
		}
		public Builder fechaSiembra( final String fechaSiembra ) {
			this.fechaSiembra=fechaSiembra;
			return this;
		}
		public Builder fechaAvisoSiniestro( final String fechaAvisoSiniestro ) {
			this.fechaAvisoSiniestro=fechaAvisoSiniestro;
			return this;
		}
		public Builder fechaOcurrencia( final String fechaOcurrencia ) {
			this.fechaOcurrencia=fechaOcurrencia;
			return this;
		}
		public Builder fechaIngresoSistema( final String fechaIngresoSistema ) {
			this.fechaIngresoSistema=fechaIngresoSistema;
			return this;
		}
		public Builder causaSiniestroCodigo( final String causaSiniestroCodigo ) {
			this.causaSiniestroCodigo=causaSiniestroCodigo;
			return this;
		}
		public Builder observacionAvisoSntro( final String observacionAvisoSntro ) {
			this.observacionAvisoSntro=observacionAvisoSntro;
			return this;
		}
		public Builder archivoAvisoSntro( final String archivoAvisoSntro ) {
			this.archivoAvisoSntro=archivoAvisoSntro;
			return this;
		}
		public Builder archivoAvisoCosecha( final String archivoAvisoCosecha ) {
			this.archivoAvisoCosecha=archivoAvisoCosecha;
			return this;
		}
		
		public RecepcionMagapWrapper  create() {
			return new RecepcionMagapWrapper(numeroSiniestroMagap, numeroTramiteSubsidio, personaReporta, 
					nombreReceptorSiniestro, cedulaAgricultor, celularAgricultor, telefonoFijoAgricultor, 
					emailAgricultor, provinciaCodigo, cantonCodigo, parroquiaCodigo, recinto, referenciaUbicacionSntro, 
					hasSiniestradas, cultivoCodigo, fechaSiembra, fechaAvisoSiniestro, fechaOcurrencia, 
					fechaIngresoSistema, causaSiniestroCodigo, observacionAvisoSntro, archivoAvisoSntro, 
					archivoAvisoCosecha);
		} 
		
	}
	
}
