package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.core.persistence.Specification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiHistoricoJoya;
import com.relative.midas.model.TbMiJoya;

public class HistoricoByJoyaSpec  extends AbstractSpecification<TbMiHistoricoJoya> {
	
	private Long idJoya;
	private EstadoJoyaEnum estado;
	
	public HistoricoByJoyaSpec(Long idJoya, EstadoJoyaEnum estado) {
		super();
		this.estado = estado;
		this.idJoya = idJoya;
	}

	@Override
	public boolean isSatisfiedBy(TbMiHistoricoJoya tb) {
		return false;
	}

	

	@Override
	public Predicate toPredicate(Root<TbMiHistoricoJoya> poll, CriteriaBuilder cb) {
		return cb.and(
				cb.equal(poll.get("tbMiJoya").get("id"), idJoya),
				cb.equal(poll.get("estado"), estado)
				);
		
	}

}
