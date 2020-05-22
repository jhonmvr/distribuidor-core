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
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiVentaLote;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.wrapper.VentaLoteWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/ventaLoteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "VentaLoteRestController - REST CRUD")
public class VentaLoteRestController extends BaseRestController implements CrudRestControllerInterface<TbMiVentaLote, GenericWrapper<TbMiVentaLote>> {

	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	public VentaLoteRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// DELETE
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiVentaLote>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiVentaLote",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiVentaLote> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiVentaLote> loc = new GenericWrapper<>();
		TbMiVentaLote a = this.mis.findVentaLoteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiVentaLote>", 
			notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiVentaLote", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiVentaLote> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page)*Integer.valueOf(pageSize);
		return findAll(new PaginatedWrapper(
				firstItem, 
				Integer.valueOf(pageSize), 
				sortFields,sortDirections, 
				isPaginated));
		
	}
	
	private PaginatedListWrapper<TbMiVentaLote> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiVentaLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiVentaLote> actions = this.mis.findAllVentaLote(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countVentaLote().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiVentaLote>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiVentaLote", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiVentaLote> persistEntity(GenericWrapper<TbMiVentaLote> wp) throws RelativeException {
		log.info("==> Entra a persistEntity");
		GenericWrapper<TbMiVentaLote> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageVentaLote(wp.getEntidad()));
		return loc;
	}
	/**
	 * Metodo para generar el codigo de venta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/generarCodigoVenta")
	@ApiOperation(
			value = "GenericWrapper<String>", 
			notes = "Metodo para generar el codigo de venta",
			response = GenericWrapper.class) 
	public GenericWrapper<String> generarCodigoVenta(@QueryParam("idAgencia") String idAgencia) throws RelativeException {
		if(StringUtils.isNotBlank(idAgencia)) {
			GenericWrapper<String> loc = new GenericWrapper<>();
			loc.setEntidad(this.mis.generarCodigoVenta(Long.valueOf(idAgencia)));
			return loc;
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "Error el id de la agencia no puede ser nulo");
		}
	}
	/**
	 * Metodo que retorna una lista de venta lote por fechaCreacion desde y hasta, ademas busca por codigo
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param codigoVenta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/findByFechasCodigo")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiVentaLote>", 
			notes = "Metodo que retorna una lista de venta lote por fechaCreacion desde y hasta, ademas busca por codigo", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiVentaLote> findByFechasCodigo(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("codigoVenta") String codigoVenta) throws RelativeException {
		return findByFechasCodigo(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				StringUtils.isNotBlank(fechaDesde) ? new Date(fechaDesde) : null,
				StringUtils.isNotBlank(fechaHasta) ? new Date(fechaHasta): null,
				StringUtils.isNotBlank(codigoVenta) ? codigoVenta : null);
		
	}
	
	private PaginatedListWrapper<TbMiVentaLote> findByFechasCodigo(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta, String codigoVenta) throws RelativeException {
		PaginatedListWrapper<TbMiVentaLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiVentaLote> actions = this.mis.findByFechasCodigo(pw,fechaDesde,fechaHasta,codigoVenta);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countByFechasCodigo(fechaDesde,fechaHasta,codigoVenta).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	/**
	 * Metodo retorna TbMiLote relacionados con la TbMiVentaLote
	 * @param pw
	 * @param idVentaLote
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/findLotesByIdVenta")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiLote>", 
			notes = "Metodo retorna TbMiLote relacionados con la TbMiVentaLote", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiLote> findLotesByIdVenta(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idVentaLote") String idVentaLote) throws RelativeException {
		if(StringUtils.isNotBlank(idVentaLote)) {
			return findLotesByIdVenta(
					new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
					StringUtils.isNotBlank(idVentaLote) ? Long.valueOf(idVentaLote) : null);
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error el id de la venta no puede ser nulo ");
		}
	}
	
	private PaginatedListWrapper<TbMiLote> findLotesByIdVenta(PaginatedWrapper pw, Long idVentaLote) throws RelativeException {
		PaginatedListWrapper<TbMiLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiLote> actions = this.mis.findLotesByIdVenta(pw,idVentaLote);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countLotesByIdVenta(idVentaLote).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	
	@POST
	@Path("/venderLote")
	@ApiOperation(
			value = "GenericWrapper<VentaLoteWrapper>", 
			notes = "Metodo para guardar la informacion de la venta lote",
			response = GenericWrapper.class) 
	public GenericWrapper<VentaLoteWrapper> venderLote(GenericWrapper<VentaLoteWrapper> vlWrapper) throws RelativeException {
		
			GenericWrapper<VentaLoteWrapper> loc = new GenericWrapper<>();
			loc.setEntidad(this.mis.venderLote(vlWrapper.getEntidad()));
			return loc;
	
	}
	
	
	@GET
	@Path("/findPendienteByCodigo")
	@ApiOperation(value = "PaginatedListWrapper<TbMiVentaLote>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAbono", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiVentaLote> findPendienteByVentaLote(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoVentaLote") String codigoVentaLote,
			@QueryParam("identificacion") String identificacion
			) throws RelativeException {
		return findPendienteByVentaLote(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), codigoVentaLote,identificacion);
	}

	private PaginatedListWrapper<TbMiVentaLote> findPendienteByVentaLote(PaginatedWrapper pw, String codigoVentaLote, String identificacion) throws RelativeException {
		PaginatedListWrapper<TbMiVentaLote> plw = new PaginatedListWrapper<>(pw);
		List<TbMiVentaLote> actions = this.mis.findVentaLotePendienteByEstadoAndCodigo(pw, EstadoJoyaEnum.PENDIENTE_HABILITANTE, codigoVentaLote,identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countVentaLoteByEstadoAndCodigo(EstadoJoyaEnum.PENDIENTE_HABILITANTE,codigoVentaLote,identificacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}
}
