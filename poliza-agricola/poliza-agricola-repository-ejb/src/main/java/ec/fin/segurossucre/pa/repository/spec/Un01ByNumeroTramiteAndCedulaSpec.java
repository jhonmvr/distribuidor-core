package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.Un01;

public class Un01ByNumeroTramiteAndCedulaSpec extends AbstractSpecification<Un01>{

	private String numeroTramite;
	private String cedula;
	
	
	public Un01ByNumeroTramiteAndCedulaSpec( ) {}
	
	public Un01ByNumeroTramiteAndCedulaSpec( String numeroTramite, String cedula ) {
		this.numeroTramite=numeroTramite;
		this.cedula=cedula;
	}

	@Override
	public boolean isSatisfiedBy(Un01 arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Un01> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal(cb.trim(poll.<String>get("un01Codigotramite")),this.numeroTramite.trim()),
		        cb.equal(cb.trim(poll.<String>get("un01Cedula")),this.cedula.trim())
		        //cb.ge( poll.get("fecha") , y)
		    );
	}
	
	
	
	
}