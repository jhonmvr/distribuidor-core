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

public class CorteCajaByIdAgenciaAndEstadoSpec extends AbstractSpecification<TbMiCorteCaja> {

	
	private EstadoMidasEnum estado;
	private Long idAgencia;

	public CorteCajaByIdAgenciaAndEstadoSpec( EstadoMidasEnum estado, Long idAgencia) {
		super();
		
		this.estado = estado;
		this.idAgencia = idAgencia;
	}

	public boolean isSatisfiedBy(TbMiCorteCaja tb) {
		return false;
	}

	public Predicate toPredicate(Root<TbMiCorteCaja> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbMiCorteCaja").get("id"), this.idAgencia));
		where.add(cb.equal(poll.get("estado"), this.estado));
		return cb.and(where.toArray(new Predicate[] {}));
	}

}
