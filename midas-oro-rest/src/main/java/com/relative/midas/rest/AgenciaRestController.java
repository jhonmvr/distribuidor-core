package com.relative.midas.rest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/agenciaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " AgenciasRestController - REST CRUD")
public class AgenciaRestController extends BaseRestController implements CrudRestControllerInterface<TbMiAgencia, GenericWrapper<TbMiAgencia>> {

	public AgenciaRestController() throws RelativeException {
		super();
	}
	
	@Inject
	Logger log;
	@Inject
	MidasOroService mis;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Eliminar
	}
   
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiAgencia", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgencia> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiAgencia> loc = new GenericWrapper<>();
		TbMiAgencia a =this.mis.findAgenciaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAgencia>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgencia", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAgencia> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbMiAgencia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiAgencia> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAgencia> actions = this.mis.findAllAgencia(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAgencia().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAgencia", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgencia> persistEntity(GenericWrapper<TbMiAgencia> wp) throws RelativeException {
		GenericWrapper<TbMiAgencia> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageAgencia(wp.getEntidad()));
		return loc;
	}
	/**
	 * Lista todas las agencias por joyas estado
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param estado
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	@POST
	@Path("/listByJoyasEstado")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAgencia>",
	notes = "Metodo Get Retorna PaginatedListWrapper<TbMiAgencia> con todas las agencias con joyas por estado", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAgencia> listByJoyasEstado(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			GenericWrapper<String> estados) throws RelativeException {
		List<EstadoJoyaEnum> estadosEnum = new ArrayList<>();
		if(estados.getEntidades() != null && !estados.getEntidades().isEmpty() ) {
			for (String estado : estados.getEntidades() ) {
				estadosEnum.add(StringUtils.isNotBlank(estado) ? MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estado) : null);
			}
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "Error estados vacio");
		}
		return listByJoyasEstado(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), estadosEnum);
		
	}
	/**
	 * Lista todas las agencias por joyas estado
	 * @param pw
	 * @param estado
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	private PaginatedListWrapper<TbMiAgencia> listByJoyasEstado(PaginatedWrapper pw,
			List<EstadoJoyaEnum> estado) throws RelativeException {
		PaginatedListWrapper<TbMiAgencia> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAgencia> actions = this.mis.findAgenciaByJoyaEstado(pw, estado);
		if (actions != null && !actions.isEmpty()) {
			actions = actions.stream().distinct().collect(Collectors.toList());
			plw.setList(actions);
		}
		
		return plw;
	}
	
	@GET
	@Path("/findByCodigoAndNombre")
	@ApiOperation(value = "PaginatedListWrapper<TbMiAgencia>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgencia", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbMiAgencia> findByCodigoAndNombre(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigo") String codigo,
			@QueryParam("nombre") String nombre
			) throws RelativeException {
		return findByCodigoAndNombre(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),codigo, nombre);
	}

	private PaginatedListWrapper<TbMiAgencia> findByCodigoAndNombre(PaginatedWrapper pw, String codigo, String nombre) throws RelativeException {
		PaginatedListWrapper<TbMiAgencia> plw = new PaginatedListWrapper<>(pw);
		List<TbMiAgencia> actions = this.mis.findAgenciaByCodigoAndNombre(pw, codigo, nombre);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countAgenciaByCodigoAndNombre(codigo, nombre).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/findByEstado")
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>",
	notes = "Lista agencias por estado", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgencia> findAgenciaByEstado(
			@QueryParam("estado") String estado) throws RelativeException {
		if(StringUtils.isBlank(estado)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error el estado no puede ser nulo");
		}
		GenericWrapper<TbMiAgencia> loc = new GenericWrapper<>();
		loc.setEntidades(this.mis.findAgenciaByEstado(MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado)));
		return loc;
	}
	
	
	@GET
	@Path("/generateCodigoAgencia")
	@ApiOperation(value = "GenericWrapper<BigInteger>",
	notes = "Codigo de la agencia a crear", 
	response = GenericWrapper.class)
	public GenericWrapper<BigInteger> generateCodigoAgencia() throws RelativeException {		
		GenericWrapper<BigInteger> loc = new GenericWrapper<>();		
		BigInteger cod = mis.generateCodigoAgencia();
		if(cod == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL GENERAR CODIGO DE LA AGENCIA");
		}
		loc.setEntidad(cod);
		return loc;
	}
	
	@POST
	@Path("/registrarAgencia")
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiAgencia", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgencia> registrarAgencia(GenericWrapper<TbMiAgencia> wp) throws RelativeException {
		GenericWrapper<TbMiAgencia> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.registrarAgencia(wp.getEntidad()));
		return loc;
	}
	
	
	@GET
	@Path("/findAgenciaMatriz")
	@ApiOperation(value = "GenericWrapper<TbMiAgencia>",
	notes = "Codigo de la agencia a crear", 
	response = GenericWrapper.class)
	public GenericWrapper<TbMiAgencia> findAgenciaMatriz() throws RelativeException {		
		GenericWrapper<TbMiAgencia> loc = new GenericWrapper<>();		
		TbMiAgencia tmp = mis.findAgenciaMatriz();
		if(tmp == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL ENCONTRAR AGENCIA MATRIZ");
		}
		loc.setEntidad(tmp);
		return loc;
	}
}
