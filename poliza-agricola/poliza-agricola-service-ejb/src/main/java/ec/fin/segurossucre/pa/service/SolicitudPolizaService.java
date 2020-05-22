package ec.com.def.pa.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;
import ec.com.def.pa.model.Estadocivil;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.TbPaCanalSecuencia;
import ec.com.def.pa.model.TbPaCaracteristicaCultivo;
import ec.com.def.pa.model.TbPaPredio;
import ec.com.def.pa.model.TbPaSolicitudPoliza;
import ec.com.def.pa.model.TbSaAsegurado;
import ec.com.def.pa.model.Un01;
import ec.com.def.pa.repository.AseguradoRepository;
import ec.com.def.pa.repository.CanalSecuenciaRepository;
import ec.com.def.pa.repository.CaracteristicaCultivoRepository;
import ec.com.def.pa.repository.ParametroRepository;
import ec.com.def.pa.repository.PredioRepository;
import ec.com.def.pa.repository.SolicitudPolizaRepository;
import ec.com.def.pa.repository.Un01Repository;
import ec.com.def.pa.un01.api.Un01ApiClient;
import ec.com.def.pa.util.SiniestroAgricolaConstantes;
import ec.com.def.pa.util.SiniestroAgricolaUtils;
import ec.com.def.pa.wrapper.ConsultaSolicitudPolizaWrapper;
import ec.com.def.pa.wrapper.UN01Wrapper;
@Stateless
public class SolicitudPolizaService {

	@Inject
	Logger log;

	@Inject
	SolicitudPolizaRepository solicitudPolizaRepository;

	@Inject
	AseguradoRepository aseguradoRepository;

	@Inject
	CaracteristicaCultivoRepository caracteristicaCultivoRepository;

	@Inject
	PredioRepository predioRepository;
	@Inject
	Un01Repository un01Repository;
	@Inject
	CanalSecuenciaRepository canalSecuenciaRepository;

	@Inject
	ParametroRepository parametroRepository;
	@Inject
	CargaMasivaPolizaService cms;

	
	
	/**
	 * Lista contratos por agencia y dos estado en clase personalizada
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws DefException
	 * @author Saul Mendez
	 */
	public List<ConsultaSolicitudPolizaWrapper> findByParams(PaginatedWrapper pw, String  numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException {
		return this.solicitudPolizaRepository.findByParams(pw, numeroSolicitud, numeroTramite, desde,hasta,canal);
	}

	
	/**
	 * Lista parametros de solicitud de poliza para la descarga a excel
	 * ContratosPerfecionados
	 * 
	 * @param pw
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws DefException
	 * @author Saul Mendez
	 */
	public List<ConsultaSolicitudPolizaWrapper> findByParamsReporte( String  numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException {
		return this.solicitudPolizaRepository.setWrapperSolicitudPoliza( numeroSolicitud, numeroTramite, desde,hasta,canal);
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
	 * @throws DefException
	 * @author Saul Mendez
	 */
	public Integer countBySolicitudPoliza(PaginatedWrapper pw, String numeroSolicitud, String numeroTramite, Date desde, Date hasta, String canal) throws DefException {
		try {
			return this.solicitudPolizaRepository.countBySolicitudesPoliza(pw, numeroSolicitud, numeroTramite, desde,hasta,canal);
		} catch (Exception e) {
			throw new DefException("" + e);
		}
	}

	
	/**
	 * SolicitudPoliza
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public TbPaSolicitudPoliza findSolicitudPolizaById(Long id) throws DefException {
		try {
			return solicitudPolizaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countSolicitudPoliza() throws DefException {
		try {
			return solicitudPolizaRepository.countAll(TbPaSolicitudPoliza.class);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "SolicitudPoliza no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaSolicitudPoliza> findAllSolicitudPoliza(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.solicitudPolizaRepository.findAll(TbPaSolicitudPoliza.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.solicitudPolizaRepository.findAll(TbPaSolicitudPoliza.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.solicitudPolizaRepository.findAll(TbPaSolicitudPoliza.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws DefException
	 */
	public TbPaSolicitudPoliza manageSolicitudPoliza(TbPaSolicitudPoliza send) throws DefException {
		try {
			log.info("==> entra a manage SolicitudPoliza");
			TbPaSolicitudPoliza persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findSolicitudPolizaById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateSolicitudPoliza(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

				return solicitudPolizaRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "TbPaSolicitudPoliza es null");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la SolicitudPoliza " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws DefException
	 */
	public TbPaSolicitudPoliza updateSolicitudPoliza(TbPaSolicitudPoliza send, TbPaSolicitudPoliza persisted)
			throws DefException {
		try {
			persisted.setAutorizacion(send.getAutorizacion());
			persisted.setEstado(send.getEstado());
			persisted.setTieneEndoso(send.getTieneEndoso());
			persisted.setUbicacionPredio(send.getUbicacionPredio());
			persisted.setValorEndoso(send.getValorEndoso());
			return solicitudPolizaRepository.update(persisted);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando SolicitudPoliza " + e.getMessage());
		}
	}

	/**
	 * CaracteristicaCultivo
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public TbPaCaracteristicaCultivo findCaracteristicaCultivoById(Long id) throws DefException {
		try {
			return caracteristicaCultivoRepository.findById(id);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "TbPaCaracteristicaCultivo NO ENCONTRADO " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countCaracteristicaCultivo() throws DefException {
		try {
			return caracteristicaCultivoRepository.countAll(TbPaCaracteristicaCultivo.class);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ,
					"countCaracteristicaCultivo no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaCaracteristicaCultivo> findAllCaracteristicaCultivo(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.caracteristicaCultivoRepository.findAll(TbPaCaracteristicaCultivo.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.caracteristicaCultivoRepository.findAll(TbPaCaracteristicaCultivo.class,
						pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.caracteristicaCultivoRepository.findAll(TbPaCaracteristicaCultivo.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws DefException
	 */
	public TbPaCaracteristicaCultivo manageCaracteristicaCultivo(TbPaCaracteristicaCultivo send)
			throws DefException {
		try {
			log.info("==> entra a manage CaracteristicaCultivo");
			TbPaCaracteristicaCultivo persisted = null;
			validarCaracteristicaCultivo(send);
			if (send != null && send.getId() != null) {
				persisted = this.findCaracteristicaCultivoById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCaracteristicaCultivo(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

				return caracteristicaCultivoRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "manageCaracteristicaCultivo");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CaracteristicaCultivo " + e.getMessage());
		}
	}
	
	public void validarCaracteristicaCultivo(TbPaCaracteristicaCultivo send)throws DefException {
		if(send.getRamoplan() == null || send.getSuperficieAsegurada() == null || send.getCostoHectarea() == null 
				|| send.getMontoAsegurado() == null || send.getFechaSiembra() == null || StringUtils.isBlank(send.getAsistenciaTecnica()) 
				|| StringUtils.isBlank(send.getRiego()) || send.getRiegoBean() == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"COMPLETE LOS CAMPOS OBLIGATORIOS DEL CULTIVO");
		}
		if(StringUtils.isNotBlank(send.getVariedad()) && send.getVariedad().length()>20) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"LA VARIEDAD DEL CULTIVO SOLO PUEDO TENER 20 CARACTERES");
		}
		
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws DefException
	 */
	public TbPaCaracteristicaCultivo updateCaracteristicaCultivo(TbPaCaracteristicaCultivo send,
			TbPaCaracteristicaCultivo persisted) throws DefException {
		try {
			persisted.setAsistenciaTecnica(send.getAsistenciaTecnica());
			persisted.setCostoHectarea(send.getCostoHectarea());
			persisted.setFechaSiembra(send.getFechaSiembra());
			persisted.setMontoAsegurado(send.getMontoAsegurado());
			persisted.setRamoplan(send.getRamoplan());
			persisted.setRiego(send.getRiego());
			persisted.setRiegoBean(send.getRiegoBean());
			persisted.setSuperficieAsegurada(send.getSuperficieAsegurada());
			persisted.setTiposemilla(send.getTiposemilla());
			persisted.setVariedad(send.getVariedad());
			return caracteristicaCultivoRepository.update(persisted);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CaracteristicaCultivo " + e.getMessage());
		}
	}

	/**
	 * Predio
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public TbPaPredio findPredioById(Long id) throws DefException {
		try {
			return predioRepository.findById(id);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countPredio() throws DefException {
		try {
			return predioRepository.countAll(TbPaPredio.class);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "Predio no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaPredio> findAllPredio(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.predioRepository.findAll(TbPaPredio.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.predioRepository.findAll(TbPaPredio.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.predioRepository.findAll(TbPaPredio.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws DefException
	 */
	public TbPaPredio managePredio(TbPaPredio send) throws DefException {
		try {
			log.info("==> entra a manage Predio");
			validatePredio(send);
			TbPaPredio persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findPredioById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updatePredio(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

				return predioRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Predio " + e.getMessage());
		}
	}
	
	private void validatePredio(TbPaPredio predio)  throws DefException {
		if(predio.getParroquia() == null || StringUtils.isBlank(predio.getRecinto())
				|| StringUtils.isBlank(predio.getReferencia()) || predio.getCondicionpredio() == null) {
			throw new DefException(Constantes.ERROR_CODE_CREATE, "COMPLETAR LOS CAMPOS REQUERIDOS");
		}
		/**
		 * LATITUD LIMITES:            162949 al 9446349
		 * LONGITUD LIMITES:        480591 al 498898 
		 */
		if(predio.getCoordenadaY() != null &&
			(predio.getCoordenadaY().compareTo(BigDecimal.valueOf(
					Long.valueOf(this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MIN_LATITUD).getValor()))) <0
				|| predio.getCoordenadaY().compareTo(BigDecimal.valueOf(
						Long.valueOf(this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MAX_LATITUD).getValor()))) > 0) ) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"LATITUD LIMITES: "+
						this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MIN_LATITUD).getValor()
						+" al "+this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MAX_LATITUD).getValor());
		}
		if(predio.getCoordenadaX() != null &&
			(predio.getCoordenadaX().compareTo(BigDecimal.valueOf(
					Long.valueOf(this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MIN_LONGITUD).getValor()))) <0
				|| predio.getCoordenadaX().compareTo(BigDecimal.valueOf(
						Long.valueOf(this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MAX_LONGITUD).getValor()))) > 0) ) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,
						"LONGITUD LIMITES: "+
								this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MIN_LONGITUD).getValor()
								+" al "+this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.MAX_LONGITUD).getValor());
		}

		if(predio.getRecinto().length()>150) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"RECINTO DEL PREDIO SOLO PUEDE TENER 150 CARACTERES");
		}
		if(predio.getReferencia().length()>150) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"REFERENCIA DEL PREDIO SOLO PUEDE TENER 150 CARACTERES");
		}
		if(predio.getAltitud() != null && predio.getAltitud().compareTo(BigDecimal.valueOf(Long.valueOf("9999999999")))>0) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"ALTITUD DEL PREDIO NO PUEDE SER MAYOR QUE 9999999999");
		}
		if(StringUtils.isNotBlank(predio.getCondicion()) && predio.getCondicion().length()>20) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"CONDICION DEL PREDIO SOLO PUEDE TENER 20 CARACTERES");
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws DefException
	 */
	public TbPaPredio updatePredio(TbPaPredio send, TbPaPredio persisted) throws DefException {
		try {
			persisted.setAltitud(send.getAltitud());
			persisted.setCondicion(send.getCondicion());
			persisted.setCondicionpredio(send.getCondicionpredio());
			persisted.setCoordenadaX(send.getCoordenadaX());
			persisted.setCoordenadaY(send.getCoordenadaY());
			persisted.setParroquia(send.getParroquia());
			persisted.setRecinto(send.getRecinto());
			persisted.setReferencia(send.getReferencia());
			return predioRepository.update(persisted);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Predio " + e.getMessage());
		}
	}

	/**
	 * Asegurado
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public TbSaAsegurado findAseguradoById(Long id) throws DefException {
		try {
			return aseguradoRepository.findById(id);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countAsegurado() throws DefException {
		try {
			return aseguradoRepository.countAll(TbSaAsegurado.class);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_READ, "Asegurado no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public List<TbSaAsegurado> findAllAsegurado(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.aseguradoRepository.findAll(TbSaAsegurado.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.aseguradoRepository.findAll(TbSaAsegurado.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.aseguradoRepository.findAll(TbSaAsegurado.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws DefException
	 */
	public TbSaAsegurado manageAsegurado(TbSaAsegurado send) throws DefException {
		try {
			log.info("==> entra a manage Asegurado");
			this.validarAsegurado(send);
			TbSaAsegurado persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findAseguradoById(send.getId());
				persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateAsegurado(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoSiniestroAgricolaEnum.ACT);
				return aseguradoRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Asegurado " + e.getMessage());
		}
	}
	
	private void validarAsegurado(TbSaAsegurado asegurado) throws DefException {
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getIdentificacion()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getApellido()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getNombres()));
		log.info("=======>>>"+(asegurado.getEstadocivil() == null));
		log.info("=======>>>"+(asegurado.getFechaNacimiento() == null));
		log.info("=======>>>"+(asegurado.getparroquiaNacimiento() == null));
		log.info("=======>>>"+(asegurado.getparroquiaDomicilio() == null));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getDireccion()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getReferencia()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getTelefonoCelular() ));
		log.info("=======>>>"+validateingreso(asegurado.getIngresoAnual()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getCorreo()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getSexo()));
		log.info("=======>>>"+(asegurado.getActeco() == null));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getAgricultura()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getNacionalidad()));
		log.info("=======>>>"+StringUtils.isBlank(asegurado.getPoliticamenteExpuesta()) );
		if ( StringUtils.isBlank(asegurado.getIdentificacion()) || StringUtils.isBlank(asegurado.getApellido())
				|| StringUtils.isBlank(asegurado.getNombres()) || StringUtils.isBlank(asegurado.getNacionalidad())
				|| StringUtils.isBlank(asegurado.getSexo()) || asegurado.getEstadocivil() == null
				|| asegurado.getFechaNacimiento() == null || asegurado.getparroquiaNacimiento() == null
				|| asegurado.getparroquiaDomicilio() == null || StringUtils.isBlank(asegurado.getDireccion())
				|| StringUtils.isBlank(asegurado.getReferencia()) || StringUtils.isBlank(asegurado.getTelefonoCelular() )
				|| !validateingreso(asegurado.getIngresoAnual()) || StringUtils.isBlank(asegurado.getCorreo())
				|| asegurado.getActeco() == null || StringUtils.isBlank(asegurado.getAgricultura())
				|| StringUtils.isBlank(asegurado.getPoliticamenteExpuesta()) ) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"COMPLETE LA INFORMACION REQUERIDA");
		}
		if(!SiniestroAgricolaUtils.validateEmail(asegurado.getCorreo())) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"CORREO ELECTRONICO INVALIDO");
		}
		if(asegurado.getDireccion().length()>50) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"CALLE DEL ASEGURADO SOLO PUEDE TENER 50 CARACTERES");
		}
		if(asegurado.getReferencia().length()>150) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"RECINTO DEL ASEGURADO SOLO PUEDE TENER 150 CARACTERES");
		}
		
	}

	
	private boolean validateingreso(BigDecimal bigDecimal) throws DefException {
		try {
			if(bigDecimal == null) {
				return false;
			}
			if(bigDecimal.compareTo(BigDecimal.valueOf(Double.valueOf("800"))) < 0) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"LOS INGRESOS ANUALES NO PUEDEN SER MENOR QUE 800");
			}
			return true;
		} catch (DefException e) {
			throw e;
		}catch (NumberFormatException e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"LOS INGRESOS ANUALES NO PUEDEN SER MENOR QUE 800");
		}
	}
	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws DefException
	 */
	public TbSaAsegurado updateAsegurado(TbSaAsegurado send, TbSaAsegurado persisted) throws DefException {
		try {
			if(persisted.getActeco()==null) {
				persisted.setActeco(send.getActeco());
			}
			if(persisted.getAgricultura()==null) {
				persisted.setAgricultura(send.getAgricultura());
			}
			if(persisted.getApellido()==null) {
				persisted.setApellido(send.getApellido());
			}
			if(persisted.getDireccion()==null) {
				persisted.setDireccion(send.getDireccion());
			}
			if(persisted.getDireccion()==null) {
				persisted.setDireccion(send.getDireccion());
			}
			if(persisted.getEstadocivil()==null) {
				persisted.setEstadocivil(send.getEstadocivil());
			}
			if(persisted.getFechaNacimiento()==null) {
				persisted.setFechaNacimiento(send.getFechaNacimiento());
			}
			if(persisted.getNacionalidad()==null) {
				persisted.setNacionalidad(send.getNacionalidad());
			}
			if(persisted.getLugarNacimiento()==null) {
				persisted.setLugarNacimiento(send.getLugarNacimiento());
			}
			if(persisted.getparroquiaDomicilio()==null) {
				persisted.setparroquiaDomicilio(send.getparroquiaDomicilio());
			}			
			if(persisted.getparroquiaNacimiento()==null) {
				persisted.setparroquiaNacimiento(send.getparroquiaNacimiento());
			}
			if(persisted.getPoliticamenteExpuesta()==null) {
				persisted.setPoliticamenteExpuesta(send.getPoliticamenteExpuesta());
			}
			if(persisted.getReferencia()==null) {
				persisted.setReferencia(send.getReferencia());
			}
			if(persisted.getSexo()==null) {
				persisted.setSexo(send.getSexo());
			}
			persisted.setCorreo(send.getCorreo());
			persisted.setEstado(EstadoSiniestroAgricolaEnum.ACT);
			persisted.setIngresoAnual(send.getIngresoAnual());
			
			persisted.setTelefonoCelular(send.getTelefonoCelular());			
			persisted.setTelefonoConvencional(send.getTelefonoConvencional());
			return aseguradoRepository.update(persisted);
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Asegurado " + e.getMessage());
		}
	}
	


	/**
	 * Guarda la poliza y actualiza las referencias
	 * 
	 * @param poliza
	 * @return
	 * @throws DefException
	 */
	public TbPaSolicitudPoliza guardarPoliza(TbPaSolicitudPoliza poliza) throws DefException {
		try {
			
			poliza.setCodigo(generateCodigo());
			poliza.setEstado(EstadoSiniestroAgricolaEnum.PE);
			poliza.setNumeroTramite(generateNumeroTramite(poliza.getRamocanal()));
			validatePoliza(poliza);
			poliza.setTbPaCaracteristicaCultivo(
					this.manageCaracteristicaCultivo(poliza.getTbPaCaracteristicaCultivo()));
			poliza.setTbPaPredio(this.managePredio(poliza.getTbPaPredio()));
			poliza.setTbSaAsegurado(this.manageAsegurado(poliza.getTbSaAsegurado()));
			if(poliza.getAutorizacion().equalsIgnoreCase("No")) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"TIENE QUE ACEPTAR LA AUTORIAZACION");
			}
			poliza = this.manageSolicitudPoliza(poliza);
			//guardarUn01(poliza);
			Un01ApiClient.callpun01Rest(this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.
					  API_URL_UN01).getValor(), null, mapPolizaToUn01(poliza));
			return poliza;
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL INTENTAR GUARDAR LA POLIZA");
		}
		
	}
	

	public UN01Wrapper mapPolizaToUn01(TbPaSolicitudPoliza poliza)throws DefException {
		UN01Wrapper un01 = new UN01Wrapper();
		un01.setActividadEconomica(poliza.getTbSaAsegurado().getActeco().getAeccod());
		un01.setApellidos(poliza.getTbSaAsegurado().getApellido());
		un01.setApellidosNombres(poliza.getNombreEjecutivo());
		un01.setAsistenciaTecnica(StringUtils.isNotBlank(poliza.getTbPaCaracteristicaCultivo().getAsistenciaTecnica())?poliza.getTbPaCaracteristicaCultivo().getAsistenciaTecnica().trim():"");
		un01.setCalleReferencia(StringUtils.isNotBlank(poliza.getTbSaAsegurado().getDireccion())?poliza.getTbSaAsegurado().getDireccion().trim():"");
		un01.setCanal(poliza.getRamocanal().getId().getCanalid());
		un01.setCantonPredio(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid().trim()
				.concat(poliza.getTbPaPredio().getParroquia().getId().getCantonid().trim()));
		un01.setCedula(poliza.getTbSaAsegurado().getIdentificacion());
		un01.setCelular(poliza.getTbSaAsegurado().getTelefonoCelular());
		un01.setCodigoCultivo(poliza.getTbPaCaracteristicaCultivo().getRamoplan().getId().getRamoplanid());
		un01.setCodigoSucursal("1");
		un01.setCodigoVariedadCultivo("");
		un01.setCondicionPredio(poliza.getTbPaPredio().getCondicionpredio().getCondicionprediocod());
		un01.setCostoEstablecimientoCultivo("0");
		un01.setCostoMantenimiento("0");
		un01.setDireccionDomicilio(poliza.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom().trim().concat("/")
				.concat(poliza.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getCantonnom().trim()).concat("/")
				.concat(poliza.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom().trim()).concat("/")
				.concat(poliza.getTbSaAsegurado().getDireccion().trim()));
		if(un01.getDireccionDomicilio().length()>100) {
			un01.setDireccionDomicilio(un01.getDireccionDomicilio().substring(0,99));
		}
		un01.setDisponeRiego(StringUtils.isNotBlank(poliza.getTbPaCaracteristicaCultivo().getRiego())?poliza.getTbPaCaracteristicaCultivo().getRiego().trim():"");
		un01.setEmail(poliza.getTbSaAsegurado().getCorreo());
		un01.setEstadoCivil(poliza.getTbSaAsegurado().getEstadocivil().getEstadocivilcod());
		un01.setEstadoSolicitud("2");
		un01.setFechaNacimiento(SiniestroAgricolaUtils.dateToStringFormat(poliza.getTbSaAsegurado().getFechaNacimiento()));
		un01.setFechaSiembra(SiniestroAgricolaUtils.dateToStringFormat(poliza.getTbPaCaracteristicaCultivo().getFechaSiembra()));
		un01.setFechaSolicitud(SiniestroAgricolaUtils.dateToStringFormat(poliza.getFechaCreacion()));
		un01.setFechaTentativaSiembra(SiniestroAgricolaUtils.dateToStringFormat(poliza.getTbPaCaracteristicaCultivo().getFechaSiembra()));
		un01.setFunteIngreso(StringUtils.isNotBlank(poliza.getTbSaAsegurado().getAgricultura())?poliza.getTbSaAsegurado().getAgricultura().trim():"");
		un01.setIngresoAnual(poliza.getTbSaAsegurado().getIngresoAnual().toString());
		un01.setLatDecimal(poliza.getTbPaPredio().getCoordenadaX() != null?poliza.getTbPaPredio().getCoordenadaX().toString():"");
		un01.setLongDecimal(poliza.getTbPaPredio().getCoordenadaY()!= null? poliza.getTbPaPredio().getCoordenadaY().toString():"");
		un01.setLugarNacimiento(poliza.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia().getProvincianom().trim().concat("/")
				.concat(poliza.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getCantonnom().trim()).concat("/")
				.concat(poliza.getTbSaAsegurado().getparroquiaNacimiento().getParroquianom().trim()));
		un01.setNacionalidad(poliza.getTbSaAsegurado().getNacionalidad());
		un01.setNombres(poliza.getTbSaAsegurado().getNombres());
		un01.setNumeroLote("S/N");
		un01.setNumeroTramite(poliza.getNumeroTramite());
		un01.setOtraCondicionPredio("");
		un01.setOtroRiego("");
		un01.setParroquiaPredio(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid().trim()
				.concat(poliza.getTbPaPredio().getParroquia().getId().getCantonid().trim())
				.concat(poliza.getTbPaPredio().getParroquia().getId().getParroquiaid().trim()));
		un01.setPEP(StringUtils.isNotBlank(poliza.getTbSaAsegurado().getPoliticamenteExpuesta())?poliza.getTbSaAsegurado().getPoliticamenteExpuesta().trim():"");
		un01.setPlazoVencimiento(poliza.getTbPaCaracteristicaCultivo().getRamoplan().getRamoplandias().toString());
		un01.setProvinciaPredio(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid().trim());
		un01.setRecinto(poliza.getTbPaPredio().getRecinto());
		if(un01.getRecinto().length()>100) {
			un01.setRecinto(un01.getRecinto().substring(0,99));
		}
		un01.setRuc("");
		un01.setSexo(poliza.getTbSaAsegurado().getSexo());
		un01.setCostoSuperficieAsegurada(poliza.getTbPaCaracteristicaCultivo().getCostoHectarea().toString());
		un01.setSuperficieAsegurada(poliza.getTbPaCaracteristicaCultivo().getSuperficieAsegurada().toString());
		un01.setSuperficieTotalCult(poliza.getTbPaCaracteristicaCultivo().getMontoAsegurado().toString());
		un01.setTelefono(poliza.getTbSaAsegurado().getTelefonoConvencional());
		un01.setTipoCanal("3");
		un01.setTipoRiego(poliza.getTbPaCaracteristicaCultivo().getRiegoBean().getRiegocod().trim());
		un01.setTipoSemilla(poliza.getTbPaCaracteristicaCultivo().getTiposemilla() != null?
				poliza.getTbPaCaracteristicaCultivo().getTiposemilla().getTiposemillacod().trim():"");
		un01.setUsuario(poliza.getUsuarioCreacion());
		un01.setValorSubsidio("");
		return un01;
	}
	
	
	public TbSaAsegurado finAseguradoByIdentificacion(String identificacion) throws DefException {
		try {
			TbSaAsegurado asegurado = aseguradoRepository.finByIdentificacion(identificacion);
			if (asegurado == null) {
				Un01 un = un01Repository.finByIdentificacion(identificacion);
				if (un != null) {
					asegurado = un01ToAsegurado(un);
				}
			}
			return asegurado;
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL LEER LA INFORMACION DEL ASEGURADO");
		}
	}

	private TbSaAsegurado un01ToAsegurado(Un01 un) throws DefException {

		try {
			TbSaAsegurado ase = new TbSaAsegurado();
			ase.setIdentificacion(un.getUn01Cedula().trim());
			ase.setApellido(un.getUn01Apellidos().trim());
			ase.setNombres(un.getUn01Nombres().trim());
			ase.setNacionalidad(un.getUn01Nacionalidad().trim());
			ase.setSexo(un.getUn01Genero().trim());
			Estadocivil estadoCivil = new Estadocivil();
			estadoCivil.setEstadocivilcod(un.getUn01Estadocivil().trim());
			ase.setEstadocivil(estadoCivil);
			ase.setFechaNacimiento(un.getUn01Fecnac());

			return ase;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "AL LEER LA INFORMACION DEL ASEGURADO DE UN01");
		}

	}

	private void guardarUn01(TbPaSolicitudPoliza poliza) throws DefException {
		Un01 un = new Un01();

		// asegurado
		un.setUn01Cedula(poliza.getTbSaAsegurado().getIdentificacion());
		un.setUn01Apellidos(poliza.getTbSaAsegurado().getApellido());
		un.setUn01Nombres(poliza.getTbSaAsegurado().getNombres());
		un.setUn01Nacionalidad(poliza.getTbSaAsegurado().getNacionalidad());
		un.setUn01Genero(poliza.getTbSaAsegurado().getSexo());
		un.setUn01Estadocivil(poliza.getTbSaAsegurado().getEstadocivil().getEstadocivilcod());
		un.setUn01Fecnac(poliza.getTbSaAsegurado().getFechaNacimiento());
		un.setUn01Lugarnacimiento(
				poliza.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getProvincia().getProvincianom().trim() + "/"
						+ poliza.getTbSaAsegurado().getparroquiaNacimiento().getCanton().getCantonnom().trim() + "/"
						+ poliza.getTbSaAsegurado().getparroquiaNacimiento().getParroquianom().trim());
		un.setUn01Direcdomicilio(
				poliza.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getProvincia().getProvincianom().trim() + "/"
						+ poliza.getTbSaAsegurado().getparroquiaDomicilio().getCanton().getCantonnom().trim() + "/"
						+ poliza.getTbSaAsegurado().getparroquiaDomicilio().getParroquianom().trim());
		un.setUn01Calleref(poliza.getTbSaAsegurado().getDireccion());
		un.setUn01Celular(poliza.getTbSaAsegurado().getTelefonoCelular());
		un.setUn01Telefono(StringUtils.isNotBlank(poliza.getTbSaAsegurado().getTelefonoConvencional())?
				poliza.getTbSaAsegurado().getTelefonoConvencional():"         0               ");
		un.setUn01Ingresoanual(poliza.getTbSaAsegurado().getIngresoAnual());
		un.setUn01Mail(poliza.getTbSaAsegurado().getCorreo());
		un.setUn01Actividadeco(poliza.getTbSaAsegurado().getActeco().getAeccod());
		

		// predio
		un.setUn01Provincia(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid());
		un.setUn01Canton(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid().trim()
				.concat(poliza.getTbPaPredio().getParroquia().getId().getCantonid().trim()));
		un.setUn01Parroquia(poliza.getTbPaPredio().getParroquia().getId().getProvinciaid().trim()
				.concat(poliza.getTbPaPredio().getParroquia().getId().getCantonid().trim())
				.concat(poliza.getTbPaPredio().getParroquia().getId().getParroquiaid().trim()));
		un.setUn01Recinto(poliza.getTbPaPredio().getRecinto());
		un.setUn01Condicionpredio(poliza.getTbPaPredio().getCondicionpredio().getCondicionprediocod());
		un.setUn01Otracondpredio(poliza.getTbPaPredio().getCondicion());
		un.setUn01LatDecimal(poliza.getTbPaPredio().getCoordenadaY() != null?poliza.getTbPaPredio().getCoordenadaY().toString():" ");
		un.setUn01LongDecimal(poliza.getTbPaPredio().getCoordenadaX() != null?poliza.getTbPaPredio().getCoordenadaX().toString():" ");
		un.setUn01Numerolote("S/N");

		// caracteristicas del cultivo
		un.setUn01Codcultivo(poliza.getTbPaCaracteristicaCultivo().getRamoplan().getId().getRamoplanid());
		un.setUn01Variedad(poliza.getTbPaCaracteristicaCultivo().getVariedad());
		// un.setUn01Codvariedadcult(poliza.getTbPaCaracteristicaCultivo().getVariedad());
		un.setUn01Superficieasegurar(poliza.getTbPaCaracteristicaCultivo().getSuperficieAsegurada());
		un.setUn01Costodirectprod(poliza.getTbPaCaracteristicaCultivo().getCostoHectarea());
		un.setUn01Montoasegurado(poliza.getTbPaCaracteristicaCultivo().getMontoAsegurado());
		un.setUn01Fechatentativasiembra(poliza.getTbPaCaracteristicaCultivo().getFechaSiembra());
		un.setUn01Tiposemilla(poliza.getTbPaCaracteristicaCultivo().getTiposemilla() != null?
				BigDecimal.valueOf(Double.valueOf(poliza.getTbPaCaracteristicaCultivo().getTiposemilla().getTiposemillacod())):
					BigDecimal.valueOf(0)	);
		un.setUn01Asistecnica(poliza.getTbPaCaracteristicaCultivo().getAsistenciaTecnica());
		un.setUn01Disponeriego(poliza.getTbPaCaracteristicaCultivo().getRiego());
		un.setUn01Tiporiego(poliza.getTbPaCaracteristicaCultivo().getRiegoBean().getRiegocod());

		// canal de solicitud del seguro
		un.setUn01Endoso(poliza.getValorEndoso() != null?poliza.getValorEndoso():BigDecimal.valueOf(0));

		un.setUn01Codvariedadcult(" ");
		  un.setUn01Codigofacilitador("1025");
		  un.setUn01Codigosucursal("1                   ");
		  un.setUn01Anulado("N");
		  un.setUn01Codigotramite(poliza.getNumeroTramite());
		  un.setUn01Apenom(poliza.getNombreEjecutivo());
		  un.setUn01Costoestablecimientoculti(BigDecimal.valueOf(0));
		  un.setUn01Costomantenimiento(BigDecimal.valueOf(0));
		  un.setUn01Costonuevo(BigDecimal.valueOf(0));
		  un.setUn01Diferenciamonto("NO");
		  un.setUn01Casacomercialid(null);
		  un.setUn01Dltfch(new Date());
		  un.setUn01Dltusr("                    ");
		  un.setUn01Emisionobs("   ");
		  un.setUn01Emisionqbe("   ");
		  un.setUn01Enviadoicore(null);
		  un.setUn01Estado(BigDecimal.valueOf(0));
		  un.setUn01Estadosolicitud(BigDecimal.valueOf(2));
		  un.setUn01EstadoUn02(BigDecimal.valueOf(0));
		  un.setUn01Estcalifinterna("AA");
		  un.setUn01Factura("          ");
		  un.setUn01FchUn02(new Date());
		  un.setUn01Fechaaprobadoqbe(new Date());
		  un.setUn01Fechaenvioemiqbe(new Date());
		  un.setUn01Fechaenvioqbe(new Date());
		  un.setUn01Fecharecibidoqbe(new Date());
		  un.setUn01Fechasiembracultivo(new Date());
		  un.setUn01Fecsol(new Date());
		  //un.setUn01Id(un01Id); 
		  un.setUn01Indmigraorcl(null);
		  un.setUn01Insfch(new Date()); 
		  un.setUn01Insusr("    ");
		  un.setUn01Montoaprobadoqbe(BigDecimal.valueOf(0));
		  un.setUn01Montorecomendadoqbe(BigDecimal.valueOf(0));		  
		  un.setUn01Obscalifinterna(null);
		  un.setUn01ObsUn02("          ");
		  un.setUn01Otroriego("          ");
		  un.setUn01Pagado(" "); 
		  un.setUn01Pago(BigDecimal.valueOf(0));
		  un.setUn01Paquetetecnologico("        "); 
		  un.setUn01Pep(" ");
		  un.setUn01Plazovenc(BigDecimal.valueOf(0)); 
		  un.setUn01Poliza("      ");
		  un.setUn01Primasugeridaqbe(BigDecimal.valueOf(0));
		  un.setUn01Rea(" "); 
		  un.setUn01Reanautoriz("  ");
		  un.setUn01Reaobs("  "); 
		  un.setUn01Reaproveeqbe("  ");
		  un.setUn01Reasts("  "); 
		  un.setUn01Reproceso("  ");
		  un.setUn01Ruc("                         "); 
		  un.setUn01Superftotcult(BigDecimal.valueOf(0));
		  un.setUn01Sts(" ");
		  un.setUn01Tipocanal(BigDecimal.valueOf(0));
		  un.setUn01Variedadnuevo("  ");
		 un.setUn01Solicitudqbe(" ");
		 un.setUn01Emisionqbe(" ");
		 un.setUn01Fuenteingreso("  ");
		 un.setUn01Valorsubsidio(BigDecimal.valueOf(0));
		 un.setUn01Xmlinsercion(null);
		 un.setUn01Casacomercialid(null);
		 un.setUn01Cicloid(null);
		 un.setUn01Reaproveeqbe(null);
		 un.setUn01Un02Descuento(null);
		 un.setUn01Un02Aporsubbanco(BigDecimal.valueOf(0));
		 un.setUn01Un02Codaseg(" ");
		 un.setUn01Un02Deremi(BigDecimal.valueOf(0));
		 un.setUn01Un02Descuento(BigDecimal.valueOf(0));
		 un.setUn01Un02Fchdlt(new Date());
		 un.setUn01Un02Fchemi(new Date());
		 un.setUn01Un02Fchfin(new Date());
		 un.setUn01Un02Fchins(new Date());
		 un.setUn01Un02Fchudp(new Date());
		 un.setUn01Un02Iva(BigDecimal.valueOf(0));
		 un.setUn01Un02Observacion(" ");
		 un.setUn01Un02Primasub(BigDecimal.valueOf(0));
		 un.setUn01Un02Primbruta(BigDecimal.valueOf(0));
		 un.setUn01Un02Primfin(BigDecimal.valueOf(0));
		 un.setUn01Un02Primneta(BigDecimal.valueOf(0));
		 un.setUn01Un02Recsc(BigDecimal.valueOf(0));
		 un.setUn01Un02Segsoccamp(BigDecimal.valueOf(0));
		 un.setUn01Un02Tasa(BigDecimal.valueOf(0));
		 un.setUn01Un02Totimp(BigDecimal.valueOf(0));
		 un.setUn01Un02Usrdlt(" ");
		 un.setUn01Un02Usrins(" ");
		 un.setUn01Un02Usrudp(" ");
		 un.setUn01Updfch(new Date());
		 un.setUn01Updusr(" ");
		 un.setUn01Ss02(BigDecimal.valueOf(0));
		 un.setUn01Ss04Estatus(BigDecimal.valueOf(0));
		 un.setUn01Ss04Fch(new Date());
		 un.setUn01Ss04Opernum(" ");
		 un.setUn01Ss04Totalpag(BigDecimal.valueOf(0));
		 un.setUn01Certificado(BigDecimal.valueOf(0));
		 un.setUn01Variedad(" ");
		 un.setUn01Solicitudobs(" ");
		 un.setUn01Otracondpredio(" ");
		 
		this.un01Repository.add(un);
	}

	private String generateCodigo() throws DefException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String codigo = SiniestroAgricolaConstantes.CODIGO_POLIZA;
		codigo = codigo.concat(String.valueOf(cal.get(Calendar.YEAR)));
		codigo = codigo.concat("-");
		codigo = codigo.concat(StringUtils.leftPad(this.solicitudPolizaRepository.generateCodigo(String.valueOf(cal.get(Calendar.YEAR))).toString(), 7, '0'));
		return codigo;

	}

	private String generateNumeroTramite(Ramocanal canal) throws DefException {
		/*
		 * TbPaCanalSecuencia seq =
		 * this.canalSecuenciaRepository.findByCanalId(canal.getId()); if(seq == null) {
		 * throw new SegSucreException(Constantes.
		 * ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR LA INFORMACION DE LA SECUENCIA PARA ESE CANAL"
		 * ); }
		 */
		
		BigDecimal seq = this.solicitudPolizaRepository.generateNumeroTramite(canal.getId().getCanalid().trim());
		String secuencial = seq.toString();
		if(seq.compareTo(BigDecimal.valueOf(Long.valueOf("1")))==0) {
			TbPaCanalSecuencia canalSeq =this.canalSecuenciaRepository.findByCanalId(canal.getId());
			secuencial = canalSeq.getSeqNumeroTramite();
		}
		String numeroTramite = canal.getId().getCanalid().trim();
		numeroTramite = numeroTramite.concat("-");
		numeroTramite = numeroTramite
				.concat(StringUtils.leftPad(secuencial, 7, '0'));
		log.info("numero tramite=================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.info(numeroTramite);
		log.info(seq.toString());
		return numeroTramite;
	}

	/**
	 * valida que las referencias de la poliza esten completas
	 * 
	 * @param entidad
	 * @author Jhon Romero
	 * @throws DefException
	 */
	private void validatePoliza(TbPaSolicitudPoliza entidad) throws DefException {
		List<ConsultaSolicitudPolizaWrapper> ls = solicitudPolizaRepository.findByParams(null, entidad.getCodigo(), null, null, null, null);
		if(ls != null && !ls.isEmpty()) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "CODIGO DE SOLICITUD OCUPADO VUELVA A INTENTAR");
		}
		if (entidad.getRamocanal() == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL CANAL");
		}
		if (entidad.getTbPaCaracteristicaCultivo() == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL CULTIVO");
		}
		if (entidad.getTbPaPredio() == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL PREDIO");
		}
		if (entidad.getTbSaAsegurado() == null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL ASEGURADO");
		}
	}


	public void upDateSolicitudPoliza()  throws DefException{
		this.solicitudPolizaRepository.upDate();
		
	}
}
