package com.mindwaresrl.egpp.v1.core;

import java.util.Collections;
import java.util.Map;


public abstract class Propiedad implements Entidad {

	public static final Integer INDICE_ID = 1;
	public static final Integer INDICE_METROS_CUADRADOS = 2;
	public static final Integer INDICE_ID_PROPIETARIO = 3;
	public static final Integer INDICE_ZONA_REPARTO = 4;
	
	private String id;
	private Double metrosCuadrados; 
	private Propietario propietario;
	private Map<ZonaReparto, Double> porcentajePorZona = Collections.emptyMap();
	
	public Propiedad(String id, Double metrosCuadrados, Propietario propietario, Map<ZonaReparto, Double> porcentajePorZona){
		this.id = id;
		this.metrosCuadrados = metrosCuadrados;
		this.propietario = propietario;
		this.porcentajePorZona = porcentajePorZona;
		this.propietario.getPropiedades().add(this);
	}
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Double getMetrosCuadrados() {
		return metrosCuadrados;
	}
	public void setMetrosCuadrados(Double metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	public Map<ZonaReparto, Double> getPorcentajePorZona() {
		return porcentajePorZona;
	}
	public void setPorcentajePorZona(Map<ZonaReparto, Double> porcentajePorZona) {
		this.porcentajePorZona = porcentajePorZona;
	}
}
