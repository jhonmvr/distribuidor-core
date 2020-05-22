package ec.fin.segurossucre.pa.repository;

import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Apol;

@Local
public interface ApolRepository extends CrudRepository<Long, Apol> {
	
	/**
	 * Metodo que busca la informacion del poliza y cliente por numero de tramite.
	 * @param numeroTramite Numero de tramite enviado
	 * @return Listado de entidades de tipo apol encontradas
	 */
	public List<Apol> findByNumeroTramite( String numeroTramite ) throws SegSucreException;
	
	public List<Apol> validarNumeroTramite(String numeroTramite)throws SegSucreException;

}
