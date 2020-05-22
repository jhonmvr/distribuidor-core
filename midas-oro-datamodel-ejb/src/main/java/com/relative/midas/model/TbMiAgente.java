package com.relative.midas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_agente database table.
 * 
 */
@Entity
@Table(name="tb_mi_agente")
//@NamedQuery(name="TbMiAgente.findAll", query="SELECT t FROM TbMiAgente t")
public class TbMiAgente implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_AGENTE_ID_GENERATOR", sequenceName="SEQ_AGENTE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_AGENTE_ID_GENERATOR")
	private Long id;

	private String direccion;

	@Column(name="email_principal")
	private String emailPrincipal;

	@Column(name="email_secundario")
	private String emailSecundario;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_usuario")
	private String nombreUsuario;

	

	private String identificacion;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="primer_nombre")
	private String primerNombre;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@Column(name="segundo_nombre")
	private String segundoNombre;

	@Column(name="telefono_principal")
	private String telefonoPrincipal;

	@Column(name="telefono_secundario")
	private String telefonoSecundario;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	//////
	@Column(name="nacionalidad")
	private String nacionalidad;
	
	@Column(name="estado_civil")
	private String estadoCivil;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;
	
	@Column(name="nivel_estudios")
	private String nivelEstudios;
	
	@Column(name="pais")
	private String pais;

	@Column(name="sector")
	private String sector;
	
	@Column(name="calle_principal")
	private String callePrincipal;
	
	@Column(name="interseccion")
	private String interseccion;
	
	@Column(name="numero_domicilio")
	private String numeroDommicilio;
	
	@Column(name="referencia_ubicacion")
	private String referenciaUbicacion;
	
	@Column(name="tipo_identificacion")
	private String tipoIdentificacion;
	
	
	//bi-directional many-to-one association to Canton
		@ManyToOne
		@JoinColumns({
			@JoinColumn(name="id_ciudad", referencedColumnName="cantonid"),
			@JoinColumn(name="id_provincia", referencedColumnName="provinciaid")
			})
		private Canton canton;

	//bi-directional many-to-one association to TbMiAgencia
	@OneToMany(mappedBy="tbMiAgente")
	private List<TbMiAgencia> tbMiAgencias;

	//bi-directional many-to-one association to TbMiAgencia
	@OneToMany(mappedBy="tbMiAgenteSupervisor")
	private List<TbMiAgencia> tbMiAgenciasSupervisors;

	//bi-directional many-to-one association to TbMiContactabilidad
	@OneToMany(mappedBy="tbMiAgente")
	private List<TbMiContactabilidad> tbMiContactabilidads;

	//bi-directional many-to-one association to TbMiContrato
	@OneToMany(mappedBy="tbMiAgente")
	private List<TbMiContrato> tbMiContratos;

	//bi-directional many-to-one association to TbMiCorteCaja
	@OneToMany(mappedBy="tbMiAgente")
	private List<TbMiCorteCaja> tbMiCorteCajas;

	public TbMiAgente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getInterseccion() {
		return interseccion;
	}

	public void setInterseccion(String interseccion) {
		this.interseccion = interseccion;
	}

	public String getNumeroDommicilio() {
		return numeroDommicilio;
	}

	public void setNumeroDommicilio(String numeroDommicilio) {
		this.numeroDommicilio = numeroDommicilio;
	}

	public String getReferenciaUbicacion() {
		return referenciaUbicacion;
	}

	public void setReferenciaUbicacion(String referenciaUbicacion) {
		this.referenciaUbicacion = referenciaUbicacion;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmailPrincipal() {
		return this.emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getEmailSecundario() {
		return this.emailSecundario;
	}

	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdUsuario() {
		return this.nombreUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.nombreUsuario = idUsuario;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefonoPrincipal() {
		return this.telefonoPrincipal;
	}

	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}

	public String getTelefonoSecundario() {
		return this.telefonoSecundario;
	}

	public void setTelefonoSecundario(String telefonoSecundario) {
		this.telefonoSecundario = telefonoSecundario;
	}

	public String getUsuarioActualizacion() {
		return this.usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public List<TbMiAgencia> getTbMiAgencias() {
		return this.tbMiAgencias;
	}

	public void setTbMiAgencias(List<TbMiAgencia> tbMiAgencias) {
		this.tbMiAgencias = tbMiAgencias;
	}

	public TbMiAgencia addTbMiAgencia(TbMiAgencia tbMiAgencia) {
		getTbMiAgencias().add(tbMiAgencia);
		tbMiAgencia.setTbMiAgente(this);

		return tbMiAgencia;
	}

	public TbMiAgencia removeTbMiAgencia(TbMiAgencia tbMiAgencia) {
		getTbMiAgencias().remove(tbMiAgencia);
		tbMiAgencia.setTbMiAgente(null);

		return tbMiAgencia;
	}

	public List<TbMiAgencia> getTbMiAgenciasSupervisors() {
		return this.tbMiAgenciasSupervisors;
	}

	public void setTbMiAgenciasSupervisors(List<TbMiAgencia> tbMiAgenciasSupervisors) {
		this.tbMiAgenciasSupervisors = tbMiAgenciasSupervisors;
	}

	public List<TbMiContactabilidad> getTbMiContactabilidads() {
		return this.tbMiContactabilidads;
	}

	public void setTbMiContactabilidads(List<TbMiContactabilidad> tbMiContactabilidads) {
		this.tbMiContactabilidads = tbMiContactabilidads;
	}

	public TbMiContactabilidad addTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().add(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiAgente(this);

		return tbMiContactabilidad;
	}

	public TbMiContactabilidad removeTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().remove(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiAgente(null);

		return tbMiContactabilidad;
	}

	public List<TbMiContrato> getTbMiContratos() {
		return this.tbMiContratos;
	}

	public void setTbMiContratos(List<TbMiContrato> tbMiContratos) {
		this.tbMiContratos = tbMiContratos;
	}

	public TbMiContrato addTbMiContrato(TbMiContrato tbMiContrato) {
		getTbMiContratos().add(tbMiContrato);
		tbMiContrato.setTbMiAgente(this);

		return tbMiContrato;
	}

	public TbMiContrato removeTbMiContrato(TbMiContrato tbMiContrato) {
		getTbMiContratos().remove(tbMiContrato);
		tbMiContrato.setTbMiAgente(null);

		return tbMiContrato;
	}

	public List<TbMiCorteCaja> getTbMiCorteCajas() {
		return this.tbMiCorteCajas;
	}

	public void setTbMiCorteCajas(List<TbMiCorteCaja> tbMiCorteCajas) {
		this.tbMiCorteCajas = tbMiCorteCajas;
	}

	public TbMiCorteCaja addTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		getTbMiCorteCajas().add(tbMiCorteCaja);
		tbMiCorteCaja.setTbMiAgente(this);

		return tbMiCorteCaja;
	}

	public TbMiCorteCaja removeTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		getTbMiCorteCajas().remove(tbMiCorteCaja);
		tbMiCorteCaja.setTbMiAgente(null);

		return tbMiCorteCaja;
	}

	

}