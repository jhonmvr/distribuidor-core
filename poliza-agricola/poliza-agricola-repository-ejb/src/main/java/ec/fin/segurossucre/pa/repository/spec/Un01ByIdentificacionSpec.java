package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.Un01;

public class Un01ByIdentificacionSpec extends AbstractSpecification<Un01> {
	private String identificacion;

	public Un01ByIdentificacionSpec(String identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public boolean isSatisfiedBy(Un01 t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Un01> poll, CriteriaBuilder cb) {
		return cb.equal(cb.trim(poll.<String>get("un01Cedula") ), this.identificacion.trim());
	}

}
