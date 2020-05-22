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

import com.relative.midas.enums.EstadoJoyaEnum;


/**
 * The persistent class for the tb_mi_venta_lote database table.
 * 
 */
@Entity
@Table(name="tb_mi_venta_lote")
public class TbMiVentaLote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_VENTA_LOTE_ID_GENERATOR", sequenceName="SEQ_VENTA_LOTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_VENTA_LOTE_ID_GENERATOR")
	private Long id;

	private String codigo;

	@Column(name="descripcion_forma_pago")
	private String descripcionFormaPago;

	private BigDecimal descuento;

	@Enumerated(EnumType.STRING)
	private EstadoJoyaEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="medida_conversion")
	private BigDecimal medidaConversion;

	@Column(name="precio_gramo")
	private BigDecimal precioGramo;

	@Column(name="precio_onza_troy")
	private BigDecimal precioOnzaTroy;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiLote
	@OneToMany(mappedBy="tbMiVentaLote")
	private List<TbMiLote> tbMiLotes;
	
	@Column(name="valor")
	private BigDecimal valor;

	//bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbMiCliente tbMiCliente;

	@Column(name="total_gramos_negociados")
	private BigDecimal totalGramosNegociados;
	
	@Column(name="valor_iva")
	private BigDecimal valorIva;
	
	@Column(name="porcentaje_iva")
	private BigDecimal porcentajeIva;
	
	public TbMiVentaLote() {
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

	public String getDescripcionFormaPago() {
		return this.descripcionFormaPago;
	}

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
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

	public BigDecimal getMedidaConversion() {
		return this.medidaConversion;
	}

	public void setMedidaConversion(BigDecimal medidaConversion) {
		this.medidaConversion = medidaConversion;
	}

	public BigDecimal getPrecioGramo() {
		return this.precioGramo;
	}

	public void setPrecioGramo(BigDecimal precioGramo) {
		this.precioGramo = precioGramo;
	}

	public BigDecimal getPrecioOnzaTroy() {
		return this.precioOnzaTroy;
	}

	public void setPrecioOnzaTroy(BigDecimal precioOnzaTroy) {
		this.precioOnzaTroy = precioOnzaTroy;
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
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<TbMiLote> getTbMiLotes() {
		return this.tbMiLotes;
	}

	public void setTbMiLotes(List<TbMiLote> tbMiLotes) {
		this.tbMiLotes = tbMiLotes;
	}

	public TbMiLote addTbMiLote(TbMiLote tbMiLote) {
		getTbMiLotes().add(tbMiLote);
		tbMiLote.setTbMiVentaLote(this);

		return tbMiLote;
	}

	public TbMiLote removeTbMiLote(TbMiLote tbMiLote) {
		getTbMiLotes().remove(tbMiLote);
		tbMiLote.setTbMiVentaLote(null);

		return tbMiLote;
	}

	public TbMiCliente getTbMiCliente() {
		return this.tbMiCliente;
	}

	public void setTbMiCliente(TbMiCliente tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}



	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(BigDecimal porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public BigDecimal getTotalGramosNegociados() {
		return totalGramosNegociados;
	}

	public void setTotalGramosNegociados(BigDecimal totalGramosNegociados) {
		this.totalGramosNegociados = totalGramosNegociados;
	}

}