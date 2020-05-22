package com.relative.midas.rest;

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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoOro;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/tipoOroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TipoOroRestController - REST CRUD")
public class TipoOroRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiTipoOro, GenericWrapper<TbMiTipoOro>> {

	public TipoOroRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	MidasOroService mis;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiTipoOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoOro> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiTipoOro> loc = new GenericWrapper<>();
		TbMiTipoOro a = this.mis.findTipoOroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiTipoOro>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiTipoOro", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiTipoOro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiTipoOro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiTipoOro> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoOro> actions = this.mis.findAllTipoOro(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countTipoOro().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiTipoOro>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoOro> persistEntity(GenericWrapper<TbMiTipoOro> wp) throws RelativeException {
		GenericWrapper<TbMiTipoOro> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageTipoOro(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/getEntityByEstado")
	@ApiOperation(value = "GenericWrapper<TbMiTipoOro>", notes = "Metodo getEntityByEstado Retorna wrapper de entidades encontradas en TbMiTipoOro", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiTipoOro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, @QueryParam("estado") String estado)
			throws RelativeException {
		return findAllTipoOroByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), estado);
	}

	private PaginatedListWrapper<TbMiTipoOro> findAllTipoOroByParams(PaginatedWrapper pw, String estado)
			throws RelativeException {
		PaginatedListWrapper<TbMiTipoOro> plw = new PaginatedListWrapper<>(pw);
		List<TbMiTipoOro> actions = null;

		actions = this.mis.findTipoOroByAll(pw,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countfindTipoOroByAll(
					StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado)
							: null)
					.intValue());
			plw.setList(actions);
		}

		return plw;
	}
	
	@GET
	@Path("/findTipoOroByQuilate")
	@ApiOperation(value = "GenericWrapper<TbMiTipoOro>", notes = "Metodo findTipoOroByQuilate Retorna wrapper de entidades encontradas en TbMiTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbMiTipoOro> findTipoOroByNombre(@QueryParam("quilate") String quilate)throws RelativeException {
		GenericWrapper<TbMiTipoOro> loc = new GenericWrapper<TbMiTipoOro>();
		loc.setEntidad(this.mis.findTipoOroByQuilate(quilate));
		return loc;
		
	}

}
