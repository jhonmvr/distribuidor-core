package com.relative.midas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_detalle_caja database table.
 * 
 */
@Entity
@Table(name="tb_mi_detalle_caja")
//@NamedQuery(name="TbMiDetalleCaja.findAll", query="SELECT t FROM TbMiDetalleCaja t")
public class TbMiDetalleCaja implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_DETALLE_CAJA_ID_GENERATOR", sequenceName="SEQ_DETALLE_CAJA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_DETALLE_CAJA_ID_GENERATOR")
	private Long id;

	private BigDecimal denomincion;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private BigDecimal numero;

	private String tipo;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	private BigDecimal valor;

	//bi-directional many-to-one association to TbMiCaja
	@ManyToOne
	@JoinColumn(name="id_caja")
	private TbMiCaja tbMiCaja;
	
	@ManyToOne
	@JoinColumn(name="id_corte_caja")
	private TbMiCorteCaja tbMiCorteCaja;


	public TbMiDetalleCaja() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getDenomincion() {
		return this.denomincion;
	}

	public void setDenomincion(BigDecimal denomincion) {
		this.denomincion = denomincion;
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

	public BigDecimal getNumero() {
		return this.numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TbMiCaja getTbMiCaja() {
		return this.tbMiCaja;
	}

	public void setTbMiCaja(TbMiCaja tbMiCaja) {
		this.tbMiCaja = tbMiCaja;
	}

	public TbMiCorteCaja getTbMiCorteCaja() {
		return tbMiCorteCaja;
	}

	public void setTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		this.tbMiCorteCaja = tbMiCorteCaja;
	}

}