package ec.com.def.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Apol;

@Local
public interface ApolRepository extends CrudRepository<Long, Apol> {
	
	/**
	 * Metodo que busca la informacion del poliza y cliente por numero de tramite.
	 * @param numeroTramite Numero de tramite enviado
	 * @return Listado de entidades de tipo apol encontradas
	 */
	public List<Apol> findByNumeroTramite( String numeroTramite ) throws DefException;
	
	public List<Apol> validarNumeroTramite(String numeroTramite)throws DefException;

}
