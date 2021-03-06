package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiParametro;


@Local
public interface ParametroRepository extends CrudRepository<Long, TbMiParametro>{

	
	/**
     * Metodo que busca el nombre de parametro
     * @param nombre
     * @return nombre
     */
	public TbMiParametro findByNombre( String nombre )throws RelativeException;
	
	public List<TbMiParametro> findByNombreAndTipoOrdered( String nombre, String tipo, Boolean ordered ) throws RelativeException;
	
	public List<TbMiParametro> findByParamPaged(String nombre, String tipo, EstadoMidasEnum estado, String caracteriticaUno, String caracteristicaDos, 
    		int page, int pageSize,String order, String direction) throws RelativeException;
	
	public Long countByParamPaged(String nombre, String tipo, EstadoMidasEnum estado, String caracteriticaUno, String caracteristicaDos) throws RelativeException;
}
