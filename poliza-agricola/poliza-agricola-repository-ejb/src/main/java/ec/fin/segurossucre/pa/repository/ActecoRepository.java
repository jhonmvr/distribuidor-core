package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Acteco;
@Local
public interface ActecoRepository extends CrudRepository<String, Acteco> {

	Acteco findByCodigo(String actividadEconomica)throws SegSucreException;

}
