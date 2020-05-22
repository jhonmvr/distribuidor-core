package com.relative.midas.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the tb_mi_historico_joya database table.
 * 
 */
@Entity
@Table(name="tb_mi_historico_joya")
public class TbMiHistoricoJoya implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_HISTORICO_JOYA_ID_GENERATOR", sequenceName="SEQ_HISTORICO_JOYA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_HISTORICO_JOYA_ID_GENERATOR")
	private Long id;

	private String comentario;

	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;
	
	private BigDecimal descuento;

	@Enumerated(EnumType.STRING)
	private EstadoJoyaEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_tipo_oro")
	private Long idTipoOro;

	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;
	
	@Column(name="valor")
	private BigDecimal valor;

	@Column(name="peso_neto")
	private BigDecimal pesoNeto;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiJoya
	@ManyToOne
	@JoinColumn(name="id_joya")
	private TbMiJoya tbMiJoya;

	public TbMiHistoricoJoya() {
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

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public Long getIdTipoOro() {
		return this.idTipoOro;
	}

	public void setIdTipoOro(Long idTipoOro) {
		this.idTipoOro = idTipoOro;
	}

	public BigDecimal getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoNeto() {
		return this.pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
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

	public TbMiJoya getTbMiJoya() {
		return this.tbMiJoya;
	}

	public void setTbMiJoya(TbMiJoya tbMiJoya) {
		this.tbMiJoya = tbMiJoya;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

}