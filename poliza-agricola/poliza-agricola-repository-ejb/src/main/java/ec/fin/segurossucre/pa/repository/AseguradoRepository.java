package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.TbSaAsegurado;
@Local
public interface AseguradoRepository extends CrudRepository<Long, TbSaAsegurado> {

	TbSaAsegurado finByIdentificacion(String identificacion) throws SegSucreException;

}
