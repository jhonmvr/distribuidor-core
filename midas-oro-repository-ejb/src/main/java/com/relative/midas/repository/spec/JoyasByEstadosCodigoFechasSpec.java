package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiJoya;

public class JoyasByEstadosCodigoFechasSpec extends AbstractSpecification<TbMiJoya> {
	
	private String codigoJoya;
	private List<EstadoJoyaEnum> estadosJoya;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public JoyasByEstadosCodigoFechasSpec(String codigoJoya, List<EstadoJoyaEnum> estadosJoya,Date fechaDesde,Date fechaHasta) {
		super();
		this.codigoJoya = codigoJoya;
		this.estadosJoya=estadosJoya;
		this.fechaDesde=fechaDesde;
		this.fechaHasta=fechaHasta;
	}

	@Override
	public boolean isSatisfiedBy(TbMiJoya tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiJoya> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if(fechaDesde != null  ) {
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaDesde));
		}
		if( fechaHasta != null ) {
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaHasta));
		}
		if( estadosJoya != null ) {
			Expression<EstadoJoyaEnum> estadoExpression = poll.get("estado");
			Predicate predicate = estadoExpression.in(estadosJoya);
			patientLevelPredicates.add(predicate);
		}
		if( !StringUtils.isEmpty(codigoJoya) ) {
			patientLevelPredicates.add(cb.like(poll.<String>get("codigoJoya"),"%" + codigoJoya + "%" ));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
