package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiMovimientoCaja;

public class MovimientoCajabyProcesoSpec  extends AbstractSpecification<TbMiMovimientoCaja> {


	private ProcesoEnum proceso;
	
	public MovimientoCajabyProcesoSpec(ProcesoEnum proceso) {
		this.proceso = proceso; 
	
	}

	@Override
	public boolean isSatisfiedBy(TbMiMovimientoCaja arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMovimientoCaja> poll, CriteriaBuilder cb) {
		
		return cb.and(
		
				cb.equal(poll.get("proceso"), proceso)
				
				);
	}
	


}
