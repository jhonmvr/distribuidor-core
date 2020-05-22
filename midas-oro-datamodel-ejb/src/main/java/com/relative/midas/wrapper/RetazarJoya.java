package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.relative.midas.enums.ProcesoEnum;

public class RetazarJoya implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idJoya;
	private Date fechaActual;
	private String codigoJoya;
	private Date fechaCompra;
	private String tipoJoya;
	private Date fechaActualizacionJoya;
	private String tipoOro;
	private Long idTipoOro;
	private Long idFunda;
	private String codigoFunda;
	private BigDecimal pesoBruto;
	private BigDecimal descuento;
	private BigDecimal pesoNeto;
	private BigDecimal precioCompra;
	private String codigoAgencia;
	private String descipcionBodega;
	private ProcesoEnum procesoContrato;
	private String codigoContrato;
	private String tipoCompra;
	
	public RetazarJoya(long idJoya, String codigoJoya, Date fechaCompra, String tipoJoya, Date fechaActualizacionJoya,
			String tipoOro, long idTipoOro, String codigoFunda, BigDecimal pesoBruto, BigDecimal descuento, BigDecimal pesoNeto,
			String codigoAgencia, String descipcionBodega, ProcesoEnum procesoContrato, String codigoContrato,Long idFunda,BigDecimal precioCompra,String tipoCompra) {
		super();
		LocalDate localDate = LocalDate.now();
		this.idJoya = idJoya;
		this.fechaActual = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.codigoJoya = codigoJoya;
		this.fechaCompra = fechaCompra;
		this.tipoJoya = tipoJoya;
		this.fechaActualizacionJoya = fechaActualizacionJoya;
		this.tipoOro = tipoOro;
		this.idTipoOro = idTipoOro;
		this.codigoFunda = codigoFunda;
		this.pesoBruto = pesoBruto;
		this.descuento = descuento;
		this.pesoNeto = pesoNeto;
		this.codigoAgencia = codigoAgencia;
		this.descipcionBodega = descipcionBodega;
		this.procesoContrato = procesoContrato;
		this.codigoContrato = codigoContrato;
		this.idFunda=idFunda;
		this.precioCompra=precioCompra;
		this.tipoCompra=tipoCompra;
	}
	
	public String getCodigoJoya() {
		return codigoJoya;
	}
	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getTipoJoya() {
		return tipoJoya;
	}
	public void setTipoJoya(String tipoJoya) {
		this.tipoJoya = tipoJoya;
	}
	public Date getFechaActualizacionJoya() {
		return fechaActualizacionJoya;
	}
	public void setFechaActualizacionJoya(Date fechaActualizacionJoya) {
		this.fechaActualizacionJoya = fechaActualizacionJoya;
	}
	public String getTipoOro() {
		return tipoOro;
	}
	public void setTipoOro(String tipoOro) {
		this.tipoOro = tipoOro;
	}
	
	public Long getIdTipoOro() {
		return idTipoOro;
	}

	public void setIdTipoOro(Long idTipoOro) {
		this.idTipoOro = idTipoOro;
	}

	public String getCodigoFunda() {
		return codigoFunda;
	}
	public void setCodigoFunda(String codigoFunda) {
		this.codigoFunda = codigoFunda;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	public String getUbicacion() {
		return this.descipcionBodega;
	}
	public void setUbicacion(String descipcionBodega) {
		this.descipcionBodega = descipcionBodega;
	}
	public ProcesoEnum getProcesoContrato() {
		return procesoContrato;
	}
	public void setProcesoContrato(ProcesoEnum procesoContrato) {
		this.procesoContrato = procesoContrato;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Long getIdJoya() {
		return idJoya;
	}

	public void setIdJoya(Long idJoya) {
		this.idJoya = idJoya;
	}

	public String getDescipcionBodega() {
		return descipcionBodega;
	}

	public void setDescipcionBodega(String descipcionBodega) {
		this.descipcionBodega = descipcionBodega;
	}

	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public Long getIdFunda() {
		return idFunda;
	}

	public void setIdFunda(Long idFunda) {
		this.idFunda = idFunda;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}
	
}
