package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAgente;

public class AgenteByNombreUsuarioSpec extends AbstractSpecification<TbMiAgente> {
	
	private String nombreUsuario;
	
	public AgenteByNombreUsuarioSpec(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAgente tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAgente> poll, CriteriaBuilder cb) {
		return cb.equal(poll.get("nombreUsuario"), this.nombreUsuario);
	}

}
