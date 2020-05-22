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
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.core.util.main.PaginatedListWrapper;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.Apol;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import ec.fin.segurossucre.pa.wrapper.ApolWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/apolRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " APOL - REST CRUD")
public class ApolRestController extends BaseRestController implements CrudRestControllerInterface<Apol, GenericWrapper<Apol>> {

	@Inject
	PolizaAgricolaService sas;
	
	public ApolRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Apol>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Apol", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Apol> getEntity(
			@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<Apol> loc = new GenericWrapper<>();
		Apol a =this.sas.findApolById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Apol>", notes = "Metodo Get listAllEntities Retorna wrapper de entidades encontradas en Apol", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<Apol> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Apol> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<Apol> plw = new PaginatedListWrapper<>(pw);
		List<Apol> actions = this.sas.findAllApol(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countApol().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Apol> persistEntity(GenericWrapper<Apol> arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Path("/findByNumeroTramite")
	@ApiOperation(value = "GenericWrapper<Apol>", notes = "Metodo Get findByNumeroTramite Retorna wrapper de entidades encontradas en Apol", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<ApolWrapper> getByNumeroTramite(@QueryParam("awreferext")String numeroTramite) throws SegSucreException{		
		GenericWrapper<ApolWrapper> loc =  new GenericWrapper<>();
		ApolWrapper tramites = this.sas.findApolByNumeroTramite(numeroTramite);
		if( tramites != null) {
			loc.setEntidad(tramites);
		} else {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO TRAMITE " + numeroTramite);
		}
		
		return loc;	
		
	}
	

}