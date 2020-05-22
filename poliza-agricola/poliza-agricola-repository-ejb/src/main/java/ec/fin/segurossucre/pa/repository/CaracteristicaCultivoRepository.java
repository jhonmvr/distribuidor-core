package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbPaCaracteristicaCultivo;
@Local
public interface CaracteristicaCultivoRepository extends CrudRepository<Long, TbPaCaracteristicaCultivo> {

}
