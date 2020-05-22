package ec.com.def.pa.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

@WebServlet(
        urlPatterns = "/SwaggerServlet",
        loadOnStartup = 1,
        asyncSupported = true
        )

public class SwaggerServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) {
		
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/poliza-agricola-rest/resources/");
        beanConfig.setTitle("SINIESTRO AGRICOLA - API REFERENCES");
        beanConfig.setDescription("MODULO PARA SINIESTRO AGRICOLA");
        beanConfig.setScan(true);
    }

}
