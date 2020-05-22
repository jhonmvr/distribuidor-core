package com.relative.midas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiDetalleMeta;
import com.relative.midas.model.TbMiMeta;
import com.relative.midas.repository.AgenciaRepository;
import com.relative.midas.repository.DetalleMetaRepository;
import com.relative.midas.repository.MetaRepository;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;

@Stateless
public class MetaOroService {
	@Inject
	Logger log;
	@Inject
	private DetalleMetaRepository detalleMetaRepository;
	@Inject
	private MetaRepository metaRepository;
	@Inject
	private AgenciaRepository agenciaRepository;
	@Inject
	private MidasOroService mos;

	public List<TbMiDetalleMeta> calcularMeta(BigDecimal porcentajeComercial, BigDecimal porcentajeGerencial,
			Date fechaCierre, String usuario, String nombreMeta) throws RelativeException {
		Date fechaInicio;
		List<TbMiDetalleMeta> detalleMeta = new ArrayList<TbMiDetalleMeta>();
		try {

			List<TbMiAgencia> listAgencia = agenciaRepository.findByEstado(EstadoMidasEnum.ACT);
			List<TbMiMeta> metaActiva = metaRepository.metaActiva(null);
			if (metaActiva != null && metaActiva.size() > 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(metaActiva.get(0).getFechaCierre());
				c.add(Calendar.DATE, 1);
				fechaInicio = c.getTime();
				;
			} else {
				List<TbMiContrato> primeroContrato = mos
						.findAllContrato(new PaginatedWrapper(0, 5, "fechaCreacion", "asc", "Y"));
				if (primeroContrato != null && primeroContrato.size() > 0) {
					fechaInicio = primeroContrato.get(0).getFechaCreacion();
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO EXISTEN CONTRATOS");
				}
			}
			log.info(">>>>porcentajeComercial: " + porcentajeComercial);
			log.info(">>>>porcentajeGerencial: " + porcentajeGerencial);
			log.info(">>>>fechaCierre: " + fechaCierre);
			log.info(">>>>usuario: " + usuario);
			log.info(">>>>nombreMeta: " + nombreMeta);
			log.info(">>>>fechaInicio: " + fechaInicio);
			for (TbMiAgencia a : listAgencia) {
				TbMiDetalleMeta metaAgencia = new TbMiDetalleMeta();
				metaAgencia.setCarteraCancelada(detalleMetaRepository.valorContrato(a.getId(),
						EstadoContratoEnum.CANCELADO, fechaInicio, fechaCierre));
				// metaAgencia.setCarteraNueva(detalleMetaRepository.valorContrato(a.getId(),
				// EstadoContratoEnum.VIGENTE, fechaInicio, fechaCierre));
				metaAgencia.setCarteraPerfeccionada(detalleMetaRepository.valorContrato(a.getId(),
						EstadoContratoEnum.PERFECCIONADO, fechaInicio, fechaCierre));
				metaAgencia.setCarteraPorVencer(
						detalleMetaRepository.valorContratoPorVencer(a.getId(), fechaInicio, fechaCierre,
								this.mos.findParametroByParam(MidasOroConstantes.DIAS_DE_GRACIA, null, null, null, null, null).get(0).getValor()));
				metaAgencia.setCarteraRenovada(detalleMetaRepository.valorContrato(a.getId(),
						EstadoContratoEnum.RENOVADO, fechaInicio, fechaCierre));
				metaAgencia.setCarteraTotal(detalleMetaRepository.valorContrato(a.getId(), EstadoContratoEnum.VIGENTE,
						fechaInicio, fechaCierre));
				metaAgencia.setCarteraVencida(
						detalleMetaRepository.valorContratoVencido(a.getId(), fechaInicio, fechaCierre,
								this.mos.findParametroByParam(MidasOroConstantes.DIAS_DE_GRACIA, null, null, null, null, null).get(0).getValor()));
				metaAgencia.setCumple(StringUtils.SPACE);
				metaAgencia.setEstado(EstadoMidasEnum.ACT);
				metaAgencia.setFechaCierre(fechaCierre);
				metaAgencia.setFechaInicio(fechaInicio);
				metaAgencia.setMetaComercial(
						metaAgencia.getCarteraTotal().multiply(porcentajeComercial).divide(new BigDecimal(100)));
				metaAgencia.setMetaGerencial(
						metaAgencia.getCarteraTotal().multiply(porcentajeGerencial).divide(new BigDecimal(100)));
				metaAgencia.setPorcentajeComercial(porcentajeComercial);
				metaAgencia.setPorcentajeGerencial(porcentajeGerencial);
				metaAgencia.setTbMiAgencia(a);
				metaAgencia.setUsuarioCreacion(usuario);
				detalleMeta.add(metaAgencia);

			}
			
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw e;
		}
		return detalleMeta;
	}

	public TbMiMeta guardarMeta(TbMiMeta entidad, List<TbMiDetalleMeta> metas) throws RelativeException {
		try {
			if (entidad != null && metas != null && metas.size() > 0) {
				List<TbMiMeta> metaActiva = metaRepository.metaActiva(EstadoMidasEnum.ACT);
				// la fecha de cierre no puede ser menor que la fecha de inicio
				if(metaActiva != null && metaActiva.size() > 0) {				
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							"PRIMERO DEBE CERRAR LA META ANTERIOR, FECHA DE CIERRE: " +metaActiva.get(0).getFechaCierre());
				}
				if (MidasOroUtil.diasFecha(entidad.getFechaInicio(), entidad.getFechaCierre()) <= 0) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							"LA FECHA DE INICIO, NO PUEDE SER MENOR QUE LA FECHA DE CIERE DE LA META");
				}
				entidad.setEstado(EstadoMidasEnum.ACT);
				TbMiMeta tbMiMeta = this.mos.manageMeta(entidad);
				for (TbMiDetalleMeta m : metas) {
					m.setEstado(EstadoMidasEnum.ACT);
					m.setTbMiMeta(tbMiMeta);
					this.mos.manageDetalleMeta(m);
				}
				return tbMiMeta;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUDE LEER LA INFORMACION DE LAS METAS");
			}

		} catch (Exception e) {
			throw e;
		}
	
	}

	public List<TbMiDetalleMeta> detalleMeta(TbMiMeta entidad, String usuario ) throws RelativeException {
		
		try {
			List<TbMiDetalleMeta> detalleMeta = detalleMetaRepository.findByIdMeta(entidad.getId());			
			log.info(">>>>porcentajeComercial: " + entidad.getPorcentajeComercial());
			log.info(">>>>porcentajeGerencial: " + entidad.getPorcentajeGerencial());
			log.info(">>>>fechaCierre: " + entidad.getFechaCierre());
			log.info(">>>>usuario: " + usuario);
			log.info(">>>>nombreMeta: " + entidad.getNombre());
			log.info(">>>>fechaInicio: " + entidad.getFechaInicio());
			for (TbMiDetalleMeta metaPorAgencia : detalleMeta) {
				metaPorAgencia.setCarteraCancelada(detalleMetaRepository.valorContrato(metaPorAgencia.getTbMiAgencia().getId(),
						EstadoContratoEnum.CANCELADO, entidad.getFechaInicio(), entidad.getFechaCierre()));
				metaPorAgencia.setCarteraNueva(detalleMetaRepository.carteraNueva(metaPorAgencia.getTbMiAgencia().getId(),
						 entidad.getFechaInicio(), entidad.getFechaCierre()));
				metaPorAgencia.setCarteraPerfeccionada(detalleMetaRepository.valorContrato(metaPorAgencia.getTbMiAgencia().getId(),
						EstadoContratoEnum.PERFECCIONADO, entidad.getFechaInicio(), entidad.getFechaCierre()));
				metaPorAgencia.setCarteraPorVencer(
						detalleMetaRepository.valorContratoPorVencer(metaPorAgencia.getTbMiAgencia().getId(), entidad.getFechaInicio(), entidad.getFechaCierre(),
								this.mos.findParametroByParam(MidasOroConstantes.DIAS_DE_GRACIA, null, null, null, null, null).get(0).getValor()));
				metaPorAgencia.setCarteraRenovada(detalleMetaRepository.valorContrato(metaPorAgencia.getTbMiAgencia().getId(),
						EstadoContratoEnum.RENOVADO, entidad.getFechaInicio(), entidad.getFechaCierre()));
				//metaPorAgencia.setCarteraTotal(carteraTotal);
				metaPorAgencia.setCarteraVencida(
						detalleMetaRepository.valorContratoVencido(metaPorAgencia.getTbMiAgencia().getId(), entidad.getFechaInicio(), entidad.getFechaCierre(),
								this.mos.findParametroByParam(MidasOroConstantes.DIAS_DE_GRACIA, null, null, null, null, null).get(0).getValor()));
				if(metaPorAgencia.getCarteraRenovada().compareTo(metaPorAgencia.getMetaComercial()) >= 0) {
					metaPorAgencia.setCumple("x");
				}
				//metaPorAgencia.setEstado(estado);
				metaPorAgencia.setFechaActualizacion(new Date());
				//metaPorAgencia.setFechaCierre(fechaCierre);
				//metaPorAgencia.setMetaComercial(metaComercial);
				//metaPorAgencia.setMetaGerencial(metaGerencial);
				//metaPorAgencia.setPorcentajeComercial(porcentajeComercial);
				//metaPorAgencia.setPorcentajeGerencial(porcentajeGerencial);
				metaPorAgencia.setUsuarioActualizacion(usuario);

			}
			return detalleMeta;
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw e;
		}
		
	}

	public TbMiMeta cerrarMeta(TbMiMeta entidad, List<TbMiDetalleMeta> metas) throws RelativeException {
		try {
			if (entidad != null && metas != null && metas.size() > 0) {				
				entidad.setEstado(EstadoMidasEnum.CERRADO);
				TbMiMeta tbMiMeta = this.mos.manageMeta(entidad);
				for (TbMiDetalleMeta m : metas) {
					m.setEstado(EstadoMidasEnum.ACT);
					this.mos.manageDetalleMeta(m);
				}
				return tbMiMeta;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUDE LEER LA INFORMACION DE LAS METAS");
			}

		} catch (Exception e) {
			throw e;
		}
	
	}

}
