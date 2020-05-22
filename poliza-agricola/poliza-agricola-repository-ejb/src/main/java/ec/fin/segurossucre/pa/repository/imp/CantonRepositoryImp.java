package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Canton;
import ec.fin.segurossucre.pa.model.CantonPK;
import ec.fin.segurossucre.pa.repository.CantonRepository;

/**
 * Session Bean implementation class CantonRepositoryImp
 */
@Stateless(mappedName = "cantonRepository")
public class CantonRepositoryImp extends GeneralRepositoryImp<CantonPK, Canton> implements CantonRepository {

    /**
     * Default constructor. 
     */
    public CantonRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<Canton> findByProvincia( String provincia, String order ) throws SegSucreException{
    	try {
			Query q = this.getEntityManager().createNativeQuery( "select * from canton  "+
					" where trim(provinciaid)=:provincia and trim(cantonestado)=:estado ORDER BY CANTONNOM "+order, Canton.class);
			q.setParameter( "provincia" , provincia.trim());
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
