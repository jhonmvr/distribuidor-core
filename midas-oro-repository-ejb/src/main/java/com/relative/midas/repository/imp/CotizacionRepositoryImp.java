package com.relative.midas.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCotizacion;
import com.relative.midas.repository.CotizacionRepository;
import com.relative.midas.repository.spec.CotizacionByEstadoSpec;

/**
 * Session Bean implementation class CotizacionRepositoryImp
 */
@Stateless(mappedName = "cotizacionRepository")
public class CotizacionRepositoryImp extends GeneralRepositoryImp<Long, TbMiCotizacion> implements CotizacionRepository  {

	
	/**
     * Default constructor. 
     */
    public CotizacionRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public List<TbMiCotizacion> findByAllPaged(EstadoMidasEnum estado, int page,
			int pageSize) {
		List<TbMiCotizacion> tmp;
    	try {
			tmp = this.findAllBySpecificationPaged(new CotizacionByEstadoSpec(  estado),page,pageSize);
			if( tmp != null && !tmp.isEmpty() ) {
				return tmp;
			}
		} catch (Exception e) {
			//log.info("NO EXISTE REGISTROS PARA cotizacion" +e);
		}
    	return null;
	}

	@Override
	public List<TbMiCotizacion> findByAll(EstadoMidasEnum estado) throws RelativeException {
		List<TbMiCotizacion> tmp;
    	try {
			tmp = this.findAllBySpecification(new CotizacionByEstadoSpec( estado));
			if( tmp != null && !tmp.isEmpty() ) {
				return tmp;
			}
		} catch (Exception e) {
			//log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION DE cotizacion PARA ID " + estado) ;
			
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR: NO EXISTE INFORMACION PROVEEDOR PARA ID " + estado) ;
			
		}
		
	}
}

