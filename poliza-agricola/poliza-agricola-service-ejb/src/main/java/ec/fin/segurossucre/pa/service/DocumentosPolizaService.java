package ec.com.def.pa.service;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;
import ec.com.def.pa.model.TbPaSolicitudPoliza;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;
import ec.com.def.pa.repository.SolicitudPolizaRepository;
import ec.com.def.pa.util.SiniestroAgricolaConstantes;
import ec.com.def.pa.util.SiniestroAgricolaUtils;
import ec.com.def.pa.wrapper.ConsultaSolicitudPolizaWrapper;
import ec.com.def.pa.wrapper.SolicitudPolizaWrapper;

@Stateless
public class DocumentosPolizaService {

	@Inject
	private Logger log;
	@Inject
	private PolizaAgricolaService sas;
	@Inject
	SolicitudPolizaService sps;
	@Inject
	ReportService rs;
	@Inject
	SolicitudPolizaRepository solicitudPolizaRepository;

	/**
	 * Metodo que crea los documentos map => mapeo de parametros format => PDF,
	 * EXCEL tipo => Tipo de documento [TB_DOC] id => identificador de los datos
	 * 
	 * @param map
	 * @param format
	 * @param tipo
	 * @param id
	 * @return
	 * @throws DefException
	 */
	public byte[] generarDocumentoPoliza(Map<String, Object> map, String format, String tipo, String numeroSolicitud, String numeroTramite,
			String desde,String hasta, String canal)
			throws DefException {
		try {
			String path =this.sas.findByNombre(SiniestroAgricolaConstantes.REPORT_PATH).getValor();
			if(StringUtils.isBlank(path)) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR PATH REPORTES");
			}

			log.info("=========>ENTRA a set repor data excel ");
			map.put("LIST_DS", this.setWrapperSolicitudPoliza(
					
					StringUtils.isNotBlank(numeroSolicitud)  ? numeroSolicitud: null,
							StringUtils.isNotBlank(numeroTramite) ? numeroTramite: null,
							StringUtils.isNotBlank(desde) ?  SiniestroAgricolaUtils.formatSringToDateFull(desde + " 00:00:00") : null,
					StringUtils.isNotBlank(hasta) ?SiniestroAgricolaUtils.formatSringToDateFull(hasta + " 23:59:59") : null,
							StringUtils.isNotBlank(canal)  ? canal: null));

			String jasper="descargaExcel.jasper";
			path = path.concat(jasper);

		   return generateReport(map, path, format);
		} catch (DefException e) {

			e.printStackTrace();
			throw e;
		} catch (Exception e) {

			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL GENERAR REPORTE" + e.getMessage());
		}
	}
	
	
	public List<ConsultaSolicitudPolizaWrapper> setWrapperSolicitudPoliza(String numeroSolicitud, String numeroTramite,
			Date desde, Date hasta, String canal) throws DefException {
		try {
			List<ConsultaSolicitudPolizaWrapper> rp = new ArrayList<>();
			List<ConsultaSolicitudPolizaWrapper> l = solicitudPolizaRepository.setWrapperSolicitudPoliza(
					StringUtils.isNotBlank(numeroSolicitud) ? numeroSolicitud : null,
					StringUtils.isNotBlank(numeroTramite) ? numeroTramite : null, desde, hasta,
					StringUtils.isNotBlank(canal) ? canal : null);
			//log.info("id  1");
			l.forEach(r -> {
				log.info("id  ");
				ConsultaSolicitudPolizaWrapper cp = new ConsultaSolicitudPolizaWrapper();
				if (r.getId() != null) {
					log.info("id documento" + r.getMontoAsegurado());
					cp.setCodigo(r.getCodigo());
					cp.setNumeroTramite(r.getNumeroTramite());
					cp.setCedulaSolictante(r.getCedulaSolictante());
					cp.setNombreSolicitante(r.getNombreSolicitante());
					cp.setApellidoSolicitante(r.getApellidoSolicitante());
					cp.setFechaCreacion(r.getFechaCreacion());
					cp.setCanal(r.getCanal());
					if(r.getEstado() !=null && !r.getEstado().toString().isEmpty()) {
					log.info("ESTADO NI" + r.getEstado());
						if (r.getEstado().equals(EstadoSiniestroAgricolaEnum.NI)) {
							cp.setEstadoValidado("NINGUNO");
							log.info("ESTADO NI" + r.getEstado());
						} else if (r.getEstado().equals(EstadoSiniestroAgricolaEnum.AA)) {
							cp.setEstadoValidado("APROBADO AUTOMATICO");
						} else if (r.getEstado().equals(EstadoSiniestroAgricolaEnum.PE)) {
							cp.setEstadoValidado("PENDIENTE");
						} else if (r.getEstado().equals(EstadoSiniestroAgricolaEnum.AM)) {
							cp.setEstadoValidado("APROBADO MANUAL");
						} else if (r.getEstado().equals(EstadoSiniestroAgricolaEnum.RE)) {
							cp.setEstadoValidado("RECHAZADO");
						}
					}
					cp.setCostoHectarea(r.getCostoHectarea());
					cp.setMontoAsegurado(r.getMontoAsegurado());
					cp.setObservacion(r.getObservacion());
					cp.setPrimaTotal(r.getPrimaTotal());
			        //log.info("lista completa " + l);

				}
				rp.add(cp);

			});
			//log.info("reporteS " + rp);
			return rp;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, e.getMessage());
		}

	}

	/**
	 * Metodo que crea los documentos map => mapeo de parametros format => PDF,
	 * EXCEL tipo => Tipo de documento [TB_DOC] id => identificador de los datos
	 * 
	 * @param map
	 * @param format
	 * @param tipo
	 * @param id
	 * @return
	 * @throws DefException
	 */
	public byte[] generarDocumento(Map<String, Object> map, String format, String tipo, String id)
			throws DefException {
		try {
			String path = this.sas.findByNombre(SiniestroAgricolaConstantes.REPORT_PATH).getValor();
			if(StringUtils.isBlank(path)) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR PATH REPORTES");
			}
			String pathSubreporte = this.sas.findByNombre(SiniestroAgricolaConstantes.REPORT_PATH).getValor();
			if(StringUtils.isBlank(path)) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR PATH REPORTES");
			}
			String pathLogo = this.sas.findByNombre(SiniestroAgricolaConstantes.REPORT_PATH).getValor();
			if(StringUtils.isBlank(path)) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR PATH REPORTES");
			}
			log.info("=========>ENTRA a set repordata 1 ");
			map.put("BEAN_DS", setSolicitudPolizaWrapper(id));
			if (StringUtils.isNotEmpty(id)) {
				if (tipo.equals(SiniestroAgricolaConstantes.SOLICITUD_POLIZA)) {
					map.put("BEAN_DS", setSolicitudPolizaWrapper(id));
					map.put("LIST_DS", setListSolicitudPolizaWrapper(id));
					TbPaTipoDocumentoPoliza plantilla = sas.findDocumentoByParams(SiniestroAgricolaConstantes.SOLICITUD_POLIZA, null,
							SiniestroAgricolaConstantes.SOLICITUD).get(0);
					String nombrePlantilla =plantilla.getPlantilla();						
					String subReportOneName =plantilla.getPlantillaUno();
					pathSubreporte = pathSubreporte.concat(subReportOneName);
					String logo="segurosSucre.png";
					pathLogo = pathLogo.concat(logo);
					map.put("pathLogo", pathLogo);
					map.put("subReportOneName", pathSubreporte);
					log.info("=========>subReportOneName "+pathSubreporte);
					path = path.concat(nombrePlantilla);
				}

			} else {
				// AQUI VAN LOS CATALOGOS
			}

			// TODO Auto-generated method stub
			return generateReport(map, path, format);
		} catch (DefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL GENERAR REPORTE" + e.getMessage());
		}
	}

	private byte[] generateReport(Map<String, Object> map, String path, String format) throws DefException {
		byte[] reportFile = null;
		log.info("=========>ENTRA a set generateReport  ");

		// reportFile = ;

		if (Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim())) {
			reportFile = this.rs.generateReporteFromBeanPDF(null, map, path/* +jasper */ );
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 "
					+ reportFile.length);
		} else {
			log.info("=========>=========>ingresa a generar rerporter ");
			reportFile = this.rs.generateReporteBeanExcel(null,map,path) ;
			log.info("=========>=========>ENTRA a map " + map);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL " + reportFile);
		    log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
		
		}

		return reportFile;
	}

	/**
	 * Metodo que llena el wrapper liquidacion contrato de compra a directo por el
	 * id del contrato
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public SolicitudPolizaWrapper setSolicitudPolizaWrapper(String idPoliza) throws DefException {
		try {
			TbPaSolicitudPoliza s = sps.findSolicitudPolizaById(Long.valueOf(idPoliza));
			SolicitudPolizaWrapper solicitudPolizaWrapper = new SolicitudPolizaWrapper();
			if(StringUtils.isNotBlank(Long.toString(s.getId()))) {
			solicitudPolizaWrapper.setnPoliza(Long.toString(s.getId()));
			}
			solicitudPolizaWrapper.setNombres(s.getTbSaAsegurado()!=null? s.getTbSaAsegurado().getNombres():" " );
			solicitudPolizaWrapper.setCedula(s.getTbSaAsegurado() !=null? s.getTbSaAsegurado().getIdentificacion():" ");
			solicitudPolizaWrapper.setApellidos(s.getTbSaAsegurado()!=null? s.getTbSaAsegurado().getApellido():" ");
			solicitudPolizaWrapper.setNacionalidad(s.getTbSaAsegurado() !=null? s.getTbSaAsegurado().getNacionalidad():" ");
			solicitudPolizaWrapper.setGenero(s.getTbSaAsegurado()!=null? s.getTbSaAsegurado().getSexo():" " );
			solicitudPolizaWrapper.setEstadoCivil(s.getTbSaAsegurado()!=null? s.getTbSaAsegurado().getEstadocivil().getEstadocivildes():" ");
			if(s.getTbSaAsegurado().getFechaNacimiento() !=null) {
			solicitudPolizaWrapper.setFechaNacimiento(SiniestroAgricolaUtils.dateToString(s.getTbSaAsegurado().getFechaNacimiento(), "dd/MM/yyyy"));
			}
			solicitudPolizaWrapper.setnTramite(s.getNumeroTramite()!=null? s.getNumeroTramite():" ");
			solicitudPolizaWrapper.setPersonaPoliticamente(s.getTbSaAsegurado().getPoliticamenteExpuesta()!=null? s.getTbSaAsegurado().getPoliticamenteExpuesta():" ");
			
		    // lugar de nacimiento
			solicitudPolizaWrapper.setProvinciaN(s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia() !=null? s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia().getProvincianom().trim() :" ");
			solicitudPolizaWrapper.setCantonN(s.getTbSaAsegurado().getparroquiaNacimiento().getCanton()!=null? s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getCantonnom().trim():" ");
			solicitudPolizaWrapper.setParroquiaN(s.getTbSaAsegurado().getparroquiaDomicilio() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom().trim(): "");
			// direccion domicilio
			solicitudPolizaWrapper.setProvinciaD(s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom().trim():" ");
			solicitudPolizaWrapper.setCantonD(s.getTbSaAsegurado().getparroquiaDomicilio().getCanton() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getCantonnom().trim():" ");
			solicitudPolizaWrapper.setParroquiaD(s.getTbSaAsegurado().getparroquiaDomicilio() !=null?  s.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom().trim():" ");
			solicitudPolizaWrapper.setCalled(s.getTbSaAsegurado().getDireccion()!=null? s.getTbSaAsegurado().getDireccion() :" ");
			solicitudPolizaWrapper.setTelefonoConvencional(s.getTbSaAsegurado() !=null? s.getTbSaAsegurado().getTelefonoConvencional():" ");
			solicitudPolizaWrapper.setTelefonoCelular(s.getTbSaAsegurado()!=null? s.getTbSaAsegurado().getTelefonoCelular():" " );
			solicitudPolizaWrapper.setCorreoElectronico(s.getTbSaAsegurado() !=null? s.getTbSaAsegurado().getCorreo():" ");
			solicitudPolizaWrapper.setReferenciaD(s.getTbSaAsegurado()  !=null? s.getTbSaAsegurado().getReferencia() :" ");
			solicitudPolizaWrapper.setIngresoMensual(s.getTbSaAsegurado() !=null? s.getTbSaAsegurado().getIngresoAnual().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setActividadEconomica(s.getTbSaAsegurado().getActeco() !=null? s.getTbSaAsegurado().getActeco().getAecdes().trim():" ");
			// datos especificos del predio
			solicitudPolizaWrapper.setProvinciaP(s.getTbPaPredio().getParroquia().getCanton().getProvincia() !=null? s.getTbPaPredio().getParroquia().getCanton().getProvincia().getProvincianom().trim():" " );
			solicitudPolizaWrapper.setCantonP(s.getTbPaPredio().getParroquia().getCanton() !=null? s.getTbPaPredio().getParroquia().getCanton().getCantonnom().trim():" ");
			solicitudPolizaWrapper.setParroquiaP(s.getTbPaPredio().getParroquia() !=null? s.getTbPaPredio().getParroquia().getParroquianom().trim(): " ");
			solicitudPolizaWrapper.setCondicionesPredio(s.getTbPaPredio() !=null? s.getTbPaPredio().getCondicionpredio().getCondicionprediodes() :" ");
			solicitudPolizaWrapper.setReferenciaP(s.getTbPaPredio().getReferencia()!=null?s.getTbPaPredio().getReferencia():" ");
			solicitudPolizaWrapper.setCoordenadaX(s.getTbPaPredio().getCoordenadaX()!=null?s.getTbPaPredio().getCoordenadaX().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setCoordenadaY(s.getTbPaPredio().getCoordenadaY()!=null?s.getTbPaPredio().getCoordenadaY().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setAltitudPredio(s.getTbPaPredio().getAltitud() != null? s.getTbPaPredio().getAltitud().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			// caracteristicas del cultivo
			solicitudPolizaWrapper.setCultivo(s.getTbPaCaracteristicaCultivo().getRamoplan()!=null? s.getTbPaCaracteristicaCultivo().getRamoplan().getRamoplannombre(): "");
			solicitudPolizaWrapper.setVariedad(s.getTbPaCaracteristicaCultivo().getVariedad()!=null? s.getTbPaCaracteristicaCultivo().getVariedad(): "");
			solicitudPolizaWrapper.setSuperficieAsegurar(s.getTbPaCaracteristicaCultivo().getSuperficieAsegurada()!=null?s.getTbPaCaracteristicaCultivo().getSuperficieAsegurada().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setMontoAsegurado(s.getTbPaCaracteristicaCultivo().getMontoAsegurado()!=null?s.getTbPaCaracteristicaCultivo().getMontoAsegurado().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setCostosHectarea(s.getTbPaCaracteristicaCultivo().getCostoHectarea()!=null?s.getTbPaCaracteristicaCultivo().getCostoHectarea().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString() : "");
			if(s.getTbPaCaracteristicaCultivo().getFechaSiembra()!=null) {
			solicitudPolizaWrapper.setFechaTentativa(SiniestroAgricolaUtils.dateToString(s.getTbPaCaracteristicaCultivo().getFechaSiembra(), "dd/MM/yyyy"));
			
			}
			solicitudPolizaWrapper.setAsistenciaRiego(s.getTbPaCaracteristicaCultivo().getAsistenciaTecnica()!=null?s.getTbPaCaracteristicaCultivo().getAsistenciaTecnica(): " ");
			solicitudPolizaWrapper.setRiego(StringUtils.isNotBlank(s.getTbPaCaracteristicaCultivo().getRiego())?s.getTbPaCaracteristicaCultivo().getRiego(): " ");
			solicitudPolizaWrapper.setTipoRiego(s.getTbPaCaracteristicaCultivo().getRiegoBean() !=null? s.getTbPaCaracteristicaCultivo().getRiegoBean().getRiegodes() : " ");
			solicitudPolizaWrapper.setTipoSemilla(s.getTbPaCaracteristicaCultivo().getTiposemilla() !=null? s.getTbPaCaracteristicaCultivo().getTiposemilla().getTiposemillades(): " ");
			// canal de solicitud
			solicitudPolizaWrapper.setCanalContratacion(s.getRamocanal().getCanalnombre()!=null? s.getRamocanal().getCanalnombre():" ");
			solicitudPolizaWrapper.setNombreEjecutivo(StringUtils.isNotBlank(s.getNombreEjecutivo())? s.getNombreEjecutivo(): " ");
			solicitudPolizaWrapper.setContactoCanal(StringUtils.isNotBlank(s.getContactoEjecutivo())?s.getContactoEjecutivo():" ");
			solicitudPolizaWrapper.setTieneEndoso(StringUtils.isNotBlank(s.getTieneEndoso())?s.getTieneEndoso():" ");
			solicitudPolizaWrapper.setAceptarInformacion(StringUtils.isNotBlank(s.getAutorizacion())?s.getAutorizacion():"");
			solicitudPolizaWrapper.setValorEndoso(  s.getValorEndoso()!=null?s.getValorEndoso().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString() : "");
			if(s.getFechaCreacion()!=null) {
			solicitudPolizaWrapper.setFechaCreacionSolicitud(SiniestroAgricolaUtils.dateToString(s.getFechaCreacion(), "yyyy/MM/dd"));
			}
			if(s.getUbicacionPredio()!= null) {
				solicitudPolizaWrapper.setUbicacion( ImageIO.read(new ByteArrayInputStream(s.getUbicacionPredio())));
				}
			solicitudPolizaWrapper.setRecintoP(s.getTbPaPredio().getRecinto() != null? s.getTbPaPredio().getRecinto(): "");
			return solicitudPolizaWrapper;
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "AL BUSCAR LA INFORMACION DE LA SOLICITUD " + e.getCause());
		}
	}
	
	/**
	 * Metodo que llena el wrapper liquidacion contrato de compra a directo por el
	 * id del contrato
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<SolicitudPolizaWrapper> setListSolicitudPolizaWrapper(String idPoliza) throws DefException {
		try {
			TbPaSolicitudPoliza s = sps.findSolicitudPolizaById(Long.valueOf(idPoliza));
			List<SolicitudPolizaWrapper> listaSolicitudPoliza = new ArrayList<SolicitudPolizaWrapper>();
			SolicitudPolizaWrapper solicitudPolizaWrapper = new SolicitudPolizaWrapper();
			if(StringUtils.isNotBlank(Long.toString(s.getId()))) {
			solicitudPolizaWrapper.setnPoliza(Long.toString(s.getId()));
			}
			solicitudPolizaWrapper.setNombres(s.getTbSaAsegurado().getNombres()!=null? s.getTbSaAsegurado().getNombres():" " );
			solicitudPolizaWrapper.setCedula(s.getTbSaAsegurado().getIdentificacion() !=null? s.getTbSaAsegurado().getIdentificacion():" ");
			solicitudPolizaWrapper.setApellidos(s.getTbSaAsegurado().getApellido() !=null? s.getTbSaAsegurado().getApellido():" ");
			solicitudPolizaWrapper.setNacionalidad(s.getTbSaAsegurado().getNacionalidad() !=null? s.getTbSaAsegurado().getNacionalidad():" ");
			solicitudPolizaWrapper.setGenero(s.getTbSaAsegurado().getSexo() !=null? s.getTbSaAsegurado().getSexo():" " );
			solicitudPolizaWrapper.setEstadoCivil(s.getTbSaAsegurado().getEstadocivil()!=null? s.getTbSaAsegurado().getEstadocivil().getEstadocivildes():" ");
			if(s.getTbSaAsegurado().getFechaNacimiento() !=null) {
			solicitudPolizaWrapper.setFechaNacimiento(SiniestroAgricolaUtils.dateToFullString(s.getTbSaAsegurado().getFechaNacimiento()));
			}
			
			solicitudPolizaWrapper.setPersonaPoliticamente(s.getTbSaAsegurado().getPoliticamenteExpuesta()!=null? s.getTbSaAsegurado().getPoliticamenteExpuesta():" ");
			
		    // lugar de nacimiento
			solicitudPolizaWrapper.setProvinciaN(s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia() !=null? s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia().getProvincianom() :" ");
			solicitudPolizaWrapper.setCantonN(s.getTbSaAsegurado().getparroquiaNacimiento().getCanton()!=null? s.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getCantonnom():" ");
			solicitudPolizaWrapper.setParroquiaN(s.getTbSaAsegurado().getparroquiaDomicilio() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom(): "");
			// direccion domicilio
			solicitudPolizaWrapper.setProvinciaD(s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom():" ");
			solicitudPolizaWrapper.setCantonD(s.getTbSaAsegurado().getparroquiaDomicilio().getCanton() !=null? s.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getCantonnom():" ");
			solicitudPolizaWrapper.setParroquiaD(s.getTbSaAsegurado().getparroquiaDomicilio() !=null?  s.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom():" ");
			solicitudPolizaWrapper.setCalled(s.getTbSaAsegurado().getDireccion()!=null? s.getTbSaAsegurado().getDireccion() :" ");
			solicitudPolizaWrapper.setTelefonoConvencional(s.getTbSaAsegurado().getTelefonoConvencional() !=null? s.getTbSaAsegurado().getTelefonoConvencional():" ");
			solicitudPolizaWrapper.setTelefonoCelular(s.getTbSaAsegurado().getTelefonoCelular()!=null? s.getTbSaAsegurado().getTelefonoCelular():" " );
			solicitudPolizaWrapper.setCorreoElectronico(s.getTbSaAsegurado().getCorreo() !=null? s.getTbSaAsegurado().getCorreo():" ");
			solicitudPolizaWrapper.setReferenciaD(s.getTbSaAsegurado().getReferencia()  !=null? s.getTbSaAsegurado().getReferencia() :" ");
			solicitudPolizaWrapper.setIngresoMensual(s.getTbSaAsegurado().getIngresoAnual() !=null? s.getTbSaAsegurado().getIngresoAnual().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setActividadEconomica(s.getTbSaAsegurado().getActeco() !=null? s.getTbSaAsegurado().getActeco().getAecdes():" ");
			// datos especificos del predio
			solicitudPolizaWrapper.setProvinciaP(s.getTbPaPredio().getParroquia().getCanton().getProvincia() !=null? s.getTbPaPredio().getParroquia().getCanton().getProvincia().getProvincianom().trim():" " );
			solicitudPolizaWrapper.setCantonP(s.getTbPaPredio().getParroquia().getCanton() !=null? s.getTbPaPredio().getParroquia().getCanton().getCantonnom().trim():" ");
			solicitudPolizaWrapper.setParroquiaP(s.getTbPaPredio().getParroquia() !=null? s.getTbPaPredio().getParroquia().getParroquianom().trim(): " ");
			solicitudPolizaWrapper.setCondicionesPredio(s.getTbPaPredio().getCondicionpredio() !=null? s.getTbPaPredio().getCondicionpredio().getCondicionprediodes() :" ");
			solicitudPolizaWrapper.setReferenciaP(s.getTbPaPredio().getReferencia()!=null?s.getTbPaPredio().getReferencia():" ");
			solicitudPolizaWrapper.setCoordenadaX(s.getTbPaPredio().getCoordenadaX()!=null?s.getTbPaPredio().getCoordenadaX().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setCoordenadaY(s.getTbPaPredio().getCoordenadaY()!=null?s.getTbPaPredio().getCoordenadaY().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setAltitudPredio(s.getTbPaPredio().getAltitud() != null? s.getTbPaPredio().getAltitud().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setRecintoP(s.getTbPaPredio().getRecinto() != null? s.getTbPaPredio().getRecinto(): "");
			
			// caracteristicas del cultivo
			solicitudPolizaWrapper.setCultivo(s.getTbPaCaracteristicaCultivo().getRamoplan()!=null? s.getTbPaCaracteristicaCultivo().getRamoplan().getRamoplannombre(): "");
			solicitudPolizaWrapper.setVariedad(s.getTbPaCaracteristicaCultivo().getVariedad()!=null? s.getTbPaCaracteristicaCultivo().getVariedad(): "");
			solicitudPolizaWrapper.setSuperficieAsegurar(s.getTbPaCaracteristicaCultivo().getSuperficieAsegurada()!=null?s.getTbPaCaracteristicaCultivo().getSuperficieAsegurada().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setMontoAsegurado(s.getTbPaCaracteristicaCultivo().getMontoAsegurado()!=null?s.getTbPaCaracteristicaCultivo().getMontoAsegurado().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(): "");
			solicitudPolizaWrapper.setCostosHectarea(s.getTbPaCaracteristicaCultivo().getCostoHectarea()!=null?s.getTbPaCaracteristicaCultivo().getCostoHectarea().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString() : "");
			if(s.getTbPaCaracteristicaCultivo().getFechaSiembra()!=null) {
				solicitudPolizaWrapper.setFechaTentativa(SiniestroAgricolaUtils.dateToString(s.getTbPaCaracteristicaCultivo().getFechaSiembra(), "dd/MM/yyyy"));
			}
			solicitudPolizaWrapper.setAsistenciaRiego(s.getTbPaCaracteristicaCultivo().getAsistenciaTecnica()!=null?s.getTbPaCaracteristicaCultivo().getAsistenciaTecnica(): " ");
			solicitudPolizaWrapper.setRiego(StringUtils.isNotBlank(s.getTbPaCaracteristicaCultivo().getRiego())?s.getTbPaCaracteristicaCultivo().getRiego(): " ");
			solicitudPolizaWrapper.setTipoRiego(s.getTbPaCaracteristicaCultivo().getRiegoBean() !=null? s.getTbPaCaracteristicaCultivo().getRiegoBean().getRiegodes() : " ");
			solicitudPolizaWrapper.setTipoSemilla(s.getTbPaCaracteristicaCultivo().getTiposemilla() !=null? s.getTbPaCaracteristicaCultivo().getTiposemilla().getTiposemillades(): " ");
			// canal de solicitud
			solicitudPolizaWrapper.setCanalContratacion(s.getRamocanal().getCanalnombre()!=null? s.getRamocanal().getCanalnombre():" ");
			solicitudPolizaWrapper.setNombreEjecutivo(StringUtils.isNotBlank(s.getNombreEjecutivo())? s.getNombreEjecutivo(): " ");
			solicitudPolizaWrapper.setContactoCanal(StringUtils.isNotBlank(s.getContactoEjecutivo())?s.getContactoEjecutivo():" ");
			solicitudPolizaWrapper.setTieneEndoso(StringUtils.isNotBlank(s.getTieneEndoso())?s.getTieneEndoso():" ");
			solicitudPolizaWrapper.setAceptarInformacion(StringUtils.isNotBlank(s.getAutorizacion())?s.getAutorizacion():"");
			solicitudPolizaWrapper.setValorEndoso(  s.getValorEndoso()!=null?s.getValorEndoso().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString() : "");
			if(s.getUbicacionPredio()!= null) {
			solicitudPolizaWrapper.setUbicacion( ImageIO.read(new ByteArrayInputStream(s.getUbicacionPredio())));
			}
			if(s.getFechaCreacion()!=null) {
				solicitudPolizaWrapper.setFechaCreacionSolicitud(SiniestroAgricolaUtils.dateToFullString(s.getFechaCreacion()));
			}
			
			listaSolicitudPoliza.add(solicitudPolizaWrapper);
			return listaSolicitudPoliza;
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "AL BUSCAR LA INFORMACION DE LA SOLICITUD " + e.getCause());
		}
	}
	

}
