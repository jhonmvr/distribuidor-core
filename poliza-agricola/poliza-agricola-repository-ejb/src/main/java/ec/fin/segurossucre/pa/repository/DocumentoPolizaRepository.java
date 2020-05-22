package ec.com.def.pa.repository;
import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbPaDocumentoPoliza;
@Local
public interface DocumentoPolizaRepository extends CrudRepository<Long, TbPaDocumentoPoliza> {

	TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza)throws DefException;

}
