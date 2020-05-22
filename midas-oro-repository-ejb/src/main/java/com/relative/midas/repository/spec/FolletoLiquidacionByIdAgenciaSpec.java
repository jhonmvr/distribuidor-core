package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFolletoLiquidacion;

public class FolletoLiquidacionByIdAgenciaSpec extends AbstractSpecification<TbMiFolletoLiquidacion> {
	private Long idAgencia;

	public FolletoLiquidacionByIdAgenciaSpec(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFolletoLiquidacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFolletoLiquidacion> poll, CriteriaBuilder cb) {

		return cb.and(cb.equal(poll.get("tbMiAgencia").get("id"), idAgencia));

	}

}
