package com.relative.midas.repository;

import java.util.List;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiTipoDocumento;



public interface TipoDocumentoRepository extends CrudRepository<Long,TbMiTipoDocumento> {
	  
		
		public List<TbMiTipoDocumento> findByTipoDocumentoAndCodigoContrato(String tipoDocumento , String codigo,Long idJoya, Long idAbono) throws RelativeException;
		public List<TbMiTipoDocumento> findByTipoDocumentoAndCodigoContrato(String tipoDocumento , String codigo,Long idJoya, Long idAbono, PaginatedWrapper pw) throws RelativeException;
		public Long countfindByDocumentoAndCodigoContrato(String tipoDocumento, String codigo,Long idJoya, Long idAbono) throws RelativeException;
}
