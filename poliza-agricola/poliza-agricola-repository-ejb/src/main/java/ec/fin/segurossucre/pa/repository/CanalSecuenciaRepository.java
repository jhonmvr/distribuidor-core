package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.RamocanalPK;
import ec.com.def.pa.model.TbPaCanalSecuencia;

@Local
public interface CanalSecuenciaRepository extends CrudRepository<Long, TbPaCanalSecuencia> {

	TbPaCanalSecuencia findByCanalId(RamocanalPK id) throws DefException;

}
