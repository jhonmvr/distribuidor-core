package ec.com.def.pa.wrapper;

import java.io.Serializable;

public class CatalogosGeneralWrapper implements Serializable {
	private static final long serialVersionUID = -2603633238095180178L;
	private String  codigo ;
	private String  descripcion;
	private String nombreCatalogo;
	
	public String getNombreCatalogo() {
		return nombreCatalogo;
	}
	public void setNombreCatalogo(String nombreCatalogo) {
		this.nombreCatalogo = nombreCatalogo;
	}
	public CatalogosGeneralWrapper() {

	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public CatalogosGeneralWrapper(String codigo, String descripcion) {

		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
