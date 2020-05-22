package ec.com.def.pa.wrapper;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SolicitudPolizaWrapper implements Serializable {
	private static final long serialVersionUID = -2603633238095180178L;

	private String nPoliza;
	private String nombres;
	private String apellidos;
	private String nacionalidad;
	private String genero;
	private String estadoCivil;
	private String fechaNacimiento;
	private String provinciaN;
	private String cantonN;
	private String parroquiaN;
	private String provinciaD;
	private String cantonD;
	private String parroquiaD;
	private String called;
	private String referenciaD;
	private String telefonoConvencional;
	private String telefonoCelular;
	private String correoElectronico;
	private String ingresoMensual;
	private String actividadEconomica;
	private String agriculturaActividad;
	private String personaPoliticamente;
	private String provinciaP;
	private String cantonP;
	private String parroquiaP;
	private String recintoP;
	private String montoAsegurado;
	private String referenciaP;
	private String condicionesPredio;
	private String otraCondicion;
	private String altitudPredio;
	private String coordenadaX;
	private String coordenadaY;
	private String cultivo;
	private String variedad;
	private String superficieAsegurar;
	private String costosHectarea;
	private String fechaTentativa;
	private String tipoSemilla;
	private String disponeRiesgo;
	private String tipoRiego;
	private String canalContratacion;
	private String endosoBeneficiario;
	private String valorEndoso;
	private String aceptarInformacion;
	private String asistenciaRiego;
	private String riego;
	private String tieneEndoso;
	private String nombreEjecutivo;
	private String contactoCanal;
	private String fechaCreacionSolicitud;
	private BufferedImage  ubicacion;
	private String cedula;
	public String getnTramite() {
		return nTramite;
	}
	public void setnTramite(String nTramite) {
		this.nTramite = nTramite;
	}
	private String nTramite;
	
	public String getFechaCreacionSolicitud() {
		return fechaCreacionSolicitud;
	}
	public void setFechaCreacionSolicitud(String fechaCreacionSolicitud) {
		this.fechaCreacionSolicitud = fechaCreacionSolicitud;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public BufferedImage  getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(BufferedImage  ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getNombreEjecutivo() {
		return nombreEjecutivo;
	}
	public void setNombreEjecutivo(String nombreEjecutivo) {
		this.nombreEjecutivo = nombreEjecutivo;
	}
	public String getContactoCanal() {
		return contactoCanal;
	}
	public void setContactoCanal(String contactoCanal) {
		this.contactoCanal = contactoCanal;
	}
	public String getTieneEndoso() {
		return tieneEndoso;
	}
	public void setTieneEndoso(String tieneEndoso) {
		this.tieneEndoso = tieneEndoso;
	}
	public String getAsistenciaRiego() {
		return asistenciaRiego;
	}
	public void setAsistenciaRiego(String asistenciaRiego) {
		this.asistenciaRiego = asistenciaRiego;
	}
	public String getRiego() {
		return riego;
	}
	public void setRiego(String riego) {
		this.riego = riego;
	}
	public String getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(String montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getnPoliza() {
		return nPoliza;
	}
	public void setnPoliza(String nPoliza) {
		this.nPoliza = nPoliza;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getProvinciaN() {
		return provinciaN;
	}
	public void setProvinciaN(String provinciaN) {
		this.provinciaN = provinciaN;
	}
	public String getCantonN() {
		return cantonN;
	}
	public void setCantonN(String cantonN) {
		this.cantonN = cantonN;
	}
	public String getParroquiaN() {
		return parroquiaN;
	}
	public void setParroquiaN(String parroquiaN) {
		this.parroquiaN = parroquiaN;
	}
	public String getProvinciaD() {
		return provinciaD;
	}
	public void setProvinciaD(String provinciaD) {
		this.provinciaD = provinciaD;
	}
	public String getCantonD() {
		return cantonD;
	}
	public void setCantonD(String cantonD) {
		this.cantonD = cantonD;
	}
	public String getParroquiaD() {
		return parroquiaD;
	}
	public void setParroquiaD(String parroquiaD) {
		this.parroquiaD = parroquiaD;
	}
	
	public String getCalled() {
		return called;
	}
	public void setCalled(String called) {
		this.called = called;
	}
	public String getReferenciaD() {
		return referenciaD;
	}
	public void setReferenciaD(String referenciaD) {
		this.referenciaD = referenciaD;
	}
	public String getTelefonoConvencional() {
		return telefonoConvencional;
	}
	public void setTelefonoConvencional(String telefonoConvencional) {
		this.telefonoConvencional = telefonoConvencional;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getIngresoMensual() {
		return ingresoMensual;
	}
	public void setIngresoMensual(String ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}
	public String getActividadEconomica() {
		return actividadEconomica;
	}
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
	public String getAgriculturaActividad() {
		return agriculturaActividad;
	}
	public void setAgriculturaActividad(String agriculturaActividad) {
		this.agriculturaActividad = agriculturaActividad;
	}
	public String getPersonaPoliticamente() {
		return personaPoliticamente;
	}
	public void setPersonaPoliticamente(String personaPoliticamente) {
		this.personaPoliticamente = personaPoliticamente;
	}
	public String getProvinciaP() {
		return provinciaP;
	}
	public void setProvinciaP(String provinciaP) {
		this.provinciaP = provinciaP;
	}
	public String getCantonP() {
		return cantonP;
	}
	public void setCantonP(String cantonP) {
		this.cantonP = cantonP;
	}
	public String getParroquiaP() {
		return parroquiaP;
	}
	public void setParroquiaP(String parroquiaP) {
		this.parroquiaP = parroquiaP;
	}
	public String getRecintoP() {
		return recintoP;
	}
	public void setRecintoP(String recintoP) {
		this.recintoP = recintoP;
	}
	public String getReferenciaP() {
		return referenciaP;
	}
	public void setReferenciaP(String referenciaP) {
		this.referenciaP = referenciaP;
	}
	public String getCondicionesPredio() {
		return condicionesPredio;
	}
	public void setCondicionesPredio(String condicionesPredio) {
		this.condicionesPredio = condicionesPredio;
	}
	public String getOtraCondicion() {
		return otraCondicion;
	}
	public void setOtraCondicion(String otraCondicion) {
		this.otraCondicion = otraCondicion;
	}
	public String getAltitudPredio() {
		return altitudPredio;
	}
	public void setAltitudPredio(String altitudPredio) {
		this.altitudPredio = altitudPredio;
	}
	public String getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public String getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	public String getVariedad() {
		return variedad;
	}
	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}
	public String getSuperficieAsegurar() {
		return superficieAsegurar;
	}
	public void setSuperficieAsegurar(String superficieAsegurar) {
		this.superficieAsegurar = superficieAsegurar;
	}
	public String getCostosHectarea() {
		return costosHectarea;
	}
	public void setCostosHectarea(String costosHectarea) {
		this.costosHectarea = costosHectarea;
	}
	public String getFechaTentativa() {
		return fechaTentativa;
	}
	public void setFechaTentativa(String fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}
	public String getTipoSemilla() {
		return tipoSemilla;
	}
	public void setTipoSemilla(String tipoSemilla) {
		this.tipoSemilla = tipoSemilla;
	}
	public String getDisponeRiesgo() {
		return disponeRiesgo;
	}
	public void setDisponeRiesgo(String disponeRiesgo) {
		this.disponeRiesgo = disponeRiesgo;
	}
	public String getTipoRiego() {
		return tipoRiego;
	}
	public void setTipoRiego(String tipoRiego) {
		this.tipoRiego = tipoRiego;
	}
	public String getCanalContratacion() {
		return canalContratacion;
	}
	public void setCanalContratacion(String canalContratacion) {
		this.canalContratacion = canalContratacion;
	}
	public String getEndosoBeneficiario() {
		return endosoBeneficiario;
	}
	public void setEndosoBeneficiario(String endosoBeneficiario) {
		this.endosoBeneficiario = endosoBeneficiario;
	}
	public String getValorEndoso() {
		return valorEndoso;
	}
	public void setValorEndoso(String valorEndoso) {
		this.valorEndoso = valorEndoso;
	}
	public String getAceptarInformacion() {
		return aceptarInformacion;
	}
	public void setAceptarInformacion(String aceptarInformacion) {
		this.aceptarInformacion = aceptarInformacion;
	}
	
	
}
