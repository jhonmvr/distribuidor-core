package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoOro;

@Local
public interface TipoOroRepository extends CrudRepository<Long, TbMiTipoOro> {

	public List<TbMiTipoOro> findByAll(EstadoMidasEnum estado) throws RelativeException;

	public Long countfindByAll(EstadoMidasEnum estado) throws RelativeException;

	public List<TbMiTipoOro> findByAllPaged(EstadoMidasEnum estado, int page, int pageSize);

	public TbMiTipoOro findByQuilate(String quilate) throws RelativeException;
}
