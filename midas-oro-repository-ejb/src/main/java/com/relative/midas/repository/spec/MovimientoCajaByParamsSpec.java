package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiMovimientoCaja;

public class MovimientoCajaByParamsSpec extends AbstractSpecification<TbMiMovimientoCaja> {
	
	private EstadoMidasEnum estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private String codigoContrato;
	private String identificacionCliente;
	private FormaPagoEnum formaPago;
	private Long idAgencia;
	
	public MovimientoCajaByParamsSpec(EstadoMidasEnum estado, Date fechaDesde, Date fechaHasta, 
			String codigoContrato,String identificacionCliente, FormaPagoEnum formaPago, Long idAgencia) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigoContrato = codigoContrato;
		this.identificacionCliente = identificacionCliente;
		this.estado = estado;
		this.formaPago = formaPago;
		this.idAgencia = idAgencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiMovimientoCaja tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMovimientoCaja> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if(this.fechaDesde != null) {
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaDesde));
		}
		if(this.fechaHasta != null) {
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaHasta));
		}
		if(StringUtils.isNotBlank(this.codigoContrato)) {
			patientLevelPredicates.add(
					cb.like(poll.<TbMiContrato>get("tbMiContrato").<String>get("codigo"), 
							"%"+this.codigoContrato+"%"));
		}
		if(StringUtils.isNotBlank(this.identificacionCliente)) {
			patientLevelPredicates.add(
					cb.like(poll.<TbMiCliente>get("tbMiCliente").<String>get("identificacion"), 
							"%"+this.identificacionCliente+"%"));
		}
		if(this.estado != null) {
			patientLevelPredicates.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		}
		if(this.formaPago != null) {
			patientLevelPredicates.add(cb.notEqual(poll.<FormaPagoEnum>get("formaPago"), this.formaPago));
		}
		if(this.idAgencia != null) {
			patientLevelPredicates.add(cb.equal(poll.<TbMiCaja>get("tbMiCaja").<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), this.idAgencia));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
