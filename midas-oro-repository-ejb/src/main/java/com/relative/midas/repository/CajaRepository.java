package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiCaja;

@Local
public interface CajaRepository extends CrudRepository<Long, TbMiCaja>{

	public TbMiCaja findCajaByAgenciaId(Long idAgencia) throws RelativeException;
		
}
