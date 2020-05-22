package com.relative.midas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 * The persistent class for the tb_mi_joya database table.
 * 
 */
@Entity
@Table(name="tb_mi_contrato_calculo")
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
public class TbMiContratoCalculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_CONTRATO_CALCULO_ID_GENERATOR", sequenceName="SEQ_CONTRATO_CALCULO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_CONTRATO_CALCULO_ID_GENERATOR")
	private Long id;

	private String rubro;

	private BigDecimal valor;
	
	private String usuario;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	//bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;

	//bi-directional many-to-one association to TbMiAprobarContrato
	@ManyToOne
	@JoinColumn(name="id_aprobar")
	private TbMiAprobarContrato tbMiAprobarContrato;
	
	public TbMiContratoCalculo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public TbMiContrato getTbMiContrato() {
		return tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

	public TbMiAprobarContrato getTbMiAprobarContrato() {
		return tbMiAprobarContrato;
	}

	public void setTbMiAprobarContrato(TbMiAprobarContrato tbMiAprobarContrato) {
		this.tbMiAprobarContrato = tbMiAprobarContrato;
	}

	
	

}