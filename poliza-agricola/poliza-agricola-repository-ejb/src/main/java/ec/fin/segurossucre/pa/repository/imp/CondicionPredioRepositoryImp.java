package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Condicionpredio;
import ec.fin.segurossucre.pa.repository.CondicionPredioRepository;
import ec.fin.segurossucre.pa.repository.spec.CondicionpredioByCodigoSpec;
@Stateless(mappedName = "condicionPredioRepository")
public class CondicionPredioRepositoryImp extends GeneralRepositoryImp<Long, Condicionpredio>
		implements CondicionPredioRepository {

	@Override
	public Condicionpredio findByCodigo(String codCondicion) throws SegSucreException {
		List<Condicionpredio> tmp;
		try {
			tmp = this.findAllBySpecification(new CondicionpredioByCodigoSpec(codCondicion));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CONDICION DE PREDIO POR CODIGO");
		}
		
	}

	
}
