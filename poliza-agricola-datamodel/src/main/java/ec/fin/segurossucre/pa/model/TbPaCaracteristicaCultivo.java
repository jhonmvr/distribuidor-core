package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_PA_CARACTERISTICA_CULTIVO database table.
 * 
 */
@Entity
@Table(name="TB_PA_CARACTERISTICA_CULTIVO")
//@NamedQuery(name="TbPaCaracteristicaCultivo.findAll", query="SELECT t FROM TbPaCaracteristicaCultivo t")
public class TbPaCaracteristicaCultivo implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PA_CARACTERISTICA_CULTIVO_ID_GENERATOR", sequenceName="SEQ_CARACTERISTICA_CULTIVO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PA_CARACTERISTICA_CULTIVO_ID_GENERATOR")
	private Long id;

	@Column(name="ASISTENCIA_TECNICA")
	private String asistenciaTecnica;

	@Column(name="COSTO_HECTAREA")
	private BigDecimal costoHectarea;

	
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	
	@Column(name="FECHA_SIEMBRA")
	private Date fechaSiembra;

	@Column(name="MONTO_ASEGURADO")
	private BigDecimal montoAsegurado;

	private String riego;

	@Column(name="SUPERFICIE_ASEGURADA")
	private BigDecimal superficieAsegurada;

	private String variedad;

	//bi-directional many-to-one association to Ramoplan
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_CULTIVO", referencedColumnName="RAMOPLANID"),
		@JoinColumn(name="ID_RAMO", referencedColumnName="RAMOID")
		})
	private Ramoplan ramoplan;

	//bi-directional many-to-one association to Riego
	@ManyToOne
	@JoinColumn(name="TIPO_RIEGO")
	private Riego riegoBean;

	//bi-directional many-to-one association to Tiposemilla
	@ManyToOne
	@JoinColumn(name="TIPO_SEMILLA")
	private Tiposemilla tiposemilla;

	public TbPaCaracteristicaCultivo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsistenciaTecnica() {
		return this.asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}

	public BigDecimal getCostoHectarea() {
		return this.costoHectarea;
	}

	public void setCostoHectarea(BigDecimal costoHectarea) {
		this.costoHectarea = costoHectarea;
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

	public Date getFechaSiembra() {
		return this.fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	public BigDecimal getMontoAsegurado() {
		return this.montoAsegurado;
	}

	public void setMontoAsegurado(BigDecimal montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}

	public String getRiego() {
		return this.riego;
	}

	public void setRiego(String riego) {
		this.riego = riego;
	}

	public BigDecimal getSuperficieAsegurada() {
		return this.superficieAsegurada;
	}

	public void setSuperficieAsegurada(BigDecimal superficieAsegurada) {
		this.superficieAsegurada = superficieAsegurada;
	}

	public String getVariedad() {
		return this.variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public Ramoplan getRamoplan() {
		return this.ramoplan;
	}

	public void setRamoplan(Ramoplan ramoplan) {
		this.ramoplan = ramoplan;
	}

	public Riego getRiegoBean() {
		return this.riegoBean;
	}

	public void setRiegoBean(Riego riegoBean) {
		this.riegoBean = riegoBean;
	}

	public Tiposemilla getTiposemilla() {
		return this.tiposemilla;
	}

	public void setTiposemilla(Tiposemilla tiposemilla) {
		this.tiposemilla = tiposemilla;
	}

}