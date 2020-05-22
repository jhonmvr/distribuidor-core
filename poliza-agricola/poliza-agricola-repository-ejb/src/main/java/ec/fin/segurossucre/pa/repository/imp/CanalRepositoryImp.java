package ec.com.def.pa.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Acteco;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.RamocanalPK;
import ec.com.def.pa.repository.CanalRepository;
import ec.com.def.pa.repository.spec.ActecoByCodigoSpec;
import ec.com.def.pa.repository.spec.RamoCanalByIdSpec;
import ec.com.def.pa.repository.spec.RamocanalByCodigoSpec;

/**
 * Session Bean implementation class CanalRepositoryImp
 */
@Stateless(mappedName = "canalRepository")
public class CanalRepositoryImp extends GeneralRepositoryImp<RamocanalPK, Ramocanal> implements CanalRepository {

	@Inject
	Logger log;
    /**
     * Default constructor. 
     */
    public CanalRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    public Ramocanal findByIdFixed( String ramoId, String canalId ) {
    	try {
			return this.findAllBySpecification( new RamoCanalByIdSpec(canalId, ramoId) ).get(0);
		} catch (Exception e) {
			log.info("==Exception Error CanalRepositoryImp.findByIdFixed " + e.getMessage());
		}
    	return null;
    }
    
    public List<Ramocanal> findByRamo( String ramoId ) {
    	try {
			return this.findAllBySpecification( new RamoCanalByIdSpec(null, ramoId) );
		} catch (Exception e) {
			log.info("==Exception Error CanalRepositoryImp.findByRamo " + e.getMessage());
		}
    	return null;
    }

	@Override
	public Ramocanal findByCodigo(String canal) throws DefException {
		List<Ramocanal> tmp;
		try {
			tmp = this.findAllBySpecification(new RamocanalByCodigoSpec(canal));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CANAL POR CODIGO");
		}
		
	}
    
    

}
