package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiJoyaSim;

public class JoyaSimByidAprobarContratoSpec extends AbstractSpecification<TbMiJoyaSim> {

private Long idAprobar;
	
	public JoyaSimByidAprobarContratoSpec(Long idAprobar) {
		 
		 this.idAprobar = idAprobar ;
	 }
	@Override
	public boolean isSatisfiedBy(TbMiJoyaSim arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoyaSim> poll, CriteriaBuilder cb) {	
		 
			return  cb.and(
			cb.equal(poll.get("tbMiAprobarContrato").get("id"),this.idAprobar)
			);
	}
	 

}
