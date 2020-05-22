package com.relative.midas.repository.spec;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCorteCaja;

public class CorteCajaByFechaCreacionSpec extends AbstractSpecification<TbMiCorteCaja> {
	
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private EstadoMidasEnum estado;
	
	public CorteCajaByFechaCreacionSpec(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}
	
	public boolean isSatisfiedBy(TbMiCorteCaja tb) {
		return false;
	}

	public Predicate toPredicate(Root<TbMiCorteCaja> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if(this.fechaCreacion != null) {
			where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaActualizacion));
		}
		if(this.fechaActualizacion != null) {
			where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaActualizacion));
		}
		if(this.estado!= null) {
			where.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado ) );	
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}


}
