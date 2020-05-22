package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAbono;

public class AbonoByEstadoIdentificacionSpec extends AbstractSpecification<TbMiAbono> {
	private EstadoMidasEnum estado;
	private String identificacion;

	public AbonoByEstadoIdentificacionSpec(EstadoMidasEnum estado, String identificacion) {
		this.estado = estado;
		this.identificacion = identificacion;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAbono arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAbono> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

		if (estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), estado));
		}
		if (identificacion != null && StringUtils.isNotBlank(identificacion)) {
			patientLevelPredicates.add(cb.equal(poll.get("tbMiCliente").get("identificacion"), identificacion));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
		
	}

}
