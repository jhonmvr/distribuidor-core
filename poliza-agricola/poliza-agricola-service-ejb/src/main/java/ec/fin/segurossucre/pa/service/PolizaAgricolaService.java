package ec.fin.segurossucre.pa.service;
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

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.core.util.main.PaginatedWrapper;
import ec.fin.segurossucre.pa.enums.EstadoSiniestroAgricolaEnum;
import ec.fin.segurossucre.pa.model.Acteco;
import ec.fin.segurossucre.pa.model.Apol;
import ec.fin.segurossucre.pa.model.Canton;
import ec.fin.segurossucre.pa.model.CantonPK;
import ec.fin.segurossucre.pa.model.Condicionpredio;
import ec.fin.segurossucre.pa.model.Estadocivil;
import ec.fin.segurossucre.pa.model.Genero;
import ec.fin.segurossucre.pa.model.Nacionalidad;
import ec.fin.segurossucre.pa.model.Pais;
import ec.fin.segurossucre.pa.model.Parroquia;
import ec.fin.segurossucre.pa.model.ParroquiaPK;
import ec.fin.segurossucre.pa.model.Provincia;
import ec.fin.segurossucre.pa.model.Ramocanal;
import ec.fin.segurossucre.pa.model.RamocanalPK;
import ec.fin.segurossucre.pa.model.Ramoplan;
import ec.fin.segurossucre.pa.model.RamoplanPK;
import ec.fin.segurossucre.pa.model.Riego;
import ec.fin.segurossucre.pa.model.TbPaCanalSecuencia;
import ec.fin.segurossucre.pa.model.TbPaDocumentoPoliza;
import ec.fin.segurossucre.pa.model.TbPaTipoDocumentoPoliza;
import ec.fin.segurossucre.pa.model.TbSaParametro;
import ec.fin.segurossucre.pa.model.TbSaUsuarioCanal;
import ec.fin.segurossucre.pa.model.Tiposemilla;
import ec.fin.segurossucre.pa.model.Un01;
import ec.fin.segurossucre.pa.repository.ActecoRepository;
import ec.fin.segurossucre.pa.repository.ApolRepository;
import ec.fin.segurossucre.pa.repository.CanalRepository;
import ec.fin.segurossucre.pa.repository.CanalSecuenciaRepository;
import ec.fin.segurossucre.pa.repository.CantonRepository;
import ec.fin.segurossucre.pa.repository.CondicionPredioRepository;
import ec.fin.segurossucre.pa.repository.DocumentoPolizaRepository;
import ec.fin.segurossucre.pa.repository.EstadoCivilRepository;
import ec.fin.segurossucre.pa.repository.GeneroRepository;
import ec.fin.segurossucre.pa.repository.NacionalidadRepository;
import ec.fin.segurossucre.pa.repository.PaisRepository;
import ec.fin.segurossucre.pa.repository.ParametroRepository;
import ec.fin.segurossucre.pa.repository.ParroquiaRepository;
import ec.fin.segurossucre.pa.repository.ProvinciaRepository;
import ec.fin.segurossucre.pa.repository.RamoPlanRepository;
import ec.fin.segurossucre.pa.repository.RiegoRepository;
import ec.fin.segurossucre.pa.repository.TipoDocumentoPolizaRepository;
import ec.fin.segurossucre.pa.repository.TipoSemillaRepository;
import ec.fin.segurossucre.pa.repository.Un01Repository;
import ec.fin.segurossucre.pa.repository.UsuarioCanalRepository;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaConstantes;
import ec.fin.segurossucre.pa.wrapper.ApolWrapper;
import ec.fin.segurossucre.pa.wrapper.CatalogosGeneralWrapper;

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
	 * @throws SegSucreException
	 */
	public TbSaParametro findParametroById(Long id) throws SegSucreException {
		try {
			return parametroRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countParametros() throws SegSucreException {
		try {
			return parametroRepository.countAll(TbSaParametro.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<TbSaParametro> findAllParametro(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbSaParametro manageParametro(TbSaParametro send) throws SegSucreException {
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
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (SegSucreException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CausaNegativa " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws SegSucreException
	 */
	public TbSaParametro updateParametro(TbSaParametro send, TbSaParametro persisted) throws SegSucreException {
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

		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CausaNegativa " + e.getMessage());
		}
	}

	public TbSaParametro findByNombre(String nombre) throws SegSucreException {
		try {
			TbSaParametro a = parametroRepository.findByNombre(nombre);
			if (a != null) {
				return a;
			} else {
				throw new SegSucreException(Constantes.ERROR_CODE_READ, "tbsaparametro no encontrada ");

			}

		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "en la busqueda tbsaparametro " + e.getMessage());
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
	 * @throws SegSucreException
	 */
	public Ramocanal findCanalById(RamocanalPK id) throws SegSucreException {
		try {
			return canalRepository.findByIdFixed(id.getRamoid(), id.getCanalid());
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Canal no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countCanal() throws SegSucreException {
		try {
			return canalRepository.countAll(Ramocanal.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Canal no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Ramocanal> findAllCanal(PaginatedWrapper pw) throws SegSucreException {
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

	public List<Ramocanal> findAllCanalByRamo(String ramoId) throws SegSucreException {
		return this.canalRepository.findByRamo(ramoId);
	}

	/**
	 * APOL
	 */

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SegSucreException
	 */
	private ApolWrapper datosPredio(List<Apol> apol) throws SegSucreException {

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
					} catch (SegSucreException e) {
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
	 * @throws SegSucreException
	 */
	public ApolWrapper findApolByNumeroTramite(String numeroTramite) throws SegSucreException {
	
		try {
			ApolWrapper apol = null;
			List<Apol> apoll = apolRepository.findByNumeroTramite(numeroTramite);
			if (apoll == null) {
				log.info("no se encontro datos en apol>>>>>>>>>>>>>");
				throw new SegSucreException(Constantes.ERROR_CODE_READ, "Apol no encontrada ");
			}
			apol = datosPredio(apoll);
			return apol;
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			e.getStackTrace();
			log.info(">>>>>>>>>>>>>>>apol no encontrada<<<<<<<<<<<<<<<<<<<<<<" + e);
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Apol no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public Apol findApolById(Long id) throws SegSucreException {
		try {
			return apolRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Apol no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countApol() throws SegSucreException {
		try {
			return apolRepository.countAll(Apol.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Apol no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Apol> findAllApol(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Provincia findProvinciaById(String id) throws SegSucreException {
		try {
			return provinciaRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Provincia no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countProvincia() throws SegSucreException {
		try {
			return provinciaRepository.countAll(Provincia.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Provincia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Provincia> findAllProvincia(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public List<CatalogosGeneralWrapper> setWrapperCatalaogoGeneral(String catalogo) throws SegSucreException {
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

		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
		return list;
	}

	/**
	 * Metodo que set el nombre del catalogo para el reporte
	 * 
	 * @return NombreCatalogo
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public CatalogosGeneralWrapper setNombreCatalogo(String catalogo) throws SegSucreException {
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
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
	 * @throws SegSucreException
	 */
	public Canton findCantonById(CantonPK id) throws SegSucreException {
		try {
			return cantonRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Canton no encontrada " + e.getMessage());
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
	 * @throws SegSucreException
	 */
	public Acteco findActecoById(String id) throws SegSucreException {
		try {
			return actecoRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countActeco() throws SegSucreException {
		try {
			return actecoRepository.countAll(Acteco.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Acteco no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Acteco> findAllActeco(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Estadocivil findEstadoCivilById(Long id) throws SegSucreException {
		try {
			return estadoCivilRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countEstadoCivil() throws SegSucreException {
		try {
			return estadoCivilRepository.countAll(Estadocivil.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "EstadoCivil no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JHON ROMERO - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Estadocivil> findAllEstadoCivil(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Long countCanton() throws SegSucreException {
		try {
			return cantonRepository.countAll(Canton.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Canton no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Canton> findAllCanton(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public List<Canton> findCantonesByProvincia(String provincia, String order) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Genero findGeneroById(Long id) throws SegSucreException {
		try {
			return generoRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countGenero() throws SegSucreException {
		try {
			return generoRepository.countAll(Genero.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Genero no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Genero> findAllGenero(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Parroquia findParroquiaById(ParroquiaPK id) throws SegSucreException {
		try {
			return this.parroquiaRepository.findByPKFixed(id.getProvinciaid(), id.getCantonid(), id.getParroquiaid());
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Parroquia no encontrada " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param provincia
	 * @param canton
	 * @return
	 * @throws SegSucreException
	 */
	public List<Parroquia> findAllParroquiaByProvinciaCanton(String provincia, String canton, String order)
			throws SegSucreException {
		try {
			return this.parroquiaRepository.findByProvinciaAndCanton(provincia, canton, order);
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Error al cargar parroquias" + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countParroquia() throws SegSucreException {
		try {
			return parroquiaRepository.countAll(Parroquia.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Parroquia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Parroquia> findAllParroquia(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Condicionpredio findCondicionpredioById(Long id) throws SegSucreException {
		try {
			return condicionPredioRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countCondicionpredio() throws SegSucreException {
		try {
			return condicionPredioRepository.countAll(Condicionpredio.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Condicionpredio no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Condicionpredio> findAllCondicionpredio(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Tiposemilla findTiposemillaById(Long id) throws SegSucreException {
		try {
			return tipoSemillaRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countTiposemilla() throws SegSucreException {
		try {
			return tipoSemillaRepository.countAll(Tiposemilla.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Tiposemilla no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Tiposemilla> findAllTiposemilla(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Riego findRiegoById(Long id) throws SegSucreException {
		try {
			return riegoRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countRiego() throws SegSucreException {
		try {
			return riegoRepository.countAll(Riego.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Riego no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Riego> findAllRiego(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Pais findPaisById(String id) throws SegSucreException {
		try {
			return paisRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countPais() throws SegSucreException {
		try {
			return paisRepository.countAll(Pais.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Pais no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Pais> findAllPais(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Ramoplan findRamoplanById(RamoplanPK id) throws SegSucreException {
		try {
			return ramoPlanRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Ramoplan no encontrada " + e.getMessage());
		}
	}

	public Long countRamoPlan() throws SegSucreException {
		try {
			return ramoPlanRepository.countAll(Ramoplan.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "RamoPlan no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Ramoplan> findAllRamoPlan(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public Nacionalidad findNacionalidadById(String id) throws SegSucreException {
		try {
			return nacionalidadRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public Long countNacionalidad() throws SegSucreException {
		try {
			return nacionalidadRepository.countAll(Nacionalidad.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Nacionalidad no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws SegSucreException
	 */
	public List<Nacionalidad> findAllNacionalidad(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbPaDocumentoPoliza findDocumentoPolizaById(Long id) throws SegSucreException {
		try {
			return documentoPolizaRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countDocumentoPoliza() throws SegSucreException {
		try {
			return documentoPolizaRepository.countAll(TbPaDocumentoPoliza.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ,
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
	 * @throws SegSucreException
	 */
	public List<TbPaDocumentoPoliza> findAllDocumentoPoliza(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbPaDocumentoPoliza manageDocumentoPoliza(TbPaDocumentoPoliza send) throws SegSucreException {
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
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (SegSucreException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando TbPaDocumentoPoliza " + e.getMessage());
		}
	}
	
	public void deleteStorage(TbPaDocumentoPoliza doc) throws SegSucreException {
		try {
			TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
			if(p==null) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
			}
			Path path = Paths.get(p.getValor().concat(String.valueOf(doc.getTbPaSolicitudPoliza().getId()).concat(String.valueOf(doc.getTbPaTipoDocumentoPoliza().getId()))));
			Files.write(path, doc.getArchivo(), StandardOpenOption.DELETE_ON_CLOSE);
		} catch (SegSucreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR ARCHIVO EN STORAGE");
		}
	}
	public void guardarFileStorage(TbPaDocumentoPoliza doc) throws IOException, SegSucreException {
		TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
		if(p==null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
		}
		Path path = Paths.get(p.getValor().concat(String.valueOf(doc.getTbPaSolicitudPoliza().getId()).concat(String.valueOf(doc.getTbPaTipoDocumentoPoliza().getId()))));
		Files.write(path, doc.getArchivo(), StandardOpenOption.CREATE_NEW);
	}
	
	public TbPaDocumentoPoliza abrirFileStorage(TbPaDocumentoPoliza doc) throws IOException, SegSucreException {
		TbSaParametro p = this.parametroRepository.findByNombre(SiniestroAgricolaConstantes.STORAGE_PATH);
		if(p==null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR STORAGE_PATH EN PARAMETROS");
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
	 * @throws SegSucreException
	 */
	public TbPaDocumentoPoliza upLoadDocumentoPoliza(TbPaDocumentoPoliza entidad) throws SegSucreException {
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
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORAMCION DEL DOCUMENTO ");
		}

	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws SegSucreException
	 */
	public TbPaDocumentoPoliza updateDocumentoPoliza(TbPaDocumentoPoliza send, TbPaDocumentoPoliza persisted)
			throws SegSucreException {
		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			return documentoPolizaRepository.update(persisted);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando DocumentoPolizaRepository " + e.getMessage());
		}
	}

	public TbPaDocumentoPoliza downloadDocumentoPoliza(Long idTipoDocumento, Long idPoliza) throws SegSucreException, IOException {
		
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
	 * @throws SegSucreException
	 */
	public TbPaTipoDocumentoPoliza findTipoDocumentoPolizaById(Long id) throws SegSucreException {
		try {
			return tipoDocumentoPolizaRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countTipoDocumentoPoliza() throws SegSucreException {
		try {
			return tipoDocumentoPolizaRepository.countAll(TbPaTipoDocumentoPoliza.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ,
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
	 * @throws SegSucreException
	 */
	public List<TbPaTipoDocumentoPoliza> findAllTipoDocumentoPoliza(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbPaTipoDocumentoPoliza manageTipoDocumentoPoliza(TbPaTipoDocumentoPoliza send) throws SegSucreException {
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
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (SegSucreException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	public List<TbPaTipoDocumentoPoliza> findDocumentoByParams(String tipoDocumento, Long id,
			String tipoPlantilla) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbPaTipoDocumentoPoliza updateTipoDocumentoPoliza(TbPaTipoDocumentoPoliza send,
			TbPaTipoDocumentoPoliza persisted) throws SegSucreException {
		try {
			persisted.setEstado(send.getEstado());
			return tipoDocumentoPolizaRepository.update(persisted);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando TipoDocumentoPolizaRepository " + e.getMessage());
		}
	}
	
	public List<TbPaTipoDocumentoPoliza> findAllDocumentoByParams(PaginatedWrapper pw, String tipoDocumento,
			Long id) throws SegSucreException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.tipoDocumentoPolizaRepository.findAllByParams(tipoDocumento, id, pw.getCurrentPage(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.tipoDocumentoPolizaRepository.findAllByParams(tipoDocumento, id);
		}
	}

	public Long countAllDocumentoByParams(String tipoDocumento, Long id) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbSaUsuarioCanal findUsuarioCanalById(Long id) throws SegSucreException {
		try {
			return usuarioCanalRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}

	public TbSaUsuarioCanal findUsuarioCanalByNombreUsuario(String nombreUsuario) throws SegSucreException {
		try {
			return usuarioCanalRepository.findByUsuario(nombreUsuario);
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countUsuarioCanal() throws SegSucreException {
		try {
			return usuarioCanalRepository.countAll(TbSaUsuarioCanal.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Usuario canton  no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws SegSucreException
	 */
	public List<TbSaUsuarioCanal> findAllUsuarioCanal(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbSaUsuarioCanal manageUsuarioCanal(TbSaUsuarioCanal send) throws SegSucreException {
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
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la InspeccionImagen " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws SegSucreException
	 */
	public TbSaUsuarioCanal updateUsuarioCanal(TbSaUsuarioCanal send, TbSaUsuarioCanal persisted)
			throws SegSucreException {
		try {
			RamocanalPK pk = new RamocanalPK();
			pk.setRamoid(send.getRamocanal().getId().getRamoid());
			pk.setCanalid(send.getRamocanal().getId().getCanalid());
			persisted.setRamocanal(this.canalRepository.findById(pk));
			persisted.setNombreUsuario(send.getNombreUsuario());
			return usuarioCanalRepository.update(persisted);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
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
	 * @throws SegSucreException
	 */
	public TbPaCanalSecuencia findCanalSecuenciaById(Long id) throws SegSucreException {
		try {
			return canalSecuenciaRepository.findById(id);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Usuario canton no encontrada " + e.getMessage());
		}
	}


	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws SegSucreException
	 */
	public Long countCanalSecuencia() throws SegSucreException {
		try {
			return canalSecuenciaRepository.countAll(TbPaCanalSecuencia.class);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Usuario canton  no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author LUIS TAMAYO - Relative Engine
	 * @throws SegSucreException
	 */
	public List<TbPaCanalSecuencia> findAllCanalSecuencia(PaginatedWrapper pw) throws SegSucreException {
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
	 * @throws SegSucreException
	 */
	public TbPaCanalSecuencia manageCanalSecuencia(TbPaCanalSecuencia send) throws SegSucreException {
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
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la InspeccionImagen " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws SegSucreException
	 */
	public TbPaCanalSecuencia updateCanalSecuencia(TbPaCanalSecuencia send, TbPaCanalSecuencia persisted)
			throws SegSucreException {
		try {
			persisted.setFechaActualizacion(new Date());
			persisted.setRamocanal(send.getRamocanal());
			persisted.setSeqNumeroTramite(send.getSeqNumeroTramite());
			persisted.setUsuarioActualizacion(send.getUsuarioActualizacion());			
			return canalSecuenciaRepository.update(persisted);
		} catch (SegSucreException e) {
			throw e;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando InspeccionImagen " + e.getMessage());
		}
	}
}
