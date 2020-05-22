package com.relative.midas.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name="tb_mi_cliente")
public class TbMiCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CLIENTE_ID_GENERATOR", sequenceName="SEQ_CLIENTE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CLIENTE_ID_GENERATOR")
	private Long id;
	
	@Column(name="apellido")
	private String apellido;
	
	private String jubilado;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="celular_referencia")
	private String celularReferencia;

	private String direccion;

	@Column(name="direccion_trabajo")
	private String direccionTrabajo;

	private String email;

	private String empresa;

	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String identificacion;

	@Column(name="ingreso_mensual")
	private BigDecimal ingresoMensual;

	@Column(name="ingresos_mensuales")
	private BigDecimal ingresosMensuales;

	private String interseccion;

	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;

	private String nacionalidad;

	@Column(name="nivel_estudios")
	private String nivelEstudios;
	
	@Column(name="nombre")
	private String nombre;
	

	@Column(name="segundo_nombre")
	private String segundoNombre;
	

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@Column(name="nombre_referencia")
	private String nombreReferencia;

	@Column(name="numero_dommicilio")
	private String numeroDommicilio;

	private String ocupacion;

	@Column(name="ocupacion_inmueble")
	private String ocupacionInmueble;

	@Column(name="referencia_ubicacion")
	private String referenciaUbicacion;

	private String sector;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	@Column(name="telefono_referencia")
	private String telefonoReferencia;

	@Column(name="telefono_trabajo")
	private String telefonoTrabajo;

	@Column(name="tipo_id")
	private String tipoId;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	
	@Column(name="cuenta_Bancaria")
	private String cuentaBancaria;
	
	@Column(name="tarjeta_Credito")
	private String tarjetaCredito;
	
	@Column(name="deudor")
	private String deudor;
	
	@Column(name="figura_Politica")
	private String figuraPolitica;

	//bi-directional many-to-one association to TbMiAbono
	@OneToMany(mappedBy="tbMiCliente")
	private List<TbMiAbono> tbMiAbonos;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_canton", referencedColumnName="cantonid"),
		@JoinColumn(name="id_provincia", referencedColumnName="provinciaid")
		})
	private Canton canton;

	//bi-directional many-to-one association to TbMiContactabilidad
	@OneToMany(mappedBy="tbMiCliente")
	private List<TbMiContactabilidad> tbMiContactabilidads;

	//bi-directional many-to-one association to TbMiContrato
	@OneToMany(mappedBy="tbMiCliente")
	private List<TbMiContrato> tbMiContratos;

	//bi-directional many-to-one association to TbMiMovimientoCaja
	@OneToMany(mappedBy="tbMiCliente")
	private List<TbMiMovimientoCaja> tbMiMovimientoCajas;

	//bi-directional many-to-one association to TbMiVentaLote
	@OneToMany(mappedBy="tbMiCliente")
	private List<TbMiVentaLote> tbMiVentaLotes;

	public TbMiCliente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCelularReferencia() {
		return this.celularReferencia;
	}

	public void setCelularReferencia(String celularReferencia) {
		this.celularReferencia = celularReferencia;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccionTrabajo() {
		return this.direccionTrabajo;
	}

	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public String getDeudor() {
		return deudor;
	}

	public void setDeudor(String deudor) {
		this.deudor = deudor;
	}

	public String getFiguraPolitica() {
		return figuraPolitica;
	}

	public void setFiguraPolitica(String figuraPolitica) {
		this.figuraPolitica = figuraPolitica;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
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

	public BigDecimal getIngresoMensual() {
		return this.ingresoMensual;
	}

	public void setIngresoMensual(BigDecimal ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}

	public BigDecimal getIngresosMensuales() {
		return this.ingresosMensuales;
	}

	public void setIngresosMensuales(BigDecimal ingresosMensuales) {
		this.ingresosMensuales = ingresosMensuales;
	}

	public String getInterseccion() {
		return this.interseccion;
	}

	public void setInterseccion(String interseccion) {
		this.interseccion = interseccion;
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

	public String getNivelEstudios() {
		return this.nivelEstudios;
	}

	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreReferencia() {
		return this.nombreReferencia;
	}

	public void setNombreReferencia(String nombreReferencia) {
		this.nombreReferencia = nombreReferencia;
	}

	public String getNumeroDommicilio() {
		return this.numeroDommicilio;
	}

	public void setNumeroDommicilio(String numeroDommicilio) {
		this.numeroDommicilio = numeroDommicilio;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getOcupacionInmueble() {
		return this.ocupacionInmueble;
	}

	public void setOcupacionInmueble(String ocupacionInmueble) {
		this.ocupacionInmueble = ocupacionInmueble;
	}

	public String getReferenciaUbicacion() {
		return this.referenciaUbicacion;
	}

	public void setReferenciaUbicacion(String referenciaUbicacion) {
		this.referenciaUbicacion = referenciaUbicacion;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoReferencia() {
		return this.telefonoReferencia;
	}

	public void setTelefonoReferencia(String telefonoReferencia) {
		this.telefonoReferencia = telefonoReferencia;
	}

	public String getTelefonoTrabajo() {
		return this.telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	public String getTipoId() {
		return this.tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
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

	public List<TbMiAbono> getTbMiAbonos() {
		return this.tbMiAbonos;
	}

	public void setTbMiAbonos(List<TbMiAbono> tbMiAbonos) {
		this.tbMiAbonos = tbMiAbonos;
	}

	public TbMiAbono addTbMiAbono(TbMiAbono tbMiAbono) {
		getTbMiAbonos().add(tbMiAbono);
		tbMiAbono.setTbMiCliente(this);

		return tbMiAbono;
	}

	public TbMiAbono removeTbMiAbono(TbMiAbono tbMiAbono) {
		getTbMiAbonos().remove(tbMiAbono);
		tbMiAbono.setTbMiCliente(null);

		return tbMiAbono;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public List<TbMiContactabilidad> getTbMiContactabilidads() {
		return this.tbMiContactabilidads;
	}

	public void setTbMiContactabilidads(List<TbMiContactabilidad> tbMiContactabilidads) {
		this.tbMiContactabilidads = tbMiContactabilidads;
	}

	public TbMiContactabilidad addTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().add(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiCliente(this);

		return tbMiContactabilidad;
	}

	public TbMiContactabilidad removeTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().remove(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiCliente(null);

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
		tbMiContrato.setTbMiCliente(this);

		return tbMiContrato;
	}

	public TbMiContrato removeTbMiContrato(TbMiContrato tbMiContrato) {
		getTbMiContratos().remove(tbMiContrato);
		tbMiContrato.setTbMiCliente(null);

		return tbMiContrato;
	}

	public List<TbMiMovimientoCaja> getTbMiMovimientoCajas() {
		return this.tbMiMovimientoCajas;
	}

	public void setTbMiMovimientoCajas(List<TbMiMovimientoCaja> tbMiMovimientoCajas) {
		this.tbMiMovimientoCajas = tbMiMovimientoCajas;
	}

	public TbMiMovimientoCaja addTbMiMovimientoCaja(TbMiMovimientoCaja tbMiMovimientoCaja) {
		getTbMiMovimientoCajas().add(tbMiMovimientoCaja);
		tbMiMovimientoCaja.setTbMiCliente(this);

		return tbMiMovimientoCaja;
	}

	public TbMiMovimientoCaja removeTbMiMovimientoCaja(TbMiMovimientoCaja tbMiMovimientoCaja) {
		getTbMiMovimientoCajas().remove(tbMiMovimientoCaja);
		tbMiMovimientoCaja.setTbMiCliente(null);

		return tbMiMovimientoCaja;
	}

	public List<TbMiVentaLote> getTbMiVentaLotes() {
		return this.tbMiVentaLotes;
	}

	public void setTbMiVentaLotes(List<TbMiVentaLote> tbMiVentaLotes) {
		this.tbMiVentaLotes = tbMiVentaLotes;
	}

	public TbMiVentaLote addTbMiVentaLote(TbMiVentaLote tbMiVentaLote) {
		getTbMiVentaLotes().add(tbMiVentaLote);
		tbMiVentaLote.setTbMiCliente(this);

		return tbMiVentaLote;
	}

	public TbMiVentaLote removeTbMiVentaLote(TbMiVentaLote tbMiVentaLote) {
		getTbMiVentaLotes().remove(tbMiVentaLote);
		tbMiVentaLote.setTbMiCliente(null);

		return tbMiVentaLote;
	}

	public String getJubilado() {
		return jubilado;
	}

	public void setJubilado(String jubilado) {
		this.jubilado = jubilado;
	}

}