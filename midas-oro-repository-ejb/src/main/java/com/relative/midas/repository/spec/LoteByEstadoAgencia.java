package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiLote;

public class LoteByEstadoAgencia extends AbstractSpecification<TbMiLote> {
	
	private EstadoMidasEnum estado1;
	private EstadoMidasEnum estado2;
	
	public LoteByEstadoAgencia(EstadoMidasEnum estado1, EstadoMidasEnum estado2) {
		super();
		this.estado1 = estado1;
		this.estado2 = estado2;
	}

	@Override
	public boolean isSatisfiedBy(TbMiLote tb) {
		return false;
	}
	
	/**
	 * Metodo que retorna una lista de lotes dos Estados
	 * @author kevin chamorro
	 */
	@Override
	public Predicate toPredicate(Root<TbMiLote> poll, CriteriaBuilder cb) {
		return cb.or(cb.equal(poll.get("estado"), this.estado1),cb.equal(poll.get("estado"), this.estado2));
	}
	
}
