package ec.com.def.pa.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.PaginatedListWrapper;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.core.web.util.BaseRestController;
import ec.com.def.core.web.util.CrudRestControllerInterface;
import ec.com.def.core.web.util.GenericWrapper;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;
import ec.com.def.pa.service.DocumentosPolizaService;
import ec.com.def.pa.service.PolizaAgricolaService;
import ec.com.def.pa.service.ReportService;
import ec.com.def.pa.util.SiniestroAgricolaUtils;
import ec.com.def.pa.util.SiniestroAgricolaUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/tipoDocumentoPolizaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbPaTipoDocumentoPolizaRestController - REST CRUD")
public class TipoDocumentoPolizaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbPaTipoDocumentoPoliza, GenericWrapper<TbPaTipoDocumentoPoliza>> {

	@Inject
	Logger log;
	@Inject
	ReportService rs;
	@Inject
	PolizaAgricolaService sas;
	@Inject
	DocumentosPolizaService doc;

	public TipoDocumentoPolizaRestController() throws DefException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws DefException {

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbPaTipoDocumentoPoliza> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbPaTipoDocumentoPoliza", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public GenericWrapper<TbPaTipoDocumentoPoliza> getEntity(@QueryParam("id") String id) throws DefException {
		GenericWrapper<TbPaTipoDocumentoPoliza> loc = new GenericWrapper<>();
		TbPaTipoDocumentoPoliza a = this.sas.findTipoDocumentoPolizaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbPaTipoDocumentoPoliza> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbPaTipoDocumentoPoliza", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = DefException.class) })
	public PaginatedListWrapper<TbPaTipoDocumentoPoliza> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws DefException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbPaTipoDocumentoPoliza> findAll(PaginatedWrapper pw) throws DefException {
		PaginatedListWrapper<TbPaTipoDocumentoPoliza> plw = new PaginatedListWrapper<>(pw);
		List<TbPaTipoDocumentoPoliza> actions = this.sas.findAllTipoDocumentoPoliza(pw);
		if (actions != null && !actions.isEmpty()) {

			plw.setTotalResults(this.sas.countTipoDocumentoPoliza().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbPaTipoDocumentoPoliza>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaTipoDocumentoPoliza", response = GenericWrapper.class)
	public GenericWrapper<TbPaTipoDocumentoPoliza> persistEntity(GenericWrapper<TbPaTipoDocumentoPoliza> wp)
			throws DefException {
		GenericWrapper<TbPaTipoDocumentoPoliza> loc = new GenericWrapper<>();
		loc.setEntidad(this.sas.manageTipoDocumentoPoliza(wp.getEntidad()));
		return loc;
	}


	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "GenericWrapper<TbPaTipoDocumentoPoliza>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbPaTipoDocumentoPoliza", response = GenericWrapper.class)
	public byte[] getPlantilla(@QueryParam("idPoliza") String idPoliza, @QueryParam("format") String formato,
			@QueryParam("tipo") String tipo) throws DefException {
		try {
			log.info("===================> getPlantilla");
			log.info("===================> getPlantilla idPoliza " + idPoliza);
			log.info("===================> getPlantilla formato " + formato);
			log.info("===================> getPlantilla tipo " + tipo);
			Map<String, Object> map = new HashMap<>();
			// String path= "C:\\reportes\\";
			// String jasper="solicitudPoliza.jasper";
			// this.setParameters(map, idPoliza);
			return this.doc.generarDocumento(map, formato,tipo, idPoliza);
		} catch (DefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL DESCARGAR DOCUMENTOS" + e.getMessage());
		}
	}

	@GET
	@Path("/getEntityByTipoDocumento")
	@ApiOperation(value = "GenericWrapper<TbPaTipoDocumentoPoliza>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbPaTipoDocumentoPoliza", response = GenericWrapper.class)
	public PaginatedListWrapper<TbPaTipoDocumentoPoliza> getEntityByTipoDocumento(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoDocumento") String tipoDocumento, @QueryParam("id") String id)
			throws DefException {
		log.info("===================> getEntityByTipoDocumento");
		log.info("===================> getEntityByTipoDocumento tipoDocumento " + tipoDocumento);
		log.info("===================> getEntityByTipoDocumento id " + id);
		return findAllDocumentoByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), tipoDocumento, id);
	}

	private PaginatedListWrapper<TbPaTipoDocumentoPoliza> findAllDocumentoByParams(PaginatedWrapper pw,
			String tipoDocumento, String id) throws DefException {
		PaginatedListWrapper<TbPaTipoDocumentoPoliza> plw = new PaginatedListWrapper<>(pw);
		List<TbPaTipoDocumentoPoliza> actions = null;

		actions = this.sas.findAllDocumentoByParams(pw, tipoDocumento,
				StringUtils.isNotBlank(id) ? Long.valueOf(id) : null);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.sas.countAllDocumentoByParams(tipoDocumento,
					StringUtils.isNotBlank(id) ? Long.valueOf(id) : null)
					.intValue());
			plw.setList(actions.stream().distinct().collect(Collectors.toList()));
		}

		return plw;
	}

}
