package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiMovEntreCaja;

public class MovEntreCajaByParamsSpec extends AbstractSpecification<TbMiMovEntreCaja> {
	
	private Long idAgenciaOrigen;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public MovEntreCajaByParamsSpec(Long idAgenciaOrigen , Date fechaDesde, Date fechaHasta) {
		super();
		this.idAgenciaOrigen = idAgenciaOrigen;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	@Override
	public boolean isSatisfiedBy(TbMiMovEntreCaja tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMovEntreCaja> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();

		if(this.idAgenciaOrigen != null) {
			where.add(cb.equal(poll.<TbMiAgencia>get("tbMiAgenciaO").<Long>get("id"), this.idAgenciaOrigen));
		}
		if(this.fechaDesde != null) {
			where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaDesde));
		}
		if(this.fechaHasta != null) {
			where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaHasta));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
