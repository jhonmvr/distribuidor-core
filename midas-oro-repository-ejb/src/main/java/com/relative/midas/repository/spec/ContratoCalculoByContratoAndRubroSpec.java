package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContratoCalculo;

public class ContratoCalculoByContratoAndRubroSpec  extends AbstractSpecification<TbMiContratoCalculo> {

	private String rubro;
	private Long idContrato;
	
	public ContratoCalculoByContratoAndRubroSpec(Long idContrato,String rubro) {
		this.rubro = rubro;
		this.idContrato = idContrato;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContratoCalculo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContratoCalculo> poll, CriteriaBuilder cb) {
		if( this.idContrato != null && !StringUtils.isEmpty( this.rubro ) ) {
			return cb.and(
					cb.equal(poll.get("tbMiContrato").get("id"), idContrato),
					cb.equal(poll.get("rubro"), rubro)
					);
		} else {
			return cb.and(
					cb.equal(poll.get("tbMiContrato").get("id"), idContrato)
					);
		}
		
	}
	


}
