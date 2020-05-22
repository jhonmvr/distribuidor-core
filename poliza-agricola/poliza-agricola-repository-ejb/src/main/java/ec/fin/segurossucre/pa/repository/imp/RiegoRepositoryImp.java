package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Riego;
import ec.fin.segurossucre.pa.repository.RiegoRepository;
import ec.fin.segurossucre.pa.repository.spec.RiegoByCodigoSpec;
@Stateless(mappedName = "riegoRepository")
public class RiegoRepositoryImp extends GeneralRepositoryImp<Long, Riego> implements RiegoRepository {

	@Override
	public Riego findByCodigo(String riego) throws SegSucreException {
		List<Riego> tmp;
		try {
			tmp = this.findAllBySpecification(new RiegoByCodigoSpec(riego));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TIPO DE RIEGO POR CODIGO");
		}
		
	}



}
