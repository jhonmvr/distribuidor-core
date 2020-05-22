package com.relative.midas.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;

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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.bpms.api.BpmsApiClient;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiCotizacion;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.ParametrosSingleton;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.CotizacionWrapper;

import io.swagger.annotations.ApiOperation;

@Path("/cotizacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CotizacionRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCotizacion, GenericWrapper<TbMiCotizacion>> {

	
	@Inject
	Logger log;

	@Inject
	MidasOroService mis;
	
	@Inject
	CompraOroService cos;
	
	@Inject
	ParametrosSingleton ps;
	
	public CotizacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiCotizacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCotizacion> loc = new GenericWrapper<>();
		TbMiCotizacion a = this.mis.findCotizacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCotizacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCotizacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiCotizacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiCotizacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCotizacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCotizacion> actions = this.mis.findAllCotizacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCotizacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCotizacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiCotizacion> persistEntity(GenericWrapper<TbMiCotizacion> wp) throws RelativeException {
		GenericWrapper<TbMiCotizacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCotizacion(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/getEntityByEstado")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiCotizacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("estado") String estado)
			throws RelativeException {
		return findAllProveedorByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), estado);
	}

	private PaginatedListWrapper<TbMiCotizacion> findAllProveedorByParams(PaginatedWrapper pw, String estado)
			throws RelativeException {
		PaginatedListWrapper<TbMiCotizacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCotizacion> actions = null;
		actions = this.mis.findCotizacionByAll(pw, StringUtils.isNotBlank(estado)?MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countfindCotizacionByAll(
					StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null ).intValue());
			plw.setList(actions);
		}

		return plw;
	}
	
	@POST
	@Path("/generateCalculos")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public GenericWrapper<HashMap<String, String>> generateCalculos(GenericWrapper<HashMap<String,String>> parameters)
			throws RelativeException {
		log.info("===> ingresa a generateCalculos");
		log.info("===> ingresa a generateCalculos parametros " + parameters);
		GenericWrapper<HashMap<String, String>> local = new GenericWrapper<>();
		String idInstanceProcess=BpmsApiClient.callBpmsDroolProcesss(
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_URL_BASE_API).getValor(),
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_AUTH_API).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_CONTAINER_ID).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_BUSINESS_PROCESS).getValor(), parameters.getEntidad());
		log.info("===> idproceso generado " + idInstanceProcess);
		ArrayList<HashMap<String, Object>> ls=BpmsApiClient.getBpmsProcesssVariable(  
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_URL_BASE_API).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_AUTH_API).getValor(), idInstanceProcess);
		List<HashMap<String, String>> tmpl= new ArrayList<>();
		if( ls != null ) {
			
			 for( Map<String, Object> x:ls ) {
				 HashMap<String, String> tmpm= new HashMap<>();
				 log.info("===>datos has variable iterando " +x);
				 Set<String> keys = x.keySet();
				 for( String key:keys ){
					 tmpm.put(key, String.valueOf( x.get( key ) ));
				 }
				 tmpl.add(tmpm);
			 }
		 }
		local.setEntidades(tmpl);
		return local;
	}
	
	@POST
	@Path("/validarMontoContratoAprobacion")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public GenericWrapper<HashMap<String, String>> validarMontoContratoAprobacion(
			@QueryParam("paramUno") String identificacion,
			@QueryParam("paramDos") String montoCotizacion,
			GenericWrapper<HashMap<String,String>> parameters)
			throws RelativeException {
		
		log.info("===> ingresa a validarMontoContratoAprobacion");
		log.info("===> ingresa a validarMontoContratoAprobacion paramUno " +identificacion );
		log.info("===> ingresa a validarMontoContratoAprobacion paramDos " + montoCotizacion );
		log.info("===> ingresa a validarMontoContratoAprobacion parametros " + parameters);
		String montoContratosCalculados=this.getMontoContratos( identificacion , montoCotizacion);
		log.info("===> ingresa a validarMontoContratoAprobacion montoContratosCalculados " + montoContratosCalculados);
		parameters.getEntidad().put("montoContrato", montoContratosCalculados);
		GenericWrapper<HashMap<String, String>> local = new GenericWrapper<>();
		String idInstanceProcess=BpmsApiClient.callBpmsDroolProcesss(
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_URL_BASE_API).getValor(),
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_AUTH_API).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_CONTAINER_ID).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_BUSINESS_PROCESS_APROBACION).getValor(), 
				parameters.getEntidad());
		log.info("===> idproceso generado " + idInstanceProcess);
		ArrayList<HashMap<String, Object>> ls=BpmsApiClient.getBpmsProcesssVariable(  
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_URL_BASE_API).getValor(), 
				ps.getParametros().get(MidasOroConstantes.PARAM_BPM_AUTH_API).getValor(), idInstanceProcess);
		List<HashMap<String, String>> tmpl= new ArrayList<>();
		if( ls != null ) {
			
			 for( Map<String, Object> x:ls ) {
				 HashMap<String, String> tmpm= new HashMap<>();
				 log.info("===>datos has variable iterando " +x);
				 Set<String> keys = x.keySet();
				 for( String key:keys ){
					 tmpm.put(key, String.valueOf( x.get( key ) ));
				 }
				 tmpl.add(tmpm);
			 }
		 }
		local.setEntidades(tmpl);
		return local;
	}
	
	/**
	 * 
	 * @param identificacion
	 * @param montoCotizacion
	 * @return
	 * @throws RelativeException
	 */
	private String getMontoContratos(String identificacion , String montoCotizacion) throws RelativeException{
		List<TbMiContrato> contratos= this.mis.findContratoByIdentificacion(null, identificacion);		
		BigDecimal montoContratosValor=BigDecimal.valueOf( Double.valueOf(montoCotizacion)  ).setScale(2,RoundingMode.HALF_UP);
		if( contratos != null && !contratos.isEmpty() ) {
			Function<TbMiContrato, BigDecimal> totalMapper = c -> c.getValorContrato();
			BigDecimal result = contratos.stream().map(totalMapper)
			        .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2,RoundingMode.HALF_UP);
			montoContratosValor.add( result ).setScale(2,RoundingMode.HALF_UP);
			return montoContratosValor.toString();
		}
		return montoContratosValor.toString();
	}
	
	
	@POST
	@Path("/guardarCotizacion")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCotizacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiCotizacion> guardarCotizacion(GenericWrapper<CotizacionWrapper> wp) throws RelativeException {
		GenericWrapper<TbMiCotizacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.guardarCotizacion(wp.getEntidad()));
		return loc;
	}
}
