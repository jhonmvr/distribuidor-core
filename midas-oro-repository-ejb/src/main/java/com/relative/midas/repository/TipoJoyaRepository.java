package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoJoya;
@Local
public interface TipoJoyaRepository  extends CrudRepository<Long, TbMiTipoJoya>{

	    public List<TbMiTipoJoya> findByAll(EstadoMidasEnum estado)  throws RelativeException;
		public Long countfindByAll(EstadoMidasEnum estado) throws RelativeException;
		List<TbMiTipoJoya> findByAllPaged(EstadoMidasEnum estado, int page, int pageSize)throws RelativeException;
		public List<TbMiTipoJoya> findByDetalleEstado(String detalle, EstadoMidasEnum estado, int startRecord,
				Integer pageSize, String sortFields, String sortDirections)throws RelativeException;
		public List<TbMiTipoJoya> findByDetalleEstado(String detalle, EstadoMidasEnum estado)throws RelativeException;
		public Long countfindByDetalleEstado(String detalle, EstadoMidasEnum estado)throws RelativeException;

	}



