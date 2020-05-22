package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiTipoDocumento;
import com.relative.midas.model.TbMiVentaLote;



public class DocumentoByTipoDocumentoAndJoyaAndAbonoSpec  extends AbstractSpecification<TbMiDocumentoHabilitante> {
	
	
 private Long idTipoDocumento;
 private Long idJoya;
 private Long idAbono;	
 private Long idVentaLote;
 private Long idCorteCaja;
	
	 
	 

	public DocumentoByTipoDocumentoAndJoyaAndAbonoSpec(Long idTipoDocumento, Long idJoya, Long idAbono, Long idVentaLote,Long idCorteCaja) {
	
		this.idTipoDocumento = idTipoDocumento;
		this.idJoya = idJoya;
		this.idAbono=idAbono;
		this.idVentaLote=idVentaLote;
		this.idCorteCaja=idCorteCaja;
	}

	public DocumentoByTipoDocumentoAndJoyaAndAbonoSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbMiDocumentoHabilitante arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			if( this.idJoya != null  ) {
				return getByJoya( poll,cb );
			} else if( this.idAbono != null ) {
				return getByAbono( poll,cb );
			} else if( this.idVentaLote != null ) {
				return getByVentaLote( poll,cb );
			} else if( this.idCorteCaja != null ) {
				return getByCorteCaja( poll,cb );
			} else {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			}
		}
		
		

		public Predicate getByJoya(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiJoya> joinC = poll.join("tbMiJoya",JoinType.INNER);
			if( this.idJoya != null && this.idTipoDocumento== null  ) {
				return cb.and(
						cb.equal(joinC.<Long>get("id"),this.idJoya)
				);
			} else if( this.idJoya == null && this.idTipoDocumento!= null  ) {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			} else {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento),
						cb.equal(joinC.<Long>get("id"),this.idJoya)
				);
			}
		}
		
		public Predicate getByAbono(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiAbono> joinC = poll.join("tbMiAbono",JoinType.INNER);
			if( this.idAbono != null && this.idTipoDocumento== null  ) {
				return cb.and(
						cb.equal(joinC.<Long>get("id"),this.idAbono)
				);
			} else if( this.idAbono == null && this.idTipoDocumento!= null  ) {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			} else {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento),
						cb.equal(joinC.<Long>get("id"),this.idAbono)
				);
			}
		}
		
		public Predicate getByVentaLote(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiVentaLote> joinC = poll.join("tbMiVentaLote",JoinType.INNER);
			if( this.idVentaLote != null && this.idTipoDocumento== null  ) {
				return cb.and(
						cb.equal(joinC.<Long>get("id"),this.idVentaLote)
				);
			} else if( this.idVentaLote == null && this.idTipoDocumento!= null  ) {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			} else {
				return cb.and(
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento),
						cb.equal(joinC.<Long>get("id"),this.idVentaLote)
				);
			}
		}
		
		private Predicate getByCorteCaja(Root<TbMiDocumentoHabilitante> poll, CriteriaBuilder cb) {
			Join<TbMiDocumentoHabilitante, TbMiTipoDocumento> joinTd = poll.join("tbMiTipoDocumento",JoinType.INNER);
			Join<TbMiDocumentoHabilitante, TbMiVentaLote> joinC = poll.join("tbMiCorteCaja",JoinType.INNER);
			if( this.idTipoDocumento!= null  ) {
				return cb.and(
						cb.equal(joinC.<Long>get("id"),this.idCorteCaja),
						cb.equal(joinTd.<Long>get("id"),this.idTipoDocumento)
				);
			}else
			{
				return cb.and(cb.equal(joinC.<Long>get("id"),this.idCorteCaja));
			}
		}
}
