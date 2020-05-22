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

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/bancoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "BancoRestController - REST CRUD")
public class BancoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiBanco, GenericWrapper<TbMiBanco>> {

	public BancoRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiBanco>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiBanco", response = GenericWrapper.class)
	public GenericWrapper<TbMiBanco> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiBanco> loc = new GenericWrapper<>();
		TbMiBanco a = this.mis.findBancoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiBanco>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiBanco", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiBanco> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiBanco> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiBanco> plw = new PaginatedListWrapper<>(pw);
		List<TbMiBanco> actions = this.mis.findAllBanco(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countBanco().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")

	@ApiOperation(value = "GenericWrapper<TbMiBanco>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiBanco", response = GenericWrapper.class)
	public GenericWrapper<TbMiBanco> persistEntity(GenericWrapper<TbMiBanco> wp) throws RelativeException {
		GenericWrapper<TbMiBanco> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageBanco(wp.getEntidad()));
		return loc;
	}
	
	
	@GET
	@Path("/listByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiBanco>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiBanco", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiBanco> listByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("estado") String estado,
			@QueryParam("nombre") String nombre) throws RelativeException {
		return listByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),
			StringUtils.isNotBlank(estado)?	MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null,nombre);
	}

	private PaginatedListWrapper<TbMiBanco> listByParams(PaginatedWrapper pw, EstadoMidasEnum estado,
			String nombre) throws RelativeException {
		PaginatedListWrapper<TbMiBanco> plw = new PaginatedListWrapper<>(pw);
		List<TbMiBanco> actions = this.mis.findBancoByParam(pw, estado, nombre);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countBancoByParam(estado,nombre).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
