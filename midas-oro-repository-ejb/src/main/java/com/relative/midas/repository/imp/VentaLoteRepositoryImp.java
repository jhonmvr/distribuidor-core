package com.relative.midas.repository.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiVentaLote;
import com.relative.midas.repository.VentaLoteRepository;
import com.relative.midas.repository.spec.LoteByIdVentaLoteSpec;
import com.relative.midas.repository.spec.VentaLoteByEstadoCodigoSpec;
import com.relative.midas.repository.spec.VentaLoteByFechaCodigoSpec;

@Stateless(mappedName = "ventaLoteRepository")
public class VentaLoteRepositoryImp extends GeneralRepositoryImp<Long, TbMiVentaLote> implements VentaLoteRepository {
	/**
	 * Metodo para generar el codigo de venta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public String generarCodigoVenta(String seqVl) throws RelativeException {
		try {
			Query query = this.getEntityManager().createNativeQuery("select nextval('" + seqVl + "')");
			BigInteger seq = (BigInteger) query.getSingleResult();
			String codigoVenta = "VL".concat("-")
					.concat(String.valueOf(new Date().getYear() + 1900))
					.concat("-").concat(StringUtils.leftPad(seq.toString(), 3, '0'));
			
//			codigoVenta = codigoVenta.concat("VL-");
//			Long numeroRegistros = this.countAll(TbMiVentaLote.class);
//			for (int i = numeroRegistros.toString().length(); i < 3; i++) {
//				codigoVenta = codigoVenta.concat("0");
//			}
//			codigoVenta = codigoVenta.concat(String.valueOf(numeroRegistros+1));
			
			return codigoVenta;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL, "Error al generar el codigo de venta " + e.getMessage());
		}
	}
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
	public List<TbMiVentaLote> findByFechasCodigo(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta, String codigoVenta) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new VentaLoteByFechaCodigoSpec(fechaDesde, fechaHasta, codigoVenta));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return findAllBySpecificationPaged(new VentaLoteByFechaCodigoSpec(
							fechaDesde, fechaHasta, codigoVenta), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecificationPaged(new VentaLoteByFechaCodigoSpec(
							fechaDesde, fechaHasta, codigoVenta), pw.getStartRecord(), pw.getPageSize());
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al buscar ventas de lote por filtro" + e.getMessage());
		}
	}
	/**
	 * Metodo retorna TbMiLote relacionados con la TbMiVentaLote
	 * @param pw
	 * @param idVentaLote
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiLote> findLotesByIdVenta(PaginatedWrapper pw, Long idVentaLote) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new LoteByIdVentaLoteSpec(idVentaLote));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return findAllBySpecificationPaged(new LoteByIdVentaLoteSpec(
							idVentaLote), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecificationPaged(new LoteByIdVentaLoteSpec(
							idVentaLote), pw.getStartRecord(), pw.getPageSize());
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al buscar ventas de lote por filtro" + e.getMessage());
		}
	}
	@Override
	public List<TbMiVentaLote> findByEstadoAndCodigo(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(new VentaLoteByEstadoCodigoSpec(pendienteHabilitante,codigoVentaLote,identificacion), startRecord, pageSize, sortFields, sortDirections);
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR VENTA LOTE POR ESTADO Y CODIGO");
		}
	}
	@Override
	public List<TbMiVentaLote> findByEstadoAndCodigo(EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion)
			throws RelativeException {
		try {
			return findAllBySpecification(new VentaLoteByEstadoCodigoSpec(pendienteHabilitante,codigoVentaLote,identificacion));
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR VENTA LOTE POR ESTADO Y CODIGO");
		}
	}
	@Override
	public Long countByEstadoAndCodigo(EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion)
			throws RelativeException {
		try {
			return countBySpecification(new VentaLoteByEstadoCodigoSpec(pendienteHabilitante,codigoVentaLote,identificacion));
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR VENTA LOTE POR ESTADO Y CODIGO");
		}
	}
}
