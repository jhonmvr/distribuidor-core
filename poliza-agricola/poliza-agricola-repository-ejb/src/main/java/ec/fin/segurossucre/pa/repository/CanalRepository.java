package ec.fin.segurossucre.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Ramocanal;
import ec.fin.segurossucre.pa.model.RamocanalPK;

@Local
public interface CanalRepository extends CrudRepository<RamocanalPK, Ramocanal>{
	
	public Ramocanal findByIdFixed( String ramoId, String canalId );
    
    public List<Ramocanal> findByRamo( String ramoId );

	public Ramocanal findByCodigo(String canal) throws SegSucreException;

}
