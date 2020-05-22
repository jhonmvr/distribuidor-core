package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoJoya;

public class TipoJoyaByDetalleEstado extends AbstractSpecification<TbMiTipoJoya> {
	
	private String detalle;
	private EstadoMidasEnum estado;

	
	
	public TipoJoyaByDetalleEstado(String detalle, EstadoMidasEnum estado) {
		this.detalle = detalle;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiTipoJoya arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiTipoJoya> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if(this.estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), estado));
		}
		if(this.detalle != null) {
			patientLevelPredicates.add(cb.like(poll.get("detalle"), "%"+detalle+ "%"));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}
	
	
	

}
