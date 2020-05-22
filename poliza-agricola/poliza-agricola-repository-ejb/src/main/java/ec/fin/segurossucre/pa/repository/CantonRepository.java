package ec.fin.segurossucre.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Canton;
import ec.fin.segurossucre.pa.model.CantonPK;

@Local
public interface CantonRepository extends CrudRepository<CantonPK, Canton> {

	public List<Canton> findByProvincia( String provincia, String order ) throws SegSucreException;
}
