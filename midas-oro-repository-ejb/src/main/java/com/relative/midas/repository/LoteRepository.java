package com.relative.midas.repository;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiLote;

@Local
public interface LoteRepository extends CrudRepository<Long, TbMiLote> {
	
	public List<TbMiLote> findByParams(String nombreLote, Date fechaDesde, Date fechaHasta,
			String usuarioCreacion, Long tipoOro, List<EstadoMidasEnum> estados, 
			PaginatedWrapper pw) throws RelativeException;
	
	public List<TbMiLote> findByVentaLoteId(Long idVentaLote) throws RelativeException;
}
