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
import ec.com.def.pa.model.Ramoplan;
import ec.com.def.pa.model.RamoplanPK;
import ec.com.def.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/ramoplanRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " RamoplanRestController - REST CRUD")
public class RamoplanRestController extends BaseRestController implements CrudRestControllerInterface<Ramoplan, GenericWrapper<Ramoplan>> {


	@Inject
	PolizaAgricolaService sas;
	
	public RamoplanRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {
		// 
		
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Ramoplan> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Ramoplan", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<Ramoplan> getEntity(
			@QueryParam("ramoId") String ramoId,
			@QueryParam("ramoplanId") String ramoplanid) throws DefException {
		GenericWrapper<Ramoplan> loc = new GenericWrapper<>();
		RamoplanPK c =new RamoplanPK();
		c.setRamoid(ramoId);
		c.setRamoplanid(ramoplanid);
		Ramoplan a =this.sas.findRamoplanById(c);
		loc.setEntidad(a);
		return loc;
	}
	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "GenericWrapper<Ramoplan> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en Ramoplan", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<Ramoplan> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Ramoplan> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<Ramoplan> plw = new PaginatedListWrapper<>(pw);
		List<Ramoplan> actions = this.sas.findAllRamoPlan(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countRamoPlan().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Ramoplan> persistEntity(GenericWrapper<Ramoplan> arg0) throws DefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<Ramoplan> getEntity(String arg0) throws DefException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
