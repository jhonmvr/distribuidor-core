package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Acteco;
@Local
public interface ActecoRepository extends CrudRepository<String, Acteco> {

	Acteco findByCodigo(String actividadEconomica)throws DefException;

}
