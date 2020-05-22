package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Ramocanal;

public class RamocanalByCodigoSpec extends AbstractSpecification<Ramocanal> {

	private String canal;
	
	public RamocanalByCodigoSpec(String canal) {
		
		this.canal = canal;
	}

	@Override
	public boolean isSatisfiedBy(Ramocanal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Ramocanal> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("id").get("canalid")), canal.trim()));
	}

}
