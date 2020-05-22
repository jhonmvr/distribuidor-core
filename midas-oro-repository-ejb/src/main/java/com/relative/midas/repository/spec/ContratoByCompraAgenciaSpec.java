package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiContrato;

public class ContratoByCompraAgenciaSpec extends AbstractSpecification<TbMiContrato> {
	private Long idAgencia;
	private String tipoContrato;

	public ContratoByCompraAgenciaSpec(String tipoContrato, Long idAgencia ) {

		this.tipoContrato = tipoContrato == null ? "" : tipoContrato;
		this.idAgencia = idAgencia;
	}

	public ContratoByCompraAgenciaSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {

		return cb.and(
				cb.equal(poll.<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), this.idAgencia),
				cb.equal(poll.<String>get("tipoCompra"), this.tipoContrato)
				);
	}

}