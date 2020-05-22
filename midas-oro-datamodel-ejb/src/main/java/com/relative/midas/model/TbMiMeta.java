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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_meta database table.
 * 
 */
@Entity
@Table(name="tb_mi_meta")
public class TbMiMeta implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_META_ID_GENERATOR", sequenceName="SEQ_META",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_META_ID_GENERATOR")
	private Long id;

	@Column(name="cartera_por_vencer")
	private BigDecimal carteraPorVencer;

	@Column(name="cartera_total")
	private BigDecimal carteraTotal;

	@Column(name="cartera_vencida")
	private BigDecimal carteraVencida;

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

	private String nombre;

	@Column(name="porcentaje_comercial")
	private BigDecimal porcentajeComercial;

	@Column(name="porcentaje_gerencial")
	private BigDecimal porcentajeGerencial;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiDetalleMeta
	@OneToMany(mappedBy="tbMiMeta")
	private List<TbMiDetalleMeta> tbMiDetalleMetas;

	public TbMiMeta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCarteraPorVencer() {
		return this.carteraPorVencer;
	}

	public void setCarteraPorVencer(BigDecimal carteraPorVencer) {
		this.carteraPorVencer = carteraPorVencer;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<TbMiDetalleMeta> getTbMiDetalleMetas() {
		return this.tbMiDetalleMetas;
	}

	public void setTbMiDetalleMetas(List<TbMiDetalleMeta> tbMiDetalleMetas) {
		this.tbMiDetalleMetas = tbMiDetalleMetas;
	}

	public TbMiDetalleMeta addTbMiDetalleMeta(TbMiDetalleMeta tbMiDetalleMeta) {
		getTbMiDetalleMetas().add(tbMiDetalleMeta);
		tbMiDetalleMeta.setTbMiMeta(this);

		return tbMiDetalleMeta;
	}

	public TbMiDetalleMeta removeTbMiDetalleMeta(TbMiDetalleMeta tbMiDetalleMeta) {
		getTbMiDetalleMetas().remove(tbMiDetalleMeta);
		tbMiDetalleMeta.setTbMiMeta(null);

		return tbMiDetalleMeta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}