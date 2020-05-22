package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiJoya;

public class JoyasByIdFundaSpec extends AbstractSpecification<TbMiJoya> {
	
	private Long idFunda;
	
	public JoyasByIdFundaSpec(Long idFunda) {
		super();
		this.idFunda = idFunda;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoya tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		return cb.equal(poll.<TbMiFunda>get("tbMiFunda").<Long>get("id"), this.idFunda);
	}

}
