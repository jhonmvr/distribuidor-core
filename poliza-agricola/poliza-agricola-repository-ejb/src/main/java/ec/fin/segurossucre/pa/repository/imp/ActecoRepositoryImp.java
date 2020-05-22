package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Acteco;
import ec.fin.segurossucre.pa.repository.ActecoRepository;
import ec.fin.segurossucre.pa.repository.spec.ActecoByCodigoSpec;
@Stateless(mappedName = "actecoRepository")
public class ActecoRepositoryImp extends GeneralRepositoryImp<String, Acteco> implements ActecoRepository {

	@Override
	public Acteco findByCodigo(String actividadEconomica) throws SegSucreException {
		List<Acteco> tmp;
		try {
			tmp = this.findAllBySpecification(new ActecoByCodigoSpec(actividadEconomica));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ACTIVIDAD ECONOMICA POR CODIGO");
		}
		
	}

}
