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

import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.ProcesoEnum;


/**
 * The persistent class for the tb_mi_contrato database table.
 * 
 */
@Entity
@Table(name = "tb_mi_contrato")
//@NamedQuery(name="TbMiContrato.findAll", query="SELECT t FROM TbMiContrato t")
public class TbMiContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_MI_CONTRATO_ID_GENERATOR", sequenceName = "SEQ_CONTRATO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_MI_CONTRATO_ID_GENERATOR")
	private Long id;

	private String codigo;
	
	private String gestion;
	
	@Column(name = "canal_contacto")
	private String canalContacto;

	@Enumerated(EnumType.STRING)
	private EstadoContratoEnum estado;

	@Column(name = "tipo_compra")
	private String tipoCompra;

	@Column(name = "porcentaje_custodia")
	private BigDecimal porcentajeCustodia;

	@Column(name = "porcentaje_gasto_adm")
	private BigDecimal porcentajeGastoAdm;

	@Column(name = "porcentaje_tasacion")
	private BigDecimal porcentajeTasacion;

	@Column(name = "porcentaje_transporte")
	private BigDecimal porcentajeTransporte;

	@Column(name = "valor_contrato")
	private BigDecimal valorContrato;

	@Column(name = "valor_custodia")
	private BigDecimal valorCustodia;

	@Column(name = "valor_gasto_adm")
	private BigDecimal valorGastoAdm;

	@Column(name = "valor_multa")
	private BigDecimal valorMulta;

	@Column(name = "valor_tasacion")
	private BigDecimal valorTasacion;

	@Column(name = "valor_transporte")
	private BigDecimal valorTransporte;

	///@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;
	
	@Column(name = "fecha_cancelacion")
	private Date fechaCancelacion;
	
	@Column(name = "fecha_renovacion")
	private Date fechaRenovacion;
	
	@Column(name = "fecha_perfeccionamiento")
	private Date fechaPerfeccionamiento;

	@Column(name = "proceso")
	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;

	// bi-directional many-to-one association to TbMiAgencia
	@ManyToOne
	@JoinColumn(name = "id_agencia")
	private TbMiAgencia tbMiAgencia;

	@ManyToOne
	@JoinColumn(name = "id_funda")
	private TbMiFunda tbMiFunda;

	// bi-directional many-to-one association to TbMiAgente
	@ManyToOne
	@JoinColumn(name = "id_agente")
	private TbMiAgente tbMiAgente;

	// bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private TbMiCliente tbMiCliente;

	// bi-directional many-to-one association to TbMiJoya
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiJoya> tbMiJoyas;

	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiContratoCalculo> tbMiContratoCalculos;

	// bi-directional many-to-one association to TbMiAbono
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiAbono> tbMiAbonos;

	// bi-directional many-to-one association to TbMiRenovacion
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiRenovacion> tbMiRenovacions;

	// bi-directional many-to-one association to TbMiJoya
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiEgreso> tbMiEgreso;

	// bi-directional many-to-one association to TbMiJoyaSim
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes;

	// bi-directional many-to-one association to TbMiContactabilidad
	@OneToMany(mappedBy = "tbMiContrato")
	private List<TbMiContactabilidad> tbMiContactabilidads;

	@ManyToOne
	@JoinColumn(name = "id_liquidacion")
	private TbMiLiquidacion tbMiLiquidacion;

	@Column(name = " fecha_actualizacion")
	private Date fechaActualizacion;

	@Column(name = " usuario_creacion")
	private String usuarioCreacion;

	@Column(name = " usuario_actualizacion")
	private String usuarioActualizacion;

	// bi-directional many-to-one association to TbMiContrato
	@ManyToOne
	@JoinColumn(name = "id_contrato_anterior")
	private TbMiContrato tbMiContratoAnterior;

	public TbMiContrato() {
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

	public EstadoContratoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoContratoEnum estado) {
		this.estado = estado;
	}

	public BigDecimal getPorcentajeCustodia() {
		return this.porcentajeCustodia;
	}

	public void setPorcentajeCustodia(BigDecimal porcentajeCustodia) {
		this.porcentajeCustodia = porcentajeCustodia;
	}

	public BigDecimal getPorcentajeGastoAdm() {
		return this.porcentajeGastoAdm;
	}

	public void setPorcentajeGastoAdm(BigDecimal porcentajeGastoAdm) {
		this.porcentajeGastoAdm = porcentajeGastoAdm;
	}

	public BigDecimal getPorcentajeTasacion() {
		return this.porcentajeTasacion;
	}

	public void setPorcentajeTasacion(BigDecimal porcentajeTasacion) {
		this.porcentajeTasacion = porcentajeTasacion;
	}

	public BigDecimal getPorcentajeTransporte() {
		return this.porcentajeTransporte;
	}

	public void setPorcentajeTransporte(BigDecimal porcentajeTransporte) {
		this.porcentajeTransporte = porcentajeTransporte;
	}

	public BigDecimal getValorContrato() {
		return this.valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}
	
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Date getFechaRenovacion() {
		return fechaRenovacion;
	}

	public void setFechaRenovacion(Date fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	public Date getFechaPerfeccionamiento() {
		return fechaPerfeccionamiento;
	}

	public void setFechaPerfeccionamiento(Date fechaPerfeccionamiento) {
		this.fechaPerfeccionamiento = fechaPerfeccionamiento;
	}

	public BigDecimal getValorCustodia() {
		return this.valorCustodia;
	}

	public void setValorCustodia(BigDecimal valorCustodia) {
		this.valorCustodia = valorCustodia;
	}

	public BigDecimal getValorGastoAdm() {
		return this.valorGastoAdm;
	}

	public void setValorGastoAdm(BigDecimal valorGastoAdm) {
		this.valorGastoAdm = valorGastoAdm;
	}

	public BigDecimal getValorMulta() {
		return this.valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorTasacion() {
		return this.valorTasacion;
	}

	public void setValorTasacion(BigDecimal valorTasacion) {
		this.valorTasacion = valorTasacion;
	}

	public BigDecimal getValorTransporte() {
		return this.valorTransporte;
	}

	public void setValorTransporte(BigDecimal valorTransporte) {
		this.valorTransporte = valorTransporte;
	}

	public TbMiAgencia getTbMiAgencia() {
		return this.tbMiAgencia;
	}

	public void setTbMiAgencia(TbMiAgencia tbMiAgencia) {
		this.tbMiAgencia = tbMiAgencia;
	}

	public TbMiAgente getTbMiAgente() {
		return this.tbMiAgente;
	}

	public void setTbMiAgente(TbMiAgente tbMiAgente) {
		this.tbMiAgente = tbMiAgente;
	}

	public TbMiCliente getTbMiCliente() {
		return this.tbMiCliente;
	}

	public void setTbMiCliente(TbMiCliente tbMiCliente) {
		this.tbMiCliente = tbMiCliente;
	}

	public List<TbMiJoya> getTbMiJoyas() {
		return this.tbMiJoyas;
	}

	public void setTbMiJoyas(List<TbMiJoya> tbMiJoyas) {
		this.tbMiJoyas = tbMiJoyas;
	}

	public TbMiJoya addTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().add(tbMiJoya);
		tbMiJoya.setTbMiContrato(this);

		return tbMiJoya;
	}

	public TbMiJoya removeTbMiJoya(TbMiJoya tbMiJoya) {
		getTbMiJoyas().remove(tbMiJoya);
		tbMiJoya.setTbMiContrato(null);

		return tbMiJoya;
	}

	public List<TbMiRenovacion> getTbMiRenovacions() {
		return this.tbMiRenovacions;
	}

	public void setTbMiRenovacions(List<TbMiRenovacion> tbMiRenovacions) {
		this.tbMiRenovacions = tbMiRenovacions;
	}

	public TbMiRenovacion addTbMiRenovacion(TbMiRenovacion tbMiRenovacion) {
		getTbMiRenovacions().add(tbMiRenovacion);
		tbMiRenovacion.setTbMiContrato(this);

		return tbMiRenovacion;
	}

	public TbMiRenovacion removeTbMiRenovacion(TbMiRenovacion tbMiRenovacion) {
		getTbMiRenovacions().remove(tbMiRenovacion);
		tbMiRenovacion.setTbMiContrato(null);

		return tbMiRenovacion;
	}

	public TbMiLiquidacion getTbMiLiquidacion() {
		return tbMiLiquidacion;
	}

	public void setTbMiLiquidacion(TbMiLiquidacion tbMiLiquidacion) {
		this.tbMiLiquidacion = tbMiLiquidacion;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public TbMiFunda getTbMiFunda() {
		return tbMiFunda;
	}

	public void setTbMiFunda(TbMiFunda tbMiFunda) {
		this.tbMiFunda = tbMiFunda;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

	public List<TbMiAbono> getTbMiAbonos() {
		return tbMiAbonos;
	}

	public void setTbMiAbonos(List<TbMiAbono> tbMiAbonos) {
		this.tbMiAbonos = tbMiAbonos;
	}

	public List<TbMiEgreso> getTbMiEgreso() {
		return tbMiEgreso;
	}

	public void setTbMiEgreso(List<TbMiEgreso> tbMiEgreso) {
		this.tbMiEgreso = tbMiEgreso;
	}

	public List<TbMiDocumentoHabilitante> getTbMiDocumentoHabilitantes() {
		return tbMiDocumentoHabilitantes;
	}

	public void setTbMiDocumentoHabilitantes(List<TbMiDocumentoHabilitante> tbMiDocumentoHabilitantes) {
		this.tbMiDocumentoHabilitantes = tbMiDocumentoHabilitantes;
	}

	public List<TbMiContratoCalculo> getTbMiContratoCalculos() {
		return tbMiContratoCalculos;
	}

	public void setTbMiContratoCalculos(List<TbMiContratoCalculo> tbMiContratoCalculos) {
		this.tbMiContratoCalculos = tbMiContratoCalculos;
	}

	public List<TbMiContactabilidad> getTbMiContactabilidads() {
		return tbMiContactabilidads;
	}

	public void setTbMiContactabilidads(List<TbMiContactabilidad> tbMiContactabilidads) {
		this.tbMiContactabilidads = tbMiContactabilidads;
	}

	public TbMiContrato getTbMiContratoAnterior() {
		return tbMiContratoAnterior;
	}

	public void setTbMiContratoAnterior(TbMiContrato tbMiContratoAnterior) {
		this.tbMiContratoAnterior = tbMiContratoAnterior;
	}

	public String getGestion() {
		return gestion;
	}

	public void setGestion(String gestion) {
		this.gestion = gestion;
	}

	public String getCanalContacto() {
		return canalContacto;
	}

	public void setCanalContacto(String canalContacto) {
		this.canalContacto = canalContacto;
	}

}