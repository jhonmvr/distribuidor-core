package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAbono;

@Local
public interface AbonoRepository extends CrudRepository<Long, TbMiAbono>{
	
	/**
	 * Metodo que busca la entidad por identificacion cliente
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAbono> getAbonoPorIdentificacionCliente(String identificacion) throws RelativeException;

	public List<TbMiAbono> findByIdClient(Long idCliente) throws RelativeException;

	public List<TbMiAbono> findByEstadoAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, EstadoMidasEnum pendienteHabilitante, String identificacion)throws RelativeException;

	public List<TbMiAbono> findByEstadoAndIdentificacion(EstadoMidasEnum pendienteHabilitante, String identificacion)throws RelativeException;

	public Long countByEstadoAndIdentificacion(EstadoMidasEnum pendienteHabilitante, String identificacion)throws RelativeException;
	
}
