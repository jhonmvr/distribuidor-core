package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Acteco;
import ec.com.def.pa.repository.ActecoRepository;
import ec.com.def.pa.repository.spec.ActecoByCodigoSpec;
@Stateless(mappedName = "actecoRepository")
public class ActecoRepositoryImp extends GeneralRepositoryImp<String, Acteco> implements ActecoRepository {

	@Override
	public Acteco findByCodigo(String actividadEconomica) throws DefException {
		List<Acteco> tmp;
		try {
			tmp = this.findAllBySpecification(new ActecoByCodigoSpec(actividadEconomica));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ACTIVIDAD ECONOMICA POR CODIGO");
		}
		
	}

}
