package com.relative.midas.repository.spec;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiJoya;

public class AgenciaByJoyaEstadoSpec extends AbstractSpecification<TbMiAgencia>{
	
	private List<EstadoJoyaEnum> estados;
	
	public AgenciaByJoyaEstadoSpec(List<EstadoJoyaEnum> estados) {
		this.estados = estados;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAgencia arg0) {
		return false;
	}
	/**
	 * Lista todas las agencias por joyas estado
	 * @author Kevin Chamorro
	 */
	@Override
	public Predicate toPredicate(Root<TbMiAgencia> poll, CriteriaBuilder cb) {
		
		Join<TbMiAgencia, TbMiContrato> joinAgenciaContrato = poll.join("tbMiContratos");
		Join<TbMiContrato, TbMiJoya> joinContratoJoya = joinAgenciaContrato.join("tbMiJoyas");
		
		return cb.and(joinContratoJoya.get("estado").in(this.estados));
	}
	
}
