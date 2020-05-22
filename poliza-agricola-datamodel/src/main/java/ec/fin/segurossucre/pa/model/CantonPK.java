package ec.com.def.pa.model;


import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CANTON database table.
 * 
 */
@Embeddable
public class CantonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final Long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String provinciaid;

	private String cantonid;

	public CantonPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CantonPK)) {
			return false;
		}
		CantonPK castOther = (CantonPK)other;
		return 
			this.provinciaid.equals(castOther.provinciaid)
			&& this.cantonid.equals(castOther.cantonid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.provinciaid.hashCode();
		hash = hash * prime + this.cantonid.hashCode();
		
		return hash;
	}
}