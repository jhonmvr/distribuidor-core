package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.Canton;
import com.relative.midas.model.TbMiCliente;

public class ClienteByParamsSpec extends AbstractSpecification<TbMiCliente> {
	
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String celular;
	private String correo;
	private String sector;
	private String ciudad;
	private String nombreReferencia;
	private String telefonoReferencia;
	private String celularReferencia;
	private EstadoMidasEnum estado;
	
	public ClienteByParamsSpec(String identificacion, String nombre, String apellido, String telefono, String celular,
			String correo, String secto, String ciudad, String nombreReferencia, String telefonoReferencia,
			String celularReferencia, EstadoMidasEnum estado) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
		this.sector = secto;
		this.ciudad = ciudad;
		this.nombreReferencia = nombreReferencia;
		this.telefonoReferencia = telefonoReferencia;
		this.celularReferencia = celularReferencia;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbMiCliente tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.like(poll.<String>get("identificacion"), "%" + this.identificacion + "%"));
		}
		if(StringUtils.isNotBlank(this.nombre)) {
			where.add(cb.like(poll.<String>get("nombre"), "%" + this.nombre + "%"));
		}
		if(StringUtils.isNotBlank(this.apellido)) {
			where.add(cb.like(poll.<String>get("apellido"), "%" + this.apellido + "%"));
		}
		if(StringUtils.isNotBlank(this.telefono)) {
			where.add(cb.like(poll.<String>get("telefonoFijo"), "%" + this.telefono + "%"));
		}
		if(StringUtils.isNotBlank(this.celular)) {
			where.add(cb.like(poll.<String>get("telefonoCelular"), "%" + this.celular + "%"));
		}
		if(StringUtils.isNotBlank(this.correo)) {
			where.add(cb.like(poll.<String>get("email"), "%" + this.correo + "%"));
		}
		if(StringUtils.isNotBlank(this.sector)) {
			where.add(cb.like(poll.<String>get("sector"), "%" + this.sector + "%"));
		}
		if(StringUtils.isNotBlank(this.ciudad)) {
			where.add(cb.like(poll.<Canton>get("canton").<String>get("cantonnom"), "%" + this.ciudad + "%"));
		}
		if(StringUtils.isNotBlank(this.nombreReferencia)) {
			where.add(cb.like(poll.<String>get("nombreReferencia"), "%" + this.nombreReferencia + "%"));
		}
		if(StringUtils.isNotBlank(this.telefonoReferencia)) {
			where.add(cb.like(poll.<String>get("telefonoReferencia"), "%" + this.telefonoReferencia + "%"));
		}
		if(StringUtils.isNotBlank(this.celularReferencia)) {
			where.add(cb.like(poll.<String>get("celularReferencia"), "%" + this.celularReferencia + "%"));
		}
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoMidasEnum>get("estado"), this.estado));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
