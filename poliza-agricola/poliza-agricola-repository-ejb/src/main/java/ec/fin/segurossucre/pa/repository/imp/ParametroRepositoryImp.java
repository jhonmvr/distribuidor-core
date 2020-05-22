package ec.com.def.pa.repository.imp;

import java.util.List;
import javax.ejb.Stateless;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.TbSaParametro;
import ec.com.def.pa.repository.ParametroRepository;
import ec.com.def.pa.repository.spec.ParametroByNombreSpec;


/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "parametroRepository")

public class ParametroRepositoryImp extends GeneralRepositoryImp<Long, TbSaParametro> implements ParametroRepository {
	  /**
     * Default constructor. 
     */
    public ParametroRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
	
	
	  /**
     * Metodo que busca por nombre de parametros
     * @param 
     * @return nombre de parametro
     */
    public TbSaParametro findByNombre( String nombre ) throws DefException{
    	List<TbSaParametro> p= this.findAllBySpecification( new ParametroByNombreSpec(nombre) );
    	if( p != null && !p.isEmpty() ) {
    		return p.get(0);
    	} 
    	return null;
    }
}
