package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCliente;
@Local
public interface ClienteRepository  extends CrudRepository<Long, TbMiCliente> {

	public List<TbMiCliente> findClienteByIdentifiacion(String identificacion) throws RelativeException;

	public List<TbMiCliente> findClienteByIdentifiacionPaged(String identificacion, Integer currentPage, Integer pageSize,
			String sortFields, String sortDirections)throws RelativeException;

	public List<TbMiCliente> findClienteByIdentifiacionPaged(String identificacion, Integer currentPage, Integer pageSize)throws RelativeException;

	public Long countClienteByIdentificacion(String identificacion)throws RelativeException;
	/**
	 * Busca clientes por parametros
	 * @param pw
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param secto
	 * @param ciudad
	 * @param nombreReferencia
	 * @param telefonoReferencia
	 * @param celularReferencia
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public List<TbMiCliente> findByParams(PaginatedWrapper pw, String identificacion, String nombre, String apellido, String telefono, 
			String celular,String correo, String secto, String ciudad, String nombreReferencia, String telefonoReferencia,
			String celularReferencia, EstadoMidasEnum estado) throws RelativeException;
	/**
	 * Cuenta clientes por parametros
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param secto
	 * @param ciudad
	 * @param nombreReferencia
	 * @param telefonoReferencia
	 * @param celularReferencia
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public Long countByParams(String identificacion, String nombre, String apellido, String telefono, 
			String celular,String correo, String secto, String ciudad, String nombreReferencia, String telefonoReferencia,
			String celularReferencia, EstadoMidasEnum estado) throws RelativeException;
	
	TbMiCliente registrarCliente(TbMiCliente cliente) throws RelativeException;
}
