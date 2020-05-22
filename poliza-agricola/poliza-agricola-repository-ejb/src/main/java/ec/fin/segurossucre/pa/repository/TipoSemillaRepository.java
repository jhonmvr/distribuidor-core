package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Tiposemilla;
@Local
public interface TipoSemillaRepository extends CrudRepository<Long, Tiposemilla> {

	Tiposemilla findByCodigo(String semilla)throws DefException;

}
