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
import com.relative.midas.enums.ProcesoEnum;


/**
 * The persistent class for the tb_mi_mov_inventario database table.
 * 
 */
@Entity
@Table(name="tb_mi_mov_inventario")
//@NamedQuery(name="TbMiMovInventario.findAll", query="SELECT t FROM TbMiMovInventario t")
public class TbMiMovInventario implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_MOV_INVENTARIO_ID_GENERATOR", sequenceName="SEQ_MOV_INVENTARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_MOV_INVENTARIO_ID_GENERATOR")
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

	//bi-directional many-to-one association to TbMiBodega
	@ManyToOne
	@JoinColumn(name="id_bodega")
	private TbMiBodega tbMiBodega;

	//bi-directional many-to-one association to TbMiInventario
	@ManyToOne
	@JoinColumn(name="id_inventario")
	private TbMiInventario tbMiInventario;
	
	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;

	public TbMiMovInventario() {
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

	public TbMiBodega getTbMiBodega() {
		return this.tbMiBodega;
	}

	public void setTbMiBodega(TbMiBodega tbMiBodega) {
		this.tbMiBodega = tbMiBodega;
	}

	public TbMiInventario getTbMiInventario() {
		return this.tbMiInventario;
	}

	public void setTbMiInventario(TbMiInventario tbMiInventario) {
		this.tbMiInventario = tbMiInventario;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

}