package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.model.TbPaCanalSecuencia;

@Local
public interface CanalSecuenciaRepository extends CrudRepository<Long, TbPaCanalSecuencia> {

	TbPaCanalSecuencia findByCanalId(RamocanalPK id) throws SegSucreException;

}
