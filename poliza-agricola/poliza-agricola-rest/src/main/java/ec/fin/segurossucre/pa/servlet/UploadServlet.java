package ec.fin.segurossucre.pa.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.fin.segurossucre.core.util.main.Constantes;
import ec.fin.segurossucre.pa.wrapper.FileWrapper;

@WebServlet("/uploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(UploadServlet.class);

	@Override
	public void init(ServletConfig config) {

		new File("C:/Users/root/AppData/Local/Temp/");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("==============> entra a metodo get en servlet con get file " + request.getParameter("getfile"));
		log.info("==============> entra a metodo get en servlet con referencia " + request.getParameter("referencia"));

		@SuppressWarnings("unchecked")
		Map<String, FileWrapper> files = (Map<String, FileWrapper>) request.getSession(false)
				.getAttribute(Constantes.FILE_UPLOAD_SESSION_ATTRIB);
		if (files == null) {
			files = new HashMap<>();
		}
		log.info("==============> Files registrado " + files.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("==============> entra a metodo post en servlet");
		log.info("==============> entra a metodo post en servlet con referencia " + request.getParameter("referencia"));
		log.info("==============> entra a metodo post en servlet 1 " + request.getSession());
		log.info("==============> entra a metodo post en servlet 1 "
				+ request.getSession().getAttribute(Constantes.FILE_UPLOAD_SESSION_ATTRIB));
		Map<String, FileWrapper> files = (Map<String, FileWrapper>) request.getSession()
				.getAttribute(Constantes.FILE_UPLOAD_SESSION_ATTRIB);
		log.info("==============> entra a metodo post en servlet " + files);
		if (files == null) {
			files = new HashMap<>();
		}
		log.info("==============> Files registrado " + files.toString());


	}

	/**
	 * Metodo que genera un objeto json de retorno
	 * 
	 * @return
	 */
	

	public String getMimeType(File file) {
		String mimetype = "";
		if (file.exists()) {
			if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
				mimetype = "image/png";
			} else {
				javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
				mimetype = mtMap.getContentType(file);
			}
		}
		return mimetype;
	}

	public String getMimeType(String file) {
		String mimetype = "";
		if (getSuffix(file).equalsIgnoreCase("png")) {
			mimetype = "image/png";
		} else {
			javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
			mimetype = mtMap.getContentType(file);
		}
		log.info("mimetype: " + mimetype);
		return mimetype;
	}

	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		return suffix;
	}

}
