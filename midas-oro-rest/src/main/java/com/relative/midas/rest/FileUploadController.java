package com.relative.midas.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.web.util.BaseRestController;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.service.MidasOroService;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.FileWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/uploadRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " FileUploadController - REST CRUD")
//@Api(value = "UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES",tags = {"UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES"})
public class FileUploadController extends BaseRestController {
	
	@Inject 
	Logger log;
	
	@Inject
	MidasOroService sas;
	
	public FileUploadController() throws RelativeException{
		super();
	}
	
	@POST
	@Path("/loadFileHabilitante")
	@ApiOperation(value = "FileWrapper ", notes = "Metodo Post loadFile", 
	response = FileWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = FileWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public FileWrapper  loadFile(FileWrapper fw) throws RelativeException {
		try {
			log.info("===============>loadFile "  );
			log.info("===============>loadFile FW getRelatedIdStr " + fw.getRelatedIdStr()  );
			log.info("===============>loadFile FW getProcess " + fw.getProcess() );
			log.info("===============>loadFile FW getTypeAction " + fw.getTypeAction() );
			if( fw.getFileBase64() == null || fw.getFileBase64().isEmpty() ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO LLEGA ARCHIVO");
			}
			//log.info("===============>loadFile " + fw.getFileBase64() );
			fw.setFile(  MidasOroUtil.convertBase64ToByteArray( fw.getFileBase64() ));
			TbMiDocumentoHabilitante da= this.sas.generateDocumentoHabilitante(fw);
			log.info("===============>loadedeFile " + da.getId() );
			return fw;
		}catch (RelativeException e) {
			throw e;
		}  
		 catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERRORException REGISTRO DE ARCHIVO");
		} 
	}
	
	
	
	
	
}
