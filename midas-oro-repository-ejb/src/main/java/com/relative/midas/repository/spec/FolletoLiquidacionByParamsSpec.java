package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFolletoLiquidacion;

public class FolletoLiquidacionByParamsSpec extends AbstractSpecification<TbMiFolletoLiquidacion> {

	private Long idAgencia;
	private String nombre;
	private Date fechaVigenciaDesde;
	private Date fechaVigenciaHasta;
	
	public FolletoLiquidacionByParamsSpec(Long idAgencia, String nombre, Date fechaVigenciaDesde,
			Date fechaVigenciaHasta) {
		super();
		this.idAgencia = idAgencia;
		this.nombre = nombre;
		this.fechaVigenciaDesde = fechaVigenciaDesde;
		this.fechaVigenciaHasta = fechaVigenciaHasta;
	}

	@Override
	public boolean isSatisfiedBy(TbMiFolletoLiquidacion tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFolletoLiquidacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.idAgencia != null) {
			where.add(cb.equal(poll.get("tbMiAgencia").get("id"), this.idAgencia));
		}
		if(StringUtils.isNotBlank(this.nombre)) {
			where.add(cb.like(poll.get("nombreFolleto"), "%"+this.nombre+"%"));
		}
		if(this.fechaVigenciaDesde != null) {
			where.add(cb.greaterThanOrEqualTo(poll.get("fechaVigencia"), this.fechaVigenciaDesde));
		}
		if(this.fechaVigenciaHasta != null) {
			where.add(cb.lessThanOrEqualTo(poll.get("fechaVigencia"), this.fechaVigenciaHasta));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
