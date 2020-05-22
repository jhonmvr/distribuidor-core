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

import com.relative.midas.enums.FormaPagoEnum;


@Entity
@Table(name="tb_mi_egreso")
public class TbMiEgreso implements Serializable {
	private static final Long serialVersionUID = 1L;



	@Id
	@SequenceGenerator(name="TB_MI_EGRESO_ID_GENERATOR", sequenceName="SEQ_EGRESO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_EGRESO_ID_GENERATOR")
	
	
	private Long id;
	
	
	

	//bi-directional many-to-one association to TbMiCotizacion
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato; 
	
	private BigDecimal  monto;	
	
	@Column(name="tipo_cuenta")
	private String tipoCuenta;

	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	private String banco;	
	

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	
	@Column(name="forma_pago")
	@Enumerated(EnumType.STRING)
	private FormaPagoEnum pago	;
	


	public FormaPagoEnum getPago() {
		return pago;
	}

	public void setPago(FormaPagoEnum pago) {
		this.pago = pago;
	}

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbMiContrato getTbMiContrato() {
		return tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
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

}
