package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Apol;
import ec.fin.segurossucre.pa.repository.ApolRepository;
import ec.fin.segurossucre.pa.repository.spec.ApolByNumeroTramiteSpec;

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
    public List<Apol> findByNumeroTramite( String numeroTramite ) throws SegSucreException{
    	List<Apol> bb = null;
    	try {
    		bb= this.findAllBySpecification(new ApolByNumeroTramiteSpec(numeroTramite));
			return bb;
		} catch (Exception e) {
			
			return bb;
		}
    }

	@Override
	public List<Apol> validarNumeroTramite(String numeroTramite) throws SegSucreException {
		try {
    		Query q = this.getEntityManager().createQuery( 
    				"SELECT count(*) FROM apol WHERE awreferext = '"+numeroTramite+
    				"' GROUP BY awpzgpol HAVING COUNT(awpzgpol)>1 "  );
    		q.setParameter( "numeroTramite" , numeroTramite);
    	
    		
			return q.getResultList();
		} catch (Exception e) {
			throw new SegSucreException( Constantes.ERROR_CODE_READ,
					"ERROR EN LA BUSQUEDA DE TAMITE EN APOL CON NUMERO TRAMITE " + numeroTramite + "  " + e.getMessage() );
		}
	}

    

}
