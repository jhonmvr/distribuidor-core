package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the GENERO database table.
 * 
 */
@Entity
//@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="GENERO_GENEROID_GENERATOR", sequenceName="SEQ_GENERO")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENERO_GENEROID_GENERATOR")
	private Long generoid;

	private String generocod;

	@Temporal(TemporalType.DATE)
	private Date generodeltim;

	private String generodelusr;

	private String generodes;

	@Temporal(TemporalType.DATE)
	private Date generoinstim;

	private String generoinsusr;

	private String generosts;

	@Temporal(TemporalType.DATE)
	private Date generoupdtim;

	private String generoupdusr;

	public Genero() {
	}

	public Long getGeneroid() {
		return this.generoid;
	}

	public void setGeneroid(Long generoid) {
		this.generoid = generoid;
	}

	public String getGenerocod() {
		return this.generocod;
	}

	public void setGenerocod(String generocod) {
		this.generocod = generocod;
	}

	public Date getGenerodeltim() {
		return this.generodeltim;
	}

	public void setGenerodeltim(Date generodeltim) {
		this.generodeltim = generodeltim;
	}

	public String getGenerodelusr() {
		return this.generodelusr;
	}

	public void setGenerodelusr(String generodelusr) {
		this.generodelusr = generodelusr;
	}

	public String getGenerodes() {
		return this.generodes;
	}

	public void setGenerodes(String generodes) {
		this.generodes = generodes;
	}

	public Date getGeneroinstim() {
		return this.generoinstim;
	}

	public void setGeneroinstim(Date generoinstim) {
		this.generoinstim = generoinstim;
	}

	public String getGeneroinsusr() {
		return this.generoinsusr;
	}

	public void setGeneroinsusr(String generoinsusr) {
		this.generoinsusr = generoinsusr;
	}

	public String getGenerosts() {
		return this.generosts;
	}

	public void setGenerosts(String generosts) {
		this.generosts = generosts;
	}

	public Date getGeneroupdtim() {
		return this.generoupdtim;
	}

	public void setGeneroupdtim(Date generoupdtim) {
		this.generoupdtim = generoupdtim;
	}

	public String getGeneroupdusr() {
		return this.generoupdusr;
	}

	public void setGeneroupdusr(String generoupdusr) {
		this.generoupdusr = generoupdusr;
	}

}