package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCliente;

public class ClienteByIdentificacionSpec extends AbstractSpecification<TbMiCliente> {
	private String identificacion;

	public ClienteByIdentificacionSpec(String identificacion) {

		this.identificacion = identificacion == null ? "" : identificacion;
	}

	public ClienteByIdentificacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiCliente arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiCliente> poll, CriteriaBuilder cb) {

		return cb.and(cb.like(poll.<String>get("identificacion"),"%"+this.identificacion+"%"), cb.equal(poll.<EstadoMidasEnum>get("estado"), EstadoMidasEnum.ACT));
	}

}