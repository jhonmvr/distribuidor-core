package com.relative.midas.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCotizacion;
import com.relative.midas.model.TbMiJoyaSim;

public class JoyaSimByidCotizacionSpec extends AbstractSpecification<TbMiJoyaSim> {
	private Long id;
	
	public JoyaSimByidCotizacionSpec(Long id) {
		 
		 this.id = id ;
	 }
	@Override
	public boolean isSatisfiedBy(TbMiJoyaSim arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoyaSim> poll, CriteriaBuilder cb) {
		
		 
			return  cb.and(
			cb.equal(poll.<TbMiCotizacion>get("tbMiCotizacion").<Long>get("id"),this.id)
			);
	}
	 
	 
}
