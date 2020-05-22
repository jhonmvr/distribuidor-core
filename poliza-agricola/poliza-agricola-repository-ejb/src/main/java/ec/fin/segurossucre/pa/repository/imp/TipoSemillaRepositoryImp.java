package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Tiposemilla;
import ec.com.def.pa.repository.TipoSemillaRepository;
import ec.com.def.pa.repository.spec.TiposemillaByCodigoSpec;
@Stateless(mappedName = "tipoSemillaRepository")
public class TipoSemillaRepositoryImp extends GeneralRepositoryImp<Long, Tiposemilla> implements TipoSemillaRepository {

	@Override
	public Tiposemilla findByCodigo(String semilla) throws DefException {
		List<Tiposemilla> tmp;
		try {
			tmp = this.findAllBySpecification(new TiposemillaByCodigoSpec(semilla));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TIPO DE SEMILLA POR CODIGO");
		}
		
	}

	

}
