package com.relative.midas.repository.spec;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;

public class BancoByEstadoAndNombre extends AbstractSpecification<TbMiBanco> {
	
	private String nombre;
	private EstadoMidasEnum  estado; 

	


	public BancoByEstadoAndNombre(String nombre, EstadoMidasEnum estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiBanco arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiBanco> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if (nombre != null) {
			patientLevelPredicates.add(cb.like(poll.get("nombre"), "%" + this.nombre + "%"));
		}
		if (estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), this.estado));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}
}
