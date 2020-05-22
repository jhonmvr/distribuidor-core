package ec.fin.segurossucre.pa.repository.imp;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.TbPaCaracteristicaCultivo;
import ec.fin.segurossucre.pa.repository.CaracteristicaCultivoRepository;
@Stateless(mappedName = "caracteristicaCultivoRepository")
public class CaracteristicaCultivoRepositoryImp extends GeneralRepositoryImp<Long, TbPaCaracteristicaCultivo>
		implements CaracteristicaCultivoRepository {

}
