package ec.fin.segurossucre.pa.rest;

import java.io.IOException;
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
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.core.util.main.PaginatedListWrapper;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.TbPaDocumentoPoliza;
import ec.fin.segurossucre.pa.service.PolizaAgricolaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/documentoPolizaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbPaDocumentoPolizaRestController - REST CRUD")
public class DocumentoPolizaRestController extends BaseRestController implements CrudRestControllerInterface<TbPaDocumentoPoliza, GenericWrapper<TbPaDocumentoPoliza>> {


	public DocumentoPolizaRestController() throws SegSucreException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	PolizaAgricolaService sas;
	


	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbPaDocumentoPoliza> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbPaDocumentoPoliza", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<TbPaDocumentoPoliza> getEntity(
			@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<TbPaDocumentoPoliza> loc = new GenericWrapper<>();
		TbPaDocumentoPoliza a =this.sas.findDocumentoPolizaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbPaDocumentoPoliza> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbPaDocumentoPoliza", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<TbPaDocumentoPoliza> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbPaDocumentoPoliza> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<TbPaDocumentoPoliza> plw = new PaginatedListWrapper<>(pw);
		List<TbPaDocumentoPoliza> actions = this.sas.findAllDocumentoPoliza(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countDocumentoPoliza().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbPaDocumentoPoliza>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaDocumentoPoliza", response = GenericWrapper.class)
	public GenericWrapper<TbPaDocumentoPoliza> persistEntity(GenericWrapper<TbPaDocumentoPoliza> wp) throws SegSucreException {
		GenericWrapper<TbPaDocumentoPoliza> loc = new GenericWrapper<>();
		loc.setEntidad(this.sas.upLoadDocumentoPoliza(wp.getEntidad()));
		return loc;
	}
	

	@GET
	@Path("/downloadHabilitante")
	@ApiOperation(value = "byte ", notes = "Metodo findByIdDocumentoHabilitante Retorna wrapper de entidades encontradas en TbPaDocumentoPoliza", response = byte.class)
	public GenericWrapper<TbPaDocumentoPoliza> downloadHabilitante(
			@QueryParam("id") String idTipoDocumento,			
			@QueryParam("idPoliza") String idPoliza) throws SegSucreException {
		
		try {
			if (idTipoDocumento != null && idPoliza != null ) {
				TbPaDocumentoPoliza a = this.sas.downloadDocumentoPoliza(Long.valueOf( idTipoDocumento), Long.valueOf( idPoliza));
				GenericWrapper<TbPaDocumentoPoliza> loc = new GenericWrapper<>();
				loc.setEntidad(a);
				return loc;
			} 
			else {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "EL ID DE DOCUMENTOS NO PUEDE SER NULO O VACIO ");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DE LA POLIZA O TIPO DE DOCUMENTO");
		} catch (SegSucreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL ABRIR STORAGE "+e.getCause());
		}

	}



}
