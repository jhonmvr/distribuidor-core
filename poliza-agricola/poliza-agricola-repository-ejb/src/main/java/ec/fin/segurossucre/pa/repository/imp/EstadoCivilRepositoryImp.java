package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Estadocivil;
import ec.fin.segurossucre.pa.repository.EstadoCivilRepository;
import ec.fin.segurossucre.pa.repository.spec.EstadocivilByCodigoSpec;
@Stateless(mappedName = "estadoCivilRepository")
public class EstadoCivilRepositoryImp extends GeneralRepositoryImp<Long, Estadocivil> implements EstadoCivilRepository {

	@Override
	public Estadocivil findByCodigo(String estadoCivil) throws SegSucreException {
		List<Estadocivil> tmp;
		try {
			tmp = this.findAllBySpecification(new EstadocivilByCodigoSpec(estadoCivil));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ESTADO CIVIL POR CODIGO");
		}
		
	}


}
