package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.persistence.Specification;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.repository.ContratoRepository;
import com.relative.midas.repository.spec.ContratoByCodigoSpec;
import com.relative.midas.repository.spec.ContratoByCompraAgenciaSpec;
import com.relative.midas.repository.spec.ContratoByIdAnteriorSpec;
import com.relative.midas.repository.spec.ContratoByIdClienteAndFechaCreacion;
import com.relative.midas.repository.spec.ContratoByIdClienteSpec;
import com.relative.midas.repository.spec.ContratoByIdContratoAndVencimientoSpec;
import com.relative.midas.repository.spec.ContratoByIdFundaSpec;
import com.relative.midas.repository.spec.ContratoByIdLiquidacionSpec;
import com.relative.midas.repository.spec.ContratoByIdentificacionClienteSpec;
import com.relative.midas.repository.spec.ContratoSearchByCustomFilterSpec;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.MovimientosDetalleCierreCajaWrapper;
import com.relative.midas.wrapper.RetazarContratos;;

/**
 * Session Bean implementation class ContratoRepositoryImpl
 */
@Stateless(mappedName = "contratoRepository")
public class ContratoRepositoryImpl extends GeneralRepositoryImp<Long, TbMiContrato> implements ContratoRepository {
	@Inject
	Logger log;

	/**
	 * Default constructor.
	 */
	public ContratoRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que obtiene la sencuencia definida
	 * 
	 * @return secuencia generada
	 * @throws RelativeException
	 */

	public List<TbMiContrato> findByTipoCompraIdAgencia(String tipoContrato, Long idAgencia) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecification(new ContratoByCompraAgenciaSpec(tipoContrato, idAgencia));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error no se encontraron Contratos para id agencia: " + idAgencia
					+ " tipoContrato: " + tipoContrato);
		}
		return null;
	}

	public Long CountFindByTipoCompraIdAgencia(String tipoContrato, Long idAgencia) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new ContratoByCompraAgenciaSpec(tipoContrato, idAgencia));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error no se encontraron Contratos para id agencia: " + idAgencia
					+ " tipoContrato: " + tipoContrato + " " + e);
		}
		return null;
	}

	public List<TbMiContrato> findByIdentificacionCliente(String identificacion) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecification(new ContratoByIdentificacionClienteSpec(identificacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente " + e);
		}
		return null;
	}

	public Long countFindByIdentificacionCliente(String identificacion) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new ContratoByIdentificacionClienteSpec(identificacion));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente " + e);
		}
		return null;
	}

	public List<TbMiContrato> findByIdentificacionClientePaged(String identificacion, int page, int pageSize,
			String order, String direction) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ContratoByIdentificacionClienteSpec(identificacion), page,
					pageSize, order, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente" + e);
		}
		return null;
	}

	public List<TbMiContrato> findByIdentificacionClientePaged(String identificacion, int page, int pageSize)
			throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ContratoByIdentificacionClienteSpec(identificacion), page,
					pageSize);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente" + e);
		}
		return null;
	}

	public TbMiContrato findByCodigoContrato(String codigo) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecification(new ContratoByCodigoSpec(codigo));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente" + e);
		}
		return null;
	}

	public List<TbMiContrato> findPorCustomFilterContratos(PaginatedWrapper pw, String fechaDesde, String fechaHasta,
			String codigo, List<EstadoContratoEnum> estado, String identificacion, String cliente, boolean contratosVencidos, Long idAgencia)
			throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(
					new ContratoSearchByCustomFilterSpec(fechaDesde, fechaHasta, codigo, estado, identificacion, cliente,
							contratosVencidos, idAgencia),
					pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar contratos " + e.getMessage());
		}
	}

	/**
	 * Lista contratos por agencia y dos estado en clase personalizada
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<RetazarContratos> findByAngenciaEstado(PaginatedWrapper pw, String idAgencia,
			EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<RetazarContratos> query = cb.createQuery(RetazarContratos.class);
			// ~~> FROM
			Root<TbMiContrato> poll = query.from(TbMiContrato.class);
			Join<TbMiContrato, TbMiAgencia> joinContratoAgencia = poll.join("tbMiAgencia");
			Join<TbMiContrato, TbMiAgente> joinContratoAgente = poll.join("tbMiAgente");
			Join<TbMiContrato, TbMiFunda> joinContratoFunda = poll.join("tbMiFunda");
			Join<TbMiFunda, TbMiBodega> joinFundaBodega = joinContratoFunda.join("tbMiBodega");
			Join<TbMiFunda, TbMiJoya> joinJoya = joinContratoFunda.join("tbMiJoyas");
			// ~~> WHERE
			Predicate where = cb.and(cb.equal(joinContratoAgencia.get("id"), idAgencia),
					poll.get("estado").in(estado1,estado2),joinJoya.get("estado").in(EstadoJoyaEnum.PERFECCIONADO,EstadoJoyaEnum.EXISTENCIA));
			query.where(where);
			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("codigo"), poll.get("proceso"), poll.get("fechaActualizacion"),
					joinContratoAgente.get("primerNombre"), joinContratoFunda.get("codigo"), joinFundaBodega.get("descripcion"),
					poll.get("tipoCompra"),joinContratoFunda.get("id"))
					.distinct(true);
			// ~~> ORDER BY
			if (pw.getSortDirections().equals("asc")) {
				query.orderBy(cb.asc(poll.get(pw.getSortFields())));
			} else {
				query.orderBy(cb.desc(poll.get(pw.getSortFields())));
			}
			// ~~> EJECUTAR CONSULTA
			TypedQuery<RetazarContratos> createQuery = this.getEntityManager().createQuery(query);
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize()).getResultList();
			} else {
				return createQuery.getResultList();
			}
			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	/**
	 * Cuenta contratos por agencia y dos estado en clase personalizada
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Integer countByAngenciaEstado(PaginatedWrapper pw, String idAgencia, EstadoContratoEnum estado1,
			EstadoContratoEnum estado2) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<TbMiContrato> poll = query.from(TbMiContrato.class);
			Join<TbMiContrato, TbMiAgencia> joinContratoAgencia = poll.join("tbMiAgencia");
			Join<TbMiContrato, TbMiFunda> joinContratoFunda = poll.join("tbMiFunda");
			Join<TbMiFunda, TbMiJoya> joinJoya = joinContratoFunda.join("tbMiJoyas");
			Predicate where = cb.and(cb.equal(joinContratoAgencia.get("id"), idAgencia),
					poll.get("estado").in(estado1,estado2),joinJoya.get("estado").in(EstadoJoyaEnum.PERFECCIONADO,EstadoJoyaEnum.EXISTENCIA));
			query.where(where);
			query.select(cb.countDistinct(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			throw new RelativeException("" + e);
		}
	}

	/**
	 * Metodo que obtiene la sencuencia definida
	 * 
	 * @return secuencia generada
	 * @throws RelativeException
	 */
	@Override
	public BigInteger generateSecuencia(String secuenciaAgencia) throws RelativeException {
		try {
			Query query = this.getEntityManager().createNativeQuery("select nextval('" + secuenciaAgencia + "')");
			return (BigInteger) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"GENERAR SECUENCIA " + ex.getMessage());
		}
	}

	@Override
	public Long validateControByIdCliente(Long idCliente, Date fechaInicio , Date fechaFin) throws RelativeException {
		
		try {
			return this.countBySpecification(new ContratoByIdClienteAndFechaCreacion( idCliente, fechaInicio , fechaFin) );
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR CONTRATOS POR CLIENTE Y FECHA DE CREACION" + e.getMessage());
		}
	}
	/**
     * Busca un contrato por id contrato anterior
     * @param idContratoAnterior
     * @return
     * @throws RelativeException
     * @author Kevin Chamorro
     */
	@Override
	public TbMiContrato findByIdAnterior(Long idContratoAnterior) throws RelativeException {
		try {
			List<TbMiContrato> contratos = this.findAllBySpecification(new ContratoByIdAnteriorSpec(idContratoAnterior));
			if(contratos != null && !contratos.isEmpty()) {
				return contratos.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al buscar contrato por id contrato anterior");
		}
	}

	@Override
	public List<TbMiContrato> findByIdCliente(Long idCliente) throws RelativeException {
	List<TbMiContrato> tmp;
	try {
		tmp = this.findAllBySpecification(new ContratoByIdClienteSpec(idCliente));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp;
		}
	}catch (Exception e) {
		throw new RelativeException("Error al buscar contrato por id del cliente " + e);
	}
	return null;
	}

	@Override
	public List<TbMiContrato> findByIdClientePaged(Long idCliente, int page, int pageSize, String order,
			String direction) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ContratoByIdClienteSpec(idCliente), page,
					pageSize, order, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por id de cliente" + e);
		}
		return null;
		
	}

	@Override
	public List<TbMiContrato> findByIdClientePaged(Long idCliente, int page, int pageSize) throws RelativeException {
		List<TbMiContrato> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ContratoByIdClienteSpec(idCliente), page,
					pageSize);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por id de cliente" + e);
		}
		return null;
	}

	@Override
	public Long countFindByIdCliente(Long idCliente) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new ContratoByIdClienteSpec(idCliente));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente " + e);
		}
		return null;
	
	}

	@Override
	public Long validateContratoByIdFunda(Long idFunda) throws RelativeException {
		try {
			return this.countBySpecification(new ContratoByIdFundaSpec( idFunda) );
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR CONTRATOS POR FUNDA " + e.getMessage());
		}
	}

	@Override
	public Long validateContratoByIdLiquidacion(Long idLiquidacion) throws RelativeException {
		try {
			return this.countBySpecification(new ContratoByIdLiquidacionSpec( idLiquidacion) );
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR CONTRATOS POR LIQUIDACION " + e.getMessage());
		}
	}

	@Override
	public TbMiContrato validatePerfeccionamiento(Long idContrato, Date fechaVencimiento) throws RelativeException {
		try {
			List<TbMiContrato> tmp;
			tmp = findAllBySpecification(new ContratoByIdContratoAndVencimientoSpec(idContrato,fechaVencimiento));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CONTRATO POR ID Y FECHA DE VENCIMIENTO");
		}
		return null;
	}

	@Override
	public BigDecimal sumMontoPorCustomFilterContratos(String fechaDesde, String fechaHasta, String codigo,
			List<EstadoContratoEnum> estado, String identificacion, String cliente, boolean contratosVencidos, Long idAgencia)
			throws RelativeException {
		
		try {
			CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		    CriteriaQuery<BigDecimal> sumQuery = criteriaBuilder.createQuery(BigDecimal.class);
		    Specification<TbMiContrato> specification = new ContratoSearchByCustomFilterSpec(fechaDesde, fechaHasta, codigo, estado, identificacion, cliente,
							contratosVencidos, idAgencia);
	      Root<TbMiContrato> root = sumQuery.from(specification.getType());
	      Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
	      sumQuery.select(criteriaBuilder.sum(root.get("valorContrato")));
	      sumQuery.where(predicate);
	      return this.getEntityManager().createQuery(sumQuery).getSingleResult();
		
		}catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL RESERVAR FUNDA " + e);
		}
	}



}
