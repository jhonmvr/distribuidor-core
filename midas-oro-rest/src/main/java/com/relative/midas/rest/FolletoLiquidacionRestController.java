package com.relative.midas.rest;

import java.util.Date;
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
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/folletoLiquidacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "FolletoLiquidacionRestController - REST CRUD")
public class FolletoLiquidacionRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiFolletoLiquidacion, GenericWrapper<TbMiFolletoLiquidacion>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	public FolletoLiquidacionRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFolletoLiquidacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFolletoLiquidacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiFolletoLiquidacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiFolletoLiquidacion> loc = new GenericWrapper<>();
		TbMiFolletoLiquidacion a = this.mis.findFolletoLiquidacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFolletoLiquidacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiFolletoLiquidacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFolletoLiquidacion> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiFolletoLiquidacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiFolletoLiquidacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFolletoLiquidacion> actions = this.mis.findAllFolletoLiquidacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFolletoLiquidacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFolletoLiquidacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiFolletoLiquidacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiFolletoLiquidacion> persistEntity(GenericWrapper<TbMiFolletoLiquidacion> wp)
			throws RelativeException {
		GenericWrapper<TbMiFolletoLiquidacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageFolletoLiquidacion(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/generarLiquidaciones")
	@ApiOperation(value = "GenericWrapper<TbMiFolletoLiquidacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFolletoLiquidacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiFolletoLiquidacion> generarLiquidaciones(@QueryParam("codigo") String codigo,
			@QueryParam("inicio") String inicio, @QueryParam("fin") String fin, @QueryParam("vigencia") String vigencia,
			@QueryParam("nombre") String nombre, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbMiFolletoLiquidacion> loc = new GenericWrapper<>();

		TbMiFolletoLiquidacion a;
		try {
			a = this.mis.generarLiquidaciones(codigo, new Long(inicio), new Long(fin), vigencia, nombre, usuario);
		} catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Codigo inicio - Codigo fin");
		}
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/findByIdAgencia")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFolletoLiquidacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiFolletoLiquidacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFolletoLiquidacion> findByIdAgencia(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		return findByIdAgencia(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), idAgencia);
	}

	private PaginatedListWrapper<TbMiFolletoLiquidacion> findByIdAgencia(PaginatedWrapper pw, String idAgencia)
			throws RelativeException {
		try {
			PaginatedListWrapper<TbMiFolletoLiquidacion> plw = new PaginatedListWrapper<>(pw);
			List<TbMiFolletoLiquidacion> actions = this.mis.findFolletoLiquidacionByIdAgencia(pw, new Long(idAgencia));
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(this.mis.countFolletoLiquidacionByIdAgencia(new Long(idAgencia)).intValue());
				plw.setList(actions);
			}
			return plw;
		} catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
		}
	}

	@GET
	@Path("/sinAsignar")
	@ApiOperation(value = "GenericWrapper<TbMiFolletoLiquidacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFolletoLiquidacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiFolletoLiquidacion> sinAsignar() throws RelativeException {
		GenericWrapper<TbMiFolletoLiquidacion> loc = new GenericWrapper<>();
		List<TbMiFolletoLiquidacion> a;
		a = this.mis.FolletoLiquidacionSinAsginar();
		loc.setEntidades(a);
		return loc;
	}
	
	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFolletoLiquidacion>",
	notes = "Busca folletos por parametros", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFolletoLiquidacion> findByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idAgencia") String idAgencia,
			@QueryParam("nombre") String nombre,
			@QueryParam("fechaVigenciaDesde") String fechaVigenciaDesde,
			@QueryParam("fechaVigenciaHasta") String fechaVigenciaHasta
			) throws RelativeException {
		return findByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), 
				sortFields, sortDirections, isPaginated), 
				StringUtils.isNotBlank(idAgencia)?Long.valueOf(idAgencia):null,
				StringUtils.isNotBlank(nombre)?nombre:null,
				StringUtils.isNotBlank(fechaVigenciaDesde)?MidasOroUtil.formatSringToDate(fechaVigenciaDesde, "MM/dd/yyyy, HH:mm:ss aaa"):null,
				StringUtils.isNotBlank(fechaVigenciaHasta)?MidasOroUtil.formatSringToDate(fechaVigenciaHasta, "MM/dd/yyyy, HH:mm:ss aaa"):null);
	}
	
	private PaginatedListWrapper<TbMiFolletoLiquidacion> findByParams(PaginatedWrapper pw, 
			Long idAgencia,String nombre,Date fechaVigenciaDesde, Date fechaVigenciaHasta) throws RelativeException {
		PaginatedListWrapper<TbMiFolletoLiquidacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFolletoLiquidacion> actions = this.mis.findFolletoLiquidacionByParams(pw, 
				idAgencia, nombre, fechaVigenciaDesde, fechaVigenciaHasta);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFolletoLiquidacionByParams(idAgencia, 
					nombre, fechaVigenciaDesde, fechaVigenciaHasta).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST
	@Path("/asignarFolleto")
	@ApiOperation(value = "GenericWrapper<TbMiFolletoLiquidacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de TbMiFolletoLiquidacion", response = GenericWrapper.class)
	public GenericWrapper<TbMiFolletoLiquidacion> asignarFolleto(
			@QueryParam("idAgencia") String idAgencia,
			GenericWrapper<TbMiFolletoLiquidacion> wp)
			throws RelativeException {
		GenericWrapper<TbMiFolletoLiquidacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.asignarFolletoLiquidacion( wp.getEntidad(), StringUtils.isNotBlank(idAgencia)?new Long(idAgencia):null ) );
		return loc;
	}

}
