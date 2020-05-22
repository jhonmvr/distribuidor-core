package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiJoyaSim;

@Local
public interface JoyaSimRepository extends CrudRepository<Long,TbMiJoyaSim> {

		public List<TbMiJoyaSim> findByAll(Long id)  throws RelativeException;
	
		public List<TbMiJoyaSim> findByAllPaged(Long id,int page, int pageSize);
		public Long countfindByAll(Long id) throws RelativeException;

		public List<TbMiJoyaSim> findByIdAprobarContrato(Long idAprobarContrato)throws RelativeException;
}
