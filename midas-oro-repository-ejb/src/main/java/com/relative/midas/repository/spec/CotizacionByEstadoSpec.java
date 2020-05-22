package com.relative.midas.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCotizacion;

public class CotizacionByEstadoSpec  extends AbstractSpecification<TbMiCotizacion>{
 private EstadoMidasEnum estado;
 
 public CotizacionByEstadoSpec(EstadoMidasEnum estado) {
	 
	 this.estado = estado;
 }

 public CotizacionByEstadoSpec() {}
 
	@Override
	public boolean isSatisfiedBy(TbMiCotizacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public Predicate toPredicate(Root<TbMiCotizacion> poll, CriteriaBuilder cb) {
		
		return  cb.and(
				cb.equal(poll.<EstadoMidasEnum>get("estado"),this.estado)
				);
	}

}
	
	


