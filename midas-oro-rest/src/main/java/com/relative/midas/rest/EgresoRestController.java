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
import com.relative.midas.model.TbMiEgreso;

import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/egresoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Egreso -REST CRUD")
public class EgresoRestController  extends BaseRestController implements CrudRestControllerInterface<TbMiEgreso, GenericWrapper<TbMiEgreso>> {
	

	public EgresoRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiEgreso>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiEgreso", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiEgreso> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiEgreso> loc = new GenericWrapper<>();
		TbMiEgreso a =this.mis.findEgresoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiEgreso>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiEgreso", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiEgreso> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiEgreso> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiEgreso> plw = new PaginatedListWrapper<>(pw);
		List<TbMiEgreso> actions = this.mis.findAllEgreso(pw);	
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countEgreso().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMTbMiEgresoiJoya>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiEgreso", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiEgreso> persistEntity(GenericWrapper<TbMiEgreso> wp) throws RelativeException {
		GenericWrapper<TbMiEgreso> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageEgreso(wp.getEntidad()));
		return loc;
	}
	
	@POST
	@Path("/persistEntityBatch")
	@ApiOperation(value = "GenericWrapper<TbMTbMiEgresoiJoya>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiEgreso", 
	response = GenericWrapper.class)
	public GenericWrapper<Integer> persistEntityBatch(GenericWrapper<TbMiEgreso> wp) throws RelativeException {
		GenericWrapper<Integer> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.createEgresoBatch(wp.getEntidades()));
		return loc;
	}

}
