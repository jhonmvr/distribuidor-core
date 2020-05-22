package ec.com.def.pa.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class ReporteTicketsServlet
 */
@WebServlet("/downloadSiniestroFileServlet")
public class DownloadFileSiniestroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(DownloadFileSiniestroServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFileSiniestroServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e) {
			//
		}

	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF ");
			String idSiniestro = request.getParameter("idSiniestro");
			String tipo = request.getParameter("tipo");
			log.info("=========>PARAMETRO idSiniestro " + idSiniestro);
			log.info("=========>PARAMETRO tipo " + tipo);
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 7");
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 9");
			response.setContentType("application/pdf");
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 10");
			response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.pdf");
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 11");
			response.setContentLength((int) 0);
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 12");
			ByteArrayInputStream fileInputStream = new ByteArrayInputStream(null);
			OutputStream responseOutputStream = response.getOutputStream();
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 13");
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
		} catch (IOException  e) {
			//
		}

	}

}
