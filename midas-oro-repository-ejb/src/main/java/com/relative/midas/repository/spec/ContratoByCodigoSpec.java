package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContrato;

public class ContratoByCodigoSpec extends AbstractSpecification<TbMiContrato>{
	
	private String codigo;

	public ContratoByCodigoSpec(String codigo) {
		this.codigo = codigo == null ? "" : codigo;
	}

	public ContratoByCodigoSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.<String>get("codigo"), this.codigo));
	}
	
}
