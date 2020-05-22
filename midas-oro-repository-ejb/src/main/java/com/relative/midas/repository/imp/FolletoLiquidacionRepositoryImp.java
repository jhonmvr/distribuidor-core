package com.relative.midas.repository.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.repository.FolletoLiquidacionRepository;
import com.relative.midas.repository.spec.FolletoLiquidacionByIdAgenciaSpec;
import com.relative.midas.repository.spec.FolletoLiquidacionByParamsSpec;
import com.relative.midas.repository.spec.FolletoLiquidacionSinAsignarSpec;
import com.relative.midas.repository.spec.ValidarFolletoLiquidacionSpec;

@Stateless(mappedName = "folletoLiquidacionRepository")
public class FolletoLiquidacionRepositoryImp extends GeneralRepositoryImp<Long, TbMiFolletoLiquidacion>
		implements FolletoLiquidacionRepository {

	@Override
	public List<TbMiFolletoLiquidacion> findByIdAgencia(Long idAgencia, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		List<TbMiFolletoLiquidacion> list = null;
		try {
			list = this.findAllBySpecificationPaged(new FolletoLiquidacionByIdAgenciaSpec(idAgencia), startRecord,
					pageSize, sortFields, sortDirections);
			if (list != null && !list.isEmpty()) {
				return list;
			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"BUSQUEDA DE LOS PAQUETES DE FUNDA idAgencia" + idAgencia);
		}
		return list;
	}

	@Override
	public List<TbMiFolletoLiquidacion> findByIdAgencia(Long idAgencia) throws RelativeException {
		List<TbMiFolletoLiquidacion> list = null;
		try {
			list = this.findAllBySpecification(new FolletoLiquidacionByIdAgenciaSpec(idAgencia));
			if (list != null && !list.isEmpty()) {
				return list;
			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"BUSQUEDA DE LOS FOLLETOS DE LIQUIDACION idAgencia" + idAgencia);
		}
		return list;
	}

	@Override
	public Long countByIdAgencia(Long idAgencia) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new FolletoLiquidacionByIdAgenciaSpec(idAgencia));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"BUSQUEDA DE LOS FOLLETOS DE LIQUIDACION idAgencia" + idAgencia);
		}
		return null;
	}
	
	@Override
	public List<TbMiFolletoLiquidacion> folletoSinAsignar() throws RelativeException {
		List<TbMiFolletoLiquidacion> list = null;
		try {
			list = this.findAllBySpecification(new FolletoLiquidacionSinAsignarSpec());
			if (list != null && !list.isEmpty()) {
				return list;
			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"BUSQUEDA DE LOS FOLLETOS DE LIQUIDACION");
		}
		return list;
	}
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
	@Override
	public List<TbMiFolletoLiquidacion> findByParams(PaginatedWrapper pw, Long idAgencia, String nombre, 
			Date fechaVigenciaDesde, Date fechaVigenciaHasta) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new FolletoLiquidacionByParamsSpec(idAgencia, nombre, 
						fechaVigenciaDesde, fechaVigenciaHasta));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new FolletoLiquidacionByParamsSpec(idAgencia, nombre, 
							fechaVigenciaDesde, fechaVigenciaHasta), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new FolletoLiquidacionByParamsSpec(idAgencia, nombre, 
							fechaVigenciaDesde, fechaVigenciaHasta));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Busqueda por parametros " + e.getMessage());
		}
	}

	@Override
	public Long countByParams(Long idAgencia, String nombre, Date fechaVigenciaDesde, Date fechaVigenciaHasta)
			throws RelativeException {
		try {
			return this.countBySpecification(new FolletoLiquidacionByParamsSpec(
					idAgencia, nombre, fechaVigenciaDesde, fechaVigenciaHasta));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Conteo de registros folleto liquidacion " + e.getMessage());
		}
	}

	@Override
	public void delete(TbMiFolletoLiquidacion entidad) throws RelativeException {
		try {			
			StringBuilder strQry = new StringBuilder("delete from TbMiLiquidacion where tbMiFolletoLiquidacion.id = :id");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("id", entidad.getId());
			q.executeUpdate();
			strQry = new StringBuilder("delete from TbMiFolletoLiquidacion f where f.id = :id");
			q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("id", entidad.getId());
			q.executeUpdate();
		} catch (Exception e) {
			 throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDEN BORRAR LIQUIDACIONES ASIGNADAS");
		}
		
	}

	@Override
	public Long validarFolletoLiquidacion(String codigo, BigInteger inicio, BigInteger fin) throws RelativeException {
		return this.countBySpecification(new ValidarFolletoLiquidacionSpec(codigo,inicio,fin));
		
	}

}
