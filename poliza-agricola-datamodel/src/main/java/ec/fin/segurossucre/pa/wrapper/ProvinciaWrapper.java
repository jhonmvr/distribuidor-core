package ec.fin.segurossucre.pa.wrapper;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import ec.fin.segurossucre.pa.model.Canton;

public class ProvinciaWrapper  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2603633238095180178L;


	@Id
	private String provinciaid;

	private String provinciaestado;

	private String provinciainfx;

	private String provincianom;

	public ProvinciaWrapper() {
	}

	public String getProvinciaid() {
		return this.provinciaid;
	}

	public void setProvinciaid(String provinciaid) {
		this.provinciaid = provinciaid;
	}

	public String getProvinciaestado() {
		return this.provinciaestado;
	}

	public void setProvinciaestado(String provinciaestado) {
		this.provinciaestado = provinciaestado;
	}

	public String getProvinciainfx() {
		return this.provinciainfx;
	}

	public void setProvinciainfx(String provinciainfx) {
		this.provinciainfx = provinciainfx;
	}

	public String getProvincianom() {
		return this.provincianom;
	}

	public void setProvincianom(String provincianom) {
		this.provincianom = provincianom;
	}


}

