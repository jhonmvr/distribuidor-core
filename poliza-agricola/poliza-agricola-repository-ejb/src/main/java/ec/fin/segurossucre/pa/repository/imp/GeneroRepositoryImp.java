package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Genero;
import ec.com.def.pa.repository.GeneroRepository;
import ec.com.def.pa.repository.spec.GeneroByCodigoSpec;
@Stateless(mappedName = "generoRepository")
public class GeneroRepositoryImp extends GeneralRepositoryImp<Long, Genero> implements GeneroRepository {

	@Override
	public Genero findByCodigo(String genero) throws DefException {
		List<Genero> tmp;
		try {
			tmp = this.findAllBySpecification(new GeneroByCodigoSpec(genero));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR GENERO POR CODIGO");
		}
		
	}

}
