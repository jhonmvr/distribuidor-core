package com.relative.midas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.midas.enums.EstadoMidasEnum;

/**
 * The persistent class for the tb_mi_joya_sim database table.
 * 
 */
@Entity
@Table(name="tb_mi_documento_habilitante")
public class TbMiDocumentoHabilitante implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -37296922946570825L;


	@Id
	@SequenceGenerator(name="TB_MI_JOYA_SIM_ID_GENERATOR", sequenceName="SEQ_JOYA_SIM",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_JOYA_SIM_ID_GENERATOR")
	private Long id;


	@Column(name="nombre_archivo")
	private String nombreArchivo;

	@Lob
	private byte[] archivo;

	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;


	//bi-directional many-to-one association to TbMiCotizacion
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private TbMiContrato tbMiContrato;
	
	@ManyToOne
	@JoinColumn(name="id_joya")
	private TbMiJoya tbMiJoya;
	
	@ManyToOne
	@JoinColumn(name="id_abono")
	private TbMiAbono tbMiAbono;
	
	@ManyToOne
	@JoinColumn(name="id_venta_lote")
	private TbMiVentaLote tbMiVentaLote;
	
	@ManyToOne
	@JoinColumn(name="id_corte_caja")
	private TbMiCorteCaja tbMiCorteCaja;
	
	
	//bi-directional many-to-one association to TbMiCotizacion
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private TbMiTipoDocumento tbMiTipoDocumento;
	

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	
	public TbMiDocumentoHabilitante() {
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreArchivo() {
		return nombreArchivo;
	}



	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}







	public EstadoMidasEnum getEstado() {
		return estado;
	}



	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}



	public TbMiContrato getTbMiContrato() {
		return tbMiContrato;
	}



	public void setTbMiContrato(TbMiContrato tbMiContrato) {
		this.tbMiContrato = tbMiContrato;
	}



	public TbMiTipoDocumento getTbMiTipoDocumento() {
		return tbMiTipoDocumento;
	}



	public void setTbMiTipoDocumento(TbMiTipoDocumento tbMiTipoDocumento) {
		this.tbMiTipoDocumento = tbMiTipoDocumento;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}



	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public TbMiJoya getTbMiJoya() {
		return tbMiJoya;
	}

	public void setTbMiJoya(TbMiJoya tbMiJoya) {
		this.tbMiJoya = tbMiJoya;
	}

	public TbMiAbono getTbMiAbono() {
		return tbMiAbono;
	}

	public void setTbMiAbono(TbMiAbono tbMiAbono) {
		this.tbMiAbono = tbMiAbono;
	}

	public TbMiVentaLote getTbMiVentaLote() {
		return tbMiVentaLote;
	}

	public void setTbMiVentaLote(TbMiVentaLote tbMiVentaLote) {
		this.tbMiVentaLote = tbMiVentaLote;
	}


	public TbMiCorteCaja getTbMiCorteCaja() {
		return tbMiCorteCaja;
	}

	public void setTbMiCorteCaja(TbMiCorteCaja tbMiCorteCaja) {
		this.tbMiCorteCaja = tbMiCorteCaja;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	

}
