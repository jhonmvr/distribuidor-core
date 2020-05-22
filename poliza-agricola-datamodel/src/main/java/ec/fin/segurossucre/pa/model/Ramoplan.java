package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RAMOPLAN database table.
 * 
 */
@Entity
//@NamedQuery(name="Ramoplan.findAll", query="SELECT r FROM Ramoplan r")
public class Ramoplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RamoplanPK id;

	private String codigobnf;

	private BigDecimal ramoplandias;

	private BigDecimal ramoplandias2;

	private String ramoplannombre;

	//bi-directional many-to-one association to TbPaCaracteristicaCultivo
	@OneToMany(mappedBy="ramoplan")
	private List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	@JoinColumn(name="RAMOID")
	private Ramo ramo;

	public Ramoplan() {
	}

	public RamoplanPK getId() {
		return this.id;
	}

	public void setId(RamoplanPK id) {
		this.id = id;
	}

	public String getCodigobnf() {
		return this.codigobnf;
	}

	public void setCodigobnf(String codigobnf) {
		this.codigobnf = codigobnf;
	}

	public BigDecimal getRamoplandias() {
		return this.ramoplandias;
	}

	public void setRamoplandias(BigDecimal ramoplandias) {
		this.ramoplandias = ramoplandias;
	}

	public BigDecimal getRamoplandias2() {
		return this.ramoplandias2;
	}

	public void setRamoplandias2(BigDecimal ramoplandias2) {
		this.ramoplandias2 = ramoplandias2;
	}

	public String getRamoplannombre() {
		return this.ramoplannombre;
	}

	public void setRamoplannombre(String ramoplannombre) {
		this.ramoplannombre = ramoplannombre;
	}

	public List<TbPaCaracteristicaCultivo> getTbPaCaracteristicaCultivos() {
		return this.tbPaCaracteristicaCultivos;
	}

	public void setTbPaCaracteristicaCultivos(List<TbPaCaracteristicaCultivo> tbPaCaracteristicaCultivos) {
		this.tbPaCaracteristicaCultivos = tbPaCaracteristicaCultivos;
	}

	public TbPaCaracteristicaCultivo addTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().add(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setRamoplan(this);

		return tbPaCaracteristicaCultivo;
	}

	public TbPaCaracteristicaCultivo removeTbPaCaracteristicaCultivo(TbPaCaracteristicaCultivo tbPaCaracteristicaCultivo) {
		getTbPaCaracteristicaCultivos().remove(tbPaCaracteristicaCultivo);
		tbPaCaracteristicaCultivo.setRamoplan(null);

		return tbPaCaracteristicaCultivo;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}