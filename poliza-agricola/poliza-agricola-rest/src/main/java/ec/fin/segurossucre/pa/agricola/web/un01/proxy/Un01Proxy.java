package ec.com.def.pa.agricola.web.un01.proxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.util.main.BaseWrapper;
import ec.com.def.pa.agricola.web.un01.rest.Un01RestInt;
import ec.com.def.pa.wrapper.UN01ColeccionWrapper;

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
	
	public static UN01ColeccionWrapper createUsuario(String serverUrl, UN01ColeccionWrapper  un01) throws DefException{
		log.info("========================createUsuario========================");
		ResteasyClient client = new ResteasyClientBuilder().build();
		Un01RestInt res= client.target(serverUrl + REST_PATH).proxy( Un01RestInt.class );
		BaseWrapper<UN01ColeccionWrapper> wrap = new BaseWrapper<>();
		wrap.setEntidad( un01 );
		log.info("====>AGREGO REGISTRO " );
		return wrap.getEntidad();
	}
	


}
