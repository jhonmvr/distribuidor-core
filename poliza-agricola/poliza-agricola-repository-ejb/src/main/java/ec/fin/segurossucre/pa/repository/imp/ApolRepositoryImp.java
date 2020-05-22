package ec.com.def.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Apol;
import ec.com.def.pa.repository.ApolRepository;
import ec.com.def.pa.repository.spec.ApolByNumeroTramiteSpec;

/**
 * Session Bean implementation class ApolRepositoryImp
 */
@Stateless(mappedName = "apolRepository")
public class ApolRepositoryImp extends GeneralRepositoryImp<Long, Apol> implements ApolRepository {

    /**
     * Default constructor. 
     */
    public ApolRepositoryImp() {
        // no implementado
    }
    
    /**
	 * Metodo que busca la informacion del poliza y cliente por numero de tramite.
	 * @param numeroTramite Numero de tramite enviado
	 * @return Listado de entidades de tipo apol encontradas
	 */
    public List<Apol> findByNumeroTramite( String numeroTramite ) throws DefException{
    	List<Apol> bb = null;
    	try {
    		bb= this.findAllBySpecification(new ApolByNumeroTramiteSpec(numeroTramite));
			return bb;
		} catch (Exception e) {
			
			return bb;
		}
    }

	@Override
	public List<Apol> validarNumeroTramite(String numeroTramite) throws DefException {
		try {
    		Query q = this.getEntityManager().createQuery( 
    				"SELECT count(*) FROM apol WHERE awreferext = '"+numeroTramite+
    				"' GROUP BY awpzgpol HAVING COUNT(awpzgpol)>1 "  );
    		q.setParameter( "numeroTramite" , numeroTramite);
    	
    		
			return q.getResultList();
		} catch (Exception e) {
			throw new DefException( Constantes.ERROR_CODE_READ,
					"ERROR EN LA BUSQUEDA DE TAMITE EN APOL CON NUMERO TRAMITE " + numeroTramite + "  " + e.getMessage() );
		}
	}

    

}
