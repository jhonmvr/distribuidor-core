package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiDetalleMeta;
import com.relative.midas.repository.DetalleMetaRepository;
import com.relative.midas.repository.spec.DetalleMetaByIdMetaSpec;
import com.relative.midas.util.MidasOroUtil;

@Stateless(mappedName = "metaRepository")
public class DetalleMetaRepositoryImp extends GeneralRepositoryImp<Long, TbMiDetalleMeta>
		implements DetalleMetaRepository {

	@Inject
	Logger log;
	
	
	@Override
	public BigDecimal valorContrato(Long idAgencia, EstadoContratoEnum tipo, Date fechaInicio, Date fechaFin)
			throws RelativeException {
		BigDecimal total = BigDecimal.ZERO;
		List<TbMiContrato> contratos = null;
		try {
			contratos = findContratoToMeta(idAgencia, tipo, fechaInicio, fechaFin);
			if (contratos != null && contratos.size() > 0) {
				for (TbMiContrato contrato : contratos) {
					total = total.add(contrato.getValorContrato());
				}
			}
			return total;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "CALCULAR EL VALOR DEL CONTRATO");
		}
	}

	/**
	 * Metodo que calcular el valor del los contratos por vencer si los contratos
	 * estan vigentes y la diferencia entre la fecha de vigencia y la fecha actual
	 * es menor a 30
	 */
	@Override
	public BigDecimal valorContratoPorVencer(Long idAgencia, Date fechaInicio, Date fechaFin,String diasGracia) throws RelativeException {
		BigDecimal total = BigDecimal.ZERO;
		List<TbMiContrato> contratos = null;
		Date fechaVencimiento = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(fechaVencimiento);
		c.add(Calendar.DATE, -Integer.parseInt(diasGracia));
		fechaVencimiento = c.getTime();
		log.info("<<<<<fecha de vencimiento: " + fechaVencimiento);
		try {
			contratos = findContratoToMeta(idAgencia, EstadoContratoEnum.VIGENTE, fechaInicio, fechaFin);
			if (contratos != null && contratos.size() > 0) {
				for (TbMiContrato contrato : contratos) {
					Long dias = MidasOroUtil.diasFecha(fechaVencimiento, contrato.getFechaVencimiento());
					log.info("<<<<<contrato.getFechaVencimiento(): " + contrato.getFechaVencimiento());
					log.info("<<<<<dias: " + dias);
					if (dias < 30 && dias > 0) {
						total = total.add(contrato.getValorContrato());
					}
				}
			}
			return total;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "CALCULAR EL VALOR DEL CONTRATO");
		}
	}

	/**
	 * Metodo que calcular el valor del los contratos vencidos si los contratos
	 * estan vigentes y la diferencia entre la fecha de vigencia y la fecha actual
	 * es 0 o menos
	 */
	@Override
	public BigDecimal valorContratoVencido(Long idAgencia, Date fechaInicio, Date fechaFin,String diasGracia) throws RelativeException {
		BigDecimal total = BigDecimal.ZERO;
		List<TbMiContrato> contratos = null;
		Date fechaVencimiento = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(fechaVencimiento);
		c.add(Calendar.DATE, -Integer.parseInt(diasGracia));
		fechaVencimiento = c.getTime();
		log.info("<<<<<fecha de vencimiento: " + fechaVencimiento);
		try {
			contratos = findContratoToMeta(idAgencia, EstadoContratoEnum.VIGENTE, fechaInicio, fechaFin);
			if (contratos != null && contratos.size() > 0) {
				for (TbMiContrato contrato : contratos) {
					Long dias = MidasOroUtil.diasFecha(fechaVencimiento, contrato.getFechaVencimiento());
					log.info("<<<<<contrato.getFechaVencimiento(): " + contrato.getFechaVencimiento());
					log.info("<<<<<dias: " + dias);
					if (dias <= 0) {
						total = total.add(contrato.getValorContrato());
					}
				}
			}
			return total;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "CALCULAR EL VALOR DEL CONTRATO");
		}
	}

	private List<TbMiContrato> findContratoToMeta(Long idAgencia, EstadoContratoEnum tipo, Date fechaInicio,
			Date fechaFin) {
		List<TbMiContrato> contratos;
		StringBuilder strQry = new StringBuilder(
				"select f from TbMiContrato f " + "where tbMiAgencia.id =:idAgencia and tipoCompra = 'CP' "
						+ "and  fechaVencimiento >= :fechaInicio and fechaVencimiento <= :fechaFin");

		if (tipo != null && tipo == EstadoContratoEnum.RENOVADO) {
			strQry.append(" and id_contrato_anterior is null ");
		} else {
			strQry.append(" and estado =:estado ");

		}
		Query q = this.getEntityManager().createQuery(strQry.toString());
		q.setParameter("idAgencia", idAgencia);
		q.setParameter("fechaInicio", fechaInicio);
		q.setParameter("fechaFin", fechaFin);
		if (tipo != null && tipo == EstadoContratoEnum.RENOVADO) {
			// strQry.append(" and id_contrato_anterior isnull ");
		} else {

			q.setParameter("estado", tipo);
		}
		contratos = q.getResultList();
		return contratos;
	}
	
	
	@Override
	public BigDecimal carteraNueva(Long idAgencia, Date fechaInicio, Date fechaFin) throws RelativeException {
		BigDecimal total = BigDecimal.ZERO;
		List<TbMiContrato> contratos = null;
		
		try {
			contratos = findContratoToMetaByFechaCreacion(idAgencia, EstadoContratoEnum.VIGENTE, fechaInicio, fechaFin);
			if (contratos != null && contratos.size() > 0) {
				for (TbMiContrato contrato : contratos) {
					total = total.add(contrato.getValorContrato());					
				}
			}
			return total;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "CALCULAR EL VALOR DEL CONTRATO");
		}
	}
	private List<TbMiContrato> findContratoToMetaByFechaCreacion(Long idAgencia, EstadoContratoEnum tipo, Date fechaInicio,
			Date fechaFin) {
		List<TbMiContrato> contratos;
		StringBuilder strQry = new StringBuilder(
				"select f from TbMiContrato f " + "where tbMiAgencia.id =:idAgencia and tipoCompra = 'CP' "
						+ "and  fechaCreacion >= :fechaInicio and fechaCreacion <= :fechaFin");

		if (tipo != null && tipo == EstadoContratoEnum.RENOVADO) {
			strQry.append(" and id_contrato_anterior is null ");
		} else {
			strQry.append(" and estado =:estado ");

		}
		Query q = this.getEntityManager().createQuery(strQry.toString());
		q.setParameter("idAgencia", idAgencia);
		q.setParameter("fechaInicio", fechaInicio);
		q.setParameter("fechaFin", fechaFin);
		if (tipo != null && tipo == EstadoContratoEnum.RENOVADO) {
			// strQry.append(" and id_contrato_anterior isnull ");
		} else {

			q.setParameter("estado", tipo);
		}
		contratos = q.getResultList();
		return contratos;
	}

	@Override
	public List<TbMiDetalleMeta> findByIdMeta(Long idMeta) throws RelativeException {
		
		try {
			
			return this.findAllBySpecification(new DetalleMetaByIdMetaSpec(idMeta));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR AL BUSCAR EL DETALLE DE LA META POR ID");
		}
	}

}
