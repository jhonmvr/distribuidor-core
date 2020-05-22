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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_tipo_joya database table.
 * 
 */
@Entity
@Table(name="tb_mi_tipo_joya")
//@NamedQuery(name="TbMiTipoJoya.findAll", query="SELECT t FROM TbMiTipoJoya t")
public class TbMiTipoJoya implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_TIPO_JOYA_ID_GENERATOR", sequenceName="SEQ_TIPO_JOYA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_TIPO_JOYA_ID_GENERATOR")
	private Long id;

	private String codigo;

	private String detalle;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	//bi-directional many-to-one association to TbMiJoya
	@OneToMany(mappedBy="tbMiTipoJoya")
	private List<TbMiJoya> tbMiJoyas;

	public TbMiTipoJoya() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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

	public List<TbMiJoya> getTbMiJoyas() {
		return this.tbMiJoyas;
	}

	public void setTbMiJoyas(List<TbMiJoya> tbMiJoyas) {
		this.tbMiJoyas = tbMiJoyas;
	}

	public TbMiJoya addTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().add(tbMiJoya);
		tbMiJoya.setTbMiTipoJoya(this);

		return tbMiJoya;
	}

	public TbMiJoya removeTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().remove(tbMiJoya);
		tbMiJoya.setTbMiTipoJoya(null);

		return tbMiJoya;
	}

}