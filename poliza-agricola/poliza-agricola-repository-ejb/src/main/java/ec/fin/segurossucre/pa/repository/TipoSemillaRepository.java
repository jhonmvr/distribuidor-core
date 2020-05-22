package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Tiposemilla;
@Local
public interface TipoSemillaRepository extends CrudRepository<Long, Tiposemilla> {

	Tiposemilla findByCodigo(String semilla)throws SegSucreException;

}
