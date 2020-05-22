package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiMeta;

@Local
public interface MetaRepository extends CrudRepository<Long, TbMiMeta>{

	
	public List<TbMiMeta> findByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException;

	public List<TbMiMeta> findByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre) throws RelativeException;

	public Long countByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre) throws RelativeException;

	public List<TbMiMeta> metaActiva(EstadoMidasEnum estado) throws RelativeException;
	
	
	
}
