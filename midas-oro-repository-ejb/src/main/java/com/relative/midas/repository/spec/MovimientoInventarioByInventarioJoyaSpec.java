package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.model.TbMiMovInventario;

public class MovimientoInventarioByInventarioJoyaSpec extends AbstractSpecification<TbMiMovInventario>{
	
	
	private Long idInventarioJoya;
	
	public MovimientoInventarioByInventarioJoyaSpec(Long idInventarioJoya) {
		super();
		
		this.idInventarioJoya=idInventarioJoya;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiMovInventario arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Predicate toPredicate(Root<TbMiMovInventario> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		patientLevelPredicates.add(cb.equal(poll.<TbMiInventario>get("tbMiInventario").<Long>get("id"), this.idInventarioJoya));
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}
	

	
}	
