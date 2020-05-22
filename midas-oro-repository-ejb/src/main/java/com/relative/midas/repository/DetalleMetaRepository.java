package com.relative.midas.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiDetalleMeta;

@Local
public interface DetalleMetaRepository extends CrudRepository<Long, TbMiDetalleMeta>{


	public BigDecimal valorContrato(Long idAgencia, EstadoContratoEnum tipo, Date fechaInicio, Date fechaFin) throws RelativeException;

	public BigDecimal valorContratoPorVencer(Long idAgencia, Date fechaInicio, Date fechaFin, String diasGracia)
			throws RelativeException;

	public BigDecimal valorContratoVencido(Long idAgencia, Date fechaInicio, Date fechaFin, String diasGracia) throws RelativeException;

	public List<TbMiDetalleMeta> findByIdMeta(Long id)throws RelativeException;

	public BigDecimal carteraNueva(Long idAgencia, Date fechaInicio, Date fechaFin) throws RelativeException;
	
	
	
}
