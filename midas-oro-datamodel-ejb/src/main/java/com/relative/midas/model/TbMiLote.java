package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.TipoLoteEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_mi_lote database table.
 * 
 */
@Entity
@Table(name="tb_mi_lote")
public class TbMiLote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_LOTE_ID_GENERATOR", sequenceName="SEQ_LOTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_LOTE_ID_GENERATOR")
	private Long id;

	@Column(name="costo_por_gramo")
	private BigDecimal costoPorGramo;

	@Column(name="descuento_compra")
	private BigDecimal descuentoCompra;

	@Column(name="descuento_retazado")
	private BigDecimal descuentoRetazado;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cierre")
	private Date fechaCierre;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String identificador;

	private BigDecimal ley;

	@Column(name="nombre_lote")
	private String nombreLote;

	@Column(name="peso_bruto_compra")
	private BigDecimal pesoBrutoCompra;

	@Column(name="peso_bruto_retazado")
	private BigDecimal pesoBrutoRetazado;

	@Column(name="peso_neto_compra")
	private BigDecimal pesoNetoCompra;

	@Column(name="peso_neto_retazado")
	private BigDecimal pesoNetoRetazado;

	@Column(name="peso_neto_vendido")
	private BigDecimal pesoNetoVendido;

	@Column(name="precio_compra")
	private BigDecimal precioCompra;

	@Column(name="precio_venta")
	private BigDecimal precioVenta;

	private String responsable;
	
	@Column(name="tipo_lote")
	@Enumerated(EnumType.STRING)
	private TipoLoteEnum tipoLote;

	@Column(name="total_gramos")
	private BigDecimal totalGramos;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	private BigDecimal utilidad;
	
	@Column(name="porcentaje_iva")
	private BigDecimal porcentajeIva;
	
	@Column(name="iva")
	private BigDecimal iva;
	
	@Column(name="valor_pagar")
	private BigDecimal valorPagar;

	//bi-directional many-to-one association to TbMiJoyaLote
	@OneToMany(mappedBy="tbMiLote")
	private List<TbMiJoyaLote> tbMiJoyaLotes;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiTipoOro
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbMiTipoOro tbMiTipoOro;

	//bi-directional many-to-one association to TbMiVentaLote
	@ManyToOne
	@JoinColumn(name="id_venta_lote")
	private TbMiVentaLote tbMiVentaLote;

	public TbMiLote() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCostoPorGramo() {
		return this.costoPorGramo;
	}

	public void setCostoPorGramo(BigDecimal costoPorGramo) {
		this.costoPorGramo = costoPorGramo;
	}

	public BigDecimal getDescuentoCompra() {
		return this.descuentoCompra;
	}

	public void setDescuentoCompra(BigDecimal descuentoCompra) {
		this.descuentoCompra = descuentoCompra;
	}

	public BigDecimal getDescuentoRetazado() {
		return this.descuentoRetazado;
	}

	public void setDescuentoRetazado(BigDecimal descuentoRetazado) {
		this.descuentoRetazado = descuentoRetazado;
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

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public BigDecimal getLey() {
		return this.ley;
	}

	public void setLey(BigDecimal ley) {
		this.ley = ley;
	}

	public String getNombreLote() {
		return this.nombreLote;
	}

	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	public BigDecimal getPesoBrutoCompra() {
		return this.pesoBrutoCompra;
	}

	public void setPesoBrutoCompra(BigDecimal pesoBrutoCompra) {
		this.pesoBrutoCompra = pesoBrutoCompra;
	}

	public BigDecimal getPesoBrutoRetazado() {
		return this.pesoBrutoRetazado;
	}

	public void setPesoBrutoRetazado(BigDecimal pesoBrutoRetazado) {
		this.pesoBrutoRetazado = pesoBrutoRetazado;
	}

	public BigDecimal getPesoNetoCompra() {
		return this.pesoNetoCompra;
	}

	public void setPesoNetoCompra(BigDecimal pesoNetoCompra) {
		this.pesoNetoCompra = pesoNetoCompra;
	}

	public BigDecimal getPesoNetoRetazado() {
		return this.pesoNetoRetazado;
	}

	public void setPesoNetoRetazado(BigDecimal pesoNetoRetazado) {
		this.pesoNetoRetazado = pesoNetoRetazado;
	}

	public BigDecimal getPesoNetoVendido() {
		return this.pesoNetoVendido;
	}

	public void setPesoNetoVendido(BigDecimal pesoNetoVendido) {
		this.pesoNetoVendido = pesoNetoVendido;
	}

	public BigDecimal getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public TipoLoteEnum getTipoLote() {
		return this.tipoLote;
	}

	public void setTipoLote(TipoLoteEnum tipoLote) {
		this.tipoLote = tipoLote;
	}

	public BigDecimal getTotalGramos() {
		return this.totalGramos;
	}

	public void setTotalGramos(BigDecimal totalGramos) {
		this.totalGramos = totalGramos;
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

	public BigDecimal getUtilidad() {
		return this.utilidad;
	}

	public void setUtilidad(BigDecimal utilidad) {
		this.utilidad = utilidad;
	}

	
	
	public BigDecimal getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(BigDecimal porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}

	public List<TbMiJoyaLote> getTbMiJoyaLotes() {
		return this.tbMiJoyaLotes;
	}

	public void setTbMiJoyaLotes(List<TbMiJoyaLote> tbMiJoyaLotes) {
		this.tbMiJoyaLotes = tbMiJoyaLotes;
	}

	public TbMiJoyaLote addTbMiJoyaLote(TbMiJoyaLote tbMiJoyaLote) {
		getTbMiJoyaLotes().add(tbMiJoyaLote);
		tbMiJoyaLote.setTbMiLote(this);

		return tbMiJoyaLote;
	}

	public TbMiJoyaLote removeTbMiJoyaLote(TbMiJoyaLote tbMiJoyaLote) {
		getTbMiJoyaLotes().remove(tbMiJoyaLote);
		tbMiJoyaLote.setTbMiLote(null);

		return tbMiJoyaLote;
	}

	public TbMiAgencia getTbMiAgencia() {
		return this.tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public TbMiTipoOro getTbMiTipoOro() {
		return this.tbMiTipoOro;
	}

	public void setTbMiTipoOro(TbMiTipoOro tbMiTipoOro) {
		this.tbMiTipoOro = tbMiTipoOro;
	}

	public TbMiVentaLote getTbMiVentaLote() {
		return this.tbMiVentaLote;
	}

	public void setTbMiVentaLote(TbMiVentaLote tbMiVentaLote) {
		this.tbMiVentaLote = tbMiVentaLote;
	}

}