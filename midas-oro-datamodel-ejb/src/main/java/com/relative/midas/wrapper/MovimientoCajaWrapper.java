package com.relative.midas.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.FormaPagoEnum;
import com.relative.midas.enums.ProcesoEnum;

public class MovimientoCajaWrapper implements Serializable{
	
	private Date fechaActualizacion;
	private String usuarioCreacion;
	private String codigoAgencia;
	private String nombreCuenta;
	private FormaPagoEnum formaPago;
	private String tipo;
	private EstadoMidasEnum estado;
	private ProcesoEnum proceso;
	private String nombreBanco;
	private BigDecimal ingreso;
	private BigDecimal egreso;
	private String comentario;
	
	public MovimientoCajaWrapper(Date fechaActualizacion, String usuarioCreacion, String codigoAgencia,String nombreCuenta,
			FormaPagoEnum formaPago, String tipo, EstadoMidasEnum estado, ProcesoEnum proceso,// String nombreBanco,
			BigDecimal ingreso, BigDecimal egreso) {
		super();
		this.fechaActualizacion = fechaActualizacion;
		this.usuarioCreacion = usuarioCreacion;
		this.codigoAgencia = codigoAgencia;
		this.nombreCuenta = nombreCuenta;
		this.formaPago = formaPago;
		this.tipo = tipo;
		this.estado = estado;
		this.proceso = proceso;
		//this.nombreBanco = nombreBanco;
		this.ingreso = ingreso;
		this.egreso = egreso;
		//this.comentario = comentario;
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
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	public FormaPagoEnum getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(FormaPagoEnum formaPago) {
		this.formaPago = formaPago;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public EstadoMidasEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public BigDecimal getIngreso() {
		return ingreso;
	}
	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}
	public BigDecimal getEgreso() {
		return egreso;
	}
	public void setEgreso(BigDecimal egreso) {
		this.egreso = egreso;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	
	
	

}
