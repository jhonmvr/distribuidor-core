package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.TbPaCaracteristicaCultivo;
@Local
public interface CaracteristicaCultivoRepository extends CrudRepository<Long, TbPaCaracteristicaCultivo> {

}
