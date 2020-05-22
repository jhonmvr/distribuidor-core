package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.model.TbMiLote;;

public class JoyaLoteByIdLoteSpec extends AbstractSpecification<TbMiJoyaLote> {
	
	private Long idLote;
	
	public JoyaLoteByIdLoteSpec(Long idLote) {
		super();
		this.idLote = idLote;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoyaLote arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoyaLote> poll, CriteriaBuilder cb) {
		return cb.equal(poll.<TbMiLote>get("tbMiLote").get("id"), this.idLote);
	}

}
