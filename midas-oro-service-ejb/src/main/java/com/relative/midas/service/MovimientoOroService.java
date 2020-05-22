package com.relative.midas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiContratoCalculo;
import com.relative.midas.model.TbMiDocumentoHabilitante;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiMovInventario;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.repository.BodegaRepository;
import com.relative.midas.repository.ContratoCalculoRepository;
import com.relative.midas.repository.DocumentoHabilitanteRepository;
import com.relative.midas.repository.InventarioRepository;
import com.relative.midas.repository.JoyaRepository;
import com.relative.midas.repository.MovInventarioRepository;
import com.relative.midas.repository.spec.ContratoCalculoByContratoAndRubroSpec;
import com.relative.midas.util.MidasOroConstantes;
import com.relative.midas.util.MidasOroUtil;

@Stateless
public class MovimientoOroService {
	@Inject
	Logger log;

	@Inject
	InventarioRepository inventarioRepository;

	@Inject
	MovInventarioRepository movInventarioRepository;

	@Inject
	BodegaRepository bodegaRepository;

	@Inject
	JoyaRepository joyaRepository;

	@Inject
	MidasOroService mos;

	@Inject
	CompraOroService cos;
	@Inject
	MovimientoCajaOroService movCaja;
	@Inject
	DocumentoHabilitanteRepository documentoHabilitanteRepository;
	
	@Inject
	ContratoCalculoRepository calculo;

	/**
	 * Metodo que permite modificar la ubicacion de una joya
	 * 
	 * @param idJoya
	 * @param idUbicacion
	 * @param usuario
	 * @return TbMiInventario
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public TbMiInventario movimientoInventario(Long idJoya, Long idUbicacion, String usuario, EstadoJoyaEnum estado, ProcesoEnum proceso)
			throws RelativeException {
		TbMiInventario inv = null;
		TbMiBodega ubicacion = null;
		TbMiMovInventario movimiento;
		inv = inventarioRepository.findByJoya(idJoya);
		if (inv == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO LA JOYA EN EL INVENTARIO");
		}
		ubicacion = bodegaRepository.findById(idUbicacion);
		if (ubicacion == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO LA UBICACION");
		}
		inv.setTbMiBodega(ubicacion);
		inv.setUsuarioActualizacion(usuario);
		inv.setEstado(estado);
		inv.setProceso(proceso);
		inv = this.mos.manageInventario(inv);
		movimiento = new TbMiMovInventario();
		movimiento.setTbMiBodega(ubicacion);
		movimiento.setTbMiInventario(inv);
		movimiento.setUsuarioCreacion(usuario);
		movimiento.setUsuarioActualizacion(usuario);
		movimiento.setEstado(estado);
		movimiento.setProceso(proceso);
		this.mos.manageMovInventario(movimiento);
		return inv;

	}

	/**
	 * Metodo que permite moificar la ubicacion de una lista de joyas
	 * 
	 * @param idJoya
	 * @param idUbicacion
	 * @param usuario
	 * @return List<TbMiInventario>
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public List<TbMiInventario> movimientoInventario(List<Long> idJoya, Long idUbicacion, String usuario,
			EstadoJoyaEnum estado, ProcesoEnum proceso) throws RelativeException {
		String errorList = StringUtils.EMPTY;
		TbMiInventario inv = null;
		TbMiBodega ubicacion = null;
		TbMiMovInventario movimiento;
		List<TbMiInventario> inventarios = new ArrayList<>();
		for (Long joya : idJoya) {
			try {
				inv = inventarioRepository.findByJoya(joya);
			} catch (RelativeException e) {
				inv = null;
			}
			try {
				ubicacion = bodegaRepository.findById(idUbicacion);
			} catch (RelativeException e) {
				ubicacion = null;
			}
			if (inv != null && ubicacion != null) {
				inv.setTbMiBodega(ubicacion);
				inv.setUsuarioActualizacion(usuario);
				inv.setEstado(estado);
				inv.setProceso(proceso);
				movimiento = new TbMiMovInventario();
				movimiento.setTbMiBodega(ubicacion);
				movimiento.setTbMiInventario(inv);
				movimiento.setUsuarioCreacion(usuario);
				movimiento.setUsuarioActualizacion(usuario);
				movimiento.setEstado(estado);
				movimiento.setProceso(proceso);
				inv = this.mos.manageInventario(inv);
				this.mos.manageMovInventario(movimiento);
				inventarios.add(inv);
			} else {
				errorList = errorList.concat(" id joya: ").concat(idJoya.toString());
			}
		}
		if (errorList != StringUtils.EMPTY) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "error en joyas: ".concat(errorList));
		}
		return inventarios;

	}


	/**
	 * Metodo q actualiza el estado del contrato y las joyas despues de cancelar
	 * contrato estado de la joya DEVUELTA inventario CLIENTE
	 * 
	 * @param contrato
	 * @return
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public TbMiContrato entregarJoyaCliente(TbMiContrato contrato) throws RelativeException {
		return null;

	}

	
	private String getCodigoContratoRenovacion(TbMiContrato contrato) {
		String codigoContrato = null;	
		if(contrato.getCodigo() != null && !contrato.getCodigo().isEmpty()) {
			String[] cod = contrato.getCodigo().split("-");
			if(cod.length == 5) {					
				Long numeroRenovacion = Long.valueOf(cod[4]);
				log.info("======numeroRenovacion:"+numeroRenovacion);
				numeroRenovacion = numeroRenovacion + 1;
				codigoContrato = cod[0].concat("-").concat(cod[1]).concat("-").concat(cod[2]).concat("-").concat(cod[3])
						.concat("-").concat( StringUtils.leftPad(numeroRenovacion.toString(), 2, "0"));
				log.info("======codigoContrato:"+codigoContrato);
			}
		}
		return codigoContrato;
	}
	
	/**
	 * Metodo q actualiza en estado del contrato en renovacion
	 * 
	 * @param TbMiContrato
	 * @return TbMiContrato
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public TbMiContrato renovarContrato(Long idContrato, String usuario,Long plazo) throws RelativeException {
		String codigoContrato = null;	
		TbMiContrato contrato = this.mos.findContratoById(idContrato);
		TbMiContrato contratoNuevo = new TbMiContrato();
		
		if(contrato !=null ) {
			// contrato.setTbMiFunda(null);
			codigoContrato=getCodigoContratoRenovacion(contrato) ;
			//calculo el plazo en funcion del contrato anterior
			//plazo = MidasOroUtil.diasFecha(contrato.getFechaCreacion(), contrato.getFechaVencimiento());
			contratoNuevo.setFechaVencimiento( MidasOroUtil.adicionEnDias(new Date(System.currentTimeMillis()), plazo) );//asigno el nuevo vencimiento para el numero contrato
			log.info("esta es la fecha actual en New Date():===>" + new Date());
			log.info("esta es la fecha actual en New Date():===>" + new Date(System.currentTimeMillis()));
			log.info("esta es la fecha actual en New Date(System.currentTimeMillis()):===>" + contratoNuevo.getFechaVencimiento());
			contrato.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			contrato.setProceso(ProcesoEnum.RENOVAR_CONTRATO);
			contrato.setFechaRenovacion(new Date());
			contratoNuevo.setId(null);
			contratoNuevo.setTbMiFunda(contrato.getTbMiFunda());
			contratoNuevo.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			contratoNuevo.setProceso(ProcesoEnum.COMPRA_PLAZO_ADENDUM);
			contratoNuevo.setCodigo(codigoContrato);
			contratoNuevo.setTbMiContratoAnterior(contrato);
			contratoNuevo.setValorContrato(contrato.getValorContrato());
			contratoNuevo.setTbMiCliente(contrato.getTbMiCliente());
			contratoNuevo.setTbMiAgencia(contrato.getTbMiAgencia());
			contratoNuevo.setTbMiAgente(contrato.getTbMiAgente());
			contratoNuevo.setTipoCompra(MidasOroConstantes.CODIGO_COMPRA_PLAZO_PREFIX);
			contratoNuevo.setUsuarioCreacion(usuario);
			contratoNuevo.setTbMiFunda(contrato.getTbMiFunda());
			contratoNuevo.setPorcentajeCustodia(contrato.getPorcentajeCustodia());
			this.mos.manageContrato(contrato);
			return this.mos.manageContrato(contratoNuevo);
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRAO EL CONTRATO CON ID:"+idContrato);
		}
		

	}

	/**
	 * Metodo q actualiza el estado(PENDIENTE_HABILITANTE) del contrato cuando se va
	 * a perfeccionar.
	 * 
	 * @param idContrato
	 * @return TbMiContrato
	 * @throws RelativeException
	 * @author Jhon Romero
	 */
	public TbMiContrato perfeccionarContrato(Long idContrato, String usuario) throws RelativeException {
		try {
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			contrato.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			contrato.setFechaPerfeccionamiento(new Date());
			contrato.setProceso(ProcesoEnum.PERFECCIONAMIENTO);
			contrato.setUsuarioActualizacion(usuario);
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			List<Long> idJoyas = new ArrayList<>();
			Long idUbicacion = this.mos.findBodegaByAgenciaAndNombre(contrato.getTbMiAgencia().getId(), MidasOroConstantes.CAJA_FUERTE_CUSTODIA).getId();
			for(TbMiJoya j : joyas) {
				idJoyas.add(j.getId());
			}
			this.movimientoInventario(idJoyas, idUbicacion, usuario, EstadoJoyaEnum.PERFECCIONADO, ProcesoEnum.PERFECCIONAMIENTO);
			createHistoricoEstadoProceso(joyas, usuario, EstadoJoyaEnum.PERFECCIONADO, ProcesoEnum.PERFECCIONAMIENTO);
			return this.mos.manageContrato(contrato);
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL PERFECCIONAR");
		}
	}
	public TbMiContrato reversarPerfeccionamiento(Long idContrato, String usuario) throws RelativeException {

		TbMiContrato c = this.mos.findContratoById(idContrato);
	//	TbMiContrato dh = documentoHabilitanteRepository.remove(arg0);

		try {
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			String[] parts = contrato.getCodigo().split("-");
			String codFinal = parts[4];
			if(Integer.parseInt(codFinal)<1){
				contrato.setEstado(EstadoContratoEnum.VIGENTE);
				
			}else {
				contrato.setEstado(EstadoContratoEnum.VIGENTE_ADENDUM); 
			
				
			}
			contrato.setProceso(ProcesoEnum.REVERSO_PERFECCIONAMIENTO);
			contrato.setUsuarioActualizacion(usuario);
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			List<Long> idJoyas = new ArrayList<>();
			Long idUbicacion = this.mos.findBodegaByAgenciaAndNombre(contrato.getTbMiAgencia().getId(), MidasOroConstantes.CAJA_FUERTE_CUSTODIA).getId();
			for(TbMiJoya j : joyas) {
				idJoyas.add(j.getId());
			}
			this.movimientoInventario(idJoyas, idUbicacion, usuario, EstadoJoyaEnum.CUSTODIA, ProcesoEnum.REVERSO_PERFECCIONAMIENTO);
			createHistoricoEstadoProceso(joyas, usuario, EstadoJoyaEnum.CUSTODIA, ProcesoEnum.REVERSO_PERFECCIONAMIENTO);
			eliminarHabilitante( c.getCodigo() , Long.valueOf("13"));
			eliminarHabilitante( c.getCodigo() , Long.valueOf("19"));
			return this.mos.manageContrato(contrato);
			
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL PERFECCIONAR");
		}
	}
	
	public TbMiContrato anularContrato(Long idContrato, String usuario) throws RelativeException {

		TbMiContrato c = this.mos.findContratoById(idContrato);


		try {
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			String[] parts = contrato.getCodigo().split("-");
			String codFinal = parts[4];
			
			contrato.setEstado(EstadoContratoEnum.ANULADO); 
			contrato.setProceso(ProcesoEnum.ANULACIÓN_CONTRATO);
			contrato.setUsuarioActualizacion(usuario);
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			List<Long> idJoyas = new ArrayList<>();
			Long idUbicacion = this.mos.findBodegaByAgenciaAndNombre(contrato.getTbMiAgencia().getId(), MidasOroConstantes.CAJA_FUERTE_CUSTODIA).getId();
			for(TbMiJoya j : joyas) {
				idJoyas.add(j.getId());
			}
			this.movimientoInventario(idJoyas, idUbicacion, usuario, EstadoJoyaEnum.ANULADA, ProcesoEnum.ANULACIÓN_CONTRATO);
			createHistoricoEstadoProceso(joyas, usuario, EstadoJoyaEnum.ANULADA, ProcesoEnum.ANULACIÓN_CONTRATO);
			eliminarHabilitante( c.getCodigo() , Long.valueOf("13"));
			eliminarHabilitante( c.getCodigo() , Long.valueOf("19"));
			return this.mos.manageContrato(contrato);
			
			/*
			this.registrarIngreso(ProcesoEnum.CANCELAR_CONTRATO, "COMPRA_PLAZO", caja.getFormaPago(),
					caja.getIngreso() != null ? caja.getIngreso() : BigDecimal.ZERO, usuario,
					idAgencia, caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null,
					caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(), contrato.getTbMiCliente().getId(),
					idContrato, caja.getTipoTarjeta(), caja.getNumeroTarjeta(),
					caja.getHabienteTarjeta(), caja.getCedHabienteTarjeta(),caja.getComentario(),null,null);
			
			*/
			
			
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL PERFECCIONAR");
		}
	}
	
	public  void eliminarHabilitante(String codigoContrato ,Long idTipoDocumento) throws RelativeException {
		
		documentoHabilitanteRepository.remove(documentoHabilitanteRepository.findByTipoDocumentoAndContrato(codigoContrato,  idTipoDocumento));
	}
	 
	
	
	
	public void createHistoricoEstadoProceso( List<TbMiJoya> joyas, String usuario,EstadoJoyaEnum estado, ProcesoEnum proceso ) {
		joyas.forEach( j->{
			try {
				TbMiJoya x = this.joyaRepository.findById(j.getId());
				x.setEstado( estado);
				x.setUsuario( usuario );
				x.setProceso( proceso );
				this.mos.createHistoricoJoya( x , "ACTUALIZACION PROCESO  " + proceso.toString());
			} catch (RelativeException e) {
				e.printStackTrace();
			}
			
		} );
	}

	public TbMiContrato cancelarContrato(String usuario, Long idContrato, List<TbMiMovimientoCaja> detalleIngresos,
			BigDecimal valorAbono,BigDecimal valorCancelacion, Long idAgencia) throws RelativeException {
		try {
			List<Long> idJoyas = new ArrayList<>();
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			Long idUbicacion = this.mos.findBodegaByAgenciaAndNombre(idAgencia, MidasOroConstantes.CAJA_FUERTE_CUSTODIA).getId();
			if (valorCancelacion == null || valorCancelacion.compareTo(BigDecimal.ZERO) == 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL COSTO DE CANCELACION NO PUEDE SER CERO");
			}
			if (contrato == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO EN CONTRATO");
			}
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			if (valorAbono == null || valorAbono.compareTo(BigDecimal.ZERO) == 0) {
				cancelacionContrato(usuario, idContrato, idAgencia,detalleIngresos, contrato);
			} else if (valorAbono != null && valorAbono.compareTo(BigDecimal.ZERO) > 0) {
				// se realisa un cruce de cuentas entre los abonos y el contrato
				if (valorCancelacion.compareTo(valorAbono) == 0) {
					this.movCaja.registrarEgreso(ProcesoEnum.CANCELAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, contrato.getTbMiAgencia().getId(),
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);
				} else {
					cancelacionContrato(usuario, idContrato,idAgencia, detalleIngresos, contrato);
					this.movCaja.registrarEgreso(ProcesoEnum.CANCELAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, contrato.getTbMiAgencia().getId(),
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);

				}
			}
			for(TbMiJoya j : joyas) {
				j.setProceso(ProcesoEnum.CANCELAR_CONTRATO);
				j.setUsuario( usuario );
				idJoyas.add(j.getId());
				this.mos.manageJoya(j);
			}
			this.movimientoInventario(idJoyas, idUbicacion, usuario, EstadoJoyaEnum.CANCELADA, ProcesoEnum.CANCELAR_CONTRATO);
			contrato.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			contrato.setProceso(ProcesoEnum.CANCELAR_CONTRATO);
			return this.mos.manageContrato(contrato);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception ex) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN EL PROCESO DE CANCELAR CONTRATO");
		}

	}
	
	public TbMiContrato cancelarContratoValores(String usuario, Long idContrato,List<TbMiContratoCalculo> detalleCalculos, List<TbMiMovimientoCaja> detalleIngresos,
			BigDecimal valorAbono,BigDecimal valorCancelacion, Long idAgencia) throws RelativeException {
		try {
			
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			if (contrato == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO EN CONTRATO");
			}
			Long idUbicacion = this.mos.findBodegaByAgenciaAndNombre(contrato.getTbMiAgencia().getId(), MidasOroConstantes.CAJA_FUERTE_CUSTODIA).getId();
			if (valorCancelacion == null || valorCancelacion.compareTo(BigDecimal.ZERO) == 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL COSTO DE CANCELACION NO PUEDE SER CERO");
			}
			
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			if (valorAbono == null || valorAbono.compareTo(BigDecimal.ZERO) == 0) {
				cancelacionContrato(usuario, idContrato,idAgencia, detalleIngresos, contrato);
			} else if (valorAbono != null && valorAbono.compareTo(BigDecimal.ZERO) > 0) {
				// se realisa un cruce de cuentas entre los abonos y el contrato
				if (valorCancelacion.compareTo(valorAbono) == 0) {
					this.movCaja.registrarEgreso(ProcesoEnum.CANCELAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, idAgencia,
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);
				} else {
					cancelacionContrato(usuario, idContrato,idAgencia,detalleIngresos, contrato);
					this.movCaja.registrarEgreso(ProcesoEnum.CANCELAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, idAgencia,
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);

				}
			}
			
			this.movimientoInventario(this.modifyJoyaOnContratoDetalle(joyas, usuario,ProcesoEnum.CANCELAR_CONTRATO), idUbicacion, usuario, 
					EstadoJoyaEnum.CANCELADA, ProcesoEnum.CANCELAR_CONTRATO);
			contrato.setEstado(EstadoContratoEnum.PENDIENTE_HABILITANTE);
			contrato.setProceso(ProcesoEnum.CANCELAR_CONTRATO);
			contrato.setFechaCancelacion(new Date());
			this.modifyContratoCalculoOnContratoDetalle(contrato, detalleCalculos, usuario);
			return this.mos.manageContrato(contrato);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception ex) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN EL PROCESO DE CANCELAR CONTRATO");
		}

	}
	/**
	 * Metodo que modifica la informacion de la joya y retorna solo los ids
	 * @param joyas
	 * @param usuario
	 * @return
	 * @throws RelativeException
	 */
	private List<Long> modifyJoyaOnContratoDetalle( List<TbMiJoya> joyas, String usuario, ProcesoEnum proceso ) throws RelativeException{
		List<Long> idJoyas = new ArrayList<>();
		for(TbMiJoya j : joyas) {
			j.setProceso(proceso);
			j.setUsuario( usuario );
			idJoyas.add(j.getId());
			this.mos.manageJoya(j);
		}
		this.createHistoricoEstadoProceso(joyas, usuario, EstadoJoyaEnum.CANCELADA, ProcesoEnum.CANCELAR_CONTRATO);
		return idJoyas;
	}
	
	/**
	 * Metodo que actualiza la informacion de los calculos de contrato
	 * @param contrato
	 * @param detalleCalculos
	 * @throws RelativeException
	 */
	private void modifyContratoCalculoOnContratoDetalle(TbMiContrato contrato, List<TbMiContratoCalculo> detalleCalculos,String usuario) throws RelativeException {
		log.info("xxxxxxxxxxxxxxxxxxxxxxx> modifyContratoCalculoOnContratoDetalle" + contrato.getId() );
		List<TbMiContratoCalculo> local=  this.calculo.findAllBySpecification(new ContratoCalculoByContratoAndRubroSpec( contrato.getId(),null ));
		log.info("xxxxxxxxxxxxxxxxxxxxxxx> modifyContratoCalculoOnContratoDetalle local " + local );
		log.info("xxxxxxxxxxxxxxxxxxxxxxx> modifyContratoCalculoOnContratoDetalle local " + local.size() );
		if( detalleCalculos != null && !detalleCalculos.isEmpty() && local != null && !local.isEmpty() ) {
			List<TbMiContratoCalculo> sacar= new ArrayList<TbMiContratoCalculo>();//listo los elementos q se guardaron 
			detalleCalculos.forEach( dc->{
				log.info("xxxxxxxxxxxxxxxxxxxxxxx> modifyContratoCalculoOnContratoDetalle ITERANDO DC " + dc.getRubro() + " - " + dc.getValor() );
				local.forEach( cc->{
					log.info("xxxxxxxxxxxxxxxxxxxxxxx> modifyContratoCalculoOnContratoDetalle ITERANDO CC " + cc.getRubro() + " - " + cc.getValor() );
					if( dc.getRubro().equalsIgnoreCase( cc.getRubro() ) ) {
						try {
							log.info("xxxxxxxxxxxxxxxxxxxxxxx> ACTUALIZANDO VALORES EN CONTRATO PARA RUBOR " + cc.getRubro() );
							log.info("xxxxxxxxxxxxxxxxxxxxxxx> ACTUALIZANDO VALORES EN CONTRATO PARA valor anterior " + cc.getValor() );
							log.info("xxxxxxxxxxxxxxxxxxxxxxx> ACTUALIZANDO VALORES EN CONTRATO PARA valor nuevo " + dc.getValor() );
							cc.setFechaActualizacion( new Date( System.currentTimeMillis() ) );
							cc.setValor( dc.getValor() );
							cc.setUsuario(usuario);
							sacar.add( this.calculo.update( cc ) );
						} catch (RelativeException e) {
							e.printStackTrace();
						}
					}
				} );
				
			} );
			//SI LOS RUBROS NO SE ACTUALIZARON SE CREAN 
			sacar.forEach( s->{
				detalleCalculos.removeIf(p->p.getRubro().equalsIgnoreCase(s.getRubro())); //SACO LOS RUBROS ACTUALIZADOS				
			});
			log.info("xxxxxxxxxxxxxxxxxxxxxxx> rubros para crear " + detalleCalculos.size() );
			if(detalleCalculos != null && !detalleCalculos.isEmpty()) {
				detalleCalculos.forEach( dc->{//CREO LOS RUBROS QUE NO FUERON ACTUALIZADOS
					try {
						dc.setTbMiContrato(contrato);
						log.info("xxxxxxxxxxxxxxxxxxxxxxx> CREANDO VALORES EN CONTRATO PARA RUBOR " + dc.getRubro() );
						log.info("xxxxxxxxxxxxxxxxxxxxxxx> CREANDO VALORES EN CONTRATO PARA valor anterior " + dc.getValor() );
						this.mos.manageContratoCalculo(dc);
					} catch (RelativeException e) {
						e.printStackTrace();
						this.log.info("ERROR AL CREAR DETALLE CALCULO ");
					}
				});
			}
		}else {//si el contrato es migrado no tiene los costos... entonces se crean
			this.mos.addContratoCalculoBatch(contrato,detalleCalculos);
		}
	}
	

	/**
	 * Registra el movimiento de caja de la cancelacion
	 * 
	 * @param usuario
	 * @param idContrato
	 * @param valorCancelacion
	 * @param contrato
	 * @throws RelativeException
	 */
	private void cancelacionContrato(String usuario, Long idContrato,Long idAgencia, List<TbMiMovimientoCaja> valorCancelacion,
			TbMiContrato contrato) throws RelativeException {
		if (valorCancelacion == null || valorCancelacion.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL COSTO DE CANCELACION NO PUEDE SER CERO");
		}
		for (TbMiMovimientoCaja caja : valorCancelacion) {
			this.movCaja.registrarIngreso(ProcesoEnum.CANCELAR_CONTRATO, "COMPRA_PLAZO", caja.getFormaPago(),
					caja.getIngreso() != null ? caja.getIngreso() : BigDecimal.ZERO, usuario,
					idAgencia, caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null,
					caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(), contrato.getTbMiCliente().getId(),
					idContrato, caja.getTipoTarjeta(), caja.getNumeroTarjeta(),
					caja.getHabienteTarjeta(), caja.getCedHabienteTarjeta(),caja.getComentario(),null,null);
		}
	}
	
	/**
	 * Registra el movimiento de caja de la renovacion
	 * 
	 * @param usuario
	 * @param idContrato
	 * @param valorCancelacion
	 * @param contrato
	 * @throws RelativeException
	 */
	private void renovarContrato(String usuario, Long idContrato, List<TbMiMovimientoCaja> valorRenovacion,
			TbMiContrato contrato) throws RelativeException {
		if (valorRenovacion == null || valorRenovacion.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL COSTO DE CANCELACION NO PUEDE SER CERO");
		}
		for (TbMiMovimientoCaja caja : valorRenovacion) {
			this.movCaja.registrarIngreso(ProcesoEnum.RENOVAR_CONTRATO, "COMPRA_PLAZO", caja.getFormaPago(),
					caja.getIngreso() != null ? caja.getIngreso() : BigDecimal.ZERO, usuario,
					contrato.getTbMiAgencia().getId(), caja.getTbMiBanco() != null ? caja.getTbMiBanco().getId() : null,
					caja.getNumeroCuentaBanco(), caja.getTipoCuentaBanco(), contrato.getTbMiCliente().getId(),
					idContrato, caja.getTipoTarjeta(), caja.getNumeroTarjeta(),
					caja.getHabienteTarjeta(), caja.getCedHabienteTarjeta(),caja.getComentario(),null,null);
		}
	}

	/**
	 * Metodo que consume todos los abonos de un cliente si hay un saldo genera un
	 * abono con ese valor
	 * 
	 * @param usuario
	 * @param cliente
	 * @param contrato
	 * @param valor
	 * @return TbMiAbono saldo
	 * @throws RelativeException
	 */
	public TbMiAbono consumirAbonos(String usuario, TbMiCliente cliente, TbMiContrato contrato, BigDecimal valor)
			throws RelativeException {
		try {
			BigDecimal totalAbonos = BigDecimal.ZERO;
			List<TbMiAbono> abonos = this.mos.findAbonosByIdCliente(cliente.getId());
			if (abonos == null || abonos.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO HAY ABONOS DISPONIBLES PARA ESE CLIENTE");
			}

			for (TbMiAbono abono : abonos) {
				totalAbonos = totalAbonos.add(abono.getValor());
			}

			if (totalAbonos.compareTo(valor) >= 0) {
				for (TbMiAbono abono : abonos) {
					valor = valor.subtract(abono.getValor());
					abono.setEstado(EstadoMidasEnum.INA);
					abono.setTbMiContrato(contrato);
					abono.setUsuarioActualizacion(usuario);
					this.mos.manageAbono(abono);

				}
				if (valor.compareTo(BigDecimal.ZERO) < 0) {
					TbMiAbono saldoAbono = new TbMiAbono();
					saldoAbono.setEstado(EstadoMidasEnum.ACT);
					saldoAbono.setFormaPago(FormaPagoEnum.MOVIMIENTO_INTERNO);
					saldoAbono.setTbMiCliente(cliente);
					saldoAbono.setUsuarioCreacion(usuario);
					saldoAbono.setValor(valor.abs());
					return this.mos.manageAbono(saldoAbono);
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"EL VALOR NO PUEDE SER MAYOR QUE LOS ABONOS A CONSUMIR");
			}

		} catch (Exception e) {

		}
		return null;

	}

	/**
	 * Metodo que realiza el proceso de renovacion de contrato 
	 * tomando en cuenta los avonos y los valores de ingreso 
	 * @param usuario
	 * @param idContrato
	 * @param valorRenovacion
	 * @param valorAbono
	 * @return
	 * @throws RelativeException
	 */
	public TbMiContrato renovarContrato(String usuario, Long idContrato, 
			List<TbMiMovimientoCaja> detalleIngresos,
			BigDecimal valorAbono,BigDecimal valorRenovacion) throws RelativeException {
		try {
			TbMiContrato contratoNuevo = this.renovarContrato(idContrato,usuario,null);
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			if (contrato == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO EN CONTRATO");
			}
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			if (valorAbono == null || valorAbono.compareTo(BigDecimal.ZERO) == 0) {
				renovarContrato(usuario, idContrato, detalleIngresos, contrato);
			} else if (valorAbono != null && valorAbono.compareTo(BigDecimal.ZERO) > 0) {
				// se realisa un cruce de cuentas entre los abonos y el contrato
				if (valorRenovacion.compareTo(valorAbono) == 0) {
					this.movCaja.registrarEgreso(ProcesoEnum.RENOVAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, contrato.getTbMiAgencia().getId(),
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);
				} else {
					renovarContrato(usuario, idContrato, detalleIngresos, contrato);
					this.movCaja.registrarEgreso(ProcesoEnum.RENOVAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, contrato.getTbMiAgencia().getId(),
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);

				}
			}		
			for(TbMiJoya j : joyas) {
				j.setProceso(ProcesoEnum.RENOVAR_CONTRATO);
				this.mos.manageJoya(j);
			}
			return contratoNuevo;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception ex) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN EL PROCESO DE CANCELAR CONTRATO");
		}

	}
	
	/**
	 * Metodo que realiza la renovacion y actualiza los valores de calculos
	 * @param usuario
	 * @param idContrato
	 * @param detalleIngresos
	 * @param valorAbono
	 * @param valorRenovacion
	 * @return
	 * @throws RelativeException
	 */
	public TbMiContrato renovarContratoValores(String usuario, Long idContrato, List<TbMiContratoCalculo> detalleCalculos,
			 List<TbMiContratoCalculo> detalleCalculosCancelacion,List<TbMiMovimientoCaja> detalleIngresos,
			BigDecimal valorAbono,BigDecimal valorRenovacion, Long plazo, Long idAgencia) throws RelativeException {
		try {
			TbMiContrato contratoNuevo = this.renovarContrato(idContrato,usuario,plazo);
			TbMiContrato contrato = this.mos.findContratoById(idContrato);
			
			if (contrato == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO EN CONTRATO");
			}
			List<TbMiJoya> joyas = this.mos.findJoyaByIdFunda(null, contrato.getTbMiFunda().getId());
			if (valorAbono == null || valorAbono.compareTo(BigDecimal.ZERO) == 0) {
				renovarContrato(usuario, idContrato, detalleIngresos, contrato);
			} else if (valorAbono != null && valorAbono.compareTo(BigDecimal.ZERO) > 0) {
				// se realisa un cruce de cuentas entre los abonos y el contrato
				if (valorRenovacion.compareTo(valorAbono) == 0) {
					this.movCaja.registrarEgreso(ProcesoEnum.RENOVAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, idAgencia,
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);
				} else {
					renovarContrato(usuario, idContrato, detalleIngresos, contrato);
					this.movCaja.registrarEgreso(ProcesoEnum.RENOVAR_CONTRATO, "ABONO_CANCELAR",
							FormaPagoEnum.MOVIMIENTO_INTERNO, valorAbono, usuario, idAgencia,
							null, null, null, contrato.getTbMiCliente().getId(), idContrato, null, null, null, null,null,null);
					this.consumirAbonos(usuario, contrato.getTbMiCliente(), contrato, valorAbono);

				}
			}		
			this.mos.addContratoCalculoBatch(contratoNuevo,detalleCalculos);
			this.modifyJoyaOnContratoDetalle(joyas, usuario,ProcesoEnum.RENOVAR_CONTRATO);
			this.modifyContratoCalculoOnContratoDetalle(contrato, detalleCalculosCancelacion,usuario);
			return contratoNuevo;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception ex) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN EL PROCESO DE CANCELAR CONTRATO");
		}

	}

}