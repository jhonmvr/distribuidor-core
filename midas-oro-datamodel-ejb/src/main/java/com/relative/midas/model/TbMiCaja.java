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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_caja database table.
 * 
 */
@Entity
@Table(name="tb_mi_caja")
//@NamedQuery(name="TbMiCaja.findAll", query="SELECT t FROM TbMiCaja t")
public class TbMiCaja implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CAJA_ID_GENERATOR", sequenceName="SEQ_CAJA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CAJA_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

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
	
	@Column(name="saldo_actual")
	private BigDecimal saldoActual;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiDetalleCaja
	@OneToMany(mappedBy="tbMiCaja")
	private List<TbMiDetalleCaja> tbMiDetalleCajas;

	public TbMiCaja() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<TbMiDetalleCaja> getTbMiDetalleCajas() {
		return this.tbMiDetalleCajas;
	}

	public void setTbMiDetalleCajas(List<TbMiDetalleCaja> tbMiDetalleCajas) {
		this.tbMiDetalleCajas = tbMiDetalleCajas;
	}

	public TbMiDetalleCaja addTbMiDetalleCaja(TbMiDetalleCaja tbMiDetalleCaja) {
		getTbMiDetalleCajas().add(tbMiDetalleCaja);
		tbMiDetalleCaja.setTbMiCaja(this);

		return tbMiDetalleCaja;
	}

	public TbMiDetalleCaja removeTbMiDetalleCaja(TbMiDetalleCaja tbMiDetalleCaja) {
		getTbMiDetalleCajas().remove(tbMiDetalleCaja);
		tbMiDetalleCaja.setTbMiCaja(null);

		return tbMiDetalleCaja;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

}