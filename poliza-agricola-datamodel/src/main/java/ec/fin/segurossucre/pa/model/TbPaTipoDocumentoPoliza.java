package ec.com.def.pa.model;
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

import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;

/**
 * The persistent class for the tb_mi_cotizacion database table.
 * 
 */
@Entity
@Table(name="TB_PA_TIPO_DOC_POLIZA")
public class TbPaTipoDocumentoPoliza implements Serializable {

	private static final Long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="TB_PA_TIPO_DOC_POLIZA_ID_GENERATOR", sequenceName="SEQ_TIPO_DOCUMENTO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_TIPO_DOC_POLIZA_ID_GENERATOR")
	private Long id;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;
    private String plantilla;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="tipo_documento")
	
	private String tipoDocumento;
	
	@Column(name="tipo_plantilla")
	
	private String tipoPlantilla;
	
	@Column(name="plantilla_uno")
	private String plantillaUno;
	
	@Column(name="plantilla_dos")
	private String plantillaDos;
	
	@Column(name="plantilla_tres")
	private String plantillaTres;
	
	
	//bi-directional many-to-one association to TbMiJoyaSim
	@OneToMany(mappedBy="tbPaTipoDocumentoPoliza")
	private List<TbPaDocumentoPoliza> tbPaDocumentoPoliza;
	
	
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

	

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	
	public EstadoSiniestroAgricolaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}

	public List<TbPaDocumentoPoliza> getTbPaDocumentoPoliza() {
		return tbPaDocumentoPoliza;
	}

	public void setTbPaDocumentoPoliza(List<TbPaDocumentoPoliza> tbPaDocumentoPoliza) {
		this.tbPaDocumentoPoliza = tbPaDocumentoPoliza;
	}

	public String getTipoPlantilla() {
		return tipoPlantilla;
	}

	public void setTipoPlantilla(String tipoPlantilla) {
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