package ec.com.def.pa.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Un01;

@Local
public interface Un01Repository extends CrudRepository<Long, Un01> {
	public List<Un01> findByNumeroTramite( String numeroTramite, Date fechaEmision ) throws DefException;

	public Un01 finByIdentificacion(String identificacion)throws DefException;

}
