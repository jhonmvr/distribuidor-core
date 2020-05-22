package ec.fin.segurossucre.sa.web.test.restinterface;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sampleRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TestSampleRestController  {
	
	

	/*@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<TbSample> listAllEntities(
	@DefaultValue("0")@QueryParam("pageNumber")
	String page, 
	@DefaultValue("8")@QueryParam("pageSize")
	String pageSize, 
	@DefaultValue("")@QueryParam("sortFields")
	String sortFields, 
	@DefaultValue("")@QueryParam("sortDirections")
	String sortDirections,
	@DefaultValue("N")@QueryParam("isPaginated")
	String isPaginated	) throws SegSucreException;

	@GET
	@Path("/getEntity")
	public BaseWrapper<TbSample> getEntity(@QueryParam("id") String idEntity) throws SegSucreException;

	@POST
	@Path("/saveEntity")
	public BaseWrapper<TbSample> persistEntity(BaseWrapper<TbSample> wrapper) throws SegSucreException ;

	
	@POST
	@Path("/deleteEntity")
	public void deleteEntity(String idEntity) throws SegSucreException ;
	*/
	

}