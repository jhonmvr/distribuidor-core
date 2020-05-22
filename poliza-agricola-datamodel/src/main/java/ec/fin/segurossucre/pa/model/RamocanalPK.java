package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RAMOCANAL database table.
 * 
 */
@Embeddable
public class RamocanalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ramoid;

	private String canalid;

	public RamocanalPK() {
	}
	public String getRamoid() {
		return this.ramoid;
	}
	public void setRamoid(String ramoid) {
		this.ramoid = ramoid;
	}
	public String getCanalid() {
		return this.canalid;
	}
	public void setCanalid(String canalid) {
		this.canalid = canalid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RamocanalPK)) {
			return false;
		}
		RamocanalPK castOther = (RamocanalPK)other;
		return 
			this.ramoid.equals(castOther.ramoid)
			&& this.canalid.equals(castOther.canalid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ramoid.hashCode();
		hash = hash * prime + this.canalid.hashCode();
		
		return hash;
	}
}