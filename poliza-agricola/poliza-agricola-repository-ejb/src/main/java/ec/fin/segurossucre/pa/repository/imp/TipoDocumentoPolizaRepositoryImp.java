package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;
import ec.com.def.pa.repository.TipoDocumentoPolizaRepository;
import ec.com.def.pa.repository.spec.DocumentoByParamsSpec;

@Stateless(mappedName = "tipoDocumentoPolizaRepository")
public class TipoDocumentoPolizaRepositoryImp extends GeneralRepositoryImp<Long, TbPaTipoDocumentoPoliza>
		implements TipoDocumentoPolizaRepository {

	@Override
	public List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id,
			String tipoPlantilla) throws DefException {
		try {
			List<TbPaTipoDocumentoPoliza> p = null;
			p = this.findAllBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, tipoPlantilla));
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findDocumentoByParams " + e.getMessage());
		}
	}

	@Override
	public List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id, Integer currentPage,
			Integer pageSize, String sortFields, String sortDirections) throws DefException {
		try {
			List<TbPaTipoDocumentoPoliza> tmp = null;
			tmp = this.findAllBySpecificationPaged(new DocumentoByParamsSpec(tipoDocumento, id, null), currentPage,
					pageSize, sortFields, sortDirections);

			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findAllByParams " + e.getMessage());
		}
	}

	@Override
	public List<TbPaTipoDocumentoPoliza> findAllByParams(String tipoDocumento, Long id)
			throws DefException {
		try {
			List<TbPaTipoDocumentoPoliza> tmp = null;
			tmp = this.findAllBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, null));

			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,
					"AL BUSCAR findAllByParams " + e.getMessage());
		}
	}

	@Override
	public Long countAllByParams(String tipoDocumento, Long id)throws DefException {		
		return this.countBySpecification(new DocumentoByParamsSpec(tipoDocumento, id, null));
	}

}
