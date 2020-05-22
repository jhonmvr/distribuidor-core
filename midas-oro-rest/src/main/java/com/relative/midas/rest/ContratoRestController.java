package com.relative.midas.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoCajaOroService;
import com.relative.midas.service.MovimientoOroService;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.CompraOroWrapper;
import com.relative.midas.wrapper.ContratoAccionWrapper;
import com.relative.midas.wrapper.RetazarContratos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/contratoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ContratoRestController - REST CRUD")
public class ContratoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiContrato, GenericWrapper<TbMiContrato>> {

	public ContratoRestController() throws RelativeException {
		super();
	}

	@Inject
	Logger log;
	@Inject
	MidasOroService mis;
	@Inject
	CompraOroService cos;

	@Inject
	MovimientoOroService mos;
	@Inject
	MovimientoCajaOroService movCaja;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		TbMiContrato a = this.mis.findContratoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiContrato>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiContrato", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiContrato> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiContrato> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContrato> actions = this.mis.findAllContrato(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContrato().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> persistEntity(GenericWrapper<TbMiContrato> wp) throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageContrato(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/generarCodigoContrato")
	public String generarCodigoContrato(@QueryParam("idAgencia") String idAgencia,
			@QueryParam("tipoCompra") String tipoCompra) throws RelativeException {
		if (idAgencia != null && !idAgencia.isEmpty() && tipoCompra != null && !tipoCompra.isEmpty()) {
			return cos.codigoContrato(tipoCompra, Long.valueOf(idAgencia));
		}
		return "id-tipoCompra-00000";

	}

	@GET
	@Path("/contratoByIdentificacion")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiContrato> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion) throws RelativeException {
		return findContratoByIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), identificacion);
	}

	@GET
	@Path("/contratoByIdCliente")
	@ApiOperation(value = "GenericWrapper<TbMiCotizacion>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiCotizacion", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiContrato> listAllEntitiesById(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idCliente") String idCliente)
			throws RelativeException {
		return findContratoByIdCliente(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), new Long(idCliente));
	}

	private PaginatedListWrapper<TbMiContrato> findContratoByIdentificacion(PaginatedWrapper pw, String identificacion)
			throws RelativeException {
		PaginatedListWrapper<TbMiContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContrato> actions = null;
		actions = this.mis.findContratoByIdentificacion(pw, identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFindContratoByIdentificacion(identificacion).intValue());
			plw.setList(actions);
		}

		return plw;
	}

	private PaginatedListWrapper<TbMiContrato> findContratoByIdCliente(PaginatedWrapper pw, Long idCliente)
			throws RelativeException {
		PaginatedListWrapper<TbMiContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContrato> actions = null;
		actions = this.mis.findContratoByIdCliente(pw, idCliente);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFindContratoByIdCliente(idCliente).intValue());
			plw.setList(actions);
		}

		return plw;
	}

	@GET
	@Path("/getContratosByFiltro")
	@ApiOperation(value = "PaginatedListWrapper<TbMiContrato>", notes = "Metodo que retorna una lista de contratos por filtro, Fecha desde, Fecha hasta, Codigo, Estado, Identificacion y Vencidos", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiContrato> getContratosByFiltro(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("codigoOperacion") String codigoOperacion, @QueryParam("estadoContrato") String estadoContrato,
			@QueryParam("identificacion") String identificacion, @QueryParam("cliente") String cliente,
			@QueryParam("contratosVencidos") String contratosVencidos, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		return getContratosByFiltro(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				fechaDesde, fechaHasta, codigoOperacion, estadoContrato, identificacion,cliente ,contratosVencidos.equalsIgnoreCase("true"),
				StringUtils.isNotBlank(idAgencia) ? Long.valueOf(idAgencia) : null);
	}

	private PaginatedListWrapper<TbMiContrato> getContratosByFiltro(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigo, String estado, String identificacion, String cliente, Boolean contratosVencidos,
			Long idAgencia) throws RelativeException {
		
		List<EstadoContratoEnum> estados = new ArrayList<EstadoContratoEnum>();
		if(StringUtils.isNotBlank(estado)) {
			String[] listEstados = estado.split(",");
			if(listEstados.length > 1) {
				for(String l : listEstados) {
					estados.add(MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, l));
				}
			}else {
				estados.add(MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado));
			}
		}
		PaginatedListWrapper<TbMiContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContrato> actions = this.mis.getContratosByFiltro(pw, fechaDesde, fechaHasta, codigo,
				estados,
				identificacion, cliente, contratosVencidos, idAgencia);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContratosByFiltro(fechaDesde, fechaHasta, codigo,
					estados, identificacion, cliente, contratosVencidos,
					idAgencia));
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/getContratoByCodigo")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> getContratoByCodigo(@QueryParam("codigo") String codigo)
			throws RelativeException {

		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		TbMiContrato a = this.mis.findContratoByCodigo(codigo);
		loc.setEntidad(a);
		return loc;

	}

	/**
	 * Lista todos los contratos por idAgencia y 2 estados del contrato
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @autor Kevin Chamorro
	 */
	@GET
	@Path("/getByEstadoAndIdAgencia")
	@ApiOperation(value = "PaginatedListWrapper<TbMiContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContrato", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiContrato> getByEstadoAndIdAgencia(
			@QueryParam("page") @DefaultValue("0") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("estado1") String estado1, @QueryParam("estado2") String estado2) throws RelativeException {

		return getByEstadoAndIdAgencia(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), idAgencia, estado1, estado2);

	}

	/**
	 * Lista todos los contratos por idAgencia y 2 estados del contrato
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @autor Kevin Chamorro
	 */
	private PaginatedListWrapper<TbMiContrato> getByEstadoAndIdAgencia(PaginatedWrapper pw, String idAgencia,
			String estado1, String estado2) throws RelativeException {
		PaginatedListWrapper<TbMiContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContrato> actions = this.mis.listContratosByEstadoAndIdAgencia(pw, idAgencia,
				StringUtils.isNotBlank(estado1) ? MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado1)
						: null,
				StringUtils.isNotBlank(estado2) ? MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado2)
						: null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContratosByEstadoAndIdAgencia(pw, idAgencia,
					StringUtils.isNotBlank(estado1) ? MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado1)
							: null,
					StringUtils.isNotBlank(estado2) ? MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado2)
							: null)
					.intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/perfeccionarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post perfeccionarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> perfeccionarContrato(@QueryParam("idContrato") String idContrato,
			@QueryParam("usuario") String usuario)throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mos.perfeccionarContrato(idContrato.isEmpty() ? null : Long.parseLong(idContrato),usuario));
		return loc;
	}

	@POST
	@Path("/entregarJoyaCliente")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post entregarJoyaCliente Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> entregarJoyaCliente(GenericWrapper<TbMiContrato> wp) throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mos.entregarJoyaCliente(wp.getEntidad()));
		return loc;
	}

	/**
	 * Lista contratos por agencia y dos estado en clase personalizada
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 */
	@GET
	@Path("/getByAngenciaEstado")
	@ApiOperation(value = "PaginatedListWrapper<ContratosPerfecionados>", notes = "Metodo que retorna contratos por agencia y dos estado en clase personalizada ContratosPerfecionados", response = GenericWrapper.class)
	public PaginatedListWrapper<RetazarContratos> getByAngenciaEstado(
			@QueryParam("page") @DefaultValue("0") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("estado1") String estado1, @QueryParam("estado2") String estado2) throws RelativeException {
		if (StringUtils.isBlank(idAgencia) || StringUtils.isBlank(estado1) || StringUtils.isBlank(estado2)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Los parametros enviados no pueden ser nulos");
		}
		return getByAngenciaEstado(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				idAgencia, MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado1),
				MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estado2));
	}

	private PaginatedListWrapper<RetazarContratos> getByAngenciaEstado(PaginatedWrapper pw, String idAgencia,
			EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException {
		PaginatedListWrapper<RetazarContratos> plw = new PaginatedListWrapper<>(pw);
		List<RetazarContratos> actions = this.mis.findByAngenciaEstado(pw, idAgencia, estado1, estado2);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countByAngenciaEstado(pw, idAgencia, estado1, estado2));
			plw.setList(actions);
		}
		return plw;
	}

	@POST
	@Path("/guardarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Guarda el comprato para compra plazos y compra directa", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> guardarContrato(@QueryParam("idAgencia") String idAgencia,
			@QueryParam("usuario") String usuario, @QueryParam("idAgente") String idAgente,
			GenericWrapper<CompraOroWrapper> compra) throws RelativeException {
		try {
			TbMiContrato contrato;
			ProcesoEnum proceso = null;
			String cuenta = null;
			GenericWrapper<TbMiContrato> loc = new GenericWrapper<TbMiContrato>();
			// log.info("===============> guardarContrato");
			if (compra.getEntidad().getTbMiContratoCalculos() != null) {
				log.info("cantidad de calculos " + compra.getEntidad().getTbMiContratoCalculos().size());
				compra.getEntidad().getTbMiContrato()
						.setTbMiContratoCalculos(compra.getEntidad().getTbMiContratoCalculos());
			}
			contrato = this.cos.guardarContrato(compra.getEntidad().getTbMiJoyas(),
					compra.getEntidad().getTbMiContratoCalculos(), compra.getEntidad().getTbMiContrato(),
					StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null, usuario,
					StringUtils.isNotBlank(idAgente) ? new Long(idAgente) : null);
			if (contrato.getTipoCompra().equalsIgnoreCase(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)) {
				proceso = ProcesoEnum.COMPRA_DIRECTA;
				cuenta = "COMPRA_DIRECTA";
			} else if (contrato.getTipoCompra().equalsIgnoreCase(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX)) {
				proceso = ProcesoEnum.COMPRA_PLAZO;
				cuenta = "COMPRA_PLAZO";
				this.movCaja.registrarIngreso(proceso, "TASACION", FormaPagoEnum.MOVIMIENTO_INTERNO,
						compra.getEntidad().getTasacion(), usuario,
						StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
						null,null,null, contrato.getTbMiCliente().getId(), contrato.getId(),
						null, null, null,null,null,null,null);
			}
			for (TbMiMovimientoCaja caja : compra.getEntidad().getTbMiMovimientoCaja()) {
				this.movCaja.registrarEgreso(proceso, cuenta, caja.getFormaPago(),
						caja.getEgreso() != null ? caja.getEgreso() : BigDecimal.ZERO, usuario,
						StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
						caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null, caja.getNumeroCuentaBanco(),
						caja.getTipoCuentaBanco(), contrato.getTbMiCliente().getId(), contrato.getId(),
						caja.getTipoTarjeta(), caja.getNumeroTarjeta(), caja.getHabienteTarjeta(),
						caja.getCedHabienteTarjeta(),null,null);
			}
			if( compra.getEntidad().getAprobarContrato() != null ) {
				compra.getEntidad().getAprobarContrato().setEstado(EstadoAprobacionEnum.FINALIZADO);
				this.mis.manageAprobarContrato( compra.getEntidad().getAprobarContrato() );
			}
			loc.setEntidad(contrato);
			return loc;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EN EL FORMATO NUMERICO");
		}
	}

	@GET
	@Path("/validateContratoByIdCliente")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "Metodo Get perfeccionarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateContratoByIdCliente(@QueryParam("idCliente") String idCliente)
			throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		if (!StringUtils.isNotBlank(idCliente)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id del cliente no puede ser nulo");
		}
		Boolean validate = this.mis.validateControByIdCliente(new Long(idCliente));
		loc.setEntidad(validate);
		return loc;
	}

	@GET
	@Path("/validateContratoByFunda")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "Metodo Get perfeccionarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateContratoByIdFunda(@QueryParam("idFunda") String idFunda)
			throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		if (!StringUtils.isNotBlank(idFunda)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id de la funda no puede ser nulo");
		}
		Boolean validate = this.mis.validateContratoByIdFunda(new Long(idFunda));
		loc.setEntidad(validate);
		return loc;
	}

	@GET
	@Path("/validateContratoByLiquidacion")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "Metodo Get perfeccionarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateContratoByIdLiquidacion(@QueryParam("idLiquidacion") String idLiquidacion)
			throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		if (!StringUtils.isNotBlank(idLiquidacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id de la funda no puede ser nulo");
		}
		Boolean validate = this.mis.validateContratoByIdLiquidacion(new Long(idLiquidacion));
		loc.setEntidad(validate);
		return loc;
	}

	@GET
	@Path("/findByContratoAnterior")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Busca un contrato por id contrato anterior", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> findByContratoAnterior(
			@QueryParam("idContratoAnterior") String idContratoAnterior) throws RelativeException {
		if (StringUtils.isBlank(idContratoAnterior)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Es necesario el id del contrato anterior");
		}
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		TbMiContrato a = this.mis.findContratoByIdAnterior(Long.valueOf(idContratoAnterior));
		loc.setEntidad(a);
		return loc;
	}

	@POST
	@Path("/cancelarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo Post cancelarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> cancelarContrato(@QueryParam("usuario") String usuario,
			@QueryParam("idContrato") String idContrato, 
			GenericWrapper<TbMiMovimientoCaja> detalleIngresos,
			@QueryParam("valorAbono") String valorAbono, @QueryParam("valorCancelacion") String valorCancelacion,
			@QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		if (StringUtils.isBlank(idContrato)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ID DEL CONTRATO NO PUEDE SER NULO");
		}
		loc.setEntidad(this.mos.cancelarContrato(usuario, new Long(idContrato), detalleIngresos.getEntidades(),
				StringUtils.isNotBlank(valorAbono) ? new BigDecimal(valorAbono.replaceAll(",", "")) : null,
				StringUtils.isNotBlank(valorCancelacion) ? new BigDecimal(valorCancelacion.replaceAll(",", ""))
						: null, Long.valueOf(idAgencia)));
		return loc;
	}
	
	@POST
	@Path("/cancelarContratoValores")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo Post cancelarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> cancelarContratoValores(@QueryParam("usuario") String usuario,
			@QueryParam("idContrato") String idContrato, 
			ContratoAccionWrapper contratoAccion,
			@QueryParam("valorAbono") String valorAbono, @QueryParam("valorCancelacion") String valorCancelacion,
			@QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		if (StringUtils.isBlank(idContrato)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ID DEL CONTRATO NO PUEDE SER NULO");
		}
		log.info("xxxxxxxxxxxxxxxxxxxxxxx>cancelarContratoValores " + idContrato);
		log.info("xxxxxxxxxxxxxxxxxxxxxxx>cancelarContratoValores " + idContrato);
		
		loc.setEntidad(this.mos.cancelarContratoValores(usuario, new Long(idContrato), 
				contratoAccion.getDetalleCalculos(),
				contratoAccion.getDetalleIngresos(),
				StringUtils.isNotBlank(valorAbono) ? new BigDecimal(valorAbono.replaceAll(",", "")) : null,
				StringUtils.isNotBlank(valorCancelacion) ? new BigDecimal(valorCancelacion.replaceAll(",", ""))
						: null, Long.valueOf(idAgencia)));
		return loc;
	}

	@POST
	@Path("/renovarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post renovarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> renovarContrato(@QueryParam("usuario") String usuario,
			@QueryParam("idContrato") String idContrato, GenericWrapper<TbMiMovimientoCaja> detalleIngresos,
			@QueryParam("valorAbono") String valorAbono, @QueryParam("valorRenovacion") String valorRenovacion)
			throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(
				this.mos.renovarContrato(usuario, new Long(idContrato), detalleIngresos.getEntidades(),
				StringUtils.isNotBlank(valorAbono) ? new BigDecimal(valorAbono.replaceAll(",", "")) : null,
				StringUtils.isNotBlank(valorRenovacion) ? new BigDecimal(valorRenovacion.replaceAll(",", "")) : null));
		return loc;
	}
	
	@POST
	@Path("/renovarContratoValores")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post renovarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> renovarContratoValores(@QueryParam("usuario") String usuario,
			@QueryParam("idContrato") String idContrato, 
			ContratoAccionWrapper contratoAccion,
			@QueryParam("valorAbono") String valorAbono, @QueryParam("valorRenovacion") String valorRenovacion, 
			@QueryParam("plazo") String plazo,
			@QueryParam("idAgencia") String idAgencia)	throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mos.renovarContratoValores(usuario, new Long(idContrato),contratoAccion.getDetalleCalculos(),contratoAccion.getDetalleCalculosCancelacion(), contratoAccion.getDetalleIngresos(),
				contratoAccion.getAbono(),	contratoAccion.getValor(), Long.valueOf(plazo),Long.valueOf(idAgencia)));
		return loc;
	}

	@POST
	@Path("/devolverJoya")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato> devolverJoya(@QueryParam("idAgencia") String idAgencia,
			@QueryParam("usuario") String usuario,GenericWrapper<TbMiContrato> wp) throws RelativeException {
		try {
			GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
			loc.setEntidad(this.mis.devolverJoya(wp.getEntidad(), Long.valueOf(idAgencia), usuario));
			return loc;
		} catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID AGENCIA");
		}
	}
	
	@GET
	@Path("/validatePerfeccionamiento")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validatePerfeccionamiento(
			@QueryParam("idContrato") String idContrato) throws RelativeException {
		if (StringUtils.isBlank(idContrato)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.mis.validatePerfeccionamiento(Long.valueOf(idContrato));
		loc.setEntidad(a);
		return loc;
	}
	
	@POST
	@Path("/validateFinFeriado")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateFinFeriados(GenericWrapper<Date> fecha) throws RelativeException {		
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.cos.validarFinSemanaFeriados(fecha.getEntidad());
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/getSumContratoByFilter")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContrato", response = GenericWrapper.class)
	public BigDecimal  getSumMontoContrato(	@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("codigoOperacion") String codigoOperacion, @QueryParam("estadoContrato") String estadoContrato,
			@QueryParam("identificacion") String identificacion, @QueryParam("cliente") String cliente,
			@QueryParam("contratosVencidos") String contratosVencidos, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {

		List<EstadoContratoEnum> estados = new ArrayList<EstadoContratoEnum>();
		if(StringUtils.isNotBlank(estadoContrato)) {
			String[] listEstados = estadoContrato.split(",");
			if(listEstados.length > 1) {
				for(String l : listEstados) {
					estados.add(MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, l));
				}
			}else {
				estados.add(MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estadoContrato));
			}
		}
		
		
		return this.mis.sumMontoContratosByFiltro(fechaDesde, fechaHasta, codigoOperacion, estados, identificacion, cliente, Boolean.valueOf(contratosVencidos),
						StringUtils.isNotBlank(idAgencia)? Long.valueOf(idAgencia):null);

	}
	
	
	@GET
	@Path("/reversoPerfeccionamiento")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo que reversa el estado a vigente y reversa el perfeciconamiento eb TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato>  reversarPerfeccionamiento(	@QueryParam("idContrato") String idContrato,
			@QueryParam("usuario") String usuario)
	
			throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mos.reversarPerfeccionamiento(idContrato.isEmpty() ? null : Long.parseLong(idContrato),usuario));
		return loc;
	}
	
	@GET
	@Path("/validateReversoPerfeccionamiento")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateReversoPerfeccionamiento(
			@QueryParam("idContrato") String idContrato) throws RelativeException {
		if (StringUtils.isBlank(idContrato)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.mis.validateReversoPerfeccionamiento(Long.valueOf(idContrato));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/anulacionContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContrato>", notes = "Metodo que que anula el contrato en TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiContrato>  anulacionContrato(	@QueryParam("idContrato") String idContrato,
			@QueryParam("usuario") String usuario)
	
			throws RelativeException {
		GenericWrapper<TbMiContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mos.anularContrato(idContrato.isEmpty() ? null : Long.parseLong(idContrato),usuario));
		return loc;
	}
	
	@GET
	@Path("/validateAnulacionContrato")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateAnulacionContrato(
			@QueryParam("idContrato") String idContrato) throws RelativeException {
		if (StringUtils.isBlank(idContrato)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.mis.validateAnulacionContrato(Long.valueOf(idContrato));
		loc.setEntidad(a);
		return loc;
	}
	
}
