package ec.com.def.pa.repository;


import javax.ejb.Local;

import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Provincia;

@Local
public interface ProvinciaRepository extends CrudRepository<String, Provincia>{

}