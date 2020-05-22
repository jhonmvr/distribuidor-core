package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiTipoDocumento;
import com.relative.midas.repository.TipoDocumentoRepository;
import com.relative.midas.repository.spec.DocumentoByTipoDocumentoSpec;

/**
 * Session Bean implementation class documentoRepository
 */
@Stateless(mappedName = "documentoRepository")
public class TipoDocumentoRepositoryImp  extends GeneralRepositoryImp<Long, TbMiTipoDocumento> implements TipoDocumentoRepository{



    

   //	@Override
   	public List<TbMiTipoDocumento> findByTipoDocumentoAndCodigoContrato(String tipoDocumento , String codigo,Long idJoya, Long idAbono) throws RelativeException {
   		List<TbMiTipoDocumento> tmp;
       	try {
   			tmp = this.findAllBySpecification(new DocumentoByTipoDocumentoSpec( tipoDocumento, codigo, idJoya, idAbono));
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION  PARA ID Y EL CONTRATO " + tipoDocumento+ " " + e.getMessage()) ;
   		}
   		return null;
       	
   	}
   	
   	public List<TbMiTipoDocumento> findByTipoDocumentoAndCodigoContrato(String tipoDocumento , String codigo, 
   			Long idJoya, Long idAbono, PaginatedWrapper pw) throws RelativeException {
   		List<TbMiTipoDocumento> tmp;
       	try {
   			tmp = this.findAllBySpecificationPaged(new DocumentoByTipoDocumentoSpec( tipoDocumento, codigo, idJoya, idAbono),pw.getStartRecord(), pw.getPageSize());
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION  PARA ID Y EL CONTRATO " + tipoDocumento) ;
   		}
   		return null;
       	
   	}

  
	public Long countfindByDocumentoAndCodigoContrato(String tipoDocumento, String codigo,Long idJoya, Long idAbono) throws RelativeException {
   		Long tmp = null;
   		try {
   			tmp = this.countBySpecification(new DocumentoByTipoDocumentoSpec( tipoDocumento, codigo, idJoya, idAbono));
   				return tmp;
   		} catch (Exception e) {
   			e.printStackTrace();
   			//log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION  PARA ID " + tipoDocumento) ;
   			
   		}
	}


	

}
