package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiHistoricoJoya;
@Local
public interface HistoricoJoyaRepository extends CrudRepository<Long, TbMiHistoricoJoya>{
	 TbMiHistoricoJoya findByIdJoya(Long idJoya, EstadoJoyaEnum estado ) throws RelativeException;
	 List<TbMiHistoricoJoya> findByIdJoyaList(Long idJoya) throws RelativeException;
}
