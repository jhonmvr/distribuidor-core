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
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;
import com.relative.midas.enums.TipoTarjetaEnum;


/**
 * The persistent class for the tb_mi_movimiento_caja database table.
 * 
 */
@Entity
@Table(name="tb_mi_movimiento_caja")
//@NamedQuery(name="TbMiMovimientoCaja.findAll", query="SELECT t FROM TbMiMovimientoCaja t")
public class TbMiMovimientoCaja implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_MOVIMIENTO_CAJA_ID_GENERATOR", sequenceName="SEQ_MOVIMIENTO_CAJA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_MOVIMIENTO_CAJA_ID_GENERATOR")
	private Long id;

	private BigDecimal egreso;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;
	
	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="forma_pago")
	@Enumerated(EnumType.STRING)
	private FormaPagoEnum formaPago;

	private BigDecimal ingreso;

	@Column(name="numero_cuenta_banco")
	private String numeroCuentaBanco;

	private String tipo;

	@Column(name="tipo_cuenta_banco")
	private String tipoCuentaBanco;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to TbMiBanco
	@ManyToOne
	@JoinColumn(name="id_banco")
	private TbMiBanco tbMiBanco;

	//bi-directional many-to-one association to TbMiCaja
	@ManyToOne
	@JoinColumn(name="id_caja")
	private TbMiCaja tbMiCaja;

	//bi-directional many-to-one association to TbMiCuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private TbMiCuenta tbMiCuenta;

	//bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbMiCliente tbMiCliente;
	
	
	@Column(name="tipo_tarjeta")
	@Enumerated(EnumType.STRING)
	private TipoTarjetaEnum tipoTarjeta	;
	
	@Column(name="numero_tarjeta")
	private String numeroTarjeta;
	
	@Column(name="habiente_tarjeta")
	private String habienteTarjeta;
	
	@Column(name="ced_habiente_tarjeta")
	private String cedHabienteTarjeta;
	
	private String comentario;
	

	//bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;
	
	@ManyToOne
	@JoinColumn(name="id_venta_lote")
	private TbMiVentaLote tbMiVentaLote;
	
	@ManyToOne
	@JoinColumn(name="id_corte_caja")
	private TbMiCorteCaja tbMiCorteCaja;

	public TbMiMovimientoCaja() {
	}

	public TbMiCorteCaja getTbMiCorteCaja() {
		return tbMiCorteCaja;
	}

	public void setTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		this.tbMiCorteCaja = tbMiCorteCaja;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getEgreso() {
		return this.egreso;
	}

	public void setEgreso(BigDecimal egreso) {
		this.egreso = egreso;
	}

	public EstadoMidasEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}
	
	public TipoTarjetaEnum getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjetaEnum tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getHabienteTarjeta() {
		return habienteTarjeta;
	}

	public void setHabienteTarjeta(String habienteTarjeta) {
		this.habienteTarjeta = habienteTarjeta;
	}

	public String getCedHabienteTarjeta() {
		return cedHabienteTarjeta;
	}

	public void setCedHabienteTarjeta(String cedHabienteTarjeta) {
		this.cedHabienteTarjeta = cedHabienteTarjeta;
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

	public FormaPagoEnum getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(FormaPagoEnum formaPago) {
		this.formaPago = formaPago;
	}

	public BigDecimal getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}

	public String getNumeroCuentaBanco() {
		return this.numeroCuentaBanco;
	}

	public void setNumeroCuentaBanco(String numeroCuentaBanco) {
		this.numeroCuentaBanco = numeroCuentaBanco;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoCuentaBanco() {
		return this.tipoCuentaBanco;
	}

	public void setTipoCuentaBanco(String tipoCuentaBanco) {
		this.tipoCuentaBanco = tipoCuentaBanco;
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

	public TbMiBanco getTbMiBanco() {
		return this.tbMiBanco;
	}

	public void setTbMiBanco(TbMiBanco tbMiBanco) {
		this.tbMiBanco = tbMiBanco;
	}

	public TbMiCaja getTbMiCaja() {
		return this.tbMiCaja;
	}

	public void setTbMiCaja(TbMiCaja tbMiCaja) {
		this.tbMiCaja = tbMiCaja;
	}

	public TbMiCuenta getTbMiCuenta() {
		return this.tbMiCuenta;
	}

	public void setTbMiCuenta(TbMiCuenta tbMiCuenta) {
		this.tbMiCuenta = tbMiCuenta;
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

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	
	

	public TbMiVentaLote getTbMiVentaLote() {
		return tbMiVentaLote;
	}

	public void setTbMiVentaLote(TbMiVentaLote tbMiVentaLote) {
		this.tbMiVentaLote = tbMiVentaLote;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}