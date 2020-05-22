package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiTipoDocumento;

public class DocumentoHabilitanteByParamsSpec extends AbstractSpecification<TbMiDocumentoHabilitante> {

	private String codigoContrato;
	private String codigoJoya;
	private String nombreCliente;
	private String identificacionCliente;
	private EstadoMidasEnum estado;
	private Long tipoDocumento;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long idAgencia;

	public DocumentoHabilitanteByParamsSpec(String codigoContrato, String codigoJoya, String nombreCliente,
			String identificacionCliente, EstadoMidasEnum estado, Long tipoDocumento,Date fechaDesde,Date fechaHasta,Long idAgencia) {
		super();
		this.codigoContrato = codigoContrato;
		this.codigoJoya = codigoJoya;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.estado = estado;
		this.tipoDocumento = tipoDocumento;
		this.fechaDesde=fechaDesde;
		this.fechaHasta=fechaHasta;
		this.idAgencia=idAgencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiDocumentoHabilitante tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {

		Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> jDocHabTipDoc = poll.join("tbMiTipoDocumento", JoinType.INNER);
		Join<TbMiDocumentoHabilitante, TbMiContrato> jDocHabContrato = poll.join("tbMiContrato", JoinType.LEFT);
		Join<TbMiDocumentoHabilitante, TbMiCorteCaja> joinCorteCaja = poll.join("tbMiCorteCaja", JoinType.LEFT);
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
	
		
		return andWere;
	}

}
