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
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/contratoCalculoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ContratoCalculoRestController - REST CRUD")
public class ContratoCalculoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiContratoCalculo, GenericWrapper<TbMiContratoCalculo>> {

	public ContratoCalculoRestController() throws RelativeException {
		super();
	}

	@Inject
	Logger log;
	@Inject
	MidasOroService mis;
	

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiContratoCalculo>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContratoCalculo", response = GenericWrapper.class)
	public GenericWrapper<TbMiContratoCalculo> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiContratoCalculo> loc = new GenericWrapper<>();
		TbMiContratoCalculo a = this.mis.findContratoCalculoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiContratoCalculo>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiContratoCalculo", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiContratoCalculo> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiContratoCalculo> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiContratoCalculo> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContratoCalculo> actions = this.mis.findAllContratoCalculo(null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContratoCalculo().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiContratoCalculo>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiContratoCalculo", response = GenericWrapper.class)
	public GenericWrapper<TbMiContratoCalculo> persistEntity(GenericWrapper<TbMiContratoCalculo> wp) throws RelativeException {
		GenericWrapper<TbMiContratoCalculo> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageContratoCalculo(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/findByIdAprobarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContratoCalculo>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiContratoCalculo", response = GenericWrapper.class)
	public GenericWrapper<TbMiContratoCalculo> findByIdAprobarContrato(@QueryParam("idAprobarContrato") String idAprobarContrato)  throws RelativeException {
		GenericWrapper<TbMiContratoCalculo> loc = new GenericWrapper<TbMiContratoCalculo>();
		loc.setEntidades(this.mis.findContratoCalculoByIdAprobarContrato(new Long(idAprobarContrato)));
		return loc;
	}
}
