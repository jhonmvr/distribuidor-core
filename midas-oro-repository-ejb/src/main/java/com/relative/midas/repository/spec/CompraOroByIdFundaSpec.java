package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCompraOro;

public class CompraOroByIdFundaSpec  extends AbstractSpecification<TbMiCompraOro> {
	private Long idFunda;
	
	public CompraOroByIdFundaSpec(Long idFunda){
		this.idFunda=idFunda;
	}
	@Override
	public boolean isSatisfiedBy(TbMiCompraOro t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiCompraOro> poll, CriteriaBuilder cb) {
		
		return cb.and( cb.equal(poll.get("tbMiFunda").get("id"), idFunda));
	}

	
}
