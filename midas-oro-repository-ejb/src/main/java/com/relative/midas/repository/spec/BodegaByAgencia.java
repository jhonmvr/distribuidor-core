package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.TipoBodegaEnum;
import com.relative.midas.model.TbMiBodega;

public class BodegaByAgencia extends AbstractSpecification<TbMiBodega> {
	
	private long idAgencia;
	private TipoBodegaEnum tipoBodega;

	public BodegaByAgencia(long idAgencia, TipoBodegaEnum tipoBodega) {
		super();
		this.idAgencia = idAgencia;
		this.tipoBodega = tipoBodega;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiBodega tb) {
		return false;
	}
	/**
	 * Metodo que retorna una lista de bodegas por tipo bodega y id agencia
	 * @param idAgencia
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	@Override
	public Predicate toPredicate(Root<TbMiBodega> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiAgencia").get("id"), this.idAgencia),
				cb.equal(poll.get("tipoBodega"), this.tipoBodega));
	}

}
