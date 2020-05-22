package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.TbSaUsuarioCanal;

@Local
public interface UsuarioCanalRepository extends CrudRepository<Long, TbSaUsuarioCanal>{
	
	/**
     * Metodo que busca canales asociados al usuario, toma el primero registro
     * @param nombreUsuario usuario a buscar
     * @return Usuario canal encontrado
     */
    public TbSaUsuarioCanal findByUsuario( String nombreUsuario );

}
