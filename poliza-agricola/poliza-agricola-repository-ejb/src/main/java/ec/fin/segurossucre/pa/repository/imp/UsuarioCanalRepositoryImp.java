package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.TbSaUsuarioCanal;
import ec.com.def.pa.repository.UsuarioCanalRepository;
import ec.com.def.pa.repository.spec.UsuarioCanalByUsuarioSpec;

/**
 * Session Bean implementation class UsuarioCanalRepositoryImp
 */
@Stateless(mappedName = "usuarioCanalRepository")
public class UsuarioCanalRepositoryImp extends GeneralRepositoryImp<Long, TbSaUsuarioCanal> implements UsuarioCanalRepository {

    /**
     * Default constructor. 
     */
    public UsuarioCanalRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Metodo que busca canales asociados al usuario, toma el primero registro
     * @param nombreUsuario usuario a buscar
     * @return Usuario canal encontrado
     */
    public TbSaUsuarioCanal findByUsuario( String nombreUsuario ) {
    	List<TbSaUsuarioCanal> ucs= this.findAllBySpecification( new UsuarioCanalByUsuarioSpec(nombreUsuario) );
    	if( ucs != null && !ucs.isEmpty() ) {
    		return ucs.get(0);
    	} 
    	return null;
    }

}
