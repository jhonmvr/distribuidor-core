package ec.com.def.pa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The persistent class for the CANTON database table.
 * 
 */
@Entity
//@NamedQuery(name="Canton.findAll", query="SELECT c FROM Canton c")
public class Canton implements Serializable {
	private static final Long serialVersionUID = 1L;

	@EmbeddedId
	private CantonPK id;

	private String cantonestado;

	private String cantoninfx;

	private String cantonnom;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="PROVINCIAID")
	private Provincia provincia;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="canton")
	private List<Parroquia> parroquias;
	
	

	public Canton() {
	}

	public CantonPK getId() {
		return this.id;
	}

	public void setId(CantonPK id) {
		this.id = id;
	}

	public String getCantonestado() {
		return this.cantonestado;
	}

	public void setCantonestado(String cantonestado) {
		this.cantonestado = cantonestado;
	}

	public String getCantoninfx() {
		return this.cantoninfx;
	}

	public void setCantoninfx(String cantoninfx) {
		this.cantoninfx = cantoninfx;
	}

	public String getCantonnom() {
		return this.cantonnom;
	}

	public void setCantonnom(String cantonnom) {
		this.cantonnom = cantonnom;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Parroquia> getParroquias() {
		return this.parroquias;
	}
	
	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
	


	public Parroquia addParroquia(Parroquia parroquia) {
		getParroquias().add(parroquia);
		parroquia.setCanton(this);

		return parroquia;
	}

	public Parroquia removeParroquia(Parroquia parroquia) {
		getParroquias().remove(parroquia);
		parroquia.setCanton(null);

		return parroquia;
	}
}