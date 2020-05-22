package com.relative.midas.model;

import java.io.Serializable;
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
import com.relative.midas.enums.TipoDocumentoEnum;
import com.relative.midas.enums.TipoPlantillaEnum;


/**
 * The persistent class for the tb_mi_cotizacion database table.
 * 
 */
@Entity
@Table(name="tb_mi_tipo_documento")
//@NamedQuery(name="TbMiCotizacion.findAll", query="SELECT t FROM TbMiCotizacion t")
public class TbMiTipoDocumento implements Serializable {

	private static final Long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="TB_MI_DOCUMENTO_ID_GENERATOR", sequenceName="SEQ_DOCUMENTO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_DOCUMENTO_ID_GENERATOR")
	private Long id;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private EstadoMidasEnum estado;
    private String plantilla;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="tipo_documento")
	@Enumerated(EnumType.STRING)
	private TipoDocumentoEnum tipoDocumento;
	
	@Column(name="tipo_plantilla")
	@Enumerated(EnumType.STRING)
	private TipoPlantillaEnum tipoPlantilla;
	
	@Column(name="plantilla_uno")
	private String plantillaUno;
	
	@Column(name="plantilla_dos")
	private String plantillaDos;
	
	@Column(name="plantilla_tres")
	private String plantillaTres;
	
	
	//bi-directional many-to-one association to TbMiJoyaSim
	@OneToMany(mappedBy="tbMiTipoDocumento")
	private List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes;
	
	
	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoMidasEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMidasEnum estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<TbMiDocumentoHabilitante> getTbMiDocumentoHabilitantes() {
		return tbMiDocumentoHabilitantes;
	}

	public void setTbMiDocumentoHabilitantes(List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes) {
		this.tbMiDocumentoHabilitantes = tbMiDocumentoHabilitantes;
	}

	public TipoPlantillaEnum getTipoPlantilla() {
		return tipoPlantilla;
	}

	public void setTipoPlantilla(TipoPlantillaEnum tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}

	public String getPlantillaUno() {
		return plantillaUno;
	}

	public void setPlantillaUno(String plantillaUno) {
		this.plantillaUno = plantillaUno;
	}

	public String getPlantillaDos() {
		return plantillaDos;
	}

	public void setPlantillaDos(String plantillaDos) {
		this.plantillaDos = plantillaDos;
	}

	public String getPlantillaTres() {
		return plantillaTres;
	}

	public void setPlantillaTres(String plantillaTres) {
		this.plantillaTres = plantillaTres;
	}

	
	
}