package ec.fin.segurossucre.pa.rest;

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

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.PaginatedListWrapper;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.TbSaAsegurado;
import ec.fin.segurossucre.pa.service.SolicitudPolizaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/aseguradoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbSaAseguradoRestController - REST CRUD")
public class AseguradoRestController extends BaseRestController implements CrudRestControllerInterface<TbSaAsegurado, GenericWrapper<TbSaAsegurado>> {


	@Inject
	SolicitudPolizaService sas;
	
	public AseguradoRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// sin implementar
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbSaAsegurado> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbSaAsegurado", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<TbSaAsegurado> getEntity(
			@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<TbSaAsegurado> loc = new GenericWrapper<>();
		TbSaAsegurado a =this.sas.findAseguradoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbSaAsegurado> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbSaAsegurado", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<TbSaAsegurado> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbSaAsegurado> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<TbSaAsegurado> plw = new PaginatedListWrapper<>(pw);
		List<TbSaAsegurado> actions = this.sas.findAllAsegurado(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countAsegurado().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbSaAsegurado>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbSaAsegurado", response = GenericWrapper.class)
	public GenericWrapper<TbSaAsegurado> persistEntity(GenericWrapper<TbSaAsegurado> wp) throws SegSucreException {
		GenericWrapper<TbSaAsegurado> loc = new GenericWrapper<>();
		loc.setEntidad(this.sas.manageAsegurado(wp.getEntidad()));
		return loc;
	}
	
	
	@GET
	@Path("/buscarAsegurado")
	@ApiOperation(value = "GenericWrapper<TbSaAsegurado> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbSaAsegurado", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<TbSaAsegurado> buscarAsegurado(
			@QueryParam("identificacion") String identificacion) throws SegSucreException {
		GenericWrapper<TbSaAsegurado> loc = new GenericWrapper<>();
		TbSaAsegurado a =this.sas.finAseguradoByIdentificacion(identificacion);
		loc.setEntidad(a);
		return loc;
	}
	

}
