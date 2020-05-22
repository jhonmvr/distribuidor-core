package ec.fin.segurossucre.pa.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

//import ec.fin.segurossucre.sa.websocket.SiniestroWebSocketClient;
import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.model.Acteco;
import ec.fin.segurossucre.pa.model.Condicionpredio;
import ec.fin.segurossucre.pa.model.Estadocivil;
import ec.fin.segurossucre.pa.model.Genero;
import ec.fin.segurossucre.pa.model.Nacionalidad;
import ec.fin.segurossucre.pa.model.Parroquia;
import ec.fin.segurossucre.pa.model.Ramocanal;
import ec.fin.segurossucre.pa.model.Ramoplan;
import ec.fin.segurossucre.pa.model.Riego;
import ec.fin.segurossucre.pa.model.TbPaCaracteristicaCultivo;
import ec.fin.segurossucre.pa.model.TbPaPredio;
import ec.fin.segurossucre.pa.model.TbPaSolicitudPoliza;
import ec.fin.segurossucre.pa.model.TbSaAsegurado;
import ec.fin.segurossucre.pa.model.Tiposemilla;
import ec.fin.segurossucre.pa.repository.ActecoRepository;
import ec.fin.segurossucre.pa.repository.AseguradoRepository;
import ec.fin.segurossucre.pa.repository.CanalRepository;
import ec.fin.segurossucre.pa.repository.CondicionPredioRepository;
import ec.fin.segurossucre.pa.repository.EstadoCivilRepository;
import ec.fin.segurossucre.pa.repository.GeneroRepository;
import ec.fin.segurossucre.pa.repository.NacionalidadRepository;
import ec.fin.segurossucre.pa.repository.ParametroRepository;
import ec.fin.segurossucre.pa.repository.ParroquiaRepository;
import ec.fin.segurossucre.pa.repository.RamoPlanRepository;
import ec.fin.segurossucre.pa.repository.RiegoRepository;
import ec.fin.segurossucre.pa.repository.TipoSemillaRepository;
import ec.fin.segurossucre.pa.un01.api.Un01ApiClient;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaConstantes;
import ec.fin.segurossucre.pa.util.SiniestroAgricolaUtils;
import ec.fin.segurossucre.pa.wrapper.CargarSolicitudWrapper;

@Stateless
public class CargaMasivaPolizaService {

	@Inject
	Logger log;

	@Inject
	SolicitudPolizaService solicitudPolizaService;

	@Inject
	AseguradoRepository aseguradoRepository;
	@Inject
	ActecoRepository actecoRepository;
	@Inject
	EstadoCivilRepository estadoCivilRepository;
	@Inject
	NacionalidadRepository nacionalidadRepository;
	@Inject
	ParroquiaRepository parroquiaRepository;
	@Inject
	GeneroRepository generoRepository;
	@Inject
	CondicionPredioRepository condicionPredioRepository;
	@Inject
	TipoSemillaRepository tiposemillaRepository;
	@Inject
	RiegoRepository riegoRepository;
	@Inject
	RamoPlanRepository ramoplanRepository;
	@Inject
	CanalRepository canalRepository;
	@Inject
	ParametroRepository parametroRepository;

	public TbPaSolicitudPoliza cargaMasiva(CargarSolicitudWrapper poliza, String canal, String telefonoContacto,
			String nombreEjecutivo) throws SegSucreException, UnsupportedEncodingException {
		log.info("==================================================================>>>>>>>>>>>>>>>>>>");
		log.info(poliza.toString());
		log.info("==================================================================>>>>>>>>>>>>>>>>>>");
		TbPaSolicitudPoliza p = solicitudPolizaService.guardarPoliza(buildPoliza(poliza, canal, telefonoContacto, nombreEjecutivo));
		return p;
		
	}

	public TbPaSolicitudPoliza buildPoliza(CargarSolicitudWrapper poliza, String canal, String telefonoContacto,
			String nombreEjecutivo) throws SegSucreException {
		TbPaSolicitudPoliza p = new TbPaSolicitudPoliza();
		p.setTbSaAsegurado(buildAsegurado(poliza));
		p.setTbPaPredio(buildPredio(poliza));
		p.setTbPaCaracteristicaCultivo(buildCultivo(poliza));
		p.setAutorizacion(validateSiNo(poliza.getAceptarInformacion()));
		p.setContactoEjecutivo(telefonoContacto);
		p.setNombreEjecutivo(nombreEjecutivo);
		p.setRamocanal(validateCanal(poliza.getCanalContratacion(), canal));
		p.setTieneEndoso(validateSiNo(poliza.getEndosoBeneficiario()));
		if (StringUtils.isNotBlank(p.getTieneEndoso()) && p.getTieneEndoso().equalsIgnoreCase("S")) {
			p.setValorEndoso(StringUtils.isNotBlank(poliza.getValorEndoso())
					? BigDecimal.valueOf(Double.valueOf(poliza.getValorEndoso()))
					: null);
			if (p.getValorEndoso().compareTo(p.getTbPaCaracteristicaCultivo().getMontoAsegurado()) > 0) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
						"EL VALOR DEL ENDOSO NO PUEDE SER MAYOR QUE EL MONDO ASEGURAR");
			}
		} else {
			if (StringUtils.isNotBlank(poliza.getValorEndoso())) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
						"SI NO TIENE ENDOSO EL CAMPO ENDOSO TIENE QUE ESTAR VACIO");
			}
			p.setValorEndoso(null);

		}

		return p;
	}

	public Ramocanal validateCanal(String canal, String canalUsuario) throws SegSucreException {
		Ramocanal c;
		if (StringUtils.isNotBlank(canalUsuario) && canalUsuario.trim().equalsIgnoreCase("1113")) {
			c = this.canalRepository.findByCodigo(canal);
		} else {
			c = this.canalRepository.findByCodigo(canalUsuario);
		}
		if (c == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE ENCONTRAR EL CODIGO DEL CANAL ");
		}
		return c;
	}

	public TbSaAsegurado buildAsegurado(CargarSolicitudWrapper poliza) throws SegSucreException {
		try {
			if (poliza.getDocumentoidentidad().length() == 9) {
				poliza.setDocumentoidentidad("0".concat(poliza.getDocumentoidentidad()));
			}
			if (!SiniestroAgricolaUtils.validadorDeCedula(poliza.getDocumentoidentidad())) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NUMERO DE CEDULA INCORRECTO");
			}
			TbSaAsegurado ase = aseguradoRepository.finByIdentificacion(poliza.getDocumentoidentidad());
			if (ase == null) {
				ase = new TbSaAsegurado();
			}
			ase.setActeco(actividadEconomica(poliza.getActividadEconomica()));
			ase.setAgricultura(validateSiNo(poliza.getAgriculturaActividad()));
			ase.setApellido(poliza.getApellidos());
			ase.setCorreo(poliza.getCorreoElectronico());
			ase.setDireccion(poliza.getCalleD());
			ase.setEstadocivil(validateEstadoCivil(poliza.getEstadoCivil()));
			ase.setFechaNacimiento(SiniestroAgricolaUtils.formatSringToDate(poliza.getFechaN()));
			ase.setIdentificacion(poliza.getDocumentoidentidad());
			ase.setIngresoAnual(BigDecimal.valueOf(Double.valueOf(poliza.getIngresoAnual())));
			ase.setNacionalidad(validarNacionalidad(poliza.getNacionalidad()));
			ase.setNombres(poliza.getNombres());
			ase.setparroquiaDomicilio(
					validateParroquia(poliza.getProvinciaD(), poliza.getCantonD(), poliza.getParroquiaD()));
			ase.setparroquiaNacimiento(
					validateParroquia(poliza.getProvinciaN(), poliza.getCantonN(), poliza.getParroquiaN()));
			ase.setPoliticamenteExpuesta(validateSiNo(poliza.getPersonaPoliticamente()));
			ase.setReferencia(poliza.getReferenciaD());
			ase.setSexo(validateGenero(poliza.getGenero()));
			ase.setTelefonoCelular(poliza.getTelefonoCelular());
			ase.setTelefonoConvencional(poliza.getTelefonoConvencional());
			return ase;
		} catch (SegSucreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR EN LOS DATOS DEL ASEGURADO:" + e.getDetalle());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "INGRESO ANUAL INCORRECTO");
		}

	}

	public String validateGenero(String genero) throws SegSucreException {
		Genero g = generoRepository.findByCodigo(genero);
		if (g == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "GENERO NO VALIDO");
		}
		return g.getGenerocod();
	}

	public Parroquia validateParroquia(String codProvincia, String codCanton, String codParroquia)
			throws SegSucreException {
		if (!StringUtils.leftPad(codProvincia.trim(), 2, '0')
				.equalsIgnoreCase(StringUtils.leftPad(codParroquia.trim(), 6, '0').substring(0, 2))) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "LA PROVINCIA NO CORRESPONDE CON LA PARROQUIA");
		}
		if (!StringUtils.leftPad(codProvincia.trim(), 2, '0')
				.equalsIgnoreCase(StringUtils.leftPad(codCanton.trim(), 4, '0').substring(0,2))) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "LA PROVINCIA NO CORRESPONDE CON EL CANTON");
		}
		if (StringUtils.leftPad(codCanton.trim(), 4, '0')
				.equalsIgnoreCase(StringUtils.leftPad(codParroquia.trim(), 6, '0').substring(0, 4))) {
			Parroquia parro = parroquiaRepository.findByPKFixed(StringUtils.leftPad(codProvincia.trim(), 2, '0'),
					StringUtils.leftPad(codCanton.trim(), 4, '0').substring(2), StringUtils.leftPad(codParroquia.trim(), 6, '0').substring(4));

			if (parro == null) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
						"AL BUSCAR LA UBICACION PROVINCIA - CANTON - PARROQUIA");
			}
			return parro;
		} else {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
					"EL CANTON NO CORRESPONDE CON LA PARROQUIA");
		}

	}

	public String validarNacionalidad(String codigo) throws SegSucreException {
		Nacionalidad n = nacionalidadRepository.findById(codigo);
		if (n == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "CODIGO DE NACIONALIDAD NO ENCONTRADO");
		}
		return n.getnacionalidadid();
	}

	public Estadocivil validateEstadoCivil(String estadoCivil) throws SegSucreException {
		Estadocivil estado = estadoCivilRepository.findByCodigo(estadoCivil);
		if (estado == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "CODIGO DE ESTADO CIVIL NO ENCONTRADO");
		}
		return estado;
	}

	public Acteco actividadEconomica(String actividadEconomica) throws SegSucreException {
		Acteco a = actecoRepository.findByCodigo(actividadEconomica);
		if (a == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "CODIGO DE LA ACTIVIDAD ECONOMICA NO ENCONTRADO");
		}
		return a;
	}

	public String validateSiNo(String validate) throws SegSucreException {
		if (StringUtils.isNotBlank(validate)) {
			if (validate.trim().toUpperCase().equalsIgnoreCase("SI")
					|| validate.trim().toUpperCase().equalsIgnoreCase("S")) {
				return "S";
			} else if (validate.trim().toUpperCase().equalsIgnoreCase("NO")
					|| validate.trim().toUpperCase().equalsIgnoreCase("N")) {
				return "N";
			}
		}

		throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "LA OPCION SOLO PUEDEN SER 'Si' o 'No'");
	}

	public TbPaPredio buildPredio(CargarSolicitudWrapper poliza) throws SegSucreException {
		try {
			TbPaPredio predio;
			predio = new TbPaPredio();
			predio.setAltitud(StringUtils.isNotBlank(poliza.getAltitudPredio())
					? BigDecimal.valueOf(Double.valueOf(poliza.getAltitudPredio()))
					: null);
			predio.setCondicion(poliza.getOtraCondicion());
			predio.setCondicionpredio(validateCondicion(poliza.getCondicionesPredio()));
			predio.setCoordenadaX(StringUtils.isNotBlank(poliza.getCoordenadaX())
					? BigDecimal.valueOf(Double.valueOf(poliza.getCoordenadaX()))
					: null);
			predio.setCoordenadaY(StringUtils.isNotBlank(poliza.getCoordenadaY())
					? BigDecimal.valueOf(Double.valueOf(poliza.getCoordenadaY()))
					: null);
			predio.setParroquia(validateParroquia(poliza.getProvinciaP(), poliza.getCantonP(), poliza.getParroquiaP()));
			predio.setRecinto(poliza.getRecintoP());
			predio.setReferencia(poliza.getReferenciaP());
			return predio;
		} catch (SegSucreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR EN LOS DATOS DEL PREDIO:" + e.getDetalle());
		} catch (NumberFormatException e) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN LOS DATOS NUMERICOS DEL PREDIO:");
		}

	}

	public Condicionpredio validateCondicion(String codCondicion) throws SegSucreException {
		Condicionpredio con = condicionPredioRepository.findByCodigo(codCondicion);
		if (con == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "CONDICION DE PREDIO NO ENCONTRADA");
		}
		return con;
	}

	public TbPaCaracteristicaCultivo buildCultivo(CargarSolicitudWrapper poliza) throws SegSucreException {
		TbPaCaracteristicaCultivo cultivo = new TbPaCaracteristicaCultivo();
		cultivo.setAsistenciaTecnica(validateSiNo(poliza.getDisponeAsistencia()));
		cultivo.setCostoHectarea(StringUtils.isNotBlank(poliza.getCostosHectarea())
				? BigDecimal.valueOf(Double.valueOf(poliza.getCostosHectarea()))
				: null);
		cultivo.setSuperficieAsegurada(StringUtils.isNotBlank(poliza.getSuperficieAsegurar())
				? BigDecimal.valueOf(Double.valueOf(poliza.getSuperficieAsegurar()))
				: null);
		if (cultivo.getCostoHectarea() != null && cultivo.getSuperficieAsegurada() != null) {
			cultivo.setMontoAsegurado(cultivo.getCostoHectarea().multiply(cultivo.getSuperficieAsegurada()));
		}
		cultivo.setFechaSiembra(SiniestroAgricolaUtils.formatSringToDate(poliza.getFechaTentativa()));
		cultivo.setRamoplan(validateCultivo(poliza.getCultivo()));
		cultivo.setRiego(validateSiNo(poliza.getDisponeRiesgo()));
		cultivo.setRiegoBean(validateRiego(poliza.getRiego(), cultivo.getRiego()));
		cultivo.setTiposemilla(validateTipoSemilla(poliza.getTipoSemilla()));
		cultivo.setVariedad(poliza.getVariedad());
		return cultivo;

	}

	public Tiposemilla validateTipoSemilla(String semilla) throws SegSucreException {
		Tiposemilla sem = tiposemillaRepository.findByCodigo(semilla);
		if (sem == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCUENTRA EL TIPO DE SEMILLA");
		}
		return sem;
	}

	public Riego validateRiego(String riego, String disponeRiego) throws SegSucreException {
		if (StringUtils.isNotBlank(disponeRiego) && disponeRiego.equalsIgnoreCase("N")
				&& !riego.equalsIgnoreCase("1")) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "EN TIPO DE RIEGO SELECCIONE SIN RIEGO");
		}
		Riego sem = riegoRepository.findByCodigo(riego);
		if (sem == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCUENTRA EL TIPO DE RIEGO");
		}
		return sem;
	}

	public Ramoplan validateCultivo(String cultivo) throws SegSucreException {
		if (StringUtils.isBlank(cultivo)) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "EL CULTIVO ES OBLIGATORIO");
		}
		Ramoplan sem = ramoplanRepository.findByCodigo(StringUtils.leftPad(cultivo, 2, '0'));
		if (sem == null) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCUENTRA EL CULTIVO");
		}
		return sem;
	}

}
