package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiRenovacion;
@Local
public interface RenovacionRepository extends CrudRepository<Long, TbMiRenovacion>{

}
