package com.relative.midas.rest;

import java.math.BigDecimal;
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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoCajaOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/corteCajaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "CorteCaja -REST CRUD")
public class CorteCajaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCorteCaja, GenericWrapper<TbMiCorteCaja>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;
	@Inject
	MovimientoCajaOroService movCaja;

	public CorteCajaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenerecWrapper<TbMiCorteCaja>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCorteCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCorteCaja> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCorteCaja> loc = new GenericWrapper<>();
		TbMiCorteCaja a = this.mis.findCorteCajaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCorteCaja>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCorteCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCorteCaja> persistEntity(GenericWrapper<TbMiCorteCaja> wp) throws RelativeException {
		GenericWrapper<TbMiCorteCaja> loc = new GenericWrapper<>();
		log.info(wp.getEntidad().getTbMiAgencia().getId().toString());
		loc.setEntidad(this.mis.manageCorteCaja(wp.getEntidad()));
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCorteCaja>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCorteCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiCorteCaja> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiCorteCaja> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCorteCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCorteCaja> actions = this.mis.findAllCorteCaja(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCorteCaja().intValue());
			plw.setList(actions);
		}
		return plw;

	}

	@GET
	@Path("/findByParam")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCorteCaja>", notes = "Metodo Get findByParam Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCorteCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiCorteCaja> findByParam(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("estado") String estado,
			@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		return findByParam(StringUtils.isNotBlank(fechaDesde) ? MidasOroUtil.formatSringToDate(fechaDesde) : null,
				StringUtils.isNotBlank(fechaHasta) ? MidasOroUtil.formatSringToDate(fechaHasta) : null,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null,
						StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated));

	}

	private PaginatedListWrapper<TbMiCorteCaja> findByParam(Date fechaDesde, Date fechaHasta, EstadoMidasEnum estado, Long idAgencia,
			PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCorteCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCorteCaja> actions = this.mis.findCorteCajaByParamPaged(fechaDesde, fechaHasta, estado,idAgencia, pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCorteCajaByParamPaged(fechaDesde, fechaHasta, estado,idAgencia).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/findValorCierre")
	public GenericWrapper<BigDecimal> findValorCierre(@QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		if (StringUtils.isBlank(idAgencia)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "La agencia no puede estar vacia");
		} else {
			GenericWrapper<BigDecimal> wp = new GenericWrapper<>();
			TbMiCorteCaja cj = this.mis.findValorUltimoCierreCaja(Long.valueOf(idAgencia));
			if (cj != null && cj.getValorCierre() != null) {
				wp.setEntidad(cj.getValorCierre());
			}
			return wp;
		}
	}

	@GET
	@Path("/validarCajaByAgenciaAndEstado")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "Metodo Get validarCajaByAgenciaAndEstado Retorna GenericWrapper de entidades encontradas en TbMiCorteCaja", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validarCierreCaja(@QueryParam("idAgencia") String idAgencia,
			@QueryParam("estado") String estado) throws RelativeException {
		Boolean f = null;
		EstadoMidasEnum estadoEnum = StringUtils.isNotBlank(estado)
				? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado)
				: null;
		if (StringUtils.isBlank(idAgencia) || idAgencia.equalsIgnoreCase("null")) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "La agencia no puede estar vacia");
		}

		GenericWrapper<Boolean> wp = new GenericWrapper<>();
		TbMiCorteCaja cj = this.mis.validarCajaByAgenciaAndEstado(Long.valueOf(idAgencia), EstadoMidasEnum.ACT);
		if (cj == null) {
			f = false;
			wp.setEntidad(f);
			return wp;
		}
		if (estadoEnum != null && estadoEnum == EstadoMidasEnum.CERRADO) {
			if (cj.getValorCierre() == null) {
				f = false;
			} else {
				f = true;
			}
		} else if (estadoEnum != null && estadoEnum == EstadoMidasEnum.ACT) {
			if (cj.getValorCierre() != null) {
				f = false;
			} else {
				f = true;
			}
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ESTADO A BUSCAR DEBE SER ACT o CERRADO");
		}

		wp.setEntidad(f);
		return wp;

	}

	@GET
	@Path("/validarSaldoCaja")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>", notes = "Metodo Get validarCajaByAgenciaAndEstado Retorna GenericWrapper de entidades encontradas en TbMiCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCaja> validarSaldoCaja(@QueryParam("idAgencia") String idAgencia)
			throws RelativeException {

		if (StringUtils.isBlank(idAgencia)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DE LA AGENCIA - VALIDE SI EL USUARIO TIENE UNA AGENCIA ASIGNADA");
		}
		GenericWrapper<TbMiCaja> wp = new GenericWrapper<>();
		TbMiCorteCaja cj = this.mis.validarCajaByAgenciaAndEstado(Long.valueOf(idAgencia), null);
		if (cj != null) {
			wp.setEntidad(cj.getTbMiCaja());
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "no se encontro ninguna caja para esa agencia");
		}
		return wp;

	}

	@GET
	@Path("/aperturarCaja")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>", notes = "Metodo Get aperturarCaja Retorna GenericWrapper de entidades encontradas en TbMiCorteCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiCorteCaja> aperturarCaja(@QueryParam("idAgencia") String idAgencia,
			@QueryParam("valor") String valor, @QueryParam("usuario") String usuario) throws RelativeException {

		// EstadoMidasEnum estadoEnum = StringUtils.isNotBlank(estado)?
		// MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null;
		if (StringUtils.isBlank(idAgencia)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "La agencia no puede estar vacia");
		}
		GenericWrapper<TbMiCorteCaja> wp = new GenericWrapper<>();
		TbMiCorteCaja cj = this.mis.aperturarCaja(Long.valueOf(idAgencia), usuario , StringUtils.isNotBlank(valor)?new BigDecimal(valor.replaceAll(",", "")):BigDecimal.ZERO);
		wp.setEntidad( cj );		
		return wp;

	}
	
	@POST
	@Path("/corteCaja")
	@ApiOperation(value = "GenericWrapper<TbMiCorteCaja>",
	notes = "Metodo que realiza el corte de caja por agencia",
	response = GenericWrapper.class)
	public GenericWrapper<TbMiCorteCaja> corteCaja(@QueryParam("idAgencia") String idAgencia, 
			@QueryParam("username") String username, @QueryParam("idCorteApertura") String idCorteApertura,
			@QueryParam("justificacion") String justificacion, @QueryParam("diferencia") String diferencia,
			GenericWrapper<TbMiCorteCaja> wp) 
			throws RelativeException {
		if(StringUtils.isBlank(idAgencia) || StringUtils.isBlank(username) 
				|| wp.getEntidad() == null || wp.getEntidad().getTbMiDetalleCajas() == null
				|| wp.getEntidad().getTbMiDetalleCajas().isEmpty()
				|| StringUtils.isBlank(idCorteApertura)) {
			throw new RelativeException(
					Constantes.ERROR_CODE_CUSTOM, "Los parametros enviados estan incompletos");
		}
		GenericWrapper<TbMiCorteCaja> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.corteCaja(Long.valueOf(idAgencia), username, Long.valueOf(idCorteApertura),
				wp.getEntidad().getTbMiDetalleCajas(),justificacion, BigDecimal.valueOf(Double.valueOf(diferencia)))  );
		return loc;
	}
	
	@GET
	@Path("/findPendienteById")
	@ApiOperation(value = "GenerecWrapper<TbMiCorteCaja>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCorteCaja", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiCorteCaja> findPendienteById(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, 
			@QueryParam("id") String id,
			@QueryParam("idAgencia") String idAgencia) throws RelativeException {
		return findPendienteById(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),StringUtils.isNotBlank(id)?Long.valueOf(id):null,
								StringUtils.isNotBlank(idAgencia) ? Long.valueOf(idAgencia) : null);
	}

	private PaginatedListWrapper<TbMiCorteCaja> findPendienteById(PaginatedWrapper pw, Long id, Long idAgencia)throws RelativeException {
		PaginatedListWrapper<TbMiCorteCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCorteCaja> actions = this.mis.findCorteCajaByIdAndEstado(pw,id,EstadoMidasEnum.PENDIENTE_HABILITANTE, idAgencia);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCorteCajaByIdAndEstado(id,EstadoMidasEnum.PENDIENTE_HABILITANTE, idAgencia).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
