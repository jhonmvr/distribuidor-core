package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiFundaRango;
import com.relative.midas.repository.FundaRangoRepository;
import com.relative.midas.repository.spec.FundaRangoByIdAgenciaSpec;
import com.relative.midas.repository.spec.FundaRangoByRangoInicialSpec;
import com.relative.midas.repository.spec.FundaRangoSinAsignarSpec;
import com.relative.midas.repository.spec.ValidacionByRangoInicialFinalSpec;

/**
 * Session Bean implementation class TipoOroRepositoryImp
 */
@Stateless(mappedName = "fundarangoRepository")
public class FundaRangoRepositoryImp extends GeneralRepositoryImp<Long, TbMiFundaRango>
		implements FundaRangoRepository {
	/**
	 * Default constructor.
	 */
	public FundaRangoRepositoryImp() {
		//
	}

	public Long countValidacionRango(Long rangoInicial) throws RelativeException {
		try {
			Long contador = this.countBySpecification(new ValidacionByRangoInicialFinalSpec(rangoInicial));
			if (contador != null) {
				return contador;
			}
			return contador != null ? contador : 0;

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	@Override
	public List<TbMiFundaRango> findByIdAgencia(Long idAgencia) throws RelativeException {
		List<TbMiFundaRango> list = null;
		try {
			list = this.findAllBySpecification(new FundaRangoByIdAgenciaSpec(idAgencia));
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
	public List<TbMiFundaRango> findByIdAgencia(Long idAgencia, int startRecord, Integer pageSize, String sortFields,
			String sortDirections) throws RelativeException {
		List<TbMiFundaRango> list = null;
		try {
			list = this.findAllBySpecificationPaged(new FundaRangoByIdAgenciaSpec(idAgencia), startRecord, pageSize,
					sortFields, sortDirections);
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
	public Long countByIdAgencia(Long idAgencia) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new FundaRangoByIdAgenciaSpec(idAgencia));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"BUSQUEDA DE LOS PAQUETES DE FUNDA idAgencia" + idAgencia);
		}
		return null;
	}

	@Override
	public List<TbMiFundaRango> fundaRangoSinAsginar() throws RelativeException {
		List<TbMiFundaRango> list = null;
		try {
			list = this.findAllBySpecification(new FundaRangoSinAsignarSpec());
			if (list != null && !list.isEmpty()) {
				return list;
			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "BUSQUEDA DE LOS PAQUETES DE FUNDA idAgencia");
		}
		return list;
	}

	public List<TbMiFundaRango> findByParams(PaginatedWrapper pw, String idAgencia, EstadoMidasEnum estado,
			String nombrePaquete, BigDecimal rangoDesde, BigDecimal rangoHasta) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(
						new FundaRangoByRangoInicialSpec(idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(
							new FundaRangoByRangoInicialSpec(idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta),
							pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(
							new FundaRangoByRangoInicialSpec(idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer movimientos entre cajas");
		}
	}

	@Override
	public void deleteFunda(TbMiFundaRango entidad) throws RelativeException {
		try {			
			StringBuilder strQry = new StringBuilder("delete from TbMiFunda where tbMifundaRango.id = :id");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("id", entidad.getId());
			q.executeUpdate();
			strQry = new StringBuilder("delete from TbMiFundaRango f where f.id = :id");
			q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("id", entidad.getId());
			q.executeUpdate();
		} catch (Exception e) {
			 throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDEN BORRAR FUNDAS ASIGNADAS");
		}
		
	}

}
