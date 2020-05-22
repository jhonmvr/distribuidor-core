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

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_joya_sim database table.
 * 
 */
@Entity
@Table(name="tb_mi_joya_sim")
//@NamedQuery(name="TbMiJoyaSim.findAll", query="SELECT t FROM TbMiJoyaSim t")
public class TbMiJoyaSim implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_JOYA_SIM_ID_GENERATOR", sequenceName="SEQ_JOYA_SIM",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_JOYA_SIM_ID_GENERATOR")
	private Long id;

	private String condicion;

	private String descripcion;

	private BigDecimal descuento;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;

	@Column(name="peso_neto")
	private BigDecimal pesoNeto;

	@Column(name="precio_cd")
	private BigDecimal precioCd;

	@Column(name="precio_cp")
	private BigDecimal precioCp;
	
	@Column(name="precio_compra_cd")
	private BigDecimal precioCompraCD;

	@Column(name="precio_compra_cp")
	private BigDecimal precioCompraCP;

	//bi-directional many-to-one association to TbMiCotizacion
	@ManyToOne
	@JoinColumn(name="id_cotizacion")
	private TbMiCotizacion tbMiCotizacion;

	//bi-directional many-to-one association to TbMiTipoJoya
	@ManyToOne
	@JoinColumn(name="id_tipo_joya")
	private TbMiTipoJoya tbMiTipoJoya;

	//bi-directional many-to-one association to TbMiTipoOro
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbMiTipoOro tbMiTipoOro;
	
	
	@ManyToOne
	@JoinColumn(name="id_aprobar")
	private TbMiAprobarContrato tbMiAprobarContrato;
	
	
	public TbMiJoyaSim() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public BigDecimal getPrecioCd() {
		return this.precioCd;
	}

	public void setPrecioCd(BigDecimal precioCd) {
		this.precioCd = precioCd;
	}

	public BigDecimal getPrecioCp() {
		return this.precioCp;
	}

	public void setPrecioCp(BigDecimal precioCp) {
		this.precioCp = precioCp;
	}

	public TbMiCotizacion getTbMiCotizacion() {
		return this.tbMiCotizacion;
	}

	public void setTbMiCotizacion(TbMiCotizacion tbMiCotizacion) {
		this.tbMiCotizacion = tbMiCotizacion;
	}

	public TbMiTipoJoya getTbMiTipoJoya() {
		return this.tbMiTipoJoya;
	}

	public void setTbMiTipoJoya(TbMiTipoJoya tbMiTipoJoya) {
		this.tbMiTipoJoya = tbMiTipoJoya;
	}

	public TbMiTipoOro getTbMiTipoOro() {
		return this.tbMiTipoOro;
	}

	public void setTbMiTipoOro(TbMiTipoOro tbMiTipoOro) {
		this.tbMiTipoOro = tbMiTipoOro;
	}

	public BigDecimal getPrecioCompraCD() {
		return precioCompraCD;
	}

	public void setPrecioCompraCD(BigDecimal precioCompraCD) {
		this.precioCompraCD = precioCompraCD;
	}

	public BigDecimal getPrecioCompraCP() {
		return precioCompraCP;
	}

	public void setPrecioCompraCP(BigDecimal precioCompraCP) {
		this.precioCompraCP = precioCompraCP;
	}

	public TbMiAprobarContrato getTbMiAprobarContrato() {
		return tbMiAprobarContrato;
	}

	public void setTbMiAprobarContrato(TbMiAprobarContrato tbMiAprobarContrato) {
		this.tbMiAprobarContrato = tbMiAprobarContrato;
	}

}