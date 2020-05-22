package ec.com.def.pa.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.com.def.core.util.main.Constantes;
import ec.com.def.core.util.main.ManagePropertiesFile;
import ec.com.def.pa.service.ReportService;
import ec.com.def.pa.util.SiniestroAgricolaConstantes;



/**
 * Servlet implementation class ReporteTicketsServlet
 */
@WebServlet("/reportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Log log = LogFactory.getLog(ReportServlet.class);
	private static final String SUBREPORTONENAME= "subReportOneName";
	private static final String SUBREPORTTWONAME= "subReportTwoName";
	private static final String SUBREPORTTHREENAME= "subReportThreeName";
	private static final String FORMAT ="format"; 
	
	@Inject
	private ReportService reportService;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost( request,response );
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		log.info("=========>ENTRA EN SERVELT REPORTE PDF ");
	String mainReportName=request.getParameter( "mainReportName" );
		String subReportOneName=request.getParameter( SUBREPORTONENAME );
		String subReportTwoName=request.getParameter( SUBREPORTTWONAME );
		String subReportThreeName=request.getParameter( SUBREPORTTHREENAME );
		String format=request.getParameter( FORMAT );
		Boolean isConsulta=request.getParameter( "consulta" )!= null && !request.getParameter( "consulta" ).isEmpty();
		log.info("=========>Es consulta " + isConsulta);
		log.info("=========>path properties " + System.getProperty(
				SiniestroAgricolaConstantes.JBOSS_CONFIG_DIR_PROPS) + 
				SiniestroAgricolaConstantes.SINIESTRO_AGRICOLA_CONF_DIR+ 
				SiniestroAgricolaConstantes.FILE_NAME_NOTIFICACION_FILE_PROPS );
		try {
			Map<String, Object> map = this.getParameters(request);
			Properties props = ManagePropertiesFile.loadProperties(SiniestroAgricolaConstantes.FILE_NAME_NOTIFICACION_FILE_PROPS,
					System.getProperty(
					SiniestroAgricolaConstantes.JBOSS_CONFIG_DIR_PROPS) + 
					SiniestroAgricolaConstantes.SINIESTRO_AGRICOLA_CONF_DIR 
					 );
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 7");


			map.put("FILE_LOGO", props.getProperty(SiniestroAgricolaConstantes.SINIESTRO_LOGO_PATH_PROPS) );
			map.put("FIRMA_PATH", props.getProperty(SiniestroAgricolaConstantes.SINIESTRO_FIRMA_PATH_PROPS) );			
			map.put(SUBREPORTONENAME,  props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +subReportOneName ) );
			map.put(SUBREPORTTWONAME, props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +subReportTwoName ) );
			map.put(SUBREPORTTHREENAME, props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +subReportThreeName ));
			
			
			byte[] reportFile = null;
			
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8 " + props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +mainReportName ));
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8.1 " + SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +mainReportName );
			if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
				reportFile = this.reportService.generateReporteFromBeanPDF(null//sins
						, map, 
						props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +mainReportName ));
				log.info("=========>ENTRA EN SERVELT REPORTE PDF 9");
				response.setContentType("application/pdf");
				log.info("=========>ENTRA EN SERVELT REPORTE PDF 10");
				response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.pdf");
			} else {
				reportFile = this.reportService.generateReporteBeanCsv(null//sins
						,map,
						props.getProperty( SiniestroAgricolaConstantes.PREFIX_REPORT_MAIN_PATH +mainReportName )) ;
				log.info("=========>ENTRA EN SERVELT REPORTE EXCEL 9");
				response.setContentType("text/csv");
				log.info("=========>ENTRA EN SERVELT REPORTE EXCCEL 10");
				response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.csv");
			}			
			
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 11");
			response.setContentLength((int) reportFile.length);
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 12");
			ByteArrayInputStream fileInputStream = new ByteArrayInputStream(reportFile);
			OutputStream responseOutputStream = response.getOutputStream();
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 13");
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				streamWrite(responseOutputStream, bytes);
			}
		}catch (Exception  e) {
			//
		}
		
	}

	private void streamWrite(OutputStream responseOutputStream, int bytes) throws ServletException {
		try {
			responseOutputStream.write(bytes);
		} catch (Exception e) {
			throw new ServletException("====>error dopost   ",e) ;
		}
	}
	
	
	
	private Map<String, Object> getParameters(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String fechaDesde=request.getParameter( "fechaDesde" );
		String fechaHasta=request.getParameter( "fechaHasta" );
		String numeroTramite=request.getParameter( "numeroTramite" );
		String identificacion=request.getParameter( "identificacion" );
		String causa=request.getParameter( "causa" );
		String estado=request.getParameter( "estado" );
		String canal=request.getParameter( "canal" );
		String codigoPoliza=request.getParameter( "codigoPoliza" );
		String idSiniestro=request.getParameter( "idSiniestro" );
		String format=request.getParameter( FORMAT );
		
		String noOficio=request.getParameter("No_OFICIO");
		String asegurado=request.getParameter("ASEGURADO");
		String canal1=request.getParameter("CANAL");
		String noReclamo=request.getParameter("No_RECLAMO");
		String noTramite=request.getParameter("No_TRAMITE");
		String provincia=request.getParameter("PROVINCIA");
		String cultivo=request.getParameter("CULTIVO");
		String causa1=request.getParameter("CAUSA");  
		String fechaOcurrencia=request.getParameter("FECHA_OCURRENCIA");
		String rendimiento=request.getParameter("RENDIMIENTO");
		String valorAjuste=request.getParameter("VALOR_AJUSTE");
		String valorCosecha=request.getParameter("VALOR_COSECHA");
		String montoRealinver=request.getParameter("MONT_REAL_INV");
		String cultivoPoliza=request.getParameter("CULTIVO_POLIZA");
		String cultivoInspeccion=request.getParameter("CULTIVO_INSPEC");
		String sitioContratado=request.getParameter("SITIO_CONTRATADO");
		String sitioInspeccion=request.getParameter("SITIO INSPECCION");
		String fechaIniciovigencia=request.getParameter("FECH_INI_VIGENCIA");
		String fechaFinvigencia=request.getParameter("FECH_FIN_VIGENCIA");		
		String fechaAvisocosecha=request.getParameter("FECHA_AVI_COSE"); 
		String diasPresentadoaviso=request.getParameter("DIAS_PRESENT_AVISO"); 

		
		log.info("=========>PARAMETRO fechaDesde " + fechaDesde);
		log.info("=========>PARAMETRO fechaHasta " + fechaHasta);
		log.info("=========>PARAMETRO numeroTramite " + numeroTramite);
		log.info("=========>PARAMETRO identificacion " + identificacion);
		log.info("=========>PARAMETRO causa " + causa);
		log.info("=========>PARAMETRO estado " + estado);
		log.info("=========>PARAMETRO CULTIVO " + cultivo);
		
		map.put("fechaDesde", fechaDesde);
		map.put("fechaHasta", fechaHasta);
		map.put("numeroTramite", numeroTramite);
		map.put("identificacion", identificacion);
		map.put("causa", causa);
		map.put("canal", canal);
		map.put("estado", estado);
		map.put("idSiniestro", idSiniestro);	
		map.put("codigoPoliza", codigoPoliza);	
		map.put(FORMAT, format);
		map.put("No_OFICIO", noOficio);
		map.put("ASEGURADO", asegurado);
		map.put("CANAL", canal1);
		map.put("No_RECLAMO", noReclamo);
		map.put("No_TRAMITE", noTramite);
		map.put("PROVINCIA", provincia);
		map.put("CULTIVO", cultivo);
		map.put("CAUSA", causa1);
		map.put("FECHA_OCURRENCIA", fechaOcurrencia);
		map.put("RENDIMIENTO", rendimiento);
		map.put("VALOR_AJUSTE", valorAjuste);
		map.put("VALOR_COSECHA", valorCosecha);
		map.put("MONT_REAL_INV", montoRealinver);
		map.put("CULTIVO_POLIZA", cultivoPoliza);
		map.put("CULTIVO_INSPEC", cultivoInspeccion);
		map.put("SITIO_CONTRATADO", sitioContratado);
		map.put("SITIO INSPECCION", sitioInspeccion);
		map.put("FECH_INI_VIGENCIA", fechaIniciovigencia);
		map.put("FECH_FIN_VIGENCIA", fechaFinvigencia);
		map.put("FECHA_AVI_COSE", fechaAvisocosecha);
		map.put("DIAS_PRESENT_AVISO", diasPresentadoaviso);
		return map;
	}
	
	
}
