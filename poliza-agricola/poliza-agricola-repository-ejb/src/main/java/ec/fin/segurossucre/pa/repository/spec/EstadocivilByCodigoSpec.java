package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Estadocivil;

public class EstadocivilByCodigoSpec extends AbstractSpecification<Estadocivil> {
	
	private String codigo;

	public EstadocivilByCodigoSpec(String codigo) {
		
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Estadocivil arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Estadocivil> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("estadocivilcod")), codigo.trim()));
	}
	

}
