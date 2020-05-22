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
import org.jfree.util.Log;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/inventarioRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "InventarioRestController - REST CRUD")
public class InventarioRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiInventario, GenericWrapper<TbMiInventario>> {

	public InventarioRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	MidasOroService mis;

	@Inject
	MovimientoOroService mos;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiInventario", response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		TbMiInventario a = this.mis.findInventarioById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiInventario>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiInventario", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiInventario> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiInventario> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiInventario> plw = new PaginatedListWrapper<>(pw);
		List<TbMiInventario> actions = this.mis.findAllInventario(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countInventario().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiInventario", response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> persistEntity(GenericWrapper<TbMiInventario> wp) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageInventario(wp.getEntidad()));
		return loc;
	}

	@POST
	@Path("/movimientoInventarioList")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo movimiento de inventario Retorna wrapper de entidad encontrada en TbMiInventario", response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> movimientoInventarioList(List<Long> idJoya,
			@QueryParam("idUbicacion") String idUbicacion, @QueryParam("usuario") String usuario,
			@QueryParam("estado") String estado, @QueryParam("proceso") String proceso) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		List<TbMiInventario> a = this.mos.movimientoInventario(idJoya, Long.parseLong(idUbicacion), usuario,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estado)
						: EstadoJoyaEnum.EXISTENCIA,
				StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso) : null);
		loc.setEntidades(a);
		return loc;
	}

	@GET
	@Path("/movimientoInventario")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo movimiento de inventario Retorna wrapper de entidades encontradas en TbMiInventario", response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> movimientoInventario(@QueryParam("idJoya") String idJoya,
			@QueryParam("idUbicacion") String idUbicacion, @QueryParam("usuario") String usuario,
			@QueryParam("estado") String estado, @QueryParam("proceso") String proceso) throws RelativeException {
		Log.info("Id Joya: " + idJoya);
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		TbMiInventario a = this.mos.movimientoInventario(Long.parseLong(idJoya), Long.parseLong(idUbicacion), usuario,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estado)
						: EstadoJoyaEnum.EXISTENCIA,
				StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso) : null);
		loc.setEntidad(a);
		return loc;
	}

	/**
	 * Metodo que cambia el estado del inventario pertenecientes a una joya
	 * 
	 * @param idJoya
	 * @param estado
	 * @param usuario
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/changeEstadoInventarioByIdJoya")
	@ApiOperation(value = "GenericWrapper<TbMiInventario>", notes = "Metodo que cambia el estado del inventario pertenecientes a una joya", response = GenericWrapper.class)
	public GenericWrapper<TbMiInventario> changeEstadoById(@QueryParam("idJoya") String idJoya,
			@QueryParam("estado") String estado, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbMiInventario> loc = new GenericWrapper<>();
		if (StringUtils.isNotBlank(idJoya) && StringUtils.isNotBlank(estado) && StringUtils.isNotBlank(usuario)) {
			loc.setEntidades(this.mis.changeEstadoInventarioByIdJoya(Long.valueOf(idJoya),
					MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estado), usuario));
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "Informacion insificiente");
		}
		return loc;
	}

}
