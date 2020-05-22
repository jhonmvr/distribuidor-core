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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiCuenta;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/cuentaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "CuentaRestController - REST CRUD")
public class CuentaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCuenta, GenericWrapper<TbMiCuenta>> {
	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	

	
	public CuentaRestController() throws RelativeException {
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
			value = "GenericWrapper<TbMiCuenta>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCuenta",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiCuenta> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCuenta> loc = new GenericWrapper<>();
		TbMiCuenta a =this.mis.findCuentaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiCuenta>", 
			notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCuenta", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiCuenta> listAllEntities(
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
	
	private PaginatedListWrapper<TbMiCuenta> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCuenta> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCuenta> actions = this.mis.findAllCuenta(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCuenta().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCuenta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCuenta", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiCuenta> persistEntity(GenericWrapper<TbMiCuenta> wp) throws RelativeException {
		GenericWrapper<TbMiCuenta> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCuenta(wp.getEntidad()));
		return loc;
	}
	
}
