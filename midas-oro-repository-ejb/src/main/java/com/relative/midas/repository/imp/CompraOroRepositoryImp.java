package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiCompraOro;
import com.relative.midas.repository.CompraOroRepository;
import com.relative.midas.repository.spec.CompraOroByIdFundaSpec;
import com.relative.midas.repository.spec.CompraOroByQuilateAndFundaSpec;

@Stateless(mappedName = "compraOroRepositoryImp")
public class CompraOroRepositoryImp extends GeneralRepositoryImp<Long, TbMiCompraOro> implements CompraOroRepository{

	/**
	 * METODO QUE BUSCA LOS TIPOS DE ORO LIGADOS AL CONTRATO 
	 */
	@Override
	public List<TbMiCompraOro> listByIdFunda(Long idFunda) throws RelativeException {
		try {
			List<TbMiCompraOro> tmp;
			tmp = this.findAllBySpecification(new CompraOroByIdFundaSpec(idFunda));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM+"AL BUSCAR TIPO ORO LIGADO AL CONTRATO");
		}
	}

	@Override
	public TbMiCompraOro findByQuilateAndFunda(String quilate, Long idFunda) throws RelativeException {
		try {
			List<TbMiCompraOro> tmp;
			tmp = this.findAllBySpecification(new CompraOroByQuilateAndFundaSpec(quilate,idFunda));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM+"AL BUSCAR TIPO ORO:"+quilate+"funda"+idFunda);
		}
	}
	
}
