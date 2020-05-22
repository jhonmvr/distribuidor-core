package ec.fin.segurossucre.pa.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.pa.model.TbPaSolicitudPoliza;
import ec.fin.segurossucre.pa.wrapper.ConsultaSolicitudPolizaWrapper;
@Local
public interface SolicitudPolizaRepository extends CrudRepository<Long, TbPaSolicitudPoliza> {

	BigDecimal generateCodigo(String anio) throws SegSucreException;

	BigDecimal generateNumeroTramite(String seq_canal)throws SegSucreException;
	/**
	 * Lista las solicitudes de poliza por los criterios de busqueda
	 * @param pw
	 * @param numeroSolicitud
	 * @param numeroTramite
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param canal
	 * @return
	 * @throws SegSucreException
	 * @author Saul Mendez
	 */
	List<ConsultaSolicitudPolizaWrapper> findByParams(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws SegSucreException;
	Integer countBySolicitudesPoliza(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws SegSucreException;
	List<ConsultaSolicitudPolizaWrapper> setWrapperSolicitudPoliza( String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws SegSucreException;

	void upDate() throws SegSucreException;

}
