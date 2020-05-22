package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdentificacionClienteSpec extends AbstractSpecification<TbMiContrato> {
	private String identificacion;

	public ContratoByIdentificacionClienteSpec(String identificacion) {

		this.identificacion = identificacion == null ? "" : identificacion;
	}

	public ContratoByIdentificacionClienteSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		//Join<TbMiCliente,TbMiContra> joincont = poll.join("tbMiCliente")
		return cb.and(cb.equal(poll.<TbMiCliente>get("tbMiCliente").<String>get("identificacion"), this.identificacion));
	}

}