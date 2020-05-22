package com.relative.midas.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoFundaEnum;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.enums.TipoAgenciaEnum;
import com.relative.midas.enums.TipoBodegaEnum;
import com.relative.midas.enums.TipoDocumentoEnum;
import com.relative.midas.model.Canton;
import com.relative.midas.model.CantonPK;
import com.relative.midas.model.Provincia;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiCompraOro;
import com.relative.midas.model.TbMiContactabilidad;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiCotizacion;
import com.relative.midas.model.TbMiCuenta;
import com.relative.midas.model.TbMiDetalleCaja;
import com.relative.midas.model.TbMiDetalleMeta;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiEgreso;
import com.relative.midas.model.TbMiFolletoLiquidacion;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiFundaRango;
import com.relative.midas.model.TbMiHistoricoJoya;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.model.TbMiJoyaSim;
import com.relative.midas.model.TbMiLiquidacion;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiMeta;
import com.relative.midas.model.TbMiMovEntreCaja;
import com.relative.midas.model.TbMiMovInventario;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.model.TbMiNotificacion;
import com.relative.midas.model.TbMiParametro;
import com.relative.midas.model.TbMiRenovacion;
import com.relative.midas.model.TbMiTipoDocumento;
import com.relative.midas.model.TbMiTipoJoya;
import com.relative.midas.model.TbMiTipoOro;
import com.relative.midas.model.TbMiVentaLote;
import com.relative.midas.repository.AbonoRepository;
import com.relative.midas.repository.AgenciaRepository;
import com.relative.midas.repository.AgenteRepository;
import com.relative.midas.repository.AprobarContratoRepository;
import com.relative.midas.repository.BancoRepository;
import com.relative.midas.repository.BodegaRepository;
import com.relative.midas.repository.CajaRepository;
import com.relative.midas.repository.CantonRepository;
import com.relative.midas.repository.ClienteRepository;
import com.relative.midas.repository.CompraOroRepository;
import com.relative.midas.repository.ContactabilidadRepository;
import com.relative.midas.repository.ContratoCalculoRepository;
import com.relative.midas.repository.ContratoRepository;
import com.relative.midas.repository.CorteCajaRepository;
import com.relative.midas.repository.CotizacionRepository;
import com.relative.midas.repository.CuentaRepository;
import com.relative.midas.repository.DetalleCajaRepository;
import com.relative.midas.repository.DetalleMetaRepository;
import com.relative.midas.repository.DocumentoHabilitanteRepository;
import com.relative.midas.repository.EgresoRepository;
import com.relative.midas.repository.FolletoLiquidacionRepository;
import com.relative.midas.repository.FundaRangoRepository;
import com.relative.midas.repository.FundaRepository;
import com.relative.midas.repository.HistoricoJoyaRepository;
import com.relative.midas.repository.InventarioRepository;
import com.relative.midas.repository.JoyaLoteRepository;
import com.relative.midas.repository.JoyaRepository;
import com.relative.midas.repository.JoyaSimRepository;
import com.relative.midas.repository.LiquidacionRepository;
import com.relative.midas.repository.LoteRepository;
import com.relative.midas.repository.MetaRepository;
import com.relative.midas.repository.MovEntreCajaRepository;
import com.relative.midas.repository.MovInventarioRepository;
import com.relative.midas.repository.MovimientoCajaRepository;
import com.relative.midas.repository.NotificacionRepository;
import com.relative.midas.repository.ParametroRepository;
import com.relative.midas.repository.ProvinciaRepository;
import com.relative.midas.repository.RenovacionRepository;
import com.relative.midas.repository.TipoDocumentoRepository;
import com.relative.midas.repository.TipoJoyaRepository;
import com.relative.midas.repository.TipoOroRepository;
import com.relative.midas.repository.VentaLoteRepository;
import com.relative.midas.repository.spec.AgenciaByEstadoSpec;
import com.relative.midas.repository.spec.AgenciaByJoyaEstadoSpec;
import com.relative.midas.repository.spec.BodegaByAgencia;
import com.relative.midas.repository.spec.BodegaByParamsSpec;
import com.relative.midas.repository.spec.CajaByAgenciaId;
import com.relative.midas.repository.spec.ContactabilidadByParams;
import com.relative.midas.repository.spec.ContratoByIdAgenciaEstadoSpec;
import com.relative.midas.repository.spec.ContratoSearchByCustomFilterSpec;
import com.relative.midas.repository.spec.FolletoLiquidacionByParamsSpec;
import com.relative.midas.repository.spec.FundaRangoByRangoInicialSpec;
import com.relative.midas.repository.spec.LiquidacionByFolletoLiquidacionSpec;
import com.relative.midas.repository.spec.LoteByEstadoAgencia;
import com.relative.midas.repository.spec.LoteByIdVentaLoteSpec;
import com.relative.midas.repository.spec.LoteByNombreLoteSpec;
import com.relative.midas.repository.spec.MovEntreCajaByParamsSpec;
import com.relative.midas.repository.spec.VentaLoteByFechaCodigoSpec;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.AprobacionWrapper;
import com.relative.midas.wrapper.CotizacionWrapper;
import com.relative.midas.wrapper.FileWrapper;
import com.relative.midas.wrapper.HabilitanteWrapper;
import com.relative.midas.wrapper.JoyaVentaVitrina;
import com.relative.midas.wrapper.MovimientoInventarioWrapper;
import com.relative.midas.wrapper.RetazarContratos;
import com.relative.midas.wrapper.RetazarJoya;
import com.relative.midas.wrapper.TrazabilidadWrapper;
import com.relative.midas.wrapper.VentaJoyaWrapper;
import com.relative.midas.wrapper.VentaLoteWrapper;

@Stateless
public class MidasOroService {
	@Inject
	Logger log;
	@Inject
	private FundaRangoRepository fundaRangoRepository;
	@Inject
	private JoyaRepository joyaRepository;
	@Inject
	private AgenciaRepository agenciaRepository;
	@Inject
	private AgenteRepository agenteRepository;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private ContratoRepository contratoRepository;
	@Inject
	private CotizacionRepository cotizacionRepository;
	@Inject
	private FundaRepository fundaRepository;
	@Inject
	private HistoricoJoyaRepository historicoJoyaRepository;
	@Inject
	private InventarioRepository inventarioRepository;
	@Inject
	private JoyaSimRepository joyaSimRepository;
	@Inject
	private RenovacionRepository renovacionRepository;
	@Inject
	private CantonRepository cantonRepository;
	@Inject
	private TipoJoyaRepository tipoJoyaRepository;
	@Inject
	private TipoOroRepository tipoOroRepository;
	@Inject
	private ProvinciaRepository provinciaRepository;
	@Inject
	private LiquidacionRepository liquidacionRepository;

	@Inject
	private EgresoRepository egresoRepository;

	@Inject
	private ParametrosSingleton parametrosSingleton;

	@Inject
	private ParametroRepository parametroRepository;

	@Inject
	private TipoDocumentoRepository documentoRepository;

	@Inject
	private AbonoRepository abonoRepository;
	@Inject
	private DocumentoHabilitanteRepository documentoHabilitanteRepository;

	@Inject
	private MovInventarioRepository movInventarioRepository;

	@Inject
	private BodegaRepository bodegaRepository;

	@Inject
	private LoteRepository loteRepository;

	@Inject
	private CorteCajaRepository corteCajaRepository;

	@Inject
	private MovimientoCajaRepository movimientoCajaRepository;

	@Inject
	private CajaRepository cajaRepository;
	@Inject
	private CuentaRepository cuentaRepository;
	@Inject
	private DetalleCajaRepository detalleCajaRepository;

	@Inject
	private JoyaLoteRepository joyaLoteRepository;
	@Inject
	private BancoRepository bancoRepository;
	@Inject
	private VentaLoteRepository ventaLoteRepository;

	@Inject
	private NotificacionRepository notificacionRepository;

	@Inject
	private MovEntreCajaRepository movEntreCajaRepository;

	@Inject
	private FolletoLiquidacionRepository folletoLiquidacionRepository;

	@Inject
	private ContratoCalculoRepository contratoCalculoRepository;

	@Inject
	private MetaRepository metaRepository;

	@Inject
	private DetalleMetaRepository detalleMetaRepository;

	@Inject
	private ContactabilidadRepository contactabilidadRepository;

	@Inject
	private MovimientoCajaOroService movimientoCajaOroService;
	@Inject
	private AprobarContratoRepository aprobarContratoRepository;
	@Inject
	private CompraOroRepository compraOroRepository;
	@Inject
	private MovimientoOroService movimientoOroService;

	/**
	 * Joya
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiJoya findJoyaById(Long id) throws RelativeException {
		try {
			return joyaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Busca las joyas para la venta en vitrina
	 * 
	 * @param pw
	 * @param codigoJoya
	 * @param idTipoJoya
	 * @return
	 */
	public List<TbMiJoya> venderJoyaByCodigoAndTipo(PaginatedWrapper pw, String codigoJoya, Long idTipoJoya)
			throws RelativeException {
		List<EstadoJoyaEnum> estados = new ArrayList<EstadoJoyaEnum>();
		estados.add(EstadoJoyaEnum.EXISTENCIA_LOTE);
		estados.add(EstadoJoyaEnum.EXISTENCIA_VITRINA);
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			return this.joyaRepository.findJoyaByParams(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), estados, codigoJoya, idTipoJoya);

		} else {
			return this.joyaRepository.findJoyaByParams(estados, codigoJoya, idTipoJoya);
		}
	}

	public Long venderJoyaByCodigoAndTipo(String codigoJoya, Long idTipoJoya) throws RelativeException {
		List<EstadoJoyaEnum> estados = new ArrayList<EstadoJoyaEnum>();
		estados.add(EstadoJoyaEnum.EXISTENCIA_LOTE);
		estados.add(EstadoJoyaEnum.EXISTENCIA_VITRINA);
		return this.joyaRepository.countJoyaByParams(estados, codigoJoya, idTipoJoya);
	}

	/**
	 * Metodo que busca la joya y sus movimientos de inventario
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public MovimientoInventarioWrapper findJoyaByIdWithInventory(Long idJoya) throws RelativeException {
		try {
			MovimientoInventarioWrapper wp = new MovimientoInventarioWrapper();
			TbMiJoya tmp = joyaRepository.findById(idJoya);
			if (tmp == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "JOYA NO ENCONTRADA");
			}
			List<TrazabilidadWrapper> inv = this.inventarioRepository.findTrazabilidad(idJoya);
			wp.setJoya(tmp);
			wp.setMovimientosInventario(inv);

			return wp;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "findJoyaByIdWithInventory");
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countJoya() throws RelativeException {
		try {
			return joyaRepository.countAll(TbMiJoya.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Joya no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiJoya> findAllJoya(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.joyaRepository.findAll(TbMiJoya.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.joyaRepository.findAll(TbMiJoya.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.joyaRepository.findAll(TbMiJoya.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que busca las joyas para acceder a su inventario
	 * 
	 * @param pw
	 * @param codigoJoya
	 * @param estadosJoya
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiJoya> findJoyaByCodigoJoyaEstadosFechas(PaginatedWrapper pw, String codigoJoya,
			List<EstadoJoyaEnum> estadosJoya, Date fechaDesde, Date fechaHasta) throws RelativeException {
		return this.joyaRepository.findByCodigoJoyaEstadosFechas(pw, codigoJoya, estadosJoya, fechaDesde, fechaHasta);
	}

	public Long countJoyaByCodigoJoyaEstadosFechas(String codigoJoya, List<EstadoJoyaEnum> estadosJoya, Date fechaDesde,
			Date fechaHasta) throws RelativeException {
		return this.joyaRepository.countByCodigoJoyaEstadosFechas(codigoJoya, estadosJoya, fechaDesde, fechaHasta);
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiJoya manageJoya(TbMiJoya send) throws RelativeException {
		try {
			log.info("==> entra a manage Joya");
			TbMiJoya persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findJoyaById(send.getId());
				return this.updateJoya(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				TbMiJoya joya = joyaRepository.add(send);
				this.createHistoricoJoya(joya, "REGISTRO DE NUEVA JOYA");
				return joya;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Joya " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiJoya updateJoya(TbMiJoya send, TbMiJoya persisted) throws RelativeException {
		try {
			TbMiJoya joya;
			persisted.setCodigoJoya(send.getCodigoJoya());
			persisted.setComentario(send.getComentario());
			persisted.setCondicion(send.getCondicion());
			persisted.setDescuento(send.getDescuento());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setPeso(send.getPeso());
			persisted.setPesoBruto(send.getPesoBruto());
			persisted.setValor(send.getValor());
			persisted.setPrecioVenta(send.getPrecioVenta());
			persisted.setTbMiContrato(send.getTbMiContrato());
			persisted.setTbMiFunda(send.getTbMiFunda());
			persisted.setTbMiTipoJoya(send.getTbMiTipoJoya());
			persisted.setTbMiCompraOro(send.getTbMiCompraOro());
			persisted.setPesoBrutoRetazado(send.getPesoBrutoRetazado());
			persisted.setDescuentoRetazado(send.getDescuentoRetazado());
			persisted.setPesoNetoRetazado(send.getPesoNetoRetazado());
			joya = joyaRepository.update(persisted);
			this.createHistoricoJoya(joya, "REGISTRO UPDATE JOYA");
			return joya;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Joya " + e.getMessage());
		}
	}

	public void createHistoricoJoya(TbMiJoya joya, String comment) throws RelativeException {
		TbMiHistoricoJoya hj = new TbMiHistoricoJoya();
		hj.setValor(joya.getValor());
		hj.setComentario(comment);
		hj.setDescuento(joya.getDescuento());
		hj.setEstado(joya.getEstado());
		hj.setFechaCreacion(new Date());
		hj.setIdTipoOro(joya.getTbMiCompraOro().getId());
		hj.setPesoBruto(joya.getPesoBruto());
		hj.setPesoNeto(joya.getPeso());
		hj.setTbMiJoya(joya);
		hj.setUsuarioCreacion(joya.getUsuario());
		hj.setUsuarioActualizacion(joya.getUsuario());
		hj.setProceso(joya.getProceso());
		this.manageHistoricoJoya(hj);
	}

	/**
	 * Metodo que retorna joyas por codigo contrato
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiJoya> findJoyaByCodigoContrato(PaginatedWrapper pw, String codigo) throws RelativeException {
		try {
			return this.joyaRepository.findByCodigoContratoPaged(pw, codigo);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna la cantidad joyas por codigo contrato
	 * 
	 * @param codigo
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Integer countJoyaByCodigoContrato(String codigo) throws RelativeException {
		try {
			return this.joyaRepository.countByCodigoContrato(codigo).intValue();
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna una clase personalizada con los datos para llenar el
	 * formulario Retazar y Trasladar
	 * 
	 * @param codigoJoya
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public RetazarJoya joyaByCodigoRetazar(String codigoJoya) throws RelativeException {
		try {
			List<RetazarJoya> lista = this.joyaRepository.findByCodigoJoya(codigoJoya);
			if (lista != null && !lista.isEmpty()) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Actualiza peso neto retazado, peso bruto retazado, descuento retazado de la
	 * joya y el lote y crea un historico joya
	 * 
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public TbMiJoyaLote retazarJoya(TbMiJoyaLote send) throws RelativeException {
		try {

			List<TbMiJoya> joyas;
			TbMiJoyaLote lote = new TbMiJoyaLote();
			TbMiJoya joyaPersisted = this.findJoyaById(send.getTbMiJoya().getId());
			joyaPersisted.setTbMiCompraOro(send.getTbMiJoya().getTbMiCompraOro());
			joyaPersisted.setPesoBrutoRetazado(send.getTbMiJoya().getPesoBrutoRetazado());
			joyaPersisted.setDescuentoRetazado(send.getTbMiJoya().getDescuentoRetazado());
			joyaPersisted.setPesoNetoRetazado(send.getTbMiJoya().getPesoNetoRetazado());
			joyaPersisted.setEstado(send.getEstado());
			joyaPersisted.setValor(send.getTbMiJoya().getValor());
			// joyaPersisted.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
			joyaPersisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			joyaPersisted.setUsuario(send.getUsuarioActualizacion());
			joyaPersisted.setProceso(ProcesoEnum.RETAZAR_TRASLADAR);
			joyaPersisted = this.manageJoya(joyaPersisted);
			TbMiJoyaLote joyaLotePersisted = new TbMiJoyaLote();
			joyaLotePersisted.setTbMiJoya(joyaPersisted);
			joyaLotePersisted.setTbMiLote(send.getTbMiLote());
			joyaLotePersisted.setEstado(joyaPersisted.getEstado());
			joyaLotePersisted.setUsuarioCreacion(send.getUsuarioCreacion());
			joyaLotePersisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			if (send.getTbMiJoya().getEstado().equals(EstadoJoyaEnum.RETAZADA_LOTE)) {
				send.getTbMiJoya().setEstado(EstadoJoyaEnum.EXISTENCIA_LOTE);
			} else if (send.getTbMiJoya().getEstado().equals(EstadoJoyaEnum.RETAZADA_VITRINA)) {
				send.getTbMiJoya().setEstado(EstadoJoyaEnum.EXISTENCIA_VITRINA);
			}
			joyaLotePersisted = this.manageJoyaLote(joyaLotePersisted);
			TbMiBodega bodega = this.findBodegaByAgenciaAndNombre(
					joyaLotePersisted.getTbMiLote().getTbMiAgencia().getId(), MidasOroConstantes.CAJA_FUERTE);

			send.getTbMiJoya().setId(joyaPersisted.getId());
			lote.setTbMiJoya(send.getTbMiJoya());
			joyas = this.joyaRepository.findByIdFunda(null, joyaPersisted.getTbMiFunda().getId());
			if (joyas == null || joyas.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE ENTRAR LAS JOYAS DE LA FUNDA");
			}
			joyas.removeIf(f -> f.getEstado().compareTo(EstadoJoyaEnum.EXISTENCIA) == 0
					|| f.getEstado().compareTo(EstadoJoyaEnum.PERFECCIONADO) == 0);
			log.info("------->>>> joyas lote" + joyas);
			if (joyas.size() == this.joyaRepository.countByIdFunda(joyaPersisted.getTbMiFunda().getId())) {
				for (TbMiJoya j : joyas) {
					if (j.getEstado().compareTo(EstadoJoyaEnum.RETAZADA_LOTE) == 0) {
						j.setEstado(EstadoJoyaEnum.EXISTENCIA_LOTE);
					} else if (j.getEstado().compareTo(EstadoJoyaEnum.RETAZADA_VITRINA) == 0) {
						j.setEstado(EstadoJoyaEnum.EXISTENCIA_VITRINA);
					}
					j = this.manageJoya(j);

					this.movimientoOroService.movimientoInventario(j.getId(), bodega.getId(),
							joyaLotePersisted.getUsuarioCreacion(), j.getEstado(), j.getProceso());
					TbMiJoyaLote jl = this.findJoyaLoteByIdJoya(j.getId());
					this.updatePesosPrecioLote(j, jl.getTbMiLote());
					jl.setEstado(j.getEstado());
					manageJoyaLote(jl);

				}
			}

			return lote;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Joya " + e.getMessage());
		}
	}

	/**
	 * Busca las joyas pendiente de habilitantes
	 * 
	 * @param pw
	 * @param pendienteHabilitante
	 * @param identificacion
	 * @param codigoJoya
	 * @return
	 */
	public List<TbMiJoya> findJoyaByEstadoAndIdentificacion(PaginatedWrapper pw, EstadoJoyaEnum pendienteHabilitante,
			String identificacion, String codigoJoya) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return joyaRepository.findByEstadoAndComprador(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), pendienteHabilitante, identificacion, codigoJoya);
		} else {
			return joyaRepository.findByEstadoAndComprador(pendienteHabilitante, identificacion, codigoJoya);
		}
	}

	public Long findAbonoByEstadoAndIdentificacion(EstadoJoyaEnum pendienteHabilitante, String identificacion,
			String codigoJoya) throws RelativeException {

		return joyaRepository.countByEstadoAndComprador(pendienteHabilitante, identificacion, codigoJoya);
	}

	/*
	 * private void saveJoyaHistorico(TbMiJoya send) throws RelativeException {
	 * TbMiHistoricoJoya hJoya = new TbMiHistoricoJoya();
	 * hJoya.setComentario("Retazar y Trasladar");
	 * hJoya.setDescuento(send.getDescuento());
	 * hJoya.setIdTipoOro(send.getTbMiCompraOro().getId());
	 * hJoya.setPesoBruto(send.getPesoBruto()); hJoya.setPesoNeto(send.getPeso());
	 * hJoya.setTbMiJoya(send); hJoya.setEstado(send.getEstado());
	 * hJoya.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
	 * hJoya.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
	 * historicoJoyaRepository.add(hJoya); }
	 */

	/**
	 * Busca las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional. Ordena por fecha
	 * creacion movimiento por prioridad
	 * 
	 * @param pw
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<JoyaVentaVitrina> findByEstadoMvCodigoTipo(PaginatedWrapper pw, EstadoJoyaEnum estadoJoya,
			EstadoJoyaEnum estadoMvIn, String codigoJoya, Long idTipoJoya) throws RelativeException {
		return this.joyaRepository.findByEstadoMvCodigoTipo(pw, estadoJoya, estadoMvIn, codigoJoya, idTipoJoya);
	}

	/**
	 * Cuenta las joyas por estado obligatorio, estado movimiento inventario
	 * obligatorio, codigo joya opcional y tipo joya opcional.
	 * 
	 * @param pw
	 * @param estadoJoya
	 * @param estadoMvIn
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Integer countByEstadoMvCodigoTipo(PaginatedWrapper pw, EstadoJoyaEnum estadoJoya, EstadoJoyaEnum estadoMvIn,
			String codigoJoya, Long idTipoJoya) throws RelativeException {
		try {
			return this.joyaRepository.countByEstadoMvCodigoTipo(pw, estadoJoya, estadoMvIn, codigoJoya, idTipoJoya);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	public TbMiJoya changeEstadoById(Long id, EstadoJoyaEnum estado, Long precioVenta, String usuario)
			throws RelativeException {
		try {
			TbMiJoya persisted = this.findJoyaById(id);
			persisted.setEstado(estado);
			if (precioVenta != null) {
				persisted.setPrecioVenta(BigDecimal.valueOf(precioVenta));
			}
			return this.manageJoya(persisted);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"NO SE PUDO COMPLETAR CORECTAMENTE LA VENTA " + e.getMessage());
		}
	}

	public List<TbMiLote> updateJoyasByLotes(List<TbMiLote> listTbMiLote, String usuario) throws RelativeException {
		try {
			List<TbMiLote> listPersisteTbMiLote = new ArrayList<>();
			log.info("listTbMiLote != null && !listTbMiLote.isEmpty()"
					+ (listTbMiLote != null && !listTbMiLote.isEmpty()));
			if (listTbMiLote != null && !listTbMiLote.isEmpty()) {
				for (TbMiLote tbMiLote : listTbMiLote) {
					TbMiLote lote = this.loteRepository.findById(tbMiLote.getId());
					log.info("lote != null" + (lote != null));
					if (lote != null) {
						listPersisteTbMiLote.add(lote);
						List<TbMiJoyaLote> listTbMiVentaLote = this.findJoyaLoteByIdLote(lote.getId());
						log.info("listTbMiVentaLote != null && !listTbMiVentaLote.isEmpty()"
								+ (listTbMiVentaLote != null && !listTbMiVentaLote.isEmpty()));
						if (listTbMiVentaLote != null && !listTbMiVentaLote.isEmpty()) {
							for (TbMiJoyaLote tbMiJoyaLote : listTbMiVentaLote) {
								log.info("tbMiJoyaLote != null && tbMiJoyaLote.getTbMiJoya() != null"
										+ (tbMiJoyaLote != null && tbMiJoyaLote.getTbMiJoya() != null));
								if (tbMiJoyaLote != null && tbMiJoyaLote.getTbMiJoya() != null) {
									TbMiJoya persisted = tbMiJoyaLote.getTbMiJoya();
									log.info("persisted != null" + (persisted != null));

									if (persisted.getPesoNetoRetazado() == null
											|| tbMiLote.getCostoPorGramo() == null) {
										throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
												"Error peso retazado de la joya es nulo ó costo por gramo es nulo : Joya:"
														+ persisted.getId());
									}
									persisted.setFechaActualizacion(new Date());
									persisted.setUsuario(usuario);
									persisted.setEstado(EstadoJoyaEnum.VENDIDA_LOTE);
									persisted.setPrecioVenta(
											persisted.getPesoNetoRetazado().multiply(tbMiLote.getCostoPorGramo()));
									this.createHistoricoJoya(joyaRepository.update(persisted), "REGISTRO UPDATE JOYA");
								} else {
									throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
											"Erro joyalote no tiene joya : " + tbMiJoyaLote.getId());
								}
							}
						} else {
							throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
									"Error no existen JoyaLote : " + lote.getId());
						}
					} else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error el lote no existe : ");
					}
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "La lista de lotes enviada esta vacia ");
			}
			return listPersisteTbMiLote;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al actualizar las joyas por lote " + e.getMessage());
		}
	}

	/**
	 * Busca todas las joyas de la funda por idFunda
	 * 
	 * @param pw
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiJoya> findJoyaByIdFunda(PaginatedWrapper pw, Long idFunda) throws RelativeException {

		return this.joyaRepository.findByIdFunda(pw, idFunda);

	}

	/**
	 * Cuenta todas las joyas de la funda por idFunda
	 * 
	 * @param idFunda
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Long countJoyaByIdFunda(Long idFunda) throws RelativeException {
		return this.joyaRepository.countByIdFunda(idFunda);

	}

	public void deleteJoyaLoteByJoya(Long idJoya) throws RelativeException {
		this.joyaLoteRepository.deleteByJoya(idJoya);
	}

	/**
	 * Agencia
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiAgencia findAgenciaById(Long id) throws RelativeException {
		try {
			return agenciaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countAgencia() throws RelativeException {
		try {
			return agenciaRepository.countAll(TbMiAgencia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agencia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAgencia> findAllAgencia(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.agenciaRepository.findAll(TbMiAgencia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.agenciaRepository.findAll(TbMiAgencia.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.agenciaRepository.findAll(TbMiAgencia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiAgencia manageAgencia(TbMiAgencia send) throws RelativeException {
		try {
			log.info("==> entra a manage Agencia");
			TbMiAgencia persisted = null;
			if (!StringUtils.isNotBlank(send.getSeqCd()) || !StringUtils.isNotBlank(send.getSeqCp())) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "No se puede leer la secuencia");
			}
			if (send != null && send.getId() != null) {
				persisted = this.findAgenciaById(send.getId());
				return this.updateAgencia(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return agenciaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Agencia " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiAgencia updateAgencia(TbMiAgencia send, TbMiAgencia persisted) throws RelativeException {
		try {
			persisted.setCallePrincipal(send.getCallePrincipal());
			persisted.setCalleSecundaria(send.getCalleSecundaria());
			persisted.setCelular(send.getCelular());
			persisted.setCodigo(send.getCodigo());
			persisted.setComponente(send.getComponente());
			persisted.setCorreoElectronico(send.getCorreoElectronico());
			persisted.setCuenta(send.getCuenta());
			persisted.setDireccion(send.getDireccion());
			persisted.setEstado(send.getEstado());
			persisted.setTipoAgencia(send.getTipoAgencia());
			persisted.setTelefono(send.getTelefono());
			persisted.setFechaActualizacion(new Date());
			persisted.setCanton(send.getCanton());
			persisted.setNombreAgencia(send.getNombreAgencia());
			persisted.setPais(send.getPais());
			persisted.setReferencia(send.getPais());
			persisted.setSector(send.getSector());
			persisted.setTbMiAgente(send.getTbMiAgente());
			persisted.setTbMiAgenteSupervisor(send.getTbMiAgenteSupervisor());
			return this.agenciaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Agencia " + e.getMessage());
		}

	}

	/**
	 * Lista todas las agencias por joyas estado
	 * 
	 * @param pw
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public List<TbMiAgencia> findAgenciaByJoyaEstado(PaginatedWrapper pw, List<EstadoJoyaEnum> estados)
			throws RelativeException {
		try {
			if (pw == null) {
				return this.agenciaRepository.findAllBySpecification(new AgenciaByJoyaEstadoSpec(estados));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.agenciaRepository.findAllBySpecificationPaged(new AgenciaByJoyaEstadoSpec(estados),
							pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.agenciaRepository.findAllBySpecification(new AgenciaByJoyaEstadoSpec(estados));
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error no existen agencias " + e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public Long countAgenciaBySpecification(List<EstadoJoyaEnum> estados) throws RelativeException {
		try {
			return agenciaRepository.countBySpecification(new AgenciaByJoyaEstadoSpec(estados));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agencia no encontrado " + e.getMessage());
		}
	}

	public List<TbMiAgencia> findAgenciaByEstado(EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.agenciaRepository.findAllBySpecification(new AgenciaByEstadoSpec(estado));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al leer agencias por estado");
		}
	}

	/**
	 * Lista las Agencias por codigo y/o nombre
	 * 
	 * @param pw       PaginatedWrapper
	 * @param codigo.. codigo de la agencia
	 * @param nombre.. nombre de la agencia
	 * @author Jhon Romero
	 * @throws RelativeException
	 * @return
	 */
	public List<TbMiAgencia> findAgenciaByCodigoAndNombre(PaginatedWrapper pw, String codigo, String nombre)
			throws RelativeException {

		if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.agenciaRepository.findByCodigoAndNombre(codigo, nombre, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.agenciaRepository.findByCodigoAndNombre(codigo, nombre);

		}
	}

	/**
	 * Devuelve en numero de registros de la agencia para estos aprametros
	 * 
	 * @param codigo.. codigo de la agencia
	 * @param nombre.. nombre de la agencia
	 * @author Jhon Romero
	 * @throws RelativeException
	 * @return
	 */
	public Long countAgenciaByCodigoAndNombre(String codigo, String nombre) throws RelativeException {
		try {
			return agenciaRepository.countByCodigoAndNombre(codigo, nombre);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agencia no encontrado " + e.getMessage());
		}
	}

	public BigInteger generateCodigoAgencia() throws RelativeException {
		return agenciaRepository.generateCodigoAgencia();
	}

	public TbMiAgencia registrarAgencia(TbMiAgencia agencia) throws RelativeException {
		try {
			if (agencia != null && StringUtils.isNotBlank(agencia.getNombreAgencia())) {
				BigInteger cod = agenciaRepository.generateCodigoAgencia();
				List<TbMiAgencia> agenciaMatriz = null;
				if (cod == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL CREAR CODIGO DE AGENCIA");
				}
				TbMiCaja caja = new TbMiCaja();
				String codigoAgencia = StringUtils.leftPad(cod.toString(), 2, "0");
				agencia.setCodigo(codigoAgencia);
				agencia.setSeqCd("seq_".concat(codigoAgencia).concat("cd"));
				agencia.setSeqCp("seq_".concat(codigoAgencia).concat("cp"));
				agencia.setSeqVl("seq_".concat(codigoAgencia).concat("vl"));
				agencia.setSeqIE("seq_".concat(codigoAgencia).concat("ie"));
				if (agencia.getTipoAgencia().equals(TipoAgenciaEnum.MATRIZ)) {
					agenciaMatriz = agenciaRepository.finByTipoAgencia(agencia.getTipoAgencia());
					log.info("agencia matrizz>>>>>" + agenciaMatriz);
					if (agenciaMatriz != null && !agenciaMatriz.isEmpty()) {
						throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
								"SOLO SE PUEDE CREAR UNA AGENCIA MATRIZ");
					}
				}
				agencia = this.manageAgencia(agencia);
				// creando caja
				caja.setEstado(EstadoMidasEnum.ACT);
				caja.setFechaCreacion(new Date());
				caja.setSaldoActual(BigDecimal.ZERO);
				caja.setTbMiAgencia(agencia);
				caja.setUsuarioCreacion(agencia.getUsuarioCreacion());
				this.manageCaja(caja);
				// creando BODEGAS para la agencia

				if (agencia.getTipoAgencia().equals(TipoAgenciaEnum.MATRIZ)) {

					String[] componentes = MidasOroConstantes.BODEGA_MATRIZ.split(",");
					for (int i = 0; i < componentes.length; i++) {
						if (StringUtils.isNotBlank(componentes[i])) {
							crearBodega(agencia, componentes, i);
						}
					}
				} else if (agencia.getTipoAgencia().equals(TipoAgenciaEnum.SUCURSAL)) {
					String[] componentes = MidasOroConstantes.BODEGA_SUCURSAL.split(",");
					for (int i = 0; i < componentes.length; i++) {
						if (StringUtils.isNotBlank(componentes[i])) {
							crearBodega(agencia, componentes, i);
						}
					}
				}

				/*
				 * String[] componentes = agencia.getComponente().split(","); for(int i = 0; i<
				 * componentes.length;i++) { if(StringUtils.isNotBlank( componentes[i] ) ) {
				 * TbMiBodega bodega = new TbMiBodega();
				 * bodega.setDescripcion(agencia.getNombreAgencia().concat(" ").concat(
				 * componentes[i])); bodega.setEstado(EstadoMidasEnum.ACT);
				 * bodega.setFechaCreacion(new Date()); bodega.setNombre(componentes[i]);
				 * bodega.setTbMiAgencia(agencia);
				 * bodega.setUsuarioCreacion(agencia.getUsuarioCreacion());
				 * this.manageBodega(bodega); } }
				 */
				return agencia;

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUEDE LEER LA INFORMACION DE LA AGENCIA");
			}

		} catch (Exception e) {
			throw e;
		}

	}

	private void crearBodega(TbMiAgencia agencia, String[] componentes, int i) throws RelativeException {
		TbMiBodega bodega = new TbMiBodega();
		bodega.setDescripcion(agencia.getNombreAgencia().concat(" ").concat(componentes[i]));
		bodega.setEstado(EstadoMidasEnum.ACT);
		bodega.setFechaCreacion(new Date());
		bodega.setNombre(componentes[i]);
		bodega.setTbMiAgencia(agencia);
		bodega.setUsuarioCreacion(agencia.getUsuarioCreacion());
		bodega.setTipoBodega(MidasOroUtil.getEnumFromString(TipoBodegaEnum.class, componentes[i]));
		this.manageBodega(bodega);
	}

	/**
	 * Devuelve la agencia matriz
	 * 
	 * @return
	 * @throws RelativeException
	 */
	public TbMiAgencia findAgenciaMatriz() throws RelativeException {

		List<TbMiAgencia> tmp = agenciaRepository.finByTipoAgencia(TipoAgenciaEnum.MATRIZ);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}

	/**
	 * Agente
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiAgente findAgenteById(Long id) throws RelativeException {
		try {
			return agenteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countAgente() throws RelativeException {
		try {
			return agenteRepository.countAll(TbMiAgente.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agente no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAgente> findAllAgente(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.agenteRepository.findAll(TbMiAgente.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.agenteRepository.findAll(TbMiAgente.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.agenteRepository.findAll(TbMiAgente.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiAgente manageAgente(TbMiAgente send) throws RelativeException {
		try {
			log.info("==> entra a manage Agente");
			TbMiAgente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findAgenteById(send.getId());
				return this.updateAgente(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Date());
				return agenteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Agente " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiAgente updateAgente(TbMiAgente send, TbMiAgente persisted) throws RelativeException {
		try {
			persisted.setDireccion(send.getDireccion());
			persisted.setEmailPrincipal(send.getEmailPrincipal());
			persisted.setEmailSecundario(send.getEmailSecundario());
			persisted.setEstado(send.getEstado());

			persisted.setFechaActualizacion(new Date());
			persisted.setIdentificacion(send.getIdentificacion());
			persisted.setIdUsuario(send.getIdUsuario());
			persisted.setPrimerApellido(send.getPrimerApellido());
			persisted.setPrimerNombre(send.getPrimerNombre());
			persisted.setSegundoApellido(send.getSegundoApellido());
			persisted.setSegundoNombre(send.getSegundoNombre());
			persisted.setTelefonoPrincipal(send.getTelefonoPrincipal());
			persisted.setTelefonoSecundario(send.getTelefonoSecundario());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setNacionalidad(send.getNacionalidad());
			persisted.setEstadoCivil(send.getEstadoCivil());
			// persisted.setFechaNacimiento(send.getFechaNacimiento());
			// persisted.setLugarNacimiento(send.getLugarNacimiento());
			persisted.setNivelEstudios(send.getNivelEstudios());
			persisted.setPais(send.getPais());
			persisted.setSector(send.getSector());
			persisted.setCallePrincipal(send.getCallePrincipal());
			persisted.setInterseccion(send.getInterseccion());
			persisted.setNumeroDommicilio(send.getNumeroDommicilio());
			persisted.setReferenciaUbicacion(send.getReferenciaUbicacion());
			persisted.setTipoIdentificacion(send.getTipoIdentificacion());

			return agenteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Agente " + e.getMessage());
		}
	}

	/**
	 * Busca los agente sin asignar
	 * 
	 * @return TbMiAgente
	 * @author Jhon Romero
	 */
	public List<TbMiAgente> findAgenteSinAsignar() throws RelativeException {
		// TODO Auto-generated method stub
		return agenteRepository.agenteSinAsignar();
	}

	public TbMiAgente findAgenteOrSupervisorByUsername(String username) throws RelativeException {
		return agenteRepository.findAgenteOrSupervisorByNombreUsuario(username);
	}

	/**
	 * Busca agente por nombre de usuario y devuelve el primero que encuentre
	 * 
	 * @param username
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public TbMiAgente findAgenteByUsername(String username) throws RelativeException {
		return agenteRepository.findByNombreUsuario(username);
	}

	/**
	 * Metodo q lista todos los Agentes.
	 * 
	 * @param paguinatedWrapper
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return Lista de Agentes
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public List<TbMiAgente> findAgenteByParams(PaginatedWrapper pw, String nombre, String apellido,
			String identificacion) throws RelativeException {

		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.agenteRepository.findByParams(nombre, apellido, identificacion, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.agenteRepository.findByParams(nombre, apellido, identificacion);
		}

	}

	public Long countAgenteByParams(String nombre, String apellido, String identificacion) throws RelativeException {
		try {
			return agenteRepository.countByParams(nombre, apellido, identificacion);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agente no encontrado " + e.getMessage());
		}
	}

	/**
	 * Cliente
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiCliente findClienteById(Long id) throws RelativeException {
		try {
			return clienteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbMiCliente> clienteByIdentificacion(PaginatedWrapper pw, String identificacion)
			throws RelativeException {

		if (pw == null) {
			return clienteRepository.findClienteByIdentifiacion(identificacion);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return clienteRepository.findClienteByIdentifiacionPaged(identificacion, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

			} else {
				return clienteRepository.findClienteByIdentifiacionPaged(identificacion, pw.getStartRecord(),
						pw.getPageSize());
			}
		}

	}

	public Long countClienteByIdentificacion(String identificacion) throws RelativeException {
		try {
			return clienteRepository.countClienteByIdentificacion(identificacion);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Lote no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCliente() throws RelativeException {
		try {
			return clienteRepository.countAll(TbMiCliente.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cliente no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCliente> findAllCliente(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.clienteRepository.findAll(TbMiCliente.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.clienteRepository.findAll(TbMiCliente.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.clienteRepository.findAll(TbMiCliente.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiCliente manageCliente(TbMiCliente send) throws RelativeException {
		try {
			log.info("==> entra a manage Cliente");
			TbMiCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findClienteById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return clienteRepository.registrarCliente(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Cliente " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiCliente updateCliente(TbMiCliente send, TbMiCliente persisted) throws RelativeException {
		try {
			persisted.setIdentificacion(send.getIdentificacion());
			persisted.setNombre(send.getNombre());
			persisted.setApellido(send.getApellido());
			persisted.setDireccion(send.getDireccion());
			persisted.setTelefonoCelular(send.getTelefonoCelular());
			persisted.setTelefonoFijo(send.getTelefonoFijo());
			persisted.setTbMiContratos(send.getTbMiContratos());
			persisted.setNacionalidad(send.getNacionalidad());
			persisted.setTipoId(send.getTipoId());
			persisted.setFechaNacimiento(send.getFechaNacimiento());
			persisted.setLugarNacimiento(send.getLugarNacimiento());
			persisted.setEstadoCivil(send.getEstadoCivil());
			persisted.setNivelEstudios(send.getNivelEstudios());
			persisted.setEmail(send.getEmail());
			persisted.setSector(send.getSector());
			persisted.setCallePrincipal(send.getCallePrincipal());
			persisted.setNumeroDommicilio(send.getNumeroDommicilio());
			persisted.setInterseccion(send.getInterseccion());
			persisted.setReferenciaUbicacion(send.getReferenciaUbicacion());
			persisted.setOcupacionInmueble(send.getOcupacionInmueble());
			persisted.setEmpresa(send.getEmpresa());
			persisted.setOcupacion(send.getOcupacion());
			persisted.setIngresoMensual(send.getIngresoMensual());
			persisted.setDireccionTrabajo(send.getDireccionTrabajo());
			persisted.setTelefonoTrabajo(send.getTelefonoTrabajo());
			persisted.setTelefonoReferencia(send.getTelefonoReferencia());
			persisted.setNombreReferencia(send.getNombreReferencia());
			persisted.setCelularReferencia(send.getCelularReferencia());
			persisted.setCanton(send.getCanton());
			persisted.setEstado(send.getEstado());
			persisted.setUsuarioActualizacion(persisted.getUsuarioActualizacion());
			persisted.setFiguraPolitica(send.getFiguraPolitica());
			persisted.setDeudor(send.getDeudor());
			persisted.setCuentaBancaria(send.getCuentaBancaria());
			persisted.setTarjetaCredito(send.getTarjetaCredito());
			persisted.setSegundoNombre(send.getSegundoNombre());
			persisted.setSegundoApellido(send.getSegundoApellido());
			persisted.setJubilado(send.getJubilado());

			return clienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cliente " + e.getMessage());
		}
	}

	public List<TbMiCliente> findClienteByParams(PaginatedWrapper pw, String identificacion, String nombre,
			String apellido, String telefono, String celular, String correo, String secto, String ciudad,
			String nombreReferencia, String telefonoReferencia, String celularReferencia, EstadoMidasEnum estado)
			throws RelativeException {
		return this.clienteRepository.findByParams(pw, identificacion, nombre, apellido, telefono, celular, correo,
				secto, ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado);
	}

	public Long countClienteByParams(String identificacion, String nombre, String apellido, String telefono,
			String celular, String correo, String secto, String ciudad, String nombreReferencia,
			String telefonoReferencia, String celularReferencia, EstadoMidasEnum estado) throws RelativeException {
		return this.clienteRepository.countByParams(identificacion, nombre, apellido, telefono, celular, correo, secto,
				ciudad, nombreReferencia, telefonoReferencia, celularReferencia, estado);
	}

	/**
	 * Contrato
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiContrato findContratoById(Long id) throws RelativeException {
		try {
			return contratoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Busca un contrato por id contrato anterior
	 * 
	 * @param idContratoAnterior
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public TbMiContrato findContratoByIdAnterior(Long idContratoAnterior) throws RelativeException {
		return contratoRepository.findByIdAnterior(idContratoAnterior);
	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiContrato findContratoByCodigo(String codigo) throws RelativeException {
		try {
			return contratoRepository.findByCodigoContrato(codigo);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countContrato() throws RelativeException {
		try {
			return contratoRepository.countAll(TbMiContrato.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Contrato no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiContrato> findAllContrato(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.contratoRepository.findAll(TbMiContrato.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoRepository.findAll(TbMiContrato.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.contratoRepository.findAll(TbMiContrato.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que lista contratos por varios filtros
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiContrato> getContratosByFiltro(PaginatedWrapper pw, String fechaDesde, String fechaHasta,
			String codigo, List<EstadoContratoEnum> estado, String identificacion, String cliente, Boolean contratosVencidos, Long idAgencia)
			throws RelativeException {
		log.info("=======> getContratosByFiltro idagencia " + idAgencia);

		idAgencia = validateAgencia(idAgencia);
		if (pw == null) {
			return this.contratoRepository.findAllBySpecification(new ContratoSearchByCustomFilterSpec(fechaDesde,
					fechaHasta, codigo, estado, identificacion, cliente, contratosVencidos, idAgencia));
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoRepository.findPorCustomFilterContratos(pw, fechaDesde, fechaHasta, codigo, estado,
						identificacion, cliente, contratosVencidos, idAgencia);
			} else {
				return this.contratoRepository.findAllBySpecification(new ContratoSearchByCustomFilterSpec(fechaDesde,
						fechaHasta, codigo, estado, identificacion, cliente, contratosVencidos, idAgencia));
			}
		}
	}

	public Integer countContratosByFiltro(String fechaDesde, String fechaHasta, String codigo,
			List<EstadoContratoEnum> estado, String identificacion, String cliente, boolean contratosVencidos, Long idAgencia)
			throws RelativeException {
		idAgencia = validateAgencia(idAgencia);
		return this.contratoRepository.countBySpecification(new ContratoSearchByCustomFilterSpec(fechaDesde, fechaHasta,
				codigo, estado, identificacion,  cliente, contratosVencidos, idAgencia)).intValue();
	}
	
	public BigDecimal sumMontoContratosByFiltro(String fechaDesde, String fechaHasta, String codigo,
			List<EstadoContratoEnum> estado, String identificacion, String cliente,  boolean contratosVencidos, Long idAgencia)
			throws RelativeException {
		idAgencia = validateAgencia(idAgencia);
		return this.contratoRepository.sumMontoPorCustomFilterContratos( fechaDesde, fechaHasta,
				codigo, estado, identificacion, cliente, contratosVencidos, idAgencia);
	}

	private Long validateAgencia(Long idAgencia) throws RelativeException {
		if (idAgencia != null) {
			TbMiAgencia a = this.agenciaRepository.findById(idAgencia);
			// log.info("=======> getContratosByFiltro agencia encontrada es de tipo " +
			// a.getTipoAgencia().toString() );
			if (a != null && TipoAgenciaEnum.MATRIZ.compareTo(a.getTipoAgencia()) == 0) {
				idAgencia = null;
				log.info("=======> getContratosByFiltro agencia es matriz debe sacar todos los contratos " + idAgencia);

			}
		}
		return idAgencia;
	}

	/**
	 * Lista todos los contratos por idAgencia y contrato estado
	 * 
	 * @param pw
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public List<TbMiContrato> findContratoByIdentificacion(PaginatedWrapper pw, String identificacion)
			throws RelativeException {
		if (pw == null) {
			return this.contratoRepository.findByIdentificacionCliente(identificacion);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoRepository.findByIdentificacionClientePaged(identificacion, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.contratoRepository.findByIdentificacionClientePaged(identificacion, pw.getStartRecord(),
						pw.getPageSize());
			}
		}
	}

	public List<TbMiContrato> findContratoByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {
		if (pw == null) {
			return this.contratoRepository.findByIdCliente(idCliente);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoRepository.findByIdClientePaged(idCliente, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.contratoRepository.findByIdClientePaged(idCliente, pw.getStartRecord(), pw.getPageSize());
			}
		}
	}

	public Long countFindContratoByIdentificacion(String identificacion) throws RelativeException {
		return this.contratoRepository.countFindByIdentificacionCliente(identificacion);
	}

	public Long countFindContratoByIdCliente(Long idCliente) throws RelativeException {
		return this.contratoRepository.countFindByIdCliente(idCliente);
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiContrato manageContrato(TbMiContrato send) throws RelativeException {
		try {
			log.info("==> entra a manage Contrato");
			TbMiContrato persisted = null;
			if (send != null && send.getId() != null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				// send.setUsuarioActualizacion( send.getUsuarioActualizacion() );
				persisted = this.findContratoById(send.getId());
				return this.updateContrato(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return contratoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Contrato " + e.getMessage());
		}
	}

	public TbMiContrato devolverJoya(TbMiContrato send, Long idAgencia, String usuario) throws RelativeException {
		try {
			if (send == null || send.getId() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUEDE LEER LA INFORMACION DEL CONTRATO ");
			}
			send = this.findContratoById(send.getId());
			send.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			send.setProceso(ProcesoEnum.ENTREGA_JOYA);
			TbMiContrato contrato = this.manageContrato(send);
			List<TbMiJoya> joyas = this.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			List<Long> idJoyas = new ArrayList<Long>();
			Long idUbicacion = this.findBodegaByAgenciaAndNombre(idAgencia, "CLIENTE").getId();
			for (TbMiJoya j : joyas) {
				j.setUsuario(usuario);
				j.setProceso(ProcesoEnum.ENTREGA_JOYA);
				idJoyas.add(j.getId());
				this.manageJoya(j);
			}
			movimientoOroService.movimientoInventario(idJoyas, idUbicacion, usuario, EstadoJoyaEnum.DEVUELTA,
					ProcesoEnum.ENTREGA_JOYA);

			return contrato;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL ENTREGAR LA JOYA");
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiContrato updateContrato(TbMiContrato send, TbMiContrato persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setEstado(send.getEstado());
			persisted.setPorcentajeCustodia(send.getPorcentajeCustodia());
			persisted.setPorcentajeGastoAdm(send.getPorcentajeGastoAdm());
			persisted.setPorcentajeTasacion(send.getPorcentajeTasacion());
			persisted.setPorcentajeTransporte(send.getPorcentajeTransporte());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTbMiAgente(send.getTbMiAgente());
			persisted.setTbMiCliente(send.getTbMiCliente());
			persisted.setTbMiJoyas(send.getTbMiJoyas());
			persisted.setTbMiRenovacions(send.getTbMiRenovacions());
			persisted.setValorContrato(send.getValorContrato());
			persisted.setValorCustodia(send.getValorCustodia());
			persisted.setValorGastoAdm(send.getValorGastoAdm());
			persisted.setValorMulta(send.getValorMulta());
			persisted.setValorTasacion(send.getValorTasacion());
			persisted.setValorTransporte(send.getValorTransporte());
			persisted.setProceso(send.getProceso());
			persisted.setGestion(send.getGestion());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setCanalContacto(send.getCanalContacto());
			persisted.setFechaPerfeccionamiento(send.getFechaPerfeccionamiento());
			return contratoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Contrato " + e.getMessage());
		}
	}

	/**
	 * Lista todos los contratos por idAgencia y contrato estado
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public List<TbMiContrato> listContratosByEstadoAndIdAgencia(PaginatedWrapper pw, String idAgencia,
			EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException {
		if (pw == null) {
			return this.contratoRepository
					.findAllBySpecification(new ContratoByIdAgenciaEstadoSpec(idAgencia, estado1, estado2));
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoRepository.findAllBySpecificationPaged(
						new ContratoByIdAgenciaEstadoSpec(idAgencia, estado1, estado2), pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.contratoRepository
						.findAllBySpecification(new ContratoByIdAgenciaEstadoSpec(idAgencia, estado1, estado2));
			}
		}
	}

	/**
	 * Lista todos los contratos por idAgencia y contrato estado
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public Long countContratosByEstadoAndIdAgencia(PaginatedWrapper pw, String idAgencia, EstadoContratoEnum estado1,
			EstadoContratoEnum estado2) throws RelativeException {
		return this.contratoRepository
				.countBySpecification(new ContratoByIdAgenciaEstadoSpec(idAgencia, estado1, estado2));
	}

	/**
	 * Lista contratos por agencia y dos estado en clase personalizada
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<RetazarContratos> findByAngenciaEstado(PaginatedWrapper pw, String idAgencia,
			EstadoContratoEnum estado1, EstadoContratoEnum estado2) throws RelativeException {
		return this.contratoRepository.findByAngenciaEstado(pw, idAgencia, estado1, estado2);
	}

	/**
	 * Cuenta contratos por agencia y dos estado en clase personalizada
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Integer countByAngenciaEstado(PaginatedWrapper pw, String idAgencia, EstadoContratoEnum estado1,
			EstadoContratoEnum estado2) throws RelativeException {
		try {
			return this.contratoRepository.countByAngenciaEstado(pw, idAgencia, estado1, estado2);
		} catch (Exception e) {
			throw new RelativeException("" + e);
		}
	}

	/**
	 * Metodo que valida la regla de negocio Rn-001: donde los clientes no pueden
	 * adquirir mas de dos contratos por mes
	 * 
	 * @param idCliente
	 * @return
	 * @throws RelativeException
	 */
	public Boolean validateControByIdCliente(Long idCliente) throws RelativeException {
		// TODO Auto-generated method stub
		Date fechaActual = new Date();
		Date fechaInicio = new Date(fechaActual.getYear(), fechaActual.getMonth(), 1);
		Calendar cal = Calendar.getInstance();
		cal.set(fechaActual.getYear(), fechaActual.getMonth(), 1);
		int ultimoDiaMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date fechaFin = new Date(fechaActual.getYear(), fechaActual.getMonth(), ultimoDiaMes);

		if (this.contratoRepository.validateControByIdCliente(idCliente, fechaInicio, fechaFin) >= 2) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean validateContratoByIdFunda(Long idFunda) throws RelativeException {
		TbMiFunda funda;
		if (this.contratoRepository.validateContratoByIdFunda(idFunda) == 0) {
			// cambiar el estado de la funda con idFunda a creado..
			funda = this.fundaRepository.findById(idFunda);
			funda.setEstado(EstadoFundaEnum.CREADA);
			this.manageFunda(funda);
			return true;
		}
		return null;
	}

	public Boolean validateContratoByIdLiquidacion(Long idLiquidacion) throws RelativeException {
		TbMiLiquidacion liquidacion;
		if (this.contratoRepository.validateContratoByIdLiquidacion(idLiquidacion) == 0) {
			// cambiar el estado de la funda con idFunda a creado..
			liquidacion = this.liquidacionRepository.findById(idLiquidacion);
			liquidacion.setEstado(EstadoFundaEnum.CREADA);
			this.manageLiquidacion(liquidacion);
			return true;
		}
		return null;
	}

	/**
	 * Cotizacion
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiCotizacion findCotizacionById(Long id) throws RelativeException {
		try {
			return cotizacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	
	/**
	 * Guarda la cotizacion y su relacion con las joyas
	 * @param entidad
	 * @return
	 */
	public TbMiCotizacion guardarCotizacion(CotizacionWrapper entidad) throws RelativeException {
		try {
			validarCotizacon(entidad);
			TbMiCotizacion cotizacion = manageCotizacion(entidad.getCotizacion());
			for(TbMiJoyaSim joyasim:entidad.getListaJoyas()) {
				joyasim.setId(null);
				joyasim.setTbMiAprobarContrato(null);
				joyasim.setTbMiCotizacion(cotizacion);
				manageJoyaSim(joyasim);
			}
			return cotizacion;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL GUARDAR COTIZACION");
		}
	}

	private void validarCotizacon(CotizacionWrapper entidad) throws RelativeException {
		if(entidad == null || entidad.getCotizacion() == null || entidad.getListaJoyas() == null || entidad.getListaJoyas().isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORAMCION DE LA COTIZACION");
		}
	}
	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCotizacion() throws RelativeException {
		try {
			return cotizacionRepository.countAll(TbMiCotizacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cotizacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCotizacion> findAllCotizacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.cotizacionRepository.findAll(TbMiCotizacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cotizacionRepository.findAll(TbMiCotizacion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.cotizacionRepository.findAll(TbMiCotizacion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param estado
	 * 
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindCotizacionByAll(EstadoMidasEnum estado) throws RelativeException {
		try {
			return cotizacionRepository.countfindByAll(estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "cotizacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca cotizacion por estado
	 * 
	 * @return List<TbSaProveedor>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCotizacion> findCotizacionByAll(PaginatedWrapper pw, EstadoMidasEnum estado)
			throws RelativeException {
		List<TbMiCotizacion> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			tmp = cotizacionRepository.findByAllPaged(estado, pw.getStartRecord(), pw.getPageSize());
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA  estado: " + estado + "page " + pw);
			}

		} else {
			tmp = cotizacionRepository.findByAll(estado);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO EXISTEN REGISTROS PARA  estado: " + estado);
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiCotizacion manageCotizacion(TbMiCotizacion send) throws RelativeException {
		try {
			log.info("==> entra a manage Cotizacion");
			TbMiCotizacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findCotizacionById(send.getId());
				return this.updateCotizacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return cotizacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Cotizacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiCotizacion updateCotizacion(TbMiCotizacion send, TbMiCotizacion persisted) throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setDescripcion(send.getDescripcion());
			persisted.setMontoCotizacion(send.getMontoCotizacion());
			persisted.setNombreCotizacion(send.getNombreCotizacion());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setCustodia(send.getCustodia());
			persisted.setDescripcion(send.getDescripcion());
			persisted.setDesembolso(send.getDesembolso());
			persisted.setGastosAdministrativos(send.getGastosAdministrativos());
			persisted.setTasacion(send.getTasacion());
			persisted.setFechaVencimiento(send.getFechaVencimiento());
			persisted.setPorcentajeCustodia(send.getPorcentajeCustodia());
			persisted.setCustodia(send.getCustodia());
			persisted.setTbMiJoyaSims(send.getTbMiJoyaSims());
			return cotizacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Cotizacion " + e.getMessage());
		}
	}

	/**
	 * Funda
	 */

	/**
	 * Lista las funda por el id del rango
	 * 
	 * @param pw
	 * @return
	 */
	public List<TbMiFunda> findFundaByRango(PaginatedWrapper pw, Long idRango) throws RelativeException {

		if (pw != null) {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.fundaRepository.findByRango(idRango, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.fundaRepository.findByRango(idRango);
			}

		} else {
			return this.fundaRepository.findByRango(idRango);
		}

	}

	/**
	 * cuenta las fundas por el id del rango
	 * 
	 * @return
	 * @throws RelativeException
	 */
	public Long countFundaByRango(Long id) throws RelativeException {
		try {
			return fundaRepository.countFindByRango(id);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Fundas no encontradas" + e.getMessage());
		}
	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiFunda findFundaById(Long id) throws RelativeException {
		try {
			return fundaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countFunda() throws RelativeException {
		try {
			return fundaRepository.countAll(TbMiFunda.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Funda no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiFunda> findAllFunda(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.fundaRepository.findAll(TbMiFunda.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.fundaRepository.findAll(TbMiFunda.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.fundaRepository.findAll(TbMiFunda.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiFunda manageFunda(TbMiFunda send) throws RelativeException {
		try {
			log.info("==> entra a manage Funda");
			TbMiFunda persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findFundaById(send.getId());
				return this.updateFunda(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				// send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return fundaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Funda " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiFunda updateFunda(TbMiFunda send, TbMiFunda persisted) throws RelativeException {
		try {
			persisted.setComentario(send.getComentario());
			persisted.setEstado(send.getEstado());
			persisted.setCodigo(send.getCodigo());
			persisted.setTbMiJoyas(send.getTbMiJoyas());
			persisted.setTbMiBodega(send.getTbMiBodega());
			return fundaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Funda " + e.getMessage());
		}
	}

	/**
	 * Liquidacion
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiLiquidacion findLiquidacionById(Long id) throws RelativeException {
		try {
			return liquidacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countLiquidacion() throws RelativeException {
		try {
			return liquidacionRepository.countAll(TbMiLiquidacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Liquidacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiLiquidacion> findAllLiquidacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.liquidacionRepository.findAll(TbMiLiquidacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.liquidacionRepository.findAll(TbMiLiquidacion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.liquidacionRepository.findAll(TbMiLiquidacion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiLiquidacion manageLiquidacion(TbMiLiquidacion send) throws RelativeException {
		try {
			log.info("==> entra a manage Liquidacion");
			TbMiLiquidacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findLiquidacionById(send.getId());
				return this.updateLiquidacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return liquidacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Liquidacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiLiquidacion updateLiquidacion(TbMiLiquidacion send, TbMiLiquidacion persisted) throws RelativeException {
		try {
			persisted.setComentario(send.getComentario());
			persisted.setEstado(send.getEstado());
			persisted.setCodigo(send.getCodigo());
			return liquidacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Liquidacion " + e.getMessage());
		}
	}

	/**
	 * Busqueda de liquidaciones por id folleto
	 * 
	 * @param pw
	 * @param idFolletoLiquidacion
	 * @return
	 * @throws RelativeException
	 * @author Kevin chamorro
	 */
	public List<TbMiLiquidacion> findLiquidacionByFolleto(PaginatedWrapper pw, Long idFolletoLiquidacion)
			throws RelativeException {
		return liquidacionRepository.findByFolletoLiquidacion(pw, idFolletoLiquidacion);
	}

	public Long countLiquidacionByParams(Long idFolletoLiquidacion) throws RelativeException {
		return liquidacionRepository
				.countBySpecification(new LiquidacionByFolletoLiquidacionSpec(idFolletoLiquidacion));
	}

	/**
	 * HistoricoJoya
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiHistoricoJoya findHistoricoJoyaById(Long id) throws RelativeException {
		try {
			return historicoJoyaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countHistoricoJoya() throws RelativeException {
		try {
			return historicoJoyaRepository.countAll(TbMiHistoricoJoya.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "HistoricoJoya no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiHistoricoJoya> findAllHistoricoJoya(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.historicoJoyaRepository.findAll(TbMiHistoricoJoya.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.historicoJoyaRepository.findAll(TbMiHistoricoJoya.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.historicoJoyaRepository.findAll(TbMiHistoricoJoya.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiHistoricoJoya manageHistoricoJoya(TbMiHistoricoJoya send) throws RelativeException {
		try {
			log.info("==> entra a manage HistoricoJoya");
			TbMiHistoricoJoya persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findHistoricoJoyaById(send.getId());
				return this.updateHistoricoJoya(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

				return historicoJoyaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiHistoricoJoya updateHistoricoJoya(TbMiHistoricoJoya send, TbMiHistoricoJoya persisted)
			throws RelativeException {
		try {
			persisted.setComentario(send.getComentario());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setTbMiJoya(send.getTbMiJoya());
			persisted.setValor(send.getValor());
			return historicoJoyaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando HistoricoJoya " + e.getMessage());
		}
	}

	/**
	 * Inventario
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiInventario findInventarioById(Long id) throws RelativeException {
		try {
			return inventarioRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countInventario() throws RelativeException {
		try {
			return inventarioRepository.countAll(TbMiInventario.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Inventario no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiInventario> findAllInventario(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.inventarioRepository.findAll(TbMiInventario.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.inventarioRepository.findAll(TbMiInventario.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.inventarioRepository.findAll(TbMiInventario.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiInventario manageInventario(TbMiInventario send) throws RelativeException {
		try {
			log.info("==> entra a manage Inventario");
			TbMiInventario persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findInventarioById(send.getId());
				return this.updateInventario(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date());
				send.setFechaCreacion(new Date());
				return inventarioRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Inventario " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiInventario updateInventario(TbMiInventario send, TbMiInventario persisted) throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setTbMiBodega(send.getTbMiBodega());
			persisted.setTbMiJoya(send.getTbMiJoya());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());

			return inventarioRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Inventario " + e.getMessage());
		}
	}

	/**
	 * Metodo que cambia el estado del inventario pertenecientes a una joya
	 * 
	 * @param idJoya
	 * @param estado
	 * @param usuario
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiInventario> changeEstadoInventarioByIdJoya(Long idJoya, EstadoJoyaEnum estado, String usuario)
			throws RelativeException {
		try {
			return this.inventarioRepository.changeEstadoInventarioByIdJoya(idJoya, estado, usuario);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando el inventario " + e.getMessage());
		}
	}

	/**
	 * MovInventario
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiMovInventario findMovInventarioById(Long id) throws RelativeException {
		try {
			return movInventarioRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countMovInventario() throws RelativeException {
		try {
			return movInventarioRepository.countAll(TbMiMovInventario.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "MovInventario no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiMovInventario> findAllMovInventario(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.movInventarioRepository.findAll(TbMiMovInventario.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.movInventarioRepository.findAll(TbMiMovInventario.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.movInventarioRepository.findAll(TbMiMovInventario.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiMovInventario manageMovInventario(TbMiMovInventario send) throws RelativeException {
		try {
			log.info("==++++++++++++++++++++++++> entra a manage MovInventario getUsuarioCreacion "
					+ send.getUsuarioCreacion());
			log.info("==++++++++++++++++++++++++> entra a manage MovInventario getUsuarioActualizacion "
					+ send.getUsuarioActualizacion());

			TbMiMovInventario persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findMovInventarioById(send.getId());
				return this.updateMovInventario(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return movInventarioRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la MovInventario " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiMovInventario updateMovInventario(TbMiMovInventario send, TbMiMovInventario persisted)
			throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setId(send.getId());
			persisted.setTbMiBodega(send.getTbMiBodega());
			persisted.setTbMiInventario(send.getTbMiInventario());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());

			return movInventarioRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando MovInventario " + e.getMessage());
		}
	}

	public List<TbMiMovimientoCaja> findMovimientoCajaByVentaLote(Long idVentaLote) throws RelativeException {
		// TODO Auto-generated method stub
		return movimientoCajaRepository.findByVentaLote(idVentaLote);
	}

	/**
	 * Bodega
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiBodega findBodegaById(Long id) throws RelativeException {
		try {
			return bodegaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public TbMiBodega findBodegaByAgenciaAndNombre(Long idAgencia, String nombreBodega) throws RelativeException {
		try {
			return bodegaRepository.findByAgenciaAndNombre(idAgencia, nombreBodega);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}

	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countBodega() throws RelativeException {
		try {
			return bodegaRepository.countAll(TbMiBodega.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Bodega no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiBodega> findAllBodega(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.bodegaRepository.findAll(TbMiBodega.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.bodegaRepository.findAll(TbMiBodega.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.bodegaRepository.findAll(TbMiBodega.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiBodega manageBodega(TbMiBodega send) throws RelativeException {
		try {
			log.info("==> entra a manage Bodega");
			TbMiBodega persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findBodegaById(send.getId());
				return this.updateBodega(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date());
				send.setFechaCreacion(new Date());
				return bodegaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Bodega " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiBodega updateBodega(TbMiBodega send, TbMiBodega persisted) throws RelativeException {
		try {
			persisted.setDescripcion(send.getDescripcion());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setId(send.getId());
			persisted.setNombre(send.getNombre());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setTipoBodega(send.getTipoBodega());

			persisted.setTbMiAgencia(send.getTbMiAgencia());

			return bodegaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Bodega " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna una lista de bodegas por tipo bodega y id agencia
	 * 
	 * @param idAgencia
	 * @param tipoBodega
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public TbMiBodega bodegasByAgencia(Long idAgencia, TipoBodegaEnum tipoBodega) throws RelativeException {
		try {
			List<TbMiBodega> lista = bodegaRepository
					.findAllBySpecification(new BodegaByAgencia(idAgencia, tipoBodega));
			if (!lista.isEmpty()) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Bodega no encontrado " + e.getMessage());
		}
	}

	/**
	 * JoyaSim
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiJoyaSim findJoyaSimById(Long id) throws RelativeException {
		try {
			return joyaSimRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countJoyaSim() throws RelativeException {
		try {
			return joyaSimRepository.countAll(TbMiJoyaSim.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "JoyaSim no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiJoyaSim> findAllJoyaSim(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.joyaSimRepository.findAll(TbMiJoyaSim.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.joyaSimRepository.findAll(TbMiJoyaSim.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.joyaSimRepository.findAll(TbMiJoyaSim.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiJoyaSim manageJoyaSim(TbMiJoyaSim send) throws RelativeException {
		try {
			log.info("==> entra a manage JoyaSim");
			TbMiJoyaSim persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findJoyaSimById(send.getId());
				return this.updateJoyaSim(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return joyaSimRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la JoyaSim " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiJoyaSim updateJoyaSim(TbMiJoyaSim send, TbMiJoyaSim persisted) throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setCondicion(send.getCondicion());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setDescuento(send.getDescuento());
			persisted.setPesoBruto(send.getPesoBruto());
			persisted.setPrecioCd(send.getPrecioCd());
			persisted.setPrecioCp(send.getPrecioCp());
			persisted.setPrecioCompraCD(send.getPrecioCompraCD());
			persisted.setPrecioCompraCP(send.getPrecioCompraCP());
			persisted.setTbMiCotizacion(send.getTbMiCotizacion());
			persisted.setTbMiTipoJoya(send.getTbMiTipoJoya());
			persisted.setTbMiTipoOro(send.getTbMiTipoOro());
			persisted.setTbMiAprobarContrato(send.getTbMiAprobarContrato());
			return joyaSimRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando JoyaSim " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca joyaSim por id Cotzacion
	 * 
	 * @return List<TbMiJoyaSim>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiJoyaSim> findJoyaSimByAll(PaginatedWrapper pw, Long id) throws RelativeException {
		List<TbMiJoyaSim> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			tmp = joyaSimRepository.findByAllPaged(id, pw.getStartRecord(), pw.getPageSize());
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA ID COTIZACION: " + id + "page " + pw);
			}

		} else {
			tmp = joyaSimRepository.findByAll(id);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA ID COTIZACION: " + id);
			}
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param id Cotizacion
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindJoyaSimByAll(Long id) throws RelativeException {
		try {
			return joyaSimRepository.countfindByAll(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "id no encontrado " + e.getMessage());
		}
	}

	/**
	 * Renovacion
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiRenovacion findRenovacionById(Long id) throws RelativeException {
		try {
			return renovacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countRenovacion() throws RelativeException {
		try {
			return renovacionRepository.countAll(TbMiRenovacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Renovacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiRenovacion> findAllRenovacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.renovacionRepository.findAll(TbMiRenovacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.renovacionRepository.findAll(TbMiRenovacion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.renovacionRepository.findAll(TbMiRenovacion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiRenovacion manageRenovacion(TbMiRenovacion send) throws RelativeException {
		try {
			log.info("==> entra a manage Renovacion");
			TbMiRenovacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findRenovacionById(send.getId());
				return this.updateRenovacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				// send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return renovacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Renovacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiRenovacion updateRenovacion(TbMiRenovacion send, TbMiRenovacion persisted) throws RelativeException {
		try {
			persisted.setMulta(send.getMulta());
			persisted.setPorcentajeCustodia(send.getPorcentajeCustodia());
			persisted.setPorcentajeGatoAdm(send.getPorcentajeGatoAdm());
			persisted.setPorcentajeValoracion(send.getPorcentajeValoracion());
			persisted.setTbMiContrato(send.getTbMiContrato());
			persisted.setTbMiRenovacions(send.getTbMiRenovacions());
			persisted.setTbMiRenovacion(send.getTbMiRenovacion());
			persisted.setValorCustodia(send.getValorCustodia());
			persisted.setValorGatoAdm(send.getValorGatoAdm());
			persisted.setValorValoracion(send.getValorValoracion());
			return renovacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Renovacion " + e.getMessage());
		}
	}

	/**
	 * TipoJoya
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiTipoJoya findTipoJoyaById(Long id) throws RelativeException {
		try {
			return tipoJoyaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countTipoJoya() throws RelativeException {
		try {
			return tipoJoyaRepository.countAll(TbMiTipoJoya.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "TipoJoya no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoJoya> findAllTipoJoya(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.tipoJoyaRepository.findAll(TbMiTipoJoya.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tipoJoyaRepository.findAll(TbMiTipoJoya.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tipoJoyaRepository.findAll(TbMiTipoJoya.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param estado
	 * 
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindTipoJoyaByAll(EstadoMidasEnum estado) throws RelativeException {
		try {
			return tipoJoyaRepository.countfindByAll(estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "estado no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param detalle y estado
	 * 
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindTipoJoyaByDetalleEstado(String detalle, EstadoMidasEnum estado) throws RelativeException {
		try {
			return tipoJoyaRepository.countfindByDetalleEstado(detalle,estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "detalle y estado no encontrado " + e.getMessage());
		}
	}

	
	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoJoya manageTipoJoya(TbMiTipoJoya send) throws RelativeException {
		log.info("==> entra a manage TipoJoya");
		try {

			TbMiTipoJoya persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoJoyaById(send.getId());
		
				return this.updateTipoJoya(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoJoyaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la TipoJoya " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoJoya updateTipoJoya(TbMiTipoJoya send, TbMiTipoJoya persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setEstado(send.getEstado());
			persisted.setDetalle(send.getDetalle());
			persisted.setTbMiJoyas(send.getTbMiJoyas());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return tipoJoyaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando TipoJoya " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca cotizacion por estado
	 * 
	 * @return List<TbMiTipoJoya>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoJoya> findTipoJoyaByAll(PaginatedWrapper pw, EstadoMidasEnum estado) throws RelativeException {
		List<TbMiTipoJoya> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			tmp = tipoJoyaRepository.findByAllPaged(estado, pw.getStartRecord(), pw.getPageSize());
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA  estado: " + estado + "page " + pw);
			}

		} else {
			tmp = tipoJoyaRepository.findByAll(estado);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO EXISTEN REGISTROS PARA  estado: " + estado);
			}
		}
	}

	
	/**
	 * Metodo que busca cotizacion por detalle y estado  
	 * 
	 * @return List<TbMiTipoJoya>
	 * @author Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoJoya> findTipoJoyaByDetalleEstado(PaginatedWrapper pw,String detalle, EstadoMidasEnum estado) throws RelativeException {
		
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) { 
			return tipoJoyaRepository.findByDetalleEstado(detalle,estado, pw.getStartRecord(), pw.getPageSize(),pw.getSortFields(),pw.getSortDirections());
		} else {
			return tipoJoyaRepository.findByDetalleEstado(detalle,estado);			
		}
	}

	
	/**
	 * TipoOro
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiTipoOro findTipoOroById(Long id) throws RelativeException {
		try {
			return tipoOroRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca tipo oro por estado
	 * 
	 * @return List<TbMiTipoJoya>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoOro> findTipoOroByAll(PaginatedWrapper pw, EstadoMidasEnum estado) throws RelativeException {
		List<TbMiTipoOro> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			tmp = tipoOroRepository.findByAllPaged(estado, pw.getStartRecord(), pw.getPageSize());
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA  estado: " + estado + "page " + pw);
			}

		} else {
			tmp = tipoOroRepository.findByAll(estado);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO EXISTEN REGISTROS PARA  estado: " + estado);
			}
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param estado
	 * 
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindTipoOroByAll(EstadoMidasEnum estado) throws RelativeException {
		try {
			return tipoJoyaRepository.countfindByAll(estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "estado no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countTipoOro() throws RelativeException {
		try {
			return tipoOroRepository.countAll(TbMiTipoOro.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "TipoOro no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoOro> findAllTipoOro(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.tipoOroRepository.findAll(TbMiTipoOro.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tipoOroRepository.findAll(TbMiTipoOro.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tipoOroRepository.findAll(TbMiTipoOro.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoOro manageTipoOro(TbMiTipoOro send) throws RelativeException {
		try {
			log.info("==> entra a manage TipoOro");
			TbMiTipoOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoOroById(send.getId());
				return this.updateTipoOro(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				// send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoOroRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la TipoOro " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoOro updateTipoOro(TbMiTipoOro send, TbMiTipoOro persisted) throws RelativeException {
		try {
			persisted.setPorcentajePureza(send.getPorcentajePureza());
			persisted.setEstado(send.getEstado());
			persisted.setQuilate(send.getQuilate());
			persisted.setEstado(send.getEstado());
			persisted.setPrecioOroCd(send.getPrecioOroCd());
			persisted.setPrecioOroCp(send.getPrecioOroCp());
			persisted.setPrecioOroVenta(send.getPrecioOroVenta());
			return tipoOroRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando TipoOro " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca tipo ORO por estado
	 * 
	 * @return List<TbMiTipoOro>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoOro> findTipoOroByEstado(PaginatedWrapper pw, EstadoMidasEnum estado) throws RelativeException {
		List<TbMiTipoOro> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			tmp = tipoOroRepository.findByAllPaged(estado, pw.getStartRecord(), pw.getPageSize());
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA  estado: " + estado + "page " + pw);
			}

		} else {
			tmp = tipoOroRepository.findByAll(estado);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO EXISTEN REGISTROS PARA  estado: " + estado);
			}
		}
	}

	/**
	 * Busca tipo de oro por el vlor de quilate
	 * 
	 * @param quilate
	 * @return
	 * @throws RelativeException
	 */
	public TbMiTipoOro findTipoOroByQuilate(String quilate) throws RelativeException {

		return this.tipoOroRepository.findByQuilate(quilate);
	}

	/**
	 * CANTON
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public Canton findCantonById(CantonPK id) throws RelativeException {
		try {
			return cantonRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCanton() throws RelativeException {
		try {
			return cantonRepository.countAll(Canton.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<Canton> findAllCanton(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.cantonRepository.findAll(Canton.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cantonRepository.findAll(Canton.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.cantonRepository.findAll(Canton.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Carga los cantones asociados a la provincia
	 * 
	 * @param provincia Criterio de busqueda
	 * @return Listado de cantones por provincia
	 * @throws RelativeException
	 */
	public List<Canton> findCantonesByProvincia(String provincia, String order) throws RelativeException {
		return this.cantonRepository.findByProvincia(provincia, order);
	}

	/**
	 * PROVINCIA
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public Provincia findProvinciaById(String id) throws RelativeException {
		try {
			return provinciaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Provincia no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countProvincia() throws RelativeException {
		try {
			return provinciaRepository.countAll(Provincia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Provincia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<Provincia> findAllProvincia(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.provinciaRepository.findAll(Provincia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.provinciaRepository.findAll(Provincia.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.provinciaRepository.findAll(Provincia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * PARAMETRO
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiParametro findParametroById(Long id) throws RelativeException {
		try {
			return parametroRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Buscar parametros por parametros
	 * 
	 * @param nombre
	 * @param tipo
	 * @param estado
	 * @param caracteriticaUno
	 * @param caracteristicaDos
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findParametroByParam(String nombre, String tipo, EstadoMidasEnum estado,
			String caracteriticaUno, String caracteristicaDos, PaginatedWrapper pw) throws RelativeException {
		try {
			List<TbMiParametro> tmp = parametroRepository.findByParamPaged(nombre, tipo, estado, caracteriticaUno,
					caracteristicaDos, pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections());
			parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
			return tmp;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countParametros(String nombre, String tipo, EstadoMidasEnum estado, String caracteriticaUno,
			String caracteristicaDos) throws RelativeException {
		try {
			return parametroRepository.countByParamPaged(nombre, tipo, estado, caracteriticaUno, caracteristicaDos);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public Long countParametros() throws RelativeException {
		try {
			return parametroRepository.countAll(TbMiParametro.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findAllParametro(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.parametroRepository.findAll(TbMiParametro.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.parametroRepository.findAll(TbMiParametro.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.parametroRepository.findAll(TbMiParametro.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiParametro manageParametro(TbMiParametro send) throws RelativeException {
		try {

			TbMiParametro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findParametroById(send.getId());
				persisted = this.updateParametro(send, persisted);
				parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
				return persisted;
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				// send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				persisted = parametroRepository.add(send);
				parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
				return persisted;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CausaNegativa " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiParametro updateParametro(TbMiParametro send, TbMiParametro persisted) throws RelativeException {
		try {
			if (send.getNombre() != null)
				persisted.setNombre(send.getNombre());
			if (send.getTipo() != null)
				persisted.setTipo(send.getTipo());
			if (send.getValor() != null)
				persisted.setValor(send.getValor());
			if (send.getCaracteriticaUno() != null)
				persisted.setCaracteriticaUno(send.getCaracteriticaUno());
			if (send.getCaracteristicaDos() != null)
				persisted.setCaracteristicaDos(send.getCaracteristicaDos());
			if (send.getEstado() != null)
				persisted.setEstado(send.getEstado());
			if (send.getOrden() != null)
				persisted.setOrden(send.getOrden());
			return parametroRepository.update(persisted);

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CausaNegativa " + e.getMessage());
		}
	}

	public TbMiParametro findByNombre(String nombre) throws RelativeException {
		try {
			TbMiParametro a = parametroRepository.findByNombre(nombre);
			if (a != null) {
				return a;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "TbMiParametro no encontrada ");

			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "en la busqueda TbMiParametro " + e.getMessage());
		}
	}

	/**
	 * Busca los parametros por nombre, tipo o los dos parametros, si se envia
	 * ordenar se ordena por el campo orden
	 * 
	 * @param nombre
	 * @param tipo
	 * @param ordered
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findByNombreTipoOrdered(String nombre, String tipo, Boolean ordered)
			throws RelativeException {
		try {
			List<TbMiParametro> a = parametroRepository.findByNombreAndTipoOrdered(nombre, tipo, ordered);
			if (a != null) {
				return a;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrados por nombre o tipo ");

			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Parametros no encontrados por nombre o tipo " + e.getMessage());
		}
	}

	/**
	 * Egreso
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiEgreso findEgresoById(Long id) throws RelativeException {
		try {
			return egresoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countEgreso() throws RelativeException {
		try {
			return egresoRepository.countAll(TbMiEgreso.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Egreso no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiEgreso> findAllEgreso(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.egresoRepository.findAll(TbMiEgreso.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.egresoRepository.findAll(TbMiEgreso.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.egresoRepository.findAll(TbMiEgreso.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiEgreso manageEgreso(TbMiEgreso send) throws RelativeException {
		try {
			log.info("==> entra a manage Egreso");
			TbMiEgreso persisted = null;
			if (send != null && send.getId() != null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				persisted = this.findEgresoById(send.getId());
				return this.updateEgreso(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return egresoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Egreso " + e.getMessage());
		}
	}

	/**
	 * Cre registro den batch de egresos
	 * 
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public Integer createEgresoBatch(List<TbMiEgreso> send) throws RelativeException {
		this.egresoRepository.add(send);
		return send.size();
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiEgreso updateEgreso(TbMiEgreso send, TbMiEgreso persisted) throws RelativeException {
		try {

			persisted.setBanco(send.getBanco());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setMonto(send.getMonto());
			persisted.setPago(send.getPago());
			persisted.setNumeroCuenta(send.getNumeroCuenta());
			persisted.setTipoCuenta(send.getTipoCuenta());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setUsuarioCreacion(send.getUsuarioActualizacion());
			persisted.setTbMiContrato(send.getTbMiContrato());

			return egresoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Egreso " + e.getMessage());
		}
	}

	/**
	 * Documento
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiTipoDocumento findTipoDocumentoById(Long id) throws RelativeException {
		try {
			return documentoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countdocumento() throws RelativeException {
		try {
			return documentoRepository.countAll(TbMiTipoDocumento.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "documento no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoDocumento> findAllDocumento(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.documentoRepository.findAll(TbMiTipoDocumento.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.documentoRepository.findAll(TbMiTipoDocumento.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.documentoRepository.findAll(TbMiTipoDocumento.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoDocumento manageDocumento(TbMiTipoDocumento send) throws RelativeException {
		try {
			log.info("==> entra a manage Documento");
			TbMiTipoDocumento persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoDocumentoById(send.getId());
				return this.updateDocumento(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return documentoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Documento " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiTipoDocumento updateDocumento(TbMiTipoDocumento send, TbMiTipoDocumento persisted)
			throws RelativeException {
		try {

			persisted.setDescripcion(send.getDescripcion());
			persisted.setEstado(send.getEstado());
			persisted.setTipoDocumento(send.getTipoDocumento());
			persisted.setFechaCreacion(send.getFechaCreacion());
			persisted.setPlantilla(send.getPlantilla());

			return documentoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Documento " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca tipo de documento y el codigo
	 * 
	 * @return List<TbMiTipoDocumento>
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiTipoDocumento> findDocumentoByTipoAndCodigo(PaginatedWrapper pw, String tipoDocumento,
			String codigo, Long idJoya, Long idAbono) throws RelativeException {
		List<TbMiTipoDocumento> tmp;
		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			tmp = documentoRepository.findByTipoDocumentoAndCodigoContrato(tipoDocumento, codigo, idJoya, idAbono, pw);
			if (tmp == null || tmp.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA ID documento: " + tipoDocumento + "page " + pw);
			}

		} else {
			tmp = documentoRepository.findByTipoDocumentoAndCodigoContrato(tipoDocumento, codigo, idJoya, idAbono);
			if (tmp == null || tmp.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"NO EXISTEN REGISTROS PARA ID tipoDocumento: " + tipoDocumento);
			}
		}
		/*
		 * if(tmp != null || !tmp.isEmpty()) { for( TbMiTipoDocumento td: tmp ){
		 * List<TbMiDocumentoHabilitante> dhs= td.getTbMiDocumentoHabilitantes(); if(
		 * dhs != null || !dhs.isEmpty() ) { List<TbMiDocumentoHabilitante>
		 * tdhs=dhs.stream().filter(d->d.getTbMiContrato().getCodigo()==codigo).collect(
		 * Collectors.toList()); td.setTbMiDocumentoHabilitantes(tdhs); } } }
		 */
		return tmp;
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param tipo documento
	 * 
	 * @return
	 * @throws RelativeException
	 */

	public Long countfindDocumentoByTipoAndcodigo(String tipoDocumento, String codigo, Long idJoya, Long idAbono)
			throws RelativeException {
		try {
			return documentoRepository.countfindByDocumentoAndCodigoContrato(tipoDocumento, codigo, idJoya, idAbono);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "id no encontrado " + e.getMessage());
		}
	}

	/**
	 * Abono
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiAbono findAbonoById(Long id) throws RelativeException {
		try {
			return abonoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countAbono() throws RelativeException {
		try {
			return abonoRepository.countAll(TbMiAbono.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Abono no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAbono> findAllAbono(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.abonoRepository.findAll(TbMiAbono.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.abonoRepository.findAll(TbMiAbono.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.abonoRepository.findAll(TbMiAbono.class, pw.getSortFields(), pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiAbono manageAbono(TbMiAbono send) throws RelativeException {
		try {
			log.info("==> entra a manage Abono");
			TbMiAbono persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.abonoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateAbono(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return abonoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send una lista de entidades con la informacion de creacion o
	 *             actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public List<TbMiAbono> manageListAbono(List<TbMiAbono> listSend) throws RelativeException {
		try {
			List<TbMiAbono> listaRetorno = new ArrayList<TbMiAbono>();
			for (TbMiAbono send : listSend) {
				TbMiAbono persisted = null;
				if (send != null && send.getId() != null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					persisted = this.abonoRepository.findById(send.getId());
					listaRetorno.add(this.updateAbono(send, persisted));
				} else if (send != null && send.getId() == null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					listaRetorno.add(abonoRepository.add(send));
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
				}
			}
			return listaRetorno;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiAbono updateAbono(TbMiAbono send, TbMiAbono persisted) throws RelativeException {
		try {
			persisted.setTbMiContrato(send.getTbMiContrato());
			persisted.setTbMiCliente(send.getTbMiCliente());
			persisted.setTipoCuenta(send.getTipoCuenta());
			persisted.setFormaPago(send.getFormaPago());
			persisted.setTbMiBanco(send.getTbMiBanco());
			persisted.setNumeroCuenta(send.getNumeroCuenta());
			persisted.setValor(send.getValor());
			persisted.setEstado(send.getEstado());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setUsuarioCreacion(send.getUsuarioCreacion());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setNumeroTarjeta(send.getNumeroTarjeta());
			persisted.setTipoTarjeta(send.getTipoTarjeta());
			persisted.setHabienteTarjeta(send.getHabienteTarjeta());
			persisted.setCedHabienteTarjeta(send.getCedHabienteTarjeta());
			return abonoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Abono " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca la entidad por identificacion cliente
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAbono> getAbonosPorIdentificacionCliente(String identificacion) throws RelativeException {
		try {
			return abonoRepository.getAbonoPorIdentificacionCliente(identificacion);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbMiAbono> findAbonosByIdCliente(Long idCliente) throws RelativeException {
		return abonoRepository.findByIdClient(idCliente);

	}

	public List<TbMiAbono> findAbonoByEstadoAndIdentificacion(PaginatedWrapper pw, EstadoMidasEnum pendienteHabilitante,
			String identificacion) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return abonoRepository.findByEstadoAndIdentificacion(pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections(), pendienteHabilitante, identificacion);
		} else {
			return abonoRepository.findByEstadoAndIdentificacion(pendienteHabilitante, identificacion);

		}
	}

	public Long countAbonoByEstadoAndIdentificacion(EstadoMidasEnum pendienteHabilitante, String identificacion)
			throws RelativeException {
		return abonoRepository.countByEstadoAndIdentificacion(pendienteHabilitante, identificacion);
	}

	/**
	 * Funda Rango
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Andres Grijalva - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiFundaRango findFundaRangoById(Long id) throws RelativeException {
		try {
			return fundaRangoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countValidacionRango(Long rangoInicial) throws RelativeException {
		try {
			return fundaRangoRepository.countValidacionRango(rangoInicial);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countFundaRango() throws RelativeException {
		try {
			return fundaRangoRepository.countAll(TbMiFundaRango.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Rango de funda no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Andres Grijalva - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiFundaRango> findAllFundaRango(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.fundaRangoRepository.findAll(TbMiFundaRango.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.fundaRangoRepository.findAll(TbMiFundaRango.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.fundaRangoRepository.findAll(TbMiFundaRango.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiFundaRango manageFundaRango(TbMiFundaRango send) throws RelativeException {
		try {
			log.info("==> entra FundaRango");
			TbMiFundaRango persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findFundaRangoById(send.getId());
				return this.updateFundaRango(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return fundaRangoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la JoyaSim " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiFundaRango updateFundaRango(TbMiFundaRango send, TbMiFundaRango persisted) throws RelativeException {
		try {
			persisted.setRangoInicial(send.getRangoInicial());
			persisted.setEstado(send.getEstado());
			persisted.setFechaCreacion(send.getFechaCreacion());
			persisted.setRangoFinal(send.getRangoFinal());
			persisted.setNombreFundas(send.getNombreFundas());
			persisted.setEstado(send.getEstado());
			persisted.setId(send.getId());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			return fundaRangoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Funda Rango " + e.getMessage());
		}
	}

	/**
	 * Metodo devuelte el total de registros de la busqueda
	 * 
	 * @param estado
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @return
	 * @throws RelativeException
	 */

	public Long countFundaRagoByParams(String idAgencia, EstadoMidasEnum estado, String nombrePaquete,
			BigDecimal rangoDesde, BigDecimal rangoHasta) throws RelativeException {
		try {
			return fundaRangoRepository.countBySpecification(
					new FundaRangoByRangoInicialSpec(idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Conteo de registros funda rango, " + e.getMessage());
		}
	}

	/**
	 * Metodo de busqueda de fundas
	 * 
	 * @return List<TbMiFundaRango>
	 * @author Kevin Chamorro - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiFundaRango> findFundaRagoByParams(PaginatedWrapper pw, String idAgencia, EstadoMidasEnum estado,
			String nombrePaquete, BigDecimal rangoDesde, BigDecimal rangoHasta) throws RelativeException {
		return this.fundaRangoRepository.findByParams(pw, idAgencia, estado, nombrePaquete, rangoDesde, rangoHasta);
	}

	/**
	 * Metodo genera el rango de fundas y el detalla de fundas asociadas al rango.
	 * 
	 * @param send Objeto con la informacion de rango
	 * @return Rango generado
	 * @throws RelativeException Si existe errores en el registro o el si el rango
	 *                           no esta definido.
	 */
	public TbMiFundaRango generateFundaRango(TbMiFundaRango send) throws RelativeException {
		try {
			TbMiFundaRango rango = new TbMiFundaRango();
			// log.info("=======>generateFundaRango");
			rango = this.manageFundaRango(send);
			// log.info("=======>rengo generado " + rango.getId());
			if (send.getRangoFinal().intValue() - send.getRangoInicial().intValue() > 0) {
				for (int i = send.getRangoInicial().intValue(); i <= send.getRangoFinal().intValue(); i++) {
					TbMiFunda funda = new TbMiFunda();
					// log.info("=======>codigo fund " + MidasOroConstantes.FUNDA_PREFIX +
					// StringUtils.leftPad(String.valueOf(i), 3, "0"));
					funda.setCodigo(MidasOroConstantes.FUNDA_PREFIX + StringUtils.leftPad(String.valueOf(i), 3, "0"));
					funda.setComentario("FUNDA CREADO CORRECTAMENTE");
					funda.setEstado(EstadoFundaEnum.CREADA);
					funda.setTbMifundaRango(rango);
					this.manageFunda(funda);
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL RANGO NO PUEDE SER CERO");
			}
			log.info("=======>generateFundaRango RETORNO");
			return rango;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERRO EXCEPTION");
		}

	}

	/**
	 * Busca las Folleto de liquidaciones por id de agencia
	 * 
	 * @param pw
	 * @param idAgencia
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiFundaRango> findFundaRangoByIdAgencia(PaginatedWrapper pw, Long idAgencia)
			throws RelativeException {

		if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return fundaRangoRepository.findByIdAgencia(idAgencia, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return fundaRangoRepository.findByIdAgencia(idAgencia);
		}

	}

	public Long countFundaRangoByIdAgencia(Long idAgencia) throws RelativeException {
		try {
			return fundaRangoRepository.countByIdAgencia(idAgencia);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Liquidacion no encontrado " + e.getMessage());
		}
	}

	public List<TbMiFundaRango> FundaRangoSinAsignar() throws RelativeException {
		return fundaRangoRepository.fundaRangoSinAsginar();
	}

	public TbMiFundaRango asignarRangoFunda(TbMiFundaRango entidad, Long idAgencia) throws RelativeException {
		Integer totalLiquidaciones = this.fundaRepository.countFunda(idAgencia);
		// log.info(">>>>>>>>>>>>>>numero de liquidaciones:"+totalLiquidaciones+ "
		// agencia:"+idAgencia+" <<<<<<<<<<<<<<<<");
		if (totalLiquidaciones < Integer
				.parseInt(parametrosSingleton.getParametros().get(MidasOroConstantes.MIN_FUNDA).getValor())) {
			return manageFundaRango(entidad);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE ASIGNAR - TOTAL FUNDAS DISPONIBLES: ".concat(totalLiquidaciones.toString()));
		}
	}

	public void deleteRangoFunda(TbMiFundaRango entidad) throws RelativeException {
		fundaRangoRepository.deleteFunda(entidad);
	}

	/**
	 * Lote
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */

	public TbMiLote findLoteById(Long id) throws RelativeException {
		try {
			return loteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbMiLote> findLoteByParamPaged(String nombreLote, Date fechaDesde, Date fechaHasta,
			String usuarioCreacion, Long tipoOro, List<EstadoMidasEnum> estados, PaginatedWrapper pw)
			throws RelativeException {
		return this.loteRepository.findByParams(nombreLote, fechaDesde, fechaHasta, usuarioCreacion, tipoOro, estados,
				pw);
	}

	public Long countLoteByParamPaged(String nombreLote, Date fechaDesde, Date fechaHasta, String usuarioCreacion,
			Long tipoOro, List<EstadoMidasEnum> estados) throws RelativeException {
		return this.loteRepository.countBySpecification(
				new LoteByNombreLoteSpec(nombreLote, usuarioCreacion, tipoOro, fechaDesde, fechaHasta, estados));
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countLote() throws RelativeException {
		try {
			return loteRepository.countAll(TbMiLote.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Lote no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiLote> findAllLote(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.loteRepository.findAll(TbMiLote.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.loteRepository.findAll(TbMiLote.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.loteRepository.findAll(TbMiLote.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiLote manageLote(TbMiLote send) throws RelativeException {
		try {
			log.info("==> entra a manage Lote");
			TbMiLote persisted = null;
			if (send != null && send.getId() != null) {
				persisted = loteRepository.findById(send.getId());
				send.setFechaActualizacion(new Date());
				return this.updateLote(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				return loteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Funda " + e.getMessage());
		}
	}

	public TbMiLote persistLoteCajaFuerteMatriz(TbMiLote send) throws RelativeException {
		try {
			List<TbMiBodega> bodegas = this.bodegaRepository
					.findAllBySpecification(new BodegaByParamsSpec(TipoAgenciaEnum.MATRIZ, TipoBodegaEnum.CAJA_FUERTE));
			if (bodegas == null || bodegas.isEmpty() || bodegas.get(0).getTbMiAgencia() == null) {
				throw new Exception("No se encontro la bodega tipo caja fuerte en la agencia matriz");
			}
			if (send == null || (send.getNombreLote() == null)) {
				throw new Exception(
						"No se pudo comprobar si existe un lote vigente del mismo tipo de oro, vefirique los parametros enviados");
			}
			// BUSCAR SI EXISTE UN LOTE VIGENTE DEL MISMO TIPO ORO

			List<EstadoMidasEnum> estadosEnum = new ArrayList<>();
			estadosEnum.add(EstadoMidasEnum.VIGENTE);
			List<TbMiLote> loteSimilar = this.loteRepository.findByParams(send.getNombreLote(), null, null, null, null,
					estadosEnum, null);
			if (loteSimilar != null && !loteSimilar.isEmpty()) {
				TbMiLote result = loteSimilar.stream()
						.filter(f -> f.getTbMiAgencia().getId().compareTo(bodegas.get(0).getTbMiAgencia().getId()) == 0)
						.findFirst().orElse(null);
				if (result != null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							"Ya existe el lote " + result.getNombreLote() + " con nombre " + send.getNombreLote());
				}
			}

			send.setTbMiAgencia(bodegas.get(0).getTbMiAgencia());
			TbMiLote persisted = null;
			if (send != null && send.getId() != null) {
				persisted = loteRepository.findById(send.getId());
				send.setFechaActualizacion(new Date());
				return this.updateLote(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				return loteRepository.add(send);
			} else {
				throw new Exception("No se realizo la transacion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Funda " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public TbMiLote updateLote(TbMiLote send, TbMiLote persisted) throws RelativeException {
		try {

			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setFechaCierre(send.getFechaCierre());
			persisted.setIdentificador(send.getIdentificador());
			persisted.setNombreLote(send.getNombreLote());
			persisted.setResponsable(send.getResponsable());
			persisted.setTipoLote(send.getTipoLote());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setPesoBrutoCompra(send.getPesoBrutoCompra());
			persisted.setDescuentoCompra(send.getDescuentoCompra());
			persisted.setPesoNetoCompra(send.getPesoNetoCompra());
			persisted.setPesoBrutoRetazado(send.getPesoBrutoRetazado());
			persisted.setDescuentoRetazado(send.getDescuentoRetazado());
			persisted.setPesoNetoRetazado(send.getPesoNetoRetazado());
			persisted.setPrecioCompra(send.getPrecioCompra());
			persisted.setPrecioVenta(send.getPrecioVenta());
			persisted.setUtilidad(send.getUtilidad());
			persisted.setLey(send.getLey());
			persisted.setCostoPorGramo(send.getCostoPorGramo());
			persisted.setTotalGramos(send.getTotalGramos());
			persisted.setPesoNetoVendido(send.getPesoNetoVendido());
			persisted.setPorcentajeIva(send.getPorcentajeIva());
			persisted.setIva(send.getIva());
			persisted.setValorPagar(send.getValorPagar());
			persisted.setTbMiVentaLote(send.getTbMiVentaLote());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTbMiTipoOro(send.getTbMiTipoOro());

			return loteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Lote " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna una lista de lotes dos Estados
	 * 
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiLote> lotesByAgenciaEstados(EstadoMidasEnum estado1, EstadoMidasEnum estado2)
			throws RelativeException {
		try {
			return this.loteRepository.findAllBySpecification(new LoteByEstadoAgencia(estado1, estado2));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al listar lotes por agencia y estados " + e.getMessage());
		}
	}

	/**
	 * Metodo persiste una lista de lotes y los retorna
	 * 
	 * @param sends
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiLote> manageLotes(List<TbMiLote> sends, TbMiVentaLote ventaLote) throws RelativeException {
		try {
			List<TbMiLote> persisteds = new ArrayList<TbMiLote>();
			if (sends != null && !sends.isEmpty()) {
				for (TbMiLote send : sends) {
					send.setTbMiVentaLote(ventaLote);
					persisteds.add(this.manageLote(send));
				}
				return persisteds;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Funda " + e.getMessage());
		}
	}

	public TbMiLote updatePesosPrecioLote(TbMiJoya tbMiJoya, TbMiLote tbMiLote) throws RelativeException {
		try {
			TbMiLote lotePersisted = this.findLoteById(tbMiLote.getId());
			if (lotePersisted == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "El lote no existe");
			}

			lotePersisted.setPesoBrutoCompra(lotePersisted.getPesoBrutoCompra() == null ? tbMiJoya.getPesoBruto()
					: lotePersisted.getPesoBrutoCompra().add(tbMiJoya.getPesoBruto()));

			lotePersisted.setDescuentoCompra(lotePersisted.getDescuentoCompra() == null ? tbMiJoya.getDescuento()
					: lotePersisted.getDescuentoCompra().add(tbMiJoya.getDescuento()));

			lotePersisted.setPesoNetoCompra(lotePersisted.getPesoNetoCompra() == null ? tbMiJoya.getPeso()
					: lotePersisted.getPesoNetoCompra().add(tbMiJoya.getPeso()));

			lotePersisted
					.setPesoBrutoRetazado(lotePersisted.getPesoBrutoRetazado() == null ? tbMiJoya.getPesoBrutoRetazado()
							: lotePersisted.getPesoBrutoRetazado().add(tbMiJoya.getPesoBrutoRetazado()));

			lotePersisted
					.setDescuentoRetazado(lotePersisted.getDescuentoRetazado() == null ? tbMiJoya.getDescuentoRetazado()
							: lotePersisted.getDescuentoRetazado().add(tbMiJoya.getDescuentoRetazado()));

			lotePersisted
					.setPesoNetoRetazado(lotePersisted.getPesoNetoRetazado() == null ? tbMiJoya.getPesoNetoRetazado()
							: lotePersisted.getPesoNetoRetazado().add(tbMiJoya.getPesoNetoRetazado()));

			lotePersisted.setPrecioCompra(lotePersisted.getPrecioCompra() == null ? tbMiJoya.getValor()
					: lotePersisted.getPrecioCompra().add(tbMiJoya.getValor()));

			return this.loteRepository.update(lotePersisted);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Ocurrio un error al actualizar los valores del lote" + e);
		}
	}

	/**
	 * Documento Habilitante
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiDocumentoHabilitante findDocumentoHabilitanteById(Long id) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public TbMiDocumentoHabilitante findDocumentoHabilitanteByTipoDocumentoAndCodigoContrato(String codigoContrato,
			Long idTipoDocumento) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndContrato(codigoContrato, idTipoDocumento);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public TbMiDocumentoHabilitante findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(Long idJoya,
			Long idAbono, Long idVentaLote, Long idCorteCaja, Long idTipoDocumento) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndJoyaAndAbono(idJoya, idAbono, idVentaLote,idCorteCaja,
					idTipoDocumento);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbMiDocumentoHabilitante> findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbonos(Long idJoya,
			Long idAbono, Long idTipoDocumento) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndJoyaAndAbonos(idJoya, idAbono, idTipoDocumento);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbMiDocumentoHabilitante> findDocumentoHabilitanteByCodigoContrato(String codigoContrato)
			throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByContrato(codigoContrato);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"No existe documentos habilitantes para contrato " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countdDocumentoHabilitante() throws RelativeException {
		try {
			return documentoHabilitanteRepository.countAll(TbMiDocumentoHabilitante.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"documentoHabilitanteRepository no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiDocumentoHabilitante> findAllDocumentoHabilitante(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.documentoHabilitanteRepository.findAll(TbMiDocumentoHabilitante.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.documentoHabilitanteRepository.findAll(TbMiDocumentoHabilitante.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.documentoHabilitanteRepository.findAll(TbMiDocumentoHabilitante.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiDocumentoHabilitante manageDocumentoHabilitante(TbMiDocumentoHabilitante send) throws RelativeException {
		try {
			log.info("==> entra a manage TbMiDocumentoHabilitante");
			TbMiDocumentoHabilitante persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findDocumentoHabilitanteById(send.getId());
				return this.updateDocumentoHabilitante(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return documentoHabilitanteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiDocumentoHabilitante updateDocumentoHabilitante(TbMiDocumentoHabilitante send,
			TbMiDocumentoHabilitante persisted) throws RelativeException {
		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			persisted.setTbMiTipoDocumento(send.getTbMiTipoDocumento());
			if (send.getTbMiContrato() != null) {
				persisted.setTbMiContrato(send.getTbMiContrato());
			}
			if (send.getTbMiJoya() != null) {
				persisted.setTbMiJoya(send.getTbMiJoya());
			}
			if (send.getTbMiAbono() != null) {
				persisted.setTbMiAbono(send.getTbMiAbono());
			}
			return documentoHabilitanteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando documentoHabilitanteRepository " + e.getMessage());
		}
	}

	/**
	 * Metodo que realiza el registro del documento habiliante basado en el file
	 * wrapper cargado con la informacion del front
	 * 
	 * @param fw file wrapper con el archivo y datos requeridos para el registro
	 * @return Documento generado
	 * @throws RelativeException
	 */
	public TbMiDocumentoHabilitante generateDocumentoHabilitante(FileWrapper fw) throws RelativeException {
		TbMiDocumentoHabilitante dhs = null;
		TbMiContrato mc = null;
		TbMiJoya mj = null;
		TbMiAbono ma = null;
		TbMiVentaLote vl = null;
		TbMiCorteCaja cc= null;
		TbMiTipoDocumento td = null;
		TbMiDocumentoHabilitante da = null;
		try {
			if (fw.getProcess() == null || fw.getProcess().equalsIgnoreCase("CONTRATO")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndCodigoContrato(fw.getRelatedIdStr(),
						Long.valueOf(fw.getTypeAction()));
			} else if (fw.getProcess().equalsIgnoreCase("JOYA")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
						Long.valueOf(fw.getRelatedIdStr()), null, null,null, Long.valueOf(fw.getTypeAction()));
			} else if (fw.getProcess().equalsIgnoreCase("ABONO")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(null,
						Long.valueOf(fw.getRelatedIdStr()), null,null, Long.valueOf(fw.getTypeAction()));
			} else if (fw.getProcess().equalsIgnoreCase("VENTA_LOTE")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(null, null,
						Long.valueOf(fw.getRelatedIdStr()),null, Long.valueOf(fw.getTypeAction()));
			}else if (fw.getProcess().equalsIgnoreCase("CIERRE_CAJA")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(null, null,null,
						Long.valueOf(fw.getRelatedIdStr()), Long.valueOf(fw.getTypeAction()));
			}
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			dhs = null;
			log.info("===================: error no existe datos para contrato y accion " + fw.getRelatedIdStr() + "  "
					+ fw.getTypeAction());
		}
		da = new TbMiDocumentoHabilitante();
		if (dhs != null) {
			da.setId(dhs.getId());
		}
		td = this.findTipoDocumentoById(Long.valueOf(fw.getTypeAction()));
		da.setTbMiTipoDocumento(td);
		if (fw.getProcess() == null || fw.getProcess().equalsIgnoreCase("CONTRATO")) {
			// mc = this.findContratoByCodigo(fw.getRelatedIdStr());
			mc = this.findContratoById(Long.valueOf(fw.getRelatedIdStr()));
			da.setTbMiContrato(mc);
		} else if (fw.getProcess().equalsIgnoreCase("JOYA")) {
			mj = this.findJoyaById(Long.valueOf(fw.getRelatedIdStr()));
			da.setTbMiJoya(mj);
		} else if (fw.getProcess().equalsIgnoreCase("ABONO")) {
			ma = this.findAbonoById(Long.valueOf(fw.getRelatedIdStr()));
			da.setTbMiAbono(ma);
		} else if (fw.getProcess().equalsIgnoreCase("VENTA_LOTE")) {
			vl = this.findVentaLoteById(Long.valueOf(fw.getRelatedIdStr()));
			da.setTbMiVentaLote(vl);
		} else if (fw.getProcess().equalsIgnoreCase("CIERRE_CAJA")) {
			cc = this.findCorteCajaById(Long.valueOf(fw.getRelatedIdStr()));
			da.setTbMiCorteCaja(cc);
		}
		da.setArchivo(fw.getFile());
		da.setEstado(EstadoMidasEnum.ACT);
		da.setFechaCreacion(new Date(System.currentTimeMillis()));
		da.setNombreArchivo(fw.getName());
		return this.manageDocumentoHabilitante(da);
	}

	/**
	 * Metodo que valida si los documentos habilitantes registrados corresponden an
	 * numero de tipos de documentos esperados.
	 * 
	 * @param codigoContrato Codigo de contrato a validar
	 * @param tipoDocumento  Tipo de documento a validar
	 * @return True si el numero de documentos habilitantes es igual al numero de
	 *         tipo de documentos esperados
	 * @throws RelativeException
	 */
	/**
	 * @param codigoContrato
	 * @param tipoDocumento
	 * @param estadoContrato
	 * @param estadoJoya
	 * @return
	 * @throws RelativeException
	 */
	/**
	 * @param codigoContrato
	 * @param tipoDocumento
	 * @param estadoContrato
	 * @param estadoJoya
	 * @return
	 * @throws RelativeException
	 */
	public Boolean validateContratoByHabilitante(String codigoContrato, String tipoDocumento, String estadoContrato,
			String estadoJoya, String usuario, Boolean salida) throws RelativeException {
		log.info("============>validateContratoByHabilitante ");
		log.info("============>validateContratoByHabilitante codigoContrato " + codigoContrato);
		log.info("============>validateContratoByHabilitante tipoDocumento " + tipoDocumento);
		log.info("============>validateContratoByHabilitante estadoContrato " + estadoContrato);
		log.info("============>validateContratoByHabilitante estadoJoya " + estadoJoya);
		try {
			// TbMiContrato c = this.findContratoByCodigo(codigoContrato);
			TbMiContrato c = this.contratoRepository.findById(Long.valueOf(codigoContrato.trim()));

			log.info("============>validateContratoByHabilitante eocntro contrato " + c);
			if (c != null)
				log.info("============xxx>validateContratoByHabilitante eocntro contrato " + c.getId());
			log.info("============xxx>validateContratoByHabilitante eocntro contrato " + c.getCodigo());
			codigoContrato = c.getCodigo();
			if (c != null && c.getEstado() != null
					&& c.getEstado().compareTo(EstadoContratoEnum.PENDIENTE_HABILITANTE) != 0) {
				return Boolean.TRUE;
			}
			log.info("============>validateContratoByHabilitante 1");
			List<TbMiDocumentoHabilitante> dhs = this
					.findDocumentoHabilitanteByCodigoContrato(String.valueOf(c.getId()));
			log.info("============>validateContratoByHabilitante 2");
			List<TbMiTipoDocumento> tds = this.findDocumentoByTipoAndCodigo(null, tipoDocumento, null, null, null);
			tds = tds.stream().distinct().collect(Collectors.toList());
			log.info("============>validateContratoByHabilitante 3");
			if (dhs != null && !dhs.isEmpty()) {
				log.info("============>validateContratoByHabilitante documento habilitantes size: " + dhs.size());
				dhs = dhs.stream()
						.filter(dh -> dh.getTbMiTipoDocumento().getTipoDocumento()
								.compareTo(MidasOroUtil.getEnumFromString(TipoDocumentoEnum.class, tipoDocumento)) == 0)
						.collect(Collectors.toList());
				log.info("============>validateContratoByHabilitante documento habilitantes filtered size: "
						+ dhs.size());
				log.info("============>validateContratoByHabilitante tipo documento habilitantes size: " + tds.size());
				if (salida && dhs.size()>0) {
					log.info("============>validateContratoByHabilitante seteamdp estadp : "
							+ MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estadoContrato));
					c.setEstado(MidasOroUtil.getEnumFromString(EstadoContratoEnum.class, estadoContrato));
					this.manageContrato(c);
					log.info("============>validateContratoByHabilitante teroma actualizar estado ");
					if (estadoJoya != null && !estadoJoya.isEmpty()) {
						log.info("============>validateContratoByHabilitante VA A CTUALIZAR JOYA "
								+ MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya));

						this.joyaRepository.updateBatch(
								MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya),
								c.getTbMiFunda().getId());

						List<TbMiJoya> joyasContrato = this.joyaRepository.findByIdFunda(null,
								c.getTbMiFunda().getId());
						for (TbMiJoya j : joyasContrato) {
							j.setEstado(MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya));
							j.setUsuario(usuario);
							j.setProceso(c.getProceso());
							this.manageJoya(j);
						}
						log.info("============>validateContratoByHabilitante teroma actualizar joya ");
					}
					return Boolean.TRUE;
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							"NO ESTAN CARGADOS TODOS LOS HABILITANTES:" + dhs.size() + "/" + tds.size());
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA EL CONTRATO " + codigoContrato);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA EL CONTRATO" + codigoContrato);
		}
	}

	public Boolean validateJoyaByHabilitante(Long idJoya, String tipoDocumento, String estadoJoya, String usuario,
			Boolean salida) throws RelativeException {
		log.info("============>validateJoyaByHabilitante ");
		log.info("============>validateJoyaByHabilitante idJoya " + idJoya);
		log.info("============>validateJoyaByHabilitante tipoDocumento " + tipoDocumento);
		log.info("============>validateJoyaByHabilitante estadoJoya " + estadoJoya);
		try {
			TbMiJoya c = this.findJoyaById(idJoya);
			log.info("============>validateJoyaByHabilitante eocntro joya " + c);
			if (c != null) {
				log.info("============>validateJoyaByHabilitante eocntro jpya " + c.getId());
				log.info("============>validateJoyaByHabilitante eocntro jpya " + c.getEstado());
			}

			if (c != null && c.getEstado() != null
					&& c.getEstado().compareTo(EstadoJoyaEnum.PENDIENTE_HABILITANTE) != 0) {
				log.info("============>validateJoyaByHabilitante eocntro jpya " + c.getEstado());
				return Boolean.FALSE;
			}
			log.info("============>validateJoyaByHabilitante 1");
			List<TbMiDocumentoHabilitante> dhs = this
					.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbonos(idJoya, null, null);
			log.info("============>validateJoyaByHabilitante 2");
			List<TbMiTipoDocumento> tds = this.findDocumentoByTipoAndCodigo(null, tipoDocumento, null, null, null);
			tds = tds.stream().distinct().collect(Collectors.toList());
			log.info("============>validateJoyaByHabilitante 3");
			if (dhs != null && !dhs.isEmpty()) {
				log.info("============>validateJoyaByHabilitante documento habilitantes size: " + dhs.size());
				dhs = dhs.stream()
						.filter(dh -> dh.getTbMiTipoDocumento().getTipoDocumento()
								.compareTo(MidasOroUtil.getEnumFromString(TipoDocumentoEnum.class, tipoDocumento)) == 0)
						.collect(Collectors.toList());
				log.info("============>validateJoyaByHabilitante documento habilitantes filtered size: " + dhs.size());
				log.info("============>validateJoyaByHabilitante tipo documento habilitantes size: " + tds.size());
				if (salida && dhs.size()>0) {
					log.info("============>validateJoyaByHabilitante seteamdp estadp : "
							+ MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya));
					c.setEstado(MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoJoya));
					c.setUsuario(usuario);
					this.manageJoya(c);
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA JOYA " + idJoya);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA JOYA " + idJoya);
		}
	}

	public Boolean validateCorteCajaByHabilitante(Long idCorteCaja, String tipoDocumento, String estadoVentaLote,
			String usuario, boolean salida) throws RelativeException {
		log.info("============>validateAbonoByHabilitante ");
		log.info("============>validateAbonoByHabilitante idVentaLote " + idCorteCaja);
		log.info("============>validateAbonoByHabilitante tipoDocumento " + tipoDocumento);
		log.info("============>validateAbonoByHabilitante estadoJoya " + estadoVentaLote);
		try {
			TbMiCorteCaja c = this.findCorteCajaById(idCorteCaja);
			log.info("============>validateAbonoByHabilitante encontro VentaLote " + c);
			if (c != null)
				log.info("============>validateAbonoByHabilitante encontro VentaLote " + c.getId());
			if (c != null && c.getEstado() != null
					&& c.getEstado().compareTo(EstadoMidasEnum.PENDIENTE_HABILITANTE) != 0) {
				return Boolean.TRUE;
			}
			log.info("============>validateAbonoByHabilitante 1");
			List<TbMiDocumentoHabilitante> dhs = this.findDocumentoHabilitanteByTipoDocumentoAndCorteCaja(idCorteCaja,
					null);
			log.info("============>validateAbonoByHabilitante 2");
			List<TbMiTipoDocumento> tds = this.findDocumentoByTipoAndCodigo(null, tipoDocumento, null, null, null);
			tds = tds.stream().distinct().collect(Collectors.toList());
			log.info("============>validateAbonoByHabilitante 3");
			if (dhs != null && !dhs.isEmpty()) {
				log.info("============>validateAbonoByHabilitante documento habilitantes size: " + dhs.size());
				dhs = dhs.stream()
						.filter(dh -> dh.getTbMiTipoDocumento().getTipoDocumento()
								.compareTo(MidasOroUtil.getEnumFromString(TipoDocumentoEnum.class, tipoDocumento)) == 0)
						.collect(Collectors.toList());
				log.info("============>validateAbonoByHabilitante documento habilitantes filtered size: " + dhs.size());
				log.info("============>validateAbonoByHabilitante tipo documento habilitantes size: " + tds.size());
				if (salida && dhs.size()>0) {
					log.info("============>validateAbonoByHabilitante seteamdp estadp : "
							+ MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estadoVentaLote));
					c.setEstado(MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estadoVentaLote));
					c.setUsuarioActualizacion(usuario);
					this.manageCorteCaja(c);
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA VentaLote " + idCorteCaja);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA VentaLote" + idCorteCaja);
		}
	}
	private List<TbMiDocumentoHabilitante> findDocumentoHabilitanteByTipoDocumentoAndCorteCaja(Long idCorteCaja,
			Long idTipoDocumento) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndIdCorteCaja(idCorteCaja, idTipoDocumento);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Boolean validateVentaLoteByHabilitante(Long idVentaLote, String tipoDocumento, String estadoVentaLote,
			String usuario, Boolean salida) throws RelativeException {
		log.info("============>validateAbonoByHabilitante ");
		log.info("============>validateAbonoByHabilitante idVentaLote " + idVentaLote);
		log.info("============>validateAbonoByHabilitante tipoDocumento " + tipoDocumento);
		log.info("============>validateAbonoByHabilitante estadoJoya " + estadoVentaLote);
		try {
			TbMiVentaLote c = this.findVentaLoteById(idVentaLote);
			log.info("============>validateAbonoByHabilitante encontro VentaLote " + c);
			if (c != null)
				log.info("============>validateAbonoByHabilitante encontro VentaLote " + c.getId());
			if (c != null && c.getEstado() != null
					&& c.getEstado().compareTo(EstadoJoyaEnum.PENDIENTE_HABILITANTE) != 0) {
				return Boolean.TRUE;
			}
			log.info("============>validateAbonoByHabilitante 1");
			List<TbMiDocumentoHabilitante> dhs = this.findDocumentoHabilitanteByTipoDocumentoAndVentaLote(idVentaLote,
					null);
			log.info("============>validateAbonoByHabilitante 2");
			List<TbMiTipoDocumento> tds = this.findDocumentoByTipoAndCodigo(null, tipoDocumento, null, null, null);
			tds = tds.stream().distinct().collect(Collectors.toList());
			log.info("============>validateAbonoByHabilitante 3");
			if (dhs != null && !dhs.isEmpty()) {
				log.info("============>validateAbonoByHabilitante documento habilitantes size: " + dhs.size());
				dhs = dhs.stream()
						.filter(dh -> dh.getTbMiTipoDocumento().getTipoDocumento()
								.compareTo(MidasOroUtil.getEnumFromString(TipoDocumentoEnum.class, tipoDocumento)) == 0)
						.collect(Collectors.toList());
				log.info("============>validateAbonoByHabilitante documento habilitantes filtered size: " + dhs.size());
				log.info("============>validateAbonoByHabilitante tipo documento habilitantes size: " + tds.size());
				if (salida && dhs.size()>0) {
					log.info("============>validateAbonoByHabilitante seteamdp estadp : "
							+ MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoVentaLote));
					c.setEstado(MidasOroUtil.getEnumFromString(EstadoJoyaEnum.class, estadoVentaLote));
					c.setUsuarioActualizacion(usuario);
					this.manageVentaLote(c);
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA VentaLote " + idVentaLote);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA LA VentaLote" + idVentaLote);
		}
	}

	private List<TbMiDocumentoHabilitante> findDocumentoHabilitanteByTipoDocumentoAndVentaLote(Long idVentaLote,
			Long idTipoDocumento) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndIdVentaLote(idVentaLote, idTipoDocumento);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Boolean validateAbonoByHabilitante(Long idAbono, String tipoDocumento, String estadAbono, String usuario,
			Boolean salida) throws RelativeException {
		log.info("============>validateAbonoByHabilitante ");
		log.info("============>validateAbonoByHabilitante idAbono " + idAbono);
		log.info("============>validateAbonoByHabilitante tipoDocumento " + tipoDocumento);
		log.info("============>validateAbonoByHabilitante estadAbono " + estadAbono);
		try {
			TbMiAbono c = this.findAbonoById(idAbono);
			log.info("============>validateAbonoByHabilitante encontro abono " + c);
			if (c != null)
				log.info("============>validateAbonoByHabilitante encontro abono " + c.getId());
			if (c != null && c.getEstado() != null
					&& c.getEstado().compareTo(EstadoMidasEnum.PENDIENTE_HABILITANTE) != 0) {
				return Boolean.TRUE;
			}
			log.info("============>validateAbonoByHabilitante 1");
			List<TbMiDocumentoHabilitante> dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbonos(null,
					idAbono, null);
			log.info("============>validateAbonoByHabilitante 2");
			List<TbMiTipoDocumento> tds = this.findDocumentoByTipoAndCodigo(null, tipoDocumento, null, null, null);
			tds = tds.stream().distinct().collect(Collectors.toList());
			log.info("============>validateAbonoByHabilitante 3");
			if (dhs != null && !dhs.isEmpty()) {
				log.info("============>validateAbonoByHabilitante documento habilitantes size: " + dhs.size());
				dhs = dhs.stream()
						.filter(dh -> dh.getTbMiTipoDocumento().getTipoDocumento()
								.compareTo(MidasOroUtil.getEnumFromString(TipoDocumentoEnum.class, tipoDocumento)) == 0)
						.collect(Collectors.toList());
				log.info("============>validateAbonoByHabilitante documento habilitantes filtered size: " + dhs.size());
				log.info("============>validateAbonoByHabilitante tipo documento habilitantes size: " + tds.size());
				if (salida && dhs.size()>0) {
					log.info("============>validateAbonoByHabilitante seteamdp estadp : "
							+ MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estadAbono));
					c.setEstado(MidasOroUtil.getEnumFromString(EstadoMidasEnum.class, estadAbono));
					c.setUsuarioCreacion(usuario);
					this.manageAbono(c);
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA EL ABONO " + idAbono);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO EXISTE DOCUMENTOS HABILITANTES CARGADOS PARA EL ABONO" + idAbono);
		}
	}

	public List<HabilitanteWrapper> findDocHabByParams(PaginatedWrapper pw, String codigoContrato, String codigoJoya,
			String nombreCliente, String identificacionCliente, EstadoMidasEnum estado, Long tipoDocumento, Date fechaDesde, Date fechaHasta, Long idAgencia) throws RelativeException {
		return this.documentoHabilitanteRepository.findByParams(pw, codigoContrato, codigoJoya, nombreCliente,
				identificacionCliente, estado,tipoDocumento,fechaDesde,fechaHasta,idAgencia);
	}

	public Long countDocHabByParams(String codigoContrato, String codigoJoya, String nombreCliente,
			String identificacionCliente, EstadoMidasEnum estado, Long tipoDocumento, Date fechaDesde, Date fechaHasta, Long idAgencia) throws RelativeException {
		return this.documentoHabilitanteRepository.countByParams(codigoContrato, codigoJoya, nombreCliente,
				identificacionCliente, estado,tipoDocumento,fechaDesde,fechaHasta,idAgencia);
	}

	/**
	 * MovimientoCaja
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiMovimientoCaja findMovimientoCajaById(Long id) throws RelativeException {
		try {
			return movimientoCajaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countMovimientoCaja() throws RelativeException {
		try {
			return movimientoCajaRepository.countAll(TbMiMovimientoCaja.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "MovimientoCaja no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiMovimientoCaja> findAllMovimientoCaja(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.movimientoCajaRepository.findAll(TbMiMovimientoCaja.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.movimientoCajaRepository.findAll(TbMiMovimientoCaja.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.movimientoCajaRepository.findAll(TbMiMovimientoCaja.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiMovimientoCaja manageMovimientoCaja(TbMiMovimientoCaja send) throws RelativeException {
		try {
			log.info("==> entra a manage MovimientoCaja");
			TbMiMovimientoCaja persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findMovimientoCajaById(send.getId());
				return this.updateMovimientoCaja(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return movimientoCajaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la MovimientoCaja " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiMovimientoCaja updateMovimientoCaja(TbMiMovimientoCaja send, TbMiMovimientoCaja persisted)
			throws RelativeException {
		try {
			persisted.setEgreso(send.getEgreso());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setFormaPago(send.getFormaPago());
			persisted.setIngreso(send.getIngreso());
			persisted.setTbMiCaja(send.getTbMiCaja());
			persisted.setTbMiCuenta(send.getTbMiCuenta());
			persisted.setTipo(send.getTipo());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setTbMiBanco(send.getTbMiBanco());
			persisted.setNumeroCuentaBanco(send.getNumeroCuentaBanco());
			persisted.setTipoCuentaBanco(send.getTipoCuentaBanco());
			persisted.setTbMiCliente(send.getTbMiCliente());
			persisted.setTbMiContrato(send.getTbMiContrato());
			persisted.setTipoTarjeta(send.getTipoTarjeta());
			persisted.setNumeroTarjeta(send.getNumeroTarjeta());
			persisted.setHabienteTarjeta(send.getHabienteTarjeta());
			persisted.setCedHabienteTarjeta(send.getCedHabienteTarjeta());

			return movimientoCajaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando MovimientoCaja " + e.getMessage());
		}
	}

	public List<TbMiMovimientoCaja> findMovCajaByParamPaged(String fechaDesde, String fechaHasta, String codigoContrato,
			String identificacionCliente, EstadoMidasEnum estado, PaginatedWrapper pw) throws RelativeException {
		if (pw != null) {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.movimientoCajaRepository.findByParamPaged(fechaDesde, fechaHasta, codigoContrato,
						identificacionCliente, estado, pw.getCurrentPage(), pw.getPageSize(), pw.getSortFields(),
						pw.getSortDirections());
			}
		}
		return this.movimientoCajaRepository.findByParam(fechaDesde, fechaHasta, codigoContrato, identificacionCliente,
				estado);
	}

	public Long countMovCajaByParamPaged(String fechaDesde, String fechaHasta, String codigoContrato,
			String identificacionCliente, EstadoMidasEnum estado) throws RelativeException {
		try {
			return movimientoCajaRepository.countFindMovimientoCajaByParam(fechaDesde, fechaHasta, codigoContrato,
					identificacionCliente, estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Movimiento Caja no encontrado " + e.getMessage());
		}
	}

	public List<TbMiMovimientoCaja> findMovimientoCajaByParams(PaginatedWrapper pw, EstadoMidasEnum estado,
			Date fechaDesde, Date fechaHasta, String codigoContrato, String identificacionCliente,
			FormaPagoEnum formaPago, Long idAgencia) throws RelativeException {
		return this.movimientoCajaRepository.findByParams(pw, estado, fechaDesde, fechaHasta, codigoContrato,
				identificacionCliente, formaPago, idAgencia);
	}

	public Long countMovimientoCajaByParams(EstadoMidasEnum estado, Date fechaDesde, Date fechaHasta,
			String codigoContrato, String identificacionCliente, FormaPagoEnum formaPago, Long idAgencia)
			throws RelativeException {
		return this.movimientoCajaRepository.countByParams(estado, fechaDesde, fechaHasta, codigoContrato,
				identificacionCliente, formaPago, idAgencia);
	}

	/**
	 * Caja
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiCaja findCajaById(Long id) throws RelativeException {
		try {
			return cajaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCaja() throws RelativeException {
		try {
			return cajaRepository.countAll(TbMiCaja.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Caja no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCaja> findAllCaja(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.cajaRepository.findAll(TbMiCaja.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cajaRepository.findAll(TbMiCaja.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.cajaRepository.findAll(TbMiCaja.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiCaja manageCaja(TbMiCaja send) throws RelativeException {
		try {
			log.info("==> entra a manage Caja");
			TbMiCaja persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findCajaById(send.getId());
				return this.updateCaja(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				return cajaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Caja " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiCaja updateCaja(TbMiCaja send, TbMiCaja persisted) throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setSaldoActual(send.getSaldoActual());

			return cajaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Caja " + e.getMessage());
		}
	}

	public TbMiCaja findCajaByAgencia(Long idAgencia) throws RelativeException {
		try {
			List<TbMiCaja> cajasAgencia = this.cajaRepository.findAllBySpecification(new CajaByAgenciaId(idAgencia));
			if (cajasAgencia != null && !cajasAgencia.isEmpty()) {
				return cajasAgencia.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Caja " + e.getMessage());
		}
	}

	/**
	 * Cuenta
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiCuenta findCuentaById(Long id) throws RelativeException {
		try {
			return cuentaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCuenta() throws RelativeException {
		try {
			return cuentaRepository.countAll(TbMiCuenta.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cuenta no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCuenta> findAllCuenta(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.cuentaRepository.findAll(TbMiCuenta.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cuentaRepository.findAll(TbMiCuenta.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.cuentaRepository.findAll(TbMiCuenta.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiCuenta manageCuenta(TbMiCuenta send) throws RelativeException {
		try {
			log.info("==> entra a manage Cuenta");
			TbMiCuenta persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findCuentaById(send.getId());
				return this.updateCuenta(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				// send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return cuentaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Cuenta " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiCuenta updateCuenta(TbMiCuenta send, TbMiCuenta persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActulizacion(new Date());
			persisted.setNombre(send.getNombre());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());

			return cuentaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cuenta " + e.getMessage());
		}
	}

	/**
	 * DetalleCaja
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiDetalleCaja findDetalleCajaById(Long id) throws RelativeException {
		try {
			return detalleCajaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que DetalleCaja la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCorteCaja() throws RelativeException {
		try {
			return detalleCajaRepository.countAll(TbMiDetalleCaja.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "DetalleCaja no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiDetalleCaja> findAllDetalleCaja(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.detalleCajaRepository.findAll(TbMiDetalleCaja.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.detalleCajaRepository.findAll(TbMiDetalleCaja.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.detalleCajaRepository.findAll(TbMiDetalleCaja.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiDetalleCaja manageDetalleCaja(TbMiDetalleCaja send) throws RelativeException {
		try {
			log.info("==> entra a manage DetalleCaja");
			TbMiDetalleCaja persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findDetalleCajaById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateDetalleCaja(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return detalleCajaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la DetalleCaja " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiDetalleCaja updateDetalleCaja(TbMiDetalleCaja send, TbMiDetalleCaja persisted) throws RelativeException {
		try {
			persisted.setDenomincion(send.getDenomincion());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setNumero(send.getNumero());
			persisted.setTbMiCaja(send.getTbMiCaja());
			persisted.setTipo(send.getTipo());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setValor(send.getValor());
			return detalleCajaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando DetalleCaja " + e.getMessage());
		}

	}

	public List<TbMiDetalleCaja> findDetalleCajaByIdCorteCaja(Long idCorteCaja, PaginatedWrapper pw)
			throws RelativeException {

		if (pw != null) {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.detalleCajaRepository.findByIdCorteCaja(idCorteCaja, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			}

		}
		return this.detalleCajaRepository.findByIdCorteCaja(idCorteCaja);

	}

	public Long countDetalleCajaByIdCorteCaja(Long idCorteCaja) throws RelativeException {
		try {
			return detalleCajaRepository.countByIdCorteCaja(idCorteCaja);
		} catch (RelativeException e) {
			throw e;
		}
	}

	/**
	 * Joya Lote
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @throws RelativeException
	 * @author KEVIN CHAMORRO - Relative Engine
	 */
	public TbMiJoyaLote findJoyaLoteById(Long id) throws RelativeException {
		try {
			return joyaLoteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 * @author KEVIN CHAMORRO - Relative Engine
	 */
	public Long countJoyaLote() throws RelativeException {
		try {
			return joyaLoteRepository.countAll(TbMiJoyaLote.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cuenta no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @throws RelativeException
	 * @author KEVIN CHAMORRO - Relative Engine
	 */
	public List<TbMiJoyaLote> findAllJoyaLote(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.joyaLoteRepository.findAll(TbMiJoyaLote.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.joyaLoteRepository.findAll(TbMiJoyaLote.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.joyaLoteRepository.findAll(TbMiJoyaLote.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 * @author KEVIN CHAMORRO - Relative Engine
	 */
	public TbMiJoyaLote manageJoyaLote(TbMiJoyaLote send) throws RelativeException {
		try {
			log.info("==> entra a manage Joya Lote");
			TbMiJoyaLote persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findJoyaLoteById(send.getId());
				return this.updateJoyaLote(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				send.setFechaActualizacion(new Date());
				return joyaLoteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Cuenta " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 * @author KEVIN CHAMORRO - Relative Engine
	 */
	public TbMiJoyaLote updateJoyaLote(TbMiJoyaLote send, TbMiJoyaLote persisted) throws RelativeException {
		try {
			persisted.setTbMiJoya(send.getTbMiJoya());
			persisted.setTbMiLote(send.getTbMiLote());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			// persisted.setFechaCreacion(new Date());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			// persisted.setUsuarioCreacion(send.getUsuarioCreacion());
			return joyaLoteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cuenta " + e.getMessage());
		}
	}

	public List<TbMiJoyaLote> findJoyaLoteByIdLote(Long idLote) throws RelativeException {
		try {
			return this.joyaLoteRepository.findJoyaLoteByIdLote(idLote);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al obtener joya lote por id lote " + e.getMessage());
		}
	}

	private TbMiJoyaLote findJoyaLoteByIdJoya(Long idJoya) throws RelativeException {
		return this.joyaLoteRepository.findByIdJoya(idJoya);
	}

	public List<TbMiJoyaLote> findJoyaLoteByIdLote(PaginatedWrapper pw, Long idLote) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return joyaLoteRepository.findByIdLote(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), idLote);
		} else {
			return joyaLoteRepository.findByIdLote(idLote);
		}
	}

	public Long countJoyaLoteByIdLote(Long idLote) throws RelativeException {
		return joyaLoteRepository.countByIdLote(idLote);
	}

	/**
	 * Banco
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Jhon Romero - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiBanco findBancoById(Long id) throws RelativeException {
		try {
			return bancoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countBanco() throws RelativeException {
		try {
			return bancoRepository.countAll(TbMiBanco.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Banco no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiBanco> findAllBanco(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.bancoRepository.findAll(TbMiBanco.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.bancoRepository.findAll(TbMiBanco.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.bancoRepository.findAll(TbMiBanco.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiBanco manageBanco(TbMiBanco send) throws RelativeException {
		try {
			log.info("==> entra a manage Banco");
			TbMiBanco persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findBancoById(send.getId());
				return this.updateBanco(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				return bancoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Banco " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiBanco updateBanco(TbMiBanco send, TbMiBanco persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setFechaActualizacion(new Date());
			persisted.setNombre(send.getNombre());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setEstado(send.getEstado());
			return bancoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Banco " + e.getMessage());
		}
	}

	/**
	 * Lista todos los bancos
	 * 
	 * @param pw
	 * @param estado
	 * @param nombre
	 * @return
	 */
	public List<TbMiBanco> findBancoByParam(PaginatedWrapper pw, EstadoMidasEnum estado, String nombre)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.bancoRepository.findByParam(estado, nombre, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.bancoRepository.findByParam(estado, nombre);
		}
	}

	public Long countBancoByParam(EstadoMidasEnum estado, String nombre) throws RelativeException {
		// TODO Auto-generated method stub
		return this.bancoRepository.countByParam(estado, nombre);
	}

	/**
	 * ContratoCalculo
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Jhon Romero - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiContratoCalculo findContratoCalculoById(Long id) throws RelativeException {
		try {
			return contratoCalculoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countContratoCalculo() throws RelativeException {
		try {
			return contratoCalculoRepository.countAll(TbMiContratoCalculo.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ContratoCalculo no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiContratoCalculo> findAllContratoCalculo(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.contratoCalculoRepository.findAll(TbMiContratoCalculo.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.contratoCalculoRepository.findAll(TbMiContratoCalculo.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.contratoCalculoRepository.findAll(TbMiContratoCalculo.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiContratoCalculo manageContratoCalculo(TbMiContratoCalculo send) throws RelativeException {
		try {
			log.info("==> entra a manage ContratoCalculo");
			TbMiContratoCalculo persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findContratoCalculoById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateContratoCalculo(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return contratoCalculoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la ContratoCalculo " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param contratoCalculos
	 * @throws RelativeException
	 */
	public void addContratoCalculoBatch(TbMiContrato contrato, List<TbMiContratoCalculo> contratoCalculos)
			throws RelativeException {
		log.info("======>addContratoCalculoBatch ");
		log.info("======>addContratoCalculoBatch cantidad de calculos: " + contratoCalculos.size());
		contratoCalculos.forEach(cc -> {
			try {
				log.info("======>iterando " + cc.getRubro());
				log.info("======>iterando " + cc.getValor());
				log.info("======>iterando " + cc.getUsuario());
				log.info("======>iterando " + cc.getTbMiContrato());
				cc.setTbMiContrato(contrato);
				this.manageContratoCalculo(cc);
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiContratoCalculo updateContratoCalculo(TbMiContratoCalculo send, TbMiContratoCalculo persisted)
			throws RelativeException {
		try {
			persisted.setRubro(send.getRubro());
			persisted.setFechaActualizacion(new Date());
			persisted.setUsuario(send.getUsuario());
			persisted.setValor(send.getValor());
			persisted.setTbMiContrato(send.getTbMiContrato());
			return contratoCalculoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando ContratoCalculo " + e.getMessage());
		}
	}

	/**
	 * Busca los calculos por id de aprobar contrato
	 * 
	 * @param long1
	 * @return
	 */
	public List<TbMiContratoCalculo> findContratoCalculoByIdAprobarContrato(Long idAprobarContrato)
			throws RelativeException {

		return contratoCalculoRepository.findByIdAprobarContrato(idAprobarContrato);
	}

	/**
	 * Venta Lote
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiVentaLote findVentaLoteById(Long id) throws RelativeException {
		try {
			return ventaLoteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Venta Lote por id no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countVentaLote() throws RelativeException {
		try {
			return ventaLoteRepository.countAll(TbMiVentaLote.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Venta Lote no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiVentaLote> findAllVentaLote(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.ventaLoteRepository.findAll(TbMiVentaLote.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.ventaLoteRepository.findAll(TbMiVentaLote.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.ventaLoteRepository.findAll(TbMiVentaLote.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Venta Lote " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiVentaLote manageVentaLote(TbMiVentaLote send) throws RelativeException {
		try {
			log.info("==> entra a manage venta lote");
			TbMiVentaLote persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.ventaLoteRepository.findById(send.getId());
				return this.updateVentaLote(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return ventaLoteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Venta Lote " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send una lista de entidades con la informacion de creacion o
	 *             actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public List<TbMiVentaLote> manageListVentaLote(List<TbMiVentaLote> listSend) throws RelativeException {
		try {
			List<TbMiVentaLote> listaRetorno = new ArrayList<TbMiVentaLote>();
			for (TbMiVentaLote send : listSend) {
				TbMiVentaLote persisted = null;
				if (send != null && send.getId() != null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					persisted = this.ventaLoteRepository.findById(send.getId());
					listaRetorno.add(this.updateVentaLote(send, persisted));
				} else if (send != null && send.getId() == null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					listaRetorno.add(ventaLoteRepository.add(send));
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
				}
			}
			return listaRetorno;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Venta Lote " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiVentaLote updateVentaLote(TbMiVentaLote send, TbMiVentaLote persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setDescripcionFormaPago(send.getDescripcionFormaPago());
			persisted.setDescuento(send.getDescuento());
			persisted.setMedidaConversion(send.getMedidaConversion());
			persisted.setPrecioGramo(send.getPrecioGramo());
			persisted.setPrecioOnzaTroy(send.getPrecioOnzaTroy());
			persisted.setEstado(send.getEstado());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setUsuarioCreacion(send.getUsuarioCreacion());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setTbMiCliente(send.getTbMiCliente());
			persisted.setPorcentajeIva(send.getPorcentajeIva());
			persisted.setValorIva(send.getValorIva());
			persisted.setTotalGramosNegociados(send.getTotalGramosNegociados());
			return ventaLoteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Venta Lote " + e.getMessage());
		}
	}

	/**
	 * Metodo para generar el codigo de venta
	 * 
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public String generarCodigoVenta(Long idAgencia) throws RelativeException {
		try {
			TbMiAgencia agencia = this.agenciaRepository.findById(idAgencia);
			return this.ventaLoteRepository.generarCodigoVenta(agencia.getSeqVl());
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_FATAL,
					"Error al generar el codigo de venta " + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna una lista de venta lote por fechaCreacion desde y hasta,
	 * ademas busca por codigo
	 * 
	 * @param pw
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param codigoVenta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiVentaLote> findByFechasCodigo(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta,
			String codigoVenta) throws RelativeException {
		try {
			return this.ventaLoteRepository.findByFechasCodigo(pw, fechaDesde, fechaHasta, codigoVenta);
		} catch (Exception e) {
			throw new RelativeException(e.getMessage());
		}
	}

	/**
	 * Metodo que retorna la cantidad de venta lote por fechaCreacion desde y hasta,
	 * ademas busca por codigo
	 * 
	 * @param pw
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param codigoVenta
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Long countByFechasCodigo(Date fechaDesde, Date fechaHasta, String codigoVenta) throws RelativeException {
		try {
			return this.ventaLoteRepository
					.countBySpecification(new VentaLoteByFechaCodigoSpec(fechaDesde, fechaHasta, codigoVenta));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al contrar registros de Venta Lote" + e.getMessage());
		}
	}

	/**
	 * Metodo retorna TbMiLote relacionados con la TbMiVentaLote
	 * 
	 * @param pw
	 * @param idVentaLote
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public List<TbMiLote> findLotesByIdVenta(PaginatedWrapper pw, Long idVentaLote) throws RelativeException {
		try {
			return this.ventaLoteRepository.findLotesByIdVenta(pw, idVentaLote);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al contrar registros de Venta Lote" + e.getMessage());
		}
	}

	/**
	 * Metodo retorna la cantidad de TbMiLote relacionados con la TbMiVentaLote
	 * 
	 * @param pw
	 * @param idVentaLote
	 * @return
	 * @throws RelativeException
	 * @author kevin chamorro
	 */
	public Long countLotesByIdVenta(Long idVentaLote) throws RelativeException {
		try {
			return this.ventaLoteRepository.countBySpecification(new LoteByIdVentaLoteSpec(idVentaLote));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error al contrar registros de Venta Lote" + e.getMessage());
		}
	}

	/**
	 * Metodo que guarda la informacion de la venta lote
	 * 
	 * @param VentaLoteWrapper
	 * @return VentaLoteWrapper
	 * @throws RelativeException
	 */
	public VentaLoteWrapper venderLote(VentaLoteWrapper vlw) throws RelativeException {

		try {
			String codigoVentaLote = this.generarCodigoVenta(vlw.getIdAgencia());
			vlw.getVentaLote().setCodigo(codigoVentaLote);
			TbMiVentaLote ventaLote = this.manageVentaLote(vlw.getVentaLote());
			vlw.setVentaLote(ventaLote);
			Long idUbicacion = this.findBodegaByAgenciaAndNombre(vlw.getIdAgencia(), "CLIENTE").getId();
			List<TbMiLote> lotes = this.manageLotes(vlw.getLotes(), ventaLote);
			lotes = this.updateJoyasByLotes(lotes, vlw.getReUser());
			for (TbMiLote lote : lotes) {
				List<TbMiJoyaLote> joyas = this.findJoyaLoteByIdLote(lote.getId());
				for (TbMiJoyaLote j : joyas) {
					this.movimientoOroService.movimientoInventario(j.getTbMiJoya().getId(), idUbicacion,
							vlw.getReUser(), EstadoJoyaEnum.VENDIDA_LOTE, ProcesoEnum.VENTA_LOTE);
					j.getTbMiJoya().setEstado(EstadoJoyaEnum.VENDIDA_LOTE);
					j.getTbMiJoya().setProceso(ProcesoEnum.VENTA_LOTE);
					this.manageJoya(j.getTbMiJoya());
				}
			}

			/*
			 * for (TbMiMovimientoCaja caja : vlw.getMovimientos()) {
			 * this.movimientoCajaOroService.registrarIngreso(ProcesoEnum.VENTA_LOTE,
			 * "VENTA_LOTE", caja.getFormaPago(), caja.getIngreso(), vlw.getReUser(),
			 * vlw.getIdAgencia(), caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId()
			 * : null, caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(),
			 * vlw.getCliente().getId(), null, caja.getTipoTarjeta(),
			 * caja.getNumeroTarjeta(), caja.getHabienteTarjeta(),
			 * caja.getCedHabienteTarjeta(), caja.getComentario());
			 * 
			 * }
			 */

			return vlw;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL GUARDAR LA VENTA LOTE");
		}
	}

	/**
	 * Corte Caja
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */

	public TbMiCorteCaja findCorteCajaById(Long id) throws RelativeException {
		try {
			return corteCajaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca por agencia ultimo valor de cierre de caja de dicha agencia
	 * 
	 * @param idAgencia
	 * @return Objeto con el valor de cierre
	 * @throws RelativeException
	 */
	public TbMiCorteCaja findValorUltimoCierreCaja(Long idAgencia) throws RelativeException {
		try {
			return corteCajaRepository.reservarCaja(idAgencia);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada" + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 * @author ANDRES GRIJALVA - Relative Engine
	 */
	public Long countDetalleCaja() throws RelativeException {
		try {
			return corteCajaRepository.countAll(TbMiCorteCaja.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Corte no encontrado " + e.getMessage());
		}
	}

	public List<TbMiCorteCaja> findCorteCajaByParamPaged(Date fechaDesde, Date fechaHasta, EstadoMidasEnum estado,
			Long idAgencia, PaginatedWrapper pw) throws RelativeException {

		if (pw != null) {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.corteCajaRepository.findByParamPaged(fechaDesde, fechaHasta, estado, idAgencia,
						pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			}

		}
		return this.corteCajaRepository.findByParam(fechaDesde, fechaHasta, estado, idAgencia);

	}

	public Long countCorteCajaByParamPaged(Date fechaDesde, Date fechaHasta, EstadoMidasEnum estado, Long idAgencia)
			throws RelativeException {
		try {
			return corteCajaRepository.countByParamPaged(fechaDesde, fechaHasta, estado, idAgencia);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Movimiento Caja no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author ANDRES GRIJALVA - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCorteCaja> findAllCorteCaja(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.corteCajaRepository.findAll(TbMiCorteCaja.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.corteCajaRepository.findAll(TbMiCorteCaja.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.corteCajaRepository.findAll(TbMiCorteCaja.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 * @author ANDRES GRIJALVA - Relative Engine
	 */
	public TbMiCorteCaja manageCorteCaja(TbMiCorteCaja send) throws RelativeException {
		try {
			TbMiCorteCaja persisted = null;

			if (send != null && send.getId() != null) {
				persisted = corteCajaRepository.findById(send.getId());
				send.setFechaActualizacion(new Date());
				return this.updateCorteCaja(send, persisted);

			} else if (send != null && send.getId() == null) {
				/*
				 * if (send.getTbMiAgente() == null ||
				 * StringUtils.isBlank(send.getTbMiAgente().getNombreUsuario())) { throw new
				 * RelativeException("Se necesita el nombre de usuario del agente"); }
				 * TbMiAgente agente =
				 * this.agenteRepository.findByNombreUsuario(send.getTbMiAgente().
				 * getNombreUsuario()); if (agente == null) { throw new
				 * RelativeException("El agente no tiene nombre de usuario"); } if
				 * (send.getTbMiCaja() == null) { TbMiCaja caja =
				 * this.cajaRepository.findCajaByAgenciaId(send.getTbMiAgencia().getId()); if
				 * (caja == null) { throw new
				 * RelativeException("No existe una caja asociada a la agencia"); }
				 * send.setTbMiCaja(caja); } send.setTbMiAgente(agente);
				 */
				send.setFechaCreacion(new Date());
				// No ingresar cuando es cierre == > send.setFechaApertura(new Date());
				return corteCajaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "actualizando Corte Caja " + e.getMessage());
		}
	}

	public TbMiCorteCaja updateCorteCaja(TbMiCorteCaja send, TbMiCorteCaja persisted) throws RelativeException {
		try {

			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			if (send.getFechaCierre() != null) {
				persisted.setFechaCierre(send.getFechaCierre());
			}
			persisted.setTbMiAgente(send.getTbMiAgente());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTbMiCaja(send.getTbMiCaja());
			return corteCajaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Corte Caja " + e.getMessage());
		}
	}

	public TbMiCorteCaja validarCajaByAgenciaAndEstado(Long idAgencia, EstadoMidasEnum estado)
			throws RelativeException {
		return corteCajaRepository.reservarCaja(idAgencia, estado);
	}

	public TbMiCorteCaja aperturarCaja(Long idAgencia, String usuario, BigDecimal valor) throws RelativeException {
		try {

			TbMiCorteCaja corteAnterior = corteCajaRepository.reservarCaja(idAgencia, EstadoMidasEnum.ACT);
			// encontrar la caja de la agencia
			TbMiCaja caja = this.cajaRepository.findCajaByAgenciaId(idAgencia);
			// encontrar el agente
			TbMiAgente tbMiAgente = this.agenteRepository.findByNombreUsuario(usuario);

			if (corteAnterior != null && corteAnterior.getValorCierre() == null) { // validar q la caja este cerrada
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"PRIMERO DEBE CERRAR LA CAJA APERTURADA ANTERIORMENTE");
			}
			if (caja != null) {
				TbMiCorteCaja apertura = new TbMiCorteCaja();
				apertura.setEstado(EstadoMidasEnum.ACT);
				apertura.setFechaApertura(new Date());
				apertura.setFechaCreacion(new Date());
				apertura.setTbMiAgencia(caja.getTbMiAgencia());
				apertura.setTbMiAgente(tbMiAgente);
				apertura.setTbMiCaja(caja);
				apertura.setUsuarioCreacion(usuario);
				apertura.setValorApertura(valor);
				
				apertura = this.manageCorteCaja(apertura);
				this.movimientoCajaOroService.registrarIngreso(ProcesoEnum.APERTURA_CAJA, "APERTURA_CAJA",
						FormaPagoEnum.EFECTIVO, valor, usuario, idAgencia, null, null, null, null, null, null, usuario,
						usuario, usuario, null, null,apertura.getId());
				return apertura;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUEDE ENCONTRAR LA CAJA DE LA AGENCIA");
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"EXISTE PROBLEMAS EN APERTURAR LA CAJA DE LA AGENCIA  " + idAgencia + "  " + e.getMessage());
		}

	}

	public TbMiCorteCaja corteCaja(Long idAgencia, String username, Long idCorteApertura,
			List<TbMiDetalleCaja> detallesCaja, String justificacion, BigDecimal diferencia) throws RelativeException {
		try {
			// BUSQUEDA DE APERTURA
			TbMiCorteCaja corteApertura = this.corteCajaRepository.findById(idCorteApertura);
			if (corteApertura == null || corteApertura.getEstado() == null
					|| corteApertura.getEstado().compareTo(EstadoMidasEnum.ACT) != 0) {
				log.info("===> ESTADO CORTE APERTURA: " + corteApertura.getEstado());
				throw new RelativeException("No se encontro la apertura de caja o la apertura esta inactiva");
			}
			// BUSQUEDA AGENCIA POR ID
			TbMiAgencia agencia = this.findAgenciaById(idAgencia);
			// VALIDACION AGENCIA
			if (agencia == null) {
				throw new RelativeException("No se encontro la agencia logeada");
			}
			// BUSQUEDA AGENTE POR USERNAME
			TbMiAgente agente = this.findAgenteByUsername(username);
			// VALIDACION EXISTENCIA AGENTE
			if (agente == null) {
				throw new RelativeException("No se encontro un agente con el usarname logeado");
			}
			// BUSQUEDA CAJA POR AGENCIA
			TbMiCaja caja = this.cajaRepository.findCajaByAgenciaId(agencia.getId());
			// VALIDACION EXISTENCIA CAJA
			if (caja == null || caja.getSaldoActual() == null ) {
				throw new RelativeException("No se encontro una caja o no tiene saldo para la agencia actual");
			}
			// SUMATORIA DE DETALLES
			BigDecimal valorTotalDetalles = detallesCaja.stream().map(m -> m.getValor()).reduce(BigDecimal.ZERO,
					BigDecimal::add);
			/*
			 * if(caja.getSaldoActual().compareTo(valorTotalDetalles) != 0) { throw new
			 * RelativeException("La sumatoria de detalles no es igual al saldo actual de la caja"
			 * ); }
			 */
			// INACTIVAR APERTURA DE CAJA ANTERIOR
			corteApertura.setEstado(EstadoMidasEnum.PENDIENTE_HABILITANTE);
			corteApertura.setUsuarioActualizacion(username);
			corteApertura.setFechaCierre(new Date());
			corteApertura.setValorCierre(valorTotalDetalles);
			corteApertura.setJustificacion(justificacion);
			corteApertura.setDiferencia(diferencia);
			this.manageCorteCaja(corteApertura);
			// CREAR REGISTRO CORTE DE CAJA PARA EL CIERRE
			for (TbMiDetalleCaja tbMiDetalleCaja : detallesCaja) {
				tbMiDetalleCaja.setTbMiCaja(caja);
				tbMiDetalleCaja.setEstado(EstadoMidasEnum.ACT);
				tbMiDetalleCaja.setUsuarioCreacion(username);
				this.manageDetalleCaja(tbMiDetalleCaja);
				corteApertura.addTbMiDetalleCaja(tbMiDetalleCaja);
			}
			caja.setSaldoActual(caja.getSaldoActual().add(diferencia)); // encerando la caja con la diferencia entre el cierre y el saldo 
			this.manageCaja(caja);
			this.movimientoCajaOroService.registrarEgreso(ProcesoEnum.CIERRE_CAJA, "CIERRE_CAJA",
					FormaPagoEnum.EFECTIVO, valorTotalDetalles, username, agencia.getId(), null, null, null, null, null,
					null, username, username, username, null,corteApertura.getId());
			
			return corteApertura;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					"No se pudo realizar el corte de caja, " + e.getMessage());
		}
	}


	public List<TbMiCorteCaja> findCorteCajaByIdAndEstado(PaginatedWrapper pw,Long id, EstadoMidasEnum estado,Long idAgencia) throws RelativeException{
		if(pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)){
			return corteCajaRepository.findByIdAndEstado(id,estado,idAgencia,pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		}else {
			return corteCajaRepository.findByIdAndEstado(id,estado,idAgencia);
		}
	}
	
	public Long countCorteCajaByIdAndEstado(Long id , EstadoMidasEnum estado,Long idAgencia) throws RelativeException{
		return corteCajaRepository.countByIdAndEstado(id,estado,idAgencia);
	}

	
	/**
	 * Notificacion
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiNotificacion findNotificacionById(Long id) throws RelativeException {
		try {
			return notificacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que Notificacion la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countNotificacion() throws RelativeException {
		try {
			return notificacionRepository.countAll(TbMiNotificacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Notificacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiNotificacion> findAllNotificacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.notificacionRepository.findAll(TbMiNotificacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.notificacionRepository.findAll(TbMiNotificacion.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.notificacionRepository.findAll(TbMiNotificacion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiNotificacion manageNotificacion(TbMiNotificacion send) throws RelativeException {
		try {
			log.info("==> entra a manage Notificacion");
			TbMiNotificacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findNotificacionById(send.getId());
				return this.updateNotificacion(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Date());
				return notificacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Notificacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiNotificacion updateNotificacion(TbMiNotificacion send, TbMiNotificacion persisted)
			throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setIdReferencia(send.getIdReferencia());
			persisted.setMensaje(send.getMensaje());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTipo(send.getTipo());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			return notificacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Notificacion " + e.getMessage());
		}
	}

	public List<TbMiNotificacion> findNotificacionByIdAgencia(PaginatedWrapper pw, Long idAgencia)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.notificacionRepository.findByIdAgencia(pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections(), idAgencia);
		} else {
			return this.notificacionRepository.findByIdAgencia(idAgencia);
		}

	}

	public Long countNotificacionByIdAgencia(Long idAgencia) throws RelativeException {
		return this.notificacionRepository.countByIdAgencia(idAgencia);
	}

	public void notificacionDeleteEntity(Long id) throws RelativeException {
		notificacionRepository.deleteEntity(id);

	}

	/**
	 * MovEntreCaja
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author Kevin Chamorro - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiMovEntreCaja findMovEntreCajaById(Long id) throws RelativeException {
		try {
			return movEntreCajaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que Notificacion la cantidad de entidades existentes
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countMovEntreCaja() throws RelativeException {
		try {
			return movEntreCajaRepository.countAll(TbMiMovEntreCaja.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "TbMiMovEntreCaja no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Kevin Chamorro - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiMovEntreCaja> findAllMovEntreCaja(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.movEntreCajaRepository.findAll(TbMiMovEntreCaja.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.movEntreCajaRepository.findAll(TbMiMovEntreCaja.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.movEntreCajaRepository.findAll(TbMiMovEntreCaja.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al leer la MovEntreCaja " + e.getMessage());
		}

	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiMovEntreCaja manageMovEntreCaja(TbMiMovEntreCaja send) throws RelativeException {
		try {
			log.info("==> entra a manage TbMiMovEntreCaja");
			TbMiMovEntreCaja persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findMovEntreCajaById(send.getId());
				return this.updateMovEntreCaja(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return movEntreCajaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la MovEntreCaja " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiMovEntreCaja updateMovEntreCaja(TbMiMovEntreCaja send, TbMiMovEntreCaja persisted)
			throws RelativeException {
		try {

			persisted.setTbMiAgenciaO(send.getTbMiAgenciaO());
			persisted.setTbMiAgenciaD(send.getTbMiAgenciaD());
			persisted.setSaldoAgenciaOrigen(send.getSaldoAgenciaOrigen());
			persisted.setSaldoAgenciaDestino(send.getSaldoAgenciaDestino());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			return movEntreCajaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Notificacion " + e.getMessage());
		}
	}

	/**
	 * Lista los Movimientos entre caja por agencia origen, agencia destino, fecha
	 * desde y fecha hasta en basea a fecha creacion
	 * 
	 * @param pw
	 * @param idAgenciaOrigen
	 * @param idAgenciaDestino
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public List<TbMiMovEntreCaja> findMovEntreCajaByParams(PaginatedWrapper pw, Long idAgenciaOrigen,
			 Date fechaDesde, Date fechaHasta) throws RelativeException {
		try {
			return this.movEntreCajaRepository.findByParams(pw, idAgenciaOrigen, fechaDesde,
					fechaHasta);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	/**
	 * Cuenta los Movimientos entre caja por agencia origen, agencia destino, fecha
	 * desde y fecha hasta en basea a fecha creacion
	 * 
	 * @param pw
	 * @param idAgenciaOrigen
	 * @param idAgenciaDestino
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public Long countMovEntreCajaByParams(Long idAgenciaOrigen,  Date fechaDesde, Date fechaHasta)
			throws RelativeException {
		try {
			return this.movEntreCajaRepository.countBySpecification(
					new MovEntreCajaByParamsSpec(idAgenciaOrigen, fechaDesde, fechaHasta));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al contar los registros de movimivimiento entre cajas");
		}
	}

	public void deleteMovEntreCaja(Long id) throws RelativeException {
		try {
			this.movEntreCajaRepository.delete(id);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, e.getMessage());
		}
	}

	/**
	 * FolletoLiquidacion
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiFolletoLiquidacion findFolletoLiquidacionById(Long id) throws RelativeException {
		try {
			return folletoLiquidacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countFolletoLiquidacion() throws RelativeException {
		try {
			return folletoLiquidacionRepository.countAll(TbMiFolletoLiquidacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"FolletoLiquidacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiFolletoLiquidacion> findAllFolletoLiquidacion(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.folletoLiquidacionRepository.findAll(TbMiFolletoLiquidacion.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.folletoLiquidacionRepository.findAll(TbMiFolletoLiquidacion.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.folletoLiquidacionRepository.findAll(TbMiFolletoLiquidacion.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los FolletoLiquidacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiFolletoLiquidacion manageFolletoLiquidacion(TbMiFolletoLiquidacion send) throws RelativeException {
		try {
			log.info("==> entra a manage FolletoLiquidacion");
			TbMiFolletoLiquidacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.folletoLiquidacionRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateFolletoLiquidacion(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return folletoLiquidacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la FolletoLiquidacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send una lista de entidades con la informacion de creacion o
	 *             actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public List<TbMiFolletoLiquidacion> manageListFolletoLiquidacion(List<TbMiFolletoLiquidacion> listSend)
			throws RelativeException {
		try {
			List<TbMiFolletoLiquidacion> listaRetorno = new ArrayList<TbMiFolletoLiquidacion>();
			for (TbMiFolletoLiquidacion send : listSend) {
				TbMiFolletoLiquidacion persisted = null;
				if (send != null && send.getId() != null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					persisted = this.folletoLiquidacionRepository.findById(send.getId());
					listaRetorno.add(this.updateFolletoLiquidacion(send, persisted));
				} else if (send != null && send.getId() == null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					listaRetorno.add(folletoLiquidacionRepository.add(send));
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
				}
			}
			return listaRetorno;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la FolletoLiquidacion " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiFolletoLiquidacion updateFolletoLiquidacion(TbMiFolletoLiquidacion send,
			TbMiFolletoLiquidacion persisted) throws RelativeException {
		try {
			persisted.setCodigoFin(send.getCodigoFin());
			persisted.setCodigoInicio(send.getCodigoInicio());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setFechaVigencia(send.getFechaVigencia());
			persisted.setNombreFolleto(send.getNombreFolleto());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTotal(send.getTotal());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setCodigo(send.getCodigo());
			persisted.setInicio(send.getInicio());
			persisted.setFin(send.getFin());
			return folletoLiquidacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando FolletoLiquidacion " + e.getMessage());
		}
	}

	public TbMiFolletoLiquidacion generarLiquidaciones(String codigo, Long inicio, Long fin, String vigencia,
			String nombre, String usuario) throws RelativeException {
		TbMiFolletoLiquidacion folleto = null;
		try {

			if (this.folletoLiquidacionRepository.validarFolletoLiquidacion(codigo, BigInteger.valueOf(inicio),
					BigInteger.valueOf(fin)) != 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EXISTEN LIQUIDACIONES CON EL MISMO CODIGO");
			}
			// VALIDACION DE EXISTENCIA DE NOMBRE
			/*
			 * List<TbMiFolletoLiquidacion> flNombreIgual =
			 * this.folletoLiquidacionRepository.findByParams(null, null, nombre, null,
			 * null); flNombreIgual = flNombreIgual.stream().filter(f ->
			 * f.getNombreFolleto().equals(nombre)) .collect(Collectors.toList()); if
			 * (!flNombreIgual.isEmpty()) { throw new
			 * RelativeException("Ya existe un folleto con el ese nombre"); }
			 */
			// GUARDADO DE FOLLETO LIQUIDACION
			folleto = new TbMiFolletoLiquidacion();
			folleto.setCodigoInicio(codigo.concat("-".concat(StringUtils.leftPad(inicio.toString(), 9, "0"))));
			folleto.setCodigoFin(codigo.concat("-".concat(StringUtils.leftPad(fin.toString(), 9, "0"))));
			folleto.setEstado(EstadoMidasEnum.ACT);
			folleto.setFechaVigencia(MidasOroUtil.formatSringToDate(vigencia));
			folleto.setInicio(BigInteger.valueOf(inicio));
			folleto.setFin(BigInteger.valueOf(fin));
			folleto.setCodigo(codigo);
			// folleto.setNombreFolleto(nombre);
			folleto.setUsuarioCreacion(usuario);
			folleto.setTotal(new BigDecimal(fin).subtract(new BigDecimal(inicio)));
			this.manageFolletoLiquidacion(folleto);
			// GENERACION DE LIQUIDACIONES DE ACUERDO AL RANGO ESTABLECIDO
			for (Long i = inicio; i <= fin; i++) {
				TbMiLiquidacion q = new TbMiLiquidacion();
				q.setCodigo(codigo.concat("-".concat(StringUtils.leftPad(i.toString(), 9, "0"))));
				// q.setComentario(nombre);
				q.setEstado(EstadoFundaEnum.CREADA);
				q.setTbMiFolletoLiquidacion(folleto);
				q.setUsuarioCreacion(usuario);
				this.manageLiquidacion(q);
			}

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"La creacion del folleto de liquidaciones, " + e.getMessage());
		}
		return folleto;
	}

	/**
	 * Busca las Folleto de liquidaciones por id de agencia
	 * 
	 * @param pw
	 * @param idAgencia
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiFolletoLiquidacion> findFolletoLiquidacionByIdAgencia(PaginatedWrapper pw, Long idAgencia)
			throws RelativeException {

		if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return folletoLiquidacionRepository.findByIdAgencia(idAgencia, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return folletoLiquidacionRepository.findByIdAgencia(idAgencia);
		}

	}

	public Long countFolletoLiquidacionByIdAgencia(Long idAgencia) throws RelativeException {
		try {
			return folletoLiquidacionRepository.countByIdAgencia(idAgencia);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Liquidacion no encontrado " + e.getMessage());
		}
	}

	/**
	 * Retorna los folletos sin asignar a una agencia
	 * 
	 * @return List<TbMiFolletoLiquidacion>
	 * @throws RelativeException
	 */
	public List<TbMiFolletoLiquidacion> FolletoLiquidacionSinAsginar() throws RelativeException {
		return folletoLiquidacionRepository.folletoSinAsignar();
	}

	/**
	 * Busqueda de folletos por agencia, codigoinicio, codigofin y fecha de vigencia
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param codigoInicio
	 * @param codigoFin
	 * @param fechaVigencia
	 * @return
	 * @throws RelativeException
	 * @author Kevin chamorro
	 */
	public List<TbMiFolletoLiquidacion> findFolletoLiquidacionByParams(PaginatedWrapper pw, Long idAgencia,
			String nombre, Date fechaVigenciaDesde, Date fechaVigenciaHasta) throws RelativeException {
		return folletoLiquidacionRepository.findByParams(pw, idAgencia, nombre, fechaVigenciaDesde, fechaVigenciaHasta);
	}

	public Long countFolletoLiquidacionByParams(Long idAgencia, String nombre, Date fechaVigenciaDesde,
			Date fechaVigenciaHasta) throws RelativeException {
		return folletoLiquidacionRepository.countBySpecification(
				new FolletoLiquidacionByParamsSpec(idAgencia, nombre, fechaVigenciaDesde, fechaVigenciaHasta));
	}

	public TbMiFolletoLiquidacion asignarFolletoLiquidacion(TbMiFolletoLiquidacion entidad, Long idAgencia)
			throws RelativeException {
		Integer totalLiquidaciones = this.liquidacionRepository.countLiquidacion(idAgencia);
		// log.info(">>>>>>>>>>>>>>numero de liquidaciones:"+totalLiquidaciones+ "
		// agencia:"+idAgencia+" <<<<<<<<<<<<<<<<");
		if (totalLiquidaciones < Integer
				.parseInt(parametrosSingleton.getParametros().get(MidasOroConstantes.MIN_LIQUIDACION).getValor())) {
			return manageFolletoLiquidacion(entidad);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE ASIGNAR - TOTAL LIQUIDACIONES DISPONIBLES: ".concat(totalLiquidaciones.toString()));
		}
	}

	public void deleteFolletoLiquidacion(TbMiFolletoLiquidacion entidad) throws RelativeException {
		folletoLiquidacionRepository.delete(entidad);

	}

	/**
	 * Meta
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiMeta findMetaById(Long id) throws RelativeException {
		try {
			return metaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countMeta() throws RelativeException {
		try {
			return metaRepository.countAll(TbMiMeta.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Meta no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiMeta> findAllMeta(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.metaRepository.findAll(TbMiMeta.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.metaRepository.findAll(TbMiMeta.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.metaRepository.findAll(TbMiMeta.class, pw.getSortFields(), pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Meta " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiMeta manageMeta(TbMiMeta send) throws RelativeException {
		try {
			log.info("==> entra a manage Meta");
			TbMiMeta persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.metaRepository.findById(send.getId());
				return this.updateMeta(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date());
				return metaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Meta " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send una lista de entidades con la informacion de creacion o
	 *             actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public List<TbMiMeta> manageListMeta(List<TbMiMeta> listSend) throws RelativeException {
		try {
			List<TbMiMeta> listaRetorno = new ArrayList<TbMiMeta>();
			for (TbMiMeta send : listSend) {
				TbMiMeta persisted = null;
				if (send != null && send.getId() != null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					persisted = this.metaRepository.findById(send.getId());
					listaRetorno.add(this.updateMeta(send, persisted));
				} else if (send != null && send.getId() == null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					listaRetorno.add(metaRepository.add(send));
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
				}
			}
			return listaRetorno;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Meta " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiMeta updateMeta(TbMiMeta send, TbMiMeta persisted) throws RelativeException {
		try {
			persisted.setCarteraPorVencer(send.getCarteraPorVencer());
			persisted.setCarteraTotal(send.getCarteraTotal());
			persisted.setCarteraVencida(send.getCarteraVencida());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setFechaCierre(send.getFechaCierre());
			persisted.setMetaComercial(send.getMetaComercial());
			persisted.setMetaGerencial(send.getMetaGerencial());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setFechaInicio(send.getFechaInicio());
			return metaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Meta " + e.getMessage());
		}
	}

	public List<TbMiMeta> findMetaByFechaAndNombre(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta, String nombre)
			throws RelativeException {

		if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.metaRepository.findByCodigoAndNombre(fechaDesde, fechaHasta, nombre, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.metaRepository.findByCodigoAndNombre(fechaDesde, fechaHasta, nombre);

		}
	}

	public Long countMetaByFechaAndNombre(Date fechaDesde, Date fechaHasta, String nombre) throws RelativeException {
		try {
			return metaRepository.countByCodigoAndNombre(fechaDesde, fechaHasta, nombre);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agencia no encontrado " + e.getMessage());
		}
	}

	/**
	 * DetalleMeta
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiDetalleMeta findDetalleMetaById(Long id) throws RelativeException {
		try {
			return detalleMetaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countDetalleMeta() throws RelativeException {
		try {
			return detalleMetaRepository.countAll(TbMiDetalleMeta.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "DetalleMeta no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiDetalleMeta> findAllDetalleMeta(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.detalleMetaRepository.findAll(TbMiDetalleMeta.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.detalleMetaRepository.findAll(TbMiDetalleMeta.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.detalleMetaRepository.findAll(TbMiDetalleMeta.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los DetalleMeta " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiDetalleMeta manageDetalleMeta(TbMiDetalleMeta send) throws RelativeException {
		try {
			log.info("==> entra a manage DetalleMeta");
			TbMiDetalleMeta persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.detalleMetaRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateDetalleMeta(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return detalleMetaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la DetalleMeta " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send una lista de entidades con la informacion de creacion o
	 *             actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public List<TbMiDetalleMeta> manageListDetalleMeta(List<TbMiDetalleMeta> listSend) throws RelativeException {
		try {
			List<TbMiDetalleMeta> listaRetorno = new ArrayList<TbMiDetalleMeta>();
			for (TbMiDetalleMeta send : listSend) {
				TbMiDetalleMeta persisted = null;
				if (send != null && send.getId() != null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					persisted = this.detalleMetaRepository.findById(send.getId());
					listaRetorno.add(this.updateDetalleMeta(send, persisted));
				} else if (send != null && send.getId() == null) {
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					listaRetorno.add(detalleMetaRepository.add(send));
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
				}
			}
			return listaRetorno;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la DetalleMeta " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiDetalleMeta updateDetalleMeta(TbMiDetalleMeta send, TbMiDetalleMeta persisted) throws RelativeException {
		try {
			persisted.setCarteraCancelada(send.getCarteraCancelada());
			persisted.setCarteraNueva(send.getCarteraNueva());
			persisted.setCarteraPerfeccionada(send.getCarteraPerfeccionada());
			persisted.setCarteraPorVencer(send.getCarteraPorVencer());
			persisted.setCarteraRenovada(send.getCarteraRenovada());
			persisted.setCarteraTotal(send.getCarteraTotal());
			persisted.setCarteraVencida(send.getCarteraVencida());
			persisted.setCumple(send.getCumple());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setFechaCierre(send.getFechaCierre());
			persisted.setMetaComercial(send.getMetaComercial());
			persisted.setMetaGerencial(send.getMetaGerencial());
			persisted.setPorcentajeComercial(send.getPorcentajeComercial());
			persisted.setPorcentajeGerencial(send.getPorcentajeGerencial());
			persisted.setTbMiAgencia(send.getTbMiAgencia());
			persisted.setTbMiMeta(send.getTbMiMeta());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setFechaInicio(send.getFechaInicio());
			return detalleMetaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando DetalleMeta " + e.getMessage());
		}
	}

	/**
	 * Contactabilidad
	 */

	/**
	 * Metodo generico para buscar la entidad por PK
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro - Relative Engine
	 */
	public TbMiContactabilidad findContactabilidadById(Long id) throws RelativeException {
		try {
			return contactabilidadRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que Notificacion la cantidad de entidades existentes
	 * 
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro - Relative Engine
	 */
	public Long countContactabilidad() throws RelativeException {
		try {
			return contactabilidadRepository.countAll(TbMiContactabilidad.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Contactabilidad no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @throws RelativeException
	 * @author Kevin Chamorro - Relative Engine
	 */
	public List<TbMiContactabilidad> findAllContactabilidad(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.contactabilidadRepository.findAll(TbMiContactabilidad.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.contactabilidadRepository.findAll(TbMiContactabilidad.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.contactabilidadRepository.findAll(TbMiContactabilidad.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al leer la MovEntreCaja " + e.getMessage());
		}

	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiContactabilidad manageContactabilidad(TbMiContactabilidad send) throws RelativeException {
		try {
			TbMiContactabilidad persisted = null;
			Instant instant = Instant.now();
			if (send != null && send.getId() != null) {
				persisted = this.findContactabilidadById(send.getId());
				send.setFechaActualizacion(new Timestamp(instant.toEpochMilli()));
				return this.updateContactabilidad(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(instant.toEpochMilli()));
				return contactabilidadRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la MovEntreCaja " + e.getMessage());
		}
	}

	public TbMiContactabilidad manageContactabilidadWithContrato(TbMiContactabilidad send) throws RelativeException {
		try {
			if (send == null || send.getTbMiContrato() == null || send.getTbMiContrato().getId() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "El contrato no existe");
			}
			TbMiContrato uContrato = send.getTbMiContrato();
			uContrato.setGestion(send.getComentario());
			this.manageContrato(uContrato);
			return manageContactabilidad(send);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la MovEntreCaja " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author Kevin Chamorro - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiContactabilidad updateContactabilidad(TbMiContactabilidad send, TbMiContactabilidad persisted)
			throws RelativeException {
		try {
			persisted.setComentario(send.getComentario());
			persisted.setEstadoGestion(send.getEstadoGestion());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(send.getFechaActualizacion());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			return contactabilidadRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Notificacion " + e.getMessage());
		}
	}

	public List<TbMiContactabilidad> findContactabilidadByParams(PaginatedWrapper pw, Long idCliente, Long idAgente,
			Long idAgencia, Long idContrato, EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.contactabilidadRepository.findByParams(pw, idCliente, idAgente, idAgencia, idContrato, estado);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	public Long countContactabilidadByParams(Long idCliente, Long idAgente, Long idAgencia, Long idContrato,
			EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.contactabilidadRepository.countBySpecification(
					new ContactabilidadByParams(idCliente, idAgente, idAgencia, idContrato, estado));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al contar los registros de contactabilidad");
		}
	}

	public void deleteContactabilidad(Long id) throws RelativeException {
		try {
			this.contactabilidadRepository.delete(id);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, e.getMessage());
		}
	}

	/**
	 * Compra Oro Tipo de oro ligado al contrato
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Andres Grijalva - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiCompraOro findCompraOroById(Long id) throws RelativeException {
		try {
			return compraOroRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCompraOro() throws RelativeException {
		try {
			return compraOroRepository.countAll(TbMiCompraOro.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Rango de funda no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Andres Grijalva - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiCompraOro> findAllCompraOro(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.compraOroRepository.findAll(TbMiCompraOro.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.compraOroRepository.findAll(TbMiCompraOro.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.compraOroRepository.findAll(TbMiCompraOro.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiCompraOro manageCompraOro(TbMiCompraOro send) throws RelativeException {
		try {
			log.info("==> entra CompraOro");
			TbMiCompraOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findCompraOroById(send.getId());
				return this.updateCompraOro(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return compraOroRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la JoyaSim " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiCompraOro updateCompraOro(TbMiCompraOro send, TbMiCompraOro persisted) throws RelativeException {
		try {
			persisted.setEstado(send.getEstado());
			persisted.setPorcentajeDescuento(send.getPorcentajeDescuento());
			persisted.setPorcentajePureza(send.getPorcentajePureza());
			persisted.setPrecioOroCd(send.getPrecioOroCd());
			persisted.setPrecioOroCp(send.getPrecioOroCp());
			persisted.setQuilate(send.getQuilate());
			persisted.setTbMiFunda(send.getTbMiFunda());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setValor(send.getValor());
			return compraOroRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando Funda Rango " + e.getMessage());
		}
	}

	/**
	 * Lista los tipos de oro por id de funda
	 * 
	 * @param idFunda
	 * @return
	 */
	public List<TbMiCompraOro> listByIdFunda(Long idFunda) throws RelativeException {
		// TODO Auto-generated method stub
		return compraOroRepository.listByIdFunda(idFunda);
	}

	public TbMiCompraOro findCompraOroByQuilateAndFunda(String quilate, Long idFunda) throws RelativeException {
		// TODO Auto-generated method stub
		return compraOroRepository.findByQuilateAndFunda(quilate, idFunda);
	}

	/**
	 * Metodo q valida si un contrato puede ser perfeccionado o no
	 * 
	 * @param idContrato
	 * @return
	 */
	public Boolean validatePerfeccionamiento(Long idContrato) throws RelativeException {

		Date fechaActual = new Date();
		// aqui le sumo los dias de gracia a la fecha actual
		Calendar c = Calendar.getInstance();
		fechaActual = MidasOroUtil.adicionEnDias(fechaActual, -Long
				.valueOf(this.parametrosSingleton.getParametros().get(MidasOroConstantes.DIAS_DE_GRACIA).getValor()));
		c.setTime(fechaActual);
		c.add(Calendar.DATE, Integer
				.parseInt(this.parametrosSingleton.getParametros().get(MidasOroConstantes.DIAS_DE_GRACIA).getValor()));
		fechaActual = c.getTime();
		TbMiContrato contrato = contratoRepository.validatePerfeccionamiento(idContrato, fechaActual);
		if (contrato != null) {
			return true;
		}
		return false;
	}	
	
	
	public Boolean validateReversoPerfeccionamiento(Long idContrato) throws RelativeException {
		
		
		TbMiContrato c = contratoRepository.findById(idContrato);
		
		if(!c.getEstado().equals(EstadoContratoEnum.PERFECCIONADO)) {
			return false;
		}
		List<TbMiJoya> joyas = this.findJoyaByIdFunda(null, c.getTbMiFunda().getId());
		List<Long> idJoyas = new ArrayList<>();
		
		for(TbMiJoya j : joyas) {
			if (j.getEstado().equals(EstadoJoyaEnum.EXISTENCIA_LOTE) || 
					j.getEstado().equals(EstadoJoyaEnum.EXISTENCIA_LOTE)) {
				return false;
			}
		}
		return true;
		
	}	
	
	public Boolean validateAnulacionContrato(Long idContrato) throws RelativeException {
		
		
		TbMiContrato c = contratoRepository.findById(idContrato);
		
		if(!c.getEstado().equals(EstadoContratoEnum.PENDIENTE_HABILITANTE)||!(c.getProceso().equals(ProcesoEnum.COMPRA_PLAZO)||
				c.getProceso().equals(ProcesoEnum.COMPRA_DIRECTA))){
			return false;
		} else {
			return true;
		}
				
	}	
	
	
	
	public String anularCodigo(Long idContrato) throws RelativeException {
		TbMiContrato c = contratoRepository.findById(idContrato);
		String codigoContrato = c.getCodigo()+"-ANULADO";
		
		return codigoContrato;
	}
	/**
	 * AprobarContrato
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiAprobarContrato findAprobarContratoById(Long id) throws RelativeException {
		try {
			return aprobarContratoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public TbMiAprobarContrato findAprobarContratoByIdWithChilds(Long id) throws RelativeException {
		try {
			TbMiAprobarContrato c = aprobarContratoRepository.findById(id);
			// c.getTbMiContratoCalculos().get(0).getValor();
			// c.getTbMiJoyaSims().get(0).getDescripcion();
			return c;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countAprobarContrato() throws RelativeException {
		try {
			return aprobarContratoRepository.countAll(TbMiAprobarContrato.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AprobarContrato no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiAprobarContrato> findAllAprobarContrato(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.aprobarContratoRepository.findAll(TbMiAprobarContrato.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.aprobarContratoRepository.findAll(TbMiAprobarContrato.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.aprobarContratoRepository.findAll(TbMiAprobarContrato.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los AprobarContrato " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiAprobarContrato manageAprobarContrato(TbMiAprobarContrato send) throws RelativeException {
		try {
			log.info("==> entra a manage AprobarContrato");
			TbMiAprobarContrato persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.aprobarContratoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateAprobarContrato(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return aprobarContratoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la AprobarContrato " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiAprobarContrato updateAprobarContrato(TbMiAprobarContrato send, TbMiAprobarContrato persisted)
			throws RelativeException {
		try {
			persisted.setAprobado(send.getAprobado());
			persisted.setDescripcion(send.getDescripcion());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Date());
			persisted.setTbMiCliente(send.getTbMiCliente());
			persisted.setTipoContrato(send.getTipoContrato());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());
			persisted.setValor(send.getValor());
			persisted.setMontoCotizacion(send.getMontoCotizacion());
			persisted.setFechaVencimiento(send.getFechaVencimiento());
			persisted.setNivelAprobacion(send.getNivelAprobacion());
			persisted.setRolAprobacion(send.getRolAprobacion());
			persisted.setRolAprobacionDos(send.getRolAprobacionDos());
			persisted.setObservacionNivelUno(send.getObservacionNivelUno());
			persisted.setObservacionNivelDos(send.getObservacionNivelDos());
			persisted.setCanalContacto(send.getCanalContacto());
			return aprobarContratoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando AprobarContrato " + e.getMessage());
		}
	}

	public List<TbMiAprobarContrato> findAprobarContratoByParams(PaginatedWrapper pw, String identificacion,
			EstadoAprobacionEnum estado, String nivelAprobacion, String rolAprobacion, String rolAprobacionDos,
			String usuarioCreacion) throws RelativeException {
		try {
			log.info("===>entra findAprobarContratoByParams");
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.aprobarContratoRepository.findAprobarContratoByParams(pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections(), identificacion, estado, nivelAprobacion,
						rolAprobacion, rolAprobacionDos, usuarioCreacion);
			}
			return this.aprobarContratoRepository.findAprobarContratoByParams(identificacion, estado, nivelAprobacion,
					rolAprobacion, rolAprobacionDos, usuarioCreacion);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR Buscar Aprobacion de contrato por parametros " + e.getMessage());
		}
	}

	public Long countAprobarContratoByParams(String identificacion, EstadoAprobacionEnum estado, String nivelAprobacion,
			String rolAprobacion, String rolAprobacionDos, String usuarioCreacion) throws RelativeException {

		return this.aprobarContratoRepository.countAprobarContratoByParams(identificacion, estado, nivelAprobacion,
				rolAprobacion, rolAprobacionDos, usuarioCreacion);
	}

	/**
	 * Valida si un cliente necesita o no aprobacion para generar un contrato
	 * 
	 * @param identificacion
	 * @return sumatorio de los valores de los contrato vigentes
	 * @throws RelativeException
	 */
	public BigDecimal validarAprobacion(String identificacion) throws RelativeException {
		List<EstadoContratoEnum> estados = new ArrayList<>();
		estados.add(EstadoContratoEnum.VIGENTE);
		estados.add(EstadoContratoEnum.VIGENTE_ADENDUM);
		return this.aprobarContratoRepository.validarValorContratos(identificacion, estados);
	}

	public TbMiAprobarContrato guardarAprobacion(AprobacionWrapper wp) throws RelativeException {
		if (wp != null && wp.getCliente() != null && wp.getAprobacion() != null && wp.getJoyas() != null
				&& !wp.getJoyas().isEmpty()) {
			try {
				TbMiAprobarContrato aprobacion = null;
				aprobacion = this.manageAprobarContrato(wp.getAprobacion());
				addAprobacionJoyaSimBatch(aprobacion, wp.getJoyas());
				if (wp.getAprobacion().getTipoContrato()
						.equalsIgnoreCase(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX)) {
					if (wp.getCalculos() == null || wp.getCalculos().isEmpty()) {
						throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
								"NO SE PUEDE GUARDAR LA APROBACION POR QUE NO SE ENCUENTRAN LOS CALCULOS PARA LA COMPRA PLAZO");
					}
					if (wp.getCalculos() != null) {
						addAprobacionCalculoBatch(aprobacion, wp.getCalculos());
					}
				}
				return aprobacion;
			} catch (RelativeException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL GUARDAR LA APROBACION");
			}
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE GUARDAR LA APROBACION POR QUE LA INFORMACION ESTA INCOMPLETA");
		}

	}

	private void addAprobacionCalculoBatch(TbMiAprobarContrato aprobacion, List<TbMiContratoCalculo> contratoCalculos)
			throws RelativeException {
		log.info("======>addContratoCalculoBatch ");
		log.info("======>addContratoCalculoBatch cantidad de calculos: " + contratoCalculos.size());
		contratoCalculos.forEach(cc -> {
			try {
				log.info("======>iterando " + cc.getRubro());
				log.info("======>iterando " + cc.getValor());
				log.info("======>iterando " + cc.getUsuario());
				log.info("======>iterando " + cc.getTbMiContrato());
				cc.setTbMiAprobarContrato(aprobacion);
				this.manageContratoCalculo(cc);
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
	}

	private void addAprobacionJoyaSimBatch(TbMiAprobarContrato aprobacion, List<TbMiJoyaSim> joyas)
			throws RelativeException {

		joyas.forEach(cc -> {
			try {
				cc.setId(null);
				cc.setTbMiCotizacion(null);
				cc.setTbMiAprobarContrato(aprobacion);
				this.manageJoyaSim(cc);
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
	}

	public List<TbMiJoyaSim> findJoyaSimByIdAprobarContrato(Long idAprobarContrato) throws RelativeException {

		return joyaSimRepository.findByIdAprobarContrato(idAprobarContrato);
	}

	/**
	 * Metodo utilizado para aprobar las solicitudes de contrato
	 * 
	 * @param wp
	 * @return
	 * @throws RelativeException
	 */
	public TbMiAprobarContrato aprobarPorNivel(TbMiAprobarContrato ac) throws RelativeException {

		if (ac != null) {
			try {
				// ac.setEstado( EstadoAprobacionEnum.APROBADO );
				// ac.setAprobado(BigDecimal.ONE);
				// if( EstadoAprobacionEnum.NIVEL_TRES.toString().equalsIgnoreCase(
				// ac.getNivelAprobacion() ) ) {
				// ac.setEstado( EstadoAprobacionEnum.PENDIENTE_APROBACION );
				// ac.setAprobado(null);
				// ac.setRolAprobacionDos(null);
				// }
				return this.manageAprobarContrato(ac);
			} catch (RelativeException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR AL APROBAR LA SOLICITUD DE CONTRATO " + ac.getId());
			}
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE APROBAR, LA INFORMACION ESTA INCOMPLETA");
		}

	}

	public VentaJoyaWrapper venderJoyaVitrina(VentaJoyaWrapper ventaJoya, String usuario, Long idAgencia)
			throws RelativeException {
		if (ventaJoya.getJoya() != null && ventaJoya.getJoya().getId() != null && ventaJoya.getComprador() != null
				&& ventaJoya.getIngresos() != null && !ventaJoya.getIngresos().isEmpty()) {
			TbMiJoya joya = findJoyaById(ventaJoya.getJoya().getId());
			joya.setPrecioVenta(ventaJoya.getJoya().getPrecioVenta());
			joya.setIva(ventaJoya.getJoya().getIva());
			joya.setEstado(EstadoJoyaEnum.PENDIENTE_HABILITANTE);
			joya.setComprador(ventaJoya.getComprador());
			joya.setProceso(ProcesoEnum.VENTA_VITRINA);
			joya.setUsuario( ventaJoya.getJoya().getUsuario() );
			joya = this.manageJoya(joya);
			TbMiBodega bodega = this.findBodegaByAgenciaAndNombre(idAgencia, TipoBodegaEnum.CLIENTE.toString());
			for (TbMiMovimientoCaja ingreso : ventaJoya.getIngresos()) {
				movimientoCajaOroService.registrarIngreso(ProcesoEnum.VENTA_VITRINA, "VENTA_VITRINA",
						ingreso.getFormaPago(), ingreso.getIngreso(), usuario, idAgencia,
						ingreso.getTbMiBanco() != null ? ingreso.getTbMiBanco().getId() : null,
						ingreso.getNumeroCuentaBanco(), ingreso.getTipoCuentaBanco(), ventaJoya.getComprador().getId(),
						null, ingreso.getTipoTarjeta(), ingreso.getNumeroTarjeta(), ingreso.getHabienteTarjeta(),
						ingreso.getCedHabienteTarjeta(), ingreso.getComentario(), null,null);
			}
			movimientoOroService.movimientoInventario(joya.getId(), bodega.getId(), usuario,
					EstadoJoyaEnum.VENDIDA_VITRINA, ProcesoEnum.VENTA_VITRINA);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"NO SE PUEDE GENERAR LA VENTA POR QUE LA INFORMACION ENVIADA ESTA INCOMPLETA");
		}
		return ventaJoya;
	}

	public List<TbMiVentaLote> findVentaLotePendienteByEstadoAndCodigo(PaginatedWrapper pw,
			EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote, String identificacion)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return ventaLoteRepository.findByEstadoAndCodigo(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), pendienteHabilitante, codigoVentaLote, identificacion);
		} else {
			return ventaLoteRepository.findByEstadoAndCodigo(pendienteHabilitante, codigoVentaLote, identificacion);

		}

	}

	public Long countVentaLoteByEstadoAndCodigo(EstadoJoyaEnum pendienteHabilitante, String codigoVentaLote,
			String identificacion) throws RelativeException {
		return ventaLoteRepository.countByEstadoAndCodigo(pendienteHabilitante, codigoVentaLote, identificacion);

	}



}
