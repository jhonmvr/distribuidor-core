package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCorteCaja;

@Local
public interface CorteCajaRepository extends CrudRepository<Long, TbMiCorteCaja> {

	public List<TbMiCorteCaja> findByParam(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado,
			Long idAgencia) throws RelativeException;

	public List<TbMiCorteCaja> findByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado,
			Long idAgencia, int inicio, int tamanio) throws RelativeException;

	public List<TbMiCorteCaja> findByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado,
			Long idAgencia, int inicio, int tamanio, String sortField, String sortDirection) throws RelativeException;

	public Long countByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado, Long idAgencia)
			throws RelativeException;

	public TbMiCorteCaja reservarCaja(Long idAgencia) throws RelativeException;

	public TbMiCorteCaja reservarCaja(Long idAgencia, EstadoMidasEnum estado) throws RelativeException;

	public Long countByIdAndEstado(Long id, EstadoMidasEnum estado, Long idAgencia) throws RelativeException;

	public List<TbMiCorteCaja> findByIdAndEstado(Long id, EstadoMidasEnum estado,Long idAgencia) throws RelativeException;

	public List<TbMiCorteCaja> findByIdAndEstado(Long id, EstadoMidasEnum estado,Long idAgencia, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

}
