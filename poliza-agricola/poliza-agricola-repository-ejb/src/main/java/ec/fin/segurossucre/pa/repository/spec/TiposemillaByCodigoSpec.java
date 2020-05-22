package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Tiposemilla;

public class TiposemillaByCodigoSpec extends AbstractSpecification<Tiposemilla> {

	private String codigo;

	public TiposemillaByCodigoSpec(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Tiposemilla arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Tiposemilla> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("tiposemillacod")), codigo.trim()));
	}
	
}
