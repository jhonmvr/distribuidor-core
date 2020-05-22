package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.midas.enums.EstadoMidasEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_mi_corte_caja database table.
 * 
 */
@Entity
@Table(name="tb_mi_corte_caja")
public class TbMiCorteCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CORTE_CAJA_ID_GENERATOR", sequenceName="SEQ_CORTE_CAJA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CORTE_CAJA_ID_GENERATOR")
	private Long id;

	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_apertura")
	private Date fechaApertura;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cierre")
	private Date fechaCierre;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	private String justificacion;
	
	@Column(name="valor_apertura")
	private BigDecimal valorApertura;

	@Column(name="valor_cierre")
	private BigDecimal valorCierre;
	

	@Column(name="diferencia")
	private BigDecimal diferencia;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	//bi-directional many-to-one association to TbMiAgente
	@ManyToOne
	@JoinColumn(name="id_agente")
	private TbMiAgente tbMiAgente;

	//bi-directional many-to-one association to TbMiCaja
	@ManyToOne
	@JoinColumn(name="id_caja")
	private TbMiCaja tbMiCaja;
	
	//bi-directional many-to-one association to TbMiDetalleCaja
	@OneToMany(mappedBy="tbMiCorteCaja")
	private List<TbMiDetalleCaja> tbMiDetalleCajas;

	public TbMiCorteCaja() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
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

	public BigDecimal getValorApertura() {
		return this.valorApertura;
	}

	public void setValorApertura(BigDecimal valorApertura) {
		this.valorApertura = valorApertura;
	}

	public BigDecimal getValorCierre() {
		return this.valorCierre;
	}

	public void setValorCierre(BigDecimal valorCierre) {
		this.valorCierre = valorCierre;
	}

	public TbMiAgencia getTbMiAgencia() {
		return this.tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public TbMiAgente getTbMiAgente() {
		return this.tbMiAgente;
	}

	public void setTbMiAgente(TbMiAgente tbMiAgente) {
		this.tbMiAgente = tbMiAgente;
	}

	public TbMiCaja getTbMiCaja() {
		return this.tbMiCaja;
	}

	public void setTbMiCaja(TbMiCaja tbMiCaja) {
		this.tbMiCaja = tbMiCaja;
	}
	
	public List<TbMiDetalleCaja> getTbMiDetalleCajas() {
		return this.tbMiDetalleCajas;
	}

	public void setTbMiDetalleCajas(List<TbMiDetalleCaja> tbMiDetalleCajas) {
		this.tbMiDetalleCajas = tbMiDetalleCajas;
	}

	public TbMiDetalleCaja addTbMiDetalleCaja(TbMiDetalleCaja tbMiDetalleCaja) {
		getTbMiDetalleCajas().add(tbMiDetalleCaja);
		tbMiDetalleCaja.setTbMiCorteCaja(this);

		return tbMiDetalleCaja;
	}

	public TbMiDetalleCaja removeTbMiDetalleCaja(TbMiDetalleCaja tbMiDetalleCaja) {
		getTbMiDetalleCajas().remove(tbMiDetalleCaja);
		tbMiDetalleCaja.setTbMiCorteCaja(null);

		return tbMiDetalleCaja;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public BigDecimal getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	
}