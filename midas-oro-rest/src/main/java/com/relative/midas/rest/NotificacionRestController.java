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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiNotificacion;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/notificacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "NotificacionRestController - REST CRUD")
public class NotificacionRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiNotificacion, GenericWrapper<TbMiNotificacion>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	@Inject
	CompraOroService cos;

	public NotificacionRestController() throws RelativeException {
		super();
	}

	@Override
	@GET
	@Path("/deleteEntity")
	@ApiOperation(value = "id", notes = "Metodo deleteEntity que elimina TbMiNotificacion", response = GenericWrapper.class)
	public void deleteEntity(@QueryParam("id") String id) throws RelativeException {
		this.mis.notificacionDeleteEntity(Long.valueOf(id));
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiNotificacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiNotificacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiNotificacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiNotificacion> loc = new GenericWrapper<>();
		TbMiNotificacion a = this.mis.findNotificacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiNotificacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiNotificacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiNotificacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiNotificacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiNotificacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiNotificacion> actions = this.mis.findAllNotificacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countNotificacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiNotificacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiNotificacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiNotificacion> persistEntity(GenericWrapper<TbMiNotificacion> wp)
			throws RelativeException {
		GenericWrapper<TbMiNotificacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageNotificacion(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/findByIdAgencia")
	@ApiOperation(value = "PaginatedListWrapper<TbMiNotificacion>", notes = "Metodo Get findByIdAgencia Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiNotificacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiNotificacion> findByIdAgencia(@QueryParam("page") @DefaultValue("0") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		return findByIdAgencia(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated),idAgencia);

	}

	private PaginatedListWrapper<TbMiNotificacion> findByIdAgencia(PaginatedWrapper pw,	String idAgencia) throws NumberFormatException, RelativeException {
		PaginatedListWrapper<TbMiNotificacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiNotificacion> actions = this.mis.findNotificacionByIdAgencia(pw,StringUtils.isNotBlank(idAgencia)?Long.valueOf(idAgencia):null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countNotificacionByIdAgencia(StringUtils.isNotBlank(idAgencia)?Long.valueOf(idAgencia):null).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
