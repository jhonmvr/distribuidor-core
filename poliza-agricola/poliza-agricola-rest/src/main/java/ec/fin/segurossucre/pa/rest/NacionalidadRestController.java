package ec.fin.segurossucre.pa.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.PaginatedListWrapper;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.Nacionalidad;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/nacionalidadRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " NacionalidadRestController - REST CRUD")
public class NacionalidadRestController extends BaseRestController implements CrudRestControllerInterface<Nacionalidad, GenericWrapper<Nacionalidad>> {


	@Inject
	PolizaAgricolaService sas;
	
	public NacionalidadRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// 
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Nacionalidad> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Nacionalidad", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Nacionalidad> getEntity(
			@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<Nacionalidad> loc = new GenericWrapper<>();
		Nacionalidad a =this.sas.findNacionalidadById(String.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Nacionalidad> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en Nacionalidad", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<Nacionalidad> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("nacionalidadid") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Nacionalidad> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<Nacionalidad> plw = new PaginatedListWrapper<>(pw);
		List<Nacionalidad> actions = this.sas.findAllNacionalidad(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countNacionalidad().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Nacionalidad> persistEntity(GenericWrapper<Nacionalidad> arg0) throws SegSucreException {
		// no persisted
		return null;
	}
	
	

}
