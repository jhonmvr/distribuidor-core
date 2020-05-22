package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.RamocanalPK;
import ec.com.def.pa.model.TbPaCanalSecuencia;
import ec.com.def.pa.repository.CanalSecuenciaRepository;
import ec.com.def.pa.repository.spec.CanalSecuenciaByCanalIdSpec;
@Stateless(mappedName = "canalSecuenciaRepository")
public class CanalSecuenciaRepositoryImp extends GeneralRepositoryImp<Long, TbPaCanalSecuencia>
		implements CanalSecuenciaRepository {

	@Override
	public TbPaCanalSecuencia findByCanalId(RamocanalPK id) throws DefException {
		try {
			List<TbPaCanalSecuencia> tmp = this.findAllBySpecification(new CanalSecuenciaByCanalIdSpec(id));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CANALSECUENCIA POR FK RAMOCANAL");
		}
		return null;
	}

}
