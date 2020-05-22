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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;


/**
 * The persistent class for the tb_mi_cotizacion database table.
 * 
 */
@Entity
@Table(name="tb_mi_cotizacion")
//@NamedQuery(name="TbMiCotizacion.findAll", query="SELECT t FROM TbMiCotizacion t")
public class TbMiCotizacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_COTIZACION_ID_GENERATOR", sequenceName="SEQ_COTIZACION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_COTIZACION_ID_GENERATOR")
	private Long id;

	private String descripcion;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="monto_cotizacion")
	private BigDecimal montoCotizacion;
	
	@Column(name="nombre_cotizacion")
	private String nombreCotizacion;
	
	private BigDecimal tasacion;
	private BigDecimal transporte;
	private BigDecimal custodia;
	@Column(name="gastos_administrativos")
	private BigDecimal gastosAdministrativos;
	@Column(name="total_cancelacion")
	private BigDecimal totalCancelacion;
	private BigDecimal desembolso;
	@Column(name="porcentaje_custodia")
	private String porcentajeCustodia;
	
	@Column(name="fecha_vencimiento")
	private String fechaVencimiento;
	
	
	

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public BigDecimal getTasacion() {
		return tasacion;
	}

	public void setTasacion(BigDecimal tasacion) {
		this.tasacion = tasacion;
	}

	public BigDecimal getTransporte() {
		return transporte;
	}

	public void setTransporte(BigDecimal transporte) {
		this.transporte = transporte;
	}

	public BigDecimal getCustodia() {
		return custodia;
	}

	public void setCustodia(BigDecimal custodia) {
		this.custodia = custodia;
	}

	public BigDecimal getGastosAdministrativos() {
		return gastosAdministrativos;
	}

	public void setGastosAdministrativos(BigDecimal gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}

	public BigDecimal getTotalCancelacion() {
		return totalCancelacion;
	}

	public void setTotalCancelacion(BigDecimal totalCancelacion) {
		this.totalCancelacion = totalCancelacion;
	}

	public BigDecimal getDesembolso() {
		return desembolso;
	}

	public void setDesembolso(BigDecimal desembolso) {
		this.desembolso = desembolso;
	}

	public String getPorcentajeCustodia() {
		return porcentajeCustodia;
	}

	public void setPorcentajeCustodia(String porcentajeCustodia) {
		this.porcentajeCustodia = porcentajeCustodia;
	}

	public BigDecimal getMontoCotizacion() {
		return montoCotizacion;
	}

	public void setMontoCotizacion(BigDecimal montoCotizacion) {
		this.montoCotizacion = montoCotizacion;
	}

	public String getNombreCotizacion() {
		return nombreCotizacion;
	}

	public void setNombreCotizacion(String nombreCotizacion) {
		this.nombreCotizacion = nombreCotizacion;
	}

	//bi-directional many-to-one association to TbMiJoyaSim
	@OneToMany(mappedBy="tbMiCotizacion")
	private List<TbMiJoyaSim> tbMiJoyaSims;

	public TbMiCotizacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	

	public List<TbMiJoyaSim> getTbMiJoyaSims() {
		return this.tbMiJoyaSims;
	}

	public void setTbMiJoyaSims(List<TbMiJoyaSim> tbMiJoyaSims) {
		this.tbMiJoyaSims = tbMiJoyaSims;
	}

	public TbMiJoyaSim addTbMiJoyaSim(TbMiJoyaSim tbMiJoyaSim) {
		getTbMiJoyaSims().add(tbMiJoyaSim);
		tbMiJoyaSim.setTbMiCotizacion(this);

		return tbMiJoyaSim;
	}

	public TbMiJoyaSim removeTbMiJoyaSim(TbMiJoyaSim tbMiJoyaSim) {
		getTbMiJoyaSims().remove(tbMiJoyaSim);
		tbMiJoyaSim.setTbMiCotizacion(null);

		return tbMiJoyaSim;
	}

}