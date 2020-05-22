package ec.fin.segurossucre.pa.repository;
import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Riego;
@Local
public interface RiegoRepository extends CrudRepository<Long, Riego> {

	Riego findByCodigo(String riego)throws SegSucreException;

}
