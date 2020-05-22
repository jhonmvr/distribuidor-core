package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiDetalleCaja;

public class DetalleCajaByIdCorteCaja extends AbstractSpecification<TbMiDetalleCaja> {
	
	private Long idCorteCaja;
	
	public DetalleCajaByIdCorteCaja(Long idCorteCaja) {
		this.idCorteCaja = idCorteCaja;
	}
		
		
		public boolean isSatisfiedBy(TbMiDetalleCaja arg0) {
			// TODO Auto-generated method stub
			return false;		
	}
		
		public Predicate toPredicate(Root<TbMiDetalleCaja> poll, CriteriaBuilder cb) {			
			List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();			
			patientLevelPredicates.add(cb.equal(poll.get("tbMiCorteCaja").get("id"), this.idCorteCaja));
			return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
		}
	
	
}
