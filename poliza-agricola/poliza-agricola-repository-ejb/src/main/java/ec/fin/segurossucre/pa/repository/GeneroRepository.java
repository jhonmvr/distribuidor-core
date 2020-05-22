package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Genero;
@Local
public interface GeneroRepository extends CrudRepository<Long, Genero> {

	Genero findByCodigo(String genero)throws DefException;

}
