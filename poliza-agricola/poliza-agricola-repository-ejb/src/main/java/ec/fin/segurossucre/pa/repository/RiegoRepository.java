package ec.com.def.pa.repository;
import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Riego;
@Local
public interface RiegoRepository extends CrudRepository<Long, Riego> {

	Riego findByCodigo(String riego)throws DefException;

}
