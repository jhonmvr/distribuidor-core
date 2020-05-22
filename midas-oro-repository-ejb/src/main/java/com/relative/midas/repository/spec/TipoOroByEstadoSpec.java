package com.relative.midas.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoOro;

public class TipoOroByEstadoSpec extends AbstractSpecification<TbMiTipoOro> {
	 private EstadoMidasEnum estado;
	 
	 public TipoOroByEstadoSpec(EstadoMidasEnum estado) {
		 
		 this.estado = estado;
	 }

	 public TipoOroByEstadoSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiTipoOro arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbMiTipoOro> poll, CriteriaBuilder cb) {
			
			return  cb.and(
					cb.equal(poll.<EstadoMidasEnum>get("estado"),this.estado)
					);
		}
	
}
