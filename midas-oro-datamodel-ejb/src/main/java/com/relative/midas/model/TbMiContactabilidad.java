package com.relative.midas.model;

import java.io.Serializable;
import java.sql.Timestamp;

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

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_contactabilidad database table.
 * 
 */
@Entity
@Table(name="tb_mi_contactabilidad")
public class TbMiContactabilidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CONTACTABILIDAD_ID_GENERATOR", sequenceName="SEQ_CONTACTABILIDAD",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CONTACTABILIDAD_ID_GENERATOR")
	private Long id;

	private String comentario;
	
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Column(name="fecha_actualizacion")
	private Timestamp fechaActualizacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;
	
	@Column(name="estado_Gestion")
	private String estadoGestion;

	
	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiAgente
	@ManyToOne
	@JoinColumn(name="id_agente")
	private TbMiAgente tbMiAgente;

	//bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbMiCliente tbMiCliente;

	//bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;

	public TbMiContactabilidad() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public String getEstadoGestion() {
		return estadoGestion;
	}

	public void setEstadoGestion(String estadoGestion) {
		this.estadoGestion = estadoGestion;
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

	public TbMiAgente getTbMiAgente() {
		return this.tbMiAgente;
	}

	public void setTbMiAgente(TbMiAgente tbMiAgente) {
		this.tbMiAgente = tbMiAgente;
	}

	public TbMiCliente getTbMiCliente() {
		return this.tbMiCliente;
	}

	public void setTbMiCliente(TbMiCliente tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}

	public TbMiContrato getTbMiContrato() {
		return this.tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

}