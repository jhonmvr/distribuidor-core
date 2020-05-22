package ec.com.def.pa.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.pa.enums.CalificacionSiniestroEnum;
import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;
import ec.com.def.pa.enums.TipoPerdidaSiniestroEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/enumRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " EnumRestController - REST CRUD")
//@Api(value = "UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES",tags = {"UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES"})
public class EnumRestController extends BaseRestController {
	
	@Inject 
	Logger log;
	
	public EnumRestController() throws DefException{
		super();
	}
	
	@GET
	@Path("/loadEstadoEnvioEnums")
	@ApiOperation(value = "EstadoSiniestroAgricolaEnum ", notes = "Metodo EstadoSiniestroAgricolaEnum Retorna valores loadEstadoEnvioEnums", 
	response = EstadoSiniestroAgricolaEnum[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = EstadoSiniestroAgricolaEnum[].class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public EstadoSiniestroAgricolaEnum[]  loadEstadoEnvioEnums()  {
		log.info("=====  ==========>loadEnum " );
		return EstadoSiniestroAgricolaEnum.values(); 
	}
	
	
	@GET
	@Path("/loadCalificacionSiniestroEnums")
	@ApiOperation(value = "CalificacionSiniestroEnum", notes = "Metodo CalificacionSiniestroEnum Retorna valores loadCalificacionSiniestroEnums", 
	response = CalificacionSiniestroEnum[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = CalificacionSiniestroEnum[].class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public CalificacionSiniestroEnum[]  loadCalificacionSiniestroEnums(){
		log.info("==============  =>loadEnum " );
		return CalificacionSiniestroEnum.values(); 
	}
	
	@GET
	@Path("/loadTipoPerdidaSiniestroEnums")
	@ApiOperation(value = "TipoPerdidaSiniestroEnum", notes = "Metodo CalificacionSiniestroEnum Retorna valores loadTipoPerdidaSiniestroEnums", 
	response = TipoPerdidaSiniestroEnum[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = TipoPerdidaSiniestroEnum[].class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public TipoPerdidaSiniestroEnum[]  loadTipoPerdidaSiniestroEnums()  {
		log.info("===============>loadEnum " );
		return TipoPerdidaSiniestroEnum.values(); 
	}
	
	
}
