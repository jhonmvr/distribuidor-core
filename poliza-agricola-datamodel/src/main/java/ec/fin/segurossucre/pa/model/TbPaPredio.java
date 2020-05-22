package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_PA_PREDIO database table.
 * 
 */
@Entity
@Table(name="TB_PA_PREDIO")
//@NamedQuery(name="TbPaPredio.findAll", query="SELECT t FROM TbPaPredio t")
public class TbPaPredio implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PA_PREDIO_ID_GENERATOR", sequenceName="SEQ_PREDIO_POLIZA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_PREDIO_ID_GENERATOR")
	private Long id;

	private BigDecimal altitud;

	private String condicion;

	@Column(name="COORDENADA_X")
	private BigDecimal coordenadaX;

	@Column(name="COORDENADA_Y")
	private BigDecimal coordenadaY;

	
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	private String recinto;

	private String referencia;

	//bi-directional many-to-one association to Condicionpredio
	@ManyToOne
	@JoinColumn(name="ID_CONDICION_PREDIO")
	private Condicionpredio condicionpredio;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CANTON", referencedColumnName="CANTONID"),
		@JoinColumn(name="ID_PARROQUIA", referencedColumnName="PARROQUIAID"),
		@JoinColumn(name="ID_PROVINCIA", referencedColumnName="PROVINCIAID")
		})
	private Parroquia parroquia;

	public TbPaPredio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAltitud() {
		return this.altitud;
	}

	public void setAltitud(BigDecimal altitud) {
		this.altitud = altitud;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public BigDecimal getCoordenadaX() {
		return this.coordenadaX;
	}

	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return this.coordenadaY;
	}

	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
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

	public String getRecinto() {
		return this.recinto;
	}

	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Condicionpredio getCondicionpredio() {
		return this.condicionpredio;
	}

	public void setCondicionpredio(Condicionpredio condicionpredio) {
		this.condicionpredio = condicionpredio;
	}

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

}