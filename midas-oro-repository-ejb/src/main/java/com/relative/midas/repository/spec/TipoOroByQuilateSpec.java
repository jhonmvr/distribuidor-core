package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiTipoOro;

public class TipoOroByQuilateSpec extends AbstractSpecification<TbMiTipoOro> {
	private String quilate;
	

	public TipoOroByQuilateSpec(String quilate) {
		this.quilate = quilate;
	}

	@Override
	public boolean isSatisfiedBy(TbMiTipoOro arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiTipoOro> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("quilate"), quilate));
	}

}
