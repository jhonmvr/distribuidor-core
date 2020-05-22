package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiHistoricoJoya;

public class HistoricoByIdJoyaSpec extends AbstractSpecification<TbMiHistoricoJoya> {

	private Long idJoya;
	
	public HistoricoByIdJoyaSpec(Long idJoya) {
		this.idJoya = idJoya;
	}

	@Override
	public boolean isSatisfiedBy(TbMiHistoricoJoya t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiHistoricoJoya> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("tbMiJoya").get("id"), idJoya));
	}

}
