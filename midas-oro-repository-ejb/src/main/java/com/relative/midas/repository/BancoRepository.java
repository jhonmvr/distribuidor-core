package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;

@Local
public interface BancoRepository extends CrudRepository<Long, TbMiBanco>{

	List<TbMiBanco> findByParam(EstadoMidasEnum estado, String nombre, int startRecord, Integer pageSize, String sortFields,
			String sortDirections)throws RelativeException;

	List<TbMiBanco> findByParam(EstadoMidasEnum estado, String nombre)throws RelativeException;

	Long countByParam(EstadoMidasEnum estado, String nombre)throws RelativeException;

}
