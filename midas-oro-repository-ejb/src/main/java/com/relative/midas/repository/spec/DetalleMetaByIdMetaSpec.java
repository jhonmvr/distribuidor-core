package com.relative.midas.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.midas.model.TbMiDetalleMeta;

public class DetalleMetaByIdMetaSpec extends AbstractSpecification<TbMiDetalleMeta>{
	
	private Long idMeta;
	
	public DetalleMetaByIdMetaSpec(Long idMeta) {
		this.idMeta = idMeta;
	}

	@Override
	public boolean isSatisfiedBy(TbMiDetalleMeta arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiDetalleMeta> poll, CriteriaBuilder cb) {
		return cb.and( cb.equal(poll.get("tbMiMeta").get("id"), this.idMeta) );
	}

}
