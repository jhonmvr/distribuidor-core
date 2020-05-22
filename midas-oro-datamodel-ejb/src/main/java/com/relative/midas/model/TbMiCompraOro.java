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
 * The persistent class for the tb_mi_compra_oro database table.
 * 
 */
@Entity
@Table(name="tb_mi_compra_oro")
public class TbMiCompraOro implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_COMPRA_ORO_ID_GENERATOR", sequenceName="SEQ_COMPRA_ORO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_COMPRA_ORO_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="porcentaje_descuento")
	private BigDecimal porcentajeDescuento;

	@Column(name="porcentaje_pureza")
	private BigDecimal porcentajePureza;

	@Column(name="precio_oro_cd")
	private BigDecimal precioOroCd;

	@Column(name="precio_oro_cp")
	private BigDecimal precioOroCp;

	@Column(name="precio_oro_venta")
	private BigDecimal precioOroVenta;
	
	private String quilate;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	private BigDecimal valor;

	//bi-directional many-to-one association to TbMiFunda
	@ManyToOne
	@JoinColumn(name="id_funda")
	private TbMiFunda tbMiFunda;

	public TbMiCompraOro() {
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

	public BigDecimal getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public BigDecimal getPorcentajePureza() {
		return this.porcentajePureza;
	}

	public void setPorcentajePureza(BigDecimal porcentajePureza) {
		this.porcentajePureza = porcentajePureza;
	}

	public BigDecimal getPrecioOroCd() {
		return this.precioOroCd;
	}

	public void setPrecioOroCd(BigDecimal precioOroCd) {
		this.precioOroCd = precioOroCd;
	}

	public BigDecimal getPrecioOroCp() {
		return this.precioOroCp;
	}

	public void setPrecioOroCp(BigDecimal precioOroCp) {
		this.precioOroCp = precioOroCp;
	}

	public String getQuilate() {
		return this.quilate;
	}

	public void setQuilate(String quilate) {
		this.quilate = quilate;
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TbMiFunda getTbMiFunda() {
		return this.tbMiFunda;
	}

	public void setTbMiFunda(TbMiFunda tbMiFunda) {
		this.tbMiFunda = tbMiFunda;
	}

	public BigDecimal getPrecioOroVenta() {
		return precioOroVenta;
	}

	public void setPrecioOroVenta(BigDecimal precioOroVenta) {
		this.precioOroVenta = precioOroVenta;
	}

}