package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.repository.ClienteRepository;
import com.relative.midas.repository.spec.ClienteByIdentificacionSpec;
import com.relative.midas.repository.spec.ClienteByParamsSpec;
import com.relative.midas.repository.spec.LoteByNombreLoteSpec;
import com.relative.midas.repository.spec.MovEntreCajaByParamsSpec;

/**
 * Session Bean implementation class ClienteRepositoryImp
 */
@Stateless(mappedName = "clienteRepository")
public class ClienteRepositoryImp extends GeneralRepositoryImp<Long, TbMiCliente> implements ClienteRepository {
	/**
	 * Default constructor.
	 */
	public ClienteRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TbMiCliente> findClienteByIdentifiacion(String identificacion) throws RelativeException {
		List<TbMiCliente> tmp;
		try {
			tmp = this.findAllBySpecification(new ClienteByIdentificacionSpec(identificacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);

		}
		return null;

	}

	@Override
	public List<TbMiCliente> findClienteByIdentifiacionPaged(String identificacion, Integer currentPage,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		List<TbMiCliente> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ClienteByIdentificacionSpec(identificacion), currentPage,
					pageSize, sortFields, sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);

		}
		return null;
	}

	@Override
	public List<TbMiCliente> findClienteByIdentifiacionPaged(String identificacion, Integer currentPage,
			Integer pageSize) throws RelativeException {
		List<TbMiCliente> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ClienteByIdentificacionSpec(identificacion), currentPage,
					pageSize);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);

		}
		return null;
	}

	@Override
	public Long countClienteByIdentificacion(String identificacion) throws RelativeException {

		try {
			Long tmp = countBySpecification(new ClienteByIdentificacionSpec(identificacion));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiCliente");
		}
		return null;
	}

	@Override
	public List<TbMiCliente> findByParams(PaginatedWrapper pw, String identificacion, String nombre, String apellido,
			String telefono, String celular, String correo, String secto, String ciudad, String nombreReferencia,
			String telefonoReferencia, String celularReferencia, EstadoMidasEnum estado) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, nombre, apellido, telefono, celular, 
						correo, secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new ClienteByParamsSpec(identificacion, nombre, apellido, telefono, celular, 
							correo, secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado), pw.getStartRecord(), 
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, nombre, apellido, telefono, celular, 
							correo, secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer clientes, " + e.getMessage());
		}
	}

	@Override
	public Long countByParams(String identificacion, String nombre, String apellido, String telefono, String celular,
			String correo, String secto, String ciudad, String nombreReferencia, String telefonoReferencia,
			String celularReferencia, EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.countBySpecification(new ClienteByParamsSpec(identificacion, nombre, apellido, telefono, celular, 
					correo, secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Conteo de registros cliente, " + e.getMessage());
		}
	}
	
	@Override
	public TbMiCliente registrarCliente(TbMiCliente cliente) throws RelativeException {
		try {
			getEntityManager().persist(cliente);
			getEntityManager().flush();
			getEntityManager().refresh(cliente);
			return cliente;
		} catch (PersistenceException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ya existe un cliente con la identificacion ingresada");
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, "EN LA CREACION DE "
					+ cliente.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

}