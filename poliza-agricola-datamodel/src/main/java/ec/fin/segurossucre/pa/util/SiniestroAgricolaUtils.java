package ec.fin.segurossucre.pa.util;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.util.main.Constantes;

public class SiniestroAgricolaUtils {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	private SiniestroAgricolaUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String convertListToString(List<String> strings) {
		StringBuilder tmp = new StringBuilder("");
		if (strings != null && !strings.isEmpty()) {
			for (String item : strings) {
				tmp.append(item + ",");
			}
		}
		return tmp.toString();
	}

	/**
	 * transforma una fecha a texto
	 * 
	 * @param date
	 * @return 4 de julio del 2019
	 * @throws RelativeException
	 */
	public static String dateToFullString(Date date) throws SegSucreException {
		try {
			String[] mes = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
					"octubre", "noviembre", "diciembre" };
			String fechaSalida;
			fechaSalida = String.valueOf(date.getDate()).concat(" de ").concat(mes[date.getMonth()]).concat(" del ")
					.concat(String.valueOf(date.getYear() + 1900));
			return fechaSalida;
		} catch (Exception e) {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE TRANSFORMAR A TEXTO");
		}
	}

	/**
	 * formate dd/MM/YYYY 31/12/2020
	 * 
	 * @param date
	 * @return
	 * @throws SegSucreException
	 */
	public static String dateToString(Date date) throws SegSucreException {
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				StringBuilder str = new StringBuilder();
				str.append(String.valueOf(Calendar.DATE));
				str.append("/");
				str.append(String.valueOf(cal.get(Calendar.MONTH) + 1));
				str.append("/");
				str.append(String.valueOf(cal.get(Calendar.YEAR)));
				return str.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SegSucreException("dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}

	public static Date adicionEnDias(Date fecha, Long numeroDias) {
		Date fechaNueva;
		fechaNueva = new Date((fecha.getTime() / 86400000 + numeroDias) * 86400000);
		return fechaNueva;
	}

	public static String dateToString(Date date, String format) throws SegSucreException {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(date);
		} catch (Exception e) {
			throw new SegSucreException("dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}

	public static Long diasFecha(Date fechaIni, Date fechaFin) {
		return ((fechaFin.getTime() - fechaIni.getTime()) / 86400000);
	}

	public static Date formatSringToDate(String date) throws SegSucreException {
		try {
			SimpleDateFormat dfIn = new SimpleDateFormat(DATE_FORMAT);
			return new Date(dfIn.parse(date).getTime());
		} catch (ParseException e) {
			throw new SegSucreException("ERROR AL CONVERTIR FECHA USE EL FORMATO: YYYY-MM-DD");
		}

	}

	/**
	 * formate yyyy-MM-dd 2020-12-31
	 * 
	 * @param date
	 * @return
	 * @throws SegSucreException
	 */
	public static String dateToStringFormat(Date date) throws SegSucreException {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String ano = String.valueOf(cal.get(Calendar.YEAR));
			String mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String dia = String.valueOf(cal.get(Calendar.DATE));

			String fecha = "";
			fecha = fecha.concat(ano).concat("-").concat(mes).concat("-").concat(dia);
			// format YYYY-MM-DD
			return fecha;
		} catch (Exception e) {
			throw new SegSucreException(
					"SmsESRestController dateToStringFormat ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}

	public static Date formatSringToDateFull(String date) throws SegSucreException {
		try {
			SimpleDateFormat dfIn = new SimpleDateFormat(DATE_FORMAT_FULL);
			return new Date(dfIn.parse(date).getTime());
		} catch (ParseException e) {
			throw new SegSucreException(
					"SmsESRestController formatearCadenaFechaDate ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getEnumFromString(Class<T> enumClass, String value) {
		if (enumClass == null) {
			throw new IllegalArgumentException("EnumClass value can't be null.");
		}

		for (Enum<?> enumValue : enumClass.getEnumConstants()) {
			if (enumValue.toString().equalsIgnoreCase(value)) {
				return (T) enumValue;
			}
		}

		// Construct an error message that indicates all possible values for the enum.
		StringBuilder errorMessage = new StringBuilder();
		boolean bFirstTime = true;
		for (Enum<?> enumValue : enumClass.getEnumConstants()) {
			errorMessage.append(bFirstTime ? "" : ", ").append(enumValue);
			bFirstTime = false;
		}
		throw new IllegalArgumentException(value + " is invalid value. Supported values are " + errorMessage);
	}

	/**
	 * Convierte la cadena que viene en base 64 a un arreglo de bytes
	 * 
	 * @param base64
	 * @return
	 */
	public static byte[] convertBase64ToByteArray(String base64) throws SegSucreException {
		try {
			return Base64.getDecoder().decode(base64);
		} catch (Exception e) {
			throw new SegSucreException("ERROR AL CONVERTIR LA BASE 64 STRING EN ARREGLO DE BYTES");
		}
	}

	public static String encodeBase64(String normal) throws SegSucreException {
		try {
			return new String(Base64.getEncoder().encode(normal.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			throw new SegSucreException("ERROR AL CONVERTIR STRING A LA BASE 64  EN ARREGLO DE BYTES");
		}
	}

	public static String encodeMd5(String normal) throws SegSucreException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(normal.getBytes(StandardCharsets.UTF_8));
			return new String(thedigest);
		} catch (Exception e) {
			throw new SegSucreException("ERROR AL CONVERTIR LA MD5 STRING EN ARREGLO DE BYTES");
		}
	}

	private static <T> T map(Class<T> type, Object[] tuple) throws SegSucreException {
		List<Class<?>> tupleTypes = new ArrayList<>();
		for (Object field : tuple) {
			tupleTypes.add(field.getClass());
		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
		} catch (Exception e) {
			throw new SegSucreException("ERROR MAP", e.getMessage());
		}
	}

	private static <T> List<T> map(Class<T> type, List<Object[]> records) throws SegSucreException {
		List<T> result = new LinkedList<>();
		for (Object[] record : records) {
			result.add(map(type, record));
		}
		return result;
	}

	public static <T> List<T> getResultList(List<Object[]> records, Class<T> type) throws SegSucreException {
		return map(type, records);
	}

	public static BigDecimal convertStringToBigDecimal(String valor, int escala) {
		if (valor != null && !valor.isEmpty()) {
			return BigDecimal.valueOf(Double.valueOf(valor)).setScale(escala, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public static String convertToString(Object obj) {
		if (obj != null) {
			return String.valueOf(obj);
		}
		return "";
	}

	public static boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) 
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
		}

		return cedulaCorrecta;
	}
	
	
	public static boolean validateEmail(String email){
		 String ePattern = "^.+@.+$";
         Pattern p = java.util.regex.Pattern.compile(ePattern);
         Matcher m = p.matcher(email);
         return m.matches();
		
	}

}
