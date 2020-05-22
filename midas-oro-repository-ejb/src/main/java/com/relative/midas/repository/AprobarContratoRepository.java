package com.relative.midas.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiAprobarContrato;

@Local
public interface AprobarContratoRepository extends CrudRepository<Long, TbMiAprobarContrato>{

	public List<TbMiAprobarContrato> findAprobarContratoByParams(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String identificacion, EstadoAprobacionEnum estado, 
			String nivelAprobacion, String rolAprobacion,String rolAprobacionDos, String usuarioCreacion)throws RelativeException;

	public List<TbMiAprobarContrato> findAprobarContratoByParams(String identificacion, EstadoAprobacionEnum estado, 
			String nivelAprobacion, String rolAprobacion,String rolAprobacionDos, String usuarioCreacion)throws RelativeException;

	public Long countAprobarContratoByParams(String identificacion, EstadoAprobacionEnum estado, String nivelAprobacion, 
			String rolAprobacion,String rolAprobacionDos, String usuarioCreacion)throws RelativeException;

	public BigDecimal validarValorContratos(String identificacion, List<EstadoContratoEnum> estados) throws RelativeException;
	
	
}
