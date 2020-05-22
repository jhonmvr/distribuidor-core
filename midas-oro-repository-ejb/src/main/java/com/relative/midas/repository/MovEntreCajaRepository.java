package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiMovEntreCaja;

@Local
public interface MovEntreCajaRepository extends CrudRepository<Long, TbMiMovEntreCaja> {
	/**
	 * Lista los Movimientos entre caja por agencia origen, agencia destino, fecha desde y fecha hasta en basea a fecha creacion
	 * @param pw
	 * @param idAgenciaOrigen
	 * @param idAgenciaDestino
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	List<TbMiMovEntreCaja> findByParams(PaginatedWrapper pw, Long idAgenciaOrigen, Date fechaDesde, Date fechaHasta) throws RelativeException;
	/**
	 * Elimina un movimiento entre caja
	 * @param id
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	void delete(Long id) throws RelativeException;
}
