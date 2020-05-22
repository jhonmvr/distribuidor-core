package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdAnteriorSpec extends AbstractSpecification<TbMiContrato> {
	
	private Long idContratoAnterior;
	
	public ContratoByIdAnteriorSpec(Long idContratoAnterior) {
		super();
		this.idContratoAnterior = idContratoAnterior;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		return cb.equal(poll.get("tbMiContratoAnterior").get("id"), this.idContratoAnterior);
	}

}
