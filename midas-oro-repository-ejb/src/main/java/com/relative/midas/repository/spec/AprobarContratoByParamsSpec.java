package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.model.TbMiCliente;

public class AprobarContratoByParamsSpec extends AbstractSpecification<TbMiAprobarContrato>{
	private String identificacion;
	private  EstadoAprobacionEnum estado;
	private String nivelAprobacion;
	private String rolAprobacion;
	private String rolAprobacionDos;
	private String usuarioCreacion;

	public AprobarContratoByParamsSpec(String identificacion,EstadoAprobacionEnum estado, String nivelAprobacion, 
			String rolAprobacion,String rolAprobacionDos,String usuarioCreacion) {
		this.identificacion= identificacion;
		this.estado=estado;
		this.nivelAprobacion=nivelAprobacion;
		this.rolAprobacion=rolAprobacion;
		this.rolAprobacionDos=rolAprobacionDos;
		this.usuarioCreacion=usuarioCreacion;
	}

	@Override
	public boolean isSatisfiedBy(TbMiAprobarContrato t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAprobarContrato> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if(StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.like(poll.<TbMiCliente>get("tbMiCliente").<String>get("identificacion"), "%" + this.identificacion + "%"));
		}
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoAprobacionEnum>get("estado"), this.estado));
		}
		if(StringUtils.isNotBlank(this.nivelAprobacion)) {
			where.add(cb.equal(poll.<String>get("nivelAprobacion"), this.nivelAprobacion));
		}
		if(StringUtils.isNotBlank(this.rolAprobacion)) {
			where.add(cb.equal(poll.<String>get("rolAprobacion"), this.rolAprobacion));
		}
		if(StringUtils.isNotBlank(this.rolAprobacionDos)) {
			where.add(cb.equal(poll.<String>get("rolAprobacionDos"), this.rolAprobacionDos));
		}
		if(StringUtils.isNotBlank(this.usuarioCreacion)) {
			where.add(cb.equal(poll.<String>get("usuarioCreacion"), this.usuarioCreacion));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
