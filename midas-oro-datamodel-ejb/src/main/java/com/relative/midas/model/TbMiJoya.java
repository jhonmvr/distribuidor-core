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
import com.relative.midas.enums.ProcesoEnum;


/**
 * The persistent class for the tb_mi_joya database table.
 * 
 */
@Entity
@Table(name="tb_mi_joya")
public class TbMiJoya implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_JOYA_ID_GENERATOR", sequenceName="SEQ_JOYA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_JOYA_ID_GENERATOR")
	private Long id;

	@Column(name="codigo_joya")
	private String codigoJoya;

	private String comentario;
	
	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;

	private String condicion;

	private String usuario;
	
	private BigDecimal descuento;

	@Column(name="descuento_retazado")
	private BigDecimal descuentoRetazado;

	@Enumerated(EnumType.STRING)
	private EstadoJoyaEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private BigDecimal peso;
	
	@Column(name="precio_venta")
	private BigDecimal precioVenta;

	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;

	@Column(name="peso_bruto_retazado")
	private BigDecimal pesoBrutoRetazado;

	@Column(name="peso_neto_retazado")
	private BigDecimal pesoNetoRetazado;
	

	@Column(name="iva")
	private BigDecimal iva;
	
	//bi-directional many-to-one association to TbMiFunda
	
	private BigDecimal valor;

	//bi-directional many-to-one association to TbMiHistoricoJoya
	@OneToMany(mappedBy="tbMiJoya")
	private List<TbMiHistoricoJoya> tbMiHistoricoJoyas;

	//bi-directional many-to-one association to TbMiInventario
	@OneToMany(mappedBy="tbMiJoya")
	private List<TbMiInventario> tbMiInventarios;
	
	@OneToMany(mappedBy="tbMiJoya")
	private List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes;

	//bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name="comprador")
	private TbMiCliente comprador;
	
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;

	//bi-directional many-to-one association to TbMiFunda
	@ManyToOne
	@JoinColumn(name="id_funda")
	private TbMiFunda tbMiFunda;

	//bi-directional many-to-one association to TbMiTipoJoya
	@ManyToOne
	@JoinColumn(name="id_tipo_joya")
	private TbMiTipoJoya tbMiTipoJoya;

	//bi-directional many-to-one association to TbMiTipoOro
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbMiCompraOro tbMiCompraOro;

	//bi-directional many-to-one association to TbMiJoyaLote
	@OneToMany(mappedBy="tbMiJoya")
	private List<TbMiJoyaLote> tbMiJoyaLotes;

	public TbMiJoya() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoJoya() {
		return this.codigoJoya;
	}

	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public BigDecimal getDescuentoRetazado() {
		return this.descuentoRetazado;
	}

	public void setDescuentoRetazado(BigDecimal descuentoRetazado) {
		this.descuentoRetazado = descuentoRetazado;
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

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoBrutoRetazado() {
		return this.pesoBrutoRetazado;
	}

	public void setPesoBrutoRetazado(BigDecimal pesoBrutoRetazado) {
		this.pesoBrutoRetazado = pesoBrutoRetazado;
	}

	public BigDecimal getPesoNetoRetazado() {
		return this.pesoNetoRetazado;
	}

	public void setPesoNetoRetazado(BigDecimal pesoNetoRetazado) {
		this.pesoNetoRetazado = pesoNetoRetazado;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<TbMiHistoricoJoya> getTbMiHistoricoJoyas() {
		return this.tbMiHistoricoJoyas;
	}

	public void setTbMiHistoricoJoyas(List<TbMiHistoricoJoya> tbMiHistoricoJoyas) {
		this.tbMiHistoricoJoyas = tbMiHistoricoJoyas;
	}

	public TbMiHistoricoJoya addTbMiHistoricoJoya(TbMiHistoricoJoya tbMiHistoricoJoya) {
		getTbMiHistoricoJoyas().add(tbMiHistoricoJoya);
		tbMiHistoricoJoya.setTbMiJoya(this);

		return tbMiHistoricoJoya;
	}

	public TbMiHistoricoJoya removeTbMiHistoricoJoya(TbMiHistoricoJoya tbMiHistoricoJoya) {
		getTbMiHistoricoJoyas().remove(tbMiHistoricoJoya);
		tbMiHistoricoJoya.setTbMiJoya(null);

		return tbMiHistoricoJoya;
	}

	public List<TbMiInventario> getTbMiInventarios() {
		return this.tbMiInventarios;
	}

	
	
	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public TbMiCliente getComprador() {
		return comprador;
	}

	public void setComprador(TbMiCliente comprador) {
		this.comprador = comprador;
	}

	public void setTbMiInventarios(List<TbMiInventario> tbMiInventarios) {
		this.tbMiInventarios = tbMiInventarios;
	}

	public TbMiInventario addTbMiInventario(TbMiInventario tbMiInventario) {
		getTbMiInventarios().add(tbMiInventario);
		tbMiInventario.setTbMiJoya(this);

		return tbMiInventario;
	}

	public TbMiInventario removeTbMiInventario(TbMiInventario tbMiInventario) {
		getTbMiInventarios().remove(tbMiInventario);
		tbMiInventario.setTbMiJoya(null);

		return tbMiInventario;
	}

	public TbMiContrato getTbMiContrato() {
		return this.tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

	public TbMiFunda getTbMiFunda() {
		return this.tbMiFunda;
	}

	public void setTbMiFunda(TbMiFunda tbMiFunda) {
		this.tbMiFunda = tbMiFunda;
	}

	public TbMiTipoJoya getTbMiTipoJoya() {
		return this.tbMiTipoJoya;
	}

	public void setTbMiTipoJoya(TbMiTipoJoya tbMiTipoJoya) {
		this.tbMiTipoJoya = tbMiTipoJoya;
	}

	public List<TbMiJoyaLote> getTbMiJoyaLotes() {
		return this.tbMiJoyaLotes;
	}

	public void setTbMiJoyaLotes(List<TbMiJoyaLote> tbMiJoyaLotes) {
		this.tbMiJoyaLotes = tbMiJoyaLotes;
	}

	public TbMiJoyaLote addTbMiJoyaLote(TbMiJoyaLote tbMiJoyaLote) {
		getTbMiJoyaLotes().add(tbMiJoyaLote);
		tbMiJoyaLote.setTbMiJoya(this);

		return tbMiJoyaLote;
	}

	public TbMiJoyaLote removeTbMiJoyaLote(TbMiJoyaLote tbMiJoyaLote) {
		getTbMiJoyaLotes().remove(tbMiJoyaLote);
		tbMiJoyaLote.setTbMiJoya(null);

		return tbMiJoyaLote;
	}

	public List<TbMiDocumentoHabilitante> getTbMiDocumentoHabilitantes() {
		return tbMiDocumentoHabilitantes;
	}

	public void setTbMiDocumentoHabilitantes(List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes) {
		this.tbMiDocumentoHabilitantes = tbMiDocumentoHabilitantes;
	}

	public TbMiCompraOro getTbMiCompraOro() {
		return tbMiCompraOro;
	}

	public void setTbMiCompraOro(TbMiCompraOro tbMiCompraOro) {
		this.tbMiCompraOro = tbMiCompraOro;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	
	

}