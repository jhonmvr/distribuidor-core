package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_PA_CANAL_SECUENCIA database table.
 * 
 */
@Entity
@Table(name="TB_PA_CANAL_SECUENCIA")
public class TbPaCanalSecuencia implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PA_CANAL_SECUENCIA_ID_GENERATOR", sequenceName="SEQ_CANAL_SECUENCIA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_CANAL_SECUENCIA_ID_GENERATOR")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="SEQ_NUMERO_TRAMITE")
	private String seqNumeroTramite;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to Ramocanal
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CANAL", referencedColumnName="CANALID"),
		@JoinColumn(name="ID_RAMO", referencedColumnName="RAMOID")
		})
	private Ramocanal ramocanal;

	public TbPaCanalSecuencia() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSeqNumeroTramite() {
		return this.seqNumeroTramite;
	}

	public void setSeqNumeroTramite(String seqNumeroTramite) {
		this.seqNumeroTramite = seqNumeroTramite;
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

	public Ramocanal getRamocanal() {
		return this.ramocanal;
	}

	public void setRamocanal(Ramocanal ramocanal) {
		this.ramocanal = ramocanal;
	}

}