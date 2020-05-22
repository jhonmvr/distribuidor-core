package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCorteCaja;

public class CorteCajaByMultiSpec extends AbstractSpecification<TbMiCorteCaja> {
	
	private Date fechaDesde;
	private Date fechaHasta;
	private EstadoMidasEnum estado;
	private Long idAgencia;
	private Long id;
	
	
	public CorteCajaByMultiSpec(Long id ,Date fechaDesde, Date fechaHasta,
			EstadoMidasEnum estado,Long idAgencia) {
		super();
		this.id=id;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;		
		this.estado = estado;
		this.idAgencia = idAgencia;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiCorteCaja arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Predicate toPredicate(Root<TbMiCorteCaja> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if(id != null ) {
			patientLevelPredicates.add(cb.equal(poll.<Long>get("id"), id));
		}
		if(fechaDesde != null ) {
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaDesde));
		}
		if(fechaHasta != null) {
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaHasta));
		}
		if(this.estado != null) {
			patientLevelPredicates.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		}
		if(this.idAgencia != null) {
			patientLevelPredicates.add(cb.equal(poll.get("tbMiAgencia").get("id"), this.idAgencia));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
