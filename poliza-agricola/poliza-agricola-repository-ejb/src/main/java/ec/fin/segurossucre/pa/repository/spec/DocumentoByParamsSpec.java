package ec.com.def.pa.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import ec.com.def.core.persistence.AbstractSpecification;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;

public class DocumentoByParamsSpec extends AbstractSpecification<TbPaTipoDocumentoPoliza> {
	
	private String tipoDocumento;
	private Long id;
	private String tipoPlantilla;
	public DocumentoByParamsSpec(String tipoDocumento, Long id,String tipoPlantilla) {
		this.tipoDocumento=tipoDocumento;
		this.id=id;
		this.tipoPlantilla=tipoPlantilla;
		
	}

	@Override
	public boolean isSatisfiedBy(TbPaTipoDocumentoPoliza arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbPaTipoDocumentoPoliza> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();		
		if(StringUtils.isNotBlank(this.tipoDocumento)) {
			where.add(cb.equal(poll.<String>get("tipoDocumento"), this.tipoDocumento));
		}
		if(this.id != null) {
			where.add(cb.equal(poll.<Long>get("id"), this.id));
		}
		if(StringUtils.isNotBlank(this.tipoPlantilla)) {
			where.add(cb.equal(poll.<String>get("tipoPlantilla"), this.tipoPlantilla));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
