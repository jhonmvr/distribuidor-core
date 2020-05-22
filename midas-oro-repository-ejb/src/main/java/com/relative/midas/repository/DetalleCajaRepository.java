package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiDetalleCaja;

@Local
public interface DetalleCajaRepository extends CrudRepository<Long, TbMiDetalleCaja>{
		public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja) throws RelativeException;
		public Long countByIdCorteCaja(Long idCorteCaja) throws RelativeException;		
		public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja, int inicio, int tamanio) throws RelativeException;		
		public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja, int inicio, int tamanio, String sortField, String sortDirection  ) throws RelativeException;
		
		
}
