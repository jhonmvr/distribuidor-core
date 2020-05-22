package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiInventario;

public class InventarioByJoyaSpec extends AbstractSpecification<TbMiInventario>{
	private Long idJoya;
	
	public InventarioByJoyaSpec (Long idJoya) {
		this.idJoya = idJoya;
	}
	

	@Override
	public boolean isSatisfiedBy(TbMiInventario arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiInventario> poll, CriteriaBuilder cb) {
		
		return cb.and(
				cb.equal(poll.get("tbMiJoya").<Long>get("id"), this.idJoya)
				);
	}

}
