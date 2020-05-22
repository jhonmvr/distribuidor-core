package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.TipoAgenciaEnum;
import com.relative.midas.model.TbMiAgencia;

public class AgenciaByTipoAgenciaSpec extends AbstractSpecification<TbMiAgencia>{

	private TipoAgenciaEnum tipoAgencia;
	public AgenciaByTipoAgenciaSpec(TipoAgenciaEnum tipoAgencia) {
		this.tipoAgencia=tipoAgencia;
	}
	@Override
	public boolean isSatisfiedBy(TbMiAgencia t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAgencia> poll, CriteriaBuilder cb) {
		return cb.and(poll.get("tipoAgencia").in(this.tipoAgencia));
	}

}
