package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiHistoricoJoya;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.repository.HistoricoJoyaRepository;
import com.relative.midas.repository.spec.ContratoCalculoByContratoAndRubroSpec;
import com.relative.midas.repository.spec.HistoricoByIdJoyaSpec;
import com.relative.midas.repository.spec.HistoricoByJoyaSpec;
/**
 * Session Bean implementation class HistoricoJoyaImp
 */
@Stateless(mappedName = "historicojoyaRepository")
public class HistoricoJoyaImp extends GeneralRepositoryImp<Long, TbMiHistoricoJoya> implements HistoricoJoyaRepository  {
	  /**
     * Default constructor. 
     */
    public HistoricoJoyaImp() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public TbMiHistoricoJoya findByIdJoya(Long idJoya, EstadoJoyaEnum estado) throws RelativeException {
		List<TbMiHistoricoJoya> tmp;
		try {
			
			tmp=this.findAllBySpecification(new HistoricoByJoyaSpec(idJoya,estado) );
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN HISTORICO");
		}
		return null;
	}
    
	@Override
	public List<TbMiHistoricoJoya> findByIdJoyaList(Long idJoya) throws RelativeException {
		List<TbMiHistoricoJoya> tmp;
		try {
			
			tmp=this.findAllBySpecification(new HistoricoByIdJoyaSpec(idJoya) );
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN HISTORICO");
		}
		return null;
	}
    
    
    
}


