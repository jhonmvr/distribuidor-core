package com.relative.midas.rest;

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
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiContactabilidad;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/contactabilidadRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ContactabilidadRestController - REST CRUD")
public class ContactabilidadRestController extends BaseRestController implements CrudRestControllerInterface<TbMiContactabilidad, GenericWrapper<TbMiContactabilidad>> {
	
	@Inject
	Logger log;
	
	@Inject
	MidasOroService mis;
	
	public ContactabilidadRestController() throws RelativeException {
		super();
	}

	@Override
	@DELETE
	@Path("/deleteEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiContactabilidad>", 
			notes = "Elimina un movimiento entre caja",
			response = GenericWrapper.class) 
	public void deleteEntity(@QueryParam("id") String id) throws RelativeException {
		if(StringUtils.isBlank(id)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El id no puede ser nulo");
		}else {
			this.mis.deleteContactabilidad(Long.valueOf(id));
		}
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(
			value = "GenericWrapper<TbMiContactabilidad>", 
			notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiContactabilidad",
			response = GenericWrapper.class) 
	public GenericWrapper<TbMiContactabilidad> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiContactabilidad> loc = new GenericWrapper<>();
		TbMiContactabilidad a =this.mis.findContactabilidadById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiContactabilidad>", 
			notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiContactabilidad", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiContactabilidad> listAllEntities(
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
	
	private PaginatedListWrapper<TbMiContactabilidad> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiContactabilidad> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContactabilidad> actions = this.mis.findAllContactabilidad(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContactabilidad().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiContactabilidad>",
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiContactabilidad", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiContactabilidad> persistEntity(GenericWrapper<TbMiContactabilidad> wp) throws RelativeException {
		log.info("Entra persistEntity");
		GenericWrapper<TbMiContactabilidad> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageContactabilidad(wp.getEntidad()));
		return loc;
	}
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param idCliente
	 * @param idAgente
	 * @param idAgencia
	 * @param idContrato
	 * @param estado
	 * @return
	 * @throws RelativeException
	 */
	@GET
	@Path("/findByParams")
	@ApiOperation(
			value = "PaginatedListWrapper<TbMiContactabilidad>", 
			notes = "Busca registros de contactabilidad por parametros, cliente, agente, agencia, contrato y estado", 
			response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiContactabilidad> findByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCliente") String idCliente, @QueryParam("idAgente") String idAgente,
			@QueryParam("idAgencia") String idAgencia, @QueryParam("idContrato") String idContrato,
			@QueryParam("estado") String estado) throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				StringUtils.isNotBlank(idCliente) ? Long.valueOf(idCliente) : null,
				StringUtils.isNotBlank(idAgente) ? Long.valueOf(idAgente) : null,
				StringUtils.isNotBlank(idAgencia) ? Long.valueOf(idAgencia) : null,
				StringUtils.isNotBlank(idContrato) ? Long.valueOf(idContrato) : null,
				StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado) : null);
	}
	
	private PaginatedListWrapper<TbMiContactabilidad> findByParams(PaginatedWrapper pw,
			Long idCliente, Long idAgente, Long idAgencia, Long idContrato, EstadoMidasEnum estado) throws RelativeException {
		PaginatedListWrapper<TbMiContactabilidad> plw = new PaginatedListWrapper<>(pw);
		List<TbMiContactabilidad> actions = this.mis.findContactabilidadByParams(pw, idCliente, 
				idAgente, idAgencia, idContrato, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countContactabilidadByParams(idCliente, 
					idAgente, idAgencia, idContrato, estado).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST
	@Path("/persistEntityWithContrato")
	@ApiOperation(value = "GenericWrapper<TbMiContactabilidad>",
	notes = "Guarda contactabilidad y actualiza el contrato con la ultima gestion", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiContactabilidad> persistEntityWithContrato(GenericWrapper<TbMiContactabilidad> wp)
			throws RelativeException {
		GenericWrapper<TbMiContactabilidad> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageContactabilidadWithContrato(wp.getEntidad()));
		return loc;
	}
	
}
