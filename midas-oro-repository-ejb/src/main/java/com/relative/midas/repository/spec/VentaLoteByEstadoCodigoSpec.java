package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiVentaLote;

public class VentaLoteByEstadoCodigoSpec extends AbstractSpecification<TbMiVentaLote> {
	
	private EstadoJoyaEnum estado;
	private String codigoVentaLote;
	private String identificacion;
	
	public VentaLoteByEstadoCodigoSpec(EstadoJoyaEnum estado, String codigoVentaLote,String identificacion) {
		this.estado = estado;
		this.codigoVentaLote = codigoVentaLote;
		this.identificacion = identificacion;
	}

	@Override
	public boolean isSatisfiedBy(TbMiVentaLote tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiVentaLote> poll, CriteriaBuilder cb) {
		
		List<Predicate> patientLevelPredicates = new ArrayList<>();
		if (estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), estado));
		}
		if (identificacion != null && StringUtils.isNotEmpty(identificacion)) {
			patientLevelPredicates.add(cb.equal(poll.get("tbMiCliente").get("identificacion"), identificacion));
		}
		if (codigoVentaLote != null && StringUtils.isNotEmpty(codigoVentaLote)) {
			patientLevelPredicates.add(cb.equal(poll.get("codigo"), codigoVentaLote));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
