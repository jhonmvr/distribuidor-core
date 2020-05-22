package ec.com.def.pa.repository.imp;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Ramoplan;
import ec.com.def.pa.model.RamoplanPK;
import ec.com.def.pa.repository.RamoPlanRepository;
import ec.com.def.pa.repository.spec.RamoplanByCodigoSpec;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Session Bean implementation class RamoPlanRepositoryImp
 */
@Stateless(mappedName = "ramoPlanRepository")
public class RamoPlanRepositoryImp extends GeneralRepositoryImp<RamoplanPK, Ramoplan> implements RamoPlanRepository {

    /**
     * Default constructor. 
     */
    public RamoPlanRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Metodo alterno de busqueda por PK, necesario para hacer trim de los valores del PK
     * @param ramoid Parametro de codigo de ramoid
     * @param ramoplanid Parametro de codigo de ramoplanid
     * @return Ramoplan Entrontrado
     */
    public Ramoplan findByPKFixed( String ramoid, String ramoplanid) throws DefException{
    	try {
			Query q = this.getEntityManager().createNativeQuery("select * from ramoplan  "
					+ " where trim(ramoid)=:ramoid AND trim(ramoplanid)=:ramoplanid ", Ramoplan.class);
			q.setParameter( "ramoid" , ramoid.trim());
			q.setParameter( "ramoplanid" , ramoplanid.trim());
			return (Ramoplan)q.getSingleResult();
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ,"ERROR EN LA BUSQUEDA POR CLAVE PRIMARIA DE CULTIVOS RAMO" 
					+ ramoid + " CULTIVO " + ramoplanid + "  " + e.getMessage() );
		}
    }

	@Override
	public Ramoplan findByCodigo(String cultivo) throws DefException {
		List<Ramoplan> tmp;
		try {
			tmp = this.findAllBySpecification(new RamoplanByCodigoSpec(cultivo));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CULTIVO POR CODIGO");
		}
		
	}

}
