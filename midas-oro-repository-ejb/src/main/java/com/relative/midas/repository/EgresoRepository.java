package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiEgreso;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiJoyaSim;

@Local
public interface EgresoRepository extends CrudRepository<Long,TbMiEgreso> {

	

}


