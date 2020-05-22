package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.Canton;
import com.relative.midas.model.CantonPK;
@Local
public interface CantonRepository extends CrudRepository<CantonPK, Canton> {

	public List<Canton> findByProvincia(String provincia, String order)throws RelativeException;
}
