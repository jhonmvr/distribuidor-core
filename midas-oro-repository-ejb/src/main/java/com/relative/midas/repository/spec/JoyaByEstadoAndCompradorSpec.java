package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiJoya;

public class JoyaByEstadoAndCompradorSpec extends AbstractSpecification<TbMiJoya> {
	private String codigoJoya;
	private String identificacion;
	private EstadoJoyaEnum estado;

	public JoyaByEstadoAndCompradorSpec(String codigoJoya, String identificacion, EstadoJoyaEnum estado) {
		this.codigoJoya = codigoJoya;
		this.identificacion = identificacion;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoya arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		patientLevelPredicates.add( cb.isNotNull(poll.get("comprador")) );
		if (codigoJoya != null  && StringUtils.isNotBlank(codigoJoya)) {
			patientLevelPredicates.add(cb.equal(poll.get("codigoJoya"), codigoJoya));
		}
		if (identificacion != null  && StringUtils.isNotBlank(identificacion)) {
			patientLevelPredicates.add(cb.equal(poll.get("comprador").get("identificacion"), identificacion));
		}
		if (estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), estado));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
		
	}

}
