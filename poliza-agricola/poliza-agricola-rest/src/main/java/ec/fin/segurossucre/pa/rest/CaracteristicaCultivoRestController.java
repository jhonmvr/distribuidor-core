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
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.CrudRestControllerInterface;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.TbPaCaracteristicaCultivo;
import ec.com.def.pa.service.SolicitudPolizaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/caracteristicaCultivoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbPaCaracteristicaCultivoRestController - REST CRUD")
public class CaracteristicaCultivoRestController extends BaseRestController implements CrudRestControllerInterface<TbPaCaracteristicaCultivo, GenericWrapper<TbPaCaracteristicaCultivo>> {


	@Inject
	SolicitudPolizaService sas;
	
	public CaracteristicaCultivoRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {
		// sin implementar
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbPaCaracteristicaCultivo> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbPaCaracteristicaCultivo", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbPaCaracteristicaCultivo> getEntity(
			@QueryParam("id") String id) throws DefException {
		GenericWrapper<TbPaCaracteristicaCultivo> loc = new GenericWrapper<>();
		TbPaCaracteristicaCultivo a =this.sas.findCaracteristicaCultivoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbPaCaracteristicaCultivo> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbPaCaracteristicaCultivo", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<TbPaCaracteristicaCultivo> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbPaCaracteristicaCultivo> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<TbPaCaracteristicaCultivo> plw = new PaginatedListWrapper<>(pw);
		List<TbPaCaracteristicaCultivo> actions = this.sas.findAllCaracteristicaCultivo(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countCaracteristicaCultivo().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbPaCaracteristicaCultivo>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaCaracteristicaCultivo", response = GenericWrapper.class)
	public GenericWrapper<TbPaCaracteristicaCultivo> persistEntity(GenericWrapper<TbPaCaracteristicaCultivo> wp) throws DefException {
		GenericWrapper<TbPaCaracteristicaCultivo> loc = new GenericWrapper<>();
		loc.setEntidad(this.sas.manageCaracteristicaCultivo(wp.getEntidad()));
		return loc;
	}
	
	

}
