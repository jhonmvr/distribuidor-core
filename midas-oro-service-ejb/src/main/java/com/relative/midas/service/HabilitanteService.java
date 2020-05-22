package com.relative.midas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiDetalleCaja;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.model.TbMiVentaLote;
import com.relative.midas.repository.AbonoRepository;
import com.relative.midas.repository.AgenciaRepository;
import com.relative.midas.repository.AgenteRepository;
import com.relative.midas.repository.ContratoCalculoRepository;
import com.relative.midas.repository.ContratoRepository;
import com.relative.midas.repository.CorteCajaRepository;
import com.relative.midas.repository.DetalleCajaRepository;
import com.relative.midas.repository.JoyaRepository;
import com.relative.midas.repository.MovimientoCajaRepository;
import com.relative.midas.repository.VentaLoteRepository;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.util.NumeroaLetras;
import com.relative.midas.wrapper.ActaDevolucionCompraPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.ActaLiquidacionCompraDirectaHabilitanteWrapper;
import com.relative.midas.wrapper.ActaLiquidacionCompraPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.ActaPerfeccionamientoCompraPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.ActaRecepcionWrapper;
import com.relative.midas.wrapper.AdendaContratoCompraVentaPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.CierreCajaHabilitanteWrapper;
import com.relative.midas.wrapper.ComprobanteEgresoHabilitanteWrapper;
import com.relative.midas.wrapper.ComprobanteIngresoHabilitanteWrapper;
import com.relative.midas.wrapper.ContratoCompraDirectaHabilitantewrapper;
import com.relative.midas.wrapper.ContratoCompraPlazoHabilitanteWrapper;
import com.relative.midas.wrapper.DetalleCajaHabilitanteWrapper;
import com.relative.midas.wrapper.DetalleIngresoEgresoWrapper;
import com.relative.midas.wrapper.FormConozcaClienteHabilitanteWrapper;
import com.relative.midas.wrapper.JoyaReporteWrapper;
import com.relative.midas.wrapper.LiquidacionCompraBienesUsadosHabilitanteWrapper;
import com.relative.midas.wrapper.LoteWrapper;
import com.relative.midas.wrapper.MovimientosDetalleCierreCajaWrapper;

@Stateless
public class HabilitanteService {
	@Inject
	Logger log;
	@Inject
	private MidasOroService mis;
	
	@Inject
	private ParametrosSingleton ps;
	
	@Inject
	private ContratoRepository contratoRepository;
	
	@Inject
	private ContratoCalculoRepository contratoCalculoRepository;
	@Inject
	private JoyaRepository joyaRepository;
	@Inject
	private AgenciaRepository agenciaRepository;
	@Inject
	private MovimientoCajaRepository movimientoCajaRepository;
	@Inject
	private AbonoRepository abonoRepository;
	@Inject
	private AgenteRepository agenteRepository;
	@Inject
	private VentaLoteRepository ventaLoteRepository;
	@Inject
	private CorteCajaRepository corteCajaRepository;
	@Inject
	private DetalleCajaRepository detalleCajaRepository;
	

	/**
	 * Metodo que llena el contrato de compra directa por id de contrato
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */
	public ContratoCompraDirectaHabilitantewrapper setWrapperCDByCodigoContrato(String codigo)
			throws RelativeException {
		try {
			TbMiContrato c = mis.findContratoByCodigo(codigo);
			TbMiContratoCalculo cc = new TbMiContratoCalculo();
			ContratoCompraDirectaHabilitantewrapper contratoCompraDirecta = new ContratoCompraDirectaHabilitantewrapper();
			contratoCompraDirecta.setEmail(c.getTbMiCliente().getEmail());
			contratoCompraDirecta.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			log.info("Fecha"+contratoCompraDirecta.getFechaCreacion());
			contratoCompraDirecta.setIdentificacion(c.getTbMiCliente().getIdentificacion());
			log.info("id"+contratoCompraDirecta.getIdentificacion());
			contratoCompraDirecta.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			log.info("id"+contratoCompraDirecta.getIdentificacionAgente());
			contratoCompraDirecta.setIdentificacionApoderado(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			log.info("id"+contratoCompraDirecta.getIdentificacionApoderado());
			contratoCompraDirecta.setNacionalidad(c.getTbMiCliente().getNacionalidad());
			log.info("id"+contratoCompraDirecta.getNacionalidad());
			contratoCompraDirecta.setNacionalidadApoderado(c.getTbMiAgente().getNacionalidad());
			log.info("id"+contratoCompraDirecta.getNacionalidadApoderado());
			contratoCompraDirecta.setNombre(setApellidosNombresContrato(codigo));
			
			
			contratoCompraDirecta.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			contratoCompraDirecta.setNombreAporderado(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			contratoCompraDirecta.setTelefonoCelular(c.getTbMiCliente().getTelefonoCelular());
			contratoCompraDirecta.setNumeroContrato(codigo);
			contratoCompraDirecta.setValorContrato(c.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("valor contrato" + contratoCompraDirecta.getValorContrato());
			contratoCompraDirecta.setValorenLetras(NumeroaLetras.convertir(contratoCompraDirecta.getValorContrato().toString(), true)); 
			log.info("letras"+contratoCompraDirecta.getValorenLetras());
			
			return contratoCompraDirecta;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ContratoCompraPlazoHabilitanteWrapper setWrapperCDByCodigoContratoPlazo(String codigo)
			throws RelativeException {
		try {
			TbMiContrato c = mis.findContratoByCodigo(codigo);
			
			ContratoCompraPlazoHabilitanteWrapper contratoCompraPlazo = new ContratoCompraPlazoHabilitanteWrapper();
			
			contratoCompraPlazo.setEmail(c.getTbMiCliente().getEmail());
			log.info("email"+contratoCompraPlazo.getEmail());
			contratoCompraPlazo.setFechaEmision(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			log.info("fecha"+contratoCompraPlazo.getFechaEmision());
			contratoCompraPlazo.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			contratoCompraPlazo.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			contratoCompraPlazo.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			
			contratoCompraPlazo.setNombreApoderado(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			
			contratoCompraPlazo.setNombreCliente(setApellidosNombresContrato(codigo));
			contratoCompraPlazo.setTelefono(c.getTbMiCliente().getTelefonoCelular());
			contratoCompraPlazo.setPrecioCompra(c.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			contratoCompraPlazo.setValorenLetras(NumeroaLetras.convertir(contratoCompraPlazo.getPrecioCompra().toString(), true)) ;
			contratoCompraPlazo.setNumeroContrato(codigo);
			log.info("nombre Cliente"+contratoCompraPlazo.getNumeroContrato());
			return contratoCompraPlazo;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ActaDevolucionCompraPlazoHabilitanteWrapper setDevolucionCP(String codigo, String usuario) throws RelativeException {
		try {
			log.info("========zzzzzzzzzzzzzzzzz> setDevolucionCP " + codigo);
			TbMiContrato c = mis.findContratoByCodigo(codigo);
			TbMiAgente a = agenteRepository.findByNombreUsuario(usuario);
			TbMiContrato contratoOriginal = c;
			String transporte, custodia;
			//NumeroaLetras numLetras = new NumeroaLetras();
			/*
			 * while (contratoOriginal.getTbMiContratoAnterior() != null) { contratoOriginal
			 * = contratoOriginal.getTbMiContratoAnterior(); }
			 */
			ActaDevolucionCompraPlazoHabilitanteWrapper devolucionCompraPlazo = new ActaDevolucionCompraPlazoHabilitanteWrapper();
			devolucionCompraPlazo.setNombreApoderado(
					a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
							+ a.getPrimerNombre() + " " + a.getSegundoNombre());
			devolucionCompraPlazo.setIdentificacionAgente(a.getIdentificacion());
			devolucionCompraPlazo.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			devolucionCompraPlazo.setNombreCliente(setApellidosNombresContrato(codigo));
			devolucionCompraPlazo.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			devolucionCompraPlazo.setServicioAdministrativo(getValoresContratoCalculo(codigo, "Gasto Administrativo"));
			
			String tasacion = getValoresContratoCalculo(codigo, "Tasacion");
			custodia = getValoresContratoCalculo(codigo, "Custodia");
			transporte = getValoresContratoCalculo(codigo, "Transporte");
			devolucionCompraPlazo.setCustodiaAlmacenamiento(new BigDecimal(transporte).add(new BigDecimal(custodia)).toString());
			devolucionCompraPlazo.setAnticipo(contratoOriginal.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			/*devolucionCompraPlazo.setAnticipo(BigDecimal.valueOf(Double.valueOf(c.getValorContrato().toString()))
					.subtract(BigDecimal.valueOf(Double.valueOf(devolucionCompraPlazo.getServicioAdministrativo())))
					.subtract(BigDecimal.valueOf(Double.valueOf(devolucionCompraPlazo.getCustodiaAlmacenamiento())))
					.subtract(BigDecimal.valueOf(Double.valueOf(tasacion))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());*/
			devolucionCompraPlazo.setTotal(contratoOriginal.getValorContrato().add(BigDecimal.valueOf(
					Double.valueOf(devolucionCompraPlazo.getServicioAdministrativo())).add(
							BigDecimal.valueOf(Double.valueOf(devolucionCompraPlazo.getCustodiaAlmacenamiento())))
					).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			devolucionCompraPlazo.setContrato(codigo);
			devolucionCompraPlazo.setFechaCancelacion(MidasOroUtil.dateToFullString(c.getFechaActualizacion()).toString());
			devolucionCompraPlazo.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());

			return devolucionCompraPlazo;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que llena el wrapper liquidacion contrato de compra a directo por el
	 * id del contrato
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */
	public ActaLiquidacionCompraDirectaHabilitanteWrapper setLiquidacionCD(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			ActaLiquidacionCompraDirectaHabilitanteWrapper liquidacionCompraDirecta = new ActaLiquidacionCompraDirectaHabilitanteWrapper();
			liquidacionCompraDirecta.setNombreApoderado(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			liquidacionCompraDirecta.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			liquidacionCompraDirecta.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			
			liquidacionCompraDirecta.setNombreCliente(setApellidosNombresContrato(codigo));
			liquidacionCompraDirecta.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
	
			liquidacionCompraDirecta.setFechaLiquidacion(MidasOroUtil.dateToFullString(new Date()));
			liquidacionCompraDirecta.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			liquidacionCompraDirecta.setValorContrato(c.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraDirecta.setValorenLetras(NumeroaLetras.convertir(liquidacionCompraDirecta.getValorContrato().toString(), true));
			return liquidacionCompraDirecta;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	public LiquidacionCompraBienesUsadosHabilitanteWrapper setLiquidacionCB(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			List<TbMiJoya> joyas = joyaRepository.joyaByFundaId(c.getTbMiFunda().getId());
			BigDecimal total = BigDecimal.ZERO;
			for (TbMiJoya j : joyas) {
				total = total.add(j.getValor());
			}
			LiquidacionCompraBienesUsadosHabilitanteWrapper liquidacionCompraBienes = new LiquidacionCompraBienesUsadosHabilitanteWrapper();

			liquidacionCompraBienes.setNombreCliente(setApellidosNombresContrato(codigo));
			liquidacionCompraBienes.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			liquidacionCompraBienes
					.setNombreAgente(c.getTbMiAgencia().getTbMiAgente().getPrimerApellido()+" " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido()
							+ " "+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre()+ " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			liquidacionCompraBienes.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			liquidacionCompraBienes.setDireccion(StringUtils.isNotBlank(c.getTbMiCliente().getSector())?c.getTbMiCliente().getSector():"");
			liquidacionCompraBienes.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaActualizacion()));
			log.info("fecha creacion" + liquidacionCompraBienes.getFechaCreacion());
			liquidacionCompraBienes.setTotal(total.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraBienes.setTelefono(c.getTbMiCliente().getTelefonoFijo());
			liquidacionCompraBienes.setValorenLetras(NumeroaLetras.convertir(liquidacionCompraBienes.getTotal().toString(), true));
			
			return liquidacionCompraBienes;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ActaLiquidacionCompraPlazoHabilitanteWrapper setLiquidacionCP(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			String transporte, custodia;
			ActaLiquidacionCompraPlazoHabilitanteWrapper liquidacionCompraPlazo = new ActaLiquidacionCompraPlazoHabilitanteWrapper();
			liquidacionCompraPlazo
					.setNombreApoderado(c.getTbMiAgencia().getTbMiAgente().getPrimerApellido()+" " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido()
							+" "+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() +" "+ c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			liquidacionCompraPlazo.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			liquidacionCompraPlazo.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			liquidacionCompraPlazo.setNombreCliente(setApellidosNombresContrato(codigo));
			liquidacionCompraPlazo.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			liquidacionCompraPlazo.setFechaPlazo(MidasOroUtil.dateToFullString(c.getFechaVencimiento()));
			liquidacionCompraPlazo.setTasacion(c.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraPlazo.setValorenLetras(NumeroaLetras.convertir(liquidacionCompraPlazo.getTasacion().toString(), true));
			liquidacionCompraPlazo.setServicioAdministrativo(getValoresContratoCalculo(codigo, "Gasto Administrativo"));
			transporte = getValoresContratoCalculo(c.getCodigo(), "Transporte");
			custodia = getValoresContratoCalculo(c.getCodigo(), "Custodia");
			// Transporte sumado en custodia
			liquidacionCompraPlazo.setCustodia(new BigDecimal(custodia).add(new BigDecimal(transporte)).toString());
			liquidacionCompraPlazo.setPorcentajeTasacion(getValoresContratoCalculo(codigo, "Tasacion"));
			liquidacionCompraPlazo.setPorcentajeTasacionB("0.00");
			liquidacionCompraPlazo.setCustodiaB("0.00");
			liquidacionCompraPlazo.setServicioAdministrativoB("0.00");
			
			liquidacionCompraPlazo.setTotal(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getPorcentajeTasacion()))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getServicioAdministrativo())))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getCustodia()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraPlazo.setTotalB(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getPorcentajeTasacion()))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getServicioAdministrativoB())))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getCustodiaB()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraPlazo.setTotalC(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getPorcentajeTasacionB()))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getServicioAdministrativo())))
					.add(BigDecimal.valueOf(Double.valueOf(liquidacionCompraPlazo.getCustodia()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			liquidacionCompraPlazo.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			liquidacionCompraPlazo.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());

			return liquidacionCompraPlazo;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "error cargando los datos " + e.getMessage());
		}
	}
	
	public ActaPerfeccionamientoCompraPlazoHabilitanteWrapper setPerfeccionamientoCP(String codigo)
			throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			
			ActaPerfeccionamientoCompraPlazoHabilitanteWrapper perfeccionamientoCompraPlazo = new ActaPerfeccionamientoCompraPlazoHabilitanteWrapper();
			perfeccionamientoCompraPlazo.setNombreApoderado(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			perfeccionamientoCompraPlazo.setNombreCliente(setApellidosNombresContrato(codigo));
			log.info(""+perfeccionamientoCompraPlazo.getNombreCliente());
			perfeccionamientoCompraPlazo.setIdentificacionApoderado(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			//perfeccionamientoCompraPlazo.setNombreCliente(c.getTbMiCliente().getApellido() + " " + c.getTbMiCliente().getNombre());
			perfeccionamientoCompraPlazo.setFechaCreacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			log.info("fecha creacion" + perfeccionamientoCompraPlazo.getFechaCreacion());
			perfeccionamientoCompraPlazo.setFechaCreacionContrato(MidasOroUtil.dateToFullString(c.getFechaActualizacion()));
			perfeccionamientoCompraPlazo.setNumeroContrato(c.getCodigo().toString());
			
			return perfeccionamientoCompraPlazo;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public AdendaContratoCompraVentaPlazoHabilitanteWrapper setAdendaCP(String codigo, String usuario) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			TbMiContrato contratoOriginal = c;
			TbMiAgente a = agenteRepository.findByNombreUsuario(usuario);
			String transporte, custodia;
			while (contratoOriginal.getTbMiContratoAnterior() != null) {
				contratoOriginal = contratoOriginal.getTbMiContratoAnterior();
			}
			AdendaContratoCompraVentaPlazoHabilitanteWrapper adendaCompraPlazo = new AdendaContratoCompraVentaPlazoHabilitanteWrapper();
			if(c.getTbMiAgencia().getTbMiAgente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"No se puede encontrar el encargado de al agencia");
			}
			adendaCompraPlazo.setNombreApoderado(a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
			+ a.getPrimerNombre() + " " +a.getSegundoNombre());
			adendaCompraPlazo.setIdentificacionApoderado(a.getIdentificacion());
			adendaCompraPlazo.setNombreCliente(setApellidosNombresContrato(codigo));
			adendaCompraPlazo.setNombreAgente(
					a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
							+ a.getPrimerNombre() + " " +a.getSegundoNombre());
			
			adendaCompraPlazo.setFechaCreacion(
					MidasOroUtil.dateToFullString(c.getTbMiContratoAnterior().getFechaCreacion()));
			adendaCompraPlazo.setNuevaFechaVencimiento(MidasOroUtil.dateToFullString(c.getFechaVencimiento()));
			adendaCompraPlazo.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			adendaCompraPlazo.setPlazoContrato(MidasOroUtil.diasFecha(c.getFechaCreacion(),
					c.getFechaVencimiento()).toString().concat(" dias"));
			adendaCompraPlazo.setRenovacion(MidasOroUtil.dateToFullString(c.getFechaCreacion()));
			adendaCompraPlazo.setNumeroContrato(c.getCodigo());
			adendaCompraPlazo.setValorRenovacion(getValoresContratoCalculo(codigo, "Calculo Revaloracion"));
			adendaCompraPlazo.setServicioAdministrativo(getValoresContratoCalculo(codigo, "Gasto Administrativo"));
			transporte = getValoresContratoCalculo(codigo, "Transporte");
			custodia = getValoresContratoCalculo(codigo, "Custodia");
			adendaCompraPlazo.setCustodiaAlmacenamiento(new BigDecimal(transporte).add(new BigDecimal(custodia)).toString());
			log.info("setValorRenovacion=====" + adendaCompraPlazo.getValorRenovacion());
			log.info("ServicioAdministrativo=====" + adendaCompraPlazo.getServicioAdministrativo());
			log.info("CustodiaAlmacenamiento=====" + adendaCompraPlazo.getCustodiaAlmacenamiento());
			adendaCompraPlazo.setTotal(BigDecimal.valueOf(Double.valueOf(adendaCompraPlazo.getValorRenovacion()))
					.add(BigDecimal.valueOf(Double.valueOf(adendaCompraPlazo.getServicioAdministrativo())))
					.add(BigDecimal.valueOf(Double.valueOf(adendaCompraPlazo.getCustodiaAlmacenamiento()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

			return adendaCompraPlazo;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	
	public DetalleIngresoEgresoWrapper setDetalleIEWrapperEgreso(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			DetalleIngresoEgresoWrapper detalleIngresoEgreso = new DetalleIngresoEgresoWrapper();
			detalleIngresoEgreso.setNumeroOperacion(codigo);

			String[] parts = codigo.split("-");
			String part1 = parts[0];
			log.info("part1" + part1);
			if (part1.equalsIgnoreCase("CD")) {
				detalleIngresoEgreso.setProceso("Compra Directa");
				log.info("" + detalleIngresoEgreso.getProceso());

				detalleIngresoEgreso.setTasacion("0.00");
				log.info("" + detalleIngresoEgreso.getTasacion());
				log.info("true");
			}
			if (part1.equalsIgnoreCase("CP")) {
				detalleIngresoEgreso.setProceso("Compra Plazo");
				detalleIngresoEgreso.setTasacion(getValoresContratoCalculo(codigo, "Tasacion"));
			}
			

			detalleIngresoEgreso.setValorContrato(c.getValorContrato().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("" + detalleIngresoEgreso.getValorContrato());

			detalleIngresoEgreso.setSubtotal(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgreso.getValorContrato()))
					.subtract(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgreso.getTasacion()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("" + detalleIngresoEgreso.getSubtotal());

			
			detalleIngresoEgreso.setIva(
					BigDecimal.valueOf(Double.valueOf(detalleIngresoEgreso.getSubtotal())).multiply(BigDecimal.valueOf(Double.valueOf(
							MidasOroConstantes.VALOR_IVA_CERO))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("" + detalleIngresoEgreso.getIva());
			detalleIngresoEgreso.setTotal(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgreso.getSubtotal()))
					.add(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgreso.getIva()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

			log.info("" + detalleIngresoEgreso.getTotal());

			return detalleIngresoEgreso;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ComprobanteEgresoHabilitanteWrapper setComprobanteEgresoWrapper(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			ComprobanteEgresoHabilitanteWrapper comprobanteEgreso = new ComprobanteEgresoHabilitanteWrapper();
			comprobanteEgreso.setCodigoComprobante(codigoComprobanteIngresoEgreso(c.getTbMiAgencia()));
			comprobanteEgreso.setNombreAgente(
					c.getTbMiAgencia().getTbMiAgente().getPrimerApellido() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoApellido() + " "
							+ c.getTbMiAgencia().getTbMiAgente().getPrimerNombre() + " " + c.getTbMiAgencia().getTbMiAgente().getSegundoNombre());
			comprobanteEgreso.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			comprobanteEgreso.setNombreCliente(setApellidosNombresContrato(codigo));
			comprobanteEgreso.setFechaEmision(MidasOroUtil.dateToString(new Date(), "dd/MM/yyyy"));
			comprobanteEgreso.setTotal(setDetalleIEWrapperEgreso(codigo).getTotal());
			comprobanteEgreso.setIdentificacionAgente(c.getTbMiAgencia().getTbMiAgente().getIdentificacion());
			log.info("" + comprobanteEgreso.getTotal());
			return comprobanteEgreso;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public DetalleIngresoEgresoWrapper setDetalleIEWrapperIngreso(String codigo, String tipoContrato)
			throws RelativeException {
		try {
			DetalleIngresoEgresoWrapper detalleIngresoEgresoIng = new DetalleIngresoEgresoWrapper();
			String fechaEmision = "";
			List<TbMiMovimientoCaja> mc;
			BigDecimal totalEgre = BigDecimal.valueOf(Double.valueOf("0.00"));
			BigDecimal totalIng =BigDecimal.valueOf(Double.valueOf("0.00"));
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);

			log.info("tipoContrato" + tipoContrato);

			if (tipoContrato.equalsIgnoreCase("INGCAN")) {
				mc = movimientoCajaRepository.findByContrato(c.getId(),
						MidasOroUtil.getEnumFromString(ProcesoEnum.class, "CANCELAR_CONTRATO"));
				detalleIngresoEgresoIng.setProceso("Cancelacion");
				log.info("" + detalleIngresoEgresoIng.getProceso());

			} else {
				mc = movimientoCajaRepository.findByContrato(c.getId(),
						MidasOroUtil.getEnumFromString(ProcesoEnum.class, "RENOVAR_CONTRATO"));
				detalleIngresoEgresoIng.setProceso("Renovacion");

			}
			if (mc != null && !mc.isEmpty()) {
				for (TbMiMovimientoCaja m : mc) {
					log.info("" + detalleIngresoEgresoIng.getProceso());
					totalEgre = totalEgre.add(m.getEgreso());
					totalIng = totalIng.add(m.getIngreso());
					if (m.getFechaCreacion() != null)
						fechaEmision = MidasOroUtil.dateToString(m.getFechaCreacion());
				}
			}

			detalleIngresoEgresoIng.setNumeroOperacion(codigo);
			detalleIngresoEgresoIng.setSubtotal(totalIng.add(totalEgre).toString());
			detalleIngresoEgresoIng.setFechaCreacion(fechaEmision);
			log.info("" + detalleIngresoEgresoIng.getSubtotal());
			log.info("" + detalleIngresoEgresoIng.getFechaCreacion());
		
			detalleIngresoEgresoIng.setIva(
					BigDecimal.valueOf(Double.valueOf(detalleIngresoEgresoIng.getSubtotal())).multiply(BigDecimal.valueOf(Double.valueOf(
					MidasOroConstantes.VALOR_IVA_CERO))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			detalleIngresoEgresoIng.setTotal(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgresoIng.getSubtotal()))
					.add(BigDecimal.valueOf(Double.valueOf(detalleIngresoEgresoIng.getIva()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

			return detalleIngresoEgresoIng;

		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public DetalleIngresoEgresoWrapper setDetalleIEWrapperIngresoAbono(String idAbono)
			throws RelativeException {
		try {
			DetalleIngresoEgresoWrapper detalleIngresoAbono = new DetalleIngresoEgresoWrapper();
			log.info(idAbono);
			TbMiAbono a = abonoRepository.findById(Long.valueOf(idAbono));
			if(idAbono != null) { 
				 detalleIngresoAbono.setProceso("Abono");
				 log.info("proceso" + detalleIngresoAbono.getProceso());
				 detalleIngresoAbono.setNumeroOperacion(idAbono.toString());
				 log.info("proceso" + detalleIngresoAbono.getNumeroOperacion());
			     detalleIngresoAbono.setSubtotal(a.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			     log.info("proceso" + detalleIngresoAbono.getSubtotal());
			     detalleIngresoAbono.setFechaCreacion(MidasOroUtil.dateToString(a.getFechaCreacion()));
			     log.info("proceso" + detalleIngresoAbono.getFechaCreacion());
			     detalleIngresoAbono.setIva("0.00");
			     log.info("proceso" + detalleIngresoAbono.getIva());
			     detalleIngresoAbono.setTotal(detalleIngresoAbono.getSubtotal());
			     log.info("proceso" + detalleIngresoAbono.getTotal());
			} 
			return detalleIngresoAbono;

			
			

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada setDetalleIEWrapperIngresoAbono" );
		}
	}
	
	

	public FormConozcaClienteHabilitanteWrapper setFormConozcaCliente(String codigo) throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
		
			FormConozcaClienteHabilitanteWrapper conozcaCliente = new FormConozcaClienteHabilitanteWrapper();
			String nombresCliente, apellidosCliente ; 
			if (StringUtils.isBlank(c.getTbMiCliente().getSegundoNombre())) {
				nombresCliente = c.getTbMiCliente().getNombre();
				log.info("no hay segundo nombre" + nombresCliente);
			}else {
				nombresCliente = c.getTbMiCliente().getNombre()+" "+ c.getTbMiCliente().getSegundoNombre();
				
			}
			if (StringUtils.isBlank(c.getTbMiCliente().getSegundoApellido())) {
				apellidosCliente = c.getTbMiCliente().getApellido();
				log.info("no hay segundo apellido"+ apellidosCliente);
			}else {
				apellidosCliente = c.getTbMiCliente().getApellido()+" " + c.getTbMiCliente().getSegundoApellido();
			}
			
			
			conozcaCliente.setNombreCliente(nombresCliente);
			conozcaCliente.setApellidoCliente(apellidosCliente);
			conozcaCliente.setNacionalidad(c.getTbMiCliente().getNacionalidad());
			conozcaCliente.setLugarNacimiento(c.getTbMiCliente().getLugarNacimiento());
			conozcaCliente.setEstadoCivil(c.getTbMiCliente().getEstadoCivil());
			conozcaCliente.setIdentificacion(c.getTbMiCliente().getIdentificacion());
			//log.info("holi"+conozcaCliente.getApellidoCliente());
			
			conozcaCliente.setEdad(c.getTbMiCliente().getFechaNacimiento() != null?MidasOroUtil.getEdad(c.getTbMiCliente().getFechaNacimiento()).toString():"");
			//log.info("edad"+ conozcaCliente.getEdad());
			//conozcaCliente.setEdad(Integer.toString( MidasOroUtil.calculateEdad(c.getTbMiCliente().getFechaNacimiento())));
			conozcaCliente.setFechaNacimiento(c.getTbMiCliente().getFechaNacimiento() != null?MidasOroUtil.dateToString(c.getTbMiCliente().getFechaNacimiento(), "dd/MM/yyyy"):"" );
			conozcaCliente.setNivelEstudio(c.getTbMiCliente().getNivelEstudios());
			conozcaCliente.setIngresoMensual(c.getTbMiCliente().getIngresoMensual() != null? c.getTbMiCliente().getIngresoMensual().toString():" ");
			conozcaCliente.setOcupacion(StringUtils.isNotBlank(c.getTbMiCliente().getOcupacion())?c.getTbMiCliente().getOcupacion():"");
			conozcaCliente.setDirecionDomicilio(c.getTbMiCliente().getSector());
			conozcaCliente.setCallePrincipal(c.getTbMiCliente().getCallePrincipal());
			conozcaCliente.setNumeroCasa(c.getTbMiCliente().getNumeroDommicilio());
			conozcaCliente.setInterseccion(c.getTbMiCliente().getInterseccion());
			conozcaCliente.setTituloInmueble(c.getTbMiCliente().getOcupacionInmueble());
			conozcaCliente.setTelefonoFijo(c.getTbMiCliente().getTelefonoFijo());
			conozcaCliente.setTelefonoCelular(c.getTbMiCliente().getTelefonoCelular());
			conozcaCliente.setEmail(c.getTbMiCliente().getEmail());
			conozcaCliente.setNombreReferencia(StringUtils.isNotBlank(c.getTbMiCliente().getNombreReferencia())?c.getTbMiCliente().getNombreReferencia():"");
			conozcaCliente.setTelefonoReferencia(StringUtils.isNotBlank(c.getTbMiCliente().getTelefonoReferencia())?c.getTbMiCliente().getTelefonoReferencia():"");
			conozcaCliente.setCuentaBancaria(StringUtils.isNotBlank(c.getTbMiCliente().getCuentaBancaria())?c.getTbMiCliente().getCuentaBancaria():"");
			conozcaCliente.setTarjetaCredito(StringUtils.isNotBlank(c.getTbMiCliente().getTarjetaCredito())?c.getTbMiCliente().getTarjetaCredito():"");
			conozcaCliente.setDeudorFinanciero(StringUtils.isNotBlank(c.getTbMiCliente().getDeudor())?c.getTbMiCliente().getDeudor():"");
		    conozcaCliente.setPersonaPolitica(StringUtils.isNotBlank(c.getTbMiCliente().getFiguraPolitica())?c.getTbMiCliente().getFiguraPolitica():"");
			

			return conozcaCliente;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, " INFORMACION INCOMPLETA DEL CLIENTE");
		}
	}
	
	
	public ActaRecepcionWrapper setActaRecepcionWrapper(String idVentaLote, String idAgencia, String usuario)
			throws RelativeException {
		try {
			
			log.info("idjoya" + idVentaLote);
			ActaRecepcionWrapper actaRecepcion = new ActaRecepcionWrapper();
		
			TbMiVentaLote vl = ventaLoteRepository.findById(Long.valueOf(idVentaLote));
			TbMiAgente a = agenteRepository.findByNombreUsuario(usuario);
			if(a == null || a.getId() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO ENCARGADO");
			}	
			
			actaRecepcion.setNombreCliente(setApellidosNombresVentaLote(idVentaLote));
			log.info("cli"+actaRecepcion.getNombreCliente());
			actaRecepcion.setIdentificacionCliente(vl.getTbMiCliente().getIdentificacion());
			actaRecepcion.setNombreAgente(a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
							+ a.getPrimerNombre() + " " + a.getSegundoNombre());
			log.info("agen"+actaRecepcion.getNombreAgente());
			actaRecepcion.setIdentificacionAgente(a.getIdentificacion());
			
			actaRecepcion.setFechaEmision(MidasOroUtil.dateToFullString(new Date()));
			actaRecepcion.setFechaVenta(MidasOroUtil.dateToFullString(vl.getFechaCreacion()));
			
			actaRecepcion.setPrecioGramo(vl.getPrecioGramo().toString());
			actaRecepcion.setDescuento(vl.getDescuento().toString());
			actaRecepcion.setPrecioOnzaTroy(vl.getPrecioOnzaTroy().toString());
			actaRecepcion.setCodigoVentaLote(vl.getCodigo());
			actaRecepcion.setTotal(vl.getValor().toString());
			actaRecepcion.setTotalEnLetras(NumeroaLetras.convertir(actaRecepcion.getTotal(), false));
			return actaRecepcion;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public List<LoteWrapper> setLoteWrapper(String idVentaLote) throws RelativeException {
		try {
			TbMiVentaLote vl = ventaLoteRepository.findById(Long.valueOf(idVentaLote));
			List<LoteWrapper> loteListReporte = new ArrayList<LoteWrapper>();
		    List<TbMiLote> lotes = mis.findLotesByIdVenta(null,Long.valueOf(idVentaLote));
			BigDecimal sumaTotalGramos= BigDecimal.valueOf(Double.valueOf("0.00"));
			BigDecimal totalPesoBruto= BigDecimal.valueOf(Double.valueOf("0.00"));
			BigDecimal totalValor= BigDecimal.valueOf(Double.valueOf("0.00"));
			
			for (TbMiLote l : lotes) {
				LoteWrapper loteWra = new LoteWrapper();
				loteWra.setKilataje(l.getTbMiTipoOro().getQuilate());
				loteWra.setLey(l.getLey().toString());
				loteWra.setCostoGramo(l.getCostoPorGramo().toString());
				loteWra.setPrecioVenta(l.getPrecioVenta().toString());
				loteWra.setTotalGramos(l.getTotalGramos().toString());
				loteWra.setValorAPagar(l.getValorPagar().toString());
				sumaTotalGramos = sumaTotalGramos.add(BigDecimal.valueOf(Double.valueOf(loteWra.getTotalGramos())));
				totalValor = totalValor.add(BigDecimal.valueOf(Double.valueOf(loteWra.getValorAPagar())));
			}
			LoteWrapper loteWra = new LoteWrapper();
			loteWra.setKilataje("");
			loteWra.setLey("");
			loteWra.setCostoGramo("");
			loteWra.setPrecioVenta("Total Gramos");
			loteWra.setTotalGramos(sumaTotalGramos.toString());
			loteWra.setValorAPagar(totalValor.toString());
			log.info("total"+totalValor);
			return loteListReporte;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	
	
	public List<JoyaReporteWrapper> setJoyaReporteWrapper(String codigo) throws RelativeException {
		try {
			TbMiContrato c = mis.findContratoByCodigo(codigo);
			List<JoyaReporteWrapper> joyaListReporte = new ArrayList<JoyaReporteWrapper>();
			List<TbMiJoya> joyas = joyaRepository.joyaByFundaId(c.getTbMiFunda().getId());
			BigDecimal totalPesoNeto= BigDecimal.valueOf(Double.valueOf("0.00"));
			BigDecimal totalPesoBruto= BigDecimal.valueOf(Double.valueOf("0.00"));
			BigDecimal totalValor= BigDecimal.valueOf(Double.valueOf("0.00"));
			
			for (TbMiJoya j : joyas) {
				JoyaReporteWrapper joyaWra = new JoyaReporteWrapper();
				joyaWra.setNumeroFunda(j.getTbMiFunda().getCodigo().toString());
				joyaWra.setTipoOro(j.getTbMiCompraOro().getQuilate());
				joyaWra.setCantidad("1");
				if(StringUtils.isBlank(j.getComentario())){
					joyaWra.setDescripcion(j.getTbMiTipoJoya().getDetalle()+" "+j.getCondicion()+" ");
				}else {
					joyaWra.setDescripcion(j.getTbMiTipoJoya().getDetalle()+" "+j.getCondicion()+" "+ j.getComentario());
				}
				
				joyaWra.setPesoBruto(j.getPesoBruto().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				joyaWra.setPesoNeto(j.getPeso().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				joyaWra.setValorCompra(j.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 	totalValor = totalValor.add(BigDecimal.valueOf(Double.valueOf(joyaWra.getValorCompra())));
			 	totalPesoNeto = totalPesoNeto.add(BigDecimal.valueOf(Double.valueOf(joyaWra.getPesoNeto())));
			 	totalPesoBruto = totalPesoBruto.add(BigDecimal.valueOf(Double.valueOf(joyaWra.getPesoBruto())));
				joyaListReporte.add(joyaWra);
			}
			JoyaReporteWrapper joyaWra = new JoyaReporteWrapper();
			joyaWra.setNumeroFunda(" ");
			joyaWra.setTipoOro(" ");
			joyaWra.setCantidad(" ");
			joyaWra.setDescripcion("TOTAL");
			
			joyaWra.setPesoNeto(totalPesoNeto.add(new BigDecimal("0.00")).toString());
			joyaWra.setPesoBruto(totalPesoBruto.add(new BigDecimal("0.00")).toString());
			joyaWra.setValorCompra(totalValor.add(new BigDecimal("0.00")).toString());
			joyaListReporte.add(joyaWra);
			log.info("total"+totalValor);
			return joyaListReporte;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ComprobanteIngresoHabilitanteWrapper setComprobanteIngresoWrapper(String codigo, String tipoContrato, String usuario)
			throws RelativeException {
		try {
			TbMiContrato c = contratoRepository.findByCodigoContrato(codigo);
			TbMiAgente at = agenteRepository.findByNombreUsuario(usuario);
			ComprobanteIngresoHabilitanteWrapper comprobanteIngreso = new ComprobanteIngresoHabilitanteWrapper();
			comprobanteIngreso.setCodigoComprobante(codigoComprobanteIngresoEgreso(c.getTbMiAgencia()));
			log.info("codigo"+ comprobanteIngreso.getCodigoComprobante());
			comprobanteIngreso.setNombreAgente(
					at.getPrimerApellido() + " " + at.getSegundoApellido() + " "
							+ at.getPrimerNombre() + " " + at.getSegundoNombre());
			comprobanteIngreso.setIdentificacionCliente(c.getTbMiCliente().getIdentificacion());
			comprobanteIngreso.setNombreCliente(setApellidosNombresContrato(codigo));
			comprobanteIngreso.setFechaEmision(MidasOroUtil.dateToString(new Date(), "dd-MM-yyyy"));
			comprobanteIngreso.setTotal(setDetalleIEWrapperIngreso(codigo, tipoContrato).getTotal());
			

			return comprobanteIngreso;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ComprobanteIngresoHabilitanteWrapper setComprobanteIngresoAbonoWrapper(String idAbono, String idAgencia, String usuario)
			throws RelativeException {
		try {
			TbMiAbono a = abonoRepository.findById(Long.valueOf(idAbono));
			//TbMiAgencia ag= agenciaRepository.findById(Long.valueOf(idAgencia));
			TbMiAgente at = agenteRepository.findByNombreUsuario(usuario);
			ComprobanteIngresoHabilitanteWrapper comprobanteIngresoAbono = new ComprobanteIngresoHabilitanteWrapper();
			comprobanteIngresoAbono.setNombreAgente(
					at.getPrimerApellido() + " " + at.getSegundoApellido() + " "
							+ at.getPrimerNombre() + " " + at.getSegundoNombre());
			// comprobanteEgreso.setIdentificacionAgente(c.getTbMiAgente().getIdentificacion());
			String nombresCliente, apellidosCliente ;  
			if (StringUtils.isBlank(a.getTbMiCliente().getSegundoNombre())) {
				nombresCliente = a.getTbMiCliente().getNombre();
				log.info("no hay segundo nombre" + nombresCliente);
			}else {
				nombresCliente = a.getTbMiCliente().getNombre()+" "+ a.getTbMiCliente().getSegundoNombre();
				
			}
			if (StringUtils.isBlank(a.getTbMiCliente().getSegundoApellido())) {
				apellidosCliente = a.getTbMiCliente().getApellido();
				log.info("no hay segundo apellido"+ apellidosCliente);
			}else {
				apellidosCliente = a.getTbMiCliente().getApellido()+" " + a.getTbMiCliente().getSegundoApellido();
			}
			comprobanteIngresoAbono.setCodigoComprobante(codigoComprobanteIngresoEgreso(Long.valueOf(idAgencia)));
			comprobanteIngresoAbono.setIdentificacionCliente(a.getTbMiCliente().getIdentificacion());
			log.info("Cliente"+ comprobanteIngresoAbono.getIdentificacionCliente());
			comprobanteIngresoAbono.setNombreCliente(apellidosCliente+ " " + nombresCliente);
			log.info("Cliente"+ comprobanteIngresoAbono.getNombreCliente());
			comprobanteIngresoAbono.setFechaEmision(MidasOroUtil.dateToString(new Date(), "dd-MM-yyyy"));
			//comprobanteIngresoAbono.setFechaEmision(setDetalleIEWrapperIngresoAbono(idAbono).getFechaCreacion());
			log.info("Cliente"+ comprobanteIngresoAbono.getFechaEmision());
			comprobanteIngresoAbono.setTotal(setDetalleIEWrapperIngresoAbono(idAbono).getTotal());
			log.info("total" + comprobanteIngresoAbono.getTotal());

			return comprobanteIngresoAbono;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	public DetalleIngresoEgresoWrapper setDetalleIngresoVentaVitrina(String idJoya)
			throws RelativeException {
		try {
			DetalleIngresoEgresoWrapper detalleIngresoVentaVitrina = new DetalleIngresoEgresoWrapper();
			log.info(idJoya);
			
			if(idJoya != null) { 
			
			TbMiJoya j = joyaRepository.findById(Long.valueOf(idJoya));
			detalleIngresoVentaVitrina.setProceso("Venta Vitrina");
			log.info("proceso" + detalleIngresoVentaVitrina.getProceso());
			detalleIngresoVentaVitrina.setNumeroOperacion(idJoya.toString());
			log.info("proceso" + detalleIngresoVentaVitrina.getNumeroOperacion());
			detalleIngresoVentaVitrina.setSubtotal(j.getPrecioVenta().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("subtotal" + detalleIngresoVentaVitrina.getSubtotal());
			detalleIngresoVentaVitrina.setIva(j.getIva().toString());
			log.info("iva" + detalleIngresoVentaVitrina.getIva());
			detalleIngresoVentaVitrina.setTotal( BigDecimal.valueOf(Double.valueOf(detalleIngresoVentaVitrina.getSubtotal())).add(
					BigDecimal.valueOf(Double.valueOf(detalleIngresoVentaVitrina.getIva()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("proceso" + detalleIngresoVentaVitrina.getTotal());    
			} 
			
			return detalleIngresoVentaVitrina;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada setDetalleIngresoVentaVitrina" );
		}
	}
	
	public ComprobanteIngresoHabilitanteWrapper setComprobanteIngresoVentaVitrina(String idJoya, String usuario)
			throws RelativeException {
		try {
			log.info("idjoya" + idJoya);
			TbMiJoya j = joyaRepository.findById(Long.valueOf(idJoya));
			// parametro id del agente
			TbMiAgencia ag= j.getTbMiContrato().getTbMiAgencia(); 
				//agenciaRepository.findById(Long.valueOf(idAgencia));
			TbMiAgente a = agenteRepository.findByNombreUsuario(usuario);
			if(a == null || a.getId() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO TIENE ENCARGADO");
			}
			ComprobanteIngresoHabilitanteWrapper comprobanteIngresoVitrina = new ComprobanteIngresoHabilitanteWrapper();
			comprobanteIngresoVitrina.setCodigoComprobante(codigoComprobanteIngresoEgreso(ag));
			comprobanteIngresoVitrina.setNombreAgente(a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
							+ a.getPrimerNombre() + " " + a.getSegundoNombre());
			log.info("agente"+ comprobanteIngresoVitrina.getNombreAgente());
			//comprobanteIngresoVitrina.setIdentificacionAgente(a.getIdentificacion());
			comprobanteIngresoVitrina.setIdentificacionAgente(a.getIdentificacion());
			log.info("cliente"+ comprobanteIngresoVitrina.getIdentificacionAgente());
			
			comprobanteIngresoVitrina.setIdentificacionCliente(j.getComprador().getIdentificacion());
			log.info("cliente"+ comprobanteIngresoVitrina.getIdentificacionCliente());
			
			//validacion nombres y apellidos 
			String nombresCliente, apellidosCliente ;  
			if (StringUtils.isBlank(j.getComprador().getSegundoNombre())) {
				nombresCliente = j.getComprador().getNombre();
				log.info("no hay segundo nombre" + nombresCliente);
			}else {
				nombresCliente = j.getComprador().getNombre()+" "+ j.getComprador().getSegundoNombre();
				
			}
			if (StringUtils.isBlank(j.getComprador().getSegundoApellido())) {
				apellidosCliente = j.getComprador().getApellido();
				log.info("no hay segundo apellido"+ apellidosCliente);
			}else {
				apellidosCliente = j.getComprador().getApellido()+" " + j.getComprador().getSegundoApellido();
			}
			comprobanteIngresoVitrina.setNombreCliente(apellidosCliente+ " " + nombresCliente);
			log.info("cliente"+comprobanteIngresoVitrina.getNombreCliente());
			//comprobanteIngresoVitrina.setFechaEmision(MidasOroUtil.dateToString(j.getFechaActualizacion())); 
			comprobanteIngresoVitrina.setFechaEmision(MidasOroUtil.dateToString(new Date(), "dd/MM/yyyy"));
			log.info("agente"+ comprobanteIngresoVitrina.getFechaEmision());
			comprobanteIngresoVitrina.setTotal(setDetalleIngresoVentaVitrina(idJoya).getTotal());
			log.info("agente"+ comprobanteIngresoVitrina.getTotal());
			
			
			return comprobanteIngresoVitrina;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada setComprobanteIngresoVentaVitrina");
		}
	}
	
	public DetalleIngresoEgresoWrapper setDetalleIngresoVentaLote(String idVentaLote)
			throws RelativeException {
		
		try {
			DetalleIngresoEgresoWrapper detalleIngresoVentaLote = new DetalleIngresoEgresoWrapper();
			log.info(idVentaLote);
			
			if(idVentaLote != null) { 
			TbMiVentaLote vl = ventaLoteRepository.findById(Long.valueOf(idVentaLote));	
			
			
			detalleIngresoVentaLote.setProceso("Venta Lote");
			log.info("proceso" + detalleIngresoVentaLote.getProceso());
			
			detalleIngresoVentaLote.setNumeroOperacion(vl.getCodigo());
			log.info("numeroOperacion" + detalleIngresoVentaLote.getNumeroOperacion());
			
			detalleIngresoVentaLote.setSubtotal(vl.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("subtotal"+ detalleIngresoVentaLote.getSubtotal());
			
			BigDecimal iva = vl.getValorIva();
			detalleIngresoVentaLote.setIva(iva.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

			log.info("subtotal"+ detalleIngresoVentaLote.getIva());
			detalleIngresoVentaLote.setTotal( BigDecimal.valueOf(Double.valueOf(detalleIngresoVentaLote.getSubtotal())).add(
					iva).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info("total"+ detalleIngresoVentaLote.getTotal());  
			} 
			
			return detalleIngresoVentaLote;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public ComprobanteIngresoHabilitanteWrapper setComprobanteIngresoVentaLote(String idVentaLote, String idAgencia, String usuario)
			throws RelativeException {
		try {
			
			log.info("idjoya" + idVentaLote);
			ComprobanteIngresoHabilitanteWrapper comprobanteIngresoVentaLote = new ComprobanteIngresoHabilitanteWrapper();
		
			TbMiVentaLote vl = ventaLoteRepository.findById(Long.valueOf(idVentaLote));
			//TbMiAgencia ag= agenciaRepository.findById(Long.valueOf(idAgencia));
			TbMiAgente a = agenteRepository.findByNombreUsuario(usuario);
			if(a == null || a.getId() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO ENCARGADO");
			}
			comprobanteIngresoVentaLote.setCodigoComprobante(codigoComprobanteIngresoEgreso(Long.valueOf(idAgencia)));
			
			comprobanteIngresoVentaLote.setNombreAgente(a.getPrimerApellido() + " " + a.getSegundoApellido() + " "
							+ a.getPrimerNombre() + " " + a.getSegundoNombre());
			log.info("agente" + comprobanteIngresoVentaLote.getNombreAgente());
			
			comprobanteIngresoVentaLote.setIdentificacionAgente(a.getIdentificacion());
			log.info("identificacion agente" + comprobanteIngresoVentaLote.getIdentificacionAgente());
			comprobanteIngresoVentaLote.setIdentificacionCliente(vl.getTbMiCliente().getIdentificacion());
			log.info("cliente identificacion" + comprobanteIngresoVentaLote.getIdentificacionCliente());
			//validacion apellidos nombres
			String nombresCliente, apellidosCliente ;  
			if (StringUtils.isBlank(vl.getTbMiCliente().getSegundoNombre())) {
				nombresCliente = vl.getTbMiCliente().getNombre();
				log.info("no hay segundo nombre" + nombresCliente);
			}else {
				nombresCliente = vl.getTbMiCliente().getNombre()+" "+ vl.getTbMiCliente().getSegundoNombre();
				
			}
			if (StringUtils.isBlank(vl.getTbMiCliente().getSegundoApellido())) {
				apellidosCliente = vl.getTbMiCliente().getApellido();
				log.info("no hay segundo apellido"+ apellidosCliente);
			}else {
				apellidosCliente = vl.getTbMiCliente().getApellido()+" " + vl.getTbMiCliente().getSegundoApellido();
			}
			
			comprobanteIngresoVentaLote.setNombreCliente(apellidosCliente+" "+nombresCliente);
			
			//comprobanteIngresoVentaLote.setFechaEmision(MidasOroUtil.dateToString(vl.getFechaCreacion())); 
			comprobanteIngresoVentaLote.setFechaEmision(MidasOroUtil.dateToString(new Date(), "dd-MM-yyyy"));
			log.info("agente" + comprobanteIngresoVentaLote.getFechaEmision());
			comprobanteIngresoVentaLote.setTotal(setDetalleIngresoVentaLote(idVentaLote).getTotal());
			log.info("agente" + comprobanteIngresoVentaLote.getTotal());
			return comprobanteIngresoVentaLote;

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public CierreCajaHabilitanteWrapper setCierreCajaHabilitanteWrapper(String idCorteCaja) throws RelativeException {
		
		try {
			String hora, minutos, segundos; 
			Calendar calendario = Calendar.getInstance();	 
			hora = StringUtils.leftPad(String.valueOf(calendario.get(Calendar.HOUR_OF_DAY) ), 2, "0");
			minutos = StringUtils.leftPad(String.valueOf(calendario.get(Calendar.MINUTE) ), 2, "0");
			segundos = StringUtils.leftPad(String.valueOf(calendario.get(Calendar.SECOND) ), 2, "0");
			String totalDetalleCaja ;
			
			BigDecimal totalMovimientosCaja, totalIngresos, totalEgresos;
			CierreCajaHabilitanteWrapper cabeceraCierreCaja = new CierreCajaHabilitanteWrapper();
			TbMiCorteCaja cj = corteCajaRepository.findById(Long.valueOf(idCorteCaja));
			//TbMiAgencia ag = agenciaRepository.findById(cj.getTbMiAgencia().getId());
			TbMiAgente agente = agenteRepository.findById(cj.getTbMiAgente().getId());
			cabeceraCierreCaja.setFechaActual(MidasOroUtil.dateToString(cj.getFechaCierre(), "dd/MM/yyyy"));
			log.info(""+cabeceraCierreCaja.getFechaActual());
			cabeceraCierreCaja.setFechaUltimoCuadre(MidasOroUtil.dateToString(cj.getFechaApertura(), "dd/MM/yyyy"));
			log.info(""+cabeceraCierreCaja.getFechaUltimoCuadre());
			cabeceraCierreCaja.setAgencia(cj.getTbMiAgencia().getNombreAgencia());
			cabeceraCierreCaja.setSaldoApertura(cj.getValorApertura().toString());
			cabeceraCierreCaja.setNombreAgente(agente.getPrimerNombre() +" "+ agente.getSegundoNombre()+" "+
			agente.getPrimerApellido()+" " + agente.getSegundoApellido());
			///valores
			//List<DetalleCajaHabilitanteWrapper> reporteDetalleCaja =setDetalleCajaHabilitanteWrapper(idCorteCaja) ;
			List<MovimientosDetalleCierreCajaWrapper> detallesMovs = setDetalleCierreCajaMovimientosWrapper(idCorteCaja);
			//totalDetalleCaja= reporteDetalleCaja.get(reporteDetalleCaja.size()-1).getValor();
			totalIngresos = detallesMovs.get(detallesMovs.size()-1).getValorIngreso();
			totalEgresos = detallesMovs.get(detallesMovs.size()-1).getValorEgreso();
			totalMovimientosCaja = cj.getValorApertura().add(totalIngresos).subtract(totalEgresos);
			cabeceraCierreCaja.setSaldoActual(totalMovimientosCaja.toString());
			cabeceraCierreCaja.setHoraActual(hora + ":" + minutos + ":" +segundos);
			log.info(""+hora);
			log.info(""+minutos);
			log.info(""+segundos);
			//arraylist.get(arraylist.size()-1)
			if(StringUtils.isBlank(cj.getJustificacion())){
				cabeceraCierreCaja.setJustificacion("");
			} else {
				cabeceraCierreCaja.setJustificacion(cj.getJustificacion());
			}
			cabeceraCierreCaja.setJustificacion(cj.getJustificacion());
			cabeceraCierreCaja.setDiferencia(cj.getDiferencia().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			log.info(""+cabeceraCierreCaja.getDiferencia());
			return cabeceraCierreCaja;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error cargando datos al wrapper CabeceraCierreCaja " + e.getMessage());
		}
		
		
	}
	
	public List<DetalleCajaHabilitanteWrapper> setDetalleCajaHabilitanteWrapper(String idCorteCaja)throws RelativeException{
		
		try {
				List<DetalleCajaHabilitanteWrapper> reporteDetalleCaja = new ArrayList<DetalleCajaHabilitanteWrapper>() ;
			
				List<TbMiDetalleCaja> detallesCajas = detalleCajaRepository.findByIdCorteCaja(Long.valueOf(idCorteCaja));
				BigDecimal totalBilletes= BigDecimal.valueOf(Double.valueOf("0.00"));
				BigDecimal totalMonedas= BigDecimal.valueOf(Double.valueOf("0.00"));
				log.info("enters billetes wrapper");
				DetalleCajaHabilitanteWrapper dcWrapper;
				dcWrapper = new DetalleCajaHabilitanteWrapper();
				dcWrapper.setDenominacion("BILLETES");
				dcWrapper.setUnidades("");
				dcWrapper.setValor("");
				reporteDetalleCaja.add(dcWrapper);
				for (TbMiDetalleCaja dc : detallesCajas) {		
				
					if(dc.getTipo().equalsIgnoreCase(MidasOroConstantes.BILLETE)) {
						dcWrapper = new DetalleCajaHabilitanteWrapper();
						dcWrapper.setDenominacion(dc.getDenomincion().toString());
						dcWrapper.setUnidades(dc.getNumero().toString());
						dcWrapper.setValor(dc.getValor().toString());
						totalBilletes = totalBilletes.add(dc.getValor());
						reporteDetalleCaja.add(dcWrapper);		
					}
				}
				dcWrapper = new DetalleCajaHabilitanteWrapper();
				dcWrapper.setDenominacion("MONEDAS");
				dcWrapper.setUnidades("");
				dcWrapper.setValor("");
				reporteDetalleCaja.add(dcWrapper);
				for (TbMiDetalleCaja dc : detallesCajas) {					
					if(dc.getTipo().equalsIgnoreCase(MidasOroConstantes.MONEDA)) {
						dcWrapper = new DetalleCajaHabilitanteWrapper();
						
							dcWrapper.setDenominacion(dc.getDenomincion().toString());
							dcWrapper.setUnidades(dc.getNumero().toString());
							dcWrapper.setValor(dc.getValor().toString());
							totalMonedas = totalMonedas.add(dc.getValor());
							reporteDetalleCaja.add(dcWrapper);
					}					
				}
				dcWrapper = new DetalleCajaHabilitanteWrapper();
				dcWrapper.setDenominacion("");
				dcWrapper.setUnidades("");
				dcWrapper.setValor(totalBilletes.add(totalMonedas).toString());
				reporteDetalleCaja.add(dcWrapper);
			
				return reporteDetalleCaja;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} catch (RelativeException e) {
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error cargando datos al wrapper BILLETES " + e.getMessage());
		}
		
	}
	
	public List<MovimientosDetalleCierreCajaWrapper> setDetalleCierreCajaMovimientosWrapper(String idCorteCaja) throws RelativeException {
		try {
			MovimientosDetalleCierreCajaWrapper total = new MovimientosDetalleCierreCajaWrapper();
			List<MovimientosDetalleCierreCajaWrapper> detallesMovs = metodoParabuscar(idCorteCaja);
			log.info("movimientos" +detallesMovs);
			BigDecimal totalIngresos= new BigDecimal("0.00");
			BigDecimal totalEgresos= new BigDecimal("0.00");
	
			for (MovimientosDetalleCierreCajaWrapper mc : detallesMovs) {
				totalIngresos = totalIngresos.add(mc.getValorIngreso());
				totalEgresos = totalEgresos.add(mc.getValorEgreso());
			}
			total.setConcepto("TOTAL");
			total.setValorIngreso(totalIngresos);
			total.setValorEgreso(totalEgresos);
			//log.info(""+total.getValorIngreso());
			//log.info(""+total.getValorEgreso());
			detallesMovs.add(total);
			
			return detallesMovs;
	} catch (NumberFormatException e) {
		e.printStackTrace();
		throw e;
	} catch (RelativeException e) {
		e.printStackTrace();
		throw new RelativeException(Constantes.ERROR_CODE_READ, "Error cargando datos al wrapper detalle Caja " + e.getMessage());
	}
	
		
	}

	public List<MovimientosDetalleCierreCajaWrapper> metodoParabuscar(String idCorteCaja) throws RelativeException {
		List<MovimientosDetalleCierreCajaWrapper> detallesMovs = movimientoCajaRepository.filWrapperByCorteCaja(Long.valueOf(idCorteCaja));
		return detallesMovs;
	}
	
	public String getValoresContratoCalculo(String codigo, String rubro) throws RelativeException {
		TbMiContrato c;
		String valorCalculadoContrato = " ";
		TbMiContratoCalculo cc;
		try {
			c = mis.findContratoByCodigo(codigo);
			if (c == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE ENCUENTRA CONTRATO CON CODIGO: " + codigo);
			}
			cc = contratoCalculoRepository.findByIdContratoAndRubro(c.getId(), rubro);

			if (cc != null) {
				valorCalculadoContrato = cc.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
			}
			if (cc == null) {
				valorCalculadoContrato = "0.00";
			}

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "En ValoresCalculadosContrato ");
		}

		return valorCalculadoContrato;
	}

	public String codigoComprobanteIngresoEgreso(TbMiAgencia agencia) throws RelativeException {
		//TbMiAgencia agencia;
		String seq;
		String codigoComprobante = "";
		try {
			
			//agencia = agenciaRepository.findById(idAgencia);
			
			seq = contratoRepository.generateSecuencia(agencia.getSeqIE()).toString();
			codigoComprobante = codigoComprobante.concat(MidasOroConstantes.CODIGO_COMPROBANTE_INGRESOS_EGRESOS).
					concat("-").concat(agencia.getCodigo())
					.concat("-").concat(StringUtils.leftPad(seq, 5, "0"));
			
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "GENERAR CODIGO DE COMPROBANTE");
		}

		return codigoComprobante;
	}
	
	public String setApellidosNombresContrato(String codigoContrato) throws RelativeException {
		TbMiContrato c = contratoRepository.findByCodigoContrato(codigoContrato);
		StringBuilder nombresCliente= new StringBuilder();
		nombresCliente.append(c.getTbMiCliente().getApellido());
		if(StringUtils.isNotBlank(c.getTbMiCliente().getSegundoApellido())) {
			nombresCliente.append(" ");
			nombresCliente.append(c.getTbMiCliente().getSegundoApellido());
		}
		nombresCliente.append(" ");
		nombresCliente.append(c.getTbMiCliente().getNombre());
		if(StringUtils.isNotBlank(c.getTbMiCliente().getSegundoNombre())) {
			nombresCliente.append(" ");
			nombresCliente.append(c.getTbMiCliente().getSegundoNombre());
		}
		

			return nombresCliente.toString();
		}
		
	public String setApellidosNombresVentaLote(String idVentaLote) throws RelativeException {
		TbMiVentaLote vl = ventaLoteRepository.findById(Long.valueOf(idVentaLote));
		String nombresCliente, apellidosCliente ;  
		if (StringUtils.isBlank(vl.getTbMiCliente().getSegundoNombre())) {
			nombresCliente = vl.getTbMiCliente().getNombre();
			log.info("no hay segundo nombre" + nombresCliente);
		}else {
			nombresCliente = vl.getTbMiCliente().getNombre()+" "+ vl.getTbMiCliente().getSegundoNombre();
			
		}
		if (StringUtils.isBlank(vl.getTbMiCliente().getSegundoApellido())) {
			apellidosCliente = vl.getTbMiCliente().getApellido();
			log.info("no hay segundo apellido"+ apellidosCliente);
		}else {
			apellidosCliente = vl.getTbMiCliente().getApellido()+" " + vl.getTbMiCliente().getSegundoApellido();
		}
		return apellidosCliente + " " + nombresCliente;
	}
		 
		

	
	public String codigoComprobanteIngresoEgreso(long idAgencia) throws RelativeException {
		TbMiAgencia agencia;
		String seq;
		String codigoComprobante = "";
		try {
			
			agencia = agenciaRepository.findById(idAgencia);
			
			seq = contratoRepository.generateSecuencia(agencia.getSeqIE()).toString();
			codigoComprobante = codigoComprobante.concat(MidasOroConstantes.CODIGO_COMPROBANTE_INGRESOS_EGRESOS).
					concat("-").concat(agencia.getCodigo())
					.concat("-").concat(StringUtils.leftPad(seq, 5, "0"));
			
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "GENERAR CODIGO DE COMPROBANTE");
		}

		return codigoComprobante;
	}
	
}
