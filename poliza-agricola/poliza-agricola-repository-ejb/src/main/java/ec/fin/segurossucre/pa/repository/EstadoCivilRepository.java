package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Estadocivil;
@Local
public interface EstadoCivilRepository extends CrudRepository<Long, Estadocivil> {

	Estadocivil findByCodigo(String estadoCivil)throws SegSucreException;

}
