package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CONDICIONPREDIO database table.
 * 
 */
@Entity
@NamedQuery(name="Condicionpredio.findAll", query="SELECT c FROM Condicionpredio c")
public class Condicionpredio implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="CONDICIONPREDIO_CONDICIONPREDIOID_GENERATOR", sequenceName="SEQ_CONDICIONPREDIO")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONDICIONPREDIO_CONDICIONPREDIOID_GENERATOR")
	private Long condicionpredioid;

	private String condicionprediocod;

	@Temporal(TemporalType.DATE)
	private Date condicionprediodeltim;

	private String condicionprediodelusr;

	private String condicionprediodes;

	@Temporal(TemporalType.DATE)
	private Date condicionpredioinstim;

	private String condicionpredioinsusr;

	private String condicionprediosts;

	@Temporal(TemporalType.DATE)
	private Date condicionpredioupdtim;

	private String condicionpredioupdusr;

	//bi-directional many-to-one association to TbPaPredio
	@OneToMany(mappedBy="condicionpredio")
	private List<TbPaPredio> tbPaPredios;

	public Condicionpredio() {
	}

	public Long getCondicionpredioid() {
		return this.condicionpredioid;
	}

	public void setCondicionpredioid(Long condicionpredioid) {
		this.condicionpredioid = condicionpredioid;
	}

	public String getCondicionprediocod() {
		return this.condicionprediocod;
	}

	public void setCondicionprediocod(String condicionprediocod) {
		this.condicionprediocod = condicionprediocod;
	}

	public Date getCondicionprediodeltim() {
		return this.condicionprediodeltim;
	}

	public void setCondicionprediodeltim(Date condicionprediodeltim) {
		this.condicionprediodeltim = condicionprediodeltim;
	}

	public String getCondicionprediodelusr() {
		return this.condicionprediodelusr;
	}

	public void setCondicionprediodelusr(String condicionprediodelusr) {
		this.condicionprediodelusr = condicionprediodelusr;
	}

	public String getCondicionprediodes() {
		return this.condicionprediodes;
	}

	public void setCondicionprediodes(String condicionprediodes) {
		this.condicionprediodes = condicionprediodes;
	}

	public Date getCondicionpredioinstim() {
		return this.condicionpredioinstim;
	}

	public void setCondicionpredioinstim(Date condicionpredioinstim) {
		this.condicionpredioinstim = condicionpredioinstim;
	}

	public String getCondicionpredioinsusr() {
		return this.condicionpredioinsusr;
	}

	public void setCondicionpredioinsusr(String condicionpredioinsusr) {
		this.condicionpredioinsusr = condicionpredioinsusr;
	}

	public String getCondicionprediosts() {
		return this.condicionprediosts;
	}

	public void setCondicionprediosts(String condicionprediosts) {
		this.condicionprediosts = condicionprediosts;
	}

	public Date getCondicionpredioupdtim() {
		return this.condicionpredioupdtim;
	}

	public void setCondicionpredioupdtim(Date condicionpredioupdtim) {
		this.condicionpredioupdtim = condicionpredioupdtim;
	}

	public String getCondicionpredioupdusr() {
		return this.condicionpredioupdusr;
	}

	public void setCondicionpredioupdusr(String condicionpredioupdusr) {
		this.condicionpredioupdusr = condicionpredioupdusr;
	}

	public List<TbPaPredio> getTbPaPredios() {
		return this.tbPaPredios;
	}

	public void setTbPaPredios(List<TbPaPredio> tbPaPredios) {
		this.tbPaPredios = tbPaPredios;
	}

	public TbPaPredio addTbPaPredio(TbPaPredio tbPaPredio) {
		getTbPaPredios().add(tbPaPredio);
		tbPaPredio.setCondicionpredio(this);

		return tbPaPredio;
	}

	public TbPaPredio removeTbPaPredio(TbPaPredio tbPaPredio) {
		getTbPaPredios().remove(tbPaPredio);
		tbPaPredio.setCondicionpredio(null);

		return tbPaPredio;
	}

}