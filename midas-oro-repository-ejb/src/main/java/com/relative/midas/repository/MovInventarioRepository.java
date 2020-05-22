package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiMovInventario;

@Local
public interface MovInventarioRepository extends CrudRepository<Long, TbMiMovInventario>{
	
	public List<TbMiMovInventario> findByInventarioJoya( Long idInventarioJoya ) throws RelativeException;
	
}
