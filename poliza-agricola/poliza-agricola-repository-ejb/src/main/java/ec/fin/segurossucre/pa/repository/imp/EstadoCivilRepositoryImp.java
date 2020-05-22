package ec.com.def.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Estadocivil;
import ec.com.def.pa.repository.EstadoCivilRepository;
import ec.com.def.pa.repository.spec.EstadocivilByCodigoSpec;
@Stateless(mappedName = "estadoCivilRepository")
public class EstadoCivilRepositoryImp extends GeneralRepositoryImp<Long, Estadocivil> implements EstadoCivilRepository {

	@Override
	public Estadocivil findByCodigo(String estadoCivil) throws DefException {
		List<Estadocivil> tmp;
		try {
			tmp = this.findAllBySpecification(new EstadocivilByCodigoSpec(estadoCivil));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ESTADO CIVIL POR CODIGO");
		}
		
	}


}
