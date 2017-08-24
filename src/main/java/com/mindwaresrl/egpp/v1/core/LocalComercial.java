package com.mindwaresrl.egpp.v1.core;

import java.util.Map;


public class LocalComercial extends Propiedad {

	public static final Integer NRO_CAMPOS = 7;	
	public static final Integer INDICE_NOMBRE = 5;
	public static final Integer INDICE_ACTIVIDAD = 6;
	
	
	public LocalComercial(String id, Double metrosCuadrados, Propietario propietario, Map<ZonaReparto, Double> porcentajePorZona
						  , String nombre, String actividad){
		
		super(id, metrosCuadrados,propietario, porcentajePorZona);
		this.nombre = nombre;
		this.actividad = actividad;
	}
	
	private String nombre;
	private String actividad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

}
