package ec.fin.segurossucre.pa.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Genero;
import ec.fin.segurossucre.pa.repository.GeneroRepository;
import ec.fin.segurossucre.pa.repository.spec.GeneroByCodigoSpec;
@Stateless(mappedName = "generoRepository")
public class GeneroRepositoryImp extends GeneralRepositoryImp<Long, Genero> implements GeneroRepository {

	@Override
	public Genero findByCodigo(String genero) throws SegSucreException {
		List<Genero> tmp;
		try {
			tmp = this.findAllBySpecification(new GeneroByCodigoSpec(genero));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR GENERO POR CODIGO");
		}
		
	}

}
