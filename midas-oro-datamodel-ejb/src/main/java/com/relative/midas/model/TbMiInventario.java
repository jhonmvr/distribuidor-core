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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.ProcesoEnum;


/**
 * The persistent class for the tb_mi_inventario database table.
 * 
 */
@Entity
@Table(name="tb_mi_inventario")
public class TbMiInventario implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_INVENTARIO_ID_GENERATOR", sequenceName="SEQ_INVENTARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_INVENTARIO_ID_GENERATOR")
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

	//bi-directional many-to-one association to TbMiJoya
	@ManyToOne
	@JoinColumn(name="id_joya")
	private TbMiJoya tbMiJoya;
	
	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;

	//bi-directional many-to-one association to TbMiMovInventario
	@OneToMany(mappedBy="tbMiInventario")
	private List<TbMiMovInventario> tbMiMovInventarios;

	public TbMiInventario() {
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

	public TbMiJoya getTbMiJoya() {
		return this.tbMiJoya;
	}

	public void setTbMiJoya(TbMiJoya tbMiJoya) {
		this.tbMiJoya = tbMiJoya;
	}

	public List<TbMiMovInventario> getTbMiMovInventarios() {
		return this.tbMiMovInventarios;
	}

	public void setTbMiMovInventarios(List<TbMiMovInventario> tbMiMovInventarios) {
		this.tbMiMovInventarios = tbMiMovInventarios;
	}

	public TbMiMovInventario addTbMiMovInventario(TbMiMovInventario tbMiMovInventario) {
		getTbMiMovInventarios().add(tbMiMovInventario);
		tbMiMovInventario.setTbMiInventario(this);

		return tbMiMovInventario;
	}

	public TbMiMovInventario removeTbMiMovInventario(TbMiMovInventario tbMiMovInventario) {
		getTbMiMovInventarios().remove(tbMiMovInventario);
		tbMiMovInventario.setTbMiInventario(null);

		return tbMiMovInventario;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

}