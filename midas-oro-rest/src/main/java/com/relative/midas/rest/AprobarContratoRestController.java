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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.AprobacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/aprobarContratoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "BancoRestController - REST CRUD")
public class AprobarContratoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiAprobarContrato, GenericWrapper<TbMiAprobarContrato>> {
	
	@Inject
	Logger log;

	public AprobarContratoRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiAprobarContrato> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiAprobarContrato> loc = new GenericWrapper<>();
		TbMiAprobarContrato a = this.mis.findAprobarContratoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAprobarContrato>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAprobarContrato", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiAprobarContrato> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiAprobarContrato> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiAprobarContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAprobarContrato> actions = this.mis.findAllAprobarContrato(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countBanco().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAprobarContrato>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAprobarContrato", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAprobarContrato> findByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion,
			@QueryParam("estado") String estado,
			@QueryParam("nivelAprobacion") String nivelAprobacion,
			@QueryParam("rolAprobacion") String rolAprobacion,
			@QueryParam("rolAprobacionDos") String rolAprobacionDos,
			@QueryParam("usuarioCreacion") String usuarioCreacion) throws RelativeException {
		
		log.info("===> Ingresa a findByParams " );
		log.info("===> Ingresa a findByParams identificacion " + identificacion );
		log.info("===> Ingresa a findByParams estado " + estado );
		log.info("===> Ingresa a findByParams nivelAprobacion " + nivelAprobacion );
		log.info("===> Ingresa a findByParams rolAprobacion " + rolAprobacion );
		log.info("===> Ingresa a findByParams rolAprobacionDos " + rolAprobacionDos );
		log.info("===> Ingresa a findByParams usuarioCreacion " + usuarioCreacion );
		return findByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), identificacion, estado,  nivelAprobacion,  rolAprobacion,
				rolAprobacionDos,usuarioCreacion);
	}

	private PaginatedListWrapper<TbMiAprobarContrato> findByParams(PaginatedWrapper pw, String identificacion
			,String estado, String nivelAprobacion, String rolAprobacion, String rolAprobacionDos,String usuarioCreacion)
			throws RelativeException {
		log.info("===> Ingresa a findByParams detalle " );
		EstadoAprobacionEnum estadoEnum=StringUtils.isEmpty(estado)?null:MidasOroUtil.getEnumFromString(EstadoAprobacionEnum.class, estado);
		log.info("===> Ingresa a findByParams estadoEnum " + estadoEnum);
		PaginatedListWrapper<TbMiAprobarContrato> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAprobarContrato> actions = this.mis.findAprobarContratoByParams(pw, identificacion, 
				estadoEnum, nivelAprobacion,  rolAprobacion,rolAprobacionDos,usuarioCreacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAprobarContratoByParams(identificacion, estadoEnum,  nivelAprobacion,  
					rolAprobacion,rolAprobacionDos,usuarioCreacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	

	@Override
	@POST
	@Path("/persistEntity")

	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiAprobarContrato> persistEntity(GenericWrapper<TbMiAprobarContrato> wp)
			throws RelativeException {
		GenericWrapper<TbMiAprobarContrato> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageAprobarContrato(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/validarAprobacion")
	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<BigDecimal> validarAprobacion(@QueryParam("identificacion") String identificacion) throws RelativeException {
		GenericWrapper<BigDecimal> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.validarAprobacion(identificacion));
		return loc;
	}

	@POST
	@Path("/guardarAprobacion")
	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiAprobarContrato> guardarAprobacion(GenericWrapper<AprobacionWrapper> wp ) throws RelativeException {
		GenericWrapper<TbMiAprobarContrato> loc = new GenericWrapper<TbMiAprobarContrato>();
		loc.setEntidad(this.mis.guardarAprobacion(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/getEntityWithChilds")
	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiAprobarContrato> getEntityWithChilds(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiAprobarContrato> loc = new GenericWrapper<>();
		TbMiAprobarContrato a = this.mis.findAprobarContratoByIdWithChilds(Long.valueOf(id));
		a.setTbMiJoyaSims(this.mis.findJoyaSimByIdAprobarContrato(a.getId()) );
		a.setTbMiContratoCalculos(this.mis.findContratoCalculoByIdAprobarContrato( a.getId() ) );
		loc.setEntidad(a);
		return loc;
	}
	
	@POST
	@Path("/aprobarPorNivel")
	@ApiOperation(value = "GenericWrapper<TbMiAprobarContrato>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAprobarContrato", response = GenericWrapper.class)
	public GenericWrapper<TbMiAprobarContrato> aprobarPorNivel(GenericWrapper<TbMiAprobarContrato> wp ) throws RelativeException {
		log.info("=================> aprobarPorNivel");
		GenericWrapper<TbMiAprobarContrato> loc = new GenericWrapper<TbMiAprobarContrato>();
		loc.setEntidad(this.mis.aprobarPorNivel(wp.getEntidad()));
		return loc;
	}

}
