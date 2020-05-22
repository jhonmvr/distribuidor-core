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
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.TipoBodegaEnum;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/bodegaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "BodegaRestController - REST CRUD")
public class BodegaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiBodega, GenericWrapper<TbMiBodega>> {

	public BodegaRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiBodega>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiBodega", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiBodega> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiBodega> loc = new GenericWrapper<>();
		TbMiBodega a =this.mis.findBodegaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiBodega>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiBodega", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiBodega> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiBodega> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiBodega> plw = new PaginatedListWrapper<>(pw);
		List<TbMiBodega> actions = this.mis.findAllBodega(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countBodega().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiBodega>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiBodega", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiBodega> persistEntity(GenericWrapper<TbMiBodega> wp) throws RelativeException {
		GenericWrapper<TbMiBodega> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageBodega(wp.getEntidad()));
		return loc;
	}
	/**
	 * Metodo que retorna una lista de bodegas por tipo bodega y id agencia
	 * @param idAgencia
	 * @param tipoBodega
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@GET
	@Path("/getBodegaByTipoAgencia")
	@ApiOperation(value = "GenericWrapper<TbMiBodega>", notes = "Metodo que retorna una lista de bodegas por tipo bodega y id agencia", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiBodega> getBodegaByAgencia(
			@QueryParam("idAgencia") String idAgencia,
			@QueryParam("tipoBodega") String tipoBodega) throws RelativeException {
		GenericWrapper<TbMiBodega> loc = new GenericWrapper<>();
		if(StringUtils.isNotBlank(idAgencia) && StringUtils.isNotBlank(tipoBodega)) {
			TbMiBodega a = this.mis.bodegasByAgencia(Long.valueOf(idAgencia), MidasOroUtil.getEnumFromString(TipoBodegaEnum.class, tipoBodega));
			loc.setEntidad(a);
		}
		return loc;
	}

}
