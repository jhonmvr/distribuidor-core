package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.midas.enums.EstadoMidasEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_mi_detalle_meta database table.
 * 
 */
@Entity
@Table(name="tb_mi_detalle_meta")
public class TbMiDetalleMeta implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_DETALLE_META_ID_GENERATOR", sequenceName="SEQ_DETALLE_META",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_DETALLE_META_ID_GENERATOR")
	private Long id;

	@Column(name="cartera_cancelada")
	private BigDecimal carteraCancelada;

	@Column(name="cartera_nueva")
	private BigDecimal carteraNueva;

	@Column(name="cartera_perfeccionada")
	private BigDecimal carteraPerfeccionada;

	@Column(name="cartera_por_vencer")
	private BigDecimal carteraPorVencer;

	@Column(name="cartera_renovada")
	private BigDecimal carteraRenovada;

	@Column(name="cartera_total")
	private BigDecimal carteraTotal;

	@Column(name="cartera_vencida")
	private BigDecimal carteraVencida;

	private String cumple;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cierre")
	private Date fechaCierre;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="meta_comercial")
	private BigDecimal metaComercial;

	@Column(name="meta_gerencial")
	private BigDecimal metaGerencial;

	@Column(name="porcentaje_comercial")
	private BigDecimal porcentajeComercial;

	@Column(name="porcentaje_gerencial")
	private BigDecimal porcentajeGerencial;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiMeta
	@ManyToOne
	@JoinColumn(name="id_meta")
	private TbMiMeta tbMiMeta;

	public TbMiDetalleMeta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCarteraCancelada() {
		return this.carteraCancelada;
	}

	public void setCarteraCancelada(BigDecimal carteraCancelada) {
		this.carteraCancelada = carteraCancelada;
	}

	public BigDecimal getCarteraNueva() {
		return this.carteraNueva;
	}

	public void setCarteraNueva(BigDecimal carteraNueva) {
		this.carteraNueva = carteraNueva;
	}

	public BigDecimal getCarteraPerfeccionada() {
		return this.carteraPerfeccionada;
	}

	public void setCarteraPerfeccionada(BigDecimal carteraPerfeccionada) {
		this.carteraPerfeccionada = carteraPerfeccionada;
	}

	public BigDecimal getCarteraPorVencer() {
		return this.carteraPorVencer;
	}

	public void setCarteraPorVencer(BigDecimal carteraPorVencer) {
		this.carteraPorVencer = carteraPorVencer;
	}

	public BigDecimal getCarteraRenovada() {
		return this.carteraRenovada;
	}

	public void setCarteraRenovada(BigDecimal carteraRenovada) {
		this.carteraRenovada = carteraRenovada;
	}

	public BigDecimal getCarteraTotal() {
		return this.carteraTotal;
	}

	public void setCarteraTotal(BigDecimal carteraTotal) {
		this.carteraTotal = carteraTotal;
	}

	public BigDecimal getCarteraVencida() {
		return this.carteraVencida;
	}

	public void setCarteraVencida(BigDecimal carteraVencida) {
		this.carteraVencida = carteraVencida;
	}

	public String getCumple() {
		return this.cumple;
	}

	public void setCumple(String cumple) {
		this.cumple = cumple;
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

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getMetaComercial() {
		return this.metaComercial;
	}

	public void setMetaComercial(BigDecimal metaComercial) {
		this.metaComercial = metaComercial;
	}

	public BigDecimal getMetaGerencial() {
		return this.metaGerencial;
	}

	public void setMetaGerencial(BigDecimal metaGerencial) {
		this.metaGerencial = metaGerencial;
	}

	public BigDecimal getPorcentajeComercial() {
		return this.porcentajeComercial;
	}

	public void setPorcentajeComercial(BigDecimal porcentajeComercial) {
		this.porcentajeComercial = porcentajeComercial;
	}

	public BigDecimal getPorcentajeGerencial() {
		return this.porcentajeGerencial;
	}

	public void setPorcentajeGerencial(BigDecimal porcentajeGerencial) {
		this.porcentajeGerencial = porcentajeGerencial;
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

	public TbMiAgencia getTbMiAgencia() {
		return this.tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public TbMiMeta getTbMiMeta() {
		return this.tbMiMeta;
	}

	public void setTbMiMeta(TbMiMeta tbMiMeta) {
		this.tbMiMeta = tbMiMeta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}