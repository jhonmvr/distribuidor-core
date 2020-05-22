package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiLiquidacion;

public class LiquidacionByFolletoLiquidacionSpec extends AbstractSpecification<TbMiLiquidacion> {
	
	private Long idFolletoLiquidacion;
	
	public LiquidacionByFolletoLiquidacionSpec(Long idFolletoLiquidacion) {
		super();
		this.idFolletoLiquidacion = idFolletoLiquidacion;
	}

	@Override
	public boolean isSatisfiedBy(TbMiLiquidacion tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiLiquidacion> poll, CriteriaBuilder cb) {
		return cb.equal(poll.get("tbMiFolletoLiquidacion").get("id"), this.idFolletoLiquidacion);
	}

}
