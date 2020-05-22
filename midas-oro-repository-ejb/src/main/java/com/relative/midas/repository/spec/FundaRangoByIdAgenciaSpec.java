package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFundaRango;

public class FundaRangoByIdAgenciaSpec extends AbstractSpecification<TbMiFundaRango> {
	private Long idAgencia;

	public FundaRangoByIdAgenciaSpec(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFundaRango arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFundaRango> poll, CriteriaBuilder cb) {

		return cb.and(cb.equal(poll.get("tbMiAgencia").get("id"), idAgencia));

	}

}
