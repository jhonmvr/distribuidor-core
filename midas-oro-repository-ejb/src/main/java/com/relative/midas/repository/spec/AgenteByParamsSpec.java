package com.relative.midas.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAgente;

public class AgenteByParamsSpec extends AbstractSpecification<TbMiAgente> {
	private String nombre;
	private String apellido;
	private String identificacion;
	public AgenteByParamsSpec(String nombre,String apellido,String identificacion){
		this.nombre=nombre;
		this.apellido=apellido;
		this.identificacion=identificacion;
	}
	@Override
	public boolean isSatisfiedBy(TbMiAgente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiAgente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank(nombre)) {
			where.add(cb.equal(poll.get("primerNombre"), this.nombre));
		}
		if(StringUtils.isNotBlank(apellido)) {
			where.add(cb.equal(poll.get("primerApellido"), this.apellido));
		}
		if(StringUtils.isNotBlank(identificacion)) {
			where.add(cb.equal(poll.get("identificacion"), this.identificacion));
		}
		
		return cb.and(where.toArray(new Predicate[]{}));
	}

	

}
