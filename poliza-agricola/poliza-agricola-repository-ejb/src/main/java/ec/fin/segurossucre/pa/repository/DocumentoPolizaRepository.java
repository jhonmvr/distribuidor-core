package ec.fin.segurossucre.pa.repository;
import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.TbPaDocumentoPoliza;
@Local
public interface DocumentoPolizaRepository extends CrudRepository<Long, TbPaDocumentoPoliza> {

	TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza)throws SegSucreException;

}
