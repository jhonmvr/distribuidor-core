package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the UN01 database table.
 * 
 */
@Entity
//@NamedQuery(name="Un01.findAll", query="SELECT u FROM Un01 u")
public class Un01 implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UN01_ID_GENERATOR", sequenceName="UN01_ID",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UN01_ID_GENERATOR")
	@Column(name="UN01_ID")
	private Long un01Id;
	
	@Column(name="UN01_ACTIVIDADECO")
	private String un01Actividadeco;

	@Column(name="UN01_ANULADO")
	private String un01Anulado;

	@Column(name="UN01_APELLIDOS")
	private String un01Apellidos;

	@Column(name="UN01_APENOM")
	private String un01Apenom;

	@Column(name="UN01_ASISTECNICA")
	private String un01Asistecnica;

	@Column(name="UN01_CALLEREF")
	private String un01Calleref;

	@Column(name="UN01_CANTON")
	private String un01Canton;

	@Column(name="UN01_CASACOMERCIALID")
	private BigDecimal un01Casacomercialid;

	@Column(name="UN01_CEDULA")
	private String un01Cedula;

	@Column(name="UN01_CELULAR")
	private String un01Celular;

	@Column(name="UN01_CERTIFICADO")
	private BigDecimal un01Certificado;

	@Column(name="UN01_CICLOID")
	private BigDecimal un01Cicloid;

	@Column(name="UN01_CODCULTIVO")
	private String un01Codcultivo;

	@Column(name="UN01_CODIGOFACILITADOR")
	private String un01Codigofacilitador;

	@Column(name="UN01_CODIGOSUCURSAL")
	private String un01Codigosucursal;

	@Column(name="UN01_CODIGOTRAMITE")
	private String un01Codigotramite;

	@Column(name="UN01_CODVARIEDADCULT")
	private String un01Codvariedadcult;

	@Column(name="UN01_CONDICIONPREDIO")
	private String un01Condicionpredio;

	@Column(name="UN01_COSTODIRECTPROD")
	private BigDecimal un01Costodirectprod;

	@Column(name="UN01_COSTOESTABLECIMIENTOCULTI")
	private BigDecimal un01Costoestablecimientoculti;

	@Column(name="UN01_COSTOMANTENIMIENTO")
	private BigDecimal un01Costomantenimiento;

	@Column(name="UN01_COSTONUEVO")
	private BigDecimal un01Costonuevo;

	@Column(name="UN01_DIFERENCIAMONTO")
	private String un01Diferenciamonto;

	@Column(name="UN01_DIRECDOMICILIO")
	private String un01Direcdomicilio;

	@Column(name="UN01_DISPONERIEGO")
	private String un01Disponeriego;

	
	@Column(name="UN01_DLTFCH")
	private Date un01Dltfch;

	@Column(name="UN01_DLTUSR")
	private String un01Dltusr;

	@Column(name="UN01_EMISIONOBS")
	private String un01Emisionobs;

	@Column(name="UN01_EMISIONQBE")
	private String un01Emisionqbe;

	@Column(name="UN01_ENDOSO")
	private BigDecimal un01Endoso;

	@Column(name="UN01_ENVIADOICORE")
	private BigDecimal un01Enviadoicore;

	@Column(name="UN01_ESTADO")
	private BigDecimal un01Estado;

	@Column(name="UN01_ESTADO_UN02")
	private BigDecimal un01EstadoUn02;

	@Column(name="UN01_ESTADOCIVIL")
	private String un01Estadocivil;

	@Column(name="UN01_ESTADOSOLICITUD")
	private BigDecimal un01Estadosolicitud;

	@Column(name="UN01_ESTCALIFINTERNA")
	private String un01Estcalifinterna;

	@Column(name="UN01_FACTURA")
	private String un01Factura;

	
	@Column(name="UN01_FCH_UN02")
	private Date un01FchUn02;

	
	@Column(name="UN01_FECHAAPROBADOQBE")
	private Date un01Fechaaprobadoqbe;

	
	@Column(name="UN01_FECHAENVIOEMIQBE")
	private Date un01Fechaenvioemiqbe;

	
	@Column(name="UN01_FECHAENVIOQBE")
	private Date un01Fechaenvioqbe;

	
	@Column(name="UN01_FECHARECIBIDOQBE")
	private Date un01Fecharecibidoqbe;

	
	@Column(name="UN01_FECHASIEMBRACULTIVO")
	private Date un01Fechasiembracultivo;

	
	@Column(name="UN01_FECHATENTATIVASIEMBRA")
	private Date un01Fechatentativasiembra;

	
	@Column(name="UN01_FECNAC")
	private Date un01Fecnac;

	
	@Column(name="UN01_FECSOL")
	private Date un01Fecsol;

	@Column(name="UN01_FUENTEINGRESO")
	private String un01Fuenteingreso;

	@Column(name="UN01_GENERO")
	private String un01Genero;

	@Column(name="UN01_INDMIGRAORCL")
	private BigDecimal un01Indmigraorcl;

	@Column(name="UN01_INGRESOANUAL")
	private BigDecimal un01Ingresoanual;

	
	@Column(name="UN01_INSFCH")
	private Date un01Insfch;

	@Column(name="UN01_INSUSR")
	private String un01Insusr;

	@Column(name="UN01_LAT_DECIMAL")
	private String un01LatDecimal;

	@Column(name="UN01_LONG_DECIMAL")
	private String un01LongDecimal;

	@Column(name="UN01_LUGARNACIMIENTO")
	private String un01Lugarnacimiento;

	@Column(name="UN01_MAIL")
	private String un01Mail;

	@Column(name="UN01_MONTOAPROBADOQBE")
	private BigDecimal un01Montoaprobadoqbe;

	@Column(name="UN01_MONTOASEGURADO")
	private BigDecimal un01Montoasegurado;

	@Column(name="UN01_MONTORECOMENDADOQBE")
	private BigDecimal un01Montorecomendadoqbe;

	@Column(name="UN01_NACIONALIDAD")
	private String un01Nacionalidad;

	@Column(name="UN01_NOMBRES")
	private String un01Nombres;

	@Column(name="UN01_NUMEROLOTE")
	private String un01Numerolote;

	@Column(name="UN01_OBS_UN02")
	private String un01ObsUn02;

	@Column(name="UN01_OBSCALIFINTERNA")
	private String un01Obscalifinterna;

	@Column(name="UN01_OTRACONDPREDIO")
	private String un01Otracondpredio;

	@Column(name="UN01_OTRORIEGO")
	private String un01Otroriego;

	@Column(name="UN01_PAGADO")
	private String un01Pagado;

	@Column(name="UN01_PAGO")
	private BigDecimal un01Pago;

	@Column(name="UN01_PAQUETETECNOLOGICO")
	private String un01Paquetetecnologico;

	@Column(name="UN01_PARROQUIA")
	private String un01Parroquia;

	@Column(name="UN01_PEP")
	private String un01Pep;

	@Column(name="UN01_PLAZOVENC")
	private BigDecimal un01Plazovenc;

	@Column(name="UN01_POLIZA")
	private String un01Poliza;

	@Column(name="UN01_PRIMASUGERIDAQBE")
	private BigDecimal un01Primasugeridaqbe;

	@Column(name="UN01_PROVINCIA")
	private String un01Provincia;

	@Column(name="UN01_REA")
	private String un01Rea;

	@Column(name="UN01_REANAUTORIZ")
	private String un01Reanautoriz;

	@Column(name="UN01_REAOBS")
	private String un01Reaobs;

	@Column(name="UN01_REAPROVEEQBE")
	private String un01Reaproveeqbe;

	@Column(name="UN01_REASTS")
	private String un01Reasts;

	@Column(name="UN01_RECINTO")
	private String un01Recinto;

	@Column(name="UN01_REPROCESO")
	private String un01Reproceso;

	@Column(name="UN01_RUC")
	private String un01Ruc;

	@Column(name="UN01_SOLICITUDOBS")
	private String un01Solicitudobs;

	@Column(name="UN01_SOLICITUDQBE")
	private String un01Solicitudqbe;

	@Column(name="UN01_SS02")
	private BigDecimal un01Ss02;

	@Column(name="UN01_SS04_ESTATUS")
	private BigDecimal un01Ss04Estatus;

	
	@Column(name="UN01_SS04_FCH")
	private Date un01Ss04Fch;

	@Column(name="UN01_SS04_OPERNUM")
	private String un01Ss04Opernum;

	@Column(name="UN01_SS04_TOTALPAG")
	private BigDecimal un01Ss04Totalpag;

	@Column(name="UN01_STS")
	private String un01Sts;

	@Column(name="UN01_SUPERFICIEASEGURAR")
	private BigDecimal un01Superficieasegurar;

	@Column(name="UN01_SUPERFTOTCULT")
	private BigDecimal un01Superftotcult;

	@Column(name="UN01_TELEFONO")
	private String un01Telefono;

	@Column(name="UN01_TIPOCANAL")
	private BigDecimal un01Tipocanal;

	@Column(name="UN01_TIPORIEGO")
	private String un01Tiporiego;

	@Column(name="UN01_TIPOSEMILLA")
	private BigDecimal un01Tiposemilla;

	@Column(name="UN01_UN02_APORSUBBANCO")
	private BigDecimal un01Un02Aporsubbanco;

	@Column(name="UN01_UN02_CODASEG")
	private String un01Un02Codaseg;

	@Column(name="UN01_UN02_DEREMI")
	private BigDecimal un01Un02Deremi;

	@Column(name="UN01_UN02_DESCUENTO")
	private BigDecimal un01Un02Descuento;

	
	@Column(name="UN01_UN02_FCHDLT")
	private Date un01Un02Fchdlt;

	
	@Column(name="UN01_UN02_FCHEMI")
	private Date un01Un02Fchemi;

	
	@Column(name="UN01_UN02_FCHFIN")
	private Date un01Un02Fchfin;

	
	@Column(name="UN01_UN02_FCHINS")
	private Date un01Un02Fchins;

	
	@Column(name="UN01_UN02_FCHUDP")
	private Date un01Un02Fchudp;

	@Column(name="UN01_UN02_IVA")
	private BigDecimal un01Un02Iva;

	@Column(name="UN01_UN02_OBSERVACION")
	private String un01Un02Observacion;

	@Column(name="UN01_UN02_PRIMASUB")
	private BigDecimal un01Un02Primasub;

	@Column(name="UN01_UN02_PRIMBRUTA")
	private BigDecimal un01Un02Primbruta;

	@Column(name="UN01_UN02_PRIMFIN")
	private BigDecimal un01Un02Primfin;

	@Column(name="UN01_UN02_PRIMNETA")
	private BigDecimal un01Un02Primneta;

	@Column(name="UN01_UN02_RECSC")
	private BigDecimal un01Un02Recsc;

	@Column(name="UN01_UN02_SEGSOCCAMP")
	private BigDecimal un01Un02Segsoccamp;

	@Column(name="UN01_UN02_TASA")
	private BigDecimal un01Un02Tasa;

	@Column(name="UN01_UN02_TOTIMP")
	private BigDecimal un01Un02Totimp;

	@Column(name="UN01_UN02_USRDLT")
	private String un01Un02Usrdlt;

	@Column(name="UN01_UN02_USRINS")
	private String un01Un02Usrins;

	@Column(name="UN01_UN02_USRUDP")
	private String un01Un02Usrudp;

	
	@Column(name="UN01_UPDFCH")
	private Date un01Updfch;

	@Column(name="UN01_UPDUSR")
	private String un01Updusr;

	@Column(name="UN01_VALORSUBSIDIO")
	private BigDecimal un01Valorsubsidio;

	@Column(name="UN01_VARIEDAD")
	private String un01Variedad;

	@Column(name="UN01_VARIEDADNUEVO")
	private String un01Variedadnuevo;

	@Lob
	@Column(name="UN01_XMLINSERCION")
	private String un01Xmlinsercion;

	public Un01() {
		// no implementado
	}

	public Long getUn01Id() {
		return this.un01Id;
	}

	public void setUn01Id(Long un01Id) {
		this.un01Id = un01Id;
	}

	public String getUn01Actividadeco() {
		return this.un01Actividadeco;
	}

	public void setUn01Actividadeco(String un01Actividadeco) {
		this.un01Actividadeco = un01Actividadeco;
	}

	public String getUn01Anulado() {
		return this.un01Anulado;
	}

	public void setUn01Anulado(String un01Anulado) {
		this.un01Anulado = un01Anulado;
	}

	public String getUn01Apellidos() {
		return this.un01Apellidos;
	}

	public void setUn01Apellidos(String un01Apellidos) {
		this.un01Apellidos = un01Apellidos;
	}

	public String getUn01Apenom() {
		return this.un01Apenom;
	}

	public void setUn01Apenom(String un01Apenom) {
		this.un01Apenom = un01Apenom;
	}

	public String getUn01Asistecnica() {
		return this.un01Asistecnica;
	}

	public void setUn01Asistecnica(String un01Asistecnica) {
		this.un01Asistecnica = un01Asistecnica;
	}

	public String getUn01Calleref() {
		return this.un01Calleref;
	}

	public void setUn01Calleref(String un01Calleref) {
		this.un01Calleref = un01Calleref;
	}

	public String getUn01Canton() {
		return this.un01Canton;
	}

	public void setUn01Canton(String un01Canton) {
		this.un01Canton = un01Canton;
	}

	public BigDecimal getUn01Casacomercialid() {
		return this.un01Casacomercialid;
	}

	public void setUn01Casacomercialid(BigDecimal un01Casacomercialid) {
		this.un01Casacomercialid = un01Casacomercialid;
	}

	public String getUn01Cedula() {
		return this.un01Cedula;
	}

	public void setUn01Cedula(String un01Cedula) {
		this.un01Cedula = un01Cedula;
	}

	public String getUn01Celular() {
		return this.un01Celular;
	}

	public void setUn01Celular(String un01Celular) {
		this.un01Celular = un01Celular;
	}

	public BigDecimal getUn01Certificado() {
		return this.un01Certificado;
	}

	public void setUn01Certificado(BigDecimal un01Certificado) {
		this.un01Certificado = un01Certificado;
	}

	public BigDecimal getUn01Cicloid() {
		return this.un01Cicloid;
	}

	public void setUn01Cicloid(BigDecimal un01Cicloid) {
		this.un01Cicloid = un01Cicloid;
	}

	public String getUn01Codcultivo() {
		return this.un01Codcultivo;
	}

	public void setUn01Codcultivo(String un01Codcultivo) {
		this.un01Codcultivo = un01Codcultivo;
	}

	public String getUn01Codigofacilitador() {
		return this.un01Codigofacilitador;
	}

	public void setUn01Codigofacilitador(String un01Codigofacilitador) {
		this.un01Codigofacilitador = un01Codigofacilitador;
	}

	public String getUn01Codigosucursal() {
		return this.un01Codigosucursal;
	}

	public void setUn01Codigosucursal(String un01Codigosucursal) {
		this.un01Codigosucursal = un01Codigosucursal;
	}

	public String getUn01Codigotramite() {
		return this.un01Codigotramite;
	}

	public void setUn01Codigotramite(String un01Codigotramite) {
		this.un01Codigotramite = un01Codigotramite;
	}

	public String getUn01Codvariedadcult() {
		return this.un01Codvariedadcult;
	}

	public void setUn01Codvariedadcult(String un01Codvariedadcult) {
		this.un01Codvariedadcult = un01Codvariedadcult;
	}

	public String getUn01Condicionpredio() {
		return this.un01Condicionpredio;
	}

	public void setUn01Condicionpredio(String un01Condicionpredio) {
		this.un01Condicionpredio = un01Condicionpredio;
	}

	public BigDecimal getUn01Costodirectprod() {
		return this.un01Costodirectprod;
	}

	public void setUn01Costodirectprod(BigDecimal un01Costodirectprod) {
		this.un01Costodirectprod = un01Costodirectprod;
	}

	public BigDecimal getUn01Costoestablecimientoculti() {
		return this.un01Costoestablecimientoculti;
	}

	public void setUn01Costoestablecimientoculti(BigDecimal un01Costoestablecimientoculti) {
		this.un01Costoestablecimientoculti = un01Costoestablecimientoculti;
	}

	public BigDecimal getUn01Costomantenimiento() {
		return this.un01Costomantenimiento;
	}

	public void setUn01Costomantenimiento(BigDecimal un01Costomantenimiento) {
		this.un01Costomantenimiento = un01Costomantenimiento;
	}

	public BigDecimal getUn01Costonuevo() {
		return this.un01Costonuevo;
	}

	public void setUn01Costonuevo(BigDecimal un01Costonuevo) {
		this.un01Costonuevo = un01Costonuevo;
	}

	public String getUn01Diferenciamonto() {
		return this.un01Diferenciamonto;
	}

	public void setUn01Diferenciamonto(String un01Diferenciamonto) {
		this.un01Diferenciamonto = un01Diferenciamonto;
	}

	public String getUn01Direcdomicilio() {
		return this.un01Direcdomicilio;
	}

	public void setUn01Direcdomicilio(String un01Direcdomicilio) {
		this.un01Direcdomicilio = un01Direcdomicilio;
	}

	public String getUn01Disponeriego() {
		return this.un01Disponeriego;
	}

	public void setUn01Disponeriego(String un01Disponeriego) {
		this.un01Disponeriego = un01Disponeriego;
	}

	public Date getUn01Dltfch() {
		return this.un01Dltfch;
	}

	public void setUn01Dltfch(Date un01Dltfch) {
		this.un01Dltfch = un01Dltfch;
	}

	public String getUn01Dltusr() {
		return this.un01Dltusr;
	}

	public void setUn01Dltusr(String un01Dltusr) {
		this.un01Dltusr = un01Dltusr;
	}

	public String getUn01Emisionobs() {
		return this.un01Emisionobs;
	}

	public void setUn01Emisionobs(String un01Emisionobs) {
		this.un01Emisionobs = un01Emisionobs;
	}

	public String getUn01Emisionqbe() {
		return this.un01Emisionqbe;
	}

	public void setUn01Emisionqbe(String un01Emisionqbe) {
		this.un01Emisionqbe = un01Emisionqbe;
	}

	public BigDecimal getUn01Endoso() {
		return this.un01Endoso;
	}

	public void setUn01Endoso(BigDecimal un01Endoso) {
		this.un01Endoso = un01Endoso;
	}

	public BigDecimal getUn01Enviadoicore() {
		return this.un01Enviadoicore;
	}

	public void setUn01Enviadoicore(BigDecimal un01Enviadoicore) {
		this.un01Enviadoicore = un01Enviadoicore;
	}

	public BigDecimal getUn01Estado() {
		return this.un01Estado;
	}

	public void setUn01Estado(BigDecimal un01Estado) {
		this.un01Estado = un01Estado;
	}

	public BigDecimal getUn01EstadoUn02() {
		return this.un01EstadoUn02;
	}

	public void setUn01EstadoUn02(BigDecimal un01EstadoUn02) {
		this.un01EstadoUn02 = un01EstadoUn02;
	}

	public String getUn01Estadocivil() {
		return this.un01Estadocivil;
	}

	public void setUn01Estadocivil(String un01Estadocivil) {
		this.un01Estadocivil = un01Estadocivil;
	}

	public BigDecimal getUn01Estadosolicitud() {
		return this.un01Estadosolicitud;
	}

	public void setUn01Estadosolicitud(BigDecimal un01Estadosolicitud) {
		this.un01Estadosolicitud = un01Estadosolicitud;
	}

	public String getUn01Estcalifinterna() {
		return this.un01Estcalifinterna;
	}

	public void setUn01Estcalifinterna(String un01Estcalifinterna) {
		this.un01Estcalifinterna = un01Estcalifinterna;
	}

	public String getUn01Factura() {
		return this.un01Factura;
	}

	public void setUn01Factura(String un01Factura) {
		this.un01Factura = un01Factura;
	}

	public Date getUn01FchUn02() {
		return this.un01FchUn02;
	}

	public void setUn01FchUn02(Date un01FchUn02) {
		this.un01FchUn02 = un01FchUn02;
	}

	public Date getUn01Fechaaprobadoqbe() {
		return this.un01Fechaaprobadoqbe;
	}

	public void setUn01Fechaaprobadoqbe(Date un01Fechaaprobadoqbe) {
		this.un01Fechaaprobadoqbe = un01Fechaaprobadoqbe;
	}

	public Date getUn01Fechaenvioemiqbe() {
		return this.un01Fechaenvioemiqbe;
	}

	public void setUn01Fechaenvioemiqbe(Date un01Fechaenvioemiqbe) {
		this.un01Fechaenvioemiqbe = un01Fechaenvioemiqbe;
	}

	public Date getUn01Fechaenvioqbe() {
		return this.un01Fechaenvioqbe;
	}

	public void setUn01Fechaenvioqbe(Date un01Fechaenvioqbe) {
		this.un01Fechaenvioqbe = un01Fechaenvioqbe;
	}

	public Date getUn01Fecharecibidoqbe() {
		return this.un01Fecharecibidoqbe;
	}

	public void setUn01Fecharecibidoqbe(Date un01Fecharecibidoqbe) {
		this.un01Fecharecibidoqbe = un01Fecharecibidoqbe;
	}

	public Date getUn01Fechasiembracultivo() {
		return this.un01Fechasiembracultivo;
	}

	public void setUn01Fechasiembracultivo(Date un01Fechasiembracultivo) {
		this.un01Fechasiembracultivo = un01Fechasiembracultivo;
	}

	public Date getUn01Fechatentativasiembra() {
		return this.un01Fechatentativasiembra;
	}

	public void setUn01Fechatentativasiembra(Date un01Fechatentativasiembra) {
		this.un01Fechatentativasiembra = un01Fechatentativasiembra;
	}

	public Date getUn01Fecnac() {
		return this.un01Fecnac;
	}

	public void setUn01Fecnac(Date un01Fecnac) {
		this.un01Fecnac = un01Fecnac;
	}

	public Date getUn01Fecsol() {
		return this.un01Fecsol;
	}

	public void setUn01Fecsol(Date un01Fecsol) {
		this.un01Fecsol = un01Fecsol;
	}

	public String getUn01Fuenteingreso() {
		return this.un01Fuenteingreso;
	}

	public void setUn01Fuenteingreso(String un01Fuenteingreso) {
		this.un01Fuenteingreso = un01Fuenteingreso;
	}

	public String getUn01Genero() {
		return this.un01Genero;
	}

	public void setUn01Genero(String un01Genero) {
		this.un01Genero = un01Genero;
	}

	public BigDecimal getUn01Indmigraorcl() {
		return this.un01Indmigraorcl;
	}

	public void setUn01Indmigraorcl(BigDecimal un01Indmigraorcl) {
		this.un01Indmigraorcl = un01Indmigraorcl;
	}

	public BigDecimal getUn01Ingresoanual() {
		return this.un01Ingresoanual;
	}

	public void setUn01Ingresoanual(BigDecimal un01Ingresoanual) {
		this.un01Ingresoanual = un01Ingresoanual;
	}

	public Date getUn01Insfch() {
		return this.un01Insfch;
	}

	public void setUn01Insfch(Date un01Insfch) {
		this.un01Insfch = un01Insfch;
	}

	public String getUn01Insusr() {
		return this.un01Insusr;
	}

	public void setUn01Insusr(String un01Insusr) {
		this.un01Insusr = un01Insusr;
	}

	public String getUn01LatDecimal() {
		return this.un01LatDecimal;
	}

	public void setUn01LatDecimal(String un01LatDecimal) {
		this.un01LatDecimal = un01LatDecimal;
	}

	public String getUn01LongDecimal() {
		return this.un01LongDecimal;
	}

	public void setUn01LongDecimal(String un01LongDecimal) {
		this.un01LongDecimal = un01LongDecimal;
	}

	public String getUn01Lugarnacimiento() {
		return this.un01Lugarnacimiento;
	}

	public void setUn01Lugarnacimiento(String un01Lugarnacimiento) {
		this.un01Lugarnacimiento = un01Lugarnacimiento;
	}

	public String getUn01Mail() {
		return this.un01Mail;
	}

	public void setUn01Mail(String un01Mail) {
		this.un01Mail = un01Mail;
	}

	public BigDecimal getUn01Montoaprobadoqbe() {
		return this.un01Montoaprobadoqbe;
	}

	public void setUn01Montoaprobadoqbe(BigDecimal un01Montoaprobadoqbe) {
		this.un01Montoaprobadoqbe = un01Montoaprobadoqbe;
	}

	public BigDecimal getUn01Montoasegurado() {
		return this.un01Montoasegurado;
	}

	public void setUn01Montoasegurado(BigDecimal un01Montoasegurado) {
		this.un01Montoasegurado = un01Montoasegurado;
	}

	public BigDecimal getUn01Montorecomendadoqbe() {
		return this.un01Montorecomendadoqbe;
	}

	public void setUn01Montorecomendadoqbe(BigDecimal un01Montorecomendadoqbe) {
		this.un01Montorecomendadoqbe = un01Montorecomendadoqbe;
	}

	public String getUn01Nacionalidad() {
		return this.un01Nacionalidad;
	}

	public void setUn01Nacionalidad(String un01Nacionalidad) {
		this.un01Nacionalidad = un01Nacionalidad;
	}

	public String getUn01Nombres() {
		return this.un01Nombres;
	}

	public void setUn01Nombres(String un01Nombres) {
		this.un01Nombres = un01Nombres;
	}

	public String getUn01Numerolote() {
		return this.un01Numerolote;
	}

	public void setUn01Numerolote(String un01Numerolote) {
		this.un01Numerolote = un01Numerolote;
	}

	public String getUn01ObsUn02() {
		return this.un01ObsUn02;
	}

	public void setUn01ObsUn02(String un01ObsUn02) {
		this.un01ObsUn02 = un01ObsUn02;
	}

	public String getUn01Obscalifinterna() {
		return this.un01Obscalifinterna;
	}

	public void setUn01Obscalifinterna(String un01Obscalifinterna) {
		this.un01Obscalifinterna = un01Obscalifinterna;
	}

	public String getUn01Otracondpredio() {
		return this.un01Otracondpredio;
	}

	public void setUn01Otracondpredio(String un01Otracondpredio) {
		this.un01Otracondpredio = un01Otracondpredio;
	}

	public String getUn01Otroriego() {
		return this.un01Otroriego;
	}

	public void setUn01Otroriego(String un01Otroriego) {
		this.un01Otroriego = un01Otroriego;
	}

	public String getUn01Pagado() {
		return this.un01Pagado;
	}

	public void setUn01Pagado(String un01Pagado) {
		this.un01Pagado = un01Pagado;
	}

	public BigDecimal getUn01Pago() {
		return this.un01Pago;
	}

	public void setUn01Pago(BigDecimal un01Pago) {
		this.un01Pago = un01Pago;
	}

	public String getUn01Paquetetecnologico() {
		return this.un01Paquetetecnologico;
	}

	public void setUn01Paquetetecnologico(String un01Paquetetecnologico) {
		this.un01Paquetetecnologico = un01Paquetetecnologico;
	}

	public String getUn01Parroquia() {
		return this.un01Parroquia;
	}

	public void setUn01Parroquia(String un01Parroquia) {
		this.un01Parroquia = un01Parroquia;
	}

	public String getUn01Pep() {
		return this.un01Pep;
	}

	public void setUn01Pep(String un01Pep) {
		this.un01Pep = un01Pep;
	}

	public BigDecimal getUn01Plazovenc() {
		return this.un01Plazovenc;
	}

	public void setUn01Plazovenc(BigDecimal un01Plazovenc) {
		this.un01Plazovenc = un01Plazovenc;
	}

	public String getUn01Poliza() {
		return this.un01Poliza;
	}

	public void setUn01Poliza(String un01Poliza) {
		this.un01Poliza = un01Poliza;
	}

	public BigDecimal getUn01Primasugeridaqbe() {
		return this.un01Primasugeridaqbe;
	}

	public void setUn01Primasugeridaqbe(BigDecimal un01Primasugeridaqbe) {
		this.un01Primasugeridaqbe = un01Primasugeridaqbe;
	}

	public String getUn01Provincia() {
		return this.un01Provincia;
	}

	public void setUn01Provincia(String un01Provincia) {
		this.un01Provincia = un01Provincia;
	}

	public String getUn01Rea() {
		return this.un01Rea;
	}

	public void setUn01Rea(String un01Rea) {
		this.un01Rea = un01Rea;
	}

	public String getUn01Reanautoriz() {
		return this.un01Reanautoriz;
	}

	public void setUn01Reanautoriz(String un01Reanautoriz) {
		this.un01Reanautoriz = un01Reanautoriz;
	}

	public String getUn01Reaobs() {
		return this.un01Reaobs;
	}

	public void setUn01Reaobs(String un01Reaobs) {
		this.un01Reaobs = un01Reaobs;
	}

	public String getUn01Reaproveeqbe() {
		return this.un01Reaproveeqbe;
	}

	public void setUn01Reaproveeqbe(String un01Reaproveeqbe) {
		this.un01Reaproveeqbe = un01Reaproveeqbe;
	}

	public String getUn01Reasts() {
		return this.un01Reasts;
	}

	public void setUn01Reasts(String un01Reasts) {
		this.un01Reasts = un01Reasts;
	}

	public String getUn01Recinto() {
		return this.un01Recinto;
	}

	public void setUn01Recinto(String un01Recinto) {
		this.un01Recinto = un01Recinto;
	}

	public String getUn01Reproceso() {
		return this.un01Reproceso;
	}

	public void setUn01Reproceso(String un01Reproceso) {
		this.un01Reproceso = un01Reproceso;
	}

	public String getUn01Ruc() {
		return this.un01Ruc;
	}

	public void setUn01Ruc(String un01Ruc) {
		this.un01Ruc = un01Ruc;
	}

	public String getUn01Solicitudobs() {
		return this.un01Solicitudobs;
	}

	public void setUn01Solicitudobs(String un01Solicitudobs) {
		this.un01Solicitudobs = un01Solicitudobs;
	}

	public String getUn01Solicitudqbe() {
		return this.un01Solicitudqbe;
	}

	public void setUn01Solicitudqbe(String un01Solicitudqbe) {
		this.un01Solicitudqbe = un01Solicitudqbe;
	}

	public BigDecimal getUn01Ss02() {
		return this.un01Ss02;
	}

	public void setUn01Ss02(BigDecimal un01Ss02) {
		this.un01Ss02 = un01Ss02;
	}

	public BigDecimal getUn01Ss04Estatus() {
		return this.un01Ss04Estatus;
	}

	public void setUn01Ss04Estatus(BigDecimal un01Ss04Estatus) {
		this.un01Ss04Estatus = un01Ss04Estatus;
	}

	public Date getUn01Ss04Fch() {
		return this.un01Ss04Fch;
	}

	public void setUn01Ss04Fch(Date un01Ss04Fch) {
		this.un01Ss04Fch = un01Ss04Fch;
	}

	public String getUn01Ss04Opernum() {
		return this.un01Ss04Opernum;
	}

	public void setUn01Ss04Opernum(String un01Ss04Opernum) {
		this.un01Ss04Opernum = un01Ss04Opernum;
	}

	public BigDecimal getUn01Ss04Totalpag() {
		return this.un01Ss04Totalpag;
	}

	public void setUn01Ss04Totalpag(BigDecimal un01Ss04Totalpag) {
		this.un01Ss04Totalpag = un01Ss04Totalpag;
	}

	public String getUn01Sts() {
		return this.un01Sts;
	}

	public void setUn01Sts(String un01Sts) {
		this.un01Sts = un01Sts;
	}

	public BigDecimal getUn01Superficieasegurar() {
		return this.un01Superficieasegurar;
	}

	public void setUn01Superficieasegurar(BigDecimal un01Superficieasegurar) {
		this.un01Superficieasegurar = un01Superficieasegurar;
	}

	public BigDecimal getUn01Superftotcult() {
		return this.un01Superftotcult;
	}

	public void setUn01Superftotcult(BigDecimal un01Superftotcult) {
		this.un01Superftotcult = un01Superftotcult;
	}

	public String getUn01Telefono() {
		return this.un01Telefono;
	}

	public void setUn01Telefono(String un01Telefono) {
		this.un01Telefono = un01Telefono;
	}

	public BigDecimal getUn01Tipocanal() {
		return this.un01Tipocanal;
	}

	public void setUn01Tipocanal(BigDecimal un01Tipocanal) {
		this.un01Tipocanal = un01Tipocanal;
	}

	public String getUn01Tiporiego() {
		return this.un01Tiporiego;
	}

	public void setUn01Tiporiego(String un01Tiporiego) {
		this.un01Tiporiego = un01Tiporiego;
	}

	public BigDecimal getUn01Tiposemilla() {
		return this.un01Tiposemilla;
	}

	public void setUn01Tiposemilla(BigDecimal un01Tiposemilla) {
		this.un01Tiposemilla = un01Tiposemilla;
	}

	public BigDecimal getUn01Un02Aporsubbanco() {
		return this.un01Un02Aporsubbanco;
	}

	public void setUn01Un02Aporsubbanco(BigDecimal un01Un02Aporsubbanco) {
		this.un01Un02Aporsubbanco = un01Un02Aporsubbanco;
	}

	public String getUn01Un02Codaseg() {
		return this.un01Un02Codaseg;
	}

	public void setUn01Un02Codaseg(String un01Un02Codaseg) {
		this.un01Un02Codaseg = un01Un02Codaseg;
	}

	public BigDecimal getUn01Un02Deremi() {
		return this.un01Un02Deremi;
	}

	public void setUn01Un02Deremi(BigDecimal un01Un02Deremi) {
		this.un01Un02Deremi = un01Un02Deremi;
	}

	public BigDecimal getUn01Un02Descuento() {
		return this.un01Un02Descuento;
	}

	public void setUn01Un02Descuento(BigDecimal un01Un02Descuento) {
		this.un01Un02Descuento = un01Un02Descuento;
	}

	public Date getUn01Un02Fchdlt() {
		return this.un01Un02Fchdlt;
	}

	public void setUn01Un02Fchdlt(Date un01Un02Fchdlt) {
		this.un01Un02Fchdlt = un01Un02Fchdlt;
	}

	public Date getUn01Un02Fchemi() {
		return this.un01Un02Fchemi;
	}

	public void setUn01Un02Fchemi(Date un01Un02Fchemi) {
		this.un01Un02Fchemi = un01Un02Fchemi;
	}

	public Date getUn01Un02Fchfin() {
		return this.un01Un02Fchfin;
	}

	public void setUn01Un02Fchfin(Date un01Un02Fchfin) {
		this.un01Un02Fchfin = un01Un02Fchfin;
	}

	public Date getUn01Un02Fchins() {
		return this.un01Un02Fchins;
	}

	public void setUn01Un02Fchins(Date un01Un02Fchins) {
		this.un01Un02Fchins = un01Un02Fchins;
	}

	public Date getUn01Un02Fchudp() {
		return this.un01Un02Fchudp;
	}

	public void setUn01Un02Fchudp(Date un01Un02Fchudp) {
		this.un01Un02Fchudp = un01Un02Fchudp;
	}

	public BigDecimal getUn01Un02Iva() {
		return this.un01Un02Iva;
	}

	public void setUn01Un02Iva(BigDecimal un01Un02Iva) {
		this.un01Un02Iva = un01Un02Iva;
	}

	public String getUn01Un02Observacion() {
		return this.un01Un02Observacion;
	}

	public void setUn01Un02Observacion(String un01Un02Observacion) {
		this.un01Un02Observacion = un01Un02Observacion;
	}

	public BigDecimal getUn01Un02Primasub() {
		return this.un01Un02Primasub;
	}

	public void setUn01Un02Primasub(BigDecimal un01Un02Primasub) {
		this.un01Un02Primasub = un01Un02Primasub;
	}

	public BigDecimal getUn01Un02Primbruta() {
		return this.un01Un02Primbruta;
	}

	public void setUn01Un02Primbruta(BigDecimal un01Un02Primbruta) {
		this.un01Un02Primbruta = un01Un02Primbruta;
	}

	public BigDecimal getUn01Un02Primfin() {
		return this.un01Un02Primfin;
	}

	public void setUn01Un02Primfin(BigDecimal un01Un02Primfin) {
		this.un01Un02Primfin = un01Un02Primfin;
	}

	public BigDecimal getUn01Un02Primneta() {
		return this.un01Un02Primneta;
	}

	public void setUn01Un02Primneta(BigDecimal un01Un02Primneta) {
		this.un01Un02Primneta = un01Un02Primneta;
	}

	public BigDecimal getUn01Un02Recsc() {
		return this.un01Un02Recsc;
	}

	public void setUn01Un02Recsc(BigDecimal un01Un02Recsc) {
		this.un01Un02Recsc = un01Un02Recsc;
	}

	public BigDecimal getUn01Un02Segsoccamp() {
		return this.un01Un02Segsoccamp;
	}

	public void setUn01Un02Segsoccamp(BigDecimal un01Un02Segsoccamp) {
		this.un01Un02Segsoccamp = un01Un02Segsoccamp;
	}

	public BigDecimal getUn01Un02Tasa() {
		return this.un01Un02Tasa;
	}

	public void setUn01Un02Tasa(BigDecimal un01Un02Tasa) {
		this.un01Un02Tasa = un01Un02Tasa;
	}

	public BigDecimal getUn01Un02Totimp() {
		return this.un01Un02Totimp;
	}

	public void setUn01Un02Totimp(BigDecimal un01Un02Totimp) {
		this.un01Un02Totimp = un01Un02Totimp;
	}

	public String getUn01Un02Usrdlt() {
		return this.un01Un02Usrdlt;
	}

	public void setUn01Un02Usrdlt(String un01Un02Usrdlt) {
		this.un01Un02Usrdlt = un01Un02Usrdlt;
	}

	public String getUn01Un02Usrins() {
		return this.un01Un02Usrins;
	}

	public void setUn01Un02Usrins(String un01Un02Usrins) {
		this.un01Un02Usrins = un01Un02Usrins;
	}

	public String getUn01Un02Usrudp() {
		return this.un01Un02Usrudp;
	}

	public void setUn01Un02Usrudp(String un01Un02Usrudp) {
		this.un01Un02Usrudp = un01Un02Usrudp;
	}

	public Date getUn01Updfch() {
		return this.un01Updfch;
	}

	public void setUn01Updfch(Date un01Updfch) {
		this.un01Updfch = un01Updfch;
	}

	public String getUn01Updusr() {
		return this.un01Updusr;
	}

	public void setUn01Updusr(String un01Updusr) {
		this.un01Updusr = un01Updusr;
	}

	public BigDecimal getUn01Valorsubsidio() {
		return this.un01Valorsubsidio;
	}

	public void setUn01Valorsubsidio(BigDecimal un01Valorsubsidio) {
		this.un01Valorsubsidio = un01Valorsubsidio;
	}

	public String getUn01Variedad() {
		return this.un01Variedad;
	}

	public void setUn01Variedad(String un01Variedad) {
		this.un01Variedad = un01Variedad;
	}

	public String getUn01Variedadnuevo() {
		return this.un01Variedadnuevo;
	}

	public void setUn01Variedadnuevo(String un01Variedadnuevo) {
		this.un01Variedadnuevo = un01Variedadnuevo;
	}

	public String getUn01Xmlinsercion() {
		return this.un01Xmlinsercion;
	}

	public void setUn01Xmlinsercion(String un01Xmlinsercion) {
		this.un01Xmlinsercion = un01Xmlinsercion;
	}

}