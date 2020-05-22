package ec.com.def.pa.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Utiliza CDI como alias de recursos JEE como persistence context, para ser usados en CDI beans
 * @author RELATIVE ENGINE - LUIS TAMAYO
 *
 */
public class ResourceCDIJEE {
	@Produces
    @PersistenceContext
    private EntityManager em;

	
    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        //return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    	Logger tmp =Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    	tmp.setLevel( Level.INFO );
    	return tmp;
    }
}
