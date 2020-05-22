package ec.com.def.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Canton;
import ec.com.def.pa.model.CantonPK;

@Local
public interface CantonRepository extends CrudRepository<CantonPK, Canton> {

	public List<Canton> findByProvincia( String provincia, String order ) throws DefException;
}
