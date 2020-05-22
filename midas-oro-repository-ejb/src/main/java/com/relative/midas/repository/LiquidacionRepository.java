package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiLiquidacion;
@Local
public interface LiquidacionRepository extends CrudRepository<Long, TbMiLiquidacion> {
	public TbMiLiquidacion reservarLiquidacion(Long idAgencia) throws RelativeException;

	
	/**
	 * Busqueda de liquidaciones por id folleto
	 * @param pw
	 * @param idFolletoLiquidacion
	 * @return
	 * @throws RelativeException
	 * @author Kevin chamorro
	 */
	public List<TbMiLiquidacion> findByFolletoLiquidacion(PaginatedWrapper pw, 
			Long idFolletoLiquidacion) throws RelativeException;

	public Integer countLiquidacion(Long idAgencia) throws RelativeException;
}
