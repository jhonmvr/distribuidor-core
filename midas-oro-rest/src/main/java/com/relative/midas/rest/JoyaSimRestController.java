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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.model.TbMiJoyaSim;
import com.relative.midas.service.MidasOroService;

import io.swagger.annotations.ApiOperation;

@Path("/joyaSimRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JoyaSimRestController extends BaseRestController implements CrudRestControllerInterface<TbMiJoyaSim, GenericWrapper<TbMiJoyaSim>> {

	public JoyaSimRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	MidasOroService mis;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaSim>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiJoyaSim", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiJoyaSim> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiJoyaSim> loc = new GenericWrapper<>();
		TbMiJoyaSim a =this.mis.findJoyaSimById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiJoyaSim>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiJoyaSim", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiJoyaSim> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiJoyaSim> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiJoyaSim> plw = new PaginatedListWrapper<>(pw);
		List<TbMiJoyaSim> actions = this.mis.findAllJoyaSim(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countJoyaSim().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaSim>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiJoyaSim", 
	response = GenericWrapper.class)
	
	public GenericWrapper<TbMiJoyaSim> persistEntity(GenericWrapper<TbMiJoyaSim> wp) throws RelativeException {
		GenericWrapper<TbMiJoyaSim> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageJoyaSim(wp.getEntidad()));
		return loc;
	}
	
	

	@GET
	@Path("/getEntityByidCotizacion")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaSim>", notes = "Metodo getEntityByidCotizacion Retorna wrapper de entidades encontradas en TbMiJoyaSim", 
	response = GenericWrapper.class)
		public PaginatedListWrapper<TbMiJoyaSim> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("id") String  id) 
			throws RelativeException {
		if( id != null && !id.isEmpty()  ) {
			return findAllTipoJoyaByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
					sortFields, sortDirections, isPaginated),id);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ID DE COTIZCION NO PUEDE SER NULO O VACIO ");
		}
		
	}
	private PaginatedListWrapper<TbMiJoyaSim> findAllTipoJoyaByParams(PaginatedWrapper pw,
			String id) throws RelativeException {
		try {
			PaginatedListWrapper<TbMiJoyaSim> plw = new PaginatedListWrapper<>(pw);
			List<TbMiJoyaSim> actions = null;
			
				actions = this.mis.findJoyaSimByAll(pw, Long.valueOf(id));
				if (actions != null && !actions.isEmpty()) {
					plw.setTotalResults(this.mis.countfindJoyaSimByAll(Long.valueOf(id)).intValue());
					plw.setList(actions);
				}

			return plw;
		}catch (RelativeException e) {
			throw e;
		}catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NumberFormatException ERROR FORMATO NUMERICO " + e.getMessage());
		} 
		catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Exception ERROR GENERAL  findAllTipoJoyaByParams " + e.getMessage());
		}
	}

	@GET
	@Path("/findByIdAprobarContrato")
	@ApiOperation(value = "GenericWrapper<TbMiJoyaSim>", notes = "Metodo getEntityByidCotizacion Retorna wrapper de entidades encontradas en TbMiJoyaSim", 
	response = GenericWrapper.class)
		public GenericWrapper<TbMiJoyaSim> findByIdAprobarContrato(@QueryParam("idAprobarContrato") String idAprobarContrato) throws RelativeException{
		try {
			GenericWrapper<TbMiJoyaSim> loc = new GenericWrapper<TbMiJoyaSim>();
			loc.setEntidades(this.mis.findJoyaSimByIdAprobarContrato(new Long(idAprobarContrato)));
			
			return loc;
		} catch (RelativeException e) {
			throw e;
		}catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ID APROBAR CONTRATO INCORRECTO");
		}
		
	}
	
	

}
