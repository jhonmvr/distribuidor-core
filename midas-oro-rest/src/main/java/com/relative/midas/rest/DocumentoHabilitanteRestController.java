package com.relative.midas.rest;

import java.util.Date;
import java.util.List;
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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.HabilitanteWrapper;

import io.swagger.annotations.ApiOperation;

@Path("/documentoHabilitanteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentoHabilitanteRestController extends BaseRestController
		implements CrudRestControllerInterface<TbMiDocumentoHabilitante, GenericWrapper<TbMiDocumentoHabilitante>> {

	@Inject
	Logger log;
	@Inject
	MidasOroService mis;
	
	public DocumentoHabilitanteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDocumentoHabilitante>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiDocumentoHabilitante", response = GenericWrapper.class)
	public GenericWrapper<TbMiDocumentoHabilitante> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiDocumentoHabilitante> loc = new GenericWrapper<>();
		TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/downloadHabilitante")
	@ApiOperation(value = "byte ", notes = "Metodo findByIdDocumentoHabilitante Retorna wrapper de entidades encontradas en TbMiDocumentoHabilitante", response = byte.class)
	public byte[] downloadHabilitante(
			@QueryParam("id") String idTipoDocumento,
			@QueryParam("codigo") String codigoContrato,
			@QueryParam("idJoya") String idJoya,@QueryParam("idAbono") String idAbono,
			@QueryParam("idVentaLote") String idVentaLote,
			@QueryParam("idCorteCaja") String idCorteCaja) throws RelativeException {
		log.info("===> idTipoDocumento " + idTipoDocumento);
		log.info("===> codigoContrato " + codigoContrato);
		log.info("===> idJoya " + idJoya);
		log.info("===> idAbono " + idAbono);
		log.info("===> idVentaLote " + idAbono);
		log.info("===> idCorteCaja " + idCorteCaja);
		if (idTipoDocumento != null &&  codigoContrato != null && !codigoContrato.isEmpty() ) {
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndCodigoContrato(codigoContrato, Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} else if (idTipoDocumento != null &&  idJoya != null && !idJoya.isEmpty() ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					Long.valueOf( idJoya ),null,null,null, Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idAbono) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,Long.valueOf( idAbono ), null,null,Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		}else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idVentaLote) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,null,Long.valueOf( idVentaLote ),null,Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} 
		else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idCorteCaja) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,null,null,Long.valueOf( idCorteCaja ),Long.valueOf( idTipoDocumento));
			return getArchivo(a);
			
		} 
		else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ID DE DOCUMENTOS NO PUEDE SER NULO O VACIO ");
		}

	}



	private byte[] getArchivo(TbMiDocumentoHabilitante a) throws RelativeException {
		if( a != null ) {
			log.info("===> documento habilitante obtenido  getId" + a.getId());
			log.info("===> documento habilitante obtenido  getArchivo" + a.getArchivo());
			return a.getArchivo();
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ARCHIVO NO ENCONTRADO");
		}
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiDocumentoHabilitante>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiDocumentoHabilitante", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbMiDocumentoHabilitante> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiDocumentoHabilitante> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiDocumentoHabilitante> plw = new PaginatedListWrapper<>(pw);
		List<TbMiDocumentoHabilitante> actions = this.mis.findAllDocumentoHabilitante(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countdDocumentoHabilitante().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDocumentoHabilitante>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiDocumentoHabilitante", response = GenericWrapper.class)
	public GenericWrapper<TbMiDocumentoHabilitante> persistEntity(GenericWrapper<TbMiDocumentoHabilitante> wp)
			throws RelativeException {
		GenericWrapper<TbMiDocumentoHabilitante> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageDocumentoHabilitante(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/findDocumentoHabilitante")
	@ApiOperation(value = "byte ", notes = "Metodo findByIdDocumentoHabilitante Retorna wrapper de entidades encontradas en TbMiDocumentoHabilitante", response = byte.class)
	public List<TbMiDocumentoHabilitante> findDocumentoHabilitanteByContrato(@QueryParam("codigo") String codigoContrato) throws RelativeException {
		log.info("===> codigoContrato " + codigoContrato);
		//GenericWrapper<TbMiDocumentoHabilitante> w= new GenericWrapper<>();
		if (  codigoContrato != null && !codigoContrato.isEmpty() ) {
			return this.mis.findDocumentoHabilitanteByCodigoContrato(codigoContrato);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL CODIGO DE CONTRATO NO TIENE DOCUMENTOS ASOCIADOS ");
		}

	}
	
	@GET
	@Path("/validateContratoByHabilitante")
	@ApiOperation(value = "byte ", notes = "Metodo findByIdDocumentoHabilitante Retorna wrapper de entidades encontradas en TbMiDocumentoHabilitante", response = byte.class)
	public GenericWrapper<Boolean> validateContratoByHabilitante(
			@QueryParam("codigo") String codigoContrato,
			@QueryParam("idJoya") String idJoya,
			@QueryParam("idAbono") String idAbono,
			@QueryParam("idVentaLote") String idVentaLote,
			@QueryParam("tipoDocumento") String tipoDocumento,
			@QueryParam("estadoContrato") String estadoContrato,
			@QueryParam("estadoJoya") String estadoJoya,
			@QueryParam("estadoAbono") String estadoAbono,
			@QueryParam("estadoVentaLote") String estadoVentaLote,
			@QueryParam("usuario") String usuario,
			@QueryParam("idCorteCaja") String idCorteCaja,
			@QueryParam("salida" ) String salida) throws RelativeException 
				{
		log.info("===> codigoContrato " + codigoContrato);
		log.info("===> idJoya " + idJoya);
		log.info("===> idAbono " + idAbono);
		log.info("===> tipoDocumento " + tipoDocumento);
		log.info("===> estadoContrato " + estadoContrato);
		log.info("===> estadoAbono " + estadoAbono);
		GenericWrapper<Boolean> w= new GenericWrapper<>();
		if (  codigoContrato != null && !codigoContrato.isEmpty()  ) {
			w.setEntidad( this.mis.validateContratoByHabilitante( codigoContrato, tipoDocumento,estadoContrato,estadoJoya,usuario,salida.equals("true")) );
			return w;
		} else if (  idJoya != null && !idJoya.isEmpty()  ) {
			w.setEntidad( this.mis.validateJoyaByHabilitante( Long.valueOf(idJoya), tipoDocumento,estadoJoya,usuario,salida.equals("true")) );
			return w;
		} else if (  idAbono != null && !idAbono.isEmpty()  ) {
			w.setEntidad( this.mis.validateAbonoByHabilitante( Long.valueOf(idAbono), tipoDocumento,estadoAbono,usuario, salida.equals("true")) );
			return w;
		}else if (  idVentaLote != null && !idVentaLote.isEmpty()  ) {
			w.setEntidad( this.mis.validateVentaLoteByHabilitante( Long.valueOf(idVentaLote), tipoDocumento,estadoVentaLote,usuario,salida.equals("true")) );
			return w;
		}else if (  idCorteCaja != null && !idCorteCaja.isEmpty()  ) {
			w.setEntidad( this.mis.validateCorteCajaByHabilitante( Long.valueOf(idCorteCaja), tipoDocumento,estadoVentaLote,usuario,salida.equals("true")) );
			return w;
		}  
		else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE CONTINUAR EL PROCESO DE CIERRE DE HABILITANTES, DEBE PROVEER LA INFORMACION "
					+ "DE CONTRATO, JOYA O ABONO QUE DESEA PROCESAR");
		}

	}
	
	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<HabilitanteWrapper>",
	notes = "Busca TbMiDocumentoHabilitante por codigo contrato, codigo joya, nombre cliente abono, identificacion cliente abono y estado",
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<HabilitanteWrapper> findByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoContrato") String codigoContrato,
			@QueryParam("codigoJoya") String codigoJoya,
			@QueryParam("nombreCliente") String nombreCliente,
			@QueryParam("identificacionCliente") String identificacionCliente,
			@QueryParam("estado") String estado,
			@QueryParam("tipoDocumento") String tipoDocumento,
			@QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("agencia") String agencia) throws RelativeException {
		log.info("==>DHRC findByParams ");
		log.info("==>DHRC findByParams codigoContrato " + codigoContrato );
		log.info("==>DHRC findByParams codigoJoya " + codigoJoya );
		log.info("==>DHRC findByParams nombreCliente " + nombreCliente );
		log.info("==>DHRC findByParams identificacionCliente " + identificacionCliente );
		log.info("==>DHRC findByParams estado " + estado );
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				StringUtils.isNotBlank(codigoContrato)? codigoContrato: null,
				StringUtils.isNotBlank(codigoJoya)? codigoJoya: null,
				StringUtils.isNotBlank(nombreCliente)? nombreCliente: null,
				StringUtils.isNotBlank(identificacionCliente)? identificacionCliente: null,
				StringUtils.isNotBlank(estado)? MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estado): null,
						StringUtils.isNotBlank(tipoDocumento)?Long.valueOf(tipoDocumento):null,
								StringUtils.isNotBlank(fechaDesde)?MidasOroUtil.formatSringToDate(fechaDesde):null,
										StringUtils.isNotBlank(fechaHasta)?MidasOroUtil.formatSringToDate(fechaHasta):null,
												StringUtils.isNotBlank(agencia)?Long.valueOf(agencia):null);
	}

	private PaginatedListWrapper<HabilitanteWrapper> findByParams(PaginatedWrapper pw, String codigoContrato, 
			String codigoJoya, String nombreCliente, String identificacionCliente, EstadoMidasEnum estado,Long tipoDocumento,
			Date fechaDesde, Date fechaHasta,Long idAgencia) throws RelativeException {
		PaginatedListWrapper<HabilitanteWrapper> plw = new PaginatedListWrapper<>(pw);
		List<HabilitanteWrapper> actions = this.mis.findDocHabByParams(pw, codigoContrato, codigoJoya, nombreCliente, 
				identificacionCliente, estado,tipoDocumento,fechaDesde,fechaHasta,idAgencia);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countDocHabByParams(codigoContrato, codigoJoya, nombreCliente, 
					identificacionCliente, estado,tipoDocumento,fechaDesde,fechaHasta,idAgencia).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
