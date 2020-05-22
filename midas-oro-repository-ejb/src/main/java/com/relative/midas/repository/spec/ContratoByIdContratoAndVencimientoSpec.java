package com.relative.midas.repository.spec;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdContratoAndVencimientoSpec extends AbstractSpecification<TbMiContrato> {
	private Long idContrato;
	private Date fechaVencimiento;
	

	
	public ContratoByIdContratoAndVencimientoSpec(Long idContrato, Date fechaVencimiento) {
		this.idContrato = idContrato;
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("id"), idContrato),cb.lessThanOrEqualTo(poll.get("fechaVencimiento"), fechaVencimiento));
	}

	
}
