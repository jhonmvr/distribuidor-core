package ec.fin.segurossucre.pa.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Acteco;
import ec.fin.segurossucre.pa.model.Ramocanal;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.repository.CanalRepository;
import ec.fin.segurossucre.pa.repository.spec.ActecoByCodigoSpec;
import ec.fin.segurossucre.pa.repository.spec.RamoCanalByIdSpec;
import ec.fin.segurossucre.pa.repository.spec.RamocanalByCodigoSpec;

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
	public Ramocanal findByCodigo(String canal) throws SegSucreException {
		List<Ramocanal> tmp;
		try {
			tmp = this.findAllBySpecification(new RamocanalByCodigoSpec(canal));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CANAL POR CODIGO");
		}
		
	}
    
    

}
