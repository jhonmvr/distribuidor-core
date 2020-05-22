package ec.fin.segurossucre.pa.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;


public class ResultadoInspeccionWrapper implements Serializable{
	private String codigoSiniestro;
	private String codigoProveedor;
	private String codigoInspector;
	private String tipoInspeccion;
	private String nuevo;
	private String fechaSincronizacion;
	private String numeroReclamo;
	private String nombreProvincia;
	private String nombreCanton;
	private String nombreParroquia;
	private String nombreRecinto;
	private String descripcionReferencia;
	private String referencia;
	private String coordenadaX;
	private String coordenadaY;
	private String nombreCultivo;
	private BigDecimal porcentajeGerminacion;
	private BigDecimal poblacionHa;
	private BigDecimal porcentajeMaleza;
	private BigDecimal edadCultivo;
	private String nombreCausa;
	private String fertilizacion;
	private String controlPlagas;
	private String controlEnfermedades;
	private String tipoPerdida;
	private String fechaSiembra;
	private String fechaSiniestro;
	private String fechaAviso;
	private String fechaInspeccion;
	private BigDecimal superficieSembrada;
	private BigDecimal superficieAfectada;
	private BigDecimal numeroPlantasMuertas;
	private BigDecimal costoPreparacionTerreno;
	private BigDecimal costoSemilla;
	private BigDecimal costoInsumosQuimicos;
	private BigDecimal costoManoObra;
	private BigDecimal totalCostosHectarea;
	private BigDecimal inversionTotal;
	private String observacionesInforme;
	private String tecnicoInspeccion;
	
	//cosecha
	private String codigoAvisoCosecha;
	private String fechaAvisoCosecha;
	private String fechaInicioCosecha;
	private String fechaEvaluacionCosecha;
	private BigDecimal rendimientoCosecha;
	private BigDecimal superficieCosecha;
	private BigDecimal rendimientoTotal;
	private String observacionesCosecha;
	private String tecnicoInspeccionCosecha;
	//ajuste
	private BigDecimal porcentajeDeducible;
	private BigDecimal inversion;
	private BigDecimal produccion;
	private BigDecimal precioAjuste;
	private BigDecimal salvamento;
	private BigDecimal valorPerdida;
	private BigDecimal valorDeducible;
	private BigDecimal valorAPagar;
	private String fechaAjuste;
	private String observacionesAjuste;
	private String estado;
	//nuevo
	private String codigoInspeccionProveedor;
	private String cedulaAgricultor;
	private String nombreAgricultor;
	private String celularAgricultor;
	private String nombreCanal;
	private String numeroTramite;
	private String hectareasAseguradas;
	private String montoAsegurado;
	private String fechaRealSiembra;
	private String fechaEnvioInspeccion;
	private String numeroPoliza;
	private String numeroCertificado;
	private String fechaInicioVigencia;
	private String fechaFinVigencia;
	private String observaciones;
	private String referenciaSitio;
	
	
	
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
	public String getTipoInspeccion() {
		return tipoInspeccion;
	}
	public void setTipoInspeccion(String tipoInspeccion) {
		this.tipoInspeccion = tipoInspeccion;
	}
	public String getNuevo() {
		return nuevo;
	}
	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}
	public String getFechaSincronizacion() {
		return fechaSincronizacion;
	}
	public void setFechaSincronizacion(String fechaSincronizacion) {
		this.fechaSincronizacion = fechaSincronizacion;
	}
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
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
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	public BigDecimal getPorcentajeGerminacion() {
		return porcentajeGerminacion;
	}
	public void setPorcentajeGerminacion(BigDecimal porcentajeGerminacion) {
		this.porcentajeGerminacion = porcentajeGerminacion;
	}
	public BigDecimal getPoblacionHa() {
		return poblacionHa;
	}
	public void setPoblacionHa(BigDecimal poblacionHa) {
		this.poblacionHa = poblacionHa;
	}
	public BigDecimal getPorcentajeMaleza() {
		return porcentajeMaleza;
	}
	public void setPorcentajeMaleza(BigDecimal porcentajeMaleza) {
		this.porcentajeMaleza = porcentajeMaleza;
	}
	public BigDecimal getEdadCultivo() {
		return edadCultivo;
	}
	public void setEdadCultivo(BigDecimal edadCultivo) {
		this.edadCultivo = edadCultivo;
	}
	public String getNombreCausa() {
		return nombreCausa;
	}
	public void setNombreCausa(String nombreCausa) {
		this.nombreCausa = nombreCausa;
	}
	public String getFertilizacion() {
		return fertilizacion;
	}
	public void setFertilizacion(String fertilizacion) {
		this.fertilizacion = fertilizacion;
	}
	public String getControlPlagas() {
		return controlPlagas;
	}
	public void setControlPlagas(String controlPlagas) {
		this.controlPlagas = controlPlagas;
	}
	public String getControlEnfermedades() {
		return controlEnfermedades;
	}
	public void setControlEnfermedades(String controlEnfermedades) {
		this.controlEnfermedades = controlEnfermedades;
	}
	public String getTipoPerdida() {
		return tipoPerdida;
	}
	public void setTipoPerdida(String tipoPerdida) {
		this.tipoPerdida = tipoPerdida;
	}
	public String getFechaSiembra() {
		return fechaSiembra;
	}
	public void setFechaSiembra(String fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}
	public String getFechaSiniestro() {
		return fechaSiniestro;
	}
	public void setFechaSiniestro(String fechaSiniestro) {
		this.fechaSiniestro = fechaSiniestro;
	}
	public String getFechaAviso() {
		return fechaAviso;
	}
	public void setFechaAviso(String fechaAviso) {
		this.fechaAviso = fechaAviso;
	}
	public String getFechaInspeccion() {
		return fechaInspeccion;
	}
	public void setFechaInspeccion(String fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}
	public BigDecimal getSuperficieSembrada() {
		return superficieSembrada;
	}
	public void setSuperficieSembrada(BigDecimal superficieSembrada) {
		this.superficieSembrada = superficieSembrada;
	}
	public BigDecimal getSuperficieAfectada() {
		return superficieAfectada;
	}
	public void setSuperficieAfectada(BigDecimal superficieAfectada) {
		this.superficieAfectada = superficieAfectada;
	}
	public BigDecimal getNumeroPlantasMuertas() {
		return numeroPlantasMuertas;
	}
	public void setNumeroPlantasMuertas(BigDecimal numeroPlantasMuertas) {
		this.numeroPlantasMuertas = numeroPlantasMuertas;
	}
	public BigDecimal getCostoPreparacionTerreno() {
		return costoPreparacionTerreno;
	}
	public void setCostoPreparacionTerreno(BigDecimal costoPreparacionTerreno) {
		this.costoPreparacionTerreno = costoPreparacionTerreno;
	}
	public BigDecimal getCostoSemilla() {
		return costoSemilla;
	}
	public void setCostoSemilla(BigDecimal costoSemilla) {
		this.costoSemilla = costoSemilla;
	}
	public BigDecimal getCostoInsumosQuimicos() {
		return costoInsumosQuimicos;
	}
	public void setCostoInsumosQuimicos(BigDecimal costoInsumosQuimicos) {
		this.costoInsumosQuimicos = costoInsumosQuimicos;
	}
	public BigDecimal getCostoManoObra() {
		return costoManoObra;
	}
	public void setCostoManoObra(BigDecimal costoManoObra) {
		this.costoManoObra = costoManoObra;
	}
	public BigDecimal getTotalCostosHectarea() {
		return totalCostosHectarea;
	}
	public void setTotalCostosHectarea(BigDecimal totalCostosHectarea) {
		this.totalCostosHectarea = totalCostosHectarea;
	}
	public BigDecimal getInversionTotal() {
		return inversionTotal;
	}
	public void setInversionTotal(BigDecimal inversionTotal) {
		this.inversionTotal = inversionTotal;
	}
	public String getObservacionesInforme() {
		return observacionesInforme;
	}
	public void setObservacionesInforme(String observacionesInforme) {
		this.observacionesInforme = observacionesInforme;
	}
	public String getTecnicoInspeccion() {
		return tecnicoInspeccion;
	}
	public void setTecnicoInspeccion(String tecnicoInspeccion) {
		this.tecnicoInspeccion = tecnicoInspeccion;
	}
	public String getFechaAvisoCosecha() {
		return fechaAvisoCosecha;
	}
	public void setFechaAvisoCosecha(String fechaAvisoCosecha) {
		this.fechaAvisoCosecha = fechaAvisoCosecha;
	}
	public String getFechaInicioCosecha() {
		return fechaInicioCosecha;
	}
	public void setFechaInicioCosecha(String fechaInicioCosecha) {
		this.fechaInicioCosecha = fechaInicioCosecha;
	}
	public String getFechaEvaluacionCosecha() {
		return fechaEvaluacionCosecha;
	}
	public void setFechaEvaluacionCosecha(String fechaEvaluacionCosecha) {
		this.fechaEvaluacionCosecha = fechaEvaluacionCosecha;
	}
	public BigDecimal getRendimientoCosecha() {
		return rendimientoCosecha;
	}
	public void setRendimientoCosecha(BigDecimal rendimientoCosecha) {
		this.rendimientoCosecha = rendimientoCosecha;
	}
	public BigDecimal getSuperficieCosecha() {
		return superficieCosecha;
	}
	public void setSuperficieCosecha(BigDecimal superficieCosecha) {
		this.superficieCosecha = superficieCosecha;
	}
	public BigDecimal getRendimientoTotal() {
		return rendimientoTotal;
	}
	public void setRendimientoTotal(BigDecimal rendimientoTotal) {
		this.rendimientoTotal = rendimientoTotal;
	}
	public String getObservacionesCosecha() {
		return observacionesCosecha;
	}
	public void setObservacionesCosecha(String observacionesCosecha) {
		this.observacionesCosecha = observacionesCosecha;
	}
	public String getTecnicoInspeccionCosecha() {
		return tecnicoInspeccionCosecha;
	}
	public void setTecnicoInspeccionCosecha(String tecnicoInspeccionCosecha) {
		this.tecnicoInspeccionCosecha = tecnicoInspeccionCosecha;
	}
	public BigDecimal getPorcentajeDeducible() {
		return porcentajeDeducible;
	}
	public void setPorcentajeDeducible(BigDecimal porcentajeDeducible) {
		this.porcentajeDeducible = porcentajeDeducible;
	}
	public BigDecimal getInversion() {
		return inversion;
	}
	public void setInversion(BigDecimal inversion) {
		this.inversion = inversion;
	}
	public BigDecimal getProduccion() {
		return produccion;
	}
	public void setProduccion(BigDecimal produccion) {
		this.produccion = produccion;
	}
	public BigDecimal getPrecioAjuste() {
		return precioAjuste;
	}
	public void setPrecioAjuste(BigDecimal precioAjuste) {
		this.precioAjuste = precioAjuste;
	}
	public BigDecimal getSalvamento() {
		return salvamento;
	}
	public void setSalvamento(BigDecimal salvamento) {
		this.salvamento = salvamento;
	}
	public BigDecimal getValorPerdida() {
		return valorPerdida;
	}
	public void setValorPerdida(BigDecimal valorPerdida) {
		this.valorPerdida = valorPerdida;
	}
	public BigDecimal getValorDeducible() {
		return valorDeducible;
	}
	public void setValorDeducible(BigDecimal valorDeducible) {
		this.valorDeducible = valorDeducible;
	}
	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public String getFechaAjuste() {
		return fechaAjuste;
	}
	public void setFechaAjuste(String fechaAjuste) {
		this.fechaAjuste = fechaAjuste;
	}
	public String getObservacionesAjuste() {
		return observacionesAjuste;
	}
	public void setObservacionesAjuste(String observacionesAjuste) {
		this.observacionesAjuste = observacionesAjuste;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigoInspeccionProveedor() {
		return codigoInspeccionProveedor;
	}
	public void setCodigoInspeccionProveedor(String codigoInspeccionProveedor) {
		this.codigoInspeccionProveedor = codigoInspeccionProveedor;
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
	public String getHectareasAseguradas() {
		return hectareasAseguradas;
	}
	public void setHectareasAseguradas(String hectareasAseguradas) {
		this.hectareasAseguradas = hectareasAseguradas;
	}
	public String getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(String montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public String getFechaRealSiembra() {
		return fechaRealSiembra;
	}
	public void setFechaRealSiembra(String fechaRealSiembra) {
		this.fechaRealSiembra = fechaRealSiembra;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getCodigoAvisoCosecha() {
		return codigoAvisoCosecha;
	}
	public void setCodigoAvisoCosecha(String codigoAvisoCosecha) {
		this.codigoAvisoCosecha = codigoAvisoCosecha;
	}
	public String getDescripcionReferencia() {
		return descripcionReferencia;
	}
	public void setDescripcionReferencia(String descripcionReferencia) {
		this.descripcionReferencia = descripcionReferencia;
	}
	public String getReferenciaSitio() {
		return referenciaSitio;
	}
	public void setReferenciaSitio(String referenciaSitio) {
		this.referenciaSitio = referenciaSitio;
	}

	
	

}
