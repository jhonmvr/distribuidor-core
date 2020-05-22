package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Acteco;

public class ActecoByCodigoSpec extends AbstractSpecification<Acteco> {
	
	private String actividadEconomica;
	

	public ActecoByCodigoSpec(String actividadEconomica) {
		
		this.actividadEconomica = actividadEconomica;
	}

	@Override
	public boolean isSatisfiedBy(Acteco arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Acteco> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("aeccod")), actividadEconomica.trim()));
	}

}
