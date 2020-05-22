package ec.com.def.pa.repository.imp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.TbSaAsegurado;

public class AseguradoByIdetificacionSpec extends AbstractSpecification<TbSaAsegurado> {
	private String identificacion;
	

	public AseguradoByIdetificacionSpec(String identificacion) {
		super();
		this.identificacion = identificacion;
	}

	@Override
	public boolean isSatisfiedBy(TbSaAsegurado arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbSaAsegurado> poll, CriteriaBuilder cb) {
		
		return cb.and(cb.equal(poll.get("identificacion"), this.identificacion.trim()));
	}

}