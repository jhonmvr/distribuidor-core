package ec.com.def.pa.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.CrudRestControllerInterface;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.TbPaCanalSecuencia;
import ec.com.def.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/canalSecuenciaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbPaCanalSecuenciaRestController - REST CRUD")
public class CanalSecuenciaRestController extends BaseRestController implements CrudRestControllerInterface<TbPaCanalSecuencia, GenericWrapper<TbPaCanalSecuencia>> {

	@Inject
	PolizaAgricolaService sas;
	

	public CanalSecuenciaRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbSaSiniestro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbPaCanalSecuencia> getEntity(
			@QueryParam("id") String id) throws DefException {
		GenericWrapper<TbPaCanalSecuencia> loc = new GenericWrapper<>();
		TbPaCanalSecuencia a =this.sas.findCanalSecuenciaById(Long.valueOf(id));
		loc.setEntidad(a);
		
		return loc;
	}
	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbSaSiniestro>", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<TbPaCanalSecuencia> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbPaCanalSecuencia> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<TbPaCanalSecuencia> plw = new PaginatedListWrapper<>(pw);
		List<TbPaCanalSecuencia> actions = this.sas.findAllCanalSecuencia(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.sas.countCanalSecuencia().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbPaCanalSecuencia>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaCanalSecuencia", response = GenericWrapper.class)
	public GenericWrapper<TbPaCanalSecuencia> persistEntity(GenericWrapper<TbPaCanalSecuencia> wp) throws DefException {
		if(wp == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"ENTIDAD NULA");
		}
		GenericWrapper<TbPaCanalSecuencia> loc = new GenericWrapper<>();
		loc.setEntidad(sas.manageCanalSecuencia(wp.getEntidad()));
		return loc;
	}

	


}
