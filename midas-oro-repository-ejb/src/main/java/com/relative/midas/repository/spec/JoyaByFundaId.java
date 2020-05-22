package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiJoya;

public class JoyaByFundaId extends AbstractSpecification<TbMiJoya> {
	
	private Long idFunda;
	
	public JoyaByFundaId(Long idFunda) {
		this.idFunda = idFunda;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiJoya arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiFunda").<Long>get("id"), this.idFunda));
	}

}
