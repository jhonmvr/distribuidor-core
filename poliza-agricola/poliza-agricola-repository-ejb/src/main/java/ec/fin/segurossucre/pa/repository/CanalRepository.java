package ec.com.def.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.RamocanalPK;

@Local
public interface CanalRepository extends CrudRepository<RamocanalPK, Ramocanal>{
	
	public Ramocanal findByIdFixed( String ramoId, String canalId );
    
    public List<Ramocanal> findByRamo( String ramoId );

	public Ramocanal findByCodigo(String canal) throws DefException;

}
