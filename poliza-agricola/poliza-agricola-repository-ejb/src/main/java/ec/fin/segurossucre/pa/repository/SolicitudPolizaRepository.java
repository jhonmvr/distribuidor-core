package ec.com.def.pa.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.pa.model.TbPaSolicitudPoliza;
import ec.com.def.pa.wrapper.ConsultaSolicitudPolizaWrapper;
@Local
public interface SolicitudPolizaRepository extends CrudRepository<Long, TbPaSolicitudPoliza> {

	BigDecimal generateCodigo(String anio) throws DefException;

	BigDecimal generateNumeroTramite(String seq_canal)throws DefException;
	/**
	 * Lista las solicitudes de poliza por los criterios de busqueda
	 * @param pw
	 * @param numeroSolicitud
	 * @param numeroTramite
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param canal
	 * @return
	 * @throws DefException
	 * @author Saul Mendez
	 */
	List<ConsultaSolicitudPolizaWrapper> findByParams(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException;
	Integer countBySolicitudesPoliza(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException;
	List<ConsultaSolicitudPolizaWrapper> setWrapperSolicitudPoliza( String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException;

	void upDate() throws DefException;

}
