package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Condicionpredio;

public class CondicionpredioByCodigoSpec extends AbstractSpecification<Condicionpredio> {
	
	private String codigo;

	
	public CondicionpredioByCodigoSpec(String codigo) {
		super();
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Condicionpredio arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Condicionpredio> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("condicionprediocod")), codigo.trim()));
	}

}