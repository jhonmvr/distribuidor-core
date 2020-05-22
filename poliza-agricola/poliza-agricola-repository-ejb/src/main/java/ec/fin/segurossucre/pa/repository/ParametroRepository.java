package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbSaParametro;

@Local
public interface ParametroRepository extends CrudRepository<Long, TbSaParametro>{

	
	/**
     * Metodo que busca el nombre de parametro
     * @param nombre
     * @return nombre
     */
	public TbSaParametro findByNombre( String nombre )throws DefException;
}
