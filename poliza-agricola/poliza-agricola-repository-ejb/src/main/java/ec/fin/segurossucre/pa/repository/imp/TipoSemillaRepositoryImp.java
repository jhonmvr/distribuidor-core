package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Tiposemilla;
import ec.fin.segurossucre.pa.repository.TipoSemillaRepository;
import ec.fin.segurossucre.pa.repository.spec.TiposemillaByCodigoSpec;
@Stateless(mappedName = "tipoSemillaRepository")
public class TipoSemillaRepositoryImp extends GeneralRepositoryImp<Long, Tiposemilla> implements TipoSemillaRepository {

	@Override
	public Tiposemilla findByCodigo(String semilla) throws SegSucreException {
		List<Tiposemilla> tmp;
		try {
			tmp = this.findAllBySpecification(new TiposemillaByCodigoSpec(semilla));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TIPO DE SEMILLA POR CODIGO");
		}
		
	}

	

}
