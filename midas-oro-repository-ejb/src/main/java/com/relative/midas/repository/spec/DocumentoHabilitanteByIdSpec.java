package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiTipoDocumento;



public class DocumentoHabilitanteByIdSpec  extends AbstractSpecification<TbMiDocumentoHabilitante> {
	
	
 private Long idHabilitante;
	 
	 public DocumentoHabilitanteByIdSpec(Long idHabilitante) {
		 
		 this.idHabilitante = idHabilitante;
	 }

	 
	 

	 public DocumentoHabilitanteByIdSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiDocumentoHabilitante arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		
		
		@Override
		public Predicate toPredicate(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			return  cb.and(
					
					cb.equal((poll.<Long>get("id")),this.idHabilitante)
					
					);
		}		
}
