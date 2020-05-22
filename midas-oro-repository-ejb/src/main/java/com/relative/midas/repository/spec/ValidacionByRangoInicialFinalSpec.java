package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFundaRango;

public class ValidacionByRangoInicialFinalSpec extends AbstractSpecification<TbMiFundaRango>  {
	
	private Long rangoInicial;
	
	public ValidacionByRangoInicialFinalSpec(Long rangoInicialValidacion) {
		super();
		this.rangoInicial = rangoInicialValidacion;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFundaRango arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFundaRango> poll, CriteriaBuilder cb) {
		return cb.and(cb.lessThanOrEqualTo(poll.<Long>get("rangoInicial"), this.rangoInicial),
				cb.greaterThanOrEqualTo(poll.<Long>get("rangoFinal"), this.rangoInicial));
	}

}
