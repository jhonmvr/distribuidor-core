package ec.fin.segurossucre.pa.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UN01ResponseWrapper  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8970279992849083239L;

	private String Hubo_Errores;
	private String Observacion;
	private String UN01_CodigoTramite;
	private String UN01_Id;
	
	public String getHubo_Errores() {
		return Hubo_Errores;
	}
	public void setHubo_Errores(String hubo_Errores) {
		Hubo_Errores = hubo_Errores;
	}
	public String getObservacion() {
		return Observacion;
	}
	public void setObservacion(String observacion) {
		Observacion = observacion;
	}
	public String getUN01_CodigoTramite() {
		return UN01_CodigoTramite;
	}
	public void setUN01_CodigoTramite(String uN01_CodigoTramite) {
		UN01_CodigoTramite = uN01_CodigoTramite;
	}
	public String getUN01_Id() {
		return UN01_Id;
	}
	public void setUN01_Id(String uN01_Id) {
		UN01_Id = uN01_Id;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("UN01ColeccionResponseWrapper: \n");
		sb.append( "Hubo_Errores:" ).append(Hubo_Errores).append("\n").
		append( "Observacion:" ).append(Observacion).append("\n").
		append( "UN01_CodigoTramite:" ).append(UN01_CodigoTramite).append("\n").
		append( "UN01_Id:" ).append(UN01_Id).append("\n");
		return sb.toString();
	}
	
	
}
