package com.mindwaresrl.egpp.v1.core;

public class Comunidad implements Entidad {
	
	public static final Integer NRO_CAMPOS = 3;
	public static final Integer INDICE_ID = 0;
	public static final Integer INDICE_NOMBRE = 1;
	public static final Integer INDICE_POBLACION = 2;
	
	private String id;
	private String nombre;
	private String poblacion;
	
	public Comunidad(String id, String nombre, String poblacion){
		this.id =  id;
		this.nombre = nombre ;
		this.poblacion = poblacion;
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
}
