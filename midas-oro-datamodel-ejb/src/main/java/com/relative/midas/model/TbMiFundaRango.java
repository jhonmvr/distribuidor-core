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

import com.relative.midas.enums.EstadoMidasEnum;


@Entity
@Table(name="tb_mi_funda_rango")

public class TbMiFundaRango implements Serializable {
	
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@SequenceGenerator(name="TB_MI_FUNDA_RANGO_ID_GENERATOR", sequenceName="SEQ_FUNDA_RANGO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_FUNDA_RANGO_ID_GENERATOR")
	private Long id;	
	
	
	

	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;

	
	
	@OneToMany(mappedBy="tbMifundaRango")
	private List<TbMiFunda> tbMiFunda;
	
	@Column(name="rango_inicial")
	private BigDecimal rangoInicial;
	
	
	public String getNombreFundas() {
		return nombreFundas;
	}

	public void setNombreFundas(String nombreFundas) {
		this.nombreFundas = nombreFundas;
	}

	@Column(name="rango_final")
	private BigDecimal rangoFinal;
	
	@Column(name="nombre_fundas")
	private String nombreFundas;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
		
	
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbMiAgencia getTbMiAgencia() {
		return tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public List<TbMiFunda> getTbMiFunda() {
		return tbMiFunda;
	}

	public void setTbMiFunda(List<TbMiFunda> tbMiFunda) {
		this.tbMiFunda = tbMiFunda;
	}

	public BigDecimal getRangoInicial() {
		return rangoInicial;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setRangoInicial(BigDecimal rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	public BigDecimal getRangoFinal() {
		return rangoFinal;
	}

	public void setRangoFinal(BigDecimal rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	
	public EstadoMidasEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	

	
	
	
	
	
	
	
	
	
	

}
