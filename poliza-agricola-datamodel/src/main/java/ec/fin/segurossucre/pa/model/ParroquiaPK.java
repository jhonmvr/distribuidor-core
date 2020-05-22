package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PARROQUIA database table.
 * 
 */
@Embeddable
public class ParroquiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final Long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String provinciaid;

	@Column(insertable=false, updatable=false)
	private String cantonid;

	private String parroquiaid;

	public ParroquiaPK() {
	}
	public String getProvinciaid() {
		return this.provinciaid;
	}
	public void setProvinciaid(String provinciaid) {
		this.provinciaid = provinciaid;
	}
	public String getCantonid() {
		return this.cantonid;
	}
	public void setCantonid(String cantonid) {
		this.cantonid = cantonid;
	}
	public String getParroquiaid() {
		return this.parroquiaid;
	}
	public void setParroquiaid(String parroquiaid) {
		this.parroquiaid = parroquiaid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParroquiaPK)) {
			return false;
		}
		ParroquiaPK castOther = (ParroquiaPK)other;
		return 
			this.provinciaid.equals(castOther.provinciaid)
			&& this.cantonid.equals(castOther.cantonid)
			&& this.parroquiaid.equals(castOther.parroquiaid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.provinciaid.hashCode();
		hash = hash * prime + this.cantonid.hashCode();
		hash = hash * prime + this.parroquiaid.hashCode();
		
		return hash;
	}
}