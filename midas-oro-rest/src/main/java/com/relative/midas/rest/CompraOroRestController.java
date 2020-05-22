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
import com.relative.core.util.main.BaseWrapper;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiCompraOro;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/compraOroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "BancoRestController - REST CRUD")
public class CompraOroRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCompraOro, GenericWrapper<TbMiCompraOro>> {

	public CompraOroRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiCompraOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCompraOro", response = GenericWrapper.class)
	public GenericWrapper<TbMiCompraOro> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCompraOro> loc = new GenericWrapper<>();
		TbMiCompraOro a = this.mis.findCompraOroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCompraOro>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCompraOro", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiCompraOro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiCompraOro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCompraOro> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCompraOro> actions = this.mis.findAllCompraOro(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCompraOro().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")

	@ApiOperation(value = "GenericWrapper<TbMiCompraOro>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCompraOro", response = GenericWrapper.class)
	public GenericWrapper<TbMiCompraOro> persistEntity(GenericWrapper<TbMiCompraOro> wp) throws RelativeException {
		GenericWrapper<TbMiCompraOro> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCompraOro(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/listByIdFunda")
	@ApiOperation(value = "BaseWrapper<TbMiCompraOro>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCompraOro", response = BaseWrapper.class)

	public BaseWrapper<TbMiCompraOro> listByIdFunda(@QueryParam("idFunda")String idFunda) throws RelativeException {
		BaseWrapper<TbMiCompraOro> loc = new BaseWrapper<TbMiCompraOro>();
		loc.setEntidades( mis.listByIdFunda(new Long(idFunda) )  );
		return loc;
	}

}
