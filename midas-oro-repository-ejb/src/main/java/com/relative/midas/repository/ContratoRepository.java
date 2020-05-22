package com.relative.midas.repository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.wrapper.RetazarContratos;
@Local
public interface ContratoRepository   extends CrudRepository<Long, TbMiContrato>{
	public List<TbMiContrato> findByIdentificacionCliente(String identificacion)  throws RelativeException;
	public List<TbMiContrato> findByIdCliente(Long idCliente)  throws RelativeException;
	
	public Long countFindByIdentificacionCliente(String identificacion) throws RelativeException;
	public Long countFindByIdCliente(Long idCliente) throws RelativeException;
	
	
	public List<TbMiContrato> findByIdentificacionClientePaged(String identificacion, int page, int pageSize, String order,
			String direction) throws RelativeException;
	public List<TbMiContrato> findByIdClientePaged(Long idCliente, int page, int pageSize, String order,
			String direction) throws RelativeException;
	
	public List<TbMiContrato> findByIdentificacionClientePaged(String identificacion, int page, int pageSize) throws RelativeException;
	public List<TbMiContrato> findByIdClientePaged(Long idCliente, int page, int pageSize) throws RelativeException;
	
	public List<TbMiContrato> findByTipoCompraIdAgencia(String tipoContrato, Long idAgencia) throws RelativeException;
	public Long CountFindByTipoCompraIdAgencia(String tipoContrato, Long idAgencia) throws RelativeException;

	public List<TbMiContrato> findPorCustomFilterContratos(PaginatedWrapper pw, String fechaDesde, String fechaHasta, String codigo,
			List<EstadoContratoEnum> estado, String identificacion, String cliente, boolean contratosVencidos, Long idAgencia) throws RelativeException;
	public BigDecimal sumMontoPorCustomFilterContratos( String fechaDesde, String fechaHasta, String codigo,
			List<EstadoContratoEnum> estado, String identificacion, String cliente, boolean contratosVencidos, Long idAgencia) throws RelativeException;
	public TbMiContrato findByCodigoContrato(String codigo) throws RelativeException;
	/**
	 * Lista contratos por agencia y dos estado en clase personalizada ContratosPerfecionados
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	List<RetazarContratos> findByAngenciaEstado(PaginatedWrapper pw, String idAgencia, EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException;
	/**
	 * Cuenta contratos por agencia y dos estado en clase personalizada ContratosPerfecionados
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	Integer countByAngenciaEstado(PaginatedWrapper pw, String idAgencia, EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException;
	public BigInteger generateSecuencia(String secuenciaAgencia) throws RelativeException;
	public Long validateControByIdCliente(Long idCliente, Date fechaInicio, Date fechaFin)throws RelativeException;
	public Long validateContratoByIdFunda(Long idFunda)throws RelativeException;
	public Long validateContratoByIdLiquidacion(Long idLiquidacion)throws RelativeException;
	
	/**
     * Busca un contrato por id contrato anterior
     * @param idContratoAnterior
     * @return
     * @throws RelativeException
     * @author Kevin Chamorro
     */
	TbMiContrato findByIdAnterior(Long idContratoAnterior) throws RelativeException;
	public TbMiContrato validatePerfeccionamiento(Long idContrato, Date fechaVigencia) throws RelativeException;
	

}
