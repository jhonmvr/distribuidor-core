package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiCuenta;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.repository.MovimientoCajaRepository;
import com.relative.midas.repository.spec.MovimientoCajaByMultiSpec;
import com.relative.midas.repository.spec.MovimientoCajaByParamsSpec;
import com.relative.midas.repository.spec.MovimientoCajaByVentaLoteSpec;
import com.relative.midas.repository.spec.MovimientoCajabyContratoandProcesoSpec;
import com.relative.midas.repository.spec.MovimientoCajabyProcesoSpec;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.MovimientoCajaWrapper;
import com.relative.midas.wrapper.MovimientosDetalleCierreCajaWrapper;


@Stateless(mappedName = "movimientoCajaRepository")
public class MovimientoCajaRepositoryImp extends GeneralRepositoryImp<Long, TbMiMovimientoCaja> implements MovimientoCajaRepository{
	
	
	public MovimientoCajaRepositoryImp() {

	}

	@Override
	public List<TbMiMovimientoCaja> findByParam(String fechaDesde, String fechaHasta, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado) throws RelativeException {

		List<TbMiMovimientoCaja> tmp;
		
		try {
			tmp = this.findAllBySpecification(
					new MovimientoCajaByMultiSpec(fechaDesde, fechaHasta , codigoContrato, identificacionCliente, estado));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMi" + e);
		}

		return null;

	}

	@Override
	public List<TbMiMovimientoCaja> findByParamPaged(String fechaCreacion, String fechaActualizacion, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado, int inicio, int tamanio) throws RelativeException {

		List<TbMiMovimientoCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new MovimientoCajaByMultiSpec(fechaCreacion, fechaActualizacion, codigoContrato, identificacionCliente, estado), inicio,
					tamanio);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiLote");
		}

		return null;

	}

	@Override
	public List<TbMiMovimientoCaja> findByParamPaged(String fechaCreacion, String fechaActualizacion, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado, int inicio, int tamanio, String sortField,
			String sortDirection) throws RelativeException {

		List<TbMiMovimientoCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new MovimientoCajaByMultiSpec(fechaCreacion, fechaActualizacion, codigoContrato, identificacionCliente, estado), inicio,
					tamanio, sortField, sortDirection);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiLote");
		}

		return null;

	}

	@Override
	public Long countFindMovimientoCajaByParam(String fechaCreacion, String fechaActualizacion, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado)
			throws RelativeException {

		try {
			Long tmp = this.countBySpecification(
					new MovimientoCajaByMultiSpec(fechaCreacion, fechaActualizacion, codigoContrato, identificacionCliente, estado));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiLote");
		}
		return null;
	}
	
	public List<TbMiMovimientoCaja> findByParams(PaginatedWrapper pw, EstadoMidasEnum estado, Date fechaDesde, 
			Date fechaHasta,String codigoContrato,String identificacionCliente,FormaPagoEnum formaPago, Long idAgencia)
					throws RelativeException{
		try {
			if (pw == null) {
				return this.findAllBySpecification(new MovimientoCajaByParamsSpec(estado,fechaDesde,
						fechaHasta,codigoContrato,identificacionCliente,formaPago,idAgencia));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return findAllBySpecificationPaged(new MovimientoCajaByParamsSpec(estado,fechaDesde,
							fechaHasta,codigoContrato,identificacionCliente,formaPago,idAgencia), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new MovimientoCajaByParamsSpec(estado,fechaDesde,
							fechaHasta,codigoContrato,identificacionCliente,formaPago,idAgencia));
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "No se pudo buscar movimientos de caja " + e.getMessage());
		}
	}
	
	public Long countByParams(EstadoMidasEnum estado, Date fechaDesde, 
			Date fechaHasta,String codigoContrato,String identificacionCliente,FormaPagoEnum formaPago, Long idAgencia) 
					throws RelativeException{
		try {
			return this.countBySpecification(new MovimientoCajaByParamsSpec(estado, fechaDesde,
					fechaHasta, codigoContrato, identificacionCliente, formaPago, idAgencia));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "No se pudo contar movimientos de caja " + e.getMessage());
		}
	}

	@Override
	public List<TbMiMovimientoCaja> findByContrato(long idContrato, ProcesoEnum proceso) throws RelativeException {
   List<TbMiMovimientoCaja> tmp;
		
		try {
			tmp = this.findAllBySpecification(new MovimientoCajabyContratoandProcesoSpec(idContrato,proceso));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMi" + e);
		}
		return null;
	}

	@Override
	public List<TbMiMovimientoCaja> findByProceso(ProcesoEnum proceso) throws RelativeException {
		List<TbMiMovimientoCaja> tmp;
			
		try {
			tmp = this.findAllBySpecification(new MovimientoCajabyProcesoSpec(proceso));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
				}
			} catch (Exception e) {
				e.getStackTrace();
				
				throw new RelativeException ("Error al buscar en TbMi" + e);
			}
			return null;
	}

	@Override
	public List<TbMiMovimientoCaja> findByVentaLote(Long idVentaLote) throws RelativeException {
		try {
			List<TbMiMovimientoCaja> tmp;
			tmp = this.findAllBySpecification(new MovimientoCajaByVentaLoteSpec(idVentaLote));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
				}
			} catch (Exception e) {
				e.getStackTrace();
				
				throw new RelativeException ("Error al buscar en TbMi" + e);
			}
			return null;
	}

	@Override
	public List<MovimientosDetalleCierreCajaWrapper> filWrapperByCorteCaja(Long idCorteCaja) throws RelativeException {
			//List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
		try {
			StringBuilder strQry = new StringBuilder(
					
					"select j.proceso,j.ingreso as ingreso,j.egreso as egreso from TbMiMovimientoCaja j "
					//+ " inner join TbMiCuenta c on c.id = j.tbMiCuenta.id "
					+ " where j.id > "
					+ " (select id from TbMiMovimientoCaja where proceso = 'APERTURA_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					+ " and j.id < (select id from TbMiMovimientoCaja where proceso = 'CIERRE_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					+ " and j.tbMiCaja.id = (select tbMiCaja.id from TbMiCorteCaja where id=:id_corte_caja ) "
					+ " and j.formaPago = 'EFECTIVO' order by egreso ");
			
			
			Query q = this.getEntityManager().createQuery(strQry.toString());
		
			q.setParameter("id_corte_caja", idCorteCaja);
			return MidasOroUtil.getResultList(q.getResultList(), MovimientosDetalleCierreCajaWrapper.class) ;
		}catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL RESERVAR FUNDA " + e);
		}
		
	
	}
	
	/*
	 	private Date fechaActualizacion;
	private String usuarioCreacion;
	private String codigoAgencia;
	private String nombreCuenta;
	private FormaPagoEnum formaPago;
	private String tipo;
	private EstadoMidasEnum estado;
	private ProcesoEnum proceso;
	private String nombreBanco;
	private BigDecimal ingreso;
	private BigDecimal egreso;
	private String comentario;
	 */
	
	public List<MovimientoCajaWrapper> findByUsuarioAndCaja(PaginatedWrapper pw,
			  String usuario, String idCorteCaja) throws RelativeException {
		try {
			StringBuilder strQry = new StringBuilder(
					
					"select j.fechaActualizacion, j.usuarioCreacion, j.tbMiCaja.tbMiAgencia.codigo,"
					+ "  c.nombre, j.formaPago, j.tipo, j.estado, j.proceso, "
					+ " j.ingreso, j.egreso  from TbMiMovimientoCaja j left join TbMiCuenta c on c.id = j.tbMiCuenta.id "
					//+ " left join TbMiBanco b on b.id = j.tbMiBanco.id"
					//+ " inner join TbMiCuenta c on c.id = j.tbMiCuenta.id "
					+ " where j.id > "
					+ " (select id from TbMiMovimientoCaja where proceso = 'APERTURA_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					//+ " and j.id < (select id from TbMiMovimientoCaja where proceso = 'CIERRE_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					+ " and j.tbMiCaja.id = (select tbMiCaja.id from TbMiCorteCaja where id=:id_corte_caja ) "
					+ " and j.formaPago = 'EFECTIVO'   order by egreso ");
			
			
			Query q = this.getEntityManager().createQuery(strQry.toString());
		
			q.setParameter("id_corte_caja", Long.valueOf(idCorteCaja));
			return MidasOroUtil.getResultList(q.getResultList(), MovimientoCajaWrapper.class) ;
		}catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "al buscar los movimientos de caja " + e);
		}
	}
	
	
	public Long countByUsuarioAndCaja( String usuario, String idCorteCaja) throws
	  RelativeException {
		try {
			StringBuilder strQry = new StringBuilder(
					
					"select j.id  from TbMiMovimientoCaja j "
					//+ " inner join TbMiCuenta c on c.id = j.tbMiCuenta.id "
					+ " where j.id > "
					+ " (select id from TbMiMovimientoCaja where proceso = 'APERTURA_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					//+ " and j.id < (select id from TbMiMovimientoCaja where proceso = 'CIERRE_CAJA' and tbMiCorteCaja.id =:id_corte_caja ) "
					+ " and j.tbMiCaja.id = (select tbMiCaja.id from TbMiCorteCaja where id=:id_corte_caja ) "
					+ " and j.formaPago = 'EFECTIVO' order by egreso ");
			
			
			Query q = this.getEntityManager().createQuery(strQry.toString());
		
			q.setParameter("id_corte_caja", Long.valueOf(idCorteCaja));
			if(q.getResultList().size()>0) {
				Long.valueOf(String.valueOf(q.getResultList().size()));
			}
			return  Long.valueOf("0");
		}catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL RESERVAR FUNDA " + e);
		}
	}
	
	/*
	 * public List<MovimientoCajaWrapper> findByUsuarioAndCaja(PaginatedWrapper pw,
	 * String usuario, String idCorteCaja) throws RelativeException { try { // ~~>
	 * QUERY CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	 * CriteriaQuery<MovimientoCajaWrapper> query =
	 * cb.createQuery(MovimientoCajaWrapper.class); // ~~> FROM
	 * Root<TbMiMovimientoCaja> poll = query.from(TbMiMovimientoCaja.class);
	 * Join<TbMiMovimientoCaja, TbMiCuenta> jmovCajaCuenta = poll.join("tbMiCuenta",
	 * JoinType.INNER); Join<TbMiMovimientoCaja,TbMiCorteCaja> joinCorteCaja =
	 * poll.join("tbMiCorteCaja") Join<TbMiMovimientoCaja, TbMiCaja> jmovCajaCaja =
	 * poll.join("tbMiCaja", JoinType.INNER); Join<TbMiMovimientoCaja, TbMiBanco>
	 * jmovCajaBanco = poll.join("tbMiBanco", JoinType.LEFT);
	 * //Join<TbMiMovimientoCaja,TbMiCorteCaja > jmovCorteCaja =
	 * poll.join("tbMiCorteCaja", JoinType.INNER); Join<TbMiCaja, TbMiAgencia>
	 * jcajaAgencia = jmovCajaCaja.join("tbMiAgencia", JoinType.INNER);
	 * 
	 * // ~~> WHERE List<Predicate> where = new ArrayList<>();
	 * if(StringUtils.isNotBlank(usuario)) {
	 * where.add(cb.equal(poll.<String>get("usuarioCreacion"),usuario)); }
	 * if(StringUtils.isNotBlank(idCorteCaja)) {
	 * where.add(cb.equal(jmovCajaCaja.<Long>get("id"), Long.valueOf( idCorteCaja )
	 * )); } where.add(cb.equal(poll.<FormaPagoEnum>get("formaPago"),FormaPagoEnum.
	 * EFECTIVO)); where.add(cb.equal(poll.<Date>get("fechaCreacion"), new Date(
	 * System.currentTimeMillis() ) ));
	 * where.add(cb.isNull(poll.<TbMiCorteCaja>get("tbMiCorteCaja").<Long>get("id")
	 * )); query.where(cb.and(where.toArray(new Predicate[]{}))); // ~~> SELECT
	 * 
	 * query.multiselect(poll.get("fechaActualizacion"),
	 * poll.get("usuarioCreacion"), jcajaAgencia.get("nombreAgencia"),
	 * jmovCajaCuenta.get("nombre"), poll.get("formaPago"),poll.get("tipo"),
	 * poll.get("estado"), poll.get("proceso"), jmovCajaBanco.get("nombre"),
	 * poll.get("ingreso"), poll.get("egreso"),poll.get("comentario")); // ~~> ORDER
	 * BY if (pw.getSortDirections().equals("asc")) {
	 * query.orderBy(cb.asc(poll.get(pw.getSortFields()))); } else {
	 * query.orderBy(cb.desc(poll.get(pw.getSortFields()))); } // ~~> EJECUTAR
	 * CONSULTA TypedQuery<MovimientoCajaWrapper> createQuery =
	 * this.getEntityManager().createQuery(query); if (pw != null &&
	 * pw.getIsPaginated() != null &&
	 * pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) { return
	 * createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize(
	 * )).getResultList(); } else { return createQuery.getResultList(); } } catch
	 * (Exception e) { e.printStackTrace(); throw new
	 * RelativeException(Constantes.ERROR_CODE_READ,
	 * "Ocurrio un error al leer documentos habilitantes"); } }
	 * 
	 * public Long countByUsuarioAndCaja( String usuario, String idCaja) throws
	 * RelativeException { try { // ~~> QUERY CriteriaBuilder cb =
	 * getEntityManager().getCriteriaBuilder();
	 * //CriteriaQuery<MovimientoCajaWrapper> query =
	 * cb.createQuery(MovimientoCajaWrapper.class); CriteriaQuery<Long> query =
	 * cb.createQuery(Long.class);
	 * 
	 * // ~~> FROM Root<TbMiMovimientoCaja> poll =
	 * query.from(TbMiMovimientoCaja.class); Join<TbMiMovimientoCaja, TbMiCaja>
	 * jmovCajaCaja = poll.join("tbMiCaja", JoinType.INNER);
	 * 
	 * // ~~> WHERE List<Predicate> where = new ArrayList<>();
	 * if(StringUtils.isNotBlank(usuario)) {
	 * where.add(cb.equal(poll.<String>get("usuarioCreacion"),usuario)); }
	 * if(StringUtils.isNotBlank(idCaja)) {
	 * where.add(cb.equal(jmovCajaCaja.<Long>get("id"), Long.valueOf( idCaja ) )); }
	 * where.add(cb.equal(poll.<FormaPagoEnum>get("formaPago"),FormaPagoEnum.
	 * EFECTIVO)); where.add(cb.equal(poll.<Date>get("fechaCreacion"), new Date(
	 * System.currentTimeMillis() ) ));
	 * where.add(cb.isNull(poll.<TbMiCorteCaja>get("tbMiCorteCaja").<Long>get("id")
	 * ));
	 * 
	 * query.where(cb.and(where.toArray(new Predicate[]{}))); // ~~> SELECT
	 * 
	 * query.select(cb.count( poll ));
	 * 
	 * // ~~> EJECUTAR CONSULTA TypedQuery<Long> createQuery =
	 * this.getEntityManager().createQuery(query); return
	 * createQuery.getSingleResult();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); throw new
	 * RelativeException(Constantes.ERROR_CODE_READ,
	 * "Ocurrio un error al leer documentos habilitantes"); } }
	 */
}
	

