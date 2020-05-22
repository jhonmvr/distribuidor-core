package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiVentaLote;

@Local
public interface VentaLoteRepository extends CrudRepository<Long, TbMiVentaLote>{
	/**
	 * Metodo para generar el codigo de venta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	String generarCodigoVenta(String seqVl) throws RelativeException;
	/**
	 * Metodo que retorna una lista de venta lote por fechaCreacion desde y hasta, ademas busca por codigo
	 * @param pw
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param codigoVenta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	List<TbMiVentaLote> findByFechasCodigo(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta, String codigoVenta) throws RelativeException;
	/**
	 * Metodo retorna TbMiLote relacionados con la TbMiVentaLote
	 * @param pw
	 * @param idVentaLote
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	List<TbMiLote> findLotesByIdVenta(PaginatedWrapper pw, Long idVentaLote) throws RelativeException;
	List<TbMiVentaLote> findByEstadoAndCodigo(int startRecord, Integer pageSize, String sortFields, String sortDirections,
			EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion) throws RelativeException;
	List<TbMiVentaLote> findByEstadoAndCodigo(EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion) throws RelativeException;
	Long countByEstadoAndCodigo(EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion) throws RelativeException;

}
