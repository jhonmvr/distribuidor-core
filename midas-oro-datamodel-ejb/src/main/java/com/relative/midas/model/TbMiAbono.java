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

import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.TipoTarjetaEnum;

@Entity
@Table(name="tb_mi_abono")
public class TbMiAbono implements Serializable {
	
	/**
	 * Tabla de abonos
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_MI_ABONO_ID_GENERATOR", sequenceName="SEQ_ABONO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_ABONO_ID_GENERATOR")
	private Long id;
	
	@Column(name="tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name="forma_pago")
	@Enumerated(EnumType.STRING)
	private FormaPagoEnum formaPago;
	
	@ManyToOne
	@JoinColumn(name="id_banco")
	private TbMiBanco tbMiBanco;
	
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="valor")
	private BigDecimal valor;
	
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	
	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	
	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;
	
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;
	
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
	
	@OneToMany(mappedBy="tbMiAbono")
	private List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public FormaPagoEnum getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPagoEnum formaPago) {
		this.formaPago = formaPago;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public EstadoMidasEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public TbMiContrato getTbMiContrato() {
		return tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

	public TbMiCliente getTbMiCliente() {
		return tbMiCliente;
	}

	public void setTbMiCliente(TbMiCliente tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}


	public TbMiBanco getTbMiBanco() {
		return tbMiBanco;
	}

	public void setTbMiBanco(TbMiBanco tbMiBanco) {
		this.tbMiBanco = tbMiBanco;
	}

	public List<TbMiDocumentoHabilitante> getTbMiDocumentoHabilitantes() {
		return tbMiDocumentoHabilitantes;
	}

	public void setTbMiDocumentoHabilitantes(List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes) {
		this.tbMiDocumentoHabilitantes = tbMiDocumentoHabilitantes;
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
	
	
	
}
