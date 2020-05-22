package ec.fin.segurossucre.pa.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.fin.segurossucre.core.persistence.AbstractSpecification;
import ec.fin.segurossucre.pa.model.TbPaDocumentoPoliza;
import ec.fin.segurossucre.pa.model.TbPaSolicitudPoliza;
import ec.fin.segurossucre.pa.model.TbPaTipoDocumentoPoliza;

public class DocumentoPolizaByParamsSpec extends AbstractSpecification<TbPaDocumentoPoliza> {
	private Long idTipoDocumento;
	private Long idPoliza;
	

	public DocumentoPolizaByParamsSpec(Long idTipoDocumento, Long idPoliza) {
		this.idTipoDocumento = idTipoDocumento;
		this.idPoliza = idPoliza;
	}

	@Override
	public boolean isSatisfiedBy(TbPaDocumentoPoliza arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbPaDocumentoPoliza> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();		
		if(this.idTipoDocumento != null) {
			where.add(cb.equal(poll.<TbPaTipoDocumentoPoliza>get("tbPaTipoDocumentoPoliza").<Long>get("id"), this.idTipoDocumento));
		}
		if(this.idPoliza != null) {
			where.add(cb.equal(poll.<TbPaSolicitudPoliza>get("tbPaSolicitudPoliza").<Long>get("id"), this.idPoliza));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
