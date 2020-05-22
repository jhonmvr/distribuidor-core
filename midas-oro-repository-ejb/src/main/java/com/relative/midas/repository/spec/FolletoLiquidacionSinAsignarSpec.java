package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFolletoLiquidacion;

public class FolletoLiquidacionSinAsignarSpec extends AbstractSpecification<TbMiFolletoLiquidacion> {

	public FolletoLiquidacionSinAsignarSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiFolletoLiquidacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFolletoLiquidacion> poll, CriteriaBuilder cb) {

		return cb.and( cb.isNull(poll.get("tbMiAgencia") )
				);

	}

}
