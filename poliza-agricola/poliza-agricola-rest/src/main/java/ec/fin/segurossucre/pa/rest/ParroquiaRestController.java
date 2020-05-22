package ec.com.def.pa.rest;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.CrudRestControllerInterface;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.Parroquia;
import ec.com.def.pa.model.ParroquiaPK;
import ec.com.def.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/parroquiaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ParroquiaRestController - REST CRUD")
public class ParroquiaRestController extends BaseRestController implements CrudRestControllerInterface<Parroquia, GenericWrapper<Parroquia>>{
	@Inject
	PolizaAgricolaService sas;
	
	public ParroquiaRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {
		// TODO Auto-generated method stub
		
	}

	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Parroquia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Parroquia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<Parroquia> getEntity(
			@QueryParam("provinciaId") String provinciaId,
			@QueryParam("cantonId") String cantonId,
			@QueryParam("parroquiaId") String parroquiaId) throws DefException {
		GenericWrapper<Parroquia> loc = new GenericWrapper<>();
		ParroquiaPK c=new ParroquiaPK();
		c.setParroquiaid(parroquiaId);
		c.setCantonid(cantonId);
		c.setProvinciaid(provinciaId);
		Parroquia a =this.sas.findParroquiaById(c);
		loc.setEntidad(a);
		return loc;
	}

	
	

	@GET
	@Path("/listAllEntitiesByProvinciaCanton")
	@ApiOperation(value = "GenericWrapper<Parroquia> ", notes = "Metodo listAllEntitiesByProvinciaCanton Retorna wrapper de entidades encontradas en Parroquia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<Parroquia> listAllEntitiesByProvinciaCanton(
			@QueryParam("provinciaId") String provinciaId,
			@QueryParam("cantonId") String cantonId,
			@QueryParam("order") @DefaultValue("asc") String order) throws DefException {
		GenericWrapper<Parroquia> loc = new GenericWrapper<>();
		loc.setEntidades( this.sas.findAllParroquiaByProvinciaCanton(provinciaId, cantonId, order) );
		return loc;
	}

	@Override
	public GenericWrapper<Parroquia> persistEntity(GenericWrapper<Parroquia> arg0) throws DefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<Parroquia> getEntity(String arg0) throws DefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<Parroquia> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws DefException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
