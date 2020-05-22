package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAgencia;

public class AgenciaByCodigoAndNombreSpec extends AbstractSpecification<TbMiAgencia> {

	private String codigo;
	private String nombre;

	public AgenciaByCodigoAndNombreSpec(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAgencia arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAgencia> poll, CriteriaBuilder cb) {

		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

		if (codigo != null) {
			patientLevelPredicates.add(cb.equal(poll.get("codigo"), codigo));
		}
		if (nombre != null) {
			patientLevelPredicates.add(cb.like(poll.get("nombreAgencia"), "%" + this.nombre + "%"));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
