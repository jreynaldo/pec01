package com.mindwaresrl.egpp.v1.core;

import java.util.ArrayList;
import java.util.List;


public class Propietario implements Entidad {
	
	public static final Integer NRO_CAMPOS = 4;
	public static final Integer INDICE_ID = 0;
	public static final Integer INDICE_NOMBRE = 1;
	public static final Integer INDICE_POBLACION = 2;
	public static final Integer INDICE_EMAIL = 3;	

	private String id;
	private String nombre;
	private String poblacion;
	private String email;
	private List<Propiedad> propiedades = new ArrayList<>();
	
	public Propietario(String id, String nombre, String poblacion, String email){
		this.id = id;
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.email = email;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	@Override
	public String toString() {
		return "Propietario [id=" + id + ", nombre=" + nombre + ", poblacion=" + poblacion + ", email=" + email
				+ ", propiedades=" + propiedades + "]";
	}
	
	

	
	
}
