package com.relative.midas.repository;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.wrapper.JoyaVentaVitrina;
import com.relative.midas.wrapper.RetazarJoya;

@Local
public interface JoyaRepository extends CrudRepository<Long, TbMiJoya>{
	//public TbMiJoya getJoyaByCodigoContrato(String codigo) throws RelativeException;

	public List<TbMiJoya> joyaByFundaId(Long idFunda) throws RelativeException;
	
	/**
	 * Actualiza el estado del tipo de joya por el estado de joya
	 * @param estado
	 * @param codigoContrato
	 * @return
	 */
	public  int updateBatch(EstadoJoyaEnum estado, Long idFunda) throws RelativeException;

	/**
	 * Metodo que retorna una clase personalizada con los datos para llenar el formulario Retazar y Trasladar
	 * @param codigoJoya
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<RetazarJoya> findByCodigoJoya(String codigoJoya) throws RelativeException ;
	
	public List<TbMiJoya> findByCodigoContratoPaged(PaginatedWrapper pw, String codigo) throws RelativeException;

	/**
	 * Metodo que retorna la cantidad joyas por codigo contrato
	 * 
	 * @param codigo
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	Long countByCodigoContrato(String codigo) throws RelativeException;
	/**
	 * Busca las joyas por estado obligatorio, estado movimiento inventario obligatorio, codigo joya opcional y tipo joya opcional.
	 * Ordena por fecha creacion movimiento por prioridad
	 * @param pw
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	List<JoyaVentaVitrina> findByEstadoMvCodigoTipo(
			PaginatedWrapper pw, EstadoJoyaEnum estadoJoya, EstadoJoyaEnum estadoMvIn, String codigoJoya, Long idTipoJoya) throws RelativeException;
	/**
	 * Cuenta las joyas por estado obligatorio, estado movimiento inventario obligatorio, codigo joya opcional y tipo joya opcional.
	 * @param pw
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	Integer countByEstadoMvCodigoTipo(
			PaginatedWrapper pw, EstadoJoyaEnum estadoJoya,EstadoJoyaEnum estadoMvIn, String codigoJoya, Long idTipoJoya) throws RelativeException;
	
	List<TbMiJoya> findByIdFunda(PaginatedWrapper pw, Long idFunda) throws RelativeException;
	Long countByIdFunda(Long idFunda) throws RelativeException;
	
	public List<TbMiJoya> findByCodigoJoyaEstadosFechas(PaginatedWrapper pw, String codigoJoya, List<EstadoJoyaEnum> estadosJoya, Date fechaDesde,
			Date fechaHasta) throws RelativeException;
	
	public Long countByCodigoJoyaEstadosFechas(String codigoJoya, List<EstadoJoyaEnum> estadosJoya, Date fechaDesde,
			Date fechaHasta) throws RelativeException ;

	public List<TbMiJoya> findByEstadoAndComprador (EstadoJoyaEnum estado, String identificacion) throws RelativeException;

	public List<TbMiJoya> findByEstadoAndComprador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, EstadoJoyaEnum pendienteHabilitante, String identificacion, String codigoJoya) throws RelativeException ;

	public List<TbMiJoya> findByEstadoAndComprador(EstadoJoyaEnum pendienteHabilitante, String identificacion,
			String codigoJoya) throws RelativeException ;

	public Long countByEstadoAndComprador(EstadoJoyaEnum pendienteHabilitante, String identificacion,
			String codigoJoya) throws RelativeException ;

	
	public List<TbMiJoya> findJoyaByParams(int startRecord, Integer pageSize, String sortFields, String sortDirections,
			List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya)throws RelativeException ;

	public List<TbMiJoya> findJoyaByParams(List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya)throws RelativeException ;

	public Long countJoyaByParams(List<EstadoJoyaEnum> estados, String codigoJoya, Long idTipoJoya)throws RelativeException ;

}





