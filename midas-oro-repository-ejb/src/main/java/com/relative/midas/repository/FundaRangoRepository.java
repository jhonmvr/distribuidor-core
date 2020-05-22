package com.relative.midas.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.model.TbMiFundaRango;



@Local
public interface FundaRangoRepository extends CrudRepository<Long, TbMiFundaRango >{
	
	public Long countValidacionRango(Long rangoInicial) throws RelativeException;

	public List<TbMiFundaRango> findByIdAgencia(Long idAgencia, int startRecord, Integer pageSize, String sortFields, String sortDirections) throws RelativeException;

	public Long countByIdAgencia(Long idAgencia) throws RelativeException;

	public List<TbMiFundaRango> findByIdAgencia(Long idAgencia) throws RelativeException;

	public List<TbMiFundaRango> fundaRangoSinAsginar() throws RelativeException;

	List<TbMiFundaRango> findByParams(PaginatedWrapper pw, String idAgencia, EstadoMidasEnum estado,
			String nombrePaquete, BigDecimal rangoDesde, BigDecimal rangoHasta) throws RelativeException;

	public void deleteFunda(TbMiFundaRango entidad)throws RelativeException;

}
