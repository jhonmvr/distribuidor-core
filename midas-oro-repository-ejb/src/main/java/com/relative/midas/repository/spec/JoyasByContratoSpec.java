package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiJoya;

public class JoyasByContratoSpec extends AbstractSpecification<TbMiJoya> {
	
	private String codigo;
	
	public JoyasByContratoSpec(String codigo) {
		super();
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoya tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiContrato").get("codigo"), this.codigo));
	}

}
