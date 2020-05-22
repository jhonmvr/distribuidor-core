package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Riego;
import ec.com.def.pa.repository.RiegoRepository;
import ec.com.def.pa.repository.spec.RiegoByCodigoSpec;
@Stateless(mappedName = "riegoRepository")
public class RiegoRepositoryImp extends GeneralRepositoryImp<Long, Riego> implements RiegoRepository {

	@Override
	public Riego findByCodigo(String riego) throws DefException {
		List<Riego> tmp;
		try {
			tmp = this.findAllBySpecification(new RiegoByCodigoSpec(riego));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TIPO DE RIEGO POR CODIGO");
		}
		
	}



}
