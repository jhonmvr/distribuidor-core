package com.relative.midas.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiFolletoLiquidacion;

@Local
public interface FolletoLiquidacionRepository extends CrudRepository<Long, TbMiFolletoLiquidacion>{

	public List<TbMiFolletoLiquidacion> findByIdAgencia(Long idAgencia, int startRecord, Integer pageSize, String sortFields,
			String sortDirections)throws RelativeException;
	public List<TbMiFolletoLiquidacion> findByIdAgencia(Long idAgencia)throws RelativeException;
	public Long countByIdAgencia(Long idAgencia)throws RelativeException;
	public List<TbMiFolletoLiquidacion> folletoSinAsignar() throws RelativeException;
	/**
	 * Busqueda de folletos por agencia, nombre, fecha de vigencia rango
	 * @param pw
	 * @param idAgencia
	 * @param nombre
	 * @param fechaVigenciaDesde
	 * @param fechaVigenciaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin CHamorro
	 */
	List<TbMiFolletoLiquidacion> findByParams(PaginatedWrapper pw, Long idAgencia, String nombre, 
			Date fechaVigenciaDesde, Date fechaVigenciaHasta) throws RelativeException;
	
	Long countByParams(Long idAgencia, String nombre, 
			Date fechaVigenciaDesde, Date fechaVigenciaHasta) throws RelativeException;
	public void delete(TbMiFolletoLiquidacion entidad)throws RelativeException;
	public Long validarFolletoLiquidacion(String codigo, BigInteger inicio, BigInteger fin)throws RelativeException;
	
}
