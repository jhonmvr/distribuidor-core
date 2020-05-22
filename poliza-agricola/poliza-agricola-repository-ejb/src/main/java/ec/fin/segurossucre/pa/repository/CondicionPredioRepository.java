package ec.com.def.pa.repository;
import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Condicionpredio;
@Local
public interface CondicionPredioRepository extends CrudRepository<Long, Condicionpredio> {

	Condicionpredio findByCodigo(String codCondicion)throws DefException;

}
