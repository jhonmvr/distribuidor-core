package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;

public class BancoByParamSpec extends AbstractSpecification<TbMiBanco> {
	private EstadoMidasEnum estado;
	private String nombre;

	public BancoByParamSpec(EstadoMidasEnum estado, String nombre) {
		this.estado = estado;
		this.nombre = nombre;
	}

	@Override
	public boolean isSatisfiedBy(TbMiBanco t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiBanco> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.estado != null) {
			where.add(cb.equal(poll.get("estado"), estado));
		}
		if(StringUtils.isNotBlank(nombre)) {
			where.add(cb.like(poll.get("nombre"), "%"+nombre+ "%"));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
