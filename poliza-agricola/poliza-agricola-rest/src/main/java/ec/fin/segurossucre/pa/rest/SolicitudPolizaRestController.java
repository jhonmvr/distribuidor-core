package ec.fin.segurossucre.pa.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.core.util.main.PaginatedListWrapper;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.core.web.util.BaseRestController;
import ec.fin.segurossucre.core.web.util.CrudRestControllerInterface;
import ec.fin.segurossucre.core.web.util.GenericWrapper;
import ec.fin.segurossucre.pa.model.TbPaSolicitudPoliza;
import ec.fin.segurossucre.pa.repository.ParametroRepository;
import ec.fin.segurossucre.pa.service.CargaMasivaPolizaService;
import ec.fin.segurossucre.pa.service.DocumentosPolizaService;
import ec.fin.segurossucre.pa.service.SolicitudPolizaService;
import ec.fin.segurossucre.pa.un01.api.Un01ApiClient;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaConstantes;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaUtils;
import ec.fin.segurossucre.pa.wrapper.CargarSolicitudWrapper;
import ec.fin.segurossucre.pa.wrapper.ConsultaSolicitudPolizaWrapper;
import ec.fin.segurossucre.pa.wrapper.FileWrapper;
import ec.fin.segurossucre.pa.wrapper.MessageWrapper;
import ec.fin.segurossucre.pa.wrapper.UN01Wrapper;
import ec.fin.segurossucre.sa.websocket.SiniestroWebSocketClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/solicitudPolizaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbPaSolicitudPolizaRestController - REST CRUD")
public class SolicitudPolizaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbPaSolicitudPoliza, GenericWrapper<TbPaSolicitudPoliza>> {

	@Inject
	Logger log;

	@Inject
	DocumentosPolizaService doc;
	@Inject
	SolicitudPolizaService sas;
	@Inject
	CargaMasivaPolizaService cms;

	@Inject
	ParametroRepository parametroRepository;

	public SolicitudPolizaRestController() throws SegSucreException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws SegSucreException {
		// sin implementar

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbPaSolicitudPoliza", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<TbPaSolicitudPoliza> getEntity(@QueryParam("id") String id) throws SegSucreException {
		GenericWrapper<TbPaSolicitudPoliza> loc = new GenericWrapper<>();
		TbPaSolicitudPoliza a = this.sas.findSolicitudPolizaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbPaSolicitudPoliza> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbPaSolicitudPoliza", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public PaginatedListWrapper<TbPaSolicitudPoliza> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws SegSucreException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbPaSolicitudPoliza> findAll(PaginatedWrapper pw) throws SegSucreException {
		PaginatedListWrapper<TbPaSolicitudPoliza> plw = new PaginatedListWrapper<>(pw);
		List<TbPaSolicitudPoliza> actions = this.sas.findAllSolicitudPoliza(pw);
		if (actions != null && !actions.isEmpty()) {

			plw.setTotalResults(this.sas.countSolicitudPoliza().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaSolicitudPoliza", response = GenericWrapper.class)
	public GenericWrapper<TbPaSolicitudPoliza> persistEntity(GenericWrapper<TbPaSolicitudPoliza> wp)
			throws SegSucreException {
		GenericWrapper<TbPaSolicitudPoliza> loc = new GenericWrapper<>();
		loc.setEntidad(this.sas.manageSolicitudPoliza(wp.getEntidad()));
		return loc;
	}

	@POST
	@Path("/guardarPoliza")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaSolicitudPoliza", response = GenericWrapper.class)
	public GenericWrapper<TbPaSolicitudPoliza> guardarPoliza(GenericWrapper<TbPaSolicitudPoliza> wp)
			throws SegSucreException {
		try {
			/*
			 * TokenWrapper tw=Un01ApiClient.getToken(
			 * this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.
			 * PARAM_API_URL_SERVICE).getValor(), RestClientWrapper.PREFIX_BASIC +
			 * this.parametroRepository.findByNombre(
			 * SiniestroAgricolaConstantes.PARAM_APGW_SINIESTROS_KEY ).getValor() );
			 */
			GenericWrapper<TbPaSolicitudPoliza> loc = new GenericWrapper<>();
			loc.setEntidad(this.sas.guardarPoliza(wp.getEntidad()));
		
			return loc;
		} catch (SegSucreException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL INTENTAR GUARDAR LA SOLICITUD"+e.getCause());
		}
	}

	@POST
	@Path("/cargaMasiva")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbPaSolicitudPoliza", response = GenericWrapper.class)
	public GenericWrapper<TbPaSolicitudPoliza> cargaMasiva(@QueryParam("hash") String hash,
			@QueryParam("canal") String canal, @QueryParam("telefonoContacto") String telefonoContacto,
			@QueryParam("nombreEjecutivo") String nombreEjecutivo, GenericWrapper<FileWrapper> wp)
			throws SegSucreException {
		GenericWrapper<TbPaSolicitudPoliza> loc = new GenericWrapper<>();
		this.loadPoliza(wp.getEntidad(), hash, canal, telefonoContacto, nombreEjecutivo);
		return loc;
	}
	

	public void loadPoliza(FileWrapper fw, String hash, String canal,String telefonoContacto,String nombreEjecutivo) throws SegSucreException {
		try {
			List<CargarSolicitudWrapper> listWrapper = processCsvToWrapper(fw, CargarSolicitudWrapper.class);
			String hashLoc = UUID.randomUUID().toString();
			final SiniestroWebSocketClient rwsc = new SiniestroWebSocketClient(new URI(this.parametroRepository
					.findByNombre(SiniestroAgricolaConstantes.PARAM_API_URL_ROOT_WEB_SOCKET).getValor() + hashLoc));
			this.sendMessage(rwsc, hash, "CARGO ARCHIVO CSV EN SISTEMA PARA PROCESAR", 0, false);
			/*
			 * TokenWrapper tw=Un01ApiClient.getToken(
			 * this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.
			 * PARAM_API_URL_SERVICE).getValor(), RestClientWrapper.PREFIX_BASIC +
			 * this.parametroRepository.findByNombre(
			 * SiniestroAgricolaConstantes.PARAM_APGW_SINIESTROS_KEY ).getValor() );
			 */
			if (listWrapper != null && !listWrapper.isEmpty()) {
				if(listWrapper.size()>200) {
					throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NUMERO MAXIMO DE REGISTROS ES 200");
				}
				int x = 1;
				final int tamanio = listWrapper.size();
				for (CargarSolicitudWrapper poliza : listWrapper) {
					x++;
					try {
						TbPaSolicitudPoliza p = cms.cargaMasiva(poliza, canal,telefonoContacto,nombreEjecutivo);
						
						this.sendMessage(rwsc, hash, "REGISTRO:" + x + " CORRECTO" + "NUMERO DE TRAMITE: "
								+ p.getNumeroTramite() + " CODIGO: " + p.getCodigo(), tamanio, true);
					} catch (SegSucreException e) {
						this.sendMessage(rwsc, hash, "ERROR EN EL REGISTRO :" + x + " CEDULA: "
								+ poliza.getDocumentoidentidad() + " " + e.getDetalle(), tamanio, true);
						log.info("ERROROR>>>>>>>>>>>>>: " + e.getDetalle());
					} catch (Exception e) {
						log.info("ERROROR>>>>>>>>>  >>>>:" + e);
						this.sendMessage(rwsc, hash, "ERROR EN EL REGISTRO :" + x + " CEDULA: "
								+ poliza.getDocumentoidentidad() + " " + e.toString(), tamanio, true);
					}
				}
			} else {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "ERROR AL CONVERTIR ARCHIVO ");
			}

		} catch (SegSucreException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "ERROR Exception  " + e.getMessage());
		}

	}

	public <T> List<T> processCsvToWrapper(FileWrapper fw, Class<T> c) throws SegSucreException {
		try {
			if (fw.getFile() == null) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "EL ARCHIVO NO FUE ENVIADO O ESTA VACIO");
			}
			Reader fileRead = new InputStreamReader(new ByteArrayInputStream(fw.getFile()), StandardCharsets.UTF_8);
			return new CsvToBeanBuilder<T>(fileRead).withSeparator('|').withType(c).build().parse();
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "AL INTENTAR CONVERTIR AL CSVTOWRAPPER");
		}
	}

	
	public void sendMessage(SiniestroWebSocketClient rwsc, String hash, String data, int counter, boolean addCount) {
		log.info("=====>INGRESA A ENVIO DE MENSAJE<<<==============");
		log.info("=====>mensaje: " + data);
		MessageWrapper mw = new MessageWrapper();
		mw.setHash(hash);
		mw.setLastId(data);
		mw.setCounter(Long.valueOf(counter));
		mw.setAddCount(addCount);
		Gson g = new Gson();
		log.info("=====>object " + rwsc);
		if (rwsc != null) {
			rwsc.sendMessage(g.toJson(mw));
			log.info("=====> TERMINO ENVIO DE MENSAJE");
		} else {
			log.info("NO SE PROCESO MENSAJES");
		}

	}

	@GET
	@Path("/findByParam")
	@ApiOperation(value = "PaginatedListWrapper<ConsultaSolicitudPolizaWrapper>", notes = "Metodo Get findByParam Retorna wrapper de informacion de paginacion y entidades encontradas en ConsultaSolicitudPolizaWrapper", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<ConsultaSolicitudPolizaWrapper> findByParam(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("numeroSolicitud") String numeroSolicitud, @QueryParam("numeroTramite") String numeroTramite,
			@QueryParam("desde") String desde, @QueryParam("hasta") String hasta, @QueryParam("canal") String canal)

			throws SegSucreException {
		return findByParam(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				StringUtils.isNotBlank(numeroSolicitud) ? numeroSolicitud : null,
				StringUtils.isNotBlank(numeroTramite) ? numeroTramite : null,
				StringUtils.isNotBlank(desde) ? SiniestroAgricolaUtils.formatSringToDateFull(desde + " 00:00:00")
						: null,
				StringUtils.isNotBlank(hasta) ? SiniestroAgricolaUtils.formatSringToDateFull(hasta + " 23:59:59")
						: null,
				StringUtils.isNotBlank(canal) ? canal : null);
	}

	private PaginatedListWrapper<ConsultaSolicitudPolizaWrapper> findByParam(PaginatedWrapper pw,
			String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal)
			throws SegSucreException {
		PaginatedListWrapper<ConsultaSolicitudPolizaWrapper> plw = new PaginatedListWrapper<>(pw);
		List<ConsultaSolicitudPolizaWrapper> actions = this.sas.findByParams(pw, numeroSolicitud, numeroTramite, desde,
				hasta, canal);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(
					this.sas.countBySolicitudPoliza(pw, numeroSolicitud, numeroTramite, desde, hasta, canal));
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbPaTipoDocumentoPoliza", response = GenericWrapper.class)
	public byte[] getPlantilla(@QueryParam("numeroSolicitud") String numeroSolicitud,
			@QueryParam("numeroTramite") String numeroTramite, @QueryParam("desde") String desde,
			@QueryParam("hasta") String hasta, @QueryParam("canal") String canal, @QueryParam("format") String formato,
			@QueryParam("tipo") String tipo) throws SegSucreException {
		try {
			Map<String, Object> map = new HashMap<>();
			return this.doc.generarDocumentoPoliza(map, formato, tipo, numeroSolicitud, numeroTramite, desde, hasta,
					canal);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "AL DESCARGAR DOCUMENTOS" + e.getMessage());
		}
	}
	
	@GET
	@Path("/upDate")
	@ApiOperation(value = "GenericWrapper<TbPaSolicitudPoliza> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbPaSolicitudPoliza", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = SegSucreException.class) })
	public GenericWrapper<TbPaSolicitudPoliza> upDate() throws SegSucreException {
		try {
			this.sas.upDateSolicitudPoliza();
			return null;
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "upDateSolicitudPoliza" + e.getMessage());
		}
	}

}
