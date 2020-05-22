package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.TbSaAsegurado;
import ec.fin.segurossucre.pa.repository.AseguradoRepository;
@Stateless(mappedName = "aseguradoRepository")
public class AseguradoRepositoryImp extends GeneralRepositoryImp<Long, TbSaAsegurado> implements AseguradoRepository {

	@Override
	public TbSaAsegurado finByIdentificacion(String identificacion) throws SegSucreException {
		try {
			List<TbSaAsegurado>tmp =null;
			tmp = this.findAllBySpecification(new AseguradoByIdetificacionSpec(identificacion));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_READ,"NO SE PUEDE LEER LA INFORMACION DEL ASEGURADO");
		}
		return null;
	}


}
