package com.relative.midas.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.relative.midas.model.TbMiMovEntreCaja;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/movEntreCajasRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "MovEntreCajaRestController - REST CRUD")
public class MovEntreCajaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiMovEntreCaja, GenericWrapper<TbMiMovEntreCaja>> {
	
	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	public MovEntreCajaRestController() throws RelativeException {
		super();
	}

	@Override
	@DELETE
	@Path("/deleteEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiMovEntreCaja>", 
			notes = "Elimina un movimiento entre caja",
			response = GenericWrapper.class) 
	public void deleteEntity(@QueryParam("id") String id) throws RelativeException {
		if(StringUtils.isBlank(id)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id no puede ser nulo");
		}else {
			this.mis.deleteMovEntreCaja(Long.valueOf(id));
		}
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiMovEntreCaja>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiMovEntreCaja",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiMovEntreCaja> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiMovEntreCaja> loc = new GenericWrapper<>();
		TbMiMovEntreCaja a =this.mis.findMovEntreCajaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiMovEntreCaja>", 
			notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMovEntreCaja", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMovEntreCaja> listAllEntities(
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
	
	private PaginatedListWrapper<TbMiMovEntreCaja> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiMovEntreCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiMovEntreCaja> actions = this.mis.findAllMovEntreCaja(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countMovEntreCaja().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiMovEntreCaja>",
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiMovEntreCaja", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiMovEntreCaja> persistEntity(GenericWrapper<TbMiMovEntreCaja> wp) throws RelativeException {
		log.info("Entra persistEntity");
		GenericWrapper<TbMiMovEntreCaja> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageMovEntreCaja(wp.getEntidad()));
		return loc;
	}
	/**
	 * Lista los Movimientos entre caja por agencia origen, agencia destino, fecha desde y fecha hasta en basea a fecha creacion
	 * @param pw
	 * @param idAgenciaOrigen
	 * @param idAgenciaDestino
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	@GET
	@Path("/findByParams")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiMovEntreCaja>", 
			notes = "Lista los Movimientos entre caja por agencia origen, agencia destino, fecha desde y fecha hasta en basea a fecha creacion", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMovEntreCaja> findByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idAgenciaOrigen") String idAgenciaOrigen,
			@QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta) throws RelativeException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss aaa");
			return findByParams(
					new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
					StringUtils.isNotBlank(idAgenciaOrigen) ? Long.valueOf(idAgenciaOrigen) : null,
					StringUtils.isNotBlank(fechaDesde) ? formatter.parse(fechaDesde) : null,
					StringUtils.isNotBlank(fechaHasta) ? formatter.parse(fechaHasta) : null);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error al convertir fechas");
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	
	private PaginatedListWrapper<TbMiMovEntreCaja> findByParams(PaginatedWrapper pw,
			Long idAgenciaOrigen, Date fechaDesde, Date fechaHasta) throws RelativeException {
		try {
			PaginatedListWrapper<TbMiMovEntreCaja> plw = new PaginatedListWrapper<>(pw);
			List<TbMiMovEntreCaja> actions = this.mis.findMovEntreCajaByParams(pw,idAgenciaOrigen,
					fechaDesde,fechaHasta);
			if (actions != null && !actions.isEmpty()) {
				plw.setTotalResults(this.mis.countMovEntreCajaByParams(idAgenciaOrigen,
						fechaDesde,fechaHasta).intValue());
				plw.setList(actions);
			}
			return plw;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	
}
