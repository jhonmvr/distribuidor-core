package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Genero;
@Local
public interface GeneroRepository extends CrudRepository<Long, Genero> {

	Genero findByCodigo(String genero)throws SegSucreException;

}
