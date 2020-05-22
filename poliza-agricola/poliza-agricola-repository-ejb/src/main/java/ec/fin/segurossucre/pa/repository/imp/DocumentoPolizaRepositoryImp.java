package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.TbPaDocumentoPoliza;
import ec.com.def.pa.repository.DocumentoPolizaRepository;
import ec.com.def.pa.repository.spec.DocumentoPolizaByParamsSpec;
@Stateless(mappedName = "documentoPolizaRepository")
public class DocumentoPolizaRepositoryImp extends GeneralRepositoryImp<Long, TbPaDocumentoPoliza>
		implements DocumentoPolizaRepository {

	@Override
	public TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza)
			throws DefException {
		// TODO Auto-generated method stub
		try {
			List<TbPaDocumentoPoliza>tmp=null;
					tmp =this.findAllBySpecification(new DocumentoPolizaByParamsSpec(idTipoDocumento,idPoliza));
					if(tmp != null && !tmp.isEmpty()) {
						return tmp.get(0);
					}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,"AL BUSCAR DOCUMENTOS DE LA POLIZA"+e.getMessage());
		}
	}


}
