package ec.fin.segurossucre.pa.repository;
import javax.ejb.Local;

import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Nacionalidad;
@Local
public interface NacionalidadRepository extends CrudRepository<String, Nacionalidad> {

}
