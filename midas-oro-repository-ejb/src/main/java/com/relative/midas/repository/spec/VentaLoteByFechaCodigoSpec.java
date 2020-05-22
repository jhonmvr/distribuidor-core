package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiVentaLote;

public class VentaLoteByFechaCodigoSpec extends AbstractSpecification<TbMiVentaLote> {
	
	private Date fechaDesde;
	private Date fechaHasta;
	private String codigo;
	
	public VentaLoteByFechaCodigoSpec(Date fechaDesde, Date fechaHasta, String codigo) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(TbMiVentaLote tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiVentaLote> poll, CriteriaBuilder cb) {
		
		List<Predicate> where = new ArrayList<>();
		if(this.fechaDesde != null) {
			where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaDesde));
		}
		if(this.fechaHasta != null) {
			where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaHasta));
		}
		if(StringUtils.isNotBlank(this.codigo)) {
			where.add(cb.like(poll.<String>get("codigo"), "%" + this.codigo + "%"));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
