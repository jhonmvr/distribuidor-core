package ec.fin.segurossucre.pa.repository;
import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Condicionpredio;
@Local
public interface CondicionPredioRepository extends CrudRepository<Long, Condicionpredio> {

	Condicionpredio findByCodigo(String codCondicion)throws SegSucreException;

}
