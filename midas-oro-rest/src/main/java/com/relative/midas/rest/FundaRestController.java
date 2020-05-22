package com.relative.midas.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.service.ParametrosSingleton;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.websocket.WebSocketClient;
import com.relative.midas.wrapper.MessageWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/fundaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Funda -REST CRUD")
public class FundaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiFunda, GenericWrapper<TbMiFunda>> {

	public FundaRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Inject
	ParametrosSingleton parametros;


	@Inject
	MidasOroService mis;
	@Inject
	CompraOroService cos;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}


	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFunda>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiFunda", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiFunda> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiFunda> loc = new GenericWrapper<>();
		TbMiFunda a =this.mis.findFundaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFunda>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiFunda", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFunda> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiFunda> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiFunda> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFunda> actions = this.mis.findAllFunda(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFunda().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiFunda>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiFunda", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiFunda> persistEntity(GenericWrapper<TbMiFunda> wp) throws RelativeException {
		GenericWrapper<TbMiFunda> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageFunda(wp.getEntidad()));
		return loc;
	}
	
	
	@GET
	@Path("/reservarFunda")
	@ApiOperation(value = "GenericWrapper<TbMiFunda>", notes = "Metodo reservarFunda Retorna wrapper de entidad encontrad en TbMiFunda", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiFunda> reservarFunda(@QueryParam("idAgencia") String idAgencia,@QueryParam("usuario") String usuario ) throws RelativeException {
		GenericWrapper<TbMiFunda> loc = new GenericWrapper<>();
		TbMiFunda a =this.cos.reservarFunda(StringUtils.isNotBlank(idAgencia)?Long.valueOf(idAgencia):null,usuario);
		loc.setEntidad(a);
		try {
			final WebSocketClient rwsc = new WebSocketClient(new URI(
					this.parametros.getParametros().get(MidasOroConstantes.PARAM_EXT_ROOT_WEBSOCKET).getValor()));
			this.sendMessage(rwsc, "FUNDA "+idAgencia,0, false);
			//rwsc.onClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loc;
	}
	
	@GET
	@Path("/findByRango")
	@ApiOperation(value = "PaginatedListWrapper<TbMiFunda>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiFunda", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiFunda> findByRango(
			@QueryParam("idRango") @DefaultValue("1") String idRango,
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findByRango(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),idRango);
	}
	
	private PaginatedListWrapper<TbMiFunda> findByRango(PaginatedWrapper pw, String idRango) throws RelativeException {
		PaginatedListWrapper<TbMiFunda> plw = new PaginatedListWrapper<>(pw);
		List<TbMiFunda> actions = this.mis.findFundaByRango(pw,StringUtils.isNotEmpty(idRango)?new Long(idRango):null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countFundaByRango(StringUtils.isNotEmpty(idRango)?new Long(idRango):null).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	
	private void sendMessage(WebSocketClient rwsc, String data, int counter, boolean addCount) {
		MessageWrapper mw = new MessageWrapper();
		mw.setHash("");
		mw.setMensaje("fundas");
		mw.setLastId(data);
		mw.setCounter(Long.valueOf(counter));
		mw.setAddCount(addCount);
		Gson g = new Gson();
		if (rwsc != null) {
			rwsc.sendMessage(g.toJson(mw));
		} else {
		}

	}

}
