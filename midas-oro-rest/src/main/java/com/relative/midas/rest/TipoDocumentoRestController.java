package com.relative.midas.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.TipoPlantillaEnum;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiTipoDocumento;
import com.relative.midas.service.HabilitanteService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.ParametrosSingleton;
import com.relative.midas.service.ReportService;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.wrapper.ContratoCompraDirectaHabilitantewrapper;
import com.relative.midas.wrapper.ContratoCompraPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.DetalleIngresoEgresoWrapper;
import com.relative.midas.wrapper.MovimientosDetalleCierreCajaWrapper;

import io.swagger.annotations.ApiOperation;

@Path("/tipoDocumentoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
 
public class TipoDocumentoRestController  extends BaseRestController implements CrudRestControllerInterface<TbMiTipoDocumento, GenericWrapper<TbMiTipoDocumento>> {

	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	@Inject
	HabilitanteService hs;
	@Inject
	ParametrosSingleton ps;
	
	@Inject 
	ReportService rs;
	
	
	
	public TipoDocumentoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDocumento>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDocumento", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoDocumento> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiTipoDocumento> loc = new GenericWrapper<>();
		TbMiTipoDocumento a =this.mis.findTipoDocumentoById((Long.valueOf(id)));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/metodoParabuscar")
	@ApiOperation(value = "GenericWrapper<TbMiDocumento>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDocumento", 
	response = GenericWrapper.class)
	public GenericWrapper<MovimientosDetalleCierreCajaWrapper> metodoParabuscar(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<MovimientosDetalleCierreCajaWrapper> loc = new GenericWrapper<>();
		List<MovimientosDetalleCierreCajaWrapper> a =this.hs.metodoParabuscar(id);
		loc.setEntidades(a);
		return loc;
	}
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiDocumento>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiDocumento", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiTipoDocumento> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiTipoDocumento> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiTipoDocumento> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoDocumento> actions = this.mis.findAllDocumento(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countJoyaSim().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDocumento>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiDocumento", 
	response = GenericWrapper.class)
	
	public GenericWrapper<TbMiTipoDocumento> persistEntity(GenericWrapper<TbMiTipoDocumento> wp) throws RelativeException {
		GenericWrapper<TbMiTipoDocumento> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageDocumento(wp.getEntidad()));
		return loc;
	}
	
	
	

	@GET
	@Path("/getEntityByTipoDocumento")
	@ApiOperation(value = "GenericWrapper<TbMiTipoDocumento>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbMiTipoDocumento", 
	response = GenericWrapper.class)
		public PaginatedListWrapper<TbMiTipoDocumento> getEntityByTipoDocumento(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoDocumento") String tipoDocumento,
		    @QueryParam("codigo") String codigo,
		    @QueryParam("idJoya") String idJoya,
		    @QueryParam("idAbono") String idAbono, 
		 	@QueryParam("idAgencia") String idAgencia,
	 		@QueryParam("idCorteCaja") String idCorteCaja) 
			throws RelativeException {
		log.info("===================> getEntityByTipoDocumento");
		log.info("===================> getEntityByTipoDocumento tipoDocumento " + tipoDocumento );
		log.info("===================> getEntityByTipoDocumento codigo " + codigo );
		return findAllDocumentoByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
			sortFields, sortDirections, isPaginated),tipoDocumento,codigo, idJoya, idAbono, idCorteCaja);
		}


	private PaginatedListWrapper<TbMiTipoDocumento> findAllDocumentoByParams(PaginatedWrapper pw,
			String tipoDocumento, String codigo, String idJoya, String idAbono, String idCorteCaja) throws RelativeException {
		PaginatedListWrapper<TbMiTipoDocumento> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoDocumento> actions = null;
		
			actions = this.mis.findDocumentoByTipoAndCodigo(pw, tipoDocumento, codigo, 
					idJoya != null?Long.valueOf(idJoya):null,idAbono!= null?Long.valueOf(idAbono):null);
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(
						this.mis.countfindDocumentoByTipoAndcodigo(tipoDocumento , codigo, 
								idJoya != null?Long.valueOf(idJoya):null,idAbono!= null?Long.valueOf(idAbono):null).intValue());
				plw.setList(actions.stream().distinct().collect(Collectors.toList()));
			}
		
		return plw;
	}
	

	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "GenericWrapper<TbMiTipoDocumento>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbMiTipoDocumento", 
	response = GenericWrapper.class)
		public byte[] getPlantilla(
			@QueryParam("id") String id,
			@QueryParam("format") String formato,
		    @QueryParam("codigoContrato") String codigoContrato,
		    @QueryParam("idJoya") String idJoya,
		    @QueryParam("idAbono") String idAbono,
		    @QueryParam("idAgencia") String idAgencia,
		    @QueryParam("idVentaLote") String idVentaLote,
		    @QueryParam("usuario") String usuario,
		    @QueryParam("idCorteCaja") String idCorteCaja
		    ) throws RelativeException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla id " + id );
		log.info("===================> getPlantilla codigoContrato " + codigoContrato );
		log.info("===================> getPlantilla idJoya " + idJoya );
		log.info("===================> getPlantilla idAbono " + idAbono );
		log.info("===================> getPlantilla idAgencia " + idAgencia );
		log.info("===================> getPlantilla idVentaLote " + idVentaLote);
		log.info("===================> getPlantilla idCorteCaja " + idCorteCaja);
		log.info("===================> getPlantilla format " + formato );
		Map<String, Object> map = new HashMap<>();
		
		//String path= "C:\\Users\\jukis\\JaspersoftWorkspace\\PrjMidasReportes\\";
		String path= this.ps.getParametros().get(MidasOroConstantes.PATH_REPORTE).getValor();
		TbMiTipoDocumento td= this.mis.findTipoDocumentoById(Long.valueOf( id ) );
		
		TbMiContrato ccc = null;
		if(StringUtils.isNotBlank(codigoContrato)) {
			ccc=this.mis.findContratoById( Long.valueOf( codigoContrato ) );
		}
		this.setParameters(map,path, ccc != null ?ccc.getCodigo():null, idJoya, idAbono, idAgencia, idCorteCaja, td);
		this.setReportData(map, path,ccc != null ?ccc.getCodigo():null, idJoya, idAbono, idAgencia, idVentaLote, td,usuario, idCorteCaja);
		return this.generateReport(map, path, formato, td);
	}
		
		/**
		 * Metodo que genera el reporte de con la informacion de habilitantes
		 * @param map
		 * @param path
		 * @param format
		 * @param td
		 * @return
		 * @throws RelativeException
		 */
		private byte[] generateReport(Map<String, Object> map,String path, String format,TbMiTipoDocumento td) throws RelativeException{
			byte[] reportFile = null;
			String mainReportName = td.getPlantilla();
			log.info("=========>ENTRA EN TipoDocumentoRestController generateReport  " + MidasOroConstantes.PREFIX_REPORT_MAIN_PATH +td.getPlantilla() );
			if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
				//reportFile = this.rs.generateReporteFromBeanPDF(sins , map,
				reportFile = this.rs.generateReporteFromBeanPDF(null , map, 
						path+mainReportName);
				log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile);
				log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile.length);
			} else {
				reportFile = this.rs.generateReporteBeanCsv(null,map,		
						path+mainReportName );
				log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile);
				log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
			}	
			return reportFile;
		}
	
	
	/**
	 * LLena parametros base para el reporte
	 * @param map parametros a enviar al reporte
	 * @param codigoContrato
	 * @param codigoJoya
	 * @param idAbono
	 * @param td
	 */
	private void setParameters(Map<String, Object> map,String path, String codigoContrato,
			String idJoya, String idAbono, String idAgencia, String idCorteCaja, TbMiTipoDocumento td){
		map.put("codigoContrato", codigoContrato);
		map.put("codigoJoya", idJoya);
		map.put("idAbono", idAbono);
		map.put("idAgencia", idAgencia);
		map.put("idCorteCaja", idCorteCaja);
		map.put("subReportOneName",  td.getPlantillaUno() );
		map.put("subReportTwoName", td.getPlantillaDos() );
		map.put("subReportThreeName", td.getPlantillaTres());
		map.put("mainReportName", td.getPlantilla());
		map.put("REPORT_PATH", path );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters " + path+td.getPlantilla() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 1" + path+td.getPlantillaUno() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 2" + path+td.getPlantillaDos() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 3" + path+td.getPlantillaTres() );
	}
	
	/**
	 * Metodo que llena la informacion necesaria para la generacion del reporte documento habilitante
	 * @param map parametros que contienen la informacion para el reporte
	 * @param path
	 * @param codigoContrato
	 * @param codigoJoya
	 * @param idAbono
	 * @param td
	 * @param usuario 
	 * @throws RelativeException
	 */
	private void setReportData(Map<String, Object> map,String path, String codigoContrato,
			String idJoya, String idAbono, String idAgencia, String idVentaLote, TbMiTipoDocumento td, String usuario, String idCorteCaja) throws RelativeException{
		
		
		if( !StringUtils.isEmpty( codigoContrato )  ) {
			if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.CD )==0  )  {
				List<ContratoCompraDirectaHabilitantewrapper> contratoCompraDirecta = new ArrayList<>();
				contratoCompraDirecta.add( hs.setWrapperCDByCodigoContrato(codigoContrato) );
				map.put("LIST_DS", contratoCompraDirecta );
			} else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.CP )==0 ) {
				List<ContratoCompraPlazoHabilitanteWrapper> contratoCompraPlazo = new ArrayList<>();
				contratoCompraPlazo.add( hs.setWrapperCDByCodigoContratoPlazo(codigoContrato) );
				map.put("LIST_DS", contratoCompraPlazo );
			} else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.DevCP )==0  ) {
				map.put("BEAN_DS", hs.setDevolucionCP(codigoContrato,usuario) );
			}else if(   td.getTipoPlantilla().compareTo( TipoPlantillaEnum.LiqCD )==0 ) {
				map.put("BEAN_DS",  hs.setLiquidacionCD(codigoContrato));
				map.put("LIST_DS", hs.setJoyaReporteWrapper(codigoContrato) );
			}else if(   td.getTipoPlantilla().compareTo( TipoPlantillaEnum.LiqCB )==0  ) {
				map.put("BEAN_DS", hs.setLiquidacionCB(codigoContrato) );
				map.put("LIST_DS", hs.setJoyaReporteWrapper(codigoContrato));
			}else if(   td.getTipoPlantilla().compareTo( TipoPlantillaEnum.LiqCP )==0 ) {
				map.put("BEAN_DS", hs.setLiquidacionCP(codigoContrato) );
				map.put("LISTJOYA_DS", hs.setJoyaReporteWrapper(codigoContrato));	
			}else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.PerCP )==0  ) {
				map.put("BEAN_DS", hs.setPerfeccionamientoCP(codigoContrato) );
			}else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.AdeCP )==0 ) {
				map.put("BEAN_DS", hs.setAdendaCP(codigoContrato,usuario)  );
			}else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.EGRE )==0 ) {
				List<DetalleIngresoEgresoWrapper> detalleEngreso = new ArrayList<>();
				detalleEngreso.add( hs.setDetalleIEWrapperEgreso(codigoContrato) );
				map.put("BEAN_DS", hs.setComprobanteEgresoWrapper(codigoContrato) );
				map.put("LIST_DS", detalleEngreso);
			}else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.INGCAN )==0 ) {
				List<DetalleIngresoEgresoWrapper> detalleIngreso = new ArrayList<>();
				detalleIngreso.add( hs.setDetalleIEWrapperIngreso(codigoContrato,"INGCAN") );
				map.put("BEAN_DS", hs.setComprobanteIngresoWrapper(codigoContrato, "INGCAN", usuario) );
				map.put("LIST_DS", detalleIngreso );
			}
			else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.INGREN )==0 ) {
				List<DetalleIngresoEgresoWrapper> detalleIngreso = new ArrayList<>();
				detalleIngreso.add( hs.setDetalleIEWrapperIngreso(codigoContrato,"INGREN") );
				map.put("BEAN_DS", hs.setComprobanteIngresoWrapper(codigoContrato, "INGREN", usuario) );
				map.put("LIST_DS", detalleIngreso );
			}
			
			else if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.IC )==0 ) {
				map.put("BEAN_DS", hs.setFormConozcaCliente(codigoContrato));
				
			}
			
			
		}else if( !StringUtils.isEmpty( idAbono )  ) {
			List<DetalleIngresoEgresoWrapper> detalleIngresoAbono = new ArrayList<>();
			detalleIngresoAbono.add( hs.setDetalleIEWrapperIngresoAbono(idAbono) );
			map.put("BEAN_DS", hs.setComprobanteIngresoAbonoWrapper(idAbono, idAgencia,usuario) );
			map.put("LIST_DS", detalleIngresoAbono);
			} 
		
		else if( !StringUtils.isEmpty( idJoya )  ) {
			List<DetalleIngresoEgresoWrapper> detalleIngresoJoya = new ArrayList<>();
			detalleIngresoJoya.add( hs.setDetalleIngresoVentaVitrina(idJoya) );
			map.put("BEAN_DS", hs.setComprobanteIngresoVentaVitrina(idJoya,usuario) );
			map.put("LIST_DS", detalleIngresoJoya);	
		}else if( !StringUtils.isEmpty( idVentaLote )  ) {
			List<DetalleIngresoEgresoWrapper> detalleVentaLote = new ArrayList<>();
			detalleVentaLote.add( hs.setDetalleIngresoVentaLote(idVentaLote) );
			map.put("BEAN_DS", hs.setComprobanteIngresoVentaLote(idVentaLote,idAgencia, usuario) );
			map.put("LIST_DS", detalleVentaLote);	
		} 
		else if( !StringUtils.isEmpty( idVentaLote ) && td.getTipoPlantilla().compareTo( TipoPlantillaEnum.REC )==0  ) {
					
					map.put("LIST_DS", hs.setLoteWrapper(idVentaLote));
					map.put("BEAN_DS", hs.setActaRecepcionWrapper(idVentaLote, idAgencia, usuario));	
				}
		else if( !StringUtils.isEmpty( idCorteCaja )  ) {
			map.put("BEAN_DS", hs.setCierreCajaHabilitanteWrapper(idCorteCaja));
			map.put("LIST_DS", hs.setDetalleCajaHabilitanteWrapper(idCorteCaja));
			map.put("LISTMOV_DS", hs.setDetalleCierreCajaMovimientosWrapper(idCorteCaja));
		} 
	}
	
	
}
