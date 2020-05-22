package com.relative.midas.model;

import java.io.Serializable;
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

import com.relative.midas.enums.EstadoJoyaEnum;


/**
 * The persistent class for the tb_mi_joya_lote database table.
 * 
 */
@Entity
@Table(name="tb_mi_joya_lote")
public class TbMiJoyaLote implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_JOYA_LOTE_ID_GENERATOR", sequenceName="SEQ_JOYA_LOTE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_JOYA_LOTE_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoJoyaEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiJoya
	@ManyToOne
	@JoinColumn(name="id_joya")
	private TbMiJoya tbMiJoya;

	//bi-directional many-to-one association to TbMiLote
	@ManyToOne
	@JoinColumn(name="id_lote")
	private TbMiLote tbMiLote;

	public TbMiJoyaLote() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoJoyaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoJoyaEnum estado) {
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

	public TbMiJoya getTbMiJoya() {
		return this.tbMiJoya;
	}

	public void setTbMiJoya(TbMiJoya tbMiJoya) {
		this.tbMiJoya = tbMiJoya;
	}

	public TbMiLote getTbMiLote() {
		return this.tbMiLote;
	}

	public void setTbMiLote(TbMiLote tbMiLote) {
		this.tbMiLote = tbMiLote;
	}

}