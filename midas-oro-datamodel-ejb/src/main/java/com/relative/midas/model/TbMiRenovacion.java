package com.relative.midas.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tb_mi_renovacion database table.
 * 
 */
@Entity
@Table(name="tb_mi_renovacion")
//@NamedQuery(name="TbMiRenovacion.findAll", query="SELECT t FROM TbMiRenovacion t")
public class TbMiRenovacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_RENOVACION_ID_GENERATOR", sequenceName="SEQ_RENOVACION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_RENOVACION_ID_GENERATOR")
	private Long id;

	private BigDecimal multa;

	@Column(name="porcentaje_custodia")
	private BigDecimal porcentajeCustodia;

	@Column(name="porcentaje_gato_adm")
	private BigDecimal porcentajeGatoAdm;

	@Column(name="porcentaje_valoracion")
	private BigDecimal porcentajeValoracion;

	@Column(name="valor_custodia")
	private BigDecimal valorCustodia;

	@Column(name="valor_gato_adm")
	private BigDecimal valorGatoAdm;

	@Column(name="valor_valoracion")
	private BigDecimal valorValoracion;

	//bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;

	//bi-directional many-to-one association to TbMiRenovacion
	@ManyToOne
	@JoinColumn(name="id_renovacion_ant")
	private TbMiRenovacion tbMiRenovacion;

	//bi-directional many-to-one association to TbMiRenovacion
	@OneToMany(mappedBy="tbMiRenovacion")
	private List<TbMiRenovacion> tbMiRenovacions;

	public TbMiRenovacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMulta() {
		return this.multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public BigDecimal getPorcentajeCustodia() {
		return this.porcentajeCustodia;
	}

	public void setPorcentajeCustodia(BigDecimal porcentajeCustodia) {
		this.porcentajeCustodia = porcentajeCustodia;
	}

	public BigDecimal getPorcentajeGatoAdm() {
		return this.porcentajeGatoAdm;
	}

	public void setPorcentajeGatoAdm(BigDecimal porcentajeGatoAdm) {
		this.porcentajeGatoAdm = porcentajeGatoAdm;
	}

	public BigDecimal getPorcentajeValoracion() {
		return this.porcentajeValoracion;
	}

	public void setPorcentajeValoracion(BigDecimal porcentajeValoracion) {
		this.porcentajeValoracion = porcentajeValoracion;
	}

	public BigDecimal getValorCustodia() {
		return this.valorCustodia;
	}

	public void setValorCustodia(BigDecimal valorCustodia) {
		this.valorCustodia = valorCustodia;
	}

	public BigDecimal getValorGatoAdm() {
		return this.valorGatoAdm;
	}

	public void setValorGatoAdm(BigDecimal valorGatoAdm) {
		this.valorGatoAdm = valorGatoAdm;
	}

	public BigDecimal getValorValoracion() {
		return this.valorValoracion;
	}

	public void setValorValoracion(BigDecimal valorValoracion) {
		this.valorValoracion = valorValoracion;
	}

	public TbMiContrato getTbMiContrato() {
		return this.tbMiContrato;
	}

	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}

	public TbMiRenovacion getTbMiRenovacion() {
		return this.tbMiRenovacion;
	}

	public void setTbMiRenovacion(TbMiRenovacion tbMiRenovacion) {
		this.tbMiRenovacion = tbMiRenovacion;
	}

	public List<TbMiRenovacion> getTbMiRenovacions() {
		return this.tbMiRenovacions;
	}

	public void setTbMiRenovacions(List<TbMiRenovacion> tbMiRenovacions) {
		this.tbMiRenovacions = tbMiRenovacions;
	}

	public TbMiRenovacion addTbMiRenovacion(TbMiRenovacion tbMiRenovacion) {
		getTbMiRenovacions().add(tbMiRenovacion);
		tbMiRenovacion.setTbMiRenovacion(this);

		return tbMiRenovacion;
	}

	public TbMiRenovacion removeTbMiRenovacion(TbMiRenovacion tbMiRenovacion) {
		getTbMiRenovacions().remove(tbMiRenovacion);
		tbMiRenovacion.setTbMiRenovacion(null);

		return tbMiRenovacion;
	}

}