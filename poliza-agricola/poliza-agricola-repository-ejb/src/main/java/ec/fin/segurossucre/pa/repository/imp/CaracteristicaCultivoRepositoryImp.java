package ec.com.def.pa.repository.imp;

import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.TbPaCaracteristicaCultivo;
import ec.com.def.pa.repository.CaracteristicaCultivoRepository;
@Stateless(mappedName = "caracteristicaCultivoRepository")
public class CaracteristicaCultivoRepositoryImp extends GeneralRepositoryImp<Long, TbPaCaracteristicaCultivo>
		implements CaracteristicaCultivoRepository {

}
