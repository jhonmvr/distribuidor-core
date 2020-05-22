package ec.com.def.sa.web.test.restinterface;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.Apol;

@Path("/apolRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public interface TestApolRestController {

	
	@GET
	@Path("/getEntity")
	public GenericWrapper<Apol> getEntity(
			@QueryParam("id") String id);

	
	@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<Apol> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws DefException ;

	

	
	public GenericWrapper<Apol> persistEntity(GenericWrapper<Apol> arg0) throws DefException;
}
