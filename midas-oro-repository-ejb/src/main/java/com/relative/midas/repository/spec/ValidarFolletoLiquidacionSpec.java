package com.relative.midas.repository.spec;

import java.math.BigInteger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFolletoLiquidacion;

public class ValidarFolletoLiquidacionSpec extends AbstractSpecification<TbMiFolletoLiquidacion> {
	private String codigo;
	private BigInteger inicio;
	private BigInteger fin;
	
	
	public ValidarFolletoLiquidacionSpec(String codigo, BigInteger inicio, BigInteger fin) {
		this.codigo = codigo;
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFolletoLiquidacion t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFolletoLiquidacion> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.or(cb.and(cb.lessThanOrEqualTo(poll.get("inicio"), inicio),cb.greaterThanOrEqualTo(poll.get("fin"), inicio),cb.equal(poll.get("codigo"), codigo)),
				cb.and(cb.lessThanOrEqualTo(poll.get("inicio"), fin),cb.greaterThanOrEqualTo(poll.get("fin"), fin),cb.equal(poll.get("codigo"), codigo)));
	}

}
