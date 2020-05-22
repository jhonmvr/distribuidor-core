package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiMovimientoCaja;

public class MovimientoCajaByMultiSpec extends AbstractSpecification<TbMiMovimientoCaja>{
	
	
	private String fechaDesde;
	private String fechaHasta;
	private String codigoContrato;
	private String identificacionCliente;
	private EstadoMidasEnum estado;
	
	public MovimientoCajaByMultiSpec(String fechaDesde, String fechaHasta, String codigoContrato,
			String identificacionCliente, EstadoMidasEnum estado) {
		super();
		
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigoContrato = codigoContrato;
		this.identificacionCliente = identificacionCliente;
		this.estado = estado;
	}
	
	@Override
	public boolean isSatisfiedBy(TbMiMovimientoCaja arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Predicate toPredicate(Root<TbMiMovimientoCaja> poll, CriteriaBuilder cb) {
		
		
		
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		
						
		if(StringUtils.isNotEmpty(this.fechaDesde) && StringUtils.isNotEmpty(this.fechaHasta) ) {
			@SuppressWarnings("deprecation")
			Date fechaH = new Date(this.fechaDesde);
			Date fechaD = new Date(this.fechaHasta);
			patientLevelPredicates.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaH));
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"), fechaD));
		}			
		if(StringUtils.isNotEmpty(this.codigoContrato)) {
			patientLevelPredicates.add(cb.equal(poll.<TbMiContrato>get("tbMiContrato").<Long>get("codigo"), Long.parseLong(this.codigoContrato) ));
		}
		if(StringUtils.isNotEmpty(this.identificacionCliente)) {
			patientLevelPredicates.add(cb.equal(poll.<TbMiCliente>get("bMiCliente").<Long>get("identificacion"), Long.parseLong(this.identificacionCliente) ));
		}
		patientLevelPredicates.add(cb.notEqual(poll.<FormaPagoEnum>get("formaPago"), FormaPagoEnum.EFECTIVO ));
		patientLevelPredicates.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}
	

	
}	
