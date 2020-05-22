package com.relative.midas.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiContactabilidad;

@Local
public interface ContactabilidadRepository extends CrudRepository<Long, TbMiContactabilidad> {
	
	List<TbMiContactabilidad> findByParams(PaginatedWrapper pw, Long idCliente, Long idAgente, Long idAgencia, Long idContrato,
			EstadoMidasEnum estado)  throws RelativeException;
	
	void delete(Long id) throws RelativeException;
}
