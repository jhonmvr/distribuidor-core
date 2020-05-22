package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.TbPaDocumentoPoliza;
import ec.fin.segurossucre.pa.repository.DocumentoPolizaRepository;
import ec.fin.segurossucre.pa.repository.spec.DocumentoPolizaByParamsSpec;
@Stateless(mappedName = "documentoPolizaRepository")
public class DocumentoPolizaRepositoryImp extends GeneralRepositoryImp<Long, TbPaDocumentoPoliza>
		implements DocumentoPolizaRepository {

	@Override
	public TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza)
			throws SegSucreException {
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
			throw new SegSucreException(Constantes.ERROR_CODE_READ,"AL BUSCAR DOCUMENTOS DE LA POLIZA"+e.getMessage());
		}
	}


}
