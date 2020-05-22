package ec.com.def.pa.wrapper;

import java.io.Serializable;
import java.util.List;

public class UN01ColeccionResponseWrapper  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 16370353391674179L;
	private List<UN01ResponseWrapper> sdtRespuestaUN01;

	public List<UN01ResponseWrapper> getSdtRespuestaUN01() {
		return sdtRespuestaUN01;
	}

	public void setSdtRespuestaUN01(List<UN01ResponseWrapper> sdtRespuestaUN01) {
		this.sdtRespuestaUN01 = sdtRespuestaUN01;
	}
	
	
	
}
