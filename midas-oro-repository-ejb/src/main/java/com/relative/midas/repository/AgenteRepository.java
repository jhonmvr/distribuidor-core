package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiAgente;
@Local
public interface AgenteRepository  extends CrudRepository<Long, TbMiAgente> {

	public List<TbMiAgente> agenteSinAsignar() throws RelativeException;
	
	/**
     * Busca agente por nombre de usuario y devuelve el primero que encuentre
     * @param nombreUsuario
     * @return
     * @throws RelativeException
     * @author Kevin Chamorro
     */
	TbMiAgente findByNombreUsuario(String nombreUsuario) throws RelativeException;

	public List<TbMiAgente> findByParams(String nombre, String apellido, String identificacion)throws RelativeException;

	public List<TbMiAgente> findByParams(String nombre, String apellido, String identificacion, int startRecord,
			Integer pageSize, String sortFields, String sortDirections)throws RelativeException;

	public Long countByParams(String nombre, String apellido, String identificacion)throws RelativeException;

	public TbMiAgente findAgenteOrSupervisorByNombreUsuario(String username)throws RelativeException;

}
