package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.model.TbPaCanalSecuencia;

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
