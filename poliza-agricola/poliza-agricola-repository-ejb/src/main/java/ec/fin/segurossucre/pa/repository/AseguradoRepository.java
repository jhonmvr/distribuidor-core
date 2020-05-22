package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbSaAsegurado;
@Local
public interface AseguradoRepository extends CrudRepository<Long, TbSaAsegurado> {

	TbSaAsegurado finByIdentificacion(String identificacion) throws DefException;

}
