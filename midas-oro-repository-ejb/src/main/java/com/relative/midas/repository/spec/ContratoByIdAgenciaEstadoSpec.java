package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiContrato;

public class ContratoByIdAgenciaEstadoSpec extends AbstractSpecification<TbMiContrato> {
	
	private String idAgencia;
	private EstadoContratoEnum estado1;
	private EstadoContratoEnum estado2;
	
	public ContratoByIdAgenciaEstadoSpec(String idAgencia, EstadoContratoEnum estado1, EstadoContratoEnum estado2) {
		this.idAgencia = StringUtils.isNotEmpty(idAgencia.trim())? idAgencia.trim(): StringUtils.EMPTY;
		this.estado1 = estado1;
		this.estado2 = estado2;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato tb) {
		return false;
	}
	
	/**
	 * Lista todos los contratos por idAgencia y 2 estados del contrato
	 * @author Kevin Chamorro
	 */
	@Override
	public Predicate toPredicate(Root<TbMiContrato> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiAgencia").get("id"), this.idAgencia),
				cb.or(cb.equal(poll.<EstadoContratoEnum>get("estado"), this.estado1),
						cb.equal(poll.<EstadoContratoEnum>get("estado"), this.estado2)));
	}

}
