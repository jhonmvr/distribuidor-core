package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;
import javax.ejb.Stateless;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.TbSaParametro;
import ec.fin.segurossucre.pa.repository.ParametroRepository;
import ec.fin.segurossucre.pa.repository.spec.ParametroByNombreSpec;


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
    public TbSaParametro findByNombre( String nombre ) throws SegSucreException{
    	List<TbSaParametro> p= this.findAllBySpecification( new ParametroByNombreSpec(nombre) );
    	if( p != null && !p.isEmpty() ) {
    		return p.get(0);
    	} 
    	return null;
    }
}
