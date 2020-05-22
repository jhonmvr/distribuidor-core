package ec.com.def.pa.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;


public class Chart implements Serializable{
	
	/**
	 * 
	 */
	private static final Long serialVersionUID = 3284597008038473226L;
	private String label;
	private BigDecimal value;
	private Long counter;
	private EstadoSiniestroAgricolaEnum status;
	
	private List<String> etiquetas;
	private List<String> valores;
	private List<String> contadores;
	
	
	
	public Chart(String label, BigDecimal value) {
		super();
		this.label = label;
		this.value = value;
	}
	
	
	
	public Chart(String label, Long counter) {
		super();
		this.label = label;
		this.counter = counter;
	}



	public Chart(String label, BigDecimal value, Long counter) {
		super();
		this.label = label;
		this.value = value;
		this.counter = counter;
	}



	public Chart(EstadoSiniestroAgricolaEnum status,Long counter) {
		super();
		this.counter = counter;
		this.status = status;
	}



	public Chart(EstadoSiniestroAgricolaEnum status,BigDecimal value) {
		super();
		this.value = value;
		this.status = status;
	}


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public List<String> getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
	public List<String> getValores() {
		return valores;
	}
	public void setValores(List<String> valores) {
		this.valores = valores;
	}
	public List<String> getContadores() {
		return contadores;
	}
	public void setContadores(List<String> contadores) {
		this.contadores = contadores;
	}



	public EstadoSiniestroAgricolaEnum getStatus() {
		return status;
	}



	public void setStatus(EstadoSiniestroAgricolaEnum status) {
		this.status = status;
	}



	

}
