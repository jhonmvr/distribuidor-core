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
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.enums.TipoTarjetaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoCajaOroService;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.FondeoWrapper;
import com.relative.midas.wrapper.MovimientoCajaWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/movimientoCajaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "MovimientoCajaRestController - REST CRUD")
public class MovimientoCajaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiMovimientoCaja, GenericWrapper<TbMiMovimientoCaja>> {
	@Inject
	Logger log;
	@Inject
	MidasOroService mis;
	@Inject
	MovimientoCajaOroService movCaja;

	public MovimientoCajaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		TbMiMovimientoCaja a = this.mis.findMovimientoCajaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMovimientoCaja>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMovimientoCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMovimientoCaja> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiMovimientoCaja> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiMovimientoCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiMovimientoCaja> actions = this.mis.findAllMovimientoCaja(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countMovimientoCaja().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> persistEntity(GenericWrapper<TbMiMovimientoCaja> wp)
			throws RelativeException {
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageMovimientoCaja(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/registrarIngreso")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo registrarIngreso Retorna wrapper de entidades encontradas en TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> registrarIngreso(@QueryParam("proceso") String proceso,@QueryParam("cuenta") String cuenta,
			@QueryParam("idCliente") String idCliente, @QueryParam("idContrato") String idContrato,
			@QueryParam("formaPago") String formaPago, @QueryParam("valor") String valor,
			@QueryParam("usuario") String usuario, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("idBanco") String idBanco, @QueryParam("numeroCuentaBanco" ) String numeroCuentaBanco,
			@QueryParam("tipoCuentaBanco") String tipoCuentaBanco, @QueryParam("tipoTarjeta") String tipoTarjeta,
			@QueryParam("numeroTarjeta") String numeroTarjeta, @QueryParam("habienteTarjeta") String habienteTarjeta,
			@QueryParam("cedHabienteTarjeta") String cedHabienteTarjeta,
			@QueryParam("comentario") String comentario) throws RelativeException {
		
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		TbMiMovimientoCaja a = this.movCaja.registrarIngreso(
				StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso):null,
				cuenta, 
				StringUtils.isNotBlank(formaPago) ? MidasOroUtil.getEnumFromString(FormaPagoEnum.class, formaPago):null,
				StringUtils.isNotBlank(valor) ? new BigDecimal(valor) : BigDecimal.ZERO, usuario,
				StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
				StringUtils.isNotBlank(idBanco) ? new Long(idBanco) : null, numeroCuentaBanco, tipoCuentaBanco,
				StringUtils.isNotBlank(idCliente) ? new Long(idCliente) : null,
				StringUtils.isNotBlank(idContrato) ? new Long(idContrato) : null,
				StringUtils.isNotBlank(tipoTarjeta) ? MidasOroUtil.getEnumFromString(TipoTarjetaEnum.class, formaPago):null,
				numeroTarjeta,habienteTarjeta,cedHabienteTarjeta, comentario,null,null);
		loc.setEntidad(a);
		return loc;
	}

	@POST
	@Path("/registrarIngresoList")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo registrarIngreso Retorna wrapper de entidades encontradas en TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> registrarIngresoList(@QueryParam("usuario") String usuario,
			@QueryParam("cuenta") String cuenta,@QueryParam("proceso") String proceso, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("idCliente") String idCliente, @QueryParam("idContrato") String idContrato,@QueryParam("idVentaLote") String idVentaLote,
			GenericWrapper<TbMiMovimientoCaja> movCaja) throws RelativeException {
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		List<TbMiMovimientoCaja> l = new ArrayList<TbMiMovimientoCaja>();
		String listError = StringUtils.EMPTY;
		if (movCaja != null && movCaja.getEntidades() != null) {
			for (TbMiMovimientoCaja caja : movCaja.getEntidades()) {
				try {
					caja=this.movCaja.registrarIngreso(
							StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso):null,
							cuenta, caja.getFormaPago(),
							caja.getIngreso() != null ? caja.getIngreso() : BigDecimal.ZERO, usuario,
							StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
							caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null,
							caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(),
							StringUtils.isNotBlank(idCliente) ? Long.valueOf(idCliente) : null,
							StringUtils.isNotBlank(idContrato) ? Long.valueOf(idContrato) : null,
							caja.getTipoTarjeta(), caja.getNumeroTarjeta(), caja.getHabienteTarjeta(), caja.getCedHabienteTarjeta(),caja.getComentario(),
							StringUtils.isNotBlank(idVentaLote) ?Long.valueOf(idVentaLote):null,null);
							
					l.add(caja);
				} catch (RelativeException e) {
					listError = listError.concat("Error: ").concat(e.getMensaje());
				}
			}
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL INGRESO");
		}
		if(!listError.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,listError);
		}
		loc.setEntidades(l);
		return loc;
	}

	@GET
	@Path("/registrarEgreso")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo registrarEgreso Retorna wrapper de entidades encontradas en TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> registrarEgreso(
			@QueryParam("proceso") String proceso,
			@QueryParam("cuenta") String cuenta,
			@QueryParam("idCliente") String idCliente, @QueryParam("idContrato") String idContrato,
			@QueryParam("formaPago") String formaPago, @QueryParam("valor") String valor,
			@QueryParam("usuario") String usuario, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("idBanco") String idBanco, @QueryParam("numeroCuentaBanco") String numeroCuentaBanco,
			@QueryParam("tipoCuentaBanco") String tipoCuentaBanco, @QueryParam("tipoTarjeta") String tipoTarjeta,
			@QueryParam("numeroTarjeta") String numeroTarjeta, @QueryParam("habienteTarjeta") String habienteTarjeta,
			@QueryParam("cedHabienteTarjeta") String cedHabienteTarjeta,
			@QueryParam("comentario") String comentario) throws RelativeException {
			
		
		
		
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		TbMiMovimientoCaja a = this.movCaja.registrarEgreso(
				StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso):null,
				cuenta, 
				StringUtils.isNotBlank(formaPago) ? MidasOroUtil.getEnumFromString(FormaPagoEnum.class, formaPago):null,
				StringUtils.isNotBlank(valor) ? new BigDecimal(valor) : BigDecimal.ZERO, usuario,
				StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
				StringUtils.isNotBlank(idBanco) ? new Long(idBanco) : null, numeroCuentaBanco, tipoCuentaBanco,
				StringUtils.isNotBlank(idCliente) ? new Long(idCliente) : null,
				StringUtils.isNotBlank(idContrato) ? new Long(idContrato) : null,
				StringUtils.isNotBlank(tipoTarjeta) ? MidasOroUtil.getEnumFromString(TipoTarjetaEnum.class, formaPago):null,
				numeroTarjeta, habienteTarjeta, cedHabienteTarjeta,comentario,null);
		loc.setEntidad(a);
		return loc;
	}

	@POST
	@Path("/registrarEgresoList")
	@ApiOperation(value = "GenericWrapper<TbMiMovimientoCaja>", notes = "Metodo registrarIngreso Retorna wrapper de entidades encontradas en TbMiMovimientoCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> registrarEgresoList(@QueryParam("usuario") String usuario,
			@QueryParam("idCliente") String idCliente, @QueryParam("idContrato") String idContrato,
			@QueryParam("cuenta") String cuenta,@QueryParam("proceso") String proceso, @QueryParam("idAgencia") String idAgencia,
			GenericWrapper<TbMiMovimientoCaja> movCaja) throws RelativeException {
		String listError = StringUtils.EMPTY;
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		List<TbMiMovimientoCaja> l = new ArrayList<TbMiMovimientoCaja>();
		if (movCaja != null && movCaja.getEntidades() != null) {
			for (TbMiMovimientoCaja caja : movCaja.getEntidades()) {
				try {
					caja = this.movCaja.registrarEgreso(
							StringUtils.isNotBlank(proceso) ? MidasOroUtil.getEnumFromString(ProcesoEnum.class, proceso):null,
							cuenta,	caja.getFormaPago(),
							caja.getEgreso() != null ? caja.getEgreso() : BigDecimal.ZERO, usuario,
							StringUtils.isNotBlank(idAgencia) ? new Long(idAgencia) : null,
							caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null,
							caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(),
							StringUtils.isNotBlank(idCliente) ? new Long(idCliente) : null,
							StringUtils.isNotBlank(idContrato) ? new Long(idContrato) : null,
							caja.getTipoTarjeta(), caja.getNumeroTarjeta(), caja.getHabienteTarjeta(),
							caja.getCedHabienteTarjeta(),caja.getComentario(),null);
							l.add(caja);
				} catch (RelativeException e) {
					listError = listError.concat("Error: ").concat(e.getMensaje());
				}
			}
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE PUEDE LEER LA INFORMACION DEL INGRESO");
		}
		if( !listError.isEmpty() ) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,listError );
		}
		loc.setEntidades(l);
		return loc;
	}

	@GET
	
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMovimientoCaja>", notes = "Metodo Get findByParam Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMovimientoCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMovimientoCaja> findByParam(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("estado") String estado, @QueryParam("fechaDesde") String fechaDesde,
            @QueryParam("fechaHasta") String fechaHasta, @QueryParam("codigoContrato") String codigoContrato,
			@QueryParam("identificacion") String identificacion,@QueryParam("formaPago") String formaPago,
			@QueryParam("idAgencia") String idAgencia)
					throws RelativeException {
		EstadoMidasEnum cEstado = null;
		Date cFechaDesde = null;
		Date cFechaHasta = null;
		FormaPagoEnum cFormaPago = null;
		if(StringUtils.isNotBlank(estado))
			cEstado = MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado);
		if(StringUtils.isNotBlank(fechaDesde))
			cFechaDesde = MidasOroUtil.formatSringToDate(fechaDesde, "dd/MM/yyyy");
		if(StringUtils.isNotBlank(fechaHasta))
			cFechaHasta = MidasOroUtil.formatSringToDate(fechaHasta, "dd/MM/yyyy");
		
		return findByParam(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				cEstado, cFechaDesde, cFechaHasta, codigoContrato, identificacion, cFormaPago,StringUtils.isNotBlank(idAgencia)?
						Long.valueOf(idAgencia):null);
	}


	private PaginatedListWrapper<TbMiMovimientoCaja> findByParam(PaginatedWrapper pw,EstadoMidasEnum estado,
			Date fechaDesde, Date fechaHasta,String codigoContrato, String identificacionCliente,FormaPagoEnum formapago, Long idAgencia)
			throws RelativeException {
		PaginatedListWrapper<TbMiMovimientoCaja> plw = new PaginatedListWrapper<>(pw);

		List<TbMiMovimientoCaja> actions = this.mis.findMovimientoCajaByParams(pw,estado,
				fechaDesde,fechaHasta,codigoContrato,identificacionCliente,formapago,idAgencia);
		if (actions != null && !actions.isEmpty()) {

			plw.setTotalResults(this.mis.countMovimientoCajaByParams(estado,
					fechaDesde,fechaHasta,codigoContrato,identificacionCliente,formapago,idAgencia).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST	
	@Path("/registrarFondeo")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMovimientoCaja>", notes = "Metodo Get findByParam Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMovimientoCaja", response = PaginatedListWrapper.class)
	public FondeoWrapper fondeo(FondeoWrapper fondeo) throws RelativeException{
		FondeoWrapper loc = new FondeoWrapper();
		try {
			TbMiAgencia matriz = this.mis.findAgenciaMatriz();
			TbMiMovimientoCaja a = this.movCaja.registrarIngreso(ProcesoEnum.MOV_ENTRE_CAJA,
					"FONDEO", FormaPagoEnum.EFECTIVO,fondeo.getIngreso().getIngreso(), fondeo.getUsuario(),
					matriz.getId(),fondeo.getIngreso().getTbMiBanco()!=null?fondeo.getIngreso().getTbMiBanco().getId():null,
					fondeo.getIngreso().getNumeroCuentaBanco()!=null? fondeo.getIngreso().getNumeroCuentaBanco():null, fondeo.getIngreso().getTipoCuentaBanco(),
					null, null,
					fondeo.getIngreso().getTipoTarjeta()!=null? fondeo.getIngreso().getTipoTarjeta():null, fondeo.getIngreso().getNumeroTarjeta()!=null? fondeo.getIngreso().getNumeroTarjeta():null ,
					fondeo.getIngreso().getHabienteTarjeta()!=null? fondeo.getIngreso().getHabienteTarjeta():null,
					fondeo.getIngreso().getCedHabienteTarjeta()!=null? fondeo.getIngreso().getCedHabienteTarjeta():null,
					fondeo.getIngreso().getComentario(),null,null);
			
			TbMiMovimientoCaja b = this.movCaja.registrarEgreso(ProcesoEnum.MOV_ENTRE_CAJA, "FONDEO",
					FormaPagoEnum.MOVIMIENTO_INTERNO, fondeo.getEgreso().getEgreso(),
					fondeo.getUsuario(), matriz.getId(),fondeo.getEgreso().getTbMiBanco()!=null? fondeo.getEgreso().getTbMiBanco().getId():null,
					fondeo.getEgreso().getNumeroCuentaBanco()!=null? fondeo.getEgreso().getNumeroCuentaBanco():null,
					fondeo.getEgreso().getTipoCuentaBanco()!=null? fondeo.getEgreso().getTipoCuentaBanco():null,
					null, null,
					fondeo.getEgreso().getTipoTarjeta()!=null? fondeo.getEgreso().getTipoTarjeta():null, 
					fondeo.getEgreso().getNumeroTarjeta()!=null? fondeo.getEgreso().getNumeroTarjeta():null, 
					fondeo.getEgreso().getHabienteTarjeta()!=null? fondeo.getEgreso().getHabienteTarjeta():null,
					fondeo.getEgreso().getCedHabienteTarjeta()!=null? fondeo.getEgreso().getCedHabienteTarjeta():null,
					fondeo.getEgreso().getComentario(),null);
			
			loc.setIngreso(a);
			loc.setEgreso(b);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"INFORMACION INCOMPLETA");
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL COMLETAR LA OPERACION"+e.getMessage());
		}
		return loc;
		
	}
	
	@GET
	@Path("/findByVentaLote")
	@ApiOperation(value = "GenericWrapper<TbMiCaja>",
	notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiMovimientoCaja> findByVentaLote(@QueryParam("idVentaLote") String idVentaLote) throws RelativeException {
		if(StringUtils.isBlank(idVentaLote)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id de la agencia no puede ser nulo");
		}
		GenericWrapper<TbMiMovimientoCaja> loc = new GenericWrapper<>();
		List<TbMiMovimientoCaja> a = this.mis.findMovimientoCajaByVentaLote(Long.valueOf(idVentaLote));
		loc.setEntidades(a);
		return loc;
	}
	
	
	@GET
	@Path("/findMovimientoByUsuario")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMovimientoCaja>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMovimientoCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<MovimientoCajaWrapper> findMovimientoByUsuario(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("usuario") String usuario,
			@QueryParam("idCaja") String idCaja) throws RelativeException {
		return findMovimientoByUsuario(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
						sortDirections, isPaginated), usuario,idCaja );

	}

	private PaginatedListWrapper<MovimientoCajaWrapper> findMovimientoByUsuario(PaginatedWrapper pw, String usuario, String idCaja) throws RelativeException {
		PaginatedListWrapper<MovimientoCajaWrapper> plw = new PaginatedListWrapper<>(pw);
		List<MovimientoCajaWrapper> actions = this.movCaja.findMovimientoCajaByUsuarioAndCaja(pw, usuario,idCaja);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.movCaja.countMovimientoCajaByUsuarioAndCaja(usuario,idCaja).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
