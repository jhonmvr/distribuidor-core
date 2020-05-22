package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.Ramoplan;

public class RamoplanByCodigoSpec extends AbstractSpecification<Ramoplan> {

	private String codigo;

	public RamoplanByCodigoSpec(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean isSatisfiedBy(Ramoplan arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Ramoplan> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(cb.trim(poll.get("id").get("ramoplanid")), codigo.trim()));
	}
	
	
}
