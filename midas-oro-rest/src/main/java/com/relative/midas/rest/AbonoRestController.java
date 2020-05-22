package com.relative.midas.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoCajaOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/abonoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "AbonoRestController - REST CRUD")
public class AbonoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiAbono, GenericWrapper<TbMiAbono>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	@Inject
	CompraOroService cos;
	
	@Inject
	MovimientoCajaOroService movCaja;

	public AbonoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiAbono>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAbono", response = GenericWrapper.class)
	public GenericWrapper<TbMiAbono> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiAbono> loc = new GenericWrapper<>();
		TbMiAbono a = this.mis.findAbonoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAbono>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAbono", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAbono> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiAbono> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiAbono> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAbono> actions = this.mis.findAllAbono(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAbono().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiAbono>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAbono", response = GenericWrapper.class)
	public GenericWrapper<TbMiAbono> persistEntity(GenericWrapper<TbMiAbono> wp) throws RelativeException {
		GenericWrapper<TbMiAbono> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageAbono(wp.getEntidad()));
		return loc;
	}

	/**
	 * Registra TbMiAbono's en caso de tener error devuelve una excepcion con la
	 * lista de los abonos q no se registraron.
	 * 
	 * @param List<TbMiAbono>
	 * @return List<TbMiAbono>
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	@POST
	@Path("/persistListEntity")
	@ApiOperation(value = "GenericWrapper<TbMiAbono>", notes = "Metodo Post persistListEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAbono", response = GenericWrapper.class)
	public GenericWrapper<TbMiAbono> persistListEntity(@QueryParam("idAgencia")  String idAgencia,GenericWrapper<TbMiAbono> wp) throws RelativeException {
		GenericWrapper<TbMiAbono> loc = new GenericWrapper<>();
		try {
			if(wp.getEntidades() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL ABONO");
			}
			TbMiAbono a = null;
			BigDecimal total = BigDecimal.ZERO;
			for (TbMiAbono abono : wp.getEntidades()) {
				abono.setTbMiBanco(abono.getTbMiBanco() != null && abono.getTbMiBanco().getId() != null ? abono.getTbMiBanco(): null);
				a=abono;
				total = total.add(abono.getValor());
				this.movCaja.registrarIngreso(
						ProcesoEnum.ABONO,"ABONO",abono.getFormaPago(),abono.getValor(), abono.getUsuarioCreacion(),
						StringUtils.isNotBlank(idAgencia) ? Long.valueOf(idAgencia) : null,
						abono.getTbMiBanco() !=null ? abono.getTbMiBanco().getId() : null, abono.getNumeroCuenta(), abono.getTipoCuenta(),
						abono.getTbMiCliente().getId(),null,abono.getTipoTarjeta(),
						abono.getNumeroTarjeta(),abono.getHabienteTarjeta(),abono.getCedHabienteTarjeta(),null,null,null);
				
			}
			a.setValor(total);
			
			a.setEstado(EstadoMidasEnum.PENDIENTE_HABILITANTE);
			a=this.mis.manageAbono(a);
			loc.setEntidad(a);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL GUARDR ABONO");
		}

		return loc;
	}

	@GET
	@Path("/getAbonosPorIdentificacionCliente")
	@ApiOperation(value = "GenericWrapper<TbMiAbono>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAbono", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiAbono> getAbonosPorIdentificacionCliente(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion) throws RelativeException {
		return getAbonosPorIdentificacionCliente(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), identificacion);
	}

	private PaginatedListWrapper<TbMiAbono> getAbonosPorIdentificacionCliente(PaginatedWrapper pw,
			String identificacion) throws RelativeException {
		PaginatedListWrapper<TbMiAbono> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAbono> abonos = this.mis.getAbonosPorIdentificacionCliente(identificacion);
		if (abonos != null && !abonos.isEmpty()) {
			plw.setTotalResults(abonos.size());
			plw.setList(abonos);
		}
		return plw;
	}
	
	@GET
	@Path("/findPendienteByIdentificacion")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAbono>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAbono", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAbono> findPendienteByIdentificacion(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion
			) throws RelativeException {
		return findPendienteByIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),identificacion);
	}

	private PaginatedListWrapper<TbMiAbono> findPendienteByIdentificacion(PaginatedWrapper pw, String identificacion) throws RelativeException {
		PaginatedListWrapper<TbMiAbono> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAbono> actions = this.mis.findAbonoByEstadoAndIdentificacion(pw, EstadoMidasEnum.PENDIENTE_HABILITANTE, identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAbonoByEstadoAndIdentificacion(EstadoMidasEnum.PENDIENTE_HABILITANTE,identificacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
