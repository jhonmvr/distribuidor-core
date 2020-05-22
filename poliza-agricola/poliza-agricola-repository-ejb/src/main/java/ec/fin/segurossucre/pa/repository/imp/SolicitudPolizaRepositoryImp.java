package ec.com.def.pa.repository.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import org.apache.commons.lang3.StringUtils;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.TbPaSolicitudPoliza;
import ec.com.def.pa.repository.SolicitudPolizaRepository;
import ec.com.def.pa.wrapper.CatalogosGeneralWrapper;
import ec.com.def.pa.wrapper.ConsultaSolicitudPolizaWrapper;

@Stateless(mappedName = "solicitudPolizaRepository")
public class SolicitudPolizaRepositoryImp extends GeneralRepositoryImp<Long, TbPaSolicitudPoliza>
		implements SolicitudPolizaRepository {
	@Inject
	Logger log;

	@Override
	public BigDecimal generateCodigo(String anio) throws DefException {
		try {
			Query query = this.getEntityManager()
					.createNativeQuery("SELECT nvl(max(regexp_substr(CODIGO,'[^-]+',1,4 )),0)+1 FROM TB_PA_SOLICITUD_POLIZA WHERE regexp_substr(CODIGO,'[^-]+',1,3 ) = '"+
			anio+"'");
			return (BigDecimal) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "GENERAR SECUENCIA " + ex.getMessage());
		}
	}

	@Override
	public BigDecimal generateNumeroTramite(String seqCanal) throws DefException {
		try {
			Query query = this.getEntityManager()
					.createNativeQuery("SELECT nvl(max(regexp_substr(NUMERO_TRAMITE, '[^-]+',1,2 )),0)+1 FROM TB_PA_SOLICITUD_POLIZA WHERE ID_CANAL='"
							+seqCanal+"'");
			return (BigDecimal) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "GENERAR SECUENCIA " + ex.getMessage());
		}
	}

	@Override
	public List<ConsultaSolicitudPolizaWrapper> findByParams(PaginatedWrapper pw, String numeroSolicitud,
			String numeroTramite, Date desde, Date hasta, String canal) throws DefException {
		// TODO Auto-generated method stub
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ConsultaSolicitudPolizaWrapper> query = cb.createQuery(ConsultaSolicitudPolizaWrapper.class);
			// ~~> FROM
			Root<TbPaSolicitudPoliza> poll = query.from(TbPaSolicitudPoliza.class);
			Join<TbPaSolicitudPoliza, Ramocanal> joinCanal = poll.join("ramocanal");

			log.info("===================> getParametros canal" + desde);
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			if (numeroSolicitud != null) {
				where.add(cb.like(poll.get("codigo"), numeroSolicitud));
			}
		
			if (desde != null) {
		
				where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), desde));
			}
			
			if (hasta != null) {
		
				where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), hasta));
			}
			if (StringUtils.isNotBlank(canal)) {
				where.add(cb.equal(poll.get("ramocanal").get("id").get("canalid"), canal));
			}
			if (StringUtils.isNotBlank(numeroTramite)) {
				where.add(cb.like(poll.get("numeroTramite"), numeroTramite));
			}
			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"),poll.get("codigo"), poll.get("numeroTramite"),poll.get("tbSaAsegurado").get("identificacion"),
					poll.get("tbSaAsegurado").get("nombres"),poll.get("tbSaAsegurado").get("apellido"), poll.get("fechaCreacion"),
					joinCanal.get("canalnombre"), poll.get("estado"),
					poll.get("tbPaCaracteristicaCultivo").get("costoHectarea"),
					poll.get("tbPaCaracteristicaCultivo").get("montoAsegurado"),
					poll.get("observacion"),poll.get("primaTotal"));

			// ~~> ORDER BY
			if (pw != null) {
				if(pw.getSortDirections().equals("asc")) {
					query.orderBy(cb.asc(poll.get(pw.getSortFields())));
				} else {
					query.orderBy(cb.desc(poll.get(pw.getSortFields())));
				}
			}
			// ~~> EJECUTAR CONSULTA

			TypedQuery<ConsultaSolicitudPolizaWrapper> createQuery = this.getEntityManager().createQuery(query);
			if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize()).getResultList();
			} else {
				return createQuery.getResultList();
			}
			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	public Integer countBySolicitudesPoliza(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde,
			Date hasta, String canal) throws DefException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<TbPaSolicitudPoliza> poll = query.from(TbPaSolicitudPoliza.class);
			List<Predicate> where = new ArrayList<>();

			if (desde != null) {
			
				where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), desde));
			}
			
			if (hasta != null) {
		
				where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), hasta));
			}
			if (StringUtils.isNotBlank(canal)) {
				where.add(cb.equal(poll.get("ramocanal").get("id").get("canalid"), canal));
			}
			if (StringUtils.isNotBlank(numeroTramite)) {
				where.add(cb.like(poll.get("numeroTramite"), numeroTramite));
			}
			query.where(cb.and(where.toArray(new Predicate[] {})));
			query.select(cb.count(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			throw new DefException("" + e);
		}
	}
	
	
	
	public List<ConsultaSolicitudPolizaWrapper> setWrapperSolicitudPoliza( String numeroSolicitud,
			String numeroTramite, Date desde, Date hasta, String canal) throws DefException {
		log.info("===================> ingresa a set el wrapper de solicitud" + numeroSolicitud);
		List<ConsultaSolicitudPolizaWrapper> list = new ArrayList<>();
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ConsultaSolicitudPolizaWrapper> query = cb.createQuery(ConsultaSolicitudPolizaWrapper.class);
			// ~~> FROM
			Root<TbPaSolicitudPoliza> poll = query.from(TbPaSolicitudPoliza.class);
			Join<TbPaSolicitudPoliza, Ramocanal> joinCanal = poll.join("ramocanal");

	
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			if (numeroSolicitud != null) {
				where.add(cb.like(poll.get("codigo"), numeroSolicitud));
			}
		
			if (desde != null) {
		
				where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), desde));
			}
			
			if (hasta != null) {
		
				where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), hasta));
			}
			if (StringUtils.isNotBlank(canal)) {
				where.add(cb.equal(poll.get("ramocanal").get("id").get("canalid"), canal));
			}
			if (StringUtils.isNotBlank(numeroTramite)) {
				where.add(cb.like(poll.get("numeroTramite"), numeroTramite));
				log.info("===================> numero tramite " + numeroTramite);
			}
			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"),poll.get("codigo"), poll.get("numeroTramite"),poll.get("tbSaAsegurado").get("identificacion"),
					poll.get("tbSaAsegurado").get("nombres"),poll.get("tbSaAsegurado").get("apellido"), poll.get("fechaCreacion"),
					joinCanal.get("canalnombre"), poll.get("estado"),
					poll.get("tbPaCaracteristicaCultivo").get("costoHectarea"),
					poll.get("tbPaCaracteristicaCultivo").get("montoAsegurado"),
					poll.get("observacion"),poll.get("primaTotal")
					);
			
			// ~~> EJECUTAR CONSULTA

			    TypedQuery<ConsultaSolicitudPolizaWrapper> createQuery = this.getEntityManager().createQuery(query);
			    log.info("===================> data ejecutado " + createQuery);
		
			    log.info("===================> data result " + createQuery.getResultList());
				return createQuery.getResultList();

			
			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public void upDate() throws DefException {
		try {
			StringBuilder str =new StringBuilder();
			str.append("UPDATE TB_PA_SOLICITUD_POLIZA poliza SET poliza.ESTADO = (SELECT un01.UN01_ESTCALIFINTERNA FROM un01 un01 WHERE un01.UN01_CODIGOTRAMITE IN \r\n" + 
					"(SELECT DISTINCT NUMERO_TRAMITE FROM TB_PA_SOLICITUD_POLIZA ) AND poliza.NUMERO_TRAMITE = un01.UN01_CODIGOTRAMITE),\r\n" + 
					"poliza.OBSERVACION =(SELECT un01.UN01_OBSCALIFINTERNA FROM un01 un01 WHERE un01.UN01_CODIGOTRAMITE IN \r\n" + 
					"(SELECT DISTINCT NUMERO_TRAMITE FROM TB_PA_SOLICITUD_POLIZA ) AND poliza.NUMERO_TRAMITE = un01.UN01_CODIGOTRAMITE),\r\n" + 
					"poliza.PRIMA_TOTAL = (SELECT un01.UN01_UN02_PRIMBRUTA FROM un01 un01 WHERE un01.UN01_CODIGOTRAMITE IN \r\n" + 
					"(SELECT DISTINCT NUMERO_TRAMITE FROM TB_PA_SOLICITUD_POLIZA ) AND poliza.NUMERO_TRAMITE = un01.UN01_CODIGOTRAMITE)\r\n" + 
					"WHERE poliza.ESTADO ='PE'");
			Query query = this.getEntityManager().createNativeQuery(str.toString());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"AL ACTUALIZAR REGISTROS DESDE EL SISTEMA AGRICOLA");
		}

		
	}
	
}