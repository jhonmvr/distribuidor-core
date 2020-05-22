package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.TbSaParametro;

@Local
public interface ParametroRepository extends CrudRepository<Long, TbSaParametro>{

	
	/**
     * Metodo que busca el nombre de parametro
     * @param nombre
     * @return nombre
     */
	public TbSaParametro findByNombre( String nombre )throws SegSucreException;
}
