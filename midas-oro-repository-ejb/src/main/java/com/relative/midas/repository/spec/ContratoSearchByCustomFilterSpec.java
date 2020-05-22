package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;

public class ContratoSearchByCustomFilterSpec extends AbstractSpecification<TbMiContrato> {
	
	private String fechaDesde;
	private String fechaHasta;
	private String codigoOperacion;
	private List<EstadoContratoEnum> estadoContrato;
	private String identificacion;
	private Boolean todosContratosVencidos;
	private Long idAgencia;
	private String cliente;

	public ContratoSearchByCustomFilterSpec(String fechaDesde, String fechaHasta, String codigoOperacion,
			List<EstadoContratoEnum> estadoContrato, String identificacion, String cliente, Boolean todosContratosVencidos, Long idAgencia) {
		super();
		
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigoOperacion = codigoOperacion;
		this.estadoContrato = estadoContrato;
		this.identificacion = identificacion;
		this.todosContratosVencidos = todosContratosVencidos;
		this.idAgencia = idAgencia;
		this.cliente = cliente;
		
	}

	@Override
	public boolean isSatisfiedBy(TbMiContrato arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Query consulta en TbMiContrato con los parametros especificados
	 * 
	 *
	 */
	@Override
	public Predicate toPredicate(Root<TbMiContrato> contrato, CriteriaBuilder cb) {
		
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		

		
		if(StringUtils.isNotBlank(this.fechaDesde)) {
			@SuppressWarnings("deprecation")
			Date fecha = new Date(this.fechaDesde);
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(contrato.<Date>get("fechaCreacion"), fecha));
		}
		
		if(StringUtils.isNotBlank(this.fechaHasta)) {
			@SuppressWarnings("deprecation")
			Date fecha = new Date(this.fechaHasta);
			patientLevelPredicates.add(cb.lessThanOrEqualTo(contrato.<Date>get("fechaCreacion"), fecha));
		}
		
		if(StringUtils.isNotBlank(this.codigoOperacion)) {
			patientLevelPredicates.add(cb.like(contrato.<String>get("codigo"), "%" + this.codigoOperacion.trim() + "%"));
		}
		
		if(StringUtils.isNotBlank(this.identificacion)) {
			patientLevelPredicates.add(cb.like(contrato.<TbMiCliente>get("tbMiCliente").<String>get("identificacion"), "%" + this.identificacion.trim() + "%"));
		}
		if(StringUtils.isNotBlank(this.cliente)) {
			patientLevelPredicates.add(cb.or(cb.like(contrato.<TbMiCliente>get("tbMiCliente").<String>get("nombre"), "%" + this.cliente.trim() + "%"),
					cb.like(contrato.<TbMiCliente>get("tbMiCliente").<String>get("apellido"), "%" + this.cliente.trim() + "%"),
					cb.like(contrato.<TbMiCliente>get("tbMiCliente").<String>get("segundoNombre"), "%" + this.cliente.trim() + "%"),
					cb.like(contrato.<TbMiCliente>get("tbMiCliente").<String>get("segundoApellido"), "%" + this.cliente.trim() + "%")));
		}
		if(Boolean.TRUE.equals(todosContratosVencidos)) {
			patientLevelPredicates.add(contrato.<EstadoContratoEnum>get("estado").in(EstadoContratoEnum.VIGENTE,EstadoContratoEnum.VIGENTE_ADENDUM));
		}else if(this.estadoContrato != null && !this.estadoContrato.isEmpty()) {
			patientLevelPredicates.add(contrato.<EstadoContratoEnum>get("estado").in(this.estadoContrato));
		}
		if(this.idAgencia != null) {
			patientLevelPredicates.add(cb.equal(contrato.get("tbMiAgencia").get("id"), this.idAgencia));
		}
		
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
