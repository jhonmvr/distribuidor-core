package ec.com.def.pa.repository;
import javax.ejb.Local;

import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Nacionalidad;
@Local
public interface NacionalidadRepository extends CrudRepository<String, Nacionalidad> {

}
