package ec.com.def.pa.wrapper;

import java.math.BigDecimal;
import java.util.Date;

import ec.com.def.core.exception.SegSucreException;
import ec.com.def.core.util.main.BaseWrapper;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Apol;
import ec.com.def.pa.model.Un01;


public class ApolWrapper extends BaseWrapper<Apol> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String provincia;
	private String canton;
	private String parroquia;
	private String recinto;
	private String referencia;
	private String ramoPlanId;
	private String ramoCanalId;
	private BigDecimal hectarreasAseguradas;
	private Date fechaPolizaInicio;
	private Date fechaPolizaExpiracion;
	
	public ApolWrapper(Apol apol , Un01 un01)  {
		this.setEntidad(apol);
		if(apol!=null) {
			provincia=apol.getAwcliprv();
			canton = apol.getAwclican();
			fechaPolizaInicio = apol.getAwpzgfin();
			fechaPolizaExpiracion= apol.getAwpzgfex();
		}
		if(un01 != null) {
			parroquia=un01.getUn01Parroquia();
			hectarreasAseguradas=un01.getUn01Superficieasegurar();
			
		}
	}
	
	public ApolWrapper(Apol apol )  {
		this.setEntidad(apol);
		if(apol!=null) {
			provincia=apol.getAwcliprv();
			canton = apol.getAwclican();
			fechaPolizaInicio = apol.getAwpzgfin();
			fechaPolizaExpiracion= apol.getAwpzgfex();
			parroquia=apol.getAwparroquia();
			hectarreasAseguradas=apol.getAwhectareas();
			
		}
	}
	
	public void setData() throws SegSucreException {
		if(!this.getEntidad().getAwclilugnac().equals("")) {
			
				String[] parts = this.getEntidad().getAwclilugnac().split("/");
				if(parts.length>2) {
					this.setProvincia(parts[0]);
					this.setCanton(parts[1]);
					this.setParroquia(parts[2]);
				}
				
		}else {
			throw new SegSucreException(Constantes.ERROR_CODE_READ, "Awclilugnac no encontrada " );
		}
		
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public String getParroquia() {
		return parroquia;
	}
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getRamoPlanId() {
		return ramoPlanId;
	}

	public void setRamoPlanId(String ramoPlanId) {
		this.ramoPlanId = ramoPlanId;
	}

	public String getRamoCanalId() {
		return ramoCanalId;
	}

	public void setRamoCanalId(String ramoCanalId) {
		this.ramoCanalId = ramoCanalId;
	}

	public BigDecimal getHectarreasAseguradas() {
		return hectarreasAseguradas;
	}

	public void setHectarreasAseguradas(BigDecimal hectarreasAseguradas) {
		this.hectarreasAseguradas = hectarreasAseguradas;
	}

	public Date getFechaPolizaInicio() {
		return fechaPolizaInicio;
	}

	public void setFechaPolizaInicio(Date fechaPolizaInicio) {
		this.fechaPolizaInicio = fechaPolizaInicio;
	}

	public Date getFechaPolizaExpiracion() {
		return fechaPolizaExpiracion;
	}

	public void setFechaPolizaExpiracion(Date fechaPolizaExpiracion) {
		this.fechaPolizaExpiracion = fechaPolizaExpiracion;
	}
	
	
	

}
