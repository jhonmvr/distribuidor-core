package com.relative.midas.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.wrapper.TrazabilidadWrapper;
@Local
public interface InventarioRepository extends CrudRepository<Long, TbMiInventario> {

	public TbMiInventario findByJoya(Long idJoya) throws RelativeException;
	/**
     * Metodo que cambia el estado del inventario pertenecientes a una joya
     * @param idJoya
     * @param estado
     * @param usuario
     * @return
     * @throws RelativeException
     * @author kevin chamorro
     */
	List<TbMiInventario> changeEstadoInventarioByIdJoya(Long idJoya, EstadoJoyaEnum estado, String usuario) throws RelativeException;
	public List<TrazabilidadWrapper> findTrazabilidad(Long idJoya) throws RelativeException;
	
}
