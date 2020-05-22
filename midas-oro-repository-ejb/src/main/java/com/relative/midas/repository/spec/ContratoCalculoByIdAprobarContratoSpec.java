package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContratoCalculo;

public class ContratoCalculoByIdAprobarContratoSpec extends AbstractSpecification<TbMiContratoCalculo> {

	private Long idAprobarContrato;
	
	public ContratoCalculoByIdAprobarContratoSpec(Long idAprobarContrato) {

		this.idAprobarContrato = idAprobarContrato;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContratoCalculo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContratoCalculo> poll, CriteriaBuilder cb) {
		
		return cb.and( cb.equal(poll.get("tbMiAprobarContrato").get("id"), idAprobarContrato));
	}

	

}
