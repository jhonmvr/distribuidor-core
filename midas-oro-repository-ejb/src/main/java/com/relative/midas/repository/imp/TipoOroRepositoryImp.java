package com.relative.midas.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoOro;
import com.relative.midas.repository.TipoOroRepository;
import com.relative.midas.repository.spec.CotizacionByEstadoSpec;
import com.relative.midas.repository.spec.TipoOroByEstadoSpec;
import com.relative.midas.repository.spec.TipoOroByQuilateSpec;


/**
 * Session Bean implementation class TipoOroRepositoryImp
 */
@Stateless(mappedName = "tipooroRepository")
public class TipoOroRepositoryImp extends GeneralRepositoryImp<Long, TbMiTipoOro> implements TipoOroRepository {
	/**
     * Default constructor. 
     */
    public TipoOroRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public List<TbMiTipoOro> findByAllPaged(EstadoMidasEnum estado, int page,
   			int pageSize) {
   		List<TbMiTipoOro> tmp;
       	try {
   			tmp = this.findAllBySpecificationPaged(new TipoOroByEstadoSpec(  estado),page,pageSize);
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			//log.info("NO EXISTE REGISTROS PARA cotizacion" +e);
   		}
       	return null;
   	}

   	@Override
   	public List<TbMiTipoOro> findByAll(EstadoMidasEnum estado) throws RelativeException {
   		List<TbMiTipoOro> tmp;
       	try {
   			tmp = this.findAllBySpecification(new TipoOroByEstadoSpec( estado));
   			if( tmp != null && !tmp.isEmpty() ) {
   				return tmp;
   			}
   		} catch (Exception e) {
   			//log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION DE TIPO ORO PARA ID " + estado) ;
   			
   		}
   		return null;
       	
   	}

   	@Override
   	public Long countfindByAll(EstadoMidasEnum estado) throws RelativeException {
   		Long tmp = null;
   		try {
   			tmp = this.countBySpecification(new CotizacionByEstadoSpec( estado));
   			
   				return tmp;
   			
   		} catch (Exception e) {
   			//log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
   			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION TIPO ORO PARA ID " + estado) ;
   			
   		}
   		
   	}

	@Override
	public TbMiTipoOro findByQuilate(String quilate) throws RelativeException {
		try {
			List<TbMiTipoOro> tmp = this.findAllBySpecification(new TipoOroByQuilateSpec(quilate));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TIPO DE ORO POR VALOR DE QUILATE");
		}
		return null;
	}
}

