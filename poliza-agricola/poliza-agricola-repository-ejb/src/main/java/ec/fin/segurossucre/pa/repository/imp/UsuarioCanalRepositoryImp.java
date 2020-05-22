package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.TbSaUsuarioCanal;
import ec.fin.segurossucre.pa.repository.UsuarioCanalRepository;
import ec.fin.segurossucre.pa.repository.spec.UsuarioCanalByUsuarioSpec;

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
