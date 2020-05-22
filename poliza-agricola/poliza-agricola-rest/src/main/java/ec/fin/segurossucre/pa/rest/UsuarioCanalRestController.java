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
import ec.com.def.pa.model.TbSaUsuarioCanal;
import ec.com.def.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/usuarioCanalRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " UsuarioCanalRestController - REST CRUD")
public class UsuarioCanalRestController extends BaseRestController implements CrudRestControllerInterface<TbSaUsuarioCanal, GenericWrapper<TbSaUsuarioCanal>>{

	public UsuarioCanalRestController() throws DefException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	PolizaAgricolaService sas;
	
	@Override
	public void deleteEntity(String arg0) throws DefException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbSaUsuarioCanal>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbSaUsuarioCanal", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbSaUsuarioCanal> getEntity(
			@QueryParam("id") String id) throws DefException {
		GenericWrapper<TbSaUsuarioCanal> loc = new GenericWrapper<>();
		TbSaUsuarioCanal a =this.sas.findUsuarioCanalById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbSaUsuarioCanal>", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbSaUsuarioCanal", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<TbSaUsuarioCanal> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbSaUsuarioCanal> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<TbSaUsuarioCanal> plw = new PaginatedListWrapper<>(pw);
		List<TbSaUsuarioCanal> actions = this.sas.findAllUsuarioCanal(pw);
		if (actions != null && !actions.isEmpty()) {
			//actions.forEach(a -> a.setClue( PasswordHash.unHashPassword(a.getClue())  ) );
			//actions.forEach(a -> a.setPassword( PasswordHash.unHashPassword(a.getPassword())  ) );
			plw.setTotalResults(this.sas.countUsuarioCanal().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@POST
	@Path("/persistEntity")
	@Override
	@ApiOperation(value = "GenericWrapper<TbSaUsuarioCanal>", notes = "Metodo persistEntity Retorna wrapper de entidades encontradas en TbSaUsuarioCanal", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbSaUsuarioCanal> persistEntity(GenericWrapper<TbSaUsuarioCanal> ucw)
			throws DefException {
		try {
			GenericWrapper<TbSaUsuarioCanal > gw= new GenericWrapper<>();
			gw.setEntidad(this.sas.manageUsuarioCanal( ucw.getEntidad() ));
			return gw;
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController persistEntity, " + e.getMessage());
		}
	}
	
	@GET
	@Path("/getEntityByNombre")
	@ApiOperation(value = "GenericWrapper<TbSaUsuarioCanal>", notes = "Metodo getEntityByNombre Retorna wrapper de entidades encontradas en TbSaUsuarioCanal", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbSaUsuarioCanal> getEntityByNombre(
			@QueryParam("nombreUsuario") String nombreUsuario) throws DefException {
		try {
			GenericWrapper<TbSaUsuarioCanal> loc = new GenericWrapper<>();
			TbSaUsuarioCanal a =this.sas.findUsuarioCanalByNombreUsuario(nombreUsuario);
			loc.setEntidad(a);
			return loc;
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}

}
