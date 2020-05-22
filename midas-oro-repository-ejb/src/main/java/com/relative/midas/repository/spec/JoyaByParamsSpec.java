package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiJoya;

public class JoyaByParamsSpec extends AbstractSpecification<TbMiJoya> {
	private List<EstadoJoyaEnum> estados;
	private String codigoJoya;
	private Long idTipoJoya;

	
	public JoyaByParamsSpec(List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya) {
		this.estados = estados;
		this.codigoJoya = codigoJoya;
		this.idTipoJoya = idTipoJoya;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoya arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

		if(estados != null) {
			patientLevelPredicates.add(poll.<EstadoJoyaEnum>get("estado").in(estados));
		}
		if(codigoJoya != null) {
			patientLevelPredicates.add(cb.equal(poll.<String>get("codigoJoya"), codigoJoya));
		}
		
		if(idTipoJoya != null) {
			patientLevelPredicates.add(cb.equal(poll.get("tbMiTipoJoya").get("id"), idTipoJoya));
		}

		
		
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
