package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAbono;

public class AbonoByClienteIdentificacion extends AbstractSpecification<TbMiAbono>{
	
	private String clienteIdentificacion;
	
	public AbonoByClienteIdentificacion(String clienteIdentificacion) {
		super();
		this.clienteIdentificacion = StringUtils.isNotEmpty(clienteIdentificacion.trim())? clienteIdentificacion.trim():StringUtils.EMPTY;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAbono arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAbono> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbMiCliente").get("identificacion"), this.clienteIdentificacion),
				cb.isNull(poll.get("tbMiContrato")));
	}

}
