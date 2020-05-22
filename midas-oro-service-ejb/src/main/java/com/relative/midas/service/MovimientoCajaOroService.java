package com.relative.midas.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.enums.TipoTarjetaEnum;
import com.relative.midas.model.TbMiBanco;
import com.relative.midas.model.TbMiCaja;
import com.relative.midas.model.TbMiCliente;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.model.TbMiCuenta;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.model.TbMiVentaLote;
import com.relative.midas.repository.CajaRepository;
import com.relative.midas.repository.CorteCajaRepository;
import com.relative.midas.repository.CuentaRepository;
import com.relative.midas.repository.DetalleCajaRepository;
import com.relative.midas.repository.MovimientoCajaRepository;
import com.relative.midas.wrapper.MovimientoCajaWrapper;

@Stateless
public class MovimientoCajaOroService {
	@Inject
	Logger log;
	@Inject
	MidasOroService mos;
	@Inject
	CajaRepository cajaRepository;
	@Inject
	CuentaRepository cuentaRepository;
	@Inject
	MovimientoCajaRepository movimientoCajaRepository;
	@Inject
	CorteCajaRepository corteCajaRepository;
	@Inject
	DetalleCajaRepository detalleCajaRepository;

	public TbMiMovimientoCaja registrarIngreso(ProcesoEnum proceso, String cuenta, FormaPagoEnum formaPagoEnum,
			BigDecimal valor, String usuario, Long idAgencia, Long idBanco, String numeroCuentaBanco,
			String tipoCuentaBanco, Long idCliente, Long idConstrato, 
			TipoTarjetaEnum tipoTarjeta, String numeroTarjeta, String habienteTarjeta, String cedHabienteTarjeta,String comentario,
			Long idVentaLote, Long idCorteCaja) throws RelativeException {
		log.info("===============>entrando a registrarIngreso<=============");
		log.info("===============>proceso<=============" + proceso);
		log.info("===============>cuenta<=============" + cuenta);
		log.info("===============>formaPagoEnum<=============" + formaPagoEnum);
		log.info("===============>valor<=============" + valor);
		log.info("===============>usuario<=============" + usuario);
		log.info("===============>idAgencia<=============" + idAgencia);
		log.info("===============>idBanco<=============" + idBanco);
		log.info("===============>numeroCuentaBanco<=============" + numeroCuentaBanco);
		log.info("===============>tipoCuentaBanco<=============" + tipoCuentaBanco);
		log.info("===============>idCliente<=============" + idCliente);
		log.info("===============>idConstrato<=============" + idConstrato);
		log.info("===============>TipoTarjeta<=============" + tipoTarjeta);
		log.info("===============>NumeroTarjeta<=============" + numeroTarjeta);
		log.info("===============>HabienteTarjeta<=============" + habienteTarjeta);
		log.info("===============>CedulaHabienteTarjeta<=============" + cedHabienteTarjeta);
		log.info("===============>idVentaLote<=============" + idVentaLote);
		log.info("===============>idVentaLote<=============" + idCorteCaja);
		TbMiMovimientoCaja movCaja = new TbMiMovimientoCaja();
		TbMiBanco banco;
		if (proceso != null && StringUtils.isNotBlank(cuenta) && formaPagoEnum != null && valor != null
				&& !valor.equals(BigDecimal.ZERO) && StringUtils.isNotBlank(usuario)) {
			TbMiCaja caja = cajaRepository.findCajaByAgenciaId(idAgencia);
			if (caja == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "LA CAJA PARA ESTA AGENCIA NO EXISTE");
			}
			TbMiCuenta cuentA = cuentaRepository.findCuentaByCodigo(cuenta);
			if (cuentA == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "LA CUENTA NO EXISTE");
			}
			TbMiContrato contrato = idConstrato != null ? mos.findContratoById(idConstrato) : null;
			
			if(idCorteCaja != null) {
				TbMiCorteCaja corteCaja = mos.findCorteCajaById(idCorteCaja);
				movCaja.setTbMiCorteCaja(corteCaja);
			}
			if (idCliente != null) {
				TbMiCliente cliente = mos.findClienteById(idCliente);
				movCaja.setTbMiCliente(cliente);
			}
			if (idVentaLote != null) {
				TbMiVentaLote ventaLote = mos.findVentaLoteById(idVentaLote);
				movCaja.setTbMiVentaLote(ventaLote);
			}
			

			
			movCaja.setEgreso(BigDecimal.ZERO);
			if (!formaPagoEnum.equals(FormaPagoEnum.EFECTIVO) && idBanco != null) {
				try {
					banco = mos.findBancoById(idBanco);
					movCaja.setTbMiBanco(banco);
					movCaja.setNumeroCuentaBanco(numeroCuentaBanco);
					movCaja.setTipoCuentaBanco(tipoCuentaBanco);
					movCaja.setTipoTarjeta(tipoTarjeta);
					movCaja.setNumeroTarjeta(numeroTarjeta);
					movCaja.setHabienteTarjeta(habienteTarjeta);
					movCaja.setCedHabienteTarjeta(cedHabienteTarjeta);
				} catch (RelativeException e) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "BANCO NO ENCONTRADO" + e.getMensaje());
				}

			}else if(formaPagoEnum.equals(FormaPagoEnum.EFECTIVO)){
				log.info( "!!!!!!!!!=>>>>Entra a ingreso con pago en efectivo y sakdo " + caja.getSaldoActual().add(valor));
				caja.setSaldoActual(caja.getSaldoActual().add(valor));	
			}
			caja.setUsuarioActualizacion(usuario);
			caja = mos.manageCaja(caja);
			log.info( "!!!!!!!!!=>>>>Caja saldo actualizado " + caja.getSaldoActual() );
			movCaja.setProceso(proceso);
			movCaja.setEstado(formaPagoEnum.equals(FormaPagoEnum.EFECTIVO) || formaPagoEnum.equals(FormaPagoEnum.MOVIMIENTO_INTERNO) ? 
					EstadoMidasEnum.PAGADO: EstadoMidasEnum.PENDIENTE);
			movCaja.setFechaCreacion(new Date());
			movCaja.setFormaPago(formaPagoEnum);
			movCaja.setIngreso(valor);
			movCaja.setTbMiCaja(caja);
			movCaja.setTbMiCuenta(cuentA);
			movCaja.setTipo("INGRESO");
			movCaja.setUsuarioCreacion(usuario);
			movCaja.setTbMiContrato(contrato);
			movCaja.setComentario(comentario);
			movCaja = mos.manageMovimientoCaja(movCaja);
			

		} else {
			if (proceso == ProcesoEnum.APERTURA_CAJA) {
				return null;
			}
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE CREAR EL INGRESO A CAJA");
		}

		return movCaja;
	}

	public TbMiMovimientoCaja registrarEgreso(ProcesoEnum proceso, String cuenta, FormaPagoEnum formaPagoEnum,
			BigDecimal valor, String usuario, Long idAgencia, Long idBanco, String numeroCuentaBanco,
			String tipoCuentaBanco, Long idCliente, Long idConstrato, TipoTarjetaEnum tipoTarjeta, 
			String numeroTarjeta, String habienteTarjeta, String cedHabienteTarjeta,String comentario, Long idCorteCaja) throws RelativeException {
		log.info("===============>entrando a registrarEgreso<=============");
		log.info("===============>cuenta<=============" + proceso);
		log.info("===============>cuenta<=============" + cuenta);
		log.info("===============>formaPagoEnum<=============" + formaPagoEnum);
		log.info("===============>valor<=============" + valor);
		log.info("===============>usuario<=============" + usuario);
		log.info("===============>idAgencia<=============" + idAgencia);
		log.info("===============>idBanco<=============" + idBanco);
		log.info("===============>numeroCuentaBanco<=============" + numeroCuentaBanco);
		log.info("===============>tipoCuentaBanco<=============" + tipoCuentaBanco);
		log.info("===============>idCliente<=============" + idCliente);
		log.info("===============>idConstrato<=============" + idConstrato);
		log.info("===============>TipoTarjeta<=============" + tipoTarjeta);
		log.info("===============>NumeroTarjeta<=============" + numeroTarjeta);
		log.info("===============>HabienteTarjeta<=============" + habienteTarjeta);
		log.info("===============>CedulaHabienteTarjeta<=============" + cedHabienteTarjeta);
		
		
		
		TbMiMovimientoCaja movCaja = new TbMiMovimientoCaja();
		if (proceso != null && StringUtils.isNotBlank(cuenta) && formaPagoEnum != null && valor != null
				&& !valor.equals(BigDecimal.ZERO) && StringUtils.isNotBlank(usuario)) {
			TbMiCaja caja = cajaRepository.findCajaByAgenciaId(idAgencia);
			TbMiCuenta cuentA = cuentaRepository.findCuentaByCodigo(cuenta);

			TbMiContrato contrato = idConstrato != null ? mos.findContratoById(idConstrato) : null;
			TbMiBanco banco;
			if (idCorteCaja != null) {
				TbMiCorteCaja corteCaja = mos.findCorteCajaById(idCorteCaja);
				movCaja.setTbMiCorteCaja(corteCaja);
			}
			if (idCliente != null) {
				TbMiCliente cliente = mos.findClienteById(idCliente);
				movCaja.setTbMiCliente(cliente);
			}
			if (caja == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "LA CAJA PARA ESTA AGENCIA NO EXISTE");
			}

			if (cuentA == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "LA CUENTA NO EXISTE");
			}
			movCaja.setEgreso(valor);
			if (!formaPagoEnum.equals(FormaPagoEnum.EFECTIVO) && idBanco != null) {
				try {
					banco = mos.findBancoById(idBanco);
					movCaja.setTbMiBanco(banco);
					movCaja.setNumeroCuentaBanco(numeroCuentaBanco);
					movCaja.setTipoCuentaBanco(tipoCuentaBanco);
					movCaja.setTipoTarjeta(tipoTarjeta);
					movCaja.setNumeroTarjeta(numeroTarjeta);
					movCaja.setHabienteTarjeta(habienteTarjeta);
					movCaja.setCedHabienteTarjeta(cedHabienteTarjeta);
					
					
				} catch (RelativeException e) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "BANCO NO ENCONTRADO" + e.getMensaje());
				}

			}else if(formaPagoEnum.equals(FormaPagoEnum.EFECTIVO)){
				caja.setSaldoActual(caja.getSaldoActual().subtract(valor));				
			}
			movCaja.setProceso(proceso);
			movCaja.setEstado(formaPagoEnum.equals(FormaPagoEnum.EFECTIVO)
					|| formaPagoEnum.equals(FormaPagoEnum.MOVIMIENTO_INTERNO) ? EstadoMidasEnum.PAGADO
							: EstadoMidasEnum.PENDIENTE);
			movCaja.setFormaPago(formaPagoEnum);
			movCaja.setIngreso(BigDecimal.ZERO);
			movCaja.setTbMiCaja(caja);
			movCaja.setTbMiCuenta(cuentA);
			movCaja.setTipo("EGRESO");
			movCaja.setUsuarioCreacion(usuario);
			movCaja.setTbMiContrato(contrato);
			movCaja.setComentario(comentario);
			movCaja = mos.manageMovimientoCaja(movCaja);
			caja.setUsuarioActualizacion(usuario);
			mos.manageCaja(caja);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE CREAR EL EGRESO A CAJA");
		}

		return movCaja;
	}
	
	public List<MovimientoCajaWrapper> findMovimientoCajaByUsuarioAndCaja(PaginatedWrapper pw, String usuario, String idCaja) throws RelativeException{
		return this.movimientoCajaRepository.findByUsuarioAndCaja(pw, usuario , idCaja);
	}
	
	public Long countMovimientoCajaByUsuarioAndCaja(String usuario, String idCaja) throws RelativeException{
		return this.movimientoCajaRepository.countByUsuarioAndCaja(usuario , idCaja);
	}



}
