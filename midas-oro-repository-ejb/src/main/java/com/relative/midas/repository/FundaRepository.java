package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.TbMiFunda;
@Local
public interface FundaRepository extends CrudRepository<Long, TbMiFunda> {
	public TbMiFunda reservarFunda(Long idAgencia) throws RelativeException;

	public List<TbMiFunda> findByRango(Long idRango,  int page, int pageSize,String order, String direction) throws RelativeException;
	
	public List<TbMiFunda> findByRango(Long idRango) throws RelativeException;
	
	public Long countFindByRango(Long idRango) throws RelativeException;

	public Integer countFunda(Long idAgencia) throws RelativeException;

	

}
