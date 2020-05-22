package ec.com.def.pa.agricola.web.un01.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.BaseWrapper;
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.seguridad.datamodel.model.SegUsuario;

@Path("/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface Un01RestInt  {
	
	
	
	@POST
	@Path("/pun01_rest")
	public BaseWrapper<SegUsuario> pun01_rest(BaseWrapper<SegUsuario> wrapper) throws DefException ;
	
	
}
