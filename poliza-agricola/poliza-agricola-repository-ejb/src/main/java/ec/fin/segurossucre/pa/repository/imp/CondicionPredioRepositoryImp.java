package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Condicionpredio;
import ec.com.def.pa.repository.CondicionPredioRepository;
import ec.com.def.pa.repository.spec.CondicionpredioByCodigoSpec;
@Stateless(mappedName = "condicionPredioRepository")
public class CondicionPredioRepositoryImp extends GeneralRepositoryImp<Long, Condicionpredio>
		implements CondicionPredioRepository {

	@Override
	public Condicionpredio findByCodigo(String codCondicion) throws DefException {
		List<Condicionpredio> tmp;
		try {
			tmp = this.findAllBySpecification(new CondicionpredioByCodigoSpec(codCondicion));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CONDICION DE PREDIO POR CODIGO");
		}
		
	}

	
}
