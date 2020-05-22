package ec.com.def.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Parroquia;
import ec.com.def.pa.model.ParroquiaPK;

@Local
public interface ParroquiaRepository extends CrudRepository<ParroquiaPK, Parroquia>{
	
	/**
	 * Metodo alterno de busqueda por PK, necesario para hacer trim de los valores del PK
	 * @param provincia parametro de codigo de provincia
	 * @param canton parametro de codigo de canton
	 * @param parroquia parametro de codigo de parroquia
	 * @return Parroquia encontrada
	 * @throws DefException
	 */
	public Parroquia findByPKFixed( String provincia,  String canton,  String parroquia )  throws DefException;

	public List<Parroquia> findByProvinciaAndCanton( String provincia, String canton, String order ) throws DefException;
	 

}
