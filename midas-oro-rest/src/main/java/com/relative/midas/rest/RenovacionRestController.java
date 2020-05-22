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
import com.relative.midas.model.TbMiRenovacion;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.ApiOperation;

@Path("/renovacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RenovacionRestController extends BaseRestController implements CrudRestControllerInterface<TbMiRenovacion, GenericWrapper<TbMiRenovacion>> {

	public RenovacionRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiRenovacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiRenovacion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiRenovacion> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiRenovacion> loc = new GenericWrapper<>();
		TbMiRenovacion a =this.mis.findRenovacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiRenovacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiRenovacion", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiRenovacion> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiRenovacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiRenovacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiRenovacion> actions = this.mis.findAllRenovacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countRenovacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiRenovacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiRenovacion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiRenovacion> persistEntity(GenericWrapper<TbMiRenovacion> wp) throws RelativeException {
		GenericWrapper<TbMiRenovacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageRenovacion(wp.getEntidad()));
		return loc;
	}

}
