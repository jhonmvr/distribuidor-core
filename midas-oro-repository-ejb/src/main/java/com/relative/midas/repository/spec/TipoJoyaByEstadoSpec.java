package com.relative.midas.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoJoya;

public class TipoJoyaByEstadoSpec extends AbstractSpecification<TbMiTipoJoya> {
	 private EstadoMidasEnum estado;
	 
	 public TipoJoyaByEstadoSpec(EstadoMidasEnum estado) {
		 
		 this.estado = estado;
	 }

	 public TipoJoyaByEstadoSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiTipoJoya arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		
		
		@Override
		public Predicate toPredicate(Root<TbMiTipoJoya> poll, CriteriaBuilder cb) {
			return  cb.and(
					
					cb.equal(poll.<EstadoMidasEnum>get("estado"),this.estado)
					
					);
		}

}
