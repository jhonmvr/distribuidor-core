package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.RamocanalPK;

public class RamoCanalByIdSpec extends AbstractSpecification<Ramocanal> {

	private String canalId;
	private String ramoId;
	
	
	public RamoCanalByIdSpec() {
		// TODO Auto-generated constructor stub
	}
	
	public RamoCanalByIdSpec( String canalId, String ramoId ) {
		this.canalId = canalId;
		this.ramoId=ramoId;
	}
	
	
	@Override
	public boolean isSatisfiedBy(Ramocanal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Ramocanal> poll, CriteriaBuilder cb) {
		if( this.ramoId != null && !this.ramoId.isEmpty() ) {
		return cb.and(
		        cb.equal( cb.trim(poll.get("id").<String>get( "ramoid" ) ),this.ramoId),
		        cb.equal( cb.trim(poll.get("id").<String>get( "canalid")),this.canalId)
		    );
		}else {
			return cb.and(
			        cb.equal( cb.trim(poll.<String>get("id.ramoid")),this.ramoId)
			    );
		}
	}

	
}