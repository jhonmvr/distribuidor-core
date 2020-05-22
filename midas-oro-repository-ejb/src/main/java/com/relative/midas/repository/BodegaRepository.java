package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiBodega;

@Local
public interface BodegaRepository extends CrudRepository<Long, TbMiBodega>{

	public TbMiBodega findByAgenciaAndNombre(Long idAgencia, String nombreBodega)throws RelativeException;
	
}
