package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiContratoCalculo;
@Local
public interface ContratoCalculoRepository   extends CrudRepository<Long, TbMiContratoCalculo>{


	TbMiContratoCalculo findByIdContratoAndRubro(Long idContrato, String rubro) throws RelativeException ;

	public List<TbMiContratoCalculo> findByIdAprobarContrato(Long idAprobarContrato) throws RelativeException ;
	

}
