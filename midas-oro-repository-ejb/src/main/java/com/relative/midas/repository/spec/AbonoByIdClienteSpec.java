package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAbono;

public class AbonoByIdClienteSpec extends AbstractSpecification<TbMiAbono>{
	
	private Long idCliente;
	
	public AbonoByIdClienteSpec(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAbono arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAbono> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiCliente").get("id"), this.idCliente),
				cb.isNull(poll.get("tbMiContrato")));
	}

}
