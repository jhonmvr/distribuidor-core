package com.relative.midas.model;


import java.io.Serializable;
import java.util.List;

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

import com.relative.midas.enums.EstadoFundaEnum;


/**
 * The persistent class for the tb_mi_funda database table.
 * 
 */
@Entity
@Table(name="tb_mi_funda")
//@NamedQuery(name="TbMiFunda.findAll", query="SELECT t FROM TbMiFunda t")
public class TbMiFunda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_FUNDA_ID_GENERATOR", sequenceName="SEQ_FUNDA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_FUNDA_ID_GENERATOR")
	private Long id;

	private String codigo;

	private String comentario;

	@Enumerated(EnumType.STRING) private EstadoFundaEnum estado;
	
	@ManyToOne
	@JoinColumn(name="id_bodega")
	private TbMiBodega tbMiBodega;


	//bi-directional many-to-one association to TbMiJoya
	@OneToMany(mappedBy="tbMiFunda")
	private List<TbMiJoya> tbMiJoyas;
	
	
	@ManyToOne
	@JoinColumn(name="id_funda_rango")
	private TbMiFundaRango tbMifundaRango;
	
	

	public TbMiFundaRango getTbMifundaRango() {
		return tbMifundaRango;
	}

	public void setTbMifundaRango(TbMiFundaRango tbMifundaRango) {
		this.tbMifundaRango = tbMifundaRango;
	}

	public TbMiFunda() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoFundaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoFundaEnum estado) {
		this.estado = estado;
	}

	public List<TbMiJoya> getTbMiJoyas() {
		return this.tbMiJoyas;
	}

	public void setTbMiJoyas(List<TbMiJoya> tbMiJoyas) {
		this.tbMiJoyas = tbMiJoyas;
	}

	public TbMiJoya addTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().add(tbMiJoya);
		tbMiJoya.setTbMiFunda(this);

		return tbMiJoya;
	}

	public TbMiJoya removeTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().remove(tbMiJoya);
		tbMiJoya.setTbMiFunda(null);

		return tbMiJoya;
	}

	public TbMiBodega getTbMiBodega() {
		return tbMiBodega;
	}

	public void setTbMiBodega(TbMiBodega tbMiBodega) {
		this.tbMiBodega = tbMiBodega;
	}
	
	

}