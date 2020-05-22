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
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiDetalleCaja;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.MovimientoCajaOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/detalleCajaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "DetalleCajaRestController - REST CRUD")
public class DetalleCajaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiDetalleCaja, GenericWrapper<TbMiDetalleCaja>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;


	public DetalleCajaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDetalleCaja>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDetalleCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiDetalleCaja> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiDetalleCaja> loc = new GenericWrapper<>();
		TbMiDetalleCaja a = this.mis.findDetalleCajaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiDetalleCaja>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiDetalleCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiDetalleCaja> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiDetalleCaja> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiDetalleCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiDetalleCaja> actions = this.mis.findAllDetalleCaja(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCaja().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDetalleCaja>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiDetalleCaja", response = GenericWrapper.class)
	public GenericWrapper<TbMiDetalleCaja> persistEntity(GenericWrapper<TbMiDetalleCaja> wp) throws RelativeException {
		GenericWrapper<TbMiDetalleCaja> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageDetalleCaja(wp.getEntidad()));
		return loc;
	}
	/*
	@GET
	@Path("/findValorCierre")
	public GenericWrapper<BigDecimal> findValorCierre(@QueryParam("idAgencia") String idAgencia ) throws RelativeException{
		log.info("==============> findValorCierre " + idAgencia );
		if(idAgencia == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"validacion de "); 
		}else {
		GenericWrapper<BigDecimal> wp= new GenericWrapper<>();
		TbMiDetalleCaja cj= this.mis.findValorUltimoCierreCaja( Long.valueOf(idAgencia) );
		wp.setEntidad( cj.getValor() );		
		return wp;
		}
	}*/
	
	@GET
	@Path("/findByIdCorteCaja")
	@ApiOperation(value = "PaginatedListWrapper<TbMiDetalleCaja>", notes = "Metodo Get findByParam Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiDetalleCaja", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiDetalleCaja> findByIdCorteCaja(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCorteCaja") String idCorteCaja) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findByIdCorteCaja(StringUtils.isNotBlank(idCorteCaja) ? new Long(idCorteCaja): null,
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja, PaginatedWrapper pw)
			throws RelativeException {
		PaginatedListWrapper<TbMiDetalleCaja> plw = new PaginatedListWrapper<>(pw);
		List<TbMiDetalleCaja> actions = this.mis.findDetalleCajaByIdCorteCaja(idCorteCaja, pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(
					this.mis.countDetalleCajaByIdCorteCaja(idCorteCaja)
							.intValue());
			plw.setList(actions);
		}
		return plw;
	}
		
}
