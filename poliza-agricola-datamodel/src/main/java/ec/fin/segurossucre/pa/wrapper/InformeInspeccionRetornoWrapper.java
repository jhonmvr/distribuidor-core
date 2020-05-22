package ec.fin.segurossucre.pa.wrapper;

import java.math.BigDecimal;

public class InformeInspeccionRetornoWrapper extends InformeInspeccionSolicitudWrapper {
	
	private String codigoInspeccionProveedor;
	private String fertilizacion;
	private String controlPlagas;
	private String controlEnfermedades;
	private String tipoPerdida;
	private String tipoInspeccion;
	private String nuevo;
	private String fechaSincronizacion;
	private String fechaSiembra;
	private String fechaInspeccion;
	private String observacionesInforme;
	private String tecnicoInspeccion;
	private BigDecimal porcentajeGerminacion;
	private BigDecimal poblacionHa;
	private BigDecimal porcentajeMaleza;
	private BigDecimal edadCultivo;
	private BigDecimal superficieSembrada;
	private BigDecimal superficieAfectada;
	private BigDecimal numeroPlantasMuertas;
	private BigDecimal costoPreparacionTerreno;
	private BigDecimal costoSemilla;
	private BigDecimal costoInsumosQuimicos;
	private BigDecimal costoManoObra;
	private BigDecimal totalCostosHectarea;
	private BigDecimal inversionTotal;
	
	
	
	public InformeInspeccionRetornoWrapper(String codigoSiniestro, String codigoProveedor, String codigoInspector,
			String cedulaAgricultor, String nombreAgricultor, String celularAgricultor, String nombreCanal,
			String numeroTramite, String nombreCultivo, String nombreProvincia, String nombreCanton,
			String nombreParroquia, String nombreRecinto, String referencia, BigDecimal coordenadaX,
			BigDecimal coordenadaY, BigDecimal hectareasAseguradas, BigDecimal montoAsegurado, String fechaRealSiembra,
			String fechaAviso, String fechaSiniestro, String nombreCausa, String fechaEnvioInspeccion,
			String numeroPoliza, String numeroCertificado, String fechaInicioVigencia, String fechaFinVigencia,
			String numeroReclamo, String observaciones, String codigoInspeccionProveedor, String fertilizacion,
			String controlPlagas, String controlEnfermedades, String tipoPerdida, String fechaSiembra,
			String fechaInspeccion, String observacionesInforme, String tecnicoInspeccion,
			BigDecimal porcentajeGerminacion, BigDecimal poblacionHa, BigDecimal porcentajeMaleza,
			BigDecimal edadCultivo, BigDecimal superficieSembrada, BigDecimal superficieAfectada,
			BigDecimal numeroPlantasMuertas, BigDecimal costoPreparacionTerreno, BigDecimal costoSemilla,
			BigDecimal costoInsumosQuimicos, BigDecimal costoManoObra, BigDecimal totalCostosHectarea,
			BigDecimal inversionTotal, String fechaAvisoCosecha, String tipoInspeccion,String nuevo,
			String fechaSincronizacion,String codigoAvisoCosecha) {
		super(codigoSiniestro, codigoProveedor, codigoInspector, cedulaAgricultor, nombreAgricultor, celularAgricultor,
				nombreCanal, numeroTramite, nombreCultivo, nombreProvincia, nombreCanton, nombreParroquia,
				nombreRecinto, referencia, coordenadaX, coordenadaY, hectareasAseguradas, montoAsegurado,
				fechaRealSiembra, fechaAviso, fechaSiniestro, nombreCausa, fechaEnvioInspeccion, numeroPoliza,
				numeroCertificado, fechaInicioVigencia, fechaFinVigencia, numeroReclamo, observaciones,fechaAvisoCosecha,
				codigoAvisoCosecha);
		this.codigoInspeccionProveedor = codigoInspeccionProveedor;
		this.fertilizacion = fertilizacion;
		this.controlPlagas = controlPlagas;
		this.controlEnfermedades = controlEnfermedades;
		this.tipoPerdida = tipoPerdida;
		this.fechaSiembra = fechaSiembra;
		this.fechaInspeccion = fechaInspeccion;
		this.observacionesInforme = observacionesInforme;
		this.tecnicoInspeccion = tecnicoInspeccion;
		this.porcentajeGerminacion = porcentajeGerminacion;
		this.poblacionHa = poblacionHa;
		this.porcentajeMaleza = porcentajeMaleza;
		this.edadCultivo = edadCultivo;
		this.superficieSembrada = superficieSembrada;
		this.superficieAfectada = superficieAfectada;
		this.numeroPlantasMuertas = numeroPlantasMuertas;
		this.costoPreparacionTerreno = costoPreparacionTerreno;
		this.costoSemilla = costoSemilla;
		this.costoInsumosQuimicos = costoInsumosQuimicos;
		this.costoManoObra = costoManoObra;
		this.totalCostosHectarea = totalCostosHectarea;
		this.inversionTotal = inversionTotal;
		this.fechaAvisoCosecha = fechaAvisoCosecha;
		this.tipoInspeccion = tipoInspeccion;
		this.nuevo = nuevo;
		this.fechaSincronizacion = fechaSincronizacion;
	}
	
	public String getCodigoInspeccionProveedor() {
		return codigoInspeccionProveedor;
	}

	public void setCodigoInspeccionProveedor(String codigoInspeccionProveedor) {
		this.codigoInspeccionProveedor = codigoInspeccionProveedor;
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

	public String getFechaInspeccion() {
		return fechaInspeccion;
	}

	public void setFechaInspeccion(String fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
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
		
		private String tipoInspeccion;
		public Builder tipoInspeccion(final String tipoInspeccion) {
			this.tipoInspeccion=tipoInspeccion;
			return this;
		}
		
		private String nuevo;
		public Builder nuevo(final String nuevo) {
			this.nuevo=nuevo;
			return this;
		}
		
		private String fechaSincronizacion;
		public Builder fechaSincronizacion(final String fechaSincronizacion) {
			this.fechaSincronizacion=fechaSincronizacion;
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
		
		private String codigoInspeccionProveedor;
		public Builder codigoInspeccionProveedor(final String codigoInspeccionProveedor) {
			this.codigoInspeccionProveedor=codigoInspeccionProveedor;
			return this;
		}
		
		private String fertilizacion;
		public Builder fertilizacion(final String fertilizacion) {
			this.fertilizacion=fertilizacion;
			return this;
		}
		
		private String controlPlagas;
		public Builder controlPlagas(final String controlPlagas) {
			this.controlPlagas=controlPlagas;
			return this;
		}
		
		private String controlEnfermedades;
		public Builder controlEnfermedades(final String controlEnfermedades) {
			this.controlEnfermedades=controlEnfermedades;
			return this;
		}
		
		private String tipoPerdida;
		public Builder tipoPerdida(final String tipoPerdida) {
			this.tipoPerdida=tipoPerdida;
			return this;
		}
		
		private String fechaSiembra;
		public Builder fechaSiembra(final String fechaSiembra) {
			this.fechaSiembra=fechaSiembra;
			return this;
		}
		
		private String fechaInspeccion;
		public Builder fechaInspeccion(final String fechaInspeccion) {
			this.fechaInspeccion=fechaInspeccion;
			return this;
		}
		
		private String observacionesInforme;
		public Builder observacionesInforme(final String observacionesInforme) {
			this.observacionesInforme=observacionesInforme;
			return this;
		}
		
		private String tecnicoInspeccion;
		public Builder tecnicoInspeccion(final String tecnicoInspeccion) {
			this.tecnicoInspeccion=tecnicoInspeccion;
			return this;
		}
		
		private String codigoAvisoCosecha;
		public Builder codigoAvisoCosecha(final String codigoAvisoCosecha) {
			this.codigoAvisoCosecha=codigoAvisoCosecha;
			return this;
		}
		
		private BigDecimal porcentajeGerminacion;
		public Builder porcentajeGerminacion(final BigDecimal porcentajeGerminacion) {
			this.porcentajeGerminacion=porcentajeGerminacion;
			return this;
		}
		
		private BigDecimal poblacionHa;
		public Builder poblacionHa(final BigDecimal poblacionHa) {
			this.poblacionHa=poblacionHa;
			return this;
		}
		
		private BigDecimal porcentajeMaleza;
		public Builder porcentajeMaleza(final BigDecimal porcentajeMaleza) {
			this.porcentajeMaleza=porcentajeMaleza;
			return this;
		}
		
		private BigDecimal edadCultivo;
		public Builder edadCultivo(final BigDecimal edadCultivo) {
			this.edadCultivo=edadCultivo;
			return this;
		}
		
		private BigDecimal superficieSembrada;
		public Builder superficieSembrada(final BigDecimal superficieSembrada) {
			this.superficieSembrada=superficieSembrada;
			return this;
		}
		
		private BigDecimal superficieAfectada;
		public Builder superficieAfectada(final BigDecimal superficieAfectada) {
			this.superficieAfectada=superficieAfectada;
			return this;
		}
		
		private BigDecimal numeroPlantasMuertas;
		public Builder numeroPlantasMuertas(final BigDecimal numeroPlantasMuertas) {
			this.numeroPlantasMuertas=numeroPlantasMuertas;
			return this;
		}
		
		private BigDecimal costoPreparacionTerreno;
		public Builder costoPreparacionTerreno(final BigDecimal costoPreparacionTerreno) {
			this.costoPreparacionTerreno=costoPreparacionTerreno;
			return this;
		}
		
		private BigDecimal costoSemilla;
		public Builder costoSemilla(final BigDecimal costoSemilla) {
			this.costoSemilla=costoSemilla;
			return this;
		}
		
		private BigDecimal costoInsumosQuimicos;
		public Builder costoInsumosQuimicos(final BigDecimal costoInsumosQuimicos) {
			this.costoInsumosQuimicos=costoInsumosQuimicos;
			return this;
		}
		
		private BigDecimal costoManoObra;
		public Builder costoManoObra(final BigDecimal costoManoObra) {
			this.costoManoObra=costoManoObra;
			return this;
		}
		
		private BigDecimal totalCostosHectarea;
		public Builder totalCostosHectarea(final BigDecimal totalCostosHectarea) {
			this.totalCostosHectarea=totalCostosHectarea;
			return this;
		}
		
		private BigDecimal inversionTotal;
		public Builder inversionTotal(final BigDecimal inversionTotal) {
			this.inversionTotal=inversionTotal;
			return this;
		}
		
		public InformeInspeccionRetornoWrapper build() {
			return new InformeInspeccionRetornoWrapper(codigoSiniestro, codigoProveedor, codigoInspector, 
					cedulaAgricultor, nombreAgricultor, celularAgricultor, nombreCanal, numeroTramite, 
					nombreCultivo, nombreProvincia, nombreCanton, nombreParroquia, nombreRecinto, referencia,
					coordenadaX, coordenadaY, hectareasAseguradas, montoAsegurado, fechaRealSiembra, fechaAviso,
					fechaSiniestro, nombreCausa, fechaEnvioInspeccion, numeroPoliza, numeroCertificado,
					fechaInicioVigencia, fechaFinVigencia, numeroReclamo, observaciones, codigoInspeccionProveedor, 
					fertilizacion, controlPlagas, controlEnfermedades, tipoPerdida, fechaSiembra, fechaInspeccion,
					observacionesInforme, tecnicoInspeccion, porcentajeGerminacion, poblacionHa, porcentajeMaleza, 
					edadCultivo, superficieSembrada, superficieAfectada, numeroPlantasMuertas, costoPreparacionTerreno, 
					costoSemilla, costoInsumosQuimicos, costoManoObra, totalCostosHectarea, inversionTotal,
					fechaAvisoCosecha, tipoInspeccion,nuevo,fechaSincronizacion,codigoAvisoCosecha);
		}
	}
	
	
	
	
	
}
