package ec.com.def.pa.model;
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

import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;


/**
 * The persistent class for the tb_mi_joya_sim database table.
 * 
 */
@Entity
@Table(name="TB_PA_DOC_SOLICITUD_POLIZA")
public class TbPaDocumentoPoliza implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -37296922946570825L;

	@Id
	@SequenceGenerator(name="TB_PA_DOC_SOLICITUD_POLIZA_ID_GENERATOR", sequenceName="SEQ_DOC_POLIZA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_DOC_SOLICITUD_POLIZA_ID_GENERATOR")
	private Long id;

	@Column(name="nombre_archivo")
	private String nombreArchivo;

	@Lob
	private byte[] archivo;

	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;

	
	@ManyToOne
	@JoinColumn(name="ID_SOLICITUD_POLIZA")
	private TbPaSolicitudPoliza tbPaSolicitudPoliza;
	
	
	//bi-directional many-to-one association to TbMiCotizacion
	@ManyToOne
	@JoinColumn(name="ID_TIPO_DOCUMENTO")
	private TbPaTipoDocumentoPoliza tbPaTipoDocumentoPoliza;
	

	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	
	public TbPaDocumentoPoliza() {
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	public EstadoSiniestroAgricolaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}

	public TbPaSolicitudPoliza getTbPaSolicitudPoliza() {
		return tbPaSolicitudPoliza;
	}

	public void setTbPaSolicitudPoliza(TbPaSolicitudPoliza tbPaSolicitudPoliza) {
		this.tbPaSolicitudPoliza = tbPaSolicitudPoliza;
	}

	public TbPaTipoDocumentoPoliza getTbPaTipoDocumentoPoliza() {
		return tbPaTipoDocumentoPoliza;
	}

	public void setTbPaTipoDocumentoPoliza(TbPaTipoDocumentoPoliza tbPaTipoDocumentoPoliza) {
		this.tbPaTipoDocumentoPoliza = tbPaTipoDocumentoPoliza;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}



	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
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


	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	

}
