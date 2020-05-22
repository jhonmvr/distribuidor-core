package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiJoyaLote;

public class JoyaLoteByJoyaSpec extends AbstractSpecification<TbMiJoyaLote> {
	
	private Long idJoya;
	
	public JoyaLoteByJoyaSpec(Long idJoya) {
		super();
		this.idJoya = idJoya;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoyaLote tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoyaLote> poll, CriteriaBuilder cb) {
		return cb.equal(poll.get("tbMiJoya").get("id"), this.idJoya);
	}

}
