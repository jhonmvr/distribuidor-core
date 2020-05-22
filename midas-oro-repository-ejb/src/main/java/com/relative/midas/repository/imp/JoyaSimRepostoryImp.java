package com.relative.midas.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiJoyaSim;
import com.relative.midas.repository.JoyaSimRepository;
import com.relative.midas.repository.spec.CotizacionByEstadoSpec;
import com.relative.midas.repository.spec.JoyaSimByidAprobarContratoSpec;
import com.relative.midas.repository.spec.JoyaSimByidCotizacionSpec;
/**
 * Session Bean implementation class JoyaSimRepostoryImp
 */
@Stateless(mappedName = "joyasimRepository")


public class JoyaSimRepostoryImp extends GeneralRepositoryImp<Long, TbMiJoyaSim> implements JoyaSimRepository {
	 /**
     * Default constructor. 
     */
    public JoyaSimRepostoryImp() {
        // TODO Auto-generated constructor stub
    }
    



    @Override
   	public List<TbMiJoyaSim> findByAllPaged(Long id , int page,
   			int pageSize) {
   		List<TbMiJoyaSim> tmp;
       	try {
   			tmp = this.findAllBySpecificationPaged(new JoyaSimByidCotizacionSpec( id),page,pageSize);
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
       	return null;
   	}

   	@Override
   	public List<TbMiJoyaSim> findByAll(Long id) throws RelativeException {
   		List<TbMiJoyaSim> tmp;
       	try {
   			tmp = this.findAllBySpecification(new JoyaSimByidCotizacionSpec( id));
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION  PARA ID " + id) ;
   			
   		}
   		return null;
       	
   	}

   	@Override
	public Long countfindByAll(Long id) throws RelativeException {
   		Long tmp = null;
   		try {
   			tmp = this.countBySpecification(new JoyaSimByidCotizacionSpec( id));
   			
   				return tmp;
   			
   		} catch (Exception e) {
   			//log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION  PARA ID " + id) ;
   			
   		}
	}




	@Override
	public List<TbMiJoyaSim> findByIdAprobarContrato(Long idAprobarContrato) throws RelativeException {
		List<TbMiJoyaSim> tmp;
       	try {
   			tmp = this.findAllBySpecification(new JoyaSimByidAprobarContratoSpec(idAprobarContrato));
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL BUSCAR JOYAS POR ID DE APROBACION " + idAprobarContrato) ;
   			
   		}
   		return null;
	}
}



