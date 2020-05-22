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
 * The persistent class for the tb_mi_cuenta database table.
 * 
 */
@Entity
@Table(name="tb_mi_cuenta")
//@NamedQuery(name="TbMiCuenta.findAll", query="SELECT t FROM TbMiCuenta t")
public class TbMiCuenta implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CUENTA_ID_GENERATOR", sequenceName="SEQ_CUENTA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CUENTA_ID_GENERATOR")
	private Long id;

	private String codigo;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actulizacion")
	private Date fechaActulizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiMovimientoCaja
	@OneToMany(mappedBy="tbMiCuenta")
	private List<TbMiMovimientoCaja> tbMiMovimientoCajas;

	public TbMiCuenta() {
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

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public Date getFechaActulizacion() {
		return this.fechaActulizacion;
	}

	public void setFechaActulizacion(Date fechaActulizacion) {
		this.fechaActulizacion = fechaActulizacion;
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
		tbMiMovimientoCaja.setTbMiCuenta(this);

		return tbMiMovimientoCaja;
	}

	public TbMiMovimientoCaja removeTbMiMovimientoCaja(TbMiMovimientoCaja tbMiMovimientoCaja) {
		getTbMiMovimientoCajas().remove(tbMiMovimientoCaja);
		tbMiMovimientoCaja.setTbMiCuenta(null);

		return tbMiMovimientoCaja;
	}

}