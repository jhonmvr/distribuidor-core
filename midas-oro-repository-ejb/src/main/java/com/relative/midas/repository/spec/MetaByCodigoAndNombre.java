package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiMeta;

public class MetaByCodigoAndNombre extends AbstractSpecification<TbMiMeta> {
	private Date fechaDesde;
	private Date fechaHasta;
	private String nombre;
	public MetaByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre) {
		this.fechaDesde= fechaDesde;
		this.fechaHasta= fechaHasta;
		this.nombre=nombre;
	}

	@Override
	public boolean isSatisfiedBy(TbMiMeta t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiMeta> poll, CriteriaBuilder cb) {

		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

		if (fechaDesde != null) {
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.get("fechaCreacion"), fechaDesde));
		}
		if (fechaHasta != null) {
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.get("fechaCreacion"), fechaHasta));
		}
		if (nombre != null) {
			patientLevelPredicates.add(cb.like(poll.get("nombre"), "%" + this.nombre + "%"));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}


	

}
