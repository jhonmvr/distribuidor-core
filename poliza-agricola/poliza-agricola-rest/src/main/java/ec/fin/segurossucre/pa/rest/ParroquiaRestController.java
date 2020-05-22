package ec.fin.segurossucre.pa.rest;
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
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.Parroquia;
import ec.fin.segurossucre.pa.model.ParroquiaPK;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/parroquiaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ParroquiaRestController - REST CRUD")
public class ParroquiaRestController extends BaseRestController implements CrudRestControllerInterface<Parroquia, GenericWrapper<Parroquia>>{
	@Inject
	PolizaAgricolaService sas;
	
	public ParroquiaRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		
	}

	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Parroquia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Parroquia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Parroquia> getEntity(
			@QueryParam("provinciaId") String provinciaId,
			@QueryParam("cantonId") String cantonId,
			@QueryParam("parroquiaId") String parroquiaId) throws SegSucreException {
		GenericWrapper<Parroquia> loc = new GenericWrapper<>();
		ParroquiaPK c=new ParroquiaPK();
		c.setParroquiaid(parroquiaId);
		c.setCantonid(cantonId);
		c.setProvinciaid(provinciaId);
		Parroquia a =this.sas.findParroquiaById(c);
		loc.setEntidad(a);
		return loc;
	}

	
	

	@GET
	@Path("/listAllEntitiesByProvinciaCanton")
	@ApiOperation(value = "GenericWrapper<Parroquia> ", notes = "Metodo listAllEntitiesByProvinciaCanton Retorna wrapper de entidades encontradas en Parroquia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<Parroquia> listAllEntitiesByProvinciaCanton(
			@QueryParam("provinciaId") String provinciaId,
			@QueryParam("cantonId") String cantonId,
			@QueryParam("order") @DefaultValue("asc") String order) throws SegSucreException {
		GenericWrapper<Parroquia> loc = new GenericWrapper<>();
		loc.setEntidades( this.sas.findAllParroquiaByProvinciaCanton(provinciaId, cantonId, order) );
		return loc;
	}

	@Override
	public GenericWrapper<Parroquia> persistEntity(GenericWrapper<Parroquia> arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<Parroquia> getEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<Parroquia> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws SegSucreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
