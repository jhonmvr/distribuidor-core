package com.relative.midas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.NotificacionEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiCompraOro;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiLiquidacion;
import com.relative.midas.model.TbMiMovInventario;
import com.relative.midas.model.TbMiNotificacion;
import com.relative.midas.model.TbMiTipoOro;
import com.relative.midas.repository.AgenciaRepository;
import com.relative.midas.repository.ClienteRepository;
import com.relative.midas.repository.ContratoRepository;
import com.relative.midas.repository.FundaRepository;
import com.relative.midas.repository.LiquidacionRepository;
import com.relative.midas.repository.NotificacionRepository;
import com.relative.midas.repository.ParametroRepository;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;

@Stateless
public class CompraOroService {
	@Inject
	Logger log;
	@Inject
	private FundaRepository fundaRepository;
	@Inject
	private LiquidacionRepository liquidacionRepository;
	@Inject
	private ContratoRepository contratoRepository;
	@Inject
	private AgenciaRepository agenciaRepository;
	@Inject
	private ParametroRepository parametroRepository;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private NotificacionRepository notificacionRepository;

	@Inject
	private MidasOroService mos;

	public String codigoContrato(String tipoCompra, Long idAgencia) throws RelativeException {
		TbMiAgencia agencia;
		String seq;
		String tipoC;
		String codigoCompra = "";
		try {
			tipoC = parametroRepository.findByNombre(tipoCompra).getValor();
			agencia = agenciaRepository.findById(idAgencia);

			if (tipoCompra.equalsIgnoreCase(MidasOroConstantes.COMPRA_DIRECTA)) {
				seq = contratoRepository.generateSecuencia(agencia.getSeqCd()).toString();
				codigoCompra = codigoCompra.concat(tipoC).concat("-")
						.concat(String.valueOf(new Date().getYear() + 1900)).concat("-").concat(agencia.getCodigo())
						.concat("-").concat(seq);
			} else if (tipoCompra.equalsIgnoreCase(MidasOroConstantes.COMPRA_PLAZOS.toString())) {
				seq = contratoRepository.generateSecuencia(agencia.getSeqCp()).toString();
				codigoCompra = codigoCompra.concat(tipoC).concat("-")
						.concat(String.valueOf(new Date().getYear() + 1900)).concat("-").concat(agencia.getCodigo())
						.concat("-").concat(seq).concat("-00");
			}
			return codigoCompra;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "GENERAR CODIGO DE CONTRATO");
		}

		
	}

	public TbMiFunda reservarFunda(Long idAgencia, String usuario) throws RelativeException {
		TbMiFunda fundaReservada = null;
		fundaReservada = fundaRepository.reservarFunda(idAgencia);
		TbMiNotificacion notificacion = null;
		if (fundaRepository.countFunda(idAgencia) <= 10) {
			notificacion = notificacionRepository.findByMulti(NotificacionEnum.FUNDA, null, idAgencia, null);
			if (notificacion != null) {
				notificacion.setEstado(EstadoMidasEnum.ACT);
			} else {
				notificacion = new TbMiNotificacion();
				notificacion.setUsuarioCreacion(usuario);
				notificacion.setEstado(EstadoMidasEnum.ACT);
				notificacion.setIdReferencia(idAgencia);
				notificacion.setMensaje(MidasOroConstantes.MESSAGE_NOTIFICACION_FUNDA);
				notificacion.setTbMiAgencia(this.agenciaRepository.findById(idAgencia));
				notificacion.setTipo(NotificacionEnum.FUNDA);
			}
			mos.manageNotificacion(notificacion);
		}

		return fundaReservada;

	}

	public TbMiLiquidacion reservarLiquidacion(Long idAgencia, String usuario) throws RelativeException {
		TbMiLiquidacion liquidacionReservada = liquidacionRepository.reservarLiquidacion(idAgencia);

		TbMiNotificacion notificacion = null;
		if (liquidacionRepository.countLiquidacion(idAgencia) <= 10) {
			notificacion = notificacionRepository.findByMulti(NotificacionEnum.LIQUIDACION, null, idAgencia, null);
			if (notificacion != null) {
				notificacion.setEstado(EstadoMidasEnum.ACT);
			} else {
				notificacion = new TbMiNotificacion();
				notificacion.setEstado(EstadoMidasEnum.ACT);
				notificacion.setIdReferencia(idAgencia);
				notificacion.setMensaje(MidasOroConstantes.MESSAGE_NOTIFICACION_LIQUIDACION);
				notificacion.setUsuarioCreacion(usuario);
				notificacion.setTbMiAgencia(this.agenciaRepository.findById(idAgencia));
				notificacion.setTipo(NotificacionEnum.LIQUIDACION);
			}
			mos.manageNotificacion(notificacion);
		}

		return liquidacionReservada;

	}

	public TbMiCliente clienteByIdentificacion(String identificacion) throws RelativeException {
		List<TbMiCliente> tmp = this.clienteRepository.findClienteByIdentifiacion(identificacion);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}

	/**
	 * Valida los datos enviados y retorna la excepcion correspondiente a la validacion realizada
	 * @param joyas
	 * @param compra
	 * @param idAgencia
	 * @param agencia
	 * @param agente
	 * @throws RelativeException
	 */
	private void validateContrato(List<TbMiJoya> joyas,TbMiContrato compra, Long idAgencia,TbMiAgencia agencia,TbMiAgente agente,TbMiBodega bodega ) throws RelativeException{
		if (compra == null || compra.getTbMiCliente() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE");
		}
		if (compra.getTbMiFunda() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DE LA FUNDA");
		}
		if (joyas == null || joyas.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DE LAS JOYAS");
		}
		if (compra.getTipoCompra().equals(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)
				&& compra.getTbMiLiquidacion() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DE LA LIQUIDACION");
		}
		if (agencia == null || agente == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DE LA AGENCIA");
		}
		if (bodega == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE LEER LA INFORMACION DE LA CAJA FUERTE");
		}
	}
	
	/**
	 * 
	 * @param compra
	 * @param bodega
	 * @param estadojoya
	 * @param proceso
	 * @param codigoContrato
	 * @param idAgencia
	 * @throws RelativeException
	 */
	private void setDatosContratoByTipo(List<TbMiJoya> joyas, TbMiAgencia agencia,TbMiAgente agente, TbMiContrato compra,
			String codigoContrato,Long idAgencia, TbMiBodega bodega) throws RelativeException{
		log.info("------------> iNGRESA A setDatosContratoByTipo codigoContrato " + codigoContrato + "  idAgencia "  + idAgencia  );
		if (compra.getTipoCompra().equals(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)) {
			compra.setProceso(ProcesoEnum.COMPRA_DIRECTA);
			codigoContrato = this.codigoContrato(MidasOroConstantes.COMPRA_DIRECTA, idAgencia);
		} else if (compra.getTipoCompra().equals(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX)) {
			compra.setProceso(ProcesoEnum.COMPRA_PLAZO);
			codigoContrato = this.codigoContrato(MidasOroConstantes.COMPRA_PLAZOS, idAgencia);
		}
		log.info("------------> iNGRESA A setDatosContratoByTipo codigoContrato geerado " + codigoContrato  );
		this.validateContrato(joyas, compra, idAgencia, agencia, agente,bodega);
		compra.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
		compra.setProceso(this.getProcesoActual( compra.getTipoCompra() ));
		compra.setCodigo(codigoContrato);
		compra.setTbMiAgencia(agencia);
		compra.setTbMiAgente(agente);
		compra.getTbMiFunda().setTbMiBodega(bodega);
	}
	
	/**
	 * Metodo que obtiene la boodega por agencia y tipo compra
	 * @param tipoCompra
	 * @param idAgencia
	 * @return
	 * @throws RelativeException
	 */
	private TbMiBodega getBodegaActual(String tipoCompra, Long idAgencia ) throws RelativeException{
		if (tipoCompra.equalsIgnoreCase(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)) {
			return this.mos.findBodegaByAgenciaAndNombre(idAgencia, MidasOroConstantes.CAJA_FUERTE);
		} else  if (tipoCompra.equalsIgnoreCase(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX))  {
			return this.mos.findBodegaByAgenciaAndNombre(idAgencia, MidasOroConstantes.CAJA_FUERTE_CUSTODIA);
		}
		return null;
	}
	
	private ProcesoEnum getProcesoActual( String tipoCompra ) {
		if (tipoCompra.equals(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)) {
			return ProcesoEnum.COMPRA_DIRECTA;
		} else if (tipoCompra.equals(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX)) {
			return  ProcesoEnum.COMPRA_PLAZO;
		}
		return null;
	}
	
	private EstadoJoyaEnum getEstadoJoyaActual( String tipoCompra ) {
		if (tipoCompra.equals(MidasOroConstantes.CODIGO_COMPRA_DIRECTO_PREFIX)) {
			return EstadoJoyaEnum.EXISTENCIA;
		} else if (tipoCompra.equals(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX)) {
			return  EstadoJoyaEnum.CUSTODIA;
		}
		return null;
	}
	
	private void setUsuarioContrato(TbMiContrato compra,String usuario) {
		log.info(()->"------------> iNGRESA A REGISTRAT CONTRATO CON ID " + compra.getId() + "  usuario "  + usuario  );
		if( compra.getId() == null ) {
			compra.setUsuarioCreacion( usuario);
		} else {
			compra.setUsuarioActualizacion( usuario);
		}
	}
	
		
	private void setUsuarioInventario(TbMiInventario compra,String usuario) {
		log.info(()->"------------> iNGRESA A REGISTRAT INVENTARIO CON ID " + compra.getId() + "  usuario "  + usuario  );
		if( compra.getId() == null ) {
			compra.setUsuarioCreacion( usuario);
		} else {
			compra.setUsuarioActualizacion( usuario);
		}
	}
	
	private void setUsuarioMovimientoInventario(TbMiMovInventario compra,String usuario) {
		log.info(()->"------------> iNGRESA A REGISTRAT MOVIMIENTO INVENTARIO CON ID " + compra.getId() + "  usuario "  + usuario  );
		if( compra.getId() == null ) {
			compra.setUsuarioCreacion( usuario);
		} else {
			compra.setUsuarioActualizacion( usuario);
		}
	}
	
	/**
	 * Metodo que se encarga del resgitro de contratos, joyas e inventario
	 * @param joyas
	 * @param contratoCalculos
	 * @param compra
	 * @param idAgencia
	 * @param usuario
	 * @param idAgente
	 * @return
	 * @throws RelativeException
	 */
	public TbMiContrato guardarContrato(List<TbMiJoya> joyas, List<TbMiContratoCalculo> contratoCalculos,
			TbMiContrato compra, Long idAgencia, String usuario, Long idAgente) throws RelativeException {

		try {
			EstadoJoyaEnum estadojoya = EstadoJoyaEnum.COTIZADO;
			String codigoContrato = null;
			TbMiBodega bodega = new TbMiBodega();
			TbMiAgencia agencia = this.mos.findAgenciaById(idAgencia);
			TbMiAgente agente = this.mos.findAgenteById(idAgente);
			TbMiContrato tbMiContrato;
			this.validateContrato(joyas, compra, idAgencia, agencia, agente,bodega);
			this.guardarTipoOro(compra.getTbMiFunda(), usuario);
			bodega= this.getBodegaActual(compra.getTipoCompra(), idAgencia);
			setDatosContratoByTipo(joyas, agencia, agente,compra, codigoContrato,idAgencia,bodega);
			
			log.info("===>>>>.contrato setDatosContratoByTipo<<<===== " + compra.getProceso() + " -bodega "+ bodega.getId() + " - "  + compra.getEstado() + " - " +  codigoContrato + " -* " +  compra.getCodigo() );
			this.setUsuarioContrato(compra, usuario);
			this.mos.manageFunda(compra.getTbMiFunda());
			tbMiContrato = this.mos.manageContrato(compra);
			log.info("===>>>>.contrato guardado<<<===== " + tbMiContrato.getId() + " - " +  codigoContrato );
			if (contratoCalculos != null) {
				this.mos.addContratoCalculoBatch(tbMiContrato, contratoCalculos);
				log.info("===>>>>.calculos guardados guardado<<<=====");
			}
			this.manageJoyasAndInventario(joyas, tbMiContrato, this.getEstadoJoyaActual( tbMiContrato.getTipoCompra() ), bodega, usuario, tbMiContrato.getCodigo() ,tbMiContrato.getProceso());
			log.info("===>>>>.joyas e invetario guardados guardado<<<=====");
			return tbMiContrato;
		} catch (Exception e) {
			throw e;
		}
	}

	private List<TbMiCompraOro> guardarTipoOro(TbMiFunda funda, String usuario) throws RelativeException {
		List<TbMiTipoOro> tipoOroList = mos.findTipoOroByEstado(null, EstadoMidasEnum.ACT);
		if (tipoOroList == null || tipoOroList.isEmpty()) {
			throw new RelativeException(
					Constantes.ERROR_CODE_CUSTOM + "AL GUARDAR TIPOS DE ORO PARA LAS JOYAS DEL CONTRATO");
		}
		List<TbMiCompraOro> list = new ArrayList<>();
		for (TbMiTipoOro o : tipoOroList) {
			TbMiCompraOro tipoOroByContrato = new TbMiCompraOro();
			tipoOroByContrato.setEstado(EstadoMidasEnum.ACT);
			tipoOroByContrato.setFechaCreacion(new Date());
			tipoOroByContrato.setPorcentajeDescuento(null);
			tipoOroByContrato.setPorcentajePureza(o.getPorcentajePureza());
			tipoOroByContrato.setPrecioOroCd(o.getPrecioOroCd());
			tipoOroByContrato.setPrecioOroCp(o.getPrecioOroCp());
			tipoOroByContrato.setQuilate(o.getQuilate());
			tipoOroByContrato.setTbMiFunda(funda);
			tipoOroByContrato.setUsuarioCreacion(usuario);
			tipoOroByContrato.setValor(null);
			tipoOroByContrato.setPrecioOroVenta( o.getPrecioOroVenta() );
			list.add(mos.manageCompraOro(tipoOroByContrato));
		}
		return list;

	}
	
	/**
	 * Metodo que llena la joya y crea el registro de la misma
	 * @param index
	 * @param actual
	 * @param tbMiContrato
	 * @param codigoContrato
	 * @param estadojoya
	 * @param proceso
	 * @return
	 * @throws RelativeException
	 */
	private TbMiJoya createJoya(int index, TbMiJoya actual,TbMiContrato tbMiContrato,String codigoContrato,
			EstadoJoyaEnum estadojoya,ProcesoEnum proceso, String usuario ) throws RelativeException{
		TbMiCompraOro tipoOroCompra = mos.findCompraOroByQuilateAndFunda(
				actual.getTbMiCompraOro().getQuilate(), tbMiContrato.getTbMiFunda().getId());
		TbMiJoya joya = new TbMiJoya();
		joya.setComentario(actual.getComentario());
		joya.setCondicion(actual.getCondicion());
		joya.setDescuento(actual.getDescuento());
		joya.setTbMiFunda(tbMiContrato.getTbMiFunda());
		joya.setTbMiContrato(tbMiContrato);
		joya.setPeso(actual.getPeso());
		joya.setPesoBruto(actual.getPesoBruto());
		joya.setTbMiTipoJoya(actual.getTbMiTipoJoya());
		joya.setTbMiCompraOro(tipoOroCompra);
		joya.setValor(actual.getValor());
		joya.setCodigoJoya(codigoContrato.concat("-").concat(String.valueOf(index + 1)));
		joya.setEstado(estadojoya);
		joya.setProceso(proceso);
		joya.setUsuario( usuario );
		return this.mos.manageJoya(joya);
	}
	
	/**
	 * Metodo que crea el inventario de la joya
	 * @param actual
	 * @param estadojoya
	 * @param bodega
	 * @param usuario
	 * @param codigoContrato
	 * @param proceso
	 * @return
	 */
	private TbMiInventario createInventario(TbMiJoya actual,EstadoJoyaEnum estadojoya,
			TbMiBodega bodega, String usuario, ProcesoEnum proceso) throws RelativeException  {
		TbMiInventario inventario = new TbMiInventario();
		inventario.setEstado(estadojoya);
		inventario.setTbMiBodega(bodega);
		//inventario.setUsuarioCreacion(usuario);
		inventario.setProceso(proceso);
		inventario.setTbMiJoya(actual);
		this.setUsuarioInventario(inventario, usuario);
		return  this.mos.manageInventario(inventario);
	}
	
	/**
	 * Metodo que crea el movimiento aosicado a unajoya e inventario y bodega
	 * @param actual
	 * @param estadojoya
	 * @param bodega
	 * @param usuario
	 * @param proceso
	 * @return
	 * @throws RelativeException
	 */
	private TbMiMovInventario createMovimientoInventario( TbMiInventario actual, EstadoJoyaEnum estadojoya,
			TbMiBodega bodega, String usuario, ProcesoEnum proceso) throws RelativeException {
		TbMiMovInventario movimiento = new TbMiMovInventario();
		movimiento.setTbMiBodega(bodega);
		//movimiento.setUsuarioCreacion(usuario);
		//movimiento.setUsuarioActualizacion(usuario);
		movimiento.setEstado(estadojoya);
		movimiento.setProceso(proceso);
		movimiento.setTbMiInventario(actual);
		this.setUsuarioMovimientoInventario(movimiento, usuario);
		return this.mos.manageMovInventario(movimiento);
	}

	/**
	 * Metodo que se encarga del registro de joyas, con su inventario y movimientos
	 * @param joyas
	 * @param tbMiContrato
	 * @param estadojoya
	 * @param bodega
	 * @param usuario
	 * @param codigoContrato
	 * @param proceso
	 * @throws RelativeException
	 */
	private void manageJoyasAndInventario(List<TbMiJoya> joyas, TbMiContrato tbMiContrato, EstadoJoyaEnum estadojoya,
			TbMiBodega bodega, String usuario, String codigoContrato,ProcesoEnum proceso) throws RelativeException {
		log.info("===>>>>.joyas<<<=====" + joyas);
		String listJoyaError = StringUtils.EMPTY;
		for (int i = 0; i < joyas.size(); i++) {
			log.info("<<<===guardar joyas=====>>");
			log.info("<<<===guardar joyas CON BODEGA =====>> " + bodega.getId());
			try {
				bodega= this.mos.findBodegaById( bodega.getId() );
				this.createMovimientoInventario(this.createInventario(
						this.createJoya(i, joyas.get(i), tbMiContrato, codigoContrato, estadojoya, proceso, usuario), 
						estadojoya, bodega, usuario, proceso),
						estadojoya, bodega, usuario, proceso); 
				log.info(">>>=====guardo joyas e inventario<<<<=====");
			} catch (Exception e) {
				e.printStackTrace();
				listJoyaError = listJoyaError.concat("codigo contrato: ").concat( codigoContrato ).concat( "  " ).concat( String.valueOf(i) ).concat("; ")
						+ e.getMessage();
			}
		}

		if (!listJoyaError.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUDO GUARDAR LAS JOYAS.. " + listJoyaError);
		}
	}

	public Boolean validarFinSemanaFeriados(Date fecha) throws RelativeException {
		if (fecha == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "LA FECHA NO PUEDE SER VACIO");
		}
		if (fecha.getDay() == 0 || fecha.getDay() == 6) {
			return true;
		} else {
			String diasFeriados = parametroRepository.findByNombre(MidasOroConstantes.PARAM_DIAS_FERIADOS).getValor();
			if(diasFeriados != null && !diasFeriados.isEmpty()) {
				String[] feriados = diasFeriados.split(",");
				for(int i =0; i < feriados.length; i++) {
					Date f = MidasOroUtil.formatSringToDate(feriados[i]);
					if(f.getDate() == fecha.getDate() && f.getMonth() == fecha.getMonth()) {
						return true;
					}
				}
			}
			
		}

		return false;
	}

}
