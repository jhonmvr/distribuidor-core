package com.relative.midas.rest;

import java.util.ArrayList;
import java.util.Date;
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
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/loteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Lote -REST CRUD")
public class LoteRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiLote, GenericWrapper<TbMiLote>> {

	public LoteRestController() throws RelativeException {
		super();
	}

	@Inject
	MidasOroService mis;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenerecWrapper<TbMiLote>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiLote", response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		TbMiLote a = this.mis.findLoteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiLote>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiLote", response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> persistEntity(GenericWrapper<TbMiLote> wp) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageLote(wp.getEntidad()));
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiLote>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiLote", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiLote> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiLote> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiLote> actions = this.mis.findAllLote(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countLote().intValue());
			plw.setList(actions);
		}
		return plw;

	}

	@POST
	@Path("/listAllEntitiesByParam")
	@ApiOperation(value = "PaginatedListWrapper<TbMiLote>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiLote", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiLote> listAllEntitiesByParam(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoOro") String tipoOro, @QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta, @QueryParam("nombreLote") String nombreLote,
			@QueryParam("usuarioCreacion") String usuarioCreacion,
			GenericWrapper<String> estados) throws RelativeException {
		List<EstadoMidasEnum> estadosEnum = null;
		if(estados.getEntidades() != null && !estados.getEntidades().isEmpty() ) {
			estadosEnum = new ArrayList<>();
			for (String estado : estados.getEntidades() ) {
				estadosEnum.add(StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null);
			}
		}
		return findAllPaged(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated), 
				StringUtils.isNotBlank(nombreLote)? nombreLote: null, 
				StringUtils.isNotBlank(usuarioCreacion)?usuarioCreacion:null,
				StringUtils.isNotBlank(tipoOro)?Long.valueOf(tipoOro): null,
				StringUtils.isNotBlank(fechaDesde)? MidasOroUtil.formatSringToDate(fechaDesde):null,
				StringUtils.isNotBlank(fechaHasta)? MidasOroUtil.formatSringToDate(fechaHasta):null,
				estadosEnum);
	}

	private PaginatedListWrapper<TbMiLote> findAllPaged(PaginatedWrapper pw, String nombreLote, String usuarioCreacion,
			Long tipoOro, Date fechaDesde, Date fechaHasta, List<EstadoMidasEnum> estados) throws RelativeException {
		PaginatedListWrapper<TbMiLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiLote> actions = this.mis.findLoteByParamPaged(nombreLote, fechaDesde, fechaHasta, usuarioCreacion, 
				tipoOro, estados, pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countLoteByParamPaged(nombreLote, fechaDesde, fechaHasta, usuarioCreacion, 
					tipoOro, estados).intValue());
			plw.setList(actions);
		}
		return plw;

	}

	/**
	 * Metodo que retorna una lista de lotes dos Estados
	 * 
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/getLotesByEstados")
	@ApiOperation(value = "GenerecWrapper<TbMiLote>", notes = "Metodo que retorna una lista de lotes dos Estados", response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> getLotesByEstados(@QueryParam("estado1") EstadoMidasEnum estado1,
			@QueryParam("estado2") EstadoMidasEnum estado2) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		List<TbMiLote> a = this.mis.lotesByAgenciaEstados(estado1, estado2);
		loc.setEntidades(a);
		return loc;
	}
	/**
	 * Metodo persiste una lista de lotes y los retorna
	 * @param wp
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@POST
	@Path("/persistListtEntity")
	@ApiOperation(value = "GenericWrapper<TbMiLote>",
	notes = "Metodo POST persistListEntity retorna GenericWrapper con la informacion de lotes insertados/actualizados",
	response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> persistListtEntity(GenericWrapper<TbMiLote> wp) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		//loc.setEntidades(this.mis.manageLotes(wp.getEntidades()));
		return loc;
	}
	
	@POST
	@Path("/persistCajaFuerteMatriz")
	@ApiOperation(value = "GenericWrapper<TbMiLote>",
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiLote",
	response = GenericWrapper.class)
	public GenericWrapper<TbMiLote> persistCajaFuerteMatriz(GenericWrapper<TbMiLote> wp) throws RelativeException {
		GenericWrapper<TbMiLote> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.persistLoteCajaFuerteMatriz(wp.getEntidad()));
		return loc;
	}

}
