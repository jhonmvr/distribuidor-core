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
import com.relative.midas.enums.TipoAgenciaEnum;


/**
 * The persistent class for the tb_mi_agencia database table.
 * 
 */
@Entity
@Table(name="tb_mi_agencia")
//@NamedQuery(name="TbMiAgencia.findAll", query="SELECT t FROM TbMiAgencia t")
public class TbMiAgencia implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_AGENCIA_ID_GENERATOR", sequenceName="SEQ_AGENCIA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_AGENCIA_ID_GENERATOR")
	private Long id;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="calle_secundaria")
	private String calleSecundaria;

	private String celular;

	private String codigo;

	private String componente;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String cuenta;

	private String direccion;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_agencia")
	private String nombreAgencia;

	private String pais;

	private String referencia;

	private String sector;

	@Column(name="seq_cd")
	private String seqCd;

	@Column(name="seq_cp")
	private String seqCp;

	@Column(name="seq_vl")
	private String seqVl;
	
	@Column(name="seq_ie")
	private String seqIE;

	private String telefono;
	
	@Column(name="tipo_agencia")
	@Enumerated(EnumType.STRING)
	private TipoAgenciaEnum tipoAgencia;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiCaja
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiCaja> tbMiCajas;

	//bi-directional many-to-one association to TbMiDetalleMeta
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiDetalleMeta> tbMiDetalleMetas;

	//bi-directional many-to-one association to TbMiFolletoLiquidacion
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiFolletoLiquidacion> tbMiFolletoLiquidacions;

	//bi-directional many-to-one association to TbMiNotificacion
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiNotificacion> tbMiNotificacions;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_ciudad", referencedColumnName="cantonid"),
		@JoinColumn(name="id_provincia", referencedColumnName="provinciaid")
		})
	private Canton canton;

	//bi-directional many-to-one association to TbMiAgente
	@ManyToOne
	@JoinColumn(name="encargado")
	private TbMiAgente tbMiAgente;

	//bi-directional many-to-one association to TbMiAgente
	@ManyToOne
	@JoinColumn(name="supervisor")
	private TbMiAgente tbMiAgenteSupervisor;

	//bi-directional many-to-one association to TbMiBodega
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiBodega> tbMiBodegas;

	//bi-directional many-to-one association to TbMiContactabilidad
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiContactabilidad> tbMiContactabilidads;

	//bi-directional many-to-one association to TbMiContrato
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiContrato> tbMiContratos;

	//bi-directional many-to-one association to TbMiCorteCaja
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiCorteCaja> tbMiCorteCajas;

	//bi-directional many-to-one association to TbMiFundaRango
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiFundaRango> tbMiFundaRangos;

	//bi-directional many-to-one association to TbMiLote
	@OneToMany(mappedBy="tbMiAgencia")
	private List<TbMiLote> tbMiLotes;

	

	public TbMiAgencia() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return this.calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSeqIE() {
		return seqIE;
	}

	public void setSeqIE(String seqIE) {
		this.seqIE = seqIE;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComponente() {
		return this.componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getNombreAgencia() {
		return this.nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSeqCd() {
		return this.seqCd;
	}

	public void setSeqCd(String seqCd) {
		this.seqCd = seqCd;
	}

	public String getSeqCp() {
		return this.seqCp;
	}

	public void setSeqCp(String seqCp) {
		this.seqCp = seqCp;
	}

	public String getSeqVl() {
		return this.seqVl;
	}

	public void setSeqVl(String seqVl) {
		this.seqVl = seqVl;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoAgenciaEnum getTipoAgencia() {
		return tipoAgencia;
	}

	public void setTipoAgencia(TipoAgenciaEnum tipoAgencia) {
		this.tipoAgencia = tipoAgencia;
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

	public List<TbMiCaja> getTbMiCajas() {
		return this.tbMiCajas;
	}

	public void setTbMiCajas(List<TbMiCaja> tbMiCajas) {
		this.tbMiCajas = tbMiCajas;
	}

	public TbMiCaja addTbMiCaja(TbMiCaja tbMiCaja) {
		getTbMiCajas().add(tbMiCaja);
		tbMiCaja.setTbMiAgencia(this);

		return tbMiCaja;
	}

	public TbMiCaja removeTbMiCaja(TbMiCaja tbMiCaja) {
		getTbMiCajas().remove(tbMiCaja);
		tbMiCaja.setTbMiAgencia(null);

		return tbMiCaja;
	}

	public List<TbMiDetalleMeta> getTbMiDetalleMetas() {
		return this.tbMiDetalleMetas;
	}

	public void setTbMiDetalleMetas(List<TbMiDetalleMeta> tbMiDetalleMetas) {
		this.tbMiDetalleMetas = tbMiDetalleMetas;
	}

	public TbMiDetalleMeta addTbMiDetalleMeta(TbMiDetalleMeta tbMiDetalleMeta) {
		getTbMiDetalleMetas().add(tbMiDetalleMeta);
		tbMiDetalleMeta.setTbMiAgencia(this);

		return tbMiDetalleMeta;
	}

	public TbMiDetalleMeta removeTbMiDetalleMeta(TbMiDetalleMeta tbMiDetalleMeta) {
		getTbMiDetalleMetas().remove(tbMiDetalleMeta);
		tbMiDetalleMeta.setTbMiAgencia(null);

		return tbMiDetalleMeta;
	}

	public List<TbMiFolletoLiquidacion> getTbMiFolletoLiquidacions() {
		return this.tbMiFolletoLiquidacions;
	}

	public void setTbMiFolletoLiquidacions(List<TbMiFolletoLiquidacion> tbMiFolletoLiquidacions) {
		this.tbMiFolletoLiquidacions = tbMiFolletoLiquidacions;
	}

	public TbMiFolletoLiquidacion addTbMiFolletoLiquidacion(TbMiFolletoLiquidacion tbMiFolletoLiquidacion) {
		getTbMiFolletoLiquidacions().add(tbMiFolletoLiquidacion);
		tbMiFolletoLiquidacion.setTbMiAgencia(this);

		return tbMiFolletoLiquidacion;
	}

	public TbMiFolletoLiquidacion removeTbMiFolletoLiquidacion(TbMiFolletoLiquidacion tbMiFolletoLiquidacion) {
		getTbMiFolletoLiquidacions().remove(tbMiFolletoLiquidacion);
		tbMiFolletoLiquidacion.setTbMiAgencia(null);

		return tbMiFolletoLiquidacion;
	}

	public List<TbMiNotificacion> getTbMiNotificacions() {
		return this.tbMiNotificacions;
	}

	public void setTbMiNotificacions(List<TbMiNotificacion> tbMiNotificacions) {
		this.tbMiNotificacions = tbMiNotificacions;
	}

	public TbMiNotificacion addTbMiNotificacion(TbMiNotificacion tbMiNotificacion) {
		getTbMiNotificacions().add(tbMiNotificacion);
		tbMiNotificacion.setTbMiAgencia(this);

		return tbMiNotificacion;
	}

	public TbMiNotificacion removeTbMiNotificacion(TbMiNotificacion tbMiNotificacion) {
		getTbMiNotificacions().remove(tbMiNotificacion);
		tbMiNotificacion.setTbMiAgencia(null);

		return tbMiNotificacion;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	

	public List<TbMiBodega> getTbMiBodegas() {
		return this.tbMiBodegas;
	}

	public void setTbMiBodegas(List<TbMiBodega> tbMiBodegas) {
		this.tbMiBodegas = tbMiBodegas;
	}

	public TbMiBodega addTbMiBodega(TbMiBodega tbMiBodega) {
		getTbMiBodegas().add(tbMiBodega);
		tbMiBodega.setTbMiAgencia(this);

		return tbMiBodega;
	}

	public TbMiBodega removeTbMiBodega(TbMiBodega tbMiBodega) {
		getTbMiBodegas().remove(tbMiBodega);
		tbMiBodega.setTbMiAgencia(null);

		return tbMiBodega;
	}

	public List<TbMiContactabilidad> getTbMiContactabilidads() {
		return this.tbMiContactabilidads;
	}

	public void setTbMiContactabilidads(List<TbMiContactabilidad> tbMiContactabilidads) {
		this.tbMiContactabilidads = tbMiContactabilidads;
	}

	public TbMiContactabilidad addTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().add(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiAgencia(this);

		return tbMiContactabilidad;
	}

	public TbMiContactabilidad removeTbMiContactabilidad(TbMiContactabilidad tbMiContactabilidad) {
		getTbMiContactabilidads().remove(tbMiContactabilidad);
		tbMiContactabilidad.setTbMiAgencia(null);

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
		tbMiContrato.setTbMiAgencia(this);

		return tbMiContrato;
	}

	public TbMiContrato removeTbMiContrato(TbMiContrato tbMiContrato) {
		getTbMiContratos().remove(tbMiContrato);
		tbMiContrato.setTbMiAgencia(null);

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
		tbMiCorteCaja.setTbMiAgencia(this);

		return tbMiCorteCaja;
	}

	public TbMiCorteCaja removeTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		getTbMiCorteCajas().remove(tbMiCorteCaja);
		tbMiCorteCaja.setTbMiAgencia(null);

		return tbMiCorteCaja;
	}

	public List<TbMiFundaRango> getTbMiFundaRangos() {
		return this.tbMiFundaRangos;
	}

	public void setTbMiFundaRangos(List<TbMiFundaRango> tbMiFundaRangos) {
		this.tbMiFundaRangos = tbMiFundaRangos;
	}

	public TbMiFundaRango addTbMiFundaRango(TbMiFundaRango tbMiFundaRango) {
		getTbMiFundaRangos().add(tbMiFundaRango);
		tbMiFundaRango.setTbMiAgencia(this);

		return tbMiFundaRango;
	}

	public TbMiFundaRango removeTbMiFundaRango(TbMiFundaRango tbMiFundaRango) {
		getTbMiFundaRangos().remove(tbMiFundaRango);
		tbMiFundaRango.setTbMiAgencia(null);

		return tbMiFundaRango;
	}

	public List<TbMiLote> getTbMiLotes() {
		return this.tbMiLotes;
	}

	public void setTbMiLotes(List<TbMiLote> tbMiLotes) {
		this.tbMiLotes = tbMiLotes;
	}

	public TbMiLote addTbMiLote(TbMiLote tbMiLote) {
		getTbMiLotes().add(tbMiLote);
		tbMiLote.setTbMiAgencia(this);

		return tbMiLote;
	}

	public TbMiLote removeTbMiLote(TbMiLote tbMiLote) {
		getTbMiLotes().remove(tbMiLote);
		tbMiLote.setTbMiAgencia(null);

		return tbMiLote;
	}

	public TbMiAgente getTbMiAgente() {
		return tbMiAgente;
	}

	public void setTbMiAgente(TbMiAgente tbMiAgente) {
		this.tbMiAgente = tbMiAgente;
	}

	public TbMiAgente getTbMiAgenteSupervisor() {
		return tbMiAgenteSupervisor;
	}

	public void setTbMiAgenteSupervisor(TbMiAgente tbMiAgenteSupervisor) {
		this.tbMiAgenteSupervisor = tbMiAgenteSupervisor;
	}
	

}