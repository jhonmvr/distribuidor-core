package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.TbPaTipoDocumentoPoliza;
import ec.fin.segurossucre.pa.repository.TipoDocumentoPolizaRepository;
import ec.fin.segurossucre.pa.repository.spec.DocumentoByParamsSpec;

@Stateless(mappedName = "tipoDocumentoPolizaRepository")
public class TipoDocumentoPolizaRepositoryImp extends GeneralRepositoryImp<Long, TbPaTipoDocumentoPoliza>
		implements TipoDocumentoPolizaRepository {

	@Override
	public List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id,
			String tipoPlantilla) throws SegSucreException {
		try {
			List<TbPaTipoDocumentoPoliza> p = null;
			p = this.findAllBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, tipoPlantilla));
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findDocumentoByParams " + e.getMessage());
		}
	}

	@Override
	public List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id, Integer currentPage,
			Integer pageSize, String sortFields, String sortDirections) throws SegSucreException {
		try {
			List<TbPaTipoDocumentoPoliza> tmp = null;
			tmp = this.findAllBySpecificationPaged(new DocumentoByParamsSpec(tipoDocumento, id, null), currentPage,
					pageSize, sortFields, sortDirections);

			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findAllByParams " + e.getMessage());
		}
	}

	@Override
	public List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id)
			throws SegSucreException {
		try {
			List<TbPaTipoDocumentoPoliza> tmp = null;
			tmp = this.findAllBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, null));

			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findAllByParams " + e.getMessage());
		}
	}

	@Override
	public Long countAllByParams(String tipoDocumento, Long id)throws SegSucreException {		
		return this.countBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, null));
	}

}
