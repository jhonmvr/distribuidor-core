package ec.fin.segurossucre.pa.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.TbSaUsuarioCanal;

public class UsuarioCanalByUsuarioSpec extends AbstractSpecification<TbSaUsuarioCanal>{

	private String nombreUsuario;
	
	public UsuarioCanalByUsuarioSpec () {}
	
	
	public UsuarioCanalByUsuarioSpec (String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	@Override
	public boolean isSatisfiedBy(TbSaUsuarioCanal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbSaUsuarioCanal> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal( cb.trim( poll.<String>get("nombreUsuario")),this.nombreUsuario.trim())//,
		    );
	}

}
