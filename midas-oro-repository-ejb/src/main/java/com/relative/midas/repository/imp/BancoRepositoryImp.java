package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.repository.BancoRepository;
import com.relative.midas.repository.spec.BancoByParamSpec;

@Stateless(mappedName = "abonoRepository")
public class BancoRepositoryImp extends GeneralRepositoryImp<Long, TbMiBanco> implements BancoRepository{

	@Override
	public List<TbMiBanco> findByParam(EstadoMidasEnum estado, String nombre, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			List<TbMiBanco> tmp ;
			tmp = this.findAllBySpecificationPaged(new BancoByParamSpec(estado,nombre), startRecord, pageSize, sortFields, sortDirections);
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR BANCOS POR PARAM");
		}
	}

	@Override
	public List<TbMiBanco> findByParam(EstadoMidasEnum estado, String nombre) throws RelativeException {
		try {
			List<TbMiBanco> tmp ;
			tmp = this.findAllBySpecification(new BancoByParamSpec(estado,nombre));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR BANCOS POR PARAM");
		}
	}

	@Override
	public Long countByParam(EstadoMidasEnum estado, String nombre) throws RelativeException {
		// TODO Auto-generated method stub
		return this.countBySpecification(new BancoByParamSpec(estado,nombre));
	}
}
