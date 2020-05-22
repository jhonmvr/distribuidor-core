package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContactabilidad;
import com.relative.midas.model.TbMiContrato;

public class ContactabilidadByParams extends AbstractSpecification<TbMiContactabilidad> {
	
	private Long idCliente;
	private Long idAgente;
	private Long idAgencia;
	private Long idContrato;
	private EstadoMidasEnum estado;
	
	public ContactabilidadByParams(Long idCliente, Long idAgente, Long idAgencia, Long idContrato,
			EstadoMidasEnum estado) {
		super();
		this.idCliente = idCliente;
		this.idAgente = idAgente;
		this.idAgencia = idAgencia;
		this.idContrato = idContrato;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiContactabilidad tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiContactabilidad> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.idCliente != null) {
			where.add(cb.equal(poll.<TbMiCliente>get("tbMiCliente").<Long>get("id"), this.idCliente));
		}
		if(this.idAgente != null) {
			where.add(cb.equal(poll.<TbMiAgente>get("tbMiAgente").<Long>get("id"), this.idAgente));
		}
		if(this.idAgencia != null) {
			where.add(cb.equal(poll.<TbMiAgencia>get("tbMiAgencia").<Long>get("id"), this.idAgencia));
		}
		if(this.idContrato != null) {
			where.add(cb.equal(poll.<TbMiContrato>get("tbMiContrato").<Long>get("id"), this.idContrato));
		}
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
