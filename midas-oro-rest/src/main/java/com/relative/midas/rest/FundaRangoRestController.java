package com.relative.midas.rest;

import java.math.BigDecimal;
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
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.model.TbMiFundaRango;
import com.relative.midas.model.TbMiFundaRango;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/fundaRangoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "FundaRango -REST CRUD")
public class FundaRangoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiFundaRango, GenericWrapper<TbMiFundaRango>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	public FundaRangoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//
	}

	@POST
	@Path("/deleteRangoFunda")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiLiquidacion", 
	response = GenericWrapper.class)
	public void deleteRangoFunda(GenericWrapper<TbMiFundaRango> entity) throws RelativeException {
		this.mis.deleteRangoFunda(entity.getEntidad());
		
	}

	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFundaRango", response = GenericWrapper.class)
	public GenericWrapper<TbMiFundaRango> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiFundaRango> loc = new GenericWrapper<>();
		TbMiFundaRango a = this.mis.findFundaRangoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFundaRango>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiFundaRango", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFundaRango> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiFundaRango> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiFundaRango> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFundaRango> actions = this.mis.findAllFundaRango(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFundaRango().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiFundaRango", response = GenericWrapper.class)
	public GenericWrapper<TbMiFundaRango> persistEntity(GenericWrapper<TbMiFundaRango> wp) throws RelativeException {
		GenericWrapper<TbMiFundaRango> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageFundaRango(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/findByParams")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiFundaRango", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiFundaRango> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia,
			@QueryParam("estado") String estado, @QueryParam("nombrePaquete") String nombrePaquete,
			@QueryParam("rangoDesde") String rangoDesde, @QueryParam("rangoHasta") String rangoHasta)
			throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated),
				StringUtils.isNotBlank(idAgencia) ? idAgencia : null,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null,
				StringUtils.isNotBlank(nombrePaquete) ? nombrePaquete : null,
				StringUtils.isNotBlank(rangoDesde) ? BigDecimal.valueOf(Double.valueOf(rangoDesde)) : null,
				StringUtils.isNotBlank(rangoHasta) ? BigDecimal.valueOf(Double.valueOf(rangoHasta)) : null);
	}

	private PaginatedListWrapper<TbMiFundaRango> findByParams(PaginatedWrapper pw, String idAgencia, EstadoMidasEnum estado,
			String nombrePaquete, BigDecimal rangoDesde, BigDecimal rangoHasta) throws RelativeException {
		PaginatedListWrapper<TbMiFundaRango> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFundaRango> actions = this.mis.findFundaRagoByParams(pw, idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFundaRagoByParams(idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/validacionRangos")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo para validar existencia de rangos en FundaRango", response = GenericWrapper.class)
	public GenericWrapper<Long> validacionRangos(@QueryParam("rangoInicial") String rangoInicial)
			throws RelativeException {
		GenericWrapper<Long> loc = new GenericWrapper<>();
		log.info("2");
		Long fundaRango = this.mis
				.countValidacionRango(StringUtils.isNotBlank(rangoInicial) ? Long.valueOf(rangoInicial) : null);
		log.info("3");
		loc.setEntidad(fundaRango);
		return loc;
	}

	@POST
	@Path("/generateRangos")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFundaRango", response = GenericWrapper.class)
	public GenericWrapper<TbMiFundaRango> generateRangos(GenericWrapper<TbMiFundaRango> rangoWrap)
			throws RelativeException {
		GenericWrapper<TbMiFundaRango> loc = new GenericWrapper<>();
		TbMiFundaRango a = this.mis.generateFundaRango(rangoWrap.getEntidad());
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/sinAsignar")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFundaRango", response = GenericWrapper.class)
	public GenericWrapper<TbMiFundaRango> sinAsignar() throws RelativeException {
		GenericWrapper<TbMiFundaRango> loc = new GenericWrapper<>();
		List<TbMiFundaRango> a;
		a = this.mis.FundaRangoSinAsignar();
		loc.setEntidades(a);
		return loc;
	}

	@GET
	@Path("/findByIdAgencia")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiFundaRango", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiFundaRango> findByIdAgencia(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("idAgencia") String idAgencia)
			throws RelativeException {
		return findByIdAgencia(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), idAgencia);
	}

	private PaginatedListWrapper<TbMiFundaRango> findByIdAgencia(PaginatedWrapper pw, String idAgencia)
			throws RelativeException {
		try {
			PaginatedListWrapper<TbMiFundaRango> plw = new PaginatedListWrapper<>(pw);
			List<TbMiFundaRango> actions = this.mis.findFundaRangoByIdAgencia(pw, new Long(idAgencia));
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(this.mis.countFundaRangoByIdAgencia(new Long(idAgencia)).intValue());
				plw.setList(actions);
			}
			return plw;
		} catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
		}
	}
	
	@POST
	@Path("/asignarFolleto")
	@ApiOperation(value = "GenericWrapper<TbMiFundaRango>", notes = "Metodo Post persistEntity Retorna GenericWrapper de TbMiFundaRango", response = GenericWrapper.class)
	public GenericWrapper<TbMiFundaRango> asignarFolleto(
			@QueryParam("idAgencia") String idAgencia,
			GenericWrapper<TbMiFundaRango> wp)
			throws RelativeException {
		GenericWrapper<TbMiFundaRango> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.asignarRangoFunda( wp.getEntidad(), StringUtils.isNotBlank(idAgencia)?new Long(idAgencia):null ) );
		return loc;
	}
	
}