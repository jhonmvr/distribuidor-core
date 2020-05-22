package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiTipoDocumento;



public class DocumentoByTipoDocumentoAndContratoSpec  extends AbstractSpecification<TbMiDocumentoHabilitante> {
	
	
 private Long idTipoDocumento;
 private String codigo;
	 
	
	 
	 

	public DocumentoByTipoDocumentoAndContratoSpec(Long idTipoDocumento, String codigo) {
	
		this.idTipoDocumento = idTipoDocumento;
		this.codigo = codigo;
	}

	public DocumentoByTipoDocumentoAndContratoSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiDocumentoHabilitante arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		
		
		@Override
		public Predicate toPredicate(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiContrato> joinC = poll.join("tbMiContrato",JoinType.INNER);
			if( this.codigo != null && this.idTipoDocumento== null  ) {
				return cb.and(
						cb.equal(joinC.<Long>get("id"),Long.valueOf(this.codigo.trim() ) ) 
				);
			} else if( this.codigo == null && this.idTipoDocumento!= null  ) {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			} else {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento),
						cb.equal(joinC.<Long>get("id"), Long.valueOf(this.codigo.trim() ) )
				);
			}
		}
}
