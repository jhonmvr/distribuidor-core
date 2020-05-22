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
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.JoyaVentaVitrina;
import com.relative.midas.wrapper.JoyaWrapper;
import com.relative.midas.wrapper.MovimientoInventarioWrapper;
import com.relative.midas.wrapper.RetazarJoya;
import com.relative.midas.wrapper.VentaJoyaWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/joyaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " JoyaRestController - REST CRUD")
public class JoyaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiJoya, GenericWrapper<TbMiJoya>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	public JoyaRestController() throws RelativeException {
		super();
		// Contructor
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiJoya", response = GenericWrapper.class)
	public GenericWrapper<TbMiJoya> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiJoya> loc = new GenericWrapper<>();
		TbMiJoya a = this.mis.findJoyaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoya>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiJoya", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiJoya> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiJoya> actions = this.mis.findAllJoya(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countJoya().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiJoya", response = GenericWrapper.class)
	public GenericWrapper<TbMiJoya> persistEntity(GenericWrapper<TbMiJoya> wp) throws RelativeException {
		GenericWrapper<TbMiJoya> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageJoya(wp.getEntidad()));
		return loc;
	}

	/**
	 * Metodo que retorna joyas por codigo contrato
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param codigo
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/getJoyasByCodigoContrato")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoya>", notes = "Metodo que retorna joyas por codigo contrato", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> getJoyasByCodigoContrato(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("codigo") String codigo)
			throws RelativeException {
		if (StringUtils.isBlank(codigo)) {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "El codigo o estado no puede estar vacio");
		}
		return getJoyasByCodigoContrato(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), codigo);
	}

	/**
	 * Metodo que retorna joyas por codigo contrato
	 * 
	 * @param pw
	 * @param codigo
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	private PaginatedListWrapper<TbMiJoya> getJoyasByCodigoContrato(PaginatedWrapper pw, String codigo)
			throws RelativeException {
		PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiJoya> actions = this.mis.findJoyaByCodigoContrato(pw, codigo);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countJoyaByCodigoContrato(codigo));
			plw.setList(actions);
		}
		return plw;
	}

	/**
	 * Metodo que retorna una clase personalizada con los datos para llenar el
	 * formulario Retazar y Trasladar
	 * 
	 * @param codigoJoya
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/getJoyaByCodigoRetazar")
	@ApiOperation(value = "GenericWrapper<RetazarJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en RetazarJoya", response = GenericWrapper.class)
	public GenericWrapper<RetazarJoya> getJoyaByCodigoRetazar(@QueryParam("codigoJoya") String codigoJoya)
			throws RelativeException {
		GenericWrapper<RetazarJoya> loc = new GenericWrapper<>();
		RetazarJoya a = this.mis.joyaByCodigoRetazar(codigoJoya);
		loc.setEntidad(a);
		return loc;
	}

	/**
	 * Metodo para retazar Joya
	 * 
	 * @param codigoJoya
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@POST
	@Path("/postRetazarJoya")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaLote>", notes = "Actualiza peso neto retazado, peso bruto retazado, descuento retazado de la joya y el lote y crea un historico joya", response = GenericWrapper.class)
	public GenericWrapper<TbMiJoyaLote> postRetazarJoya(GenericWrapper<TbMiJoyaLote> wp) throws RelativeException {
		if (wp == null || wp.getEntidad() == null || wp.getEntidad().getTbMiJoya() == null
				|| wp.getEntidad().getTbMiLote() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Existen valores nulos");
		}
		GenericWrapper<TbMiJoyaLote> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.retazarJoya(wp.getEntidad()));
		return loc;
	}

	/**
	 * Busca las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional. Ordena por fecha
	 * creacion movimiento por prioridad
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/findByEstadoMvCodigoTipo")
	@ApiOperation(value = "PaginatedListWrapper<JoyaVentaVitrina>", notes = "Busca las joyas por estado obligatorio, estado movimiento inventario obligatorio, codigo joya opcional y tipo joya opcional. "
			+ "Ordena por fecha creacion movimiento por prioridad", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<JoyaVentaVitrina> findByEstadoMvCodigoTipo(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("estadoJoya") String estadoJoya, @QueryParam("estadoMvIn") String estadoMvIn,
			@QueryParam("codigoJoya") String codigoJoya, @QueryParam("idTipoJoya") String idTipoJoya)
			throws RelativeException {
		try {
			return findByEstadoMvCodigoTipo(
					new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
							isPaginated),
					StringUtils.isNotBlank(estadoJoya)
							? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya)
							: null,
					StringUtils.isNotBlank(estadoMvIn)
							? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoMvIn)
							: null,
					StringUtils.isNotBlank(codigoJoya) ? codigoJoya : null,
					StringUtils.isNotBlank(idTipoJoya) ? Long.valueOf(idTipoJoya) : null);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	/**
	 * Busca las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional. Ordena por fecha
	 * creacion movimiento por prioridad
	 * 
	 * @param pw
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	private PaginatedListWrapper<JoyaVentaVitrina> findByEstadoMvCodigoTipo(PaginatedWrapper pw,
			EstadoJoyaEnum estadoJoya, EstadoJoyaEnum estadoMvIn, String codigoJoya, Long idTipoJoya)
			throws RelativeException {
		PaginatedListWrapper<JoyaVentaVitrina> plw = new PaginatedListWrapper<>(pw);
		List<JoyaVentaVitrina> actions = this.mis.findByEstadoMvCodigoTipo(pw, estadoJoya, estadoMvIn, codigoJoya,
				idTipoJoya);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countByEstadoMvCodigoTipo(pw, estadoJoya, estadoMvIn, codigoJoya, idTipoJoya));
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/changeEstadoById")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo para cambiar el estado y precio de venta de una joya, por id", response = GenericWrapper.class)
	public GenericWrapper<TbMiJoya> changeEstadoById(@QueryParam("id") String id, @QueryParam("estado") String estado,
			@QueryParam("precioVenta") String precioVenta, @QueryParam("usuario") String usuario)
			throws RelativeException {
		GenericWrapper<TbMiJoya> loc = new GenericWrapper<>();
		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(estado) && StringUtils.isNotBlank(usuario)) {
			loc.setEntidad(this.mis.changeEstadoById(StringUtils.isNotBlank(id) ? Long.valueOf(id) : null,
					StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estado)
							: null,
					StringUtils.isNotBlank(precioVenta) ? Long.valueOf((long) Double.parseDouble(precioVenta)) : null,
					StringUtils.isNotBlank(precioVenta) ? usuario : null));
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "Informacion insificiente");
		}
		return loc;
	}

	@POST
	@Path("/updateJoyasByLotes")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo para actualizar las joyas de lotes", response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> updateJoyasByLotes(GenericWrapper<TbMiLote> wp) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		loc.setEntidades(this.mis.updateJoyasByLotes(wp.getEntidades(), "daemon"));
		return loc;
	}

	/**
	 * Busca todas las joyas de la funda por idFunda
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/findByIdFunda")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoya>", notes = "Busca todas las joyas de una funda", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> findByIdFunda(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idFunda") String idFunda)
			throws RelativeException {
		try {
			if (StringUtils.isBlank(idFunda)) {
				throw new RelativeException(Constantes.ERROR_CODE_FATAL, "El id funda no puede ser nulo");
			}
			return findByIdFunda(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
					sortDirections, isPaginated), Long.valueOf(idFunda));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	/**
	 * Busca todas las joyas de la funda por idFunda
	 * 
	 * @param pw
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	private PaginatedListWrapper<TbMiJoya> findByIdFunda(PaginatedWrapper pw, Long idFunda) throws RelativeException {
		PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>(pw);
		try {
			List<TbMiJoya> actions = this.mis.findJoyaByIdFunda(pw, idFunda);
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(this.mis.countJoyaByIdFunda(idFunda).intValue());
				plw.setList(actions);
			}
			return plw;
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@POST
	@Path("/findByCodigoJoyaEstadosFechas")
	@ApiOperation(value = "PaginatedListWrapper<JoyaVentaVitrina>", notes = "Busca las joyas por estado obligatorio, estado movimiento inventario obligatorio, codigo joya opcional y tipo joya opcional. "
			+ "Ordena por fecha creacion movimiento por prioridad", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> findByCodigoJoyaEstadosFechas(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, JoyaWrapper jw) throws RelativeException {
		try {
			PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>();
			log.info("====>findByCodigoJoyaEstadosFechas");
			log.info("====>findByCodigoJoyaEstadosFechas codigoJoya " + jw.getCodigoJoya());
			log.info("====>findByCodigoJoyaEstadosFechas fecha desde " + jw.getFechaDesde());
			log.info("====>findByCodigoJoyaEstadosFechas fecha hasta " + jw.getFechaHasta());
			log.info("====>findByCodigoJoyaEstadosFechas estados " + jw.getEstadosJoyaStr());
			plw.setList(this.mis.findJoyaByCodigoJoyaEstadosFechas(
					new PaginatedWrapper(
							Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated),
					jw.getCodigoJoya(), jw.getEstadosJoya(),
					StringUtils.isEmpty(jw.getFechaDesde()) ? null
							: MidasOroUtil.formatSringToDate(jw.getFechaDesde().substring(0, 10),
									MidasOroUtil.DATE_FORMAT),
					StringUtils.isEmpty(jw.getFechaHasta()) ? null
							: MidasOroUtil.formatSringToDate(jw.getFechaHasta().substring(0, 10),
									MidasOroUtil.DATE_FORMAT)));
			if (plw.getList() != null) {
				plw.setTotalResults(this.mis.countJoyaByCodigoJoyaEstadosFechas(jw.getCodigoJoya(), jw.getEstadosJoya(),
						StringUtils.isEmpty(jw.getFechaDesde()) ? null
								: MidasOroUtil.formatSringToDate(jw.getFechaDesde().substring(0, 10),
										MidasOroUtil.DATE_FORMAT),
						StringUtils.isEmpty(jw.getFechaHasta()) ? null
								: MidasOroUtil.formatSringToDate(jw.getFechaHasta().substring(0, 10),
										MidasOroUtil.DATE_FORMAT))
						.intValue());
			}
			return plw;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@GET
	@Path("/getEntityWithInventory")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiJoya", response = GenericWrapper.class)
	public MovimientoInventarioWrapper getEntityWithInventory(@QueryParam("id") String id) throws RelativeException {

		return this.mis.findJoyaByIdWithInventory(Long.valueOf(id));

	}

	@POST
	@Path("/venderJoya")
	@ApiOperation(value = "GenericWrapper<TbMiJoya>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiJoya", response = GenericWrapper.class)
	public GenericWrapper<VentaJoyaWrapper> venderJoya(@QueryParam("usuario") String usuario,
			@QueryParam("idAgencia") String idAgencia, GenericWrapper<VentaJoyaWrapper> ventaJoya)
			throws RelativeException {
		GenericWrapper<VentaJoyaWrapper> loc = new GenericWrapper<VentaJoyaWrapper>();
		loc.setEntidad(this.mis.venderJoyaVitrina(ventaJoya.getEntidad(), usuario, Long.valueOf(idAgencia)));
		return loc;

	}

	@GET
	@Path("/venderByCodigoAndTipo")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoya>", notes = "Busca las joyas por estado obligatorio, estado movimiento inventario obligatorio, codigo joya opcional y tipo joya opcional. "
			+ "Ordena por fecha creacion movimiento por prioridad", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> venderByCodigoAndTipo(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoJoya") String codigoJoya, @QueryParam("idTipoJoya") String idTipoJoya)
			throws RelativeException {
		try {
			return venderByCodigoAndTipo(
					new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
							isPaginated),
					StringUtils.isNotBlank(codigoJoya) ? codigoJoya : null,
					StringUtils.isNotBlank(idTipoJoya) ? Long.valueOf(idTipoJoya) : null);
		} catch (RelativeException e) {
			throw e;
		}catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE FORMATEAR: ");
		}
	}
	
	
	@GET
	@Path("/findPendienteByIdentificacion")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoya>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiJoya", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoya> findPendienteByIdentificacion(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion,
			@QueryParam("codigoJoya") String codigoJoya
			) throws RelativeException {
		return findPendienteByIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),identificacion,codigoJoya);
	}

	private PaginatedListWrapper<TbMiJoya> findPendienteByIdentificacion(PaginatedWrapper pw, String identificacion, String codigoJoya) throws RelativeException {
		PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>(pw);
		List<TbMiJoya> actions = this.mis.findJoyaByEstadoAndIdentificacion(pw, EstadoJoyaEnum.PENDIENTE_HABILITANTE, identificacion,codigoJoya);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.findAbonoByEstadoAndIdentificacion(EstadoJoyaEnum.PENDIENTE_HABILITANTE,identificacion,codigoJoya).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	

	private PaginatedListWrapper<TbMiJoya> venderByCodigoAndTipo(PaginatedWrapper pw, String codigoJoya,
			Long idTipoJoya) throws RelativeException {
		PaginatedListWrapper<TbMiJoya> plw = new PaginatedListWrapper<>(pw);

		List<TbMiJoya> actions = this.mis.venderJoyaByCodigoAndTipo(pw, codigoJoya, idTipoJoya);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.venderJoyaByCodigoAndTipo(codigoJoya, idTipoJoya).intValue());
			plw.setList(actions);
		}
		return plw;

	}

}
