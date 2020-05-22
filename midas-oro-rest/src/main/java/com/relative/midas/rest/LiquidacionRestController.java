package com.relative.midas.rest;

import java.net.URI;
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

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.model.TbMiLiquidacion;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.ParametrosSingleton;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.websocket.WebSocketClient;
import com.relative.midas.wrapper.MessageWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/liquidacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Liquidacion -REST CRUD")
public class LiquidacionRestController extends BaseRestController implements CrudRestControllerInterface<TbMiLiquidacion, GenericWrapper<TbMiLiquidacion>> {

	public LiquidacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	MidasOroService mis;
	@Inject
	CompraOroService cos;
	@Inject
	ParametrosSingleton parametros;

	@POST
	@Path("/deleteFolleto")
	@ApiOperation(value = "GenericWrapper<TbMiLiquidacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiLiquidacion", 
	response = GenericWrapper.class)
	public void deleteFolleto(GenericWrapper<TbMiFolletoLiquidacion> entity) throws RelativeException {
		this.mis.deleteFolletoLiquidacion(entity.getEntidad());
		
	}


	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiLiquidacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiLiquidacion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiLiquidacion> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiLiquidacion> loc = new GenericWrapper<>();
		TbMiLiquidacion a =this.mis.findLiquidacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiLiquidacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiLiquidacion", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiLiquidacion> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiLiquidacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiLiquidacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiLiquidacion> actions = this.mis.findAllLiquidacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countLiquidacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiLiquidacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiLiquidacion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiLiquidacion> persistEntity(GenericWrapper<TbMiLiquidacion> wp) throws RelativeException {
		GenericWrapper<TbMiLiquidacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageLiquidacion(wp.getEntidad()));
		return loc;
	}
	
	
	@GET
	@Path("/reservarLiquidacion")
	@ApiOperation(value = "GenericWrapper<TbMiLiquidacion>", notes = "Metodo reservarLiquidacion Retorna wrapper de entidad encontrad en TbMiLiquidacion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiLiquidacion> reservarLiquidacion(@QueryParam("idAgencia")  String idAgencia,@QueryParam("usuario") String usuario ) throws RelativeException {
		GenericWrapper<TbMiLiquidacion> loc = new GenericWrapper<>();
		TbMiLiquidacion a =this.cos.reservarLiquidacion(Long.valueOf(idAgencia),usuario);
		loc.setEntidad(a);
		try {
			final WebSocketClient rwsc = new WebSocketClient(new URI(
					this.parametros.getParametros().get(MidasOroConstantes.PARAM_EXT_ROOT_WEBSOCKET).getValor()));
			this.sendMessage(rwsc, "LIQUIDACION "+ idAgencia,0, false);
			//rwsc.onClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loc;
	}
	
	@GET
	@Path("/findByFolleto")
	@ApiOperation(value = "PaginatedListWrapper<TbMiLiquidacion>",
	notes = "Busca liquidaciones por folleto", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiLiquidacion> findByFolleto(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idFolletoLiquidacion") String idFolletoLiquidacion
			) throws RelativeException {
		if(StringUtils.isBlank(idFolletoLiquidacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Id del folleto nulo");
		}
		return findByFolleto(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf(idFolletoLiquidacion));
	}
	
	private PaginatedListWrapper<TbMiLiquidacion> findByFolleto(PaginatedWrapper pw, 
			Long idFolletoLiquidacion) throws RelativeException {
		PaginatedListWrapper<TbMiLiquidacion> plw = new PaginatedListWrapper<>(pw);
		List<TbMiLiquidacion> actions = this.mis.findLiquidacionByFolleto(pw, idFolletoLiquidacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countLiquidacionByParams(idFolletoLiquidacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	private void sendMessage(WebSocketClient rwsc, String data, int counter, boolean addCount) {
		MessageWrapper mw = new MessageWrapper();
		mw.setHash("");
		mw.setMensaje("liquidacion");
		mw.setLastId(data);
		mw.setCounter(Long.valueOf(counter));
		mw.setAddCount(addCount);
		Gson g = new Gson();
		if (rwsc != null) {
			rwsc.sendMessage(g.toJson(mw));
		} else {
		}

	}


	@Override
	public void deleteEntity(String idEntity) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
}
