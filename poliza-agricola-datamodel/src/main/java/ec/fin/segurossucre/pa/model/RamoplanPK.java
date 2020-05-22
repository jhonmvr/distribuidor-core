package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RAMOPLAN database table.
 * 
 */
@Embeddable
public class RamoplanPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ramoid;

	private String ramoplanid;

	public RamoplanPK() {
	}
	public String getRamoid() {
		return this.ramoid;
	}
	public void setRamoid(String ramoid) {
		this.ramoid = ramoid;
	}
	public String getRamoplanid() {
		return this.ramoplanid;
	}
	public void setRamoplanid(String ramoplanid) {
		this.ramoplanid = ramoplanid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RamoplanPK)) {
			return false;
		}
		RamoplanPK castOther = (RamoplanPK)other;
		return 
			this.ramoid.equals(castOther.ramoid)
			&& this.ramoplanid.equals(castOther.ramoplanid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ramoid.hashCode();
		hash = hash * prime + this.ramoplanid.hashCode();
		
		return hash;
	}
}