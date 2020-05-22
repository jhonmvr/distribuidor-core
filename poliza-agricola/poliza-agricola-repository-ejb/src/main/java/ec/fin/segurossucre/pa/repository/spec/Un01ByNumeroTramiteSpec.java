package ec.com.def.pa.repository.spec;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Un01;

public class Un01ByNumeroTramiteSpec extends AbstractSpecification<Un01>{

	private String numeroTramite;
	private Date fechaEmision;
	
	
	public Un01ByNumeroTramiteSpec( ) {}
	
	public Un01ByNumeroTramiteSpec( String numeroTramite, Date fechaEmision ) {
		this.numeroTramite=numeroTramite;
		this.fechaEmision=fechaEmision;
	}

	@Override
	public boolean isSatisfiedBy(Un01 arg0) {
		
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Un01> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal(cb.trim(poll.<String>get("un01Codigotramite")),this.numeroTramite.trim()),
		        cb.greaterThanOrEqualTo( poll.<Date>get("un01Un02Fchemi") , this.fechaEmision)
		    );
	}
	
	
	
	
}