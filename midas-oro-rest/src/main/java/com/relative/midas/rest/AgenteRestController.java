package com.relative.midas.rest;

import java.util.List;
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

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroConstantes;

import io.swagger.annotations.ApiOperation;

@Path("/agenteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgenteRestController extends BaseRestController implements CrudRestControllerInterface<TbMiAgente, GenericWrapper<TbMiAgente>> {
	
	@Inject
	Logger log;

	@Inject
	MidasOroService mis;
	
	public AgenteRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiAgente>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAgente", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgente> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiAgente> loc = new GenericWrapper<>();
		TbMiAgente a =this.mis.findAgenteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAgente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgente", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiAgente> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiAgente> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiAgente> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAgente> actions = this.mis.findAllAgente(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAgente().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")

	@ApiOperation(value = "GenericWrapper<TbMiAgente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAgente", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgente> persistEntity(GenericWrapper<TbMiAgente> wp) throws RelativeException {
		GenericWrapper<TbMiAgente> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageAgente(wp.getEntidad()));
		return loc;
	}

	
	@GET
	@Path("/agenteSinAsignar")
	@ApiOperation(value = "GenericWrapper<TbMiAgente>", notes = "Metodo agenteSinAsignar Retorna wrapper de entidades encontradas en TbMiAgente", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgente> agenteSinAsignar() throws RelativeException {
		GenericWrapper<TbMiAgente> loc = new GenericWrapper<>();
		List<TbMiAgente> a =this.mis.findAgenteSinAsignar();
		loc.setEntidades(a);
		return loc;
	}
	
	@GET
	@Path("/agenteByUsername")
	@ApiOperation(value = "GenericWrapper<TbMiAgente>",
	notes = "Busca agente por nombre de usuario y devuelve el primero que encuentre", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgente> agenteByUsername(
			@QueryParam("username") String username) throws RelativeException {
		GenericWrapper<TbMiAgente> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(username)) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Username no puede ser nulo");
		}
		loc.setEntidad(this.mis.findAgenteByUsername(username));
		return loc;
	}
	
	@GET
	@Path("/agenteOrSupervisorByUsername")
	@ApiOperation(value = "GenericWrapper<TbMiAgente>",
	notes = "Busca agente por nombre de usuario y devuelve el primero que encuentre", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgente> agenteOrSupervisorByUsername(
			@QueryParam("username") String username,
			@QueryParam("rol") String rol) throws RelativeException {
		log.info( "llega a agenteOrSupervisorByUsername " + rol);
		GenericWrapper<TbMiAgente> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(username)) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Username no puede ser nulo");
		} else {
			rol = rol.replaceAll(" ", "_");
			log.info( "nuevo rol " + rol);
		}
		if(!rol.equalsIgnoreCase(MidasOroConstantes.ADMINISTRADOR) && !rol.equalsIgnoreCase(MidasOroConstantes.GERENTE_COMERCIAL )
				&& !rol.equalsIgnoreCase(MidasOroConstantes.GERENTE_GENERAL ) ) {
			log.info( "llega a agenteOrSupervisorByUsername entra a if " + rol);
			loc.setEntidad(this.mis.findAgenteOrSupervisorByUsername(username));
		}		
		return loc;
	}
	
	

	@GET
	@Path("/agenteByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAgente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgente", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiAgente> agenteByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("nombre") String nombre,
			@QueryParam("apellido") String apellido,
			@QueryParam("identificacion") String identificacion
			) throws RelativeException {
		return agenteByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),nombre,apellido,identificacion);
	}

	private PaginatedListWrapper<TbMiAgente> agenteByParams(PaginatedWrapper pw, String nombre,
			String apellido, String identificacion) throws RelativeException {
		PaginatedListWrapper<TbMiAgente> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAgente> actions = this.mis.findAgenteByParams(pw,nombre,apellido,identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAgenteByParams(nombre,apellido,identificacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
}
