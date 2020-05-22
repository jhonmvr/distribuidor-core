package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Apol;

public class ApolByNumeroTramiteSpec extends AbstractSpecification<Apol>{

	private String numeroTramite;
	
	
	public ApolByNumeroTramiteSpec( ) {}
	
	public ApolByNumeroTramiteSpec( String numeroTramite ) {
		this.numeroTramite=numeroTramite;
	}
	
	@Override
	public boolean isSatisfiedBy(Apol arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Apol> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal(cb.trim(poll.<String>get("awreferext")),this.numeroTramite.trim())//,
		        //cb.ge( poll.get("fecha") , y)
		    );
	}

	
	
}
