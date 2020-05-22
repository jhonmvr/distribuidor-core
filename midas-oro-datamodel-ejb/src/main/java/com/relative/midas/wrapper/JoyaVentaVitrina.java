package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.relative.midas.enums.ProcesoEnum;

public class JoyaVentaVitrina implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idJoya;
	private String codigoJoya;
	private Long idTipoJoya;
	private String detalleTipoJoya;
	private String comentarioJoya;
	private Long idTipoOro;
	private String quilateTipoOro;
	private Date fechaCreacionMovimiento;
	private BigDecimal pesoBruto;
	private Long idInventario;
	private Long idContrato;
	private Date fechaCreacionContrato;
	private Long idBodega;
	private String descripcionBodega;
	private Long idAgencia;
	private String codigoAgencia;
	private ProcesoEnum procesoContrato;
	private String codigoContrato;
	private BigDecimal pvp;
	private BigDecimal precioOroPvp;
	
	public JoyaVentaVitrina(Long idJoya, String codigoJoya, Long idTipoJoya, String detalleTipoJoya,
			String comentarioJoya, Long idTipoOro, String quilateTipoOro, Date fechaCreacionMovimiento,
			BigDecimal pesoBruto, Long idInventario, Long idContrato, Date fechaCreacionContrato, Long idBodega,
			String descripcionBodega, Long idAgencia, String codigoAgencia, ProcesoEnum procesoContrato,
			String codigoContrato,BigDecimal pvp,BigDecimal precioOroPvp) {
		this.idJoya = idJoya;
		this.codigoJoya = codigoJoya;
		this.idTipoJoya = idTipoJoya;
		this.detalleTipoJoya = detalleTipoJoya;
		this.comentarioJoya = comentarioJoya;
		this.idTipoOro = idTipoOro;
		this.quilateTipoOro = quilateTipoOro;
		this.fechaCreacionMovimiento = fechaCreacionMovimiento;
		this.pesoBruto = pesoBruto;
		this.idInventario = idInventario;
		this.idContrato = idContrato;
		this.fechaCreacionContrato = fechaCreacionContrato;
		this.idBodega = idBodega;
		this.descripcionBodega = descripcionBodega;
		this.idAgencia = idAgencia;
		this.codigoAgencia = codigoAgencia;
		this.procesoContrato = procesoContrato;
		this.codigoContrato = codigoContrato;
		this.pvp = pvp;
		this.precioOroPvp=precioOroPvp;
	}
	public Long getIdJoya() {
		return idJoya;
	}
	public void setIdJoya(Long idJoya) {
		this.idJoya = idJoya;
	}
	public String getCodigoJoya() {
		return codigoJoya;
	}
	public void setCodigoJoya(String codigoJoya) {
		this.codigoJoya = codigoJoya;
	}
	public Long getIdTipoJoya() {
		return idTipoJoya;
	}
	public void setIdTipoJoya(Long idTipoJoya) {
		this.idTipoJoya = idTipoJoya;
	}
	public String getDetalleTipoJoya() {
		return detalleTipoJoya;
	}
	public void setDetalleTipoJoya(String detalleTipoJoya) {
		this.detalleTipoJoya = detalleTipoJoya;
	}
	public String getComentarioJoya() {
		return comentarioJoya;
	}
	public void setComentarioJoya(String comentarioJoya) {
		this.comentarioJoya = comentarioJoya;
	}
	public Long getIdTipoOro() {
		return idTipoOro;
	}
	public void setIdTipoOro(Long idTipoOro) {
		this.idTipoOro = idTipoOro;
	}
	public String getQuilateTipoOro() {
		return quilateTipoOro;
	}
	public void setQuilateTipoOro(String quilateTipoOro) {
		this.quilateTipoOro = quilateTipoOro;
	}
	public Date getFechaCreacionMovimiento() {
		return fechaCreacionMovimiento;
	}
	public void setFechaCreacionMovimiento(Date fechaCreacionMovimiento) {
		this.fechaCreacionMovimiento = fechaCreacionMovimiento;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public Long getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}
	public Long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	public Date getFechaCreacionContrato() {
		return fechaCreacionContrato;
	}
	public void setFechaCreacionContrato(Date fechaCreacionContrato) {
		this.fechaCreacionContrato = fechaCreacionContrato;
	}
	public Long getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}
	public String getDescripcionBodega() {
		return descripcionBodega;
	}
	public void setDescripcionBodega(String descripcionBodega) {
		this.descripcionBodega = descripcionBodega;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	public ProcesoEnum getProcesoContrato() {
		return procesoContrato;
	}
	public void setProcesoContrato(ProcesoEnum procesoContrato) {
		this.procesoContrato = procesoContrato;
	}
	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public BigDecimal getPvp() {
		return pvp;
	}
	public void setPvp(BigDecimal pvp) {
		this.pvp = pvp;
	}
	public BigDecimal getPrecioOroPvp() {
		return precioOroPvp;
	}
	public void setPrecioOroPvp(BigDecimal precioOroPvp) {
		this.precioOroPvp = precioOroPvp;
	}
}
