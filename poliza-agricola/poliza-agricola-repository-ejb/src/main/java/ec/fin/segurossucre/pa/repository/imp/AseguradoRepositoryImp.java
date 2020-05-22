package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.TbSaAsegurado;
import ec.com.def.pa.repository.AseguradoRepository;
@Stateless(mappedName = "aseguradoRepository")
public class AseguradoRepositoryImp extends GeneralRepositoryImp<Long, TbSaAsegurado> implements AseguradoRepository {

	@Override
	public TbSaAsegurado finByIdentificacion(String identificacion) throws DefException {
		try {
			List<TbSaAsegurado>tmp =null;
			tmp = this.findAllBySpecification(new AseguradoByIdetificacionSpec(identificacion));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,"NO SE PUEDE LEER LA INFORMACION DEL ASEGURADO");
		}
		return null;
	}


}
