package ec.com.def.pa.rest;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.repository.ProvinciaRepository;
import ec.com.def.pa.service.PolizaAgricolaService;
import ec.com.def.pa.service.ReportService;
import ec.com.def.pa.util.SiniestroAgricolaConstantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@Path("/descargaCatalogoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " DescargaCatalogoRestController - REST CRUD")

public class DescargaCatalogoRestController extends BaseRestController {
	static final String PROVINCIA = "PROVINCIA";
	static final String CANTON = "CANTON";
	static final String PARROQUIA = "PARROQUIA";
	static final String RAMO_PLAN= "LISTA DE CULTIVOS";
	static final String GENERO= "GENERO";
	static final String ESTADO_CIVIL="ESTADO CIVIL";
	static final String NACIONALIDAD = "NACIONALIDAD";
	static final String ECONOMICA="ACTIVIDAD ECONOMICA";
	static final String CONDICION_PREDIO = "CONDICION PREDIO";
	static final String TIPO_SEMILLA = "TIPO SEMILLA";
	static final String RIEGO = "RIEGO";
	public DescargaCatalogoRestController() throws DefException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject 
	Logger log;
	
	@Inject 
	ReportService rs;
	
	@Inject
	PolizaAgricolaService sas;
	
	@Inject
	private ProvinciaRepository provinciaRepository;

	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "GenericWrapper<CatalogosGeneralWrapper>", notes = "Metodo que Retorna wrapper de entidades encontradas en CatalogosGeneralWrapper", 
	response = GenericWrapper.class)
		public byte[] getPlantilla(
			@QueryParam("catalogo") String catalogo,
			@QueryParam("format") String formato
		    ) throws  DefException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla catalogo " + catalogo );
		log.info("===================> getPlantilla formato " + formato );
		Map<String, Object> map = new HashMap<>();
		String path= this.sas.findByNombre(SiniestroAgricolaConstantes.REPORT_PATH).getValor();
		String jasper="descargaCatalogo.jasper";
		this.setParameters(map,path,  catalogo,jasper);
		this.setReportData(map, path, catalogo);
		return this.generateReport(map, path, formato, jasper);
	}
	
	/**
	 * Metodo que genera el reporte de con la informacion de habilitantes
	 * @param map
	 * @param path
	 * @param format
	 * @return
	 * @throws DefException 
	 */
	private byte[] generateReport(Map<String, Object> map,String path, String format,String jasper) throws  DefException{
		byte[] reportFile = null;
		
		if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
			reportFile = this.rs.generateReporteBeanExcel(null,map,path+jasper) ;
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL " + reportFile);
		    log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
		} else {
			reportFile = this.rs.generateReporteBeanCsv(null,map,		
					path+jasper );
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
		}	
		
		return reportFile;
	}

/**
 * LLena parametros base para el reporte
 * @param map parametros a enviar al reporte
 * @param codigoContrato

 */
private void setParameters(Map<String, Object> map,String path, String catalogo ,String jasper){
	map.put("catalogo", catalogo);
	map.put("mainReportName", jasper);
	map.put("REPORT_PATH", path );
	log.info("=========>ENTRA EL PATH setParameters " + path );
	log.info("=========>JASPER " + jasper );
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
private void setReportData(Map<String, Object> map,String path, String catalogo) throws DefException{
	
	if( StringUtils.isNotEmpty( catalogo )) {
	//log.info("=========>llega catalogo provincia " + catalogo); 
	   if(catalogo.equals(PROVINCIA))
		  {
			map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
			map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			log.info("=========>llega a provincia " + sas.setNombreCatalogo(catalogo)); 
			 }else if(catalogo.equals(CANTON)) {
				map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo) );
				map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(PARROQUIA)){
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo)); 
			 }else if (catalogo.equals(RAMO_PLAN)){
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(GENERO)){
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(ECONOMICA)) {
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 } else if(catalogo.equals(CONDICION_PREDIO)){
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(TIPO_SEMILLA)) {
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(RIEGO)){
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(NACIONALIDAD)) {
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
			 }else if(catalogo.equals(ESTADO_CIVIL)) {
				 map.put("LIST_DS", sas.setWrapperCatalaogoGeneral(catalogo));
				 map.put("BEAN_DS", sas.setNombreCatalogo(catalogo));
		 } 
	}
  }
	
}
