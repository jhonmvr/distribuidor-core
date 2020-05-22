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
import ec.fin.segurossucre.pa.model.Ramocanal;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaConstantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/canalRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " CanalRest - REST CRUD")
public class CanalRestController extends BaseRestController implements CrudRestControllerInterface<Ramocanal, GenericWrapper<Ramocanal>> {

	@Inject
	PolizaAgricolaService sas;
	
	public CanalRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Ramocanal>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Ramocanal", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Ramocanal> getEntity(
			@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<Ramocanal> loc = new GenericWrapper<>();
		RamocanalPK pk = new RamocanalPK();
		pk.setCanalid( id );
		pk.setRamoid( SiniestroAgricolaConstantes.RAMO_ID );
		Ramocanal a =this.sas.findCanalById(pk);
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Ramocanal>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Ramocanal", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<Ramocanal> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Ramocanal> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<Ramocanal> plw = new PaginatedListWrapper<>(pw);
		List<Ramocanal> actions = this.sas.findAllCanal(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countCanal().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@Override
	//@POST
	//@Path("/persistEntity")
	public GenericWrapper<Ramocanal> persistEntity(GenericWrapper<Ramocanal> wp) throws SegSucreException {
		
		return null;
	}

}
