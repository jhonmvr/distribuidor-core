package com.relative.midas.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.midas.model.TbMiMovInventario;
import com.relative.midas.repository.MovInventarioRepository;
import com.relative.midas.repository.spec.MovimientoInventarioByInventarioJoyaSpec;

/**
 * Session Bean implementation class InventarioRepositoryImp
 */
@Stateless(mappedName = "movInventarioRepository")
public class MovInventarioRepositoryImp extends GeneralRepositoryImp<Long, TbMiMovInventario> implements MovInventarioRepository {
	/**
     * Default constructor. 
     */
    public MovInventarioRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    public List<TbMiMovInventario> findByInventarioJoya( Long idInventarioJoya ) throws RelativeException{
    	return this.findAllBySpecification( new MovimientoInventarioByInventarioJoyaSpec(idInventarioJoya) );
    }
}



