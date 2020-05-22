package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiVentaLote;

public class LoteByIdVentaLoteSpec extends AbstractSpecification<TbMiLote> {
	
	private Long idVentaLote;
	
	public LoteByIdVentaLoteSpec(Long idVentaLote) {
		super();
		this.idVentaLote = idVentaLote;
	}

	@Override
	public boolean isSatisfiedBy(TbMiLote tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiLote> poll, CriteriaBuilder cb) {
		return cb.equal(poll.<TbMiVentaLote>get("tbMiVentaLote").<Long>get("id"), this.idVentaLote);
	}

}
