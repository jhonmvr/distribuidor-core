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

import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.TipoBodegaEnum;


/**
 * The persistent class for the tb_mi_bodega database table.
 * 
 */
@Entity
@Table(name="tb_mi_bodega")
public class TbMiBodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_BODEGA_ID_GENERATOR", sequenceName="SEQ_BODEGA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_BODEGA_ID_GENERATOR")
	private Long id;

	private String descripcion;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_bodega")
	private TipoBodegaEnum tipoBodega;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiFunda
	@OneToMany(mappedBy="tbMiBodega")
	private List<TbMiFunda> tbMiFundas;

	//bi-directional many-to-one association to TbMiInventario
	@OneToMany(mappedBy="tbMiBodega")
	private List<TbMiInventario> tbMiInventarios;

	//bi-directional many-to-one association to TbMiMovInventario
	@OneToMany(mappedBy="tbMiBodega")
	private List<TbMiMovInventario> tbMiMovInventarios;

	public TbMiBodega() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoBodegaEnum getTipoBodega() {
		return this.tipoBodega;
	}

	public void setTipoBodega(TipoBodegaEnum tipoBodega) {
		this.tipoBodega = tipoBodega;
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

	public List<TbMiFunda> getTbMiFundas() {
		return this.tbMiFundas;
	}

	public void setTbMiFundas(List<TbMiFunda> tbMiFundas) {
		this.tbMiFundas = tbMiFundas;
	}

	public TbMiFunda addTbMiFunda(TbMiFunda tbMiFunda) {
		getTbMiFundas().add(tbMiFunda);
		tbMiFunda.setTbMiBodega(this);

		return tbMiFunda;
	}

	public TbMiFunda removeTbMiFunda(TbMiFunda tbMiFunda) {
		getTbMiFundas().remove(tbMiFunda);
		tbMiFunda.setTbMiBodega(null);

		return tbMiFunda;
	}

	public List<TbMiInventario> getTbMiInventarios() {
		return this.tbMiInventarios;
	}

	public void setTbMiInventarios(List<TbMiInventario> tbMiInventarios) {
		this.tbMiInventarios = tbMiInventarios;
	}

	public TbMiInventario addTbMiInventario(TbMiInventario tbMiInventario) {
		getTbMiInventarios().add(tbMiInventario);
		tbMiInventario.setTbMiBodega(this);

		return tbMiInventario;
	}

	public TbMiInventario removeTbMiInventario(TbMiInventario tbMiInventario) {
		getTbMiInventarios().remove(tbMiInventario);
		tbMiInventario.setTbMiBodega(null);

		return tbMiInventario;
	}

	public List<TbMiMovInventario> getTbMiMovInventarios() {
		return this.tbMiMovInventarios;
	}

	public void setTbMiMovInventarios(List<TbMiMovInventario> tbMiMovInventarios) {
		this.tbMiMovInventarios = tbMiMovInventarios;
	}

	public TbMiMovInventario addTbMiMovInventario(TbMiMovInventario tbMiMovInventario) {
		getTbMiMovInventarios().add(tbMiMovInventario);
		tbMiMovInventario.setTbMiBodega(this);

		return tbMiMovInventario;
	}

	public TbMiMovInventario removeTbMiMovInventario(TbMiMovInventario tbMiMovInventario) {
		getTbMiMovInventarios().remove(tbMiMovInventario);
		tbMiMovInventario.setTbMiBodega(null);

		return tbMiMovInventario;
	}

}