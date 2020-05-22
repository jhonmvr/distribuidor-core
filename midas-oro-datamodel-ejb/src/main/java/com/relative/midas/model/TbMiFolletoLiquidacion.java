package com.relative.midas.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.midas.enums.EstadoMidasEnum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_mi_folleto_liquidacion database table.
 * 
 */
@Entity
@Table(name="tb_mi_folleto_liquidacion")
public class TbMiFolletoLiquidacion implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_FOLLETO_LIQUIDACION_ID_GENERATOR", sequenceName="SEQ_FOLLETO_LIQUIDACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_FOLLETO_LIQUIDACION_ID_GENERATOR")
	private Long id;

	@Column(name="codigo_fin")
	private String codigoFin;

	@Column(name="codigo_inicio")
	private String codigoInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vigencia")
	private Date fechaVigencia;

	@Column(name="nombre_folleto")
	private String nombreFolleto;

	private BigDecimal total;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;
		
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;

	//bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbMiAgencia tbMiAgencia;
	
	private String codigo;
	
	private BigInteger inicio;
	
	private BigInteger fin;

	//bi-directional many-to-one association to TbMiLiquidacion
	@OneToMany(mappedBy="tbMiFolletoLiquidacion")
	private List<TbMiLiquidacion> tbMiLiquidacions;

	public TbMiFolletoLiquidacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoFin() {
		return this.codigoFin;
	}

	public void setCodigoFin(String codigoFin) {
		this.codigoFin = codigoFin;
	}

	public String getCodigoInicio() {
		return this.codigoInicio;
	}

	public void setCodigoInicio(String codigoInicio) {
		this.codigoInicio = codigoInicio;
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

	public Date getFechaVigencia() {
		return this.fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getNombreFolleto() {
		return this.nombreFolleto;
	}

	public void setNombreFolleto(String nombreFolleto) {
		this.nombreFolleto = nombreFolleto;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public TbMiAgencia getTbMiAgencia() {
		return this.tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public List<TbMiLiquidacion> getTbMiLiquidacions() {
		return this.tbMiLiquidacions;
	}

	public void setTbMiLiquidacions(List<TbMiLiquidacion> tbMiLiquidacions) {
		this.tbMiLiquidacions = tbMiLiquidacions;
	}

	public TbMiLiquidacion addTbMiLiquidacion(TbMiLiquidacion tbMiLiquidacion) {
		getTbMiLiquidacions().add(tbMiLiquidacion);
		tbMiLiquidacion.setTbMiFolletoLiquidacion(this);

		return tbMiLiquidacion;
	}

	public TbMiLiquidacion removeTbMiLiquidacion(TbMiLiquidacion tbMiLiquidacion) {
		getTbMiLiquidacions().remove(tbMiLiquidacion);
		tbMiLiquidacion.setTbMiFolletoLiquidacion(null);

		return tbMiLiquidacion;
	}

	public EstadoMidasEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigInteger getInicio() {
		return inicio;
	}

	public void setInicio(BigInteger inicio) {
		this.inicio = inicio;
	}

	public BigInteger getFin() {
		return fin;
	}

	public void setFin(BigInteger fin) {
		this.fin = fin;
	}

}