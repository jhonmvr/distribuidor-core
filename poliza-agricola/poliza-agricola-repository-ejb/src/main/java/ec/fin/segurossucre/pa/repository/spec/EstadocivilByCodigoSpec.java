package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.Estadocivil;

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
