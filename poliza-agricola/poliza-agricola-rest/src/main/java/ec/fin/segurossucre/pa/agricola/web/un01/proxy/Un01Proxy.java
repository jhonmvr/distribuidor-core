package ec.fin.segurossucre.pa.agricola.web.un01.proxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.BaseWrapper;
import ec.fin.segurossucre.pa.agricola.web.un01.rest.Un01RestInt;
import ec.fin.segurossucre.pa.wrapper.UN01ColeccionWrapper;

/**
 * Clase utilizada para acceso a los servicios de seguridad
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 */
public class Un01Proxy {
	static final String REST_PATH = "/sucreagrop/rest";
	private static Log log = LogFactory.getLog(Un01Proxy.class);
	public static void main(String[] args) {
		//NO CODE
	}
	
	public static UN01ColeccionWrapper createUsuario(String serverUrl, UN01ColeccionWrapper  un01) throws SegSucreException{
		log.info("========================createUsuario========================");
		ResteasyClient client = new ResteasyClientBuilder().build();
		Un01RestInt res= client.target(serverUrl + REST_PATH).proxy( Un01RestInt.class );
		BaseWrapper<UN01ColeccionWrapper> wrap = new BaseWrapper<>();
		wrap.setEntidad( un01 );
		log.info("====>AGREGO REGISTRO " );
		return wrap.getEntidad();
	}
	


}
