package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.core.persistence.Specification;
import com.relative.midas.model.TbMiMovimientoCaja;

public class MovimientoCajaByVentaLoteSpec extends AbstractSpecification<TbMiMovimientoCaja>  {
	private Long idVentaLote;
	
	

	public MovimientoCajaByVentaLoteSpec(Long idVentaLote) {
		this.idVentaLote = idVentaLote;
	}

	@Override
	public boolean isSatisfiedBy(TbMiMovimientoCaja t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMovimientoCaja> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("tbMiVentaLote").get("id"), idVentaLote));
	}


}
