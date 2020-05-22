package ec.fin.segurossucre.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Parroquia;
import ec.fin.segurossucre.pa.model.ParroquiaPK;

@Local
public interface ParroquiaRepository extends CrudRepository<ParroquiaPK, Parroquia>{
	
	/**
	 * Metodo alterno de busqueda por PK, necesario para hacer trim de los valores del PK
	 * @param provincia parametro de codigo de provincia
	 * @param canton parametro de codigo de canton
	 * @param parroquia parametro de codigo de parroquia
	 * @return Parroquia encontrada
	 * @throws SegSucreException
	 */
	public Parroquia findByPKFixed( String provincia,  String canton,  String parroquia )  throws SegSucreException;

	public List<Parroquia> findByProvinciaAndCanton( String provincia, String canton, String order ) throws SegSucreException;
	 

}
