package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;

import ec.fin.segurossucre.pa.enums.EstadoSiniestroAgricolaEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the TB_SA_ASEGURADO database table.
 * 
 */
@Entity
@Table(name="TB_SA_ASEGURADO")
//@NamedQuery(name="TbSaAsegurado.findAll", query="SELECT t FROM TbSaAsegurado t")
public class TbSaAsegurado implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SA_ASEGURADO_ID_GENERATOR", sequenceName="SEQ_ASEGURADO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SA_ASEGURADO_ID_GENERATOR")
	private Long id;

	private String agricultura;

	private String apellido;

	private String correo;

	private String direccion;

	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;


	@Column(name="FECHA_ACTUALIZACION")
	private Timestamp fechaActualizacion;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	private String identificacion;

	@Column(name="INGRESO_ANUAL")
	private BigDecimal ingresoAnual;

	@Column(name="LUGAR_NACIMIENTO")
	private String lugarNacimiento;

	private String nacionalidad;

	private String nombres;

	@Column(name="NUMERO_TRAMITE")
	private String numeroTramite;

	@Column(name="POLITICAMENTE_EXPUESTA")
	private String politicamenteExpuesta;

	private String referencia;

	private String sexo;

	@Column(name="TELEFONO_CELULAR")
	private String telefonoCelular;

	@Column(name="TELEFONO_CONVENCIONAL")
	private String telefonoConvencional;

	//bi-directional many-to-one association to Acteco
	@ManyToOne
	@JoinColumn(name="ACTIVIDAD_ECONOMICA")
	private Acteco acteco;

	//bi-directional many-to-one association to Estadocivil
	@ManyToOne
	@JoinColumn(name="ESTADO_CIVIL")
	private Estadocivil estadocivil;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CANTON_D", referencedColumnName="CANTONID"),
		@JoinColumn(name="ID_PARROQUIA_D", referencedColumnName="PARROQUIAID"),
		@JoinColumn(name="ID_PROVINCIA_D", referencedColumnName="PROVINCIAID")
		})
	private Parroquia parroquiaDomicilio;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CANTON_N", referencedColumnName="CANTONID"),
		@JoinColumn(name="ID_PARROQUIA_N", referencedColumnName="PARROQUIAID"),
		@JoinColumn(name="ID_PROVINCIA_N", referencedColumnName="PROVINCIAID")
		})
	private Parroquia parroquiaNacimiento;

	public TbSaAsegurado() {
		//sin definir
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgricultura() {
		return this.agricultura;
	}

	public void setAgricultura(String agricultura) {
		this.agricultura = agricultura;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public EstadoSiniestroAgricolaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}

	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public BigDecimal getIngresoAnual() {
		return this.ingresoAnual;
	}

	public void setIngresoAnual(BigDecimal ingresoAnual) {
		this.ingresoAnual = ingresoAnual;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroTramite() {
		return this.numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	public String getPoliticamenteExpuesta() {
		return this.politicamenteExpuesta;
	}

	public void setPoliticamenteExpuesta(String politicamenteExpuesta) {
		this.politicamenteExpuesta = politicamenteExpuesta;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoConvencional() {
		return this.telefonoConvencional;
	}

	public void setTelefonoConvencional(String telefonoConvencional) {
		this.telefonoConvencional = telefonoConvencional;
	}

	public Acteco getActeco() {
		return this.acteco;
	}

	public void setActeco(Acteco acteco) {
		this.acteco = acteco;
	}

	public Estadocivil getEstadocivil() {
		return this.estadocivil;
	}

	public void setEstadocivil(Estadocivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public Parroquia getparroquiaDomicilio() {
		return this.parroquiaDomicilio;
	}

	public void setparroquiaDomicilio(Parroquia parroquiaDomicilio) {
		this.parroquiaDomicilio = parroquiaDomicilio;
	}

	public Parroquia getparroquiaNacimiento() {
		return this.parroquiaNacimiento;
	}

	public void setparroquiaNacimiento(Parroquia parroquiaNacimiento) {
		this.parroquiaNacimiento = parroquiaNacimiento;
	}

}