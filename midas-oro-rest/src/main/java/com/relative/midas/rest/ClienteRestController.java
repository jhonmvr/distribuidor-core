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
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.service.CompraOroService;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/clienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ClienteRestController - REST CRUD")
public class ClienteRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiCliente, GenericWrapper<TbMiCliente>> {

	public ClienteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

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
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAgencia", response = GenericWrapper.class)
	public GenericWrapper<TbMiCliente> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiCliente> loc = new GenericWrapper<>();
		TbMiCliente a = this.mis.findClienteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCliente", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiCliente> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiCliente> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCliente> actions = this.mis.findAllCliente(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCliente().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiCliente", response = GenericWrapper.class)
	public GenericWrapper<TbMiCliente> persistEntity(GenericWrapper<TbMiCliente> wp) throws RelativeException {
		GenericWrapper<TbMiCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCliente(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/clienteByIdentificacion")
	@ApiOperation(value = "GenericWrapper<TbMiCliente>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCliente", response = GenericWrapper.class)
	public GenericWrapper<TbMiCliente> clienteByIdentificacion(@QueryParam("identificacion") String identificacion)
			throws RelativeException {
		GenericWrapper<TbMiCliente> loc = new GenericWrapper<>();
		TbMiCliente a = this.cos.clienteByIdentificacion(identificacion);
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/clienteByIdentificacionList")
	@ApiOperation(value = "GenericWrapper<TbMiCliente>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCliente", response = GenericWrapper.class)
	public PaginatedListWrapper<TbMiCliente> clienteByIdentificacionList(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion) throws RelativeException {
		return this.clienteByIdentificacionList(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), identificacion);
	}

	private PaginatedListWrapper<TbMiCliente> clienteByIdentificacionList(PaginatedWrapper pw, String identificacion)
			throws RelativeException {
		PaginatedListWrapper<TbMiCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCliente> actions = this.mis.clienteByIdentificacion(pw, identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countClienteByIdentificacion(identificacion).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCliente", 
	response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiCliente> findByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion,@QueryParam("nombre") String nombre,
			@QueryParam("apellido") String apellido,@QueryParam("telefono") String telefono,
			@QueryParam("celular") String celular,@QueryParam("correo") String correo,
			@QueryParam("sector") String sector,@QueryParam("ciudad") String ciudad,
			@QueryParam("nombreReferencia") String nombreReferencia,@QueryParam("telefonoReferencia") String telefonoReferencia,
			@QueryParam("celularReferencia") String celularReferencia,
			@QueryParam("estado") String estado) throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				StringUtils.isNotBlank(identificacion)?identificacion:null,StringUtils.isNotBlank(nombre)?nombre:null,
				StringUtils.isNotBlank(apellido)?apellido:null,StringUtils.isNotBlank(telefono)?telefono:null,
				StringUtils.isNotBlank(celular)?celular:null,StringUtils.isNotBlank(correo)?correo:null,
				StringUtils.isNotBlank(sector)?sector:null,StringUtils.isNotBlank(ciudad)?ciudad:null,
				StringUtils.isNotBlank(nombreReferencia)?nombreReferencia:null,
				StringUtils.isNotBlank(telefonoReferencia)?telefonoReferencia:null,
				StringUtils.isNotBlank(celularReferencia)?celularReferencia:null,
				StringUtils.isNotBlank(estado)? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado):null);
	}

	private PaginatedListWrapper<TbMiCliente> findByParams(PaginatedWrapper pw, String identificacion, String nombre,
			String apellido, String telefono, String celular, String correo, String secto, String ciudad,
			String nombreReferencia, String telefonoReferencia, String celularReferencia, EstadoMidasEnum estado) 
			throws RelativeException {

		PaginatedListWrapper<TbMiCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbMiCliente> actions = this.mis.findClienteByParams(pw, identificacion, nombre, apellido, telefono, celular, correo, 
				secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countClienteByParams(identificacion, nombre, apellido, telefono, celular, correo, 
					secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
