package ec.com.def.pa.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.PaginatedWrapper;
import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;
import ec.com.def.pa.model.Acteco;
import ec.com.def.pa.model.Apol;
import ec.com.def.pa.model.Canton;
import ec.com.def.pa.model.CantonPK;
import ec.com.def.pa.model.Condicionpredio;
import ec.com.def.pa.model.Estadocivil;
import ec.com.def.pa.model.Genero;
import ec.com.def.pa.model.Nacionalidad;
import ec.com.def.pa.model.Pais;
import ec.com.def.pa.model.Parroquia;
import ec.com.def.pa.model.ParroquiaPK;
import ec.com.def.pa.model.Provincia;
import ec.com.def.pa.model.Ramocanal;
import ec.com.def.pa.model.RamocanalPK;
import ec.com.def.pa.model.Ramoplan;
import ec.com.def.pa.model.RamoplanPK;
import ec.com.def.pa.model.Riego;
import ec.com.def.pa.model.TbPaCanalSecuencia;
import ec.com.def.pa.model.TbPaDocumentoPoliza;
import ec.com.def.pa.model.TbPaTipoDocumentoPoliza;
import ec.com.def.pa.model.TbSaParametro;
import ec.com.def.pa.model.TbSaUsuarioCanal;
import ec.com.def.pa.model.Tiposemilla;
import ec.com.def.pa.model.Un01;
import ec.com.def.pa.repository.ActecoRepository;
import ec.com.def.pa.repository.ApolRepository;
import ec.com.def.pa.repository.CanalRepository;
import ec.com.def.pa.repository.CanalSecuenciaRepository;
import ec.com.def.pa.repository.CantonRepository;
import ec.com.def.pa.repository.CondicionPredioRepository;
import ec.com.def.pa.repository.DocumentoPolizaRepository;
import ec.com.def.pa.repository.EstadoCivilRepository;
import ec.com.def.pa.repository.GeneroRepository;
import ec.com.def.pa.repository.NacionalidadRepository;
import ec.com.def.pa.repository.PaisRepository;
import ec.com.def.pa.repository.ParametroRepository;
import ec.com.def.pa.repository.ParroquiaRepository;
import ec.com.def.pa.repository.ProvinciaRepository;
import ec.com.def.pa.repository.RamoPlanRepository;
import ec.com.def.pa.repository.RiegoRepository;
import ec.com.def.pa.repository.TipoDocumentoPolizaRepository;
import ec.com.def.pa.repository.TipoSemillaRepository;
import ec.com.def.pa.repository.Un01Repository;
import ec.com.def.pa.repository.UsuarioCanalRepository;
import ec.com.def.pa.util.SiniestroAgricolaConstantes;
import ec.com.def.pa.wrapper.ApolWrapper;
import ec.com.def.pa.wrapper.CatalogosGeneralWrapper;

@Stateless
public class PolizaAgricolaService {
	static final String PROVINCIA = "PROVINCIA";
	static final String CANTON = "CANTON";
	static final String PARROQUIA = "PARROQUIA";
	static final String RAMO_PLAN = "LISTA DE CULTIVOS";
	static final String GENERO = "GENERO";
	static final String ESTADO_CIVIL = "ESTADO CIVIL";
	static final String NACIONALIDAD = "NACIONALIDAD";
	static final String ECONOMICA = "ACTIVIDAD ECONOMICA";
	static final String CONDICION_PREDIO = "CONDICION PREDIO";
	static final String TIPO_SEMILLA = "TIPO SEMILLA";
	static final String RIEGO = "RIEGO";

	static final String CANTONN = "CANTÓN";
	static final String GENERON = "GÉNERO";
	static final String ECONOMICAN = "ACTIVIDAD ECONÓMICA";
	static final String CONDICION_PREDION = "CONDICIÓN PREDIO";

	@Inject
	Logger log;
	@Inject
	private ApolRepository apolRepository;
	@Inject
	private Un01Repository un01Repository;
	@Inject
	private ProvinciaRepository provinciaRepository;
	@Inject
	private CantonRepository cantonRepository;
	@Inject
	private ParroquiaRepository parroquiaRepository;
	@Inject
	private ActecoRepository actecoRepository;
	@Inject
	private EstadoCivilRepository estadoCivilRepository;
	@Inject
	private GeneroRepository generoRepository;
	@Inject
	private CondicionPredioRepository condicionPredioRepository;
	@Inject
	private TipoSemillaRepository tipoSemillaRepository;
	@Inject
	private RiegoRepository riegoRepository;
	@Inject
	private PaisRepository paisRepository;
	@Inject
	private RamoPlanRepository ramoPlanRepository;
	@Inject
	private NacionalidadRepository nacionalidadRepository;
	@Inject
	private CanalRepository canalRepository;
	@Inject
	private DocumentoPolizaRepository documentoPolizaRepository;
	@Inject
	private TipoDocumentoPolizaRepository tipoDocumentoPolizaRepository;
	@Inject
	private ParametroRepository parametroRepository;
	@Inject
	private UsuarioCanalRepository usuarioCanalRepository;
	
	@Inject
	private CanalSecuenciaRepository canalSecuenciaRepository;
	/**
	 * PARAMETRO
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public TbSaParametro findParametroById(Long id) throws DefException {
		try {
			return parametroRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countParametros() throws DefException {
		try {
			return parametroRepository.countAll(TbSaParametro.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<TbSaParametro> findAllParametro(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.parametroRepository.findAll(TbSaParametro.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.parametroRepository.findAll(TbSaParametro.class, pw.getCurrentPage(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.parametroRepository.findAll(TbSaParametro.class, pw.getSortFields(),
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
	public TbSaParametro manageParametro(TbSaParametro send) throws DefException {
		try {

			TbSaParametro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findParametroById(send.getId());
				persisted = this.updateParametro(send, persisted);
				// parametrosSingleton.setParametros( this.parametroRepository.findAll(
				// TbSaParametro.class ) );
				return persisted;
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				// send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				persisted = parametroRepository.add(send);
				// parametrosSingleton.setParametros( this.parametroRepository.findAll(
				// TbSaParametro.class ) );
				return persisted;
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CausaNegativa " + e.getMessage());
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
	public TbSaParametro updateParametro(TbSaParametro send, TbSaParametro persisted) throws DefException {
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
			return parametroRepository.update(persisted);

		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CausaNegativa " + e.getMessage());
		}
	}

	public TbSaParametro findByNombre(String nombre) throws DefException {
		try {
			TbSaParametro a = parametroRepository.findByNombre(nombre);
			if (a != null) {
				return a;
			} else {
				throw new DefException(Constantes.ERROR_CODE_READ, "tbsaparametro no encontrada ");

			}

		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "en la busqueda tbsaparametro " + e.getMessage());
		}
	}

	/**
	 * CANAL
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public Ramocanal findCanalById(RamocanalPK id) throws DefException {
		try {
			return canalRepository.findByIdFixed(id.getRamoid(), id.getCanalid());
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Canal no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countCanal() throws DefException {
		try {
			return canalRepository.countAll(Ramocanal.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Canal no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public List<Ramocanal> findAllCanal(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.canalRepository.findAll(Ramocanal.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.canalRepository.findAll(Ramocanal.class, pw.getCurrentPage(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.canalRepository.findAll(Ramocanal.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	public List<Ramocanal> findAllCanalByRamo(String ramoId) throws DefException {
		return this.canalRepository.findByRamo(ramoId);
	}

	/**
	 * APOL
	 */

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DefException
	 */
	private ApolWrapper datosPredio(List<Apol> apol) throws DefException {

		log.info("entra en DatosPredio===>>>>");
		log.info("entra en apol===>>>>  " + apol);

		try {
			List<Un01> un01s = null;
			Un01 uno = null;
			Apol tmp = null;
			ApolWrapper awL = null;
			if (apol != null && !apol.isEmpty()) {
				tmp = apol.get(0);
				if (tmp.getAwparroquia() != null && !tmp.getAwparroquia().trim().isEmpty()) {
					un01s = null;
					awL = new ApolWrapper(tmp);
					awL.setRamoCanalId(tmp.getAwcanal().trim());
					awL.setRamoPlanId(tmp.getAwtplan().trim());
					return awL;
				} else {
					try {
						un01s = un01Repository.findByNumeroTramite(tmp.getAwreferext().trim(), tmp.getAwpzgfem());
						if (un01s != null && !un01s.isEmpty()) {
							log.info("exiye datos en apol y un01o===>>>>");
							uno = un01s.get(0);
						}
					} catch (DefException e) {
						log.info("no se encuentra Un01 para el tramite:" + apol.get(0).getAwreferext());
						un01s = null;
					}
					awL = new ApolWrapper(tmp, uno);
					awL.setRamoCanalId(tmp.getAwcanal().trim());
					awL.setRamoPlanId(tmp.getAwtplan().trim());
					return awL;
				}
			} else {
				awL = new ApolWrapper(tmp, uno);
				awL.setRamoCanalId(null);
				awL.setRamoPlanId(null);
				return awL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo de busqueda por numero de Tramite
	 * 
	 * @param numeroTramite
	 * @return List<Apol>
	 * @throws DefException
	 */
	public ApolWrapper findApolByNumeroTramite(String numeroTramite) throws DefException {
	
		try {
			ApolWrapper apol = null;
			List<Apol> apoll = apolRepository.findByNumeroTramite(numeroTramite);
			if (apoll == null) {
				log.info("no se encontro datos en apol>>>>>>>>>>>>>");
				throw new DefException(Constantes.ERROR_CODE_READ, "Apol no encontrada ");
			}
			apol = datosPredio(apoll);
			return apol;
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			e.getStackTrace();
			log.info(">>>>>>>>>>>>>>>apol no encontrada<<<<<<<<<<<<<<<<<<<<<<" + e);
			throw new DefException(Constantes.ERROR_CODE_READ, "Apol no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Apol findApolById(Long id) throws DefException {
		try {
			return apolRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Apol no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countApol() throws DefException {
		try {
			return apolRepository.countAll(Apol.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Apol no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Apol> findAllApol(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.apolRepository.findAll(Apol.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.apolRepository.findAll(Apol.class, pw.getCurrentPage(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.apolRepository.findAll(Apol.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * PROVINCIA
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Provincia findProvinciaById(String id) throws DefException {
		try {
			return provinciaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Provincia no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countProvincia() throws DefException {
		try {
			return provinciaRepository.countAll(Provincia.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Provincia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Provincia> findAllProvincia(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.provinciaRepository.findAll(Provincia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.provinciaRepository.findAll(Provincia.class, pw.getCurrentPage(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.provinciaRepository.findAll(Provincia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que agrega las listas de los catalogos en el wrapper
	 * CatalogosGeneralWrapper
	 * 
	 * @return CatalogosGeneralWrapper
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<CatalogosGeneralWrapper> setWrapperCatalaogoGeneral(String catalogo) throws DefException {
		List<CatalogosGeneralWrapper> list = new ArrayList<>();
		try {
			if (catalogo.equals(PROVINCIA)) {
				List<Provincia> provincias = this.provinciaRepository.findAll(Provincia.class);
				provincias.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getProvinciaid());
					c.setDescripcion(p.getProvincianom());
					log.info("=========>insertar report data " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(CANTON)) {
				List<Canton> cantones = this.cantonRepository.findAll(Canton.class);
				cantones.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getProvincia().getProvinciaid().trim() + p.getId().getCantonid().trim());
					c.setDescripcion(p.getCantonnom());
					log.info("=========>insertar report data canton " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(PARROQUIA)) {
				List<Parroquia> parroquias = this.parroquiaRepository.findAll(Parroquia.class);
				parroquias.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getId().getProvinciaid().trim() + p.getId().getCantonid().trim()
							+ p.getId().getParroquiaid());
					c.setDescripcion(p.getParroquianom());
					log.info("=========>insertar report data parroquia " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(RAMO_PLAN)) {
				List<Ramoplan> ramoPlan = this.ramoPlanRepository.findAll(Ramoplan.class);
				ramoPlan.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getId().getRamoplanid());
					c.setDescripcion(p.getRamoplannombre());
					log.info("=========>insertar report data parroquia " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(GENERO)) {
				List<Genero> genero = this.generoRepository.findAll(Genero.class);
				genero.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getGenerocod());
					c.setDescripcion(p.getGenerodes());
					log.info("=========>insertar report data genero " + c.getCodigo());
					list.add(c);
				});

			} else if (catalogo.equals(ECONOMICA)) {
				List<Acteco> actividadEconomica = this.actecoRepository.findAll(Acteco.class);
				actividadEconomica.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getAeccod());
					c.setDescripcion(p.getAecdes());
					log.info("=========>insertar report data actividad economica " + c.getCodigo());
					list.add(c);
				});

			} else if (catalogo.equals(CONDICION_PREDIO)) {
				List<Condicionpredio> condicionPredio = this.condicionPredioRepository.findAll(Condicionpredio.class);
				condicionPredio.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getCondicionprediocod());
					c.setDescripcion(p.getCondicionprediodes());
					log.info("=========>insertar report data actividad condicion predio " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(TIPO_SEMILLA)) {
				List<Tiposemilla> tipoSemilla = this.tipoSemillaRepository.findAll(Tiposemilla.class);
				tipoSemilla.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getTiposemillacod());
					c.setDescripcion(p.getTiposemillades());
					log.info("=========>insertar report data tipo semilla " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(RIEGO)) {
				List<Riego> riego = this.riegoRepository.findAll(Riego.class);
				riego.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getRiegocod());
					c.setDescripcion(p.getRiegodes());
					log.info("=========>insertar report data riego " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(NACIONALIDAD)) {
				List<Nacionalidad> pais = this.nacionalidadRepository.findAll(Nacionalidad.class);
				pais.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getnacionalidadid());
					c.setDescripcion(p.getNacionalidaddes());
					log.info("=========>insertar report data pais " + c.getCodigo());
					list.add(c);
				});
			} else if (catalogo.equals(ESTADO_CIVIL)) {
				List<Estadocivil> pais = this.estadoCivilRepository.findAll(Estadocivil.class);
				pais.forEach(p -> {
					CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
					c.setCodigo(p.getEstadocivilcod());
					c.setDescripcion(p.getEstadocivildes());
					log.info("=========>insertar report data pais " + c.getCodigo());
					list.add(c);
				});
			}

		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
		return list;
	}

	/**
	 * Metodo que set el nombre del catalogo para el reporte
	 * 
	 * @return NombreCatalogo
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public CatalogosGeneralWrapper setNombreCatalogo(String catalogo) throws DefException {
		try {
			log.info("=========>llega a set catalogo provincia  " + catalogo);
			CatalogosGeneralWrapper c = new CatalogosGeneralWrapper();
			if (catalogo.equals(PROVINCIA)) {
				c.setNombreCatalogo(PROVINCIA);
			} else if (catalogo.equals(CANTON)) {
				c.setNombreCatalogo(CANTONN);
			} else if (catalogo.equals(PARROQUIA)) {
				c.setNombreCatalogo(PARROQUIA);
			} else if (catalogo.equals(RAMO_PLAN)) {
				c.setNombreCatalogo(RAMO_PLAN);
			} else if (catalogo.equals(GENERO)) {
				c.setNombreCatalogo(GENERON);
			} else if (catalogo.equals(ECONOMICA)) {
				c.setNombreCatalogo(ECONOMICAN);
			} else if (catalogo.equals(CONDICION_PREDIO)) {
				c.setNombreCatalogo(CONDICION_PREDION);
			} else if (catalogo.equals(TIPO_SEMILLA)) {
				c.setNombreCatalogo(TIPO_SEMILLA);
			} else if (catalogo.equals(RIEGO)) {
				c.setNombreCatalogo(RIEGO);
			} else if (catalogo.equals(NACIONALIDAD)) {
				c.setNombreCatalogo(NACIONALIDAD);
			} else if (catalogo.equals(ESTADO_CIVIL)) {
				c.setNombreCatalogo(ESTADO_CIVIL);
			}

			log.info("=========>insertar nombre del catalogo " + c.getNombreCatalogo());
			return c;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * CANTON
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Canton findCantonById(CantonPK id) throws DefException {
		try {
			return cantonRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Acteco
	 */
	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public Acteco findActecoById(String id) throws DefException {
		try {
			return actecoRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countActeco() throws DefException {
		try {
			return actecoRepository.countAll(Acteco.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Acteco no encontrado " + e.getMessage());
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
	public List<Acteco> findAllActeco(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.actecoRepository.findAll(Acteco.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.actecoRepository.findAll(Acteco.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.actecoRepository.findAll(Acteco.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * EstadoCivil
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author JHON ROMERO - Relative Engine
	 * @throws DefException
	 */
	public Estadocivil findEstadoCivilById(Long id) throws DefException {
		try {
			return estadoCivilRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countEstadoCivil() throws DefException {
		try {
			return estadoCivilRepository.countAll(Estadocivil.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "EstadoCivil no encontrado " + e.getMessage());
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
	public List<Estadocivil> findAllEstadoCivil(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.estadoCivilRepository.findAll(Estadocivil.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.estadoCivilRepository.findAll(Estadocivil.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.estadoCivilRepository.findAll(Estadocivil.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countCanton() throws DefException {
		try {
			return cantonRepository.countAll(Canton.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Canton no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Canton> findAllCanton(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.cantonRepository.findAll(Canton.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cantonRepository.findAll(Canton.class, pw.getCurrentPage(), pw.getPageSize(),
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
	 * @throws DefException
	 */
	public List<Canton> findCantonesByProvincia(String provincia, String order) throws DefException {
		return this.cantonRepository.findByProvincia(provincia, order);
	}

	/**
	 * Genero
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Genero findGeneroById(Long id) throws DefException {
		try {
			return generoRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countGenero() throws DefException {
		try {
			return generoRepository.countAll(Genero.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Genero no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Genero> findAllGenero(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.generoRepository.findAll(Genero.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.generoRepository.findAll(Genero.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.generoRepository.findAll(Genero.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * PARROQUIA
	 */
	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Parroquia findParroquiaById(ParroquiaPK id) throws DefException {
		try {
			return this.parroquiaRepository.findByPKFixed(id.getProvinciaid(), id.getCantonid(), id.getParroquiaid());
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Parroquia no encontrada " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param provincia
	 * @param canton
	 * @return
	 * @throws DefException
	 */
	public List<Parroquia> findAllParroquiaByProvinciaCanton(String provincia, String canton, String order)
			throws DefException {
		try {
			return this.parroquiaRepository.findByProvinciaAndCanton(provincia, canton, order);
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Error al cargar parroquias" + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countParroquia() throws DefException {
		try {
			return parroquiaRepository.countAll(Parroquia.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Parroquia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Parroquia> findAllParroquia(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.parroquiaRepository.findAll(Parroquia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.parroquiaRepository.findAll(Parroquia.class, pw.getCurrentPage(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.parroquiaRepository.findAll(Parroquia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Condicionpredio
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Condicionpredio findCondicionpredioById(Long id) throws DefException {
		try {
			return condicionPredioRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countCondicionpredio() throws DefException {
		try {
			return condicionPredioRepository.countAll(Condicionpredio.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Condicionpredio no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Condicionpredio> findAllCondicionpredio(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.condicionPredioRepository.findAll(Condicionpredio.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.condicionPredioRepository.findAll(Condicionpredio.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.condicionPredioRepository.findAll(Condicionpredio.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Tiposemilla
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Tiposemilla findTiposemillaById(Long id) throws DefException {
		try {
			return tipoSemillaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countTiposemilla() throws DefException {
		try {
			return tipoSemillaRepository.countAll(Tiposemilla.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Tiposemilla no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Tiposemilla> findAllTiposemilla(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.tipoSemillaRepository.findAll(Tiposemilla.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tipoSemillaRepository.findAll(Tiposemilla.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tipoSemillaRepository.findAll(Tiposemilla.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/*
	 * Riego
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Riego findRiegoById(Long id) throws DefException {
		try {
			return riegoRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countRiego() throws DefException {
		try {
			return riegoRepository.countAll(Riego.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Riego no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Riego> findAllRiego(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.riegoRepository.findAll(Riego.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.riegoRepository.findAll(Riego.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.riegoRepository.findAll(Riego.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Pais
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Pais findPaisById(String id) throws DefException {
		try {
			return paisRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countPais() throws DefException {
		try {
			return paisRepository.countAll(Pais.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Pais no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Pais> findAllPais(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.paisRepository.findAll(Pais.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.paisRepository.findAll(Pais.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.paisRepository.findAll(Pais.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * RAMOPLAN
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public Ramoplan findRamoplanById(RamoplanPK id) throws DefException {
		try {
			return ramoPlanRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Ramoplan no encontrada " + e.getMessage());
		}
	}

	public Long countRamoPlan() throws DefException {
		try {
			return ramoPlanRepository.countAll(Ramoplan.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "RamoPlan no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Ramoplan> findAllRamoPlan(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.ramoPlanRepository.findAll(Ramoplan.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.ramoPlanRepository.findAll(Ramoplan.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.ramoPlanRepository.findAll(Ramoplan.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}
	/*
	 * Nacionalidad
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public Nacionalidad findNacionalidadById(String id) throws DefException {
		try {
			return nacionalidadRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countNacionalidad() throws DefException {
		try {
			return nacionalidadRepository.countAll(Nacionalidad.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Nacionalidad no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<Nacionalidad> findAllNacionalidad(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.nacionalidadRepository.findAll(Nacionalidad.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.nacionalidadRepository.findAll(Nacionalidad.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.nacionalidadRepository.findAll(Nacionalidad.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}

	}

	/**
	 * Documento Poliza
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public TbPaDocumentoPoliza findDocumentoPolizaById(Long id) throws DefException {
		try {
			return documentoPolizaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countDocumentoPoliza() throws DefException {
		try {
			return documentoPolizaRepository.countAll(TbPaDocumentoPoliza.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ,
					"DocumentoPolizaRepository no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaDocumentoPoliza> findAllDocumentoPoliza(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.documentoPolizaRepository.findAll(TbPaDocumentoPoliza.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.documentoPolizaRepository.findAll(TbPaDocumentoPoliza.class, pw.getCurrentPage(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.documentoPolizaRepository.findAll(TbPaDocumentoPoliza.class, pw.getSortFields(),
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
	public TbPaDocumentoPoliza manageDocumentoPoliza(TbPaDocumentoPoliza send) throws DefException {
		try {
			log.info("==> entra a manage TbPaDocumentoPoliza");
			TbPaDocumentoPoliza persisted = null;
			guardarFileStorage(send);
			send.setArchivo(null);
			if (send != null && send.getId() != null) {
				persisted = this.findDocumentoPolizaById(send.getId());
				return this.updateDocumentoPoliza(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return documentoPolizaRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando TbPaDocumentoPoliza " + e.getMessage());
		}
	}
	
	public void deleteStorage(TbPaDocumentoPoliza doc) throws DefException {
		try {
			TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
			if(p==null) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
			}
			Path path = Paths.get(p.getValor().concat(String.valueOf(doc.getTbPaSolicitudPoliza().getId()).concat(String.valueOf(doc.getTbPaTipoDocumentoPoliza().getId()))));
			Files.write(path, doc.getArchivo(), StandardOpenOption.DELETE_ON_CLOSE);
		} catch (DefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR ARCHIVO EN STORAGE");
		}
	}
	public void guardarFileStorage(TbPaDocumentoPoliza doc) throws IOException, DefException {
		TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
		if(p==null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
		}
		Path path = Paths.get(p.getValor().concat(String.valueOf(doc.getTbPaSolicitudPoliza().getId()).concat(String.valueOf(doc.getTbPaTipoDocumentoPoliza().getId()))));
		Files.write(path, doc.getArchivo(), StandardOpenOption.CREATE_NEW);
	}
	
	public TbPaDocumentoPoliza abrirFileStorage(TbPaDocumentoPoliza doc) throws IOException, DefException {
		TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
		if(p==null) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
		}
		Path path = Paths.get(p.getValor().concat(String.valueOf(doc.getTbPaSolicitudPoliza().getId()).concat(String.valueOf(doc.getTbPaTipoDocumentoPoliza().getId()))));
		doc.setArchivo(Files.readAllBytes(path));
		return doc;
	}


	/**
	 * crea o actualiza un registro
	 * 
	 * @param entidad
	 * @return
	 * @throws DefException
	 */
	public TbPaDocumentoPoliza upLoadDocumentoPoliza(TbPaDocumentoPoliza entidad) throws DefException {
		if (entidad != null && entidad.getTbPaSolicitudPoliza() != null
				&& entidad.getTbPaTipoDocumentoPoliza() != null) {
			TbPaDocumentoPoliza tmp = this.documentoPolizaRepository.downloadDocumentoPoliza(
					entidad.getTbPaTipoDocumentoPoliza().getId(), entidad.getTbPaSolicitudPoliza().getId());
			if (tmp != null) {
				tmp.setEstado(EstadoSiniestroAgricolaEnum.ACT);
				tmp.setTbPaSolicitudPoliza(entidad.getTbPaSolicitudPoliza());
				tmp.setTbPaTipoDocumentoPoliza(entidad.getTbPaTipoDocumentoPoliza());
				tmp.setArchivo(entidad.getArchivo());
				tmp.setNombreArchivo(entidad.getNombreArchivo());		
				deleteStorage(tmp);
			}else {
				tmp = entidad;
			}
			return this.manageDocumentoPoliza(tmp);
		} else {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORAMCION DEL DOCUMENTO ");
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
	public TbPaDocumentoPoliza updateDocumentoPoliza(TbPaDocumentoPoliza send, TbPaDocumentoPoliza persisted)
			throws DefException {
		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			return documentoPolizaRepository.update(persisted);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando DocumentoPolizaRepository " + e.getMessage());
		}
	}

	public TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza) throws DefException, IOException {
		
		return this.abrirFileStorage(documentoPolizaRepository.downloadDocumentoPoliza(idTipoDocumento, idPoliza));
	}

	/**
	 * Documento Poliza
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontradaa
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public TbPaTipoDocumentoPoliza findTipoDocumentoPolizaById(Long id) throws DefException {
		try {
			return tipoDocumentoPolizaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countTipoDocumentoPoliza() throws DefException {
		try {
			return tipoDocumentoPolizaRepository.countAll(TbPaTipoDocumentoPoliza.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ,
					"TipoDocumentoPolizaRepository no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaTipoDocumentoPoliza> findAllTipoDocumentoPoliza(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.tipoDocumentoPolizaRepository.findAll(TbPaTipoDocumentoPoliza.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tipoDocumentoPolizaRepository.findAll(TbPaTipoDocumentoPoliza.class, pw.getCurrentPage(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tipoDocumentoPolizaRepository.findAll(TbPaTipoDocumentoPoliza.class, pw.getSortFields(),
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
	public TbPaTipoDocumentoPoliza manageTipoDocumentoPoliza(TbPaTipoDocumentoPoliza send) throws DefException {
		try {
			log.info("==> entra a manage TbPaTipoTipoDocumentoPoliza");
			TbPaTipoDocumentoPoliza persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoDocumentoPolizaById(send.getId());
				return this.updateTipoDocumentoPoliza(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoDocumentoPolizaRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	public List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id,
			String tipoPlantilla) throws DefException {
		List<TbPaTipoDocumentoPoliza> tmp;
		tmp = tipoDocumentoPolizaRepository.findDocumentoByParams(tipoDocumento, id, tipoPlantilla);
		return tmp;
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws DefException
	 */
	public TbPaTipoDocumentoPoliza updateTipoDocumentoPoliza(TbPaTipoDocumentoPoliza send,
			TbPaTipoDocumentoPoliza persisted) throws DefException {
		try {
			persisted.setEstado(send.getEstado());
			return tipoDocumentoPolizaRepository.update(persisted);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando TipoDocumentoPolizaRepository " + e.getMessage());
		}
	}
	
	public List<TbPaTipoDocumentoPoliza> findAllDocumentoByParams(PaginatedWrapper pw, String tipoDocumento,
			Long id) throws DefException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.tipoDocumentoPolizaRepository.findAllByParams(tipoDocumento, id, pw.getCurrentPage(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.tipoDocumentoPolizaRepository.findAllByParams(tipoDocumento, id);
		}
	}

	public Long countAllDocumentoByParams(String tipoDocumento, Long id) throws DefException {
		return this.tipoDocumentoPolizaRepository.countAllByParams(tipoDocumento, id);

	}

	/**
	 * USUARIO CANAL
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public TbSaUsuarioCanal findUsuarioCanalById(Long id) throws DefException {
		try {
			return usuarioCanalRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}

	public TbSaUsuarioCanal findUsuarioCanalByNombreUsuario(String nombreUsuario) throws DefException {
		try {
			return usuarioCanalRepository.findByUsuario(nombreUsuario);
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countUsuarioCanal() throws DefException {
		try {
			return usuarioCanalRepository.countAll(TbSaUsuarioCanal.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Usuario canton  no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public List<TbSaUsuarioCanal> findAllUsuarioCanal(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.usuarioCanalRepository.findAll(TbSaUsuarioCanal.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.usuarioCanalRepository.findAll(TbSaUsuarioCanal.class, pw.getCurrentPage(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.usuarioCanalRepository.findAll(TbSaUsuarioCanal.class, pw.getSortFields(),
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
	public TbSaUsuarioCanal manageUsuarioCanal(TbSaUsuarioCanal send) throws DefException {
		try {
			TbSaUsuarioCanal persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findUsuarioCanalById(send.getId());
				return this.updateUsuarioCanal(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				// send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				return usuarioCanalRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la InspeccionImagen " + e.getMessage());
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
	public TbSaUsuarioCanal updateUsuarioCanal(TbSaUsuarioCanal send, TbSaUsuarioCanal persisted)
			throws DefException {
		try {
			RamocanalPK pk = new RamocanalPK();
			pk.setRamoid(send.getRamocanal().getId().getRamoid());
			pk.setCanalid(send.getRamocanal().getId().getCanalid());
			persisted.setRamocanal(this.canalRepository.findById(pk));
			persisted.setNombreUsuario(send.getNombreUsuario());
			return usuarioCanalRepository.update(persisted);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando InspeccionImagen " + e.getMessage());
		}
	}
	
	/**
	 * CANAL SECUENCIA
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public TbPaCanalSecuencia findCanalSecuenciaById(Long id) throws DefException {
		try {
			return canalSecuenciaRepository.findById(id);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}


	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws DefException
	 */
	public Long countCanalSecuencia() throws DefException {
		try {
			return canalSecuenciaRepository.countAll(TbPaCanalSecuencia.class);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_READ, "Usuario canton  no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws DefException
	 */
	public List<TbPaCanalSecuencia> findAllCanalSecuencia(PaginatedWrapper pw) throws DefException {
		if (pw == null) {
			return this.canalSecuenciaRepository.findAll(TbPaCanalSecuencia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.canalSecuenciaRepository.findAll(TbPaCanalSecuencia.class, pw.getCurrentPage(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.canalSecuenciaRepository.findAll(TbPaCanalSecuencia.class, pw.getSortFields(),
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
	public TbPaCanalSecuencia manageCanalSecuencia(TbPaCanalSecuencia send) throws DefException {
		try {
			TbPaCanalSecuencia persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findCanalSecuenciaById(send.getId());
				return this.updateCanalSecuencia(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				return canalSecuenciaRepository.add(send);
			} else {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la InspeccionImagen " + e.getMessage());
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
	public TbPaCanalSecuencia updateCanalSecuencia(TbPaCanalSecuencia send, TbPaCanalSecuencia persisted)
			throws DefException {
		try {
			persisted.setFechaActualizacion(new Date());
			persisted.setRamocanal(send.getRamocanal());
			persisted.setSeqNumeroTramite(send.getSeqNumeroTramite());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());			
			return canalSecuenciaRepository.update(persisted);
		} catch (DefException e) {
			throw e;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando InspeccionImagen " + e.getMessage());
		}
	}
}
