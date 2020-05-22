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

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiDetalleMeta;
import com.relative.midas.service.MetaOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/detalleMetaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "DetalleMetaRestController - REST CRUD")
public class DetalleMetaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiDetalleMeta, GenericWrapper<TbMiDetalleMeta>> {
	
	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	@Inject
	MetaOroService metaService;
	
	
	public DetalleMetaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiDetalleMeta>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDetalleMeta",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiDetalleMeta> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiDetalleMeta> loc = new GenericWrapper<>();
		TbMiDetalleMeta a =this.mis.findDetalleMetaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiDetalleMeta>", 
			notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiDetalleMeta", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiDetalleMeta> listAllEntities(
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
	
	private PaginatedListWrapper<TbMiDetalleMeta> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiDetalleMeta> plw = new PaginatedListWrapper<>(pw);
		List<TbMiDetalleMeta> actions = this.mis.findAllDetalleMeta(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countDetalleMeta().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDetalleMeta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiDetalleMeta", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiDetalleMeta> persistEntity(GenericWrapper<TbMiDetalleMeta> wp) throws RelativeException {
		GenericWrapper<TbMiDetalleMeta> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageDetalleMeta(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/calcularMeta")
	@ApiOperation(
			value = "GenericWrapper<TbMiDetalleMeta>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDetalleMeta",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiDetalleMeta> calcularMeta(@QueryParam("porcentajeComercial") String porcentajeComercial,
			@QueryParam("porcentajeGerencial") String porcentajeGerencial,
			@QueryParam("fechaCierre") String fechaCierre,
			@QueryParam("usuario") String usuario,
			@QueryParam("nombreMeta") String nombreMeta) throws RelativeException {
		try {
			GenericWrapper<TbMiDetalleMeta> loc = new GenericWrapper<>();
			List<TbMiDetalleMeta> a =this.metaService.calcularMeta( StringUtils.isNotBlank(porcentajeComercial)?new BigDecimal(porcentajeComercial.replaceAll(",", "")):BigDecimal.ZERO,
					StringUtils.isNotBlank(porcentajeGerencial)?new BigDecimal(porcentajeGerencial.replaceAll(",", "")):BigDecimal.ZERO,
					MidasOroUtil.formatSringToDate(fechaCierre),
					usuario,
					nombreMeta);
			loc.setEntidades(a);
			return loc;
		} catch (Exception e) {
			throw e;
		}
	}
	

}
