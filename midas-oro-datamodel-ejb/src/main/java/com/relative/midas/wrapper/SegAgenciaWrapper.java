package com.relative.midas.wrapper;

import java.io.Serializable;

public class SegAgenciaWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8735843211615442123L;
	
	private Long id;
	private String descripcion;
	private String nombre;
	
	public SegAgenciaWrapper() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
