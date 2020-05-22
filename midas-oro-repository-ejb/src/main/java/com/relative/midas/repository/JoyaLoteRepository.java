package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiJoyaLote;

@Local
public interface JoyaLoteRepository extends CrudRepository<Long, TbMiJoyaLote> {
	
	List<TbMiJoyaLote> findJoyaLoteByIdLote(Long idLote) throws RelativeException;
	
	void deleteByJoya(Long idJoya) throws RelativeException;

	public TbMiJoyaLote findByIdJoya(Long idJoya)throws RelativeException;

	public List<TbMiJoyaLote> findByIdLote(int startRecord, Integer pageSize, String sortFields, String sortDirections,
			Long idLote)throws RelativeException;

	public List<TbMiJoyaLote> findByIdLote(Long idLote)throws RelativeException;

	public Long countByIdLote(Long idLote)throws RelativeException;


}
