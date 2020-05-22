package ec.com.def.pa.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UN01ColeccionWrapper  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8970279992849083239L;
	
	private List<UN01Wrapper> UN01Coleccion;

	public List<UN01Wrapper> getUN01Coleccion() {
		return UN01Coleccion;
	}

	public void setUN01Coleccion(List<UN01Wrapper> uN01Coleccion) {
		UN01Coleccion = uN01Coleccion;
	}
	
}
