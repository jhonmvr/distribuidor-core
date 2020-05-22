package com.relative.midas.repository.spec;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdClienteAndFechaCreacion  extends AbstractSpecification<TbMiContrato>{

	private Long idCliente;
	private Date fechaInicio;
	private Date fechaFin;
	
	public ContratoByIdClienteAndFechaCreacion(Long idCliente,Date fechaInicio,Date fechaFin){
		this.idCliente = idCliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		
		return cb.and(
				cb.equal(poll.get("tbMiCliente").get("id"),idCliente),
				cb.greaterThanOrEqualTo(poll.get("fechaCreacion"), fechaInicio),
				cb.lessThanOrEqualTo(poll.get("fechaCreacion"), fechaFin)
				);
	}
}
