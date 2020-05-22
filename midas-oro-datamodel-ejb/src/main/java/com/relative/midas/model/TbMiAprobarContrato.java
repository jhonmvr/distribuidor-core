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

import com.relative.midas.enums.EstadoAprobacionEnum;


/**
 * The persistent class for the tb_mi_aprobar_contrato database table.
 * 
 */
@Entity
@Table(name="tb_mi_aprobar_contrato")
//@NamedQuery(name="TbMiAprobarContrato.findAll", query="SELECT t FROM TbMiAprobarContrato t")
public class TbMiAprobarContrato implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_APROBAR_CONTRATO_ID_GENERATOR", sequenceName="SEQ_APROBAR_CONTRATO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_APROBAR_CONTRATO_ID_GENERATOR")
	private Long id;

	private BigDecimal aprobado;

	@Column(name = "canal_contacto")
	private String canalContacto;
	
	private String descripcion;

	@Enumerated(EnumType.STRING)
	private EstadoAprobacionEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="tipo_contrato")
	private String tipoContrato;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
	
	@Column(name="rol_aprobacion")
	private String rolAprobacion;
	
	@Column(name="rol_aprobacion_dos")
	private String rolAprobacionDos;
	
	@Column(name="nivel_aprobacion")
	private String nivelAprobacion;

	
	@Column(name="observacion_nivel_uno")
	private String observacionNivelUno;

	@Column(name="observacion_nivel_dos")
	private String observacionNivelDos;
	
	private BigDecimal valor;

	@Column(name="monto_cotizacion")
	private BigDecimal montoCotizacion;
	//bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbMiCliente tbMiCliente;

	//bi-directional many-to-one association to TbMiContratoCalculo
	@OneToMany(mappedBy="tbMiAprobarContrato")
	private List<TbMiContratoCalculo> tbMiContratoCalculos;
	
	@OneToMany(mappedBy="tbMiAprobarContrato")
	private List<TbMiJoyaSim> tbMiJoyaSims;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	public TbMiAprobarContrato() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAprobado() {
		return this.aprobado;
	}

	public void setAprobado(BigDecimal aprobado) {
		this.aprobado = aprobado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoAprobacionEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoAprobacionEnum estado) {
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

	public String getTipoContrato() {
		return this.tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getMontoCotizacion() {
		return montoCotizacion;
	}

	public void setMontoCotizacion(BigDecimal montoCotizacion) {
		this.montoCotizacion = montoCotizacion;
	}

	public TbMiCliente getTbMiCliente() {
		return this.tbMiCliente;
	}

	public void setTbMiCliente(TbMiCliente tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}

	public List<TbMiContratoCalculo> getTbMiContratoCalculos() {
		return this.tbMiContratoCalculos;
	}

	public void setTbMiContratoCalculos(List<TbMiContratoCalculo> tbMiContratoCalculos) {
		this.tbMiContratoCalculos = tbMiContratoCalculos;
	}

	public TbMiContratoCalculo addTbMiContratoCalculo(TbMiContratoCalculo tbMiContratoCalculo) {
		getTbMiContratoCalculos().add(tbMiContratoCalculo);
		tbMiContratoCalculo.setTbMiAprobarContrato(this);

		return tbMiContratoCalculo;
	}

	public TbMiContratoCalculo removeTbMiContratoCalculo(TbMiContratoCalculo tbMiContratoCalculo) {
		getTbMiContratoCalculos().remove(tbMiContratoCalculo);
		tbMiContratoCalculo.setTbMiAprobarContrato(null);

		return tbMiContratoCalculo;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getRolAprobacion() {
		return rolAprobacion;
	}

	public void setRolAprobacion(String rolAprobacion) {
		this.rolAprobacion = rolAprobacion;
	}

	public String getNivelAprobacion() {
		return nivelAprobacion;
	}

	public void setNivelAprobacion(String nivelAprobacion) {
		this.nivelAprobacion = nivelAprobacion;
	}

	public String getRolAprobacionDos() {
		return rolAprobacionDos;
	}

	public void setRolAprobacionDos(String rolAprobacionDos) {
		this.rolAprobacionDos = rolAprobacionDos;
	}

	public List<TbMiJoyaSim> getTbMiJoyaSims() {
		return tbMiJoyaSims;
	}

	public void setTbMiJoyaSims(List<TbMiJoyaSim> tbMiJoyaSims) {
		this.tbMiJoyaSims = tbMiJoyaSims;
	}

	public String getObservacionNivelUno() {
		return observacionNivelUno;
	}

	public void setObservacionNivelUno(String observacionNivelUno) {
		this.observacionNivelUno = observacionNivelUno;
	}

	public String getObservacionNivelDos() {
		return observacionNivelDos;
	}

	public void setObservacionNivelDos(String observacionNivelDos) {
		this.observacionNivelDos = observacionNivelDos;
	}

	public String getCanalContacto() {
		return canalContacto;
	}

	public void setCanalContacto(String canalContacto) {
		this.canalContacto = canalContacto;
	}
	
	

}