package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiBodega;

public class BodegaByAgenciaAndNombreSpec extends AbstractSpecification<TbMiBodega>{
	
	private String nombre;
	private Long idAgencia;
	
	public BodegaByAgenciaAndNombreSpec(Long idAgencia, String nombre) {
		super();
		this.nombre = nombre;
		this.idAgencia = idAgencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiBodega arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiBodega> poll, CriteriaBuilder cb) {
		return cb.and( cb.equal(poll.get("tbMiAgencia").get("id"), this.idAgencia),
				cb.equal(poll.get("nombre"),this.nombre) );
	}

}
