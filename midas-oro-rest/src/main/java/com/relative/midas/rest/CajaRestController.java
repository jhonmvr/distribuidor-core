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
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/cajaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "CajaRestController - REST CRUD")
public class CajaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCaja, GenericWrapper<TbMiCaja>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	public CajaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCaja> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCaja> loc = new GenericWrapper<>();
		TbMiCaja a = this.mis.findCajaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCaja>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiCaja> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiCaja> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCaja> actions = this.mis.findAllCaja(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCaja().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCaja> persistEntity(GenericWrapper<TbMiCaja> wp) throws RelativeException {
		GenericWrapper<TbMiCaja> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCaja(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/findByAgencia")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>",
	notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCaja> findByAgencia(@QueryParam("idAgencia") String idAgencia) throws RelativeException {
		if(StringUtils.isBlank(idAgencia)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id de la agencia no puede ser nulo");
		}
		GenericWrapper<TbMiCaja> loc = new GenericWrapper<>();
		TbMiCaja a = this.mis.findCajaByAgencia(Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;
	}


}
