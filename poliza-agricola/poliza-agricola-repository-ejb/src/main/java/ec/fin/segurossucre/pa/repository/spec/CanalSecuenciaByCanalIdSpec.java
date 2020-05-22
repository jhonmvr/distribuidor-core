package ec.com.def.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.RamocanalPK;
import ec.com.def.pa.model.TbPaCanalSecuencia;

public class CanalSecuenciaByCanalIdSpec extends AbstractSpecification<TbPaCanalSecuencia> {

	private RamocanalPK id;
	
	
	public CanalSecuenciaByCanalIdSpec(RamocanalPK id) {
		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(TbPaCanalSecuencia t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbPaCanalSecuencia> poll, CriteriaBuilder cb) {
		
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("ramocanal").get("id").get("ramoid"), this.id.getRamoid()),
				cb.equal(poll.get("ramocanal").get("id").get("canalid"), this.id.getCanalid()));
	}

}
