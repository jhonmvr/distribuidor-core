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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiDetalleMeta;
import com.relative.midas.model.TbMiMeta;
import com.relative.midas.service.MetaOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/metaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "MetaRestController - REST CRUD")
public class MetaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiMeta, GenericWrapper<TbMiMeta>> {

	@Inject
	Logger log;

	@Inject
	MidasOroService mis;

	@Inject
	MetaOroService metaService;

	public MetaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Delete
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiMeta>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiMeta", response = GenericWrapper.class)
	public GenericWrapper<TbMiMeta> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiMeta> loc = new GenericWrapper<>();
		TbMiMeta a = this.mis.findMetaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMeta>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMeta", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMeta> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbMiMeta> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiMeta> plw = new PaginatedListWrapper<>(pw);
		List<TbMiMeta> actions = this.mis.findAllMeta(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countMeta().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiMeta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiMeta", response = GenericWrapper.class)
	public GenericWrapper<TbMiMeta> persistEntity(GenericWrapper<TbMiMeta> wp) throws RelativeException {
		GenericWrapper<TbMiMeta> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageMeta(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/findByFechaAndNombre")
	@ApiOperation(value = "PaginatedListWrapper<TbMiMeta>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiMeta", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiMeta> findByFechaAndNombre(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("nombre") String nombre) throws RelativeException {
		return findByFechaAndNombre(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), fechaDesde, fechaHasta, nombre);

	}

	private PaginatedListWrapper<TbMiMeta> findByFechaAndNombre(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String nombre) throws RelativeException {
		Date fechaD = null;
		Date fechaH = null;
		PaginatedListWrapper<TbMiMeta> plw = new PaginatedListWrapper<>(pw);
		if (StringUtils.isNotBlank(fechaDesde)) {
			fechaD = MidasOroUtil.formatSringToDate(fechaDesde);
		}
		if (StringUtils.isNotBlank(fechaHasta)) {
			fechaH = MidasOroUtil.formatSringToDate(fechaHasta);
		}

		List<TbMiMeta> actions = this.mis.findMetaByFechaAndNombre(pw, fechaD, fechaH, nombre);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countMetaByFechaAndNombre(fechaD, fechaH, nombre).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@POST
	@Path("/guardarMeta")
	@ApiOperation(value = "GenericWrapper<TbMiMeta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiMeta", response = GenericWrapper.class)
	public GenericWrapper<TbMiMeta> guardarMeta(GenericWrapper<TbMiMeta> wp) throws RelativeException {
		if (wp != null && wp.getEntidad() != null && wp.getEntidad().getTbMiDetalleMetas() != null) {
			List<TbMiDetalleMeta> metas = wp.getEntidad().getTbMiDetalleMetas();
			GenericWrapper<TbMiMeta> loc = new GenericWrapper<>();
			loc.setEntidad(this.metaService.guardarMeta(wp.getEntidad(), metas));
			return loc;
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE LAS METAS");
		}
	}
	
	@POST
	@Path("/detalleMeta")
	@ApiOperation(value = "GenericWrapper<TbMiMeta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de TbMiDetalleMeta", response = GenericWrapper.class)
	public GenericWrapper<TbMiDetalleMeta> detalleMeta(@QueryParam("usuario") String usuario,GenericWrapper<TbMiMeta> wp) throws RelativeException {
		if (wp != null && wp.getEntidad() != null ) {
			
			GenericWrapper<TbMiDetalleMeta> loc = new GenericWrapper<>();
			loc.setEntidades(this.metaService.detalleMeta( wp.getEntidad(), usuario )  );
			return loc;
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE LAS METAS");
		}
	}
	
	@POST
	@Path("/cerrarMeta")
	@ApiOperation(value = "GenericWrapper<TbMiMeta>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiMeta", response = GenericWrapper.class)
	public GenericWrapper<TbMiMeta> cerrarMeta(GenericWrapper<TbMiMeta> wp) throws RelativeException {
		if (wp != null && wp.getEntidad() != null && wp.getEntidad().getTbMiDetalleMetas() != null) {
			List<TbMiDetalleMeta> metas = wp.getEntidad().getTbMiDetalleMetas();
			GenericWrapper<TbMiMeta> loc = new GenericWrapper<>();
			loc.setEntidad(this.metaService.cerrarMeta(wp.getEntidad(), metas));
			return loc;
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE LAS METAS");
		}
	}

}
