package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.midas.enums.EstadoFundaEnum;

import java.util.Date;


/**
 * The persistent class for the tb_mi_liquidacion database table.
 * 
 */
@Entity
@Table(name="tb_mi_liquidacion")
public class TbMiLiquidacion implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_LIQUIDACION_ID_GENERATOR", sequenceName="SEQ_LIQUIDACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_LIQUIDACION_ID_GENERATOR")
	private Long id;

	private String codigo;

	private String comentario;

	@Enumerated(EnumType.STRING)
	private EstadoFundaEnum estado;

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

	//bi-directional many-to-one association to TbMiFolletoLiquidacion
	@ManyToOne
	@JoinColumn(name="id_folleto_liquidacion")
	private TbMiFolletoLiquidacion tbMiFolletoLiquidacion;

	public TbMiLiquidacion() {
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

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoFundaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoFundaEnum estado) {
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

	public TbMiFolletoLiquidacion getTbMiFolletoLiquidacion() {
		return this.tbMiFolletoLiquidacion;
	}

	public void setTbMiFolletoLiquidacion(TbMiFolletoLiquidacion tbMiFolletoLiquidacion) {
		this.tbMiFolletoLiquidacion = tbMiFolletoLiquidacion;
	}

}