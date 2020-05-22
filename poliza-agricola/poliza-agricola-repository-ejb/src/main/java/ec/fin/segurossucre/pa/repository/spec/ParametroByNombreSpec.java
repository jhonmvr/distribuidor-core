package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.TbSaParametro;


public class ParametroByNombreSpec extends AbstractSpecification<TbSaParametro> {
private String nombre;
	
	public ParametroByNombreSpec () {}


	public ParametroByNombreSpec(String nombre) {
	
		this.nombre = nombre;
	}




	@Override
	public boolean isSatisfiedBy(TbSaParametro arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Predicate toPredicate(Root<TbSaParametro> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal(poll.<String>get("nombre"),this.nombre.trim())
		    );
	}
	

}
