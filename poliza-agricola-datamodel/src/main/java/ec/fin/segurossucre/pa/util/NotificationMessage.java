package ec.com.def.pa.util;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage {
	private String messageDefinition;
	private String messageId;
	private String brokerName;
	private String queueName;
	private String isVirtual;
	private String version;
	private String actionVersion;
	private String actionName;
	private String referenceCode;
	private String template;
	private String stringBindings;
	private List<String> tos;
	private String fromEmail;
	private List<String> ccs;
	private String replyTo;
	private String subject;
	private String numeroTramite;
	private String codigoASiniestro;
	private String codigoACosecha;
	private String codigoNegativa;
	private String numeroReclamo;
	private String valorPagado;
	private String nombreAsegurado;
	private String cedulaAsegurado;
	
	public NotificationMessage() {
		messageId="";
		brokerName="";
		queueName="";
		isVirtual="";
		version="";
		actionVersion="";
		actionName="";
		referenceCode="";
		template="";
		stringBindings="";
		tos = new ArrayList<>();
		tos.add("");
		fromEmail="";
		ccs = new ArrayList<>();
		ccs.add("");
		replyTo="";
		subject="";
		numeroTramite="";
		codigoASiniestro="";
		codigoACosecha="";
		codigoNegativa="";
		numeroReclamo="";
		valorPagado="";
		nombreAsegurado="";
		cedulaAsegurado="";
		
	}
	
	public NotificationMessage(String messageId,String brokerName,String queueName,String isVirtual,String version, String actionVersion,
			String actionName,String referenceCode, String template,String stringBindings,List<String> tos,String fromEmail,List<String> ccs,
			String replyTo,String subject, String numeroTramite,String codigoASiniestro,String codigoACosecha,String codigoNegativa,
			String numeroReclamo,String valorPagado,String nombreAsegurado,String cedulaAsegurado) {
		this.messageId=messageId==null?"":messageId;
		this.brokerName=brokerName==null?"":brokerName;
		this.queueName=queueName==null?"":queueName;
		this.isVirtual=isVirtual==null?"":isVirtual;
		this.version=version==null?"":version;
		this.actionVersion=actionVersion==null?"":actionVersion;
		this.actionName=actionName==null?"":actionName;
		this.referenceCode=referenceCode==null?"":referenceCode;
		this.template=template==null?"":template;
		this.stringBindings=stringBindings==null?"":stringBindings;
		if (tos==null) {
			this.tos = new ArrayList<>();
			this.tos.add("");
		}else {
			this.tos = tos;				
		}		
		this.fromEmail=fromEmail==null?"":fromEmail;		
		this.ccs = ccs;
		this.replyTo=replyTo==null?"":replyTo;
		this.subject=subject==null?"":subject;
		this.numeroTramite=numeroTramite==null?"":numeroTramite;
		this.codigoASiniestro=codigoASiniestro==null?"":codigoASiniestro;
		this.codigoACosecha=codigoACosecha==null?"":codigoACosecha;
		this.codigoNegativa=codigoNegativa==null?"":codigoNegativa;
		this.numeroReclamo=numeroReclamo==null?"":numeroReclamo;
		this.valorPagado=valorPagado==null?"":valorPagado;
		this.nombreAsegurado=nombreAsegurado==null?"":nombreAsegurado;
		this.cedulaAsegurado=cedulaAsegurado==null?"":cedulaAsegurado;
	}
	
	public String buildSms() {
		StringBuilder toss = new StringBuilder("\""+this.tos.get(0)+"\"");	
		List<String> listTos = this.tos;
		listTos.remove(0);
		listTos.forEach(p->toss.append(toss + ","+"\""+p+"\""));		
		toss.append("["+tos+"]");			
		String stringBindingss = "[[\"--numeroTramite--\",\""+numeroTramite+"\"],[\"--codigoASiniestro--\","
				+ "\""+codigoASiniestro+"\"],[\"--codigoACosecha--\",\""+codigoACosecha+"\"],"
				+ "[\"--codigoNegativa--\",\""+codigoNegativa+"\"],[\"--numeroReclamo--\",\""+numeroReclamo+"\"],"
				+ "[\"--valorPagado--\",\""+valorPagado+"\"],[\"--nombreAsegurado--\",\""+nombreAsegurado+"\"],"
				+ "[\"--cedulaAsegurado--\",\""+cedulaAsegurado+"\"]]";
		String base ="\"messageId\":\"" +messageId+ "\","
				+ "\"brokerName\":\"" +brokerName+ "\","
				+ "\"queueName\":\"" +queueName+ "\","
				+ "\"isVirtual\":\"" +isVirtual+ "\","
				+ "\"version\":\"" +version+ "\",";
		String action ="\"action\":{\"actionName\":\""+actionName+"\",\"version\":\""+actionVersion+"\"},";
		String content="\"content\":{\"referenceCode\":\""+referenceCode+"\",\"template\":\""+template+"\",\"stringBindings\":"+stringBindingss+",\"tos\":"+toss+"}";
		messageDefinition="{"+base+action+content+"}";
		
		return messageDefinition;
	}
	
	public String buildEmail() {
		StringBuilder toss = new StringBuilder("\""+this.tos.get(0)+"\"");	
		List<String> listTos = this.tos;
		listTos.remove(0);
		listTos.forEach(p->	toss.append(toss + ","+"\""+p+"\"")	);		
		toss.append("["+tos+"]");
		String ccsLoc ="";
		
		if(this.ccs!=null && !this.ccs.isEmpty() && this.ccs.toString()!=null) {
			ccsLoc = ",\"ccs\":" + this.ccs.toString();
		}
		String stringBindingss = "[[\"--numeroTramite--\",\""+numeroTramite+"\"],[\"--codigoASiniestro--\","
				+ "\""+codigoASiniestro+"\"],[\"--codigoACosecha--\",\""+codigoACosecha+"\"],"
				+ "[\"--codigoNegativa--\",\""+codigoNegativa+"\"],[\"--numeroReclamo--\",\""+numeroReclamo+"\"],"
				+ "[\"--valorPagado--\",\""+valorPagado+"\"],[\"--nombreAsegurado--\",\""+nombreAsegurado+"\"],"
				+ "[\"--cedulaAsegurado--\",\""+cedulaAsegurado+"\"]]";
		String base ="\"messageId\":\"" +messageId+ "\","
				+ "\"brokerName\":\"" +brokerName+ "\","
				+ "\"queueName\":\"" +queueName+ "\","
				+ "\"isVirtual\":\"" +isVirtual+ "\","
				+ "\"version\":\"" +version+ "\",";
		String action ="\"action\":{\"actionName\":\""+actionName+"\",\"version\":\""+actionVersion+"\"},";
		String content="\"content\":{\"referenceCode\":\""+referenceCode+"\",\"template\":\""+template+"\",\"stringBindings\":"+stringBindingss+"},";
		String emailDefinition = "\"emailDefinition\": {\"hasFiles\":false,\"fromEmail\":\""+fromEmail+"\",\"tos\":" +toss.toString()
				+ccsLoc+",\"replyTo\": \""+replyTo+"\", \"subject\": \""+subject+"\"}";
		messageDefinition="{"+base+action+content+emailDefinition+"}";
				
		return messageDefinition;
	}
	
	public static String buildMensajeDefinicion(List<String> str) {
		StringBuilder mensaje = new StringBuilder(str.get(0));
		for(int s=1; s<str.size(); s++) {
			mensaje.append(mensaje + ","+ str.get(s));		
		}
		mensaje.append("["+mensaje+"]");
		return mensaje.toString();
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getIsVirtual() {
		return isVirtual;
	}
	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getActionVersion() {
		return actionVersion;
	}
	public void setActionVersion(String actionVersion) {
		this.actionVersion = actionVersion;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getStringBindings() {
		return stringBindings;
	}
	public void setStringBindings(String stringBindings) {
		this.stringBindings = stringBindings;
	}
	public List<String> getTos() {
		return tos;
	}
	public void setTos(List<String> tos) {
		this.tos = tos;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public List<String> getCcs() {
		return ccs;
	}
	public void setCcs(List<String> ccs) {
		this.ccs = ccs;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public String getCodigoASiniestro() {
		return codigoASiniestro;
	}
	public void setCodigoASiniestro(String codigoASiniestro) {
		this.codigoASiniestro = codigoASiniestro;
	}
	public String getCodigoACosecha() {
		return codigoACosecha;
	}
	public void setCodigoACosecha(String codigoACosecha) {
		this.codigoACosecha = codigoACosecha;
	}
	public String getCodigoNegativa() {
		return codigoNegativa;
	}
	public void setCodigoNegativa(String codigoNegativa) {
		this.codigoNegativa = codigoNegativa;
	}
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	public String getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(String valorPagado) {
		this.valorPagado = valorPagado;
	}
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}
	public String getCedulaAsegurado() {
		return cedulaAsegurado;
	}
	public void setCedulaAsegurado(String cedulaAsegurado) {
		this.cedulaAsegurado = cedulaAsegurado;
	}

	public String getDefinition() {
		return messageDefinition;
	}

	public void setDefinition(String messageDefinition) {
		this.messageDefinition = messageDefinition;
	}
	
}
