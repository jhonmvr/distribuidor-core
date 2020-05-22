package com.relative.midas.repository.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
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
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiTipoDocumento;
import com.relative.midas.repository.DocumentoHabilitanteRepository;
import com.relative.midas.repository.spec.DocumentoByTipoDocumentoAndContratoSpec;
import com.relative.midas.repository.spec.DocumentoByTipoDocumentoAndJoyaAndAbonoSpec;
import com.relative.midas.repository.spec.DocumentoHabilitanteByParamsSpec;
import com.relative.midas.wrapper.HabilitanteWrapper;

/**
 * Session Bean implementation class documentoHabilitanteRepository
 */
@Stateless(mappedName = "documentoHabilitanteRepository")
public class DocumentoHabilitanteRepositoryImp extends GeneralRepositoryImp<Long, TbMiDocumentoHabilitante> implements DocumentoHabilitanteRepository  {
	  /**
     * Default constructor. 
     */
    public DocumentoHabilitanteRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
  

	//@Override
	public TbMiDocumentoHabilitante findByTipoDocumentoAndContrato( String codigoContrato, Long idTipoDocumento) {
		List<TbMiDocumentoHabilitante> tmp= this.findAllBySpecification((new DocumentoByTipoDocumentoAndContratoSpec(idTipoDocumento,codigoContrato)));
		if( tmp != null && !tmp.isEmpty() ) {
			return tmp.get(0);
		} else {
			return null;
		}
	}
	
	public TbMiDocumentoHabilitante findByTipoDocumentoAndJoyaAndAbono( Long idJoya, Long idAbono,Long idVentaLote,Long idCorteCaja, Long idTipoDocumento) {
		List<TbMiDocumentoHabilitante> tmp= this.findAllBySpecification((new DocumentoByTipoDocumentoAndJoyaAndAbonoSpec(idTipoDocumento,idJoya,idAbono,idVentaLote,idCorteCaja)));
		if( tmp != null && !tmp.isEmpty() ) {
			return tmp.get(0);
		} else {
			return null;
		}
	}
	
	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndJoyaAndAbonos( Long idJoya, Long idAbono, Long idTipoDocumento)  throws RelativeException {
		return this.findAllBySpecification((new DocumentoByTipoDocumentoAndJoyaAndAbonoSpec(idTipoDocumento,idJoya,idAbono,null,null)));
		
	}
	
	public List<TbMiDocumentoHabilitante> findByContrato( String codigoContrato) throws RelativeException {
		return this.findAllBySpecification((new DocumentoByTipoDocumentoAndContratoSpec(null,codigoContrato)));
	}

	@Override
	public List<HabilitanteWrapper> findByParams(PaginatedWrapper pw, String codigoContrato, String codigoJoya,String nombreCliente, String identificacionCliente,
			EstadoMidasEnum estado, Long tipoDocumento,Date fechaDesde,Date fechaHasta, Long idAgencia) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<HabilitanteWrapper> query = cb.createQuery(HabilitanteWrapper.class);
			// ~~> FROM
			Root<TbMiDocumentoHabilitante> poll = query.from(TbMiDocumentoHabilitante.class);
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> jDocHabTipDoc = poll.join("tbMiTipoDocumento", JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiContrato> jDocHabContrato = poll.join("tbMiContrato", JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiCorteCaja> joinCorteCaja = poll.join("tbMiCorteCaja", JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiJoya> jDocHabJoya = poll.join("tbMiJoya", JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiAbono> jDocHabAbono = poll.join("tbMiAbono", JoinType.LEFT);
			Join<TbMiAbono, TbMiCliente> jAbonoCliente = jDocHabAbono.join("tbMiCliente", JoinType.LEFT);
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if(StringUtils.isNotBlank(codigoContrato)) {
				where.add(cb.like(poll.<TbMiContrato>get("tbMiContrato").
						<String>get("codigo"), "%" + codigoContrato + "%"));
			}
			if(StringUtils.isNotBlank(codigoJoya)) {
				where.add(cb.like(poll.<TbMiJoya>get("tbMiJoya").
						<String>get("codigoJoya"), "%" + codigoJoya + "%"));
			}
			if(StringUtils.isNotBlank(nombreCliente)) {
				where.add(cb.like(poll.<TbMiAbono>get("tbMiAbono").
						<TbMiCliente>get("tbMiCliente").get("nombre"), "%" + nombreCliente + "%"));
			}
			if(StringUtils.isNotBlank(identificacionCliente)) {
				where.add(cb.like(poll.<TbMiAbono>get("tbMiAbono").
						<TbMiCliente>get("tbMiCliente").get("identificacion"), "%" + identificacionCliente + "%"));
			}
			if(estado != null) {
				where.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), estado));
			}
			if(tipoDocumento != null) {
				where.add(cb.equal(jDocHabTipDoc.<Long>get("id"), tipoDocumento));
			}
			if(fechaDesde != null) {
				where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaDesde));
			}
			if(fechaHasta != null) {
				where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaHasta));
			}
			Predicate andWere = cb.and(where.toArray(new Predicate[]{}));
			if(idAgencia != null) {
				andWere=cb.and(cb.or(cb.equal(joinCorteCaja.<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), idAgencia),
						cb.equal(jDocHabContrato.<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), idAgencia)),andWere);
			}
			query.where(andWere);
			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("nombreArchivo"), poll.get("estado"),poll.get("fechaCreacion"),
					poll.get("fechaActualizacion"), jDocHabTipDoc.get("id"), jDocHabTipDoc.get("descripcion"), jDocHabContrato.get("id"),
					jDocHabContrato.get("codigo"), jDocHabJoya.get("id"),jDocHabJoya.get("codigoJoya"),
					jDocHabAbono.get("id"), jAbonoCliente.get("id"), jAbonoCliente.get("identificacion") ,joinCorteCaja.get("id"));
			// ~~> ORDER BY
			if (pw.getSortDirections().equals("asc")) {
				query.orderBy(cb.asc(poll.get(pw.getSortFields())));
			} else {
				query.orderBy(cb.desc(poll.get(pw.getSortFields())));
			}
			// ~~> EJECUTAR CONSULTA
			TypedQuery<HabilitanteWrapper> createQuery = this.getEntityManager().createQuery(query);
			if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize()).getResultList();
			} else {
				return createQuery.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer documentos habilitantes");
		}
	}

	@Override
	public Long countByParams(String codigoContrato, String codigoJoya, String nombreCliente,
			String identificacionCliente, EstadoMidasEnum estado, Long tipoDocumento,Date fechaDesde,Date fechaHasta, Long idAgencia) throws RelativeException {
		try {
			return this.countBySpecification(new DocumentoHabilitanteByParamsSpec(codigoContrato, codigoJoya, 
							nombreCliente, identificacionCliente, estado,tipoDocumento,fechaDesde,fechaHasta,idAgencia));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Contando registros documentos habilitantes");
		}
	}



	@Override
	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndIdVentaLote(Long idVentaLote, Long idTipoDocumento)
			throws RelativeException {
		return this.findAllBySpecification((new DocumentoByTipoDocumentoAndJoyaAndAbonoSpec(idTipoDocumento,null,null,idVentaLote,null)));
	}
	@Override
	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndIdCorteCaja(Long idCorteCaja, Long idTipoDocumento)
			throws RelativeException {
		return this.findAllBySpecification((new DocumentoByTipoDocumentoAndJoyaAndAbonoSpec(idTipoDocumento,null,null,null,idCorteCaja)));
	}
    
}



