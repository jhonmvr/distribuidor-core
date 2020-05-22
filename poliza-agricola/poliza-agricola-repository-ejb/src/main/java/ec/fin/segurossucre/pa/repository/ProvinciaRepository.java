package ec.fin.segurossucre.pa.repository;


import javax.ejb.Local;

import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Provincia;

@Local
public interface ProvinciaRepository extends CrudRepository<String, Provincia>{

}