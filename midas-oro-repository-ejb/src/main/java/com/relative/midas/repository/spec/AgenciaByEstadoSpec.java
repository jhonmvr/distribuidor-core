package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAgencia;

public class AgenciaByEstadoSpec extends AbstractSpecification<TbMiAgencia> {
	
	private EstadoMidasEnum estado;
	
	public AgenciaByEstadoSpec(EstadoMidasEnum estado) {
		super();
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAgencia tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAgencia> poll, CriteriaBuilder cb) {
		return cb.equal(poll.<String>get("estado"), this.estado);
	}

}
