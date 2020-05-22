package ec.fin.segurossucre.pa.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Un01;

@Local
public interface Un01Repository extends CrudRepository<Long, Un01> {
	public List<Un01> findByNumeroTramite( String numeroTramite, Date fechaEmision ) throws SegSucreException;

	public Un01 finByIdentificacion(String identificacion)throws SegSucreException;

}
