package ec.fin.segurossucre.pa.repository;
import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.TbPaTipoDocumentoPoliza;
@Local
public interface TipoDocumentoPolizaRepository extends CrudRepository<Long, TbPaTipoDocumentoPoliza> {

	List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id, String tipoPlantilla) throws SegSucreException;

	List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id, Integer currentPage, Integer pageSize,
			String sortFields, String sortDirections)throws SegSucreException;

	List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id)throws SegSucreException;

	Long countAllByParams(String tipoDocumento, Long id)throws SegSucreException;

}
