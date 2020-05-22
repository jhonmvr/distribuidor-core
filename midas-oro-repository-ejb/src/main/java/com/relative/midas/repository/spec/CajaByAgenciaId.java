package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCaja;

public class CajaByAgenciaId extends AbstractSpecification<TbMiCaja> {
	
	private Long idAgencia;
	
	public CajaByAgenciaId(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiCaja arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiCaja> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiAgencia").<Long>get("id"), this.idAgencia));
	}

}
