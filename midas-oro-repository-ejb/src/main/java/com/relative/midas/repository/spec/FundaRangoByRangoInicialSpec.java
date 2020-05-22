package com.relative.midas.repository.spec;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiFundaRango;

public class FundaRangoByRangoInicialSpec extends AbstractSpecification<TbMiFundaRango> {
	
	private String idAgencia;
	private EstadoMidasEnum estado;
	private String nombrePaquete;
	private BigDecimal rangoDesde;
	private BigDecimal rangoHasta;
	
	public FundaRangoByRangoInicialSpec(String idAgencia, EstadoMidasEnum estado, String nombrePaquete, BigDecimal rangoDesde,
			BigDecimal rangoHasta) {
		super();
		this.idAgencia = idAgencia;
		this.estado = estado;
		this.nombrePaquete = nombrePaquete;
		this.rangoDesde = rangoDesde;
		this.rangoHasta = rangoHasta;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFundaRango tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFundaRango> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.idAgencia != null) {
			where.add(cb.equal(poll.<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), this.idAgencia));
		}
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		}
		if(StringUtils.isNotBlank(this.nombrePaquete)) {
			where.add(cb.like(poll.<String>get("nombreFundas"), "%"+this.nombrePaquete+"%"));
		}
		if(this.rangoDesde != null) {
			where.add(cb.greaterThanOrEqualTo(poll.<BigDecimal>get("rangoInicial"), this.rangoDesde));
		}
		if(this.rangoHasta != null) {
			where.add(cb.lessThanOrEqualTo(poll.<BigDecimal>get("rangoInicial"), this.rangoHasta));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}
		 
}