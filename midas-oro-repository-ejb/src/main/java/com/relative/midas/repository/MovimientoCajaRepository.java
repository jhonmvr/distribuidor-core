package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.wrapper.MovimientoCajaWrapper;
import com.relative.midas.wrapper.MovimientosDetalleCierreCajaWrapper;

@Local
public interface MovimientoCajaRepository extends CrudRepository<Long, TbMiMovimientoCaja>{
	
	public List<TbMiMovimientoCaja> findByParam(String fechaDesde, String fechaHasta, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado ) throws RelativeException ;
	
	public List<TbMiMovimientoCaja> findByContrato( long idContrato, ProcesoEnum proceso) throws RelativeException ;
	
	public List<TbMiMovimientoCaja> findByProceso(ProcesoEnum proceso) throws RelativeException ;
	
	public List<TbMiMovimientoCaja> findByParamPaged(String fechaDesde, String fechaHasta, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado, int inicio, int tamanio  ) throws RelativeException;
	
	public List<TbMiMovimientoCaja> findByParamPaged(String fechaDesde, String fechaHasta, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado, int inicio, int tamanio, String sortField, String sortDirection  ) throws RelativeException ;


	public Long countFindMovimientoCajaByParam(String fechaDesde, String fechaHasta, String codigoContrato,
			 String identificacionCliente, EstadoMidasEnum estado) throws RelativeException;
	
	List<TbMiMovimientoCaja> findByParams(PaginatedWrapper pw, EstadoMidasEnum estado, Date fechaDesde, 
			Date fechaHasta,String codigoContrato,String identificacionCliente,FormaPagoEnum formaPago, Long idAgencia)
					throws RelativeException;
	
	Long countByParams(EstadoMidasEnum estado, Date fechaDesde, 
			Date fechaHasta,String codigoContrato,String identificacionCliente,FormaPagoEnum formaPago, Long idAgencia) 
					throws RelativeException;

	public List<TbMiMovimientoCaja> findByVentaLote(Long idVentaLote)throws RelativeException;
	
	public List<MovimientosDetalleCierreCajaWrapper> filWrapperByCorteCaja(Long idCorteCaja) throws RelativeException;
	
	public List<MovimientoCajaWrapper> findByUsuarioAndCaja(PaginatedWrapper pw, String usuario, String idCaja) throws RelativeException;
	
	public Long countByUsuarioAndCaja( String usuario, String idCaja) throws RelativeException;
	
		
}
