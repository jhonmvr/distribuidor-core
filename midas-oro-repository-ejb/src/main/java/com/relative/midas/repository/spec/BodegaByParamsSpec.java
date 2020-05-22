package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.TipoAgenciaEnum;
import com.relative.midas.enums.TipoBodegaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiBodega;

public class BodegaByParamsSpec extends AbstractSpecification<TbMiBodega> {
	
	private TipoAgenciaEnum tipoAgencia;
	private TipoBodegaEnum tipoBodega;
	
	public BodegaByParamsSpec(TipoAgenciaEnum tipoAgencia, TipoBodegaEnum tipoBodega) {
		super();
		this.tipoAgencia = tipoAgencia;
		this.tipoBodega = tipoBodega;
	}

	@Override
	public boolean isSatisfiedBy(TbMiBodega tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiBodega> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.tipoAgencia != null) {
			where.add(cb.equal(poll.<TbMiAgencia>get("tbMiAgencia").<TipoAgenciaEnum>get("tipoAgencia"), this.tipoAgencia));
		}
		if(this.tipoBodega != null) {
			where.add(cb.equal(poll.<TipoBodegaEnum>get("tipoBodega"), this.tipoBodega));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
