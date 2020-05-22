package com.relative.midas.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.repository.ContratoCalculoRepository;
import com.relative.midas.repository.spec.ContratoCalculoByContratoAndRubroSpec;
import com.relative.midas.repository.spec.ContratoCalculoByIdAprobarContratoSpec;;

/**
 * Session Bean implementation class ContratoRepositoryImpl
 */
@Stateless(mappedName = "contratoRepository")
public class ContratoCalculoRepositoryImpl extends GeneralRepositoryImp<Long, TbMiContratoCalculo> implements ContratoCalculoRepository {
	@Inject
	Logger log;

	/**
	 * Default constructor.
	 */
	public ContratoCalculoRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TbMiContratoCalculo findByIdContratoAndRubro(Long idContrato, String rubro) throws RelativeException {
		try {
			List<TbMiContratoCalculo> tmp;
			tmp=this.findAllBySpecification(new ContratoCalculoByContratoAndRubroSpec(idContrato,rubro) );
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN CONTRATO CALCULO");
		}
		return null;
	}

	@Override
	public List<TbMiContratoCalculo> findByIdAprobarContrato(Long idAprobarContrato) throws RelativeException {
		try {
			List<TbMiContratoCalculo> tmp;
			tmp=this.findAllBySpecification(new ContratoCalculoByIdAprobarContratoSpec(idAprobarContrato) );
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN CONTRATO CALCULO");
		}
		return null;
	}

	
}
