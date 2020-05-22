package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.NotificacionEnum;
import com.relative.midas.model.TbMiNotificacion;

public class NotificacionByMultiSpec extends AbstractSpecification<TbMiNotificacion> {
	
	private NotificacionEnum tipo;
	private EstadoMidasEnum estado;
	private Long idAgencia;
	private Long idReferencia;

	public NotificacionByMultiSpec(NotificacionEnum tipo, EstadoMidasEnum estado, Long idAgencia,Long idReferencia) {
		super();
		this.tipo = tipo;
		this.estado = estado;
		this.idAgencia = idAgencia;
		this.idReferencia = idReferencia;
	}

	@Override
	public boolean isSatisfiedBy(TbMiNotificacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiNotificacion> poll, CriteriaBuilder cb) {
		
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

		if(tipo != null) {
			patientLevelPredicates.add(cb.equal(poll.<NotificacionEnum>get("tipo"),tipo));
		}
		if(estado != null) {
			patientLevelPredicates.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), estado));
		}
		
		if(idAgencia != null) {
			patientLevelPredicates.add(cb.equal(poll.get("tbMiAgencia").get("id"), idAgencia));
		}

		if(idReferencia != null) {
			patientLevelPredicates.add(cb.equal(poll.<Long>get("idReferencia"),idReferencia));
		}
		
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
