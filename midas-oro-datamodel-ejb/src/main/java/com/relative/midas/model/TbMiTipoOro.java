package com.relative.midas.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_mi_tipo_oro")
//@NamedQuery(name="TbMiTipoOro.findAll", query="SELECT t FROM TbMiTipoOro t")
public class TbMiTipoOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_TIPO_ORO_ID_GENERATOR", sequenceName="SEQ_TIPO_ORO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_TIPO_ORO_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Column(name="porcentaje_pureza")
	private BigDecimal porcentajePureza;

	private String quilate;
	
	@Column(name="precio_oro_cp")
	private BigDecimal precioOroCp;
	
	@Column(name="precio_oro_cd")
	private BigDecimal precioOroCd;
	
	@Column(name="precio_oro_venta")
	private BigDecimal precioOroVenta;
	
	public TbMiTipoOro() {
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

	public BigDecimal getPorcentajePureza() {
		return this.porcentajePureza;
	}

	public void setPorcentajePureza(BigDecimal porcentajePureza) {
		this.porcentajePureza = porcentajePureza;
	}

	public String getQuilate() {
		return this.quilate;
	}

	public void setQuilate(String quilate) {
		this.quilate = quilate;
	}

	public BigDecimal getPrecioOroCp() {
		return precioOroCp;
	}
	
	public void setPrecioOroCp(BigDecimal precioOroCp) {
		this.precioOroCp = precioOroCp;
	}
	
	public BigDecimal getPrecioOroCd() {
		return precioOroCd;
	}
	
	public void setPrecioOroCd(BigDecimal precioOroCd) {
		this.precioOroCd = precioOroCd;
	}

	public BigDecimal getPrecioOroVenta() {
		return precioOroVenta;
	}

	public void setPrecioOroVenta(BigDecimal precioOroVenta) {
		this.precioOroVenta = precioOroVenta;
	}

}