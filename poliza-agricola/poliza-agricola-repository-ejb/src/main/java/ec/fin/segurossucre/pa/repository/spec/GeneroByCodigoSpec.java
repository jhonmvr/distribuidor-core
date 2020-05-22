package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Genero;

public class GeneroByCodigoSpec extends AbstractSpecification<Genero> {
	private String codigo;

	public GeneroByCodigoSpec(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Genero arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Genero> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("generocod")), codigo.trim()));
	}
	

}
