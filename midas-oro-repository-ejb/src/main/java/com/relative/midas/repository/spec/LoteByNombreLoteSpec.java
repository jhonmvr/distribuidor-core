package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiTipoOro;

public class LoteByNombreLoteSpec extends AbstractSpecification<TbMiLote> {
	
	private String nombreLote;
	private String usuarioCreacion;
	private Long tipoOro;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<EstadoMidasEnum> estados;
	
	public LoteByNombreLoteSpec(String nombreLote, String usuarioCreacion, Long tipoOro,
			Date fechaDesde, Date fechaHasta, List<EstadoMidasEnum> estados) {
		super();
		this.nombreLote = nombreLote;
		this.usuarioCreacion = usuarioCreacion;
		this.tipoOro = tipoOro;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.estados = estados;
	}

	public boolean isSatisfiedBy(TbMiLote tb) {
		return false;
	}
	
	public Predicate toPredicate(Root<TbMiLote> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		
		if(StringUtils.isNotEmpty(this.nombreLote)) {
			patientLevelPredicates.add(cb.like(poll.get("nombreLote"), "%" + this.nombreLote + "%"));
		}				
		if(this.fechaHasta != null) {
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaHasta));
		}
		if(this.fechaDesde != null) {
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), this.fechaDesde));
		}
		if(StringUtils.isNotEmpty(this.usuarioCreacion)) {
			patientLevelPredicates.add(cb.equal(poll.<String>get("usuarioCreacion"), this.usuarioCreacion));
		}
		if(this.tipoOro != null) {
			patientLevelPredicates.add(cb.equal(poll.<TbMiTipoOro>get("tbMiTipoOro").<Long>get("id"), this.tipoOro));
		}
		if(this.estados != null && !this.estados.isEmpty()) {
			patientLevelPredicates.add(poll.get("estado").in(this.estados));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}	
	

}
