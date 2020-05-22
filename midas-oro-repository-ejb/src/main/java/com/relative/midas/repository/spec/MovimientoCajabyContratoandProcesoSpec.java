package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiMovimientoCaja;

public class MovimientoCajabyContratoandProcesoSpec  extends AbstractSpecification<TbMiMovimientoCaja> {

	
	private Long idContrato;
	private ProcesoEnum proceso;
	
	public MovimientoCajabyContratoandProcesoSpec(Long idContrato, ProcesoEnum proceso) {
		this.proceso = proceso; 
		this.idContrato = idContrato;
	}

	@Override
	public boolean isSatisfiedBy(TbMiMovimientoCaja arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMovimientoCaja> poll, CriteriaBuilder cb) {
		
		return cb.and(
				cb.equal(poll.get("tbMiContrato").get("id"), idContrato),	
				cb.equal(poll.get("proceso"), proceso)
				
				);
	}
	


}
