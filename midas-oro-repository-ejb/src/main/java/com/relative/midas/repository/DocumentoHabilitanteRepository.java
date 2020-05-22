package com.relative.midas.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.wrapper.HabilitanteWrapper;

@Local
public interface DocumentoHabilitanteRepository extends CrudRepository<Long, TbMiDocumentoHabilitante> {

	public TbMiDocumentoHabilitante findByTipoDocumentoAndContrato( String codigoContrato, Long idTipoDocumento);
	
	public List<TbMiDocumentoHabilitante> findByContrato( String codigoContrato) throws RelativeException;
	
	public TbMiDocumentoHabilitante findByTipoDocumentoAndJoyaAndAbono( Long idJoya, Long idAbono,Long idVentaLote,Long idCorteCaja, Long idTipoDocumento);
	
	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndJoyaAndAbonos( Long idJoya, Long idAbono, Long idTipoDocumento)  throws RelativeException ;
	
	 List<HabilitanteWrapper> findByParams(PaginatedWrapper pw, String codigoContrato, String codigoJoya,
			String nombreCliente, String identificacionCliente, EstadoMidasEnum estado, Long tipoDocumento, Date fechaDesde, Date fechaHasta, Long idAgencia) throws RelativeException;
	
	Long countByParams(String codigoContrato, String codigoJoya, String nombreCliente, String identificacionCliente, 
			EstadoMidasEnum estado, Long tipoDocumento, Date fechaDesde, Date fechaHasta, Long idAgencia) throws RelativeException;

	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndIdVentaLote(Long idVentaLote, Long idTipoDocumento)throws RelativeException;

	public List<TbMiDocumentoHabilitante> findByTipoDocumentoAndIdCorteCaja(Long idCorteCaja, Long idTipoDocumento)throws RelativeException;
}
