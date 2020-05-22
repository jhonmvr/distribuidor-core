package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiFundaRango;

public class FundaByIdRangoSpec extends AbstractSpecification<TbMiFunda>{
	Long idFundaRango;
	String codigo;
	String comentario;
	String estado;
	
	public FundaByIdRangoSpec(Long idFundaRango, String codigo, String comentario, String estado) {
		
		
		this.idFundaRango = idFundaRango;
		this.codigo = codigo==null?"":codigo;
		 this.comentario = comentario==null?"":comentario;
		 this.estado = estado==null?"":estado;
		 
	}

	@Override
	public boolean isSatisfiedBy(TbMiFunda arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiFunda> poll, CriteriaBuilder cb) {
		
		if( idFundaRango != null ) {
			return  cb.and(cb.equal( poll.<TbMiFundaRango>get("tbMifundaRango").<Long>get("id") ,this.idFundaRango ));
		} else {
			return  cb.and(
					cb.like(cb.trim(poll.<String>get("codigo")),"%"+this.codigo.trim()+"%"),
					cb.like(cb.trim( poll.<String>get("comentario")),"%"+this.comentario.trim()+"%"),
					cb.like(cb.trim(poll.<String>get("estado")),"%"+this.estado.trim()+"%")
					);
		}
		
		
	}
	

}
