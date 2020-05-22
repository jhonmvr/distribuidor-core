package ec.com.def.pa.wrapper;

import java.io.Serializable;

public class MessageWrapper implements Serializable {
	
	private String fechaDesde;
	private String fechaHasta;
	private String estadoSiniestro;
	private String sessionIdSender;
	private String sessionIdReceiver;
	private Long counter;
	private Boolean addCount;
	private String hash;
	private String lastId;
	private Boolean finished;
	
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getEstadoSiniestro() {
		return estadoSiniestro;
	}
	public void setEstadoSiniestro(String estadoSiniestro) {
		this.estadoSiniestro = estadoSiniestro;
	}
	public String getSessionIdSender() {
		return sessionIdSender;
	}
	public void setSessionIdSender(String sessionIdSender) {
		this.sessionIdSender = sessionIdSender;
	}
	public String getSessionIdReceiver() {
		return sessionIdReceiver;
	}
	public void setSessionIdReceiver(String sessionIdReceiver) {
		this.sessionIdReceiver = sessionIdReceiver;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getLastId() {
		return lastId;
	}
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public Boolean getAddCount() {
		return addCount;
	}
	public void setAddCount(Boolean addCount) {
		this.addCount = addCount;
	}

	
	
}
