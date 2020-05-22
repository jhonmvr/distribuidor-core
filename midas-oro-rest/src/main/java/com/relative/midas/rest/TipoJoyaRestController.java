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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.model.TbMiTipoJoya;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/tipoJoyaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TipoJoyaRestController - REST CRUD")
public class TipoJoyaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiTipoJoya, GenericWrapper<TbMiTipoJoya>> {

	public TipoJoyaRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiTipoJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiTipoJoya", response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoJoya> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiTipoJoya> loc = new GenericWrapper<>();
		TbMiTipoJoya a = this.mis.findTipoJoyaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiTipoJoya>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiTipoJoya", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiTipoJoya> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiTipoJoya> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiTipoJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoJoya> actions = this.mis.findAllTipoJoya(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countTipoJoya().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMTbMiTipoJoyaiJoya>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiTipoJoya", response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoJoya> persistEntity(GenericWrapper<TbMiTipoJoya> wp) throws RelativeException {
		GenericWrapper<TbMiTipoJoya> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageTipoJoya(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/getEntityByEstado")
	@ApiOperation(value = "GenericWrapper<TbMiTipoJoya>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiTipoJoya> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("estado") String estado)
			throws RelativeException {
		return findAllTipoJoyaByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), estado);
	}

	private PaginatedListWrapper<TbMiTipoJoya> findAllTipoJoyaByParams(PaginatedWrapper pw, String estado)
			throws RelativeException {
		PaginatedListWrapper<TbMiTipoJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoJoya> actions = null;

		actions = this.mis.findTipoJoyaByAll(pw,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countfindTipoJoyaByAll(
					StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado)
							: null)
					.intValue());
			plw.setList(actions);
		}

		return plw;
	}
	@GET
	@Path("/findAllTipoJoyaByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiTipoJoya>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiTipoJoya", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiTipoJoya> findAllTipoJoyaByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("detalle") String detalle,
			@QueryParam("estado") String estado) throws RelativeException {
		return findAllTipoJoyaByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),StringUtils.isNotBlank(detalle)?detalle:null,
			StringUtils.isNotBlank(estado)?	MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null);
	}
	
	
	
	private PaginatedListWrapper<TbMiTipoJoya> findAllTipoJoyaByParams(PaginatedWrapper pw,String detalle ,EstadoMidasEnum estado)
			throws RelativeException {
		PaginatedListWrapper<TbMiTipoJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoJoya> actions = null;

		actions = this.mis.findTipoJoyaByDetalleEstado(pw,detalle,estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countfindTipoJoyaByDetalleEstado(detalle,estado)
					.intValue());
			plw.setList(actions);
		}

		return plw;
	}

	
}
