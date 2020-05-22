package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Parroquia;
import ec.fin.segurossucre.pa.model.ParroquiaPK;
import ec.fin.segurossucre.pa.repository.ParroquiaRepository;

/**
 * Session Bean implementation class ParroquiaRepositoryImp
 */
@Stateless(mappedName = "parroquiaRepository")
public class ParroquiaRepositoryImp extends GeneralRepositoryImp<ParroquiaPK, Parroquia> implements ParroquiaRepository {

	
	@Inject
	Logger log;
    /**
     * Default constructor. 
     */
    public ParroquiaRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * Metodo alterno de busqueda por PK, necesario para hacer trim de los valores del PK
	 * @param provincia parametro de codigo de provincia
	 * @param canton parametro de codigo de canton
	 * @param parroquia parametro de codigo de parroquia
	 * @return Parroquia encontrada
	 * @throws SegSucreException
	 */
    public Parroquia findByPKFixed( String provincia, String canton, String parroquia ) {
    	try {
    		log.info( "===>findByPKFixed " );
			Query q = this.getEntityManager().createNativeQuery(
					" SELECT p.PROVINCIAID, p.CANTONID, p.PARROQUIAID, p.PARROQUIANOM, "
					+ "p.PARROQUIAINFX, p.PARROQUIAESTADO FROM PARROQUIA p " + 
					" where trim(p.PROVINCIAID)=:pro AND trim(p.CANTONID)=:can AND  trim(p.PARROQUIAID)=:parr ", Parroquia.class);
			q.setParameter( "pro" , provincia.trim());
			q.setParameter( "can" , canton.trim());
			q.setParameter( "parr" , parroquia.trim());
			log.info( "===>va a correr qyery " );
			List<Parroquia> ps=q.getResultList();
			log.info( "===>va a correr qyery " + ps );
			if(ps != null && !ps.isEmpty()) {
				return ps.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    	return null;
    }
    
    @SuppressWarnings("unchecked")
   	public List<Parroquia> findByProvinciaAndCanton( String provincia, String canton, String order ) throws SegSucreException{
       	try {
   			Query q = this.getEntityManager().createNativeQuery("select * from parroquia  "+
   				 "where trim(provinciaid)=:provincia and trim(cantonid)=:canton and trim(parroquiaestado)=:estado ORDER BY PARROQUIANOM " + order, Parroquia.class);
   			q.setParameter( "provincia" , provincia.trim());
   			q.setParameter( "canton" , canton.trim());
   			q.setParameter( "estado" , "A");
   			return q.getResultList();
   		} catch (IllegalStateException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"IllegalStateException findByProvincia " + e.getMessage() );
   		} catch (QueryTimeoutException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"QueryTimeoutException findByProvincia " + e.getMessage() );
   		}catch (TransactionRequiredException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"TransactionRequiredException findByProvincia " + e.getMessage() );
   		}catch (PessimisticLockException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"TransactionRequiredException findByProvincia " + e.getMessage() );
   		}catch (LockTimeoutException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"LockTimeoutException findByProvincia " + e.getMessage() );
   		}catch (PersistenceException e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"PersistenceException findByProvincia " + e.getMessage() );
   		}catch (Exception e) {
   			throw new SegSucreException( Constantes.ERROR_CODE_READ,"Exception findByProvincia " + e.getMessage() );
   		}
       	
       	 
       	
       }

}
