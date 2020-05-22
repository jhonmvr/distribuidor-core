package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiCuenta;

@Local
public interface CuentaRepository extends CrudRepository<Long, TbMiCuenta>{

	public TbMiCuenta findCuentaByCodigo(String cuenta) throws RelativeException;
		
}
