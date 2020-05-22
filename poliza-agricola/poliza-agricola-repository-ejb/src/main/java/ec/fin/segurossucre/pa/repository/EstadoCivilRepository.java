package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Estadocivil;
@Local
public interface EstadoCivilRepository extends CrudRepository<Long, Estadocivil> {

	Estadocivil findByCodigo(String estadoCivil)throws DefException;

}
