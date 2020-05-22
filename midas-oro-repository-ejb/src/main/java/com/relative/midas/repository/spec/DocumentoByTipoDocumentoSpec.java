package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiTipoDocumento;



public class DocumentoByTipoDocumentoSpec  extends AbstractSpecification<TbMiTipoDocumento> {
	
	
 private String tipoDocumento;
 private String codigo;
 private Long idJoya;
 private Long idAbono;
	
	 
	 

	public DocumentoByTipoDocumentoSpec(String tipoDocumento, String codigo, Long idJoya, Long idAbono) {
		System.out.println("=====>DocumentoByTipoDocumentoSpec " + tipoDocumento + "  " + codigo );
		this.tipoDocumento = tipoDocumento;
		this.codigo = codigo;
		this.idJoya=idJoya;
		this.idAbono=idAbono;
	}

	public DocumentoByTipoDocumentoSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiTipoDocumento arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		
		
		@Override
		public Predicate toPredicate(Root<TbMiTipoDocumento> poll, CriteriaBuilder cb) {
			if( this.codigo != null && !this.codigo.isEmpty() ) {
				return this.getByContrato(poll, cb);
			} else if( this.idJoya != null ) {
				return this.getByJoya(poll, cb);
			} else if( this.idAbono != null ) {
				return this.getByAbono(poll, cb);
			} else if( this.tipoDocumento != null ) {
				return cb.and(cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento));
			}
			return null;
		}
		
		private Predicate getByContrato(Root<TbMiTipoDocumento> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiDocumentoHabilitantes",JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiContrato> joinC = joinTd.join("tbMiContrato",JoinType.LEFT);
			if( this.codigo != null && this.tipoDocumento== null  ) {
				return cb.and(cb.equal(cb.trim(joinC.<String>get("codigo")),this.codigo.trim())
				);
			} else if( this.codigo == null && this.tipoDocumento!= null  ) {
				return cb.and(cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento)
				);
			} else {
				return cb.and(
						cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento),
						cb.equal(cb.trim(joinC.<String>get("codigo")),this.codigo.trim())
				);
			}
		}
		
		private Predicate getByJoya(Root<TbMiTipoDocumento> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiDocumentoHabilitantes",JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiJoya> joinC = joinTd.join("tbMiJoya",JoinType.LEFT);
			if( this.idJoya != null && this.tipoDocumento== null  ) {
				return cb.and(cb.equal(joinC.<Long>get("id"),this.idJoya));
			} else if( this.codigo == null && this.tipoDocumento!= null  ) {
				return cb.and(cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento));
			} else {
				return cb.and(
						cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento),
						cb.equal(joinC.<Long>get("id"),this.idJoya)
				);
			}
		}
		
		private Predicate getByAbono(Root<TbMiTipoDocumento> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiDocumentoHabilitantes",JoinType.LEFT);
			Join<TbMiDocumentoHabilitante, TbMiAbono> joinC = joinTd.join("tbMiAbono",JoinType.LEFT);
			if( this.idJoya != null && this.tipoDocumento== null  ) {
				return cb.and(cb.equal(joinC.<Long>get("id"),this.idAbono));
			} else if( this.codigo == null && this.tipoDocumento!= null  ) {
				return cb.and(cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento));
			} else {
				return cb.and(
						cb.equal(cb.trim(poll.<String>get("tipoDocumento")),this.tipoDocumento),
						cb.equal(joinC.<Long>get("id"),this.idAbono)
				);
			}
		}
}
