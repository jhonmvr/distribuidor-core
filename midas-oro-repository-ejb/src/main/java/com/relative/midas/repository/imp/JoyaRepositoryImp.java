package com.relative.midas.repository.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiCompraOro;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiMovInventario;
import com.relative.midas.model.TbMiTipoJoya;
import com.relative.midas.repository.JoyaRepository;
import com.relative.midas.repository.spec.JoyaByEstadoAndCompradorSpec;
import com.relative.midas.repository.spec.JoyaByFundaId;
import com.relative.midas.repository.spec.JoyaByParamsSpec;
import com.relative.midas.repository.spec.JoyasByContratoSpec;
import com.relative.midas.repository.spec.JoyasByEstadosCodigoFechasSpec;
import com.relative.midas.repository.spec.JoyasByIdFundaSpec;
import com.relative.midas.wrapper.JoyaVentaVitrina;
import com.relative.midas.wrapper.RetazarJoya;

/**
 * Session Bean implementation class JoyaRepositoryImp
 */
@Stateless(mappedName = "joyaRepository")
public class JoyaRepositoryImp extends GeneralRepositoryImp<Long, TbMiJoya> implements JoyaRepository {

	@Inject
	Logger log;

	/**
	 * Default constructor.
	 */
	public JoyaRepositoryImp() {
		//
	}

	@Override
	public List<TbMiJoya> joyaByFundaId(Long idFunda) throws RelativeException {
		List<TbMiJoya> tmp;
		try {
			tmp = this.findAllBySpecification(new JoyaByFundaId(idFunda));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error joyaByFundaId: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza el estado del tipo de joya por el estado de joya
	 * 
	 * @param estado
	 * @param codigoContrato
	 * @return
	 */
	@Override
	public int updateBatch(EstadoJoyaEnum estado, Long idFunda) throws RelativeException {
		try {
			log.info("============>updateBatch CON ESTADO " + estado + " idFunda " + idFunda);
			CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
			CriteriaUpdate<TbMiJoya> update = builder.createCriteriaUpdate(TbMiJoya.class);
			Root<TbMiJoya> root = update.from(TbMiJoya.class);
			update.set(root.<EstadoJoyaEnum>get("estado"), estado);
			update.where(builder.equal(root.<TbMiFunda>get("tbMiFunda").<Long>get("id"), idFunda));
			return this.getEntityManager().createQuery(update).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error updateBatch: " + e.getMessage());
		}
	}

	/*
	 * Metodo que retorna una clase personalizada con los datos para llenar el
	 * formulario Retazar y Trasladar
	 * 
	 * @param codigoJoya
	 * 
	 * @return
	 * 
	 * @throws RelativeException
	 * 
	 * @author kevin chamorro
	 */
	@Override
	public List<RetazarJoya> findByCodigoJoya(String codigoJoya) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<RetazarJoya> query = cb.createQuery(RetazarJoya.class);
			// ~~> FROM
			Root<TbMiJoya> poll = query.from(TbMiJoya.class);
			Join<TbMiJoya, TbMiTipoJoya> joinJoyaTipoJoya = poll.join("tbMiTipoJoya");
			Join<TbMiJoya, TbMiCompraOro> joinJoyaTipoOro = poll.join("tbMiCompraOro");
			Join<TbMiJoya, TbMiFunda> joinJoyaFunda = poll.join("tbMiFunda");
			Join<TbMiJoya, TbMiContrato> joinJoyaContrato = poll.join("tbMiContrato");
			Join<TbMiJoya, TbMiInventario> joinJoyaInventario = poll.join("tbMiInventarios");
			Join<TbMiInventario, TbMiBodega> joinInventarioBodega = joinJoyaInventario.join("tbMiBodega");
			Join<TbMiContrato, TbMiAgencia> joinContratoAgencia = joinJoyaContrato.join("tbMiAgencia");
			// ~~> WHERE
			Predicate where = cb.equal(poll.get("codigoJoya"), codigoJoya);
			query.where(where);
			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("codigoJoya"), joinJoyaContrato.get("fechaCreacion"),
					joinJoyaTipoJoya.get("detalle"), poll.get("fechaActualizacion"), joinJoyaTipoOro.get("quilate"),
					joinJoyaTipoOro.get("id"), joinJoyaFunda.get("codigo"), poll.get("pesoBruto"),
					poll.get("descuento"), poll.get("peso"), joinContratoAgencia.get("codigo"),
					joinInventarioBodega.get("descripcion"), joinJoyaContrato.get("proceso"),
					joinJoyaContrato.get("codigo"), joinJoyaFunda.get("id"), poll.get("valor"),
					joinJoyaContrato.get("tipoCompra"));
			// ~~> EJECUTAR CONSULTA
			TypedQuery<RetazarJoya> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getResultList();
			// ~~> FIN
		} catch (Exception e) {
			throw new RelativeException("Error: " + e.getMessage());
		}
	}

	/**
	 * Metodo que carga la informacion por codigo de contrato paginado
	 * 
	 * @param codigo
	 * @param identificacion
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param direction
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiJoya> findByCodigoContratoPaged(PaginatedWrapper pw, String codigo) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new JoyasByContratoSpec(codigo));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new JoyasByContratoSpec(codigo), pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new JoyasByContratoSpec(codigo));
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"NO EXISTE JOYAS PARA EL CODIGO DE CONTRATO ENVIADO " + codigo + " " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna la cantidad joyas por codigo contrato
	 * 
	 * @param codigo
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@Override
	public Long countByCodigoContrato(String codigo) throws RelativeException {
		try {
			return this.countBySpecification(new JoyasByContratoSpec(codigo));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"NO EXISTE JOYAS PARA EL CODIGO DE CONTRATO ENVIADO " + codigo + " " + e.getMessage());
		}
	}

	/**
	 * Busca las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional. Ordena por fecha
	 * creacion movimiento por prioridad
	 * 
	 * @author kevin chamorro
	 */
	@Override
	public List<JoyaVentaVitrina> findByEstadoMvCodigoTipo(PaginatedWrapper pw, EstadoJoyaEnum estadoJoya,
			EstadoJoyaEnum estadoMvIn, String codigoJoya, Long idTipoJoya) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<JoyaVentaVitrina> query = cb.createQuery(JoyaVentaVitrina.class);
			// ~~> FROM
			Root<TbMiJoya> poll = query.from(TbMiJoya.class);
			Join<TbMiJoya, TbMiTipoJoya> joinJoyaTipoJoya = poll.join("tbMiTipoJoya");
			Join<TbMiJoya, TbMiCompraOro> joinJoyaTipoOro = poll.join("tbMiCompraOro");
			Join<TbMiJoya, TbMiContrato> joinJoyaContrato = poll.join("tbMiContrato");
			Join<TbMiJoya, TbMiInventario> joinJoyaInventario = poll.join("tbMiInventarios");
			Join<TbMiInventario, TbMiMovInventario> joinJoyaMovInventario = joinJoyaInventario
					.join("tbMiMovInventarios");
			Join<TbMiMovInventario, TbMiBodega> joinMviBodega = joinJoyaMovInventario.join("tbMiBodega");
			Join<TbMiBodega, TbMiAgencia> joinBodegaAgencia = joinMviBodega.join("tbMiAgencia");
			// ~~> WHERE
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(poll.get("estado"), estadoJoya));
			predicates.add(cb.equal(joinJoyaMovInventario.get("estado"), estadoMvIn));
			if (StringUtils.isNotBlank(codigoJoya)) {
				predicates.add(cb.like(poll.get("codigoJoya"), "%" + codigoJoya.trim() + "%"));
			}
			if (idTipoJoya != null) {
				predicates.add(cb.equal(joinJoyaTipoJoya.get("id"), idTipoJoya));
			}
			query.where(cb.and(predicates.toArray(new Predicate[] {})));
			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("codigoJoya"), joinJoyaTipoJoya.get("id"),
					joinJoyaTipoJoya.get("detalle"), poll.get("comentario"), joinJoyaTipoOro.get("id"),
					joinJoyaTipoOro.get("quilate"), joinJoyaMovInventario.get("fechaCreacion"), poll.get("pesoBruto"),
					joinJoyaInventario.get("id"), joinJoyaContrato.get("id"), joinJoyaContrato.get("fechaCreacion"),
					joinMviBodega.get("id"), joinMviBodega.get("descripcion"), joinBodegaAgencia.get("id"),
					joinBodegaAgencia.get("nombreAgencia"), joinJoyaContrato.get("proceso"),
					joinJoyaContrato.get("codigo"), poll.get("valor"), joinJoyaTipoOro.get("precioOroVenta"))
					.distinct(true);
			// ~~> ORDER BY
			if (pw.getSortDirections().equals("asc")) {
				query.orderBy(cb.desc(joinJoyaMovInventario.get("fechaCreacion")),
						cb.asc(poll.get(pw.getSortFields())));
			} else {
				query.orderBy(cb.desc(joinJoyaMovInventario.get("fechaCreacion")),
						cb.desc(poll.get(pw.getSortFields())));
			}
			// ~~> EJECUTAR CONSULTA
			TypedQuery<JoyaVentaVitrina> createQuery = this.getEntityManager().createQuery(query);
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize()).getResultList();
			} else {
				return createQuery.getResultList();
			}
			// ~~> FIN
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"No se pudo listar joyas de la vitrina. EstadoJoya:" + estadoJoya + ". EstadoMovimientoInventario: "
							+ estadoMvIn + ". CodigoJoya: " + codigoJoya + ". TipoJoya: " + idTipoJoya + ". "
							+ e.getMessage());
		}
	}

	/**
	 * Cuenta las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional.
	 * 
	 * @author kevin chamorro
	 */
	@Override
	public Integer countByEstadoMvCodigoTipo(PaginatedWrapper pw, EstadoJoyaEnum estadoJoya, EstadoJoyaEnum estadoMvIn,
			String codigoJoya, Long idTipoJoya) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<TbMiJoya> poll = query.from(TbMiJoya.class);
			Join<TbMiJoya, TbMiTipoJoya> joinJoyaTipoJoya = poll.join("tbMiTipoJoya");
			Join<TbMiJoya, TbMiInventario> joinJoyaInventario = poll.join("tbMiInventarios");
			Join<TbMiInventario, TbMiMovInventario> joinJoyaMovInventario = joinJoyaInventario
					.join("tbMiMovInventarios");
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(poll.get("estado"), estadoJoya));
			predicates.add(cb.equal(joinJoyaMovInventario.get("estado"), estadoMvIn));
			if (StringUtils.isNotBlank(codigoJoya)) {
				predicates.add(cb.like(poll.get("codigoJoya"), "%" + codigoJoya.trim() + "%"));
			}
			if (idTipoJoya != null) {
				predicates.add(cb.equal(joinJoyaTipoJoya.get("id"), idTipoJoya));
			}
			query.where(cb.and(predicates.toArray(new Predicate[] {})));
			// ~~> SELECT
			query.select(cb.count(poll.get("id"))).distinct(true);
			// ~~> EJECUTAR CONSULTA
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
			// ~~> FIN
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"No se pudo contar joyas de la vitrina. EstadoJoya:" + estadoJoya + ". EstadoMovimientoInventario: "
							+ estadoMvIn + ". CodigoJoya: " + codigoJoya + ". TipoJoya: " + idTipoJoya + ". "
							+ e.getMessage());
		}
	}

	/**
	 * Busca todas las joyas de la funda por idFunda
	 * 
	 * @param pw
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@Override
	public List<TbMiJoya> findByIdFunda(PaginatedWrapper pw, Long idFunda) throws RelativeException {
		try {

			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.findAllBySpecificationPaged(new JoyasByIdFundaSpec(idFunda), pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.findAllBySpecification(new JoyasByIdFundaSpec(idFunda));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al consultar las joyas de la funda " + idFunda + " " + e.getMessage());
		}
	}

	/**
	 * Cuenta todas las joyas de la funda por idFunda
	 * 
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@Override
	public Long countByIdFunda(Long idFunda) throws RelativeException {
		try {
			return this.countBySpecification(new JoyasByIdFundaSpec(idFunda));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al contar las joyas de la funda ");
		}
	}

	public List<TbMiJoya> findByCodigoJoyaEstadosFechas(PaginatedWrapper pw, String codigoJoya,
			List<EstadoJoyaEnum> estadosJoya, Date fechaDesde, Date fechaHasta) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(
						new JoyasByEstadosCodigoFechasSpec(codigoJoya, estadosJoya, fechaDesde, fechaHasta));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(
							new JoyasByEstadosCodigoFechasSpec(codigoJoya, estadosJoya, fechaDesde, fechaHasta),
							pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(
							new JoyasByEstadosCodigoFechasSpec(codigoJoya, estadosJoya, fechaDesde, fechaHasta));
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"NO EXISTE JOYAS PARA EL CODIGO DE CONTRATO ESTADOS O RANGO DE FECHAS " + e.getMessage());
		}
	}

	public Long countByCodigoJoyaEstadosFechas(String codigoJoya, List<EstadoJoyaEnum> estadosJoya, Date fechaDesde,
			Date fechaHasta) throws RelativeException {
		try {
			return this.countBySpecification(
					new JoyasByEstadosCodigoFechasSpec(codigoJoya, estadosJoya, fechaDesde, fechaHasta));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"NO EXISTE JOYAS PARA EL CODIGO DE CONTRATO ESTADOS O RANGO DE FECHAS  " + e.getMessage());
		}
	}

	@Override
	public List<TbMiJoya> findJoyaByParams(int startRecord, Integer pageSize, String sortFields, String sortDirections,
			List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya) throws RelativeException {
		try {
			List<TbMiJoya> tmp = this.findAllBySpecificationPaged(new JoyaByParamsSpec(estados, codigoJoya, idTipoJoya),
					startRecord, pageSize, sortFields, sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYAS");
		}
		return null;
	}

	@Override
	public List<TbMiJoya> findJoyaByParams(List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya)
			throws RelativeException {
		try {
			List<TbMiJoya> tmp = this.findAllBySpecification(new JoyaByParamsSpec(estados, codigoJoya, idTipoJoya));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYAS");
		}
		return null;
	}

	@Override
	public Long countJoyaByParams(List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya)
			throws RelativeException {
		try {
			return this.countBySpecification(new JoyaByParamsSpec(estados, codigoJoya, idTipoJoya));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYAS");
		}
	}

	@Override
	public List<TbMiJoya> findByEstadoAndComprador(EstadoJoyaEnum estado, String identificacion)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbMiJoya> findByEstadoAndComprador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, EstadoJoyaEnum pendienteHabilitante, String identificacion, String codigoJoya)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(
					new JoyaByEstadoAndCompradorSpec(codigoJoya, identificacion, pendienteHabilitante), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYA PENDIENTE DE HABILITANTES");
		}

	}

	@Override
	public List<TbMiJoya> findByEstadoAndComprador(EstadoJoyaEnum pendienteHabilitante, String identificacion,
			String codigoJoya) throws RelativeException {
		try {
			return findAllBySpecification(
					new JoyaByEstadoAndCompradorSpec(codigoJoya, identificacion, pendienteHabilitante));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYA PENDIENTE DE HABILITANTES");
		}

	}

	@Override
	public Long countByEstadoAndComprador(EstadoJoyaEnum pendienteHabilitante, String identificacion, String codigoJoya)
			throws RelativeException {

		try {
			return countBySpecification(
					new JoyaByEstadoAndCompradorSpec(codigoJoya, identificacion, pendienteHabilitante));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR JOYA PENDIENTE DE HABILITANTES");
		}
	}

}
