package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.model.TbPaCanalSecuencia;
import ec.fin.segurossucre.pa.repository.CanalSecuenciaRepository;
import ec.fin.segurossucre.pa.repository.spec.CanalSecuenciaByCanalIdSpec;
@Stateless(mappedName = "canalSecuenciaRepository")
public class CanalSecuenciaRepositoryImp extends GeneralRepositoryImp<Long, TbPaCanalSecuencia>
		implements CanalSecuenciaRepository {

	@Override
	public TbPaCanalSecuencia findByCanalId(RamocanalPK id) throws SegSucreException {
		try {
			List<TbPaCanalSecuencia> tmp = this.findAllBySpecification(new CanalSecuenciaByCanalIdSpec(id));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CANALSECUENCIA POR FK RAMOCANAL");
		}
		return null;
	}

}
