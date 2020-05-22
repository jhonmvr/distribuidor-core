package com.relative.midas.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/joyaLoteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "JoyaLoteRestController - REST CRUD")
public class JoyaLoteRestController extends BaseRestController implements CrudRestControllerInterface<TbMiJoyaLote, GenericWrapper<TbMiJoyaLote>> {
	
	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	@Inject
	CompraOroService cos;
	
	public JoyaLoteRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(
	value = "GenericWrapper<TbMiJoyaLote>", 
	notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiJoyaLote",
	response = GenericWrapper.class) 
	public GenericWrapper<TbMiJoyaLote> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiJoyaLote> loc = new GenericWrapper<>();
		TbMiJoyaLote a =this.mis.findJoyaLoteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
	value = "PaginatedListWrapper<TbMiJoyaLote>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiJoyaLote", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoyaLote> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page)*Integer.valueOf(pageSize);
		return findAll(new PaginatedWrapper(
				firstItem, 
				Integer.valueOf(pageSize), 
				sortFields,sortDirections, 
				isPaginated));
		
	}
	
	private PaginatedListWrapper<TbMiJoyaLote> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiJoyaLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiJoyaLote> actions = this.mis.findAllJoyaLote(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countJoyaLote().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaLote>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiJoyaLote", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiJoyaLote> persistEntity(GenericWrapper<TbMiJoyaLote> wp) throws RelativeException {
		GenericWrapper<TbMiJoyaLote> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageJoyaLote(wp.getEntidad()));
		return loc;
	}
	
	@DELETE
	@Path("/deleteByJoya")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaLote>",
	notes = "Elimina todas las relaciones de joya lote por joya", 
	response = GenericWrapper.class)
	public void deleteByJoya(@QueryParam("idJoya") String idJoya) throws RelativeException {
		if(StringUtils.isBlank(idJoya)) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, "No pude ser nulo el id joya");
		}
		this.mis.deleteJoyaLoteByJoya(Long.valueOf(idJoya));
	}
	
	@GET
	@Path("/findByIdLote")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoyaLote>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiJoyaLote", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoyaLote> findByIdLote(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idLote") String idLote
			) throws RelativeException {
		return findByIdLote(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),idLote);
	}

	private PaginatedListWrapper<TbMiJoyaLote> findByIdLote(PaginatedWrapper pw, String idLote) throws  RelativeException {
		try {
			PaginatedListWrapper<TbMiJoyaLote> plw = new PaginatedListWrapper<>(pw);
			List<TbMiJoyaLote> actions = this.mis.findJoyaLoteByIdLote(pw, Long.valueOf(idLote));
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(this.mis.countJoyaLoteByIdLote(Long.valueOf(idLote)).intValue());
				plw.setList(actions);
			}
			return plw;
		}catch (RelativeException e) {
			throw e;
		}
		catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR FORMATEAR A NUMERICO:" +idLote);
		}
	}

	
}
