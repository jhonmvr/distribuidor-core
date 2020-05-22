package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Riego;

public class RiegoByCodigoSpec extends AbstractSpecification<Riego> {

	private String codigo;

	public RiegoByCodigoSpec(String codigo) {
		super();
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Riego arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Riego> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("riegocod")), codigo.trim()));
	}
	
}
