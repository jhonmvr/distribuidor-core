package ec.fin.segurossucre.pa.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import ec.fin.segurossucre.pa.model.Canton;
import ec.fin.segurossucre.pa.model.CantonPK;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/cantonRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Canton - REST CRUD")
public class CantonRestController extends BaseRestController implements CrudRestControllerInterface<Canton, GenericWrapper<Canton>> {

	@Inject
	PolizaAgricolaService sas;
	
	public CantonRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		
	}

	 	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Canton>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Canton", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Canton> getEntity(
			@QueryParam("provinciaId") String  provinciaId,
			@QueryParam("cantoId") String cantonId) throws SegSucreException {
		GenericWrapper<Canton> loc = new GenericWrapper<>();
		CantonPK c =new CantonPK();
		c.setCantonid(cantonId);
		c.setProvinciaid(provinciaId);
		Canton a =this.sas.findCantonById(c);
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	public GenericWrapper<Canton> getEntity( String  id) throws SegSucreException {
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Canton>", notes = "Metodo Get listAllEntities Retorna wrapper de entidades encontradas en Canton", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<Canton> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Canton> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<Canton> plw = new PaginatedListWrapper<>(pw);
		List<Canton> actions = this.sas.findAllCanton(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countCanton().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Canton> persistEntity(GenericWrapper<Canton> arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Path("/listAllEntitiesByProvincia")
	@ApiOperation(value = "PaginatedListWrapper<Canton>", notes = "Metodo Get listAllEntitiesByProvincia Retorna wrapper de entidades encontradas en Canton", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<Canton> listAllEntitiesByProvincia(
			@QueryParam("provincia") @DefaultValue("01") String provincia,
			@QueryParam("order") @DefaultValue("asc") String order) throws SegSucreException {
		PaginatedListWrapper<Canton> pw = new PaginatedListWrapper<>();
		pw.setList( this.sas.findCantonesByProvincia(provincia, order) );
		return pw;
	}
	
	

}
