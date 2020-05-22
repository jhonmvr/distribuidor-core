package ec.com.def.pa.repository;
import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;
@Local
public interface TipoDocumentoPolizaRepository extends CrudRepository<Long, TbPaTipoDocumentoPoliza> {

	List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id, String tipoPlantilla) throws DefException;

	List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id, Integer currentPage, Integer pageSize,
			String sortFields, String sortDirections)throws DefException;

	List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id)throws DefException;

	Long countAllByParams(String tipoDocumento, Long id)throws DefException;

}
