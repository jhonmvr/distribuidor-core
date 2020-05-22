package com.relative.midas.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiCuenta;

public class CuentaByCodigo extends AbstractSpecification<TbMiCuenta> {
	 private String codigo;
	 
	 public CuentaByCodigo(String codigo) {
		 
		 this.codigo = codigo==null?"":codigo;
	 }

	 public CuentaByCodigo() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiCuenta arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		
		
		@Override
		public Predicate toPredicate(Root<TbMiCuenta> poll, CriteriaBuilder cb) {
			return  cb.and(
					
					cb.equal(poll.<String>get("codigo"),this.codigo)
					
					);
		}

}
