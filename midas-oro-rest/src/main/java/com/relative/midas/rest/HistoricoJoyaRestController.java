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
import com.relative.midas.model.TbMiHistoricoJoya;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.ApiOperation;

@Path("/historicoJoyaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HistoricoJoyaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiHistoricoJoya, GenericWrapper<TbMiHistoricoJoya>> {

	public HistoricoJoyaRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiHistoricoJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiHistoricoJoya", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiHistoricoJoya> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiHistoricoJoya> loc = new GenericWrapper<>();
		TbMiHistoricoJoya a =this.mis.findHistoricoJoyaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiHistoricoJoya>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiHistoricoJoya", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiHistoricoJoya> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiHistoricoJoya> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiHistoricoJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiHistoricoJoya> actions = this.mis.findAllHistoricoJoya(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countHistoricoJoya().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiHistoricoJoya>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiHistoricoJoya", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiHistoricoJoya> persistEntity(GenericWrapper<TbMiHistoricoJoya> wp) throws RelativeException {
		GenericWrapper<TbMiHistoricoJoya> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageHistoricoJoya(wp.getEntidad()));
		return loc;
	}

}
