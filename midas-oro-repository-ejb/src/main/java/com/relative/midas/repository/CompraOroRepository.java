package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiCompraOro;

@Local
public interface CompraOroRepository extends CrudRepository<Long, TbMiCompraOro>{

	List<TbMiCompraOro> listByIdFunda(Long idFunda) throws RelativeException;

	TbMiCompraOro findByQuilateAndFunda(String quilate, Long idFunda)throws RelativeException;
	
	
	
}
