package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdClienteSpec extends AbstractSpecification<TbMiContrato> {
	private Long idCliente;

	public ContratoByIdClienteSpec(Long idCliente) {

		this.idCliente = idCliente;
	}

	public ContratoByIdClienteSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		//Join<TbMiCliente,TbMiContra> joincont = poll.join("tbMiCliente")
		return cb.and(cb.equal(poll.<TbMiCliente>get("tbMiCliente").get("id"), this.idCliente));
	}

}