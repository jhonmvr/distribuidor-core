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
 * The persistent class for the tb_mi_banco database table.
 * 
 */
@Entity
@Table(name="tb_mi_banco")
//@NamedQuery(name="TbMiBanco.findAll", query="SELECT t FROM TbMiBanco t")
public class TbMiBanco implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_BANCO_ID_GENERATOR", sequenceName="SEQ_BANCO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_BANCO_ID_GENERATOR")
	private Long id;

	private String codigo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	//bi-directional many-to-one association to TbMiMovimientoCaja
	@OneToMany(mappedBy="tbMiBanco")
	private List<TbMiMovimientoCaja> tbMiMovimientoCajas;

	public TbMiBanco() {
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

	public List<TbMiMovimientoCaja> getTbMiMovimientoCajas() {
		return this.tbMiMovimientoCajas;
	}

	public void setTbMiMovimientoCajas(List<TbMiMovimientoCaja> tbMiMovimientoCajas) {
		this.tbMiMovimientoCajas = tbMiMovimientoCajas;
	}

	public TbMiMovimientoCaja addTbMiMovimientoCaja(TbMiMovimientoCaja tbMiMovimientoCaja) {
		getTbMiMovimientoCajas().add(tbMiMovimientoCaja);
		tbMiMovimientoCaja.setTbMiBanco(this);

		return tbMiMovimientoCaja;
	}

	public TbMiMovimientoCaja removeTbMiMovimientoCaja(TbMiMovimientoCaja tbMiMovimientoCaja) {
		getTbMiMovimientoCajas().remove(tbMiMovimientoCaja);
		tbMiMovimientoCaja.setTbMiBanco(null);

		return tbMiMovimientoCaja;
	}

	public EstadoMidasEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

}