
package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_mi_mov_entre_caja database table.
 * 
 */
@Entity
@Table(name="tb_mi_mov_entre_caja")
public class TbMiMovEntreCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_MOV_ENTRE_CAJA_ID_GENERATOR", sequenceName="SEQ_MOV_ENTRE_CAJA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_MOV_ENTRE_CAJA_ID_GENERATOR")
	private Long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="saldo_agencia_destino")
	private BigDecimal saldoAgenciaDestino;

	@Column(name="saldo_agencia_origen")
	private BigDecimal saldoAgenciaOrigen;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia_destino")
	private TbMiAgencia tbMiAgenciaD;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia_origen")
	private TbMiAgencia tbMiAgenciaO;

	public TbMiMovEntreCaja() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
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

	public BigDecimal getSaldoAgenciaDestino() {
		return this.saldoAgenciaDestino;
	}

	public void setSaldoAgenciaDestino(BigDecimal saldoAgenciaDestino) {
		this.saldoAgenciaDestino = saldoAgenciaDestino;
	}

	public BigDecimal getSaldoAgenciaOrigen() {
		return this.saldoAgenciaOrigen;
	}

	public void setSaldoAgenciaOrigen(BigDecimal saldoAgenciaOrigen) {
		this.saldoAgenciaOrigen = saldoAgenciaOrigen;
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

	public TbMiAgencia getTbMiAgenciaD() {
		return tbMiAgenciaD;
	}

	public void setTbMiAgenciaD(TbMiAgencia tbMiAgenciaD) {
		this.tbMiAgenciaD = tbMiAgenciaD;
	}

	public TbMiAgencia getTbMiAgenciaO() {
		return tbMiAgenciaO;
	}

	public void setTbMiAgenciaO(TbMiAgencia tbMiAgenciaO) {
		this.tbMiAgenciaO = tbMiAgenciaO;
	}

}