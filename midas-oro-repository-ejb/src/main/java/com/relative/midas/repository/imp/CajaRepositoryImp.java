package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.repository.CajaRepository;
import com.relative.midas.repository.spec.CajaByAgenciaId;

@Stateless(mappedName = "cajaRepository")
public class CajaRepositoryImp extends GeneralRepositoryImp<Long, TbMiCaja> implements CajaRepository{
	
	@Override
	public TbMiCaja findCajaByAgenciaId(Long idAgencia) throws RelativeException{
		List<TbMiCaja> tmp;
		
		try {
			tmp = this.findAllBySpecification(new CajaByAgenciaId(idAgencia));
			if(tmp != null && !tmp.isEmpty()) {				
					return tmp.get(0);				
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"NO SE PUEDE LEER LA INFORMACION DE LA CAJA");
		}
		return null;
		
	}
	
	
	
}
