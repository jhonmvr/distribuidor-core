package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.NotificacionEnum;
import com.relative.midas.model.TbMiNotificacion;

@Local
public interface NotificacionRepository extends CrudRepository<Long, TbMiNotificacion>{

	public TbMiNotificacion findByMulti(NotificacionEnum tipo, EstadoMidasEnum estado, Long idAgencia, Long idReferencia)
			throws RelativeException;

	public List<TbMiNotificacion> findByIdAgencia(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idAgencia)throws RelativeException;

	public List<TbMiNotificacion> findByIdAgencia(Long idAgencia)throws RelativeException;

	public Long countByIdAgencia(Long idAgencia)throws RelativeException;

	public void deleteEntity(Long id)throws RelativeException;

	
	
}
