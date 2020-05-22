package com.relative.midas.repository;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.TipoAgenciaEnum;
import com.relative.midas.model.TbMiAgencia;
@Local
public interface AgenciaRepository extends CrudRepository<Long, TbMiAgencia>{
	
	/**
	 * Lista todas las agencias por joyas estado
	 * @param tb
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	List<TbMiAgencia> findAllBySpecificationPaged(Class<TbMiAgencia> tb, Integer page, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	public List<TbMiAgencia> findByCodigoAndNombre(String codigo, String nombre, int startRecord, Integer pageSize,
			String sortFields, String sortDirections)throws RelativeException;

	public List<TbMiAgencia> findByCodigoAndNombre(String codigo, String nombre)throws RelativeException;

	public Long countByCodigoAndNombre(String codigo, String nombre)throws RelativeException;

	public List<TbMiAgencia> findByEstado(EstadoMidasEnum estado) throws RelativeException;

	public BigInteger generateCodigoAgencia() throws RelativeException;

	public List<TbMiAgencia> finByTipoAgencia(TipoAgenciaEnum tipoAgencia) throws RelativeException;

	public BigInteger generateCodigoIngresoEgreso() throws RelativeException;

}
