package ec.com.def.pa.rest;

import java.util.List;

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
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.CrudRestControllerInterface;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.Condicionpredio;
import ec.com.def.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/condicionpredioRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " CondicionpredioRestController - REST CRUD")
public class CondicionpredioRestController extends BaseRestController implements CrudRestControllerInterface<Condicionpredio, GenericWrapper<Condicionpredio>> {


	@Inject
	PolizaAgricolaService sas;
	
	public CondicionpredioRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {
		// 
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Condicionpredio> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Condicionpredio", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<Condicionpredio> getEntity(
			@QueryParam("condicionpredioid") String condicionpredioid) throws DefException {
		GenericWrapper<Condicionpredio> loc = new GenericWrapper<>();
		Condicionpredio a =this.sas.findCondicionpredioById(Long.valueOf(condicionpredioid));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Condicionpredio> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en Condicionpredio", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<Condicionpredio> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("condicionpredioid") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Condicionpredio> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<Condicionpredio> plw = new PaginatedListWrapper<>(pw);
		List<Condicionpredio> actions = this.sas.findAllCondicionpredio(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.sas.countCondicionpredio().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Condicionpredio> persistEntity(GenericWrapper<Condicionpredio> arg0) throws DefException {
		// no persisted
		return null;
	}
	
	

}