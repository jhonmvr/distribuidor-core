package com.relative.midas.rest;

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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.ApiOperation;

@Path("/inventarioRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovInventarioRestController extends BaseRestController implements CrudRestControllerInterface<TbMiInventario, GenericWrapper<TbMiInventario>> {

	public MovInventarioRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	MidasOroService mis;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiInventario", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		TbMiInventario a =this.mis.findInventarioById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiInventario>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiInventario", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiInventario> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiInventario> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiInventario> plw = new PaginatedListWrapper<>(pw);
		List<TbMiInventario> actions = this.mis.findAllInventario(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countInventario().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiInventario", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> persistEntity(GenericWrapper<TbMiInventario> wp) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageInventario(wp.getEntidad()));
		return loc;
	}



}
